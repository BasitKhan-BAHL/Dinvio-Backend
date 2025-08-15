package com.basitk.dinvio.resource;

import com.basitk.dinvio.dto.menu.MenuItemListRequestDto;
import com.basitk.dinvio.dto.menu.MenuItemRequestDto;
import com.basitk.dinvio.dto.base.BaseResponseDto;
import com.basitk.dinvio.model.Menu;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/menu")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MenuResource {

    @POST
    @Path("/add")
    public Response addMenuItem(MenuItemRequestDto request) {
        if (request.name == null || request.price == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new BaseResponseDto(false, "Name and price are required", null))
                    .build();
        }

        Menu item = new Menu();
        item.category = request.category;
        item.name = request.name;
        item.description = request.description;
        item.price = request.price;

        item.persist();

        return Response.ok(
                new BaseResponseDto(true, "Menu item added successfully", null)
        ).build();
    }

    @POST
    @Path("/bulk-add")
    public Response bulkAddMenuItems(MenuItemListRequestDto request) {
        if (request.items == null || request.items.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new BaseResponseDto(false, "No menu items provided", null))
                    .build();
        }

        List<Menu> savedItems = new ArrayList<>();
        for (MenuItemRequestDto req : request.items) {
            Menu item = new Menu();
            item.category = req.category;
            item.name = req.name;
            item.description = req.description;
            item.price = req.price;
            item.persist();
            savedItems.add(item);
        }

        return Response.ok(
                new BaseResponseDto(true, "Bulk menu items added successfully", savedItems)
        ).build();
    }

    @GET
    @Path("/all")
    public Response getAllMenuItems() {
        List<Menu> items = Menu.listAll();
        return Response.ok(
                new BaseResponseDto(true, "Menu items retrieved successfully", items)
        ).build();
    }
}
