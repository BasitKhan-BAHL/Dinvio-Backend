package com.basitk.dinvio.resource;

import com.basitk.dinvio.dto.base.BaseResponseDto;
import com.basitk.dinvio.dto.category.CategoryListRequestDto;
import com.basitk.dinvio.dto.category.CategoryRequestDto;
import com.basitk.dinvio.model.Category;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @POST
    @Path("/add")
    public Response addCategory(CategoryRequestDto request) {
        if (request.name == null || request.name.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new BaseResponseDto(false, "Category name is required", null))
                    .build();
        }

        Category category = new Category();
        category.name = request.name;
        category.description = request.description;
        category.icon = request.icon;
        category.color1 = request.color1;
        category.color2 = request.color2;
        category.persist();

        return Response.ok(
                new BaseResponseDto(true, "Category added successfully", null)
        ).build();
    }

    @POST
    @Path("/bulk-add")
    public Response bulkAddCategories(CategoryListRequestDto request) {
        if (request.items == null || request.items.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new BaseResponseDto(false, "No categories provided", null))
                    .build();
        }

        List<Category> savedCategories = new ArrayList<>();
        for (CategoryRequestDto req : request.items) {
            Category category = new Category();
            category.name = req.name;
            category.description = req.description;
            category.icon = req.icon;
            category.color1 = req.color1;
            category.color2 = req.color2;
            category.persist();
            savedCategories.add(category);
        }

        return Response.ok(
                new BaseResponseDto(true, "Bulk categories added successfully", savedCategories)
        ).build();
    }

    @GET
    @Path("/all")
    public Response getAllCategories() {
        List<Category> categories = Category.listAll();
        return Response.ok(
                new BaseResponseDto(true, "Categories retrieved successfully", categories)
        ).build();
    }
}
