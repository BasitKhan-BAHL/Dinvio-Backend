package com.basitk.dinvio.dto.category;

public class CategoryDataDto {
    public String categoryId;

    public CategoryDataDto() {
    }

    public CategoryDataDto(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "CategoryDataDto{" +
                "categoryId='" + categoryId + '\'' +
                '}';
    }
}
