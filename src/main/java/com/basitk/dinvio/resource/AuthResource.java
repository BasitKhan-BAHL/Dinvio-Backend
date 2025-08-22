package com.basitk.dinvio.resource;

import com.basitk.dinvio.dto.base.BaseResponseDto;
import com.basitk.dinvio.dto.login.LoginRequestDto;
import com.basitk.dinvio.dto.login.LoginDataDto;
import com.basitk.dinvio.dto.register.RegisterDataDto;
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
        String userId = user.getUserId();

        if (Roles.ADMIN.equals(user.roleId)) {
            token = jwtService.generateAdminToken(user.username, user.restaurantCode, userId, user.roleId);
        } else {
            token = jwtService.generateUserToken(user.username, user.restaurantCode, userId, user.roleId);
        }

        LoginDataDto loginData = new LoginDataDto(
                token,
                userId,
                user.username,
                user.roleId,
                Roles.getRoleName(user.roleId),
                user.restaurantCode
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

        if (!Roles.isValidRole(request.roleId)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new BaseResponseDto(false, "Invalid role ID provided", null))
                    .build();
        }

        User user = new User();
        user.username = request.username;
        user.password = BCrypt.hashpw(request.password, BCrypt.gensalt());
        user.roleId = request.roleId;
        user.restaurantCode = request.restaurantCode;
        user.persist();

        String userId = user.getUserId();
        RegisterDataDto registerData = new RegisterDataDto(userId);

        return Response.ok(
                new BaseResponseDto(true, "User registered successfully", registerData)
        ).build();
    }
}
