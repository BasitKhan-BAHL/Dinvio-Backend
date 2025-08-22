package com.basitk.dinvio.dto.category;

import java.util.List;

public class CategoryListRequestDto {
    public List<CategoryRequestDto> items;

    public CategoryListRequestDto() {
    }

    public CategoryListRequestDto(List<CategoryRequestDto> items) {
        this.items = items;
    }

    public List<CategoryRequestDto> getItems() {
        return items;
    }

    public void setItems(List<CategoryRequestDto> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CategoryListRequestDto{" +
                "items=" + items +
                '}';
    }
}
