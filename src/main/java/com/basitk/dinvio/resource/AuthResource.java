package com.basitk.dinvio.resource;

import com.basitk.dinvio.dto.base.BaseResponseDto;
import com.basitk.dinvio.dto.login.LoginRequestDto;
import com.basitk.dinvio.dto.login.LoginDataDto;
import com.basitk.dinvio.dto.register.RegisterRequestDto;
import com.basitk.dinvio.model.User;
import com.basitk.dinvio.security.JwtService;
import com.basitk.dinvio.security.Roles;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    JwtService jwtService;

    @POST
    @Path("/login")
    public Response login(LoginRequestDto request) {
        User user = User.find("username", request.username).firstResult();

        if (user == null || !BCrypt.checkpw(request.password, user.password)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new BaseResponseDto(false, "Invalid username or password", null))
                    .build();
        }

        String token;
        if (Roles.ADMIN.equals(user.role)) {
            token = jwtService.generateAdminToken(user.username, user.restaurantCode, user.userId, user.roleId);
        } else {
            token = jwtService.generateUserToken(user.username, user.restaurantCode, user.userId, user.roleId);
        }

        LoginDataDto loginData = new LoginDataDto(
                token,
                user.username,
                user.role,
                user.restaurantCode,
                user.userId,
                user.roleId
        );

        return Response.ok(
                new BaseResponseDto(true, "Login Successfully", loginData)
        ).build();
    }

    @POST
    @Path("/register")
    public Response register(RegisterRequestDto request) {
        if (User.find("username", request.username).firstResult() != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new BaseResponseDto(false, "Username already exists", null))
                    .build();
        }

        if (User.find("user_id", request.userId).firstResult() != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new BaseResponseDto(false, "User ID already exists", null))
                    .build();
        }

        User user = new User();
        user.username = request.username;
        user.password = BCrypt.hashpw(request.password, BCrypt.gensalt());
        user.role = request.role;
        user.restaurantCode = request.restaurantCode;
        user.userId = request.userId;
        user.roleId = request.roleId;
        user.persist();

        return Response.ok(
                new BaseResponseDto(true, "User registered successfully", null)
        ).build();
    }
}
