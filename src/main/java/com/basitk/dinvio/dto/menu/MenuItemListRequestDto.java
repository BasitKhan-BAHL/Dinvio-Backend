package com.basitk.dinvio.dto.menu;

import java.util.List;

public class MenuItemListRequestDto {
    public List<MenuItemRequestDto> items;

    public MenuItemListRequestDto() {
    }

    public MenuItemListRequestDto(List<MenuItemRequestDto> items) {
        this.items = items;
    }

    public List<MenuItemRequestDto> getItems() {
        return items;
    }

    public void setItems(List<MenuItemRequestDto> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "MenuItemListRequestDto{" +
                "items=" + items +
                '}';
    }
}
