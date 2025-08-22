package com.basitk.dinvio.dto.menu;

public class MenuItemDataDto {
    public String menuId;

    public MenuItemDataDto() {
    }

    public MenuItemDataDto(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "MenuDataDto{" +
                "menuId='" + menuId + '\'' +
                '}';
    }
}
