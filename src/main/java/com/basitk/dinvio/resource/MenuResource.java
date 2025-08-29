package com.basitk.dinvio.resource;

import com.basitk.dinvio.dto.menu.MenuItemDataDto;
import com.basitk.dinvio.dto.menu.MenuItemListRequestDto;
import com.basitk.dinvio.dto.menu.MenuItemRequestDto;
import com.basitk.dinvio.dto.base.BaseResponseDto;
import com.basitk.dinvio.model.Category;
import com.basitk.dinvio.model.Menu;
import com.basitk.dinvio.security.JwtClaimExtractor;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/menu")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MenuResource {

    @Inject
    JwtClaimExtractor jwtClaimExtractor;

    @POST
    @Path("/add")
    @RolesAllowed("ADMIN")
    public Response addMenuItem(MenuItemRequestDto request) {
        if (request.name == null || request.name.isBlank() || request.price == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new BaseResponseDto(false, "Name and price are required", null))
                    .build();
        }

        String restaurantCode = jwtClaimExtractor.getRestaurantCode();
        String userId = jwtClaimExtractor.getUserId();

        Category category = Category.find("categoryId = ?1 and restaurantCode = ?2",
                new Object[]{request.categoryId, restaurantCode}).firstResult();
        if (category == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new BaseResponseDto(false, "Invalid category for this restaurant", null))
                    .build();
        }

        Menu existingMenu = Menu.find("name = ?1 and categoryId = ?2 and restaurantCode = ?3",
                new Object[]{request.name, request.categoryId, restaurantCode}).firstResult();

        if (existingMenu != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new BaseResponseDto(false, "Menu item already exists in this category", null))
                    .build();
        }

        Menu item = new Menu();
        item.name = request.name.trim();
        item.description = request.description;
        item.price = request.price;
        item.categoryId = request.categoryId;
        item.restaurantCode = restaurantCode;
        item.userId = userId;
        item.persist();

        String menuId = item.getMenuId();

        MenuItemDataDto menuItemData = new MenuItemDataDto(
                menuId,
                item.name,
                item.description,
                item.price,
                item.categoryId,
                restaurantCode,
                userId
        );

        return Response.ok(
                new BaseResponseDto(true, "Menu item added successfully", menuItemData)
        ).build();
    }

    @POST
    @Path("/bulk-add")
    @RolesAllowed("ADMIN")
    public Response bulkAddMenuItems(MenuItemListRequestDto request) {
        if (request.items == null || request.items.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new BaseResponseDto(false, "No menu items provided", null))
                    .build();
        }

        String restaurantCode = jwtClaimExtractor.getRestaurantCode();
        String userId = jwtClaimExtractor.getUserId();

        Set<String> requestUniqueKeys = new HashSet<>();
        for (MenuItemRequestDto req : request.items) {
            if (req.name == null || req.price == null || req.categoryId == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new BaseResponseDto(false,
                                "Each item must have name, price, and categoryId", null))
                        .build();
            }

            String key = req.name.trim().toLowerCase() + "|" + req.categoryId;
            if (!requestUniqueKeys.add(key)) {
                return Response.status(Response.Status.CONFLICT)
                        .entity(new BaseResponseDto(false,
                                "Duplicate menu item '" + req.name + "' found in request for category " + req.categoryId, null))
                        .build();
            }
        }

        for (MenuItemRequestDto req : request.items) {
            Category category = Category.find("_id = ?1 and restaurantCode = ?2",
                            new ObjectId(req.categoryId), restaurantCode)
                    .firstResult();
            if (category == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new BaseResponseDto(false,
                                "Invalid categoryId for item: " + req.name, null))
                        .build();
            }
        }

        List<String> names = request.items.stream()
                .map(req -> req.name.trim())
                .toList();

        List<Menu> existing = Menu.find("restaurantCode = ?1 and name in ?2", restaurantCode, names).list();
        if (!existing.isEmpty()) {
            List<String> existingNames = existing.stream()
                    .map(menu -> menu.name)
                    .toList();
            return Response.status(Response.Status.CONFLICT)
                    .entity(new BaseResponseDto(false,
                            "Menu items already exist for this restaurant: " + existingNames, null))
                    .build();
        }

        List<Menu> savedItems = new ArrayList<>();
        for (MenuItemRequestDto req : request.items) {
            Menu item = new Menu();
            item.name = req.name.trim();
            item.description = req.description;
            item.price = req.price;
            item.categoryId = req.categoryId;
            item.restaurantCode = restaurantCode;
            item.userId = userId;
            item.persist();
            savedItems.add(item);
        }

        List<MenuItemDataDto> responseList = savedItems.stream()
                .map(item -> new MenuItemDataDto(
                        item.getMenuId(),
                        item.name,
                        item.description,
                        item.price,
                        item.categoryId,
                        item.restaurantCode,
                        item.userId
                ))
                .toList();

        return Response.ok(
                new BaseResponseDto(true, "Bulk menu items added successfully", responseList)
        ).build();
    }

    @GET
    @Path("/all")
    @RolesAllowed({"ADMIN", "USER"})
    public Response getAllMenuItems() {
        List<Menu> items = Menu.listAll();

        List<MenuItemDataDto> menuItemData = items.stream()
                .map(item -> new MenuItemDataDto(
                        item.id.toString(),
                        item.name,
                        item.description,
                        item.price,
                        item.categoryId,
                        item.restaurantCode,
                        item.userId
                ))
                .toList();

        return Response.ok(
                new BaseResponseDto(true, "Menu items retrieved successfully", menuItemData)
        ).build();
    }
}