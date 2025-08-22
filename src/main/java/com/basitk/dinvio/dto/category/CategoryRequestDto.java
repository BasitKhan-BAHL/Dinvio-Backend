package com.basitk.dinvio.dto.category;

public class CategoryRequestDto {
    public String name;
    public String description;
    public String icon;

    public CategoryRequestDto() {
    }

    public CategoryRequestDto(String name, String description,  String icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "CategoryRequestDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
