package com.basitk.dinvio.dto.register;

public class RegisterDataDto {
    public String userId;

    public RegisterDataDto() {
    }

    public RegisterDataDto(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RegisterDataDto{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
