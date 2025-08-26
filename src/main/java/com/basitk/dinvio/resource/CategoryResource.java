package com.basitk.dinvio.resource;

import com.basitk.dinvio.dto.base.BaseResponseDto;
import com.basitk.dinvio.dto.category.CategoryDataDto;
import com.basitk.dinvio.dto.category.CategoryListRequestDto;
import com.basitk.dinvio.dto.category.CategoryRequestDto;
import com.basitk.dinvio.model.Category;
import com.basitk.dinvio.security.JwtClaimExtractor;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    JwtClaimExtractor jwtClaimExtractor;

    @POST
    @Path("/add")
    @RolesAllowed("ADMIN")
    public Response addCategory(CategoryRequestDto request) {
        if (request.name == null || request.name.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new BaseResponseDto(false, "Category name is required", null))
                    .build();
        }

        String restaurantCode = jwtClaimExtractor.getRestaurantCode();
        String userId = jwtClaimExtractor.getUserId();

        Category existing = Category.find("name = ?1 and restaurantCode = ?2",
                request.name, restaurantCode).firstResult();
        if (existing != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new BaseResponseDto(false,
                            "Category with this name already exists for this restaurant", null))
                    .build();
        }

        Category category = new Category();
        category.name = request.name;
        category.description = request.description;
        category.icon = request.icon;
        category.restaurantCode = restaurantCode;
        category.userId = userId;
        category.persist();

        String categoryId = category.getCategoryId();

        CategoryDataDto categoryData = new CategoryDataDto(
                categoryId,
                category.name,
                category.description,
                category.icon,
                restaurantCode,
                userId
        );

        return Response.ok(
                new BaseResponseDto(true, "Category added successfully", categoryData)
        ).build();
    }

    @POST
    @Path("/bulk-add")
    @RolesAllowed("ADMIN")
    public Response bulkAddCategories(CategoryListRequestDto request) {
        if (request.items == null || request.items.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new BaseResponseDto(false, "No categories provided", null))
                    .build();
        }

        String restaurantCode = jwtClaimExtractor.getRestaurantCode();
        String userId = jwtClaimExtractor.getUserId();

        Set<String> seenNames = new HashSet<>();
        for (CategoryRequestDto req : request.items) {
            if (req.name == null || req.name.isBlank()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new BaseResponseDto(false, "Category name is required for all items", null))
                        .build();
            }
            String lowerName = req.name.trim().toLowerCase();
            if (!seenNames.add(lowerName)) {
                return Response.status(Response.Status.CONFLICT)
                        .entity(new BaseResponseDto(false,
                                "Duplicate category '" + req.name + "' found in request", null))
                        .build();
            }
        }

        List<String> names = request.items.stream()
                .map(req -> req.name.trim())
                .toList();

        List<Category> existing = Category.find("restaurantCode = ?1 and name in ?2", restaurantCode, names).list();
        if (!existing.isEmpty()) {
            List<String> existingNames = existing.stream()
                    .map(cat -> cat.name)
                    .toList();
            return Response.status(Response.Status.CONFLICT)
                    .entity(new BaseResponseDto(false,
                            "Categories already exist for this restaurant: " + existingNames, null))
                    .build();
        }

        List<Category> savedCategories = new ArrayList<>();

        for (CategoryRequestDto req : request.items) {
            Category category = new Category();
            category.name = req.name.trim();
            category.description = req.description;
            category.icon = req.icon;
            category.restaurantCode = restaurantCode;
            category.userId = userId;
            category.persist();
            savedCategories.add(category);
        }

        List<CategoryDataDto> responseList = savedCategories.stream()
                .map(cat -> new CategoryDataDto(
                        cat.getCategoryId(),
                        cat.name,
                        cat.description,
                        cat.icon,
                        cat.restaurantCode,
                        cat.userId
                ))
                .toList();

        return Response.ok(
                new BaseResponseDto(true, "Bulk categories added successfully", responseList)
        ).build();
    }

    @GET
    @Path("/all")
    @RolesAllowed({"ADMIN", "USER"})
    public Response getAllCategories() {
        List<Category> categories = Category.listAll();

        List<CategoryDataDto> categoryData = categories.stream()
                .map(cat -> new CategoryDataDto(
                        cat.id.toString(),
                        cat.name,
                        cat.description,
                        cat.icon,
                        cat.restaurantCode,
                        cat.userId
                ))
                .toList();

        return Response.ok(
                new BaseResponseDto(true, "Categories retrieved successfully", categoryData)
        ).build();
    }
}
