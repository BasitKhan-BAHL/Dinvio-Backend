package com.basitk.dinvio.resource;

import com.basitk.dinvio.dto.base.BaseResponseDto;
import com.basitk.dinvio.dto.order.OrderItemRequestDto;
import com.basitk.dinvio.dto.order.OrderRequestDto;
import com.basitk.dinvio.model.Menu;
import com.basitk.dinvio.model.Order;
import com.basitk.dinvio.model.OrderItem;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @POST
    @Path("/place")
    public Response placeOrder(OrderRequestDto request) {
        if (request.items == null || request.items.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new BaseResponseDto(false, "Order must contain at least one item", null))
                    .build();
        }

        double totalPrice = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequestDto itemReq : request.items) {
            Menu menuItem = Menu.findById(new org.bson.types.ObjectId(itemReq.menuItemId));
            if (menuItem == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new BaseResponseDto(false, "Menu item not found: " + itemReq.menuItemId, null))
                        .build();
            }

            OrderItem orderItem = new OrderItem();
            orderItem.menuItemId = menuItem.id.toString();
            orderItem.name = menuItem.name;
            orderItem.quantity = itemReq.quantity;
            orderItem.price = menuItem.price;
            orderItem.subtotal = menuItem.price * itemReq.quantity;

            totalPrice += orderItem.subtotal;
            orderItems.add(orderItem);
        }

        Order order = new Order();
        order.restaurantCode = request.restaurantCode;
        order.items = orderItems;
        order.totalPrice = totalPrice;
        order.status = "Pending";
        order.date = LocalDate.now();
        order.time = LocalTime.now();

        order.persist();

        return Response.ok(
                new BaseResponseDto(true, "Order placed successfully", order)
        ).build();
    }
}
