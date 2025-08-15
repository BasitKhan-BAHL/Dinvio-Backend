package com.basitk.dinvio.dto.category;

public class CategoryRequestDto {
    public String name;
    public String description;
    public String icon;
    public String color1;
    public String color2;

    public CategoryRequestDto() {
    }

    public CategoryRequestDto(String name, String description,  String icon, String color1, String color2) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.color1 = color1;
        this.color2 = color2;
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

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    @Override
    public String toString() {
        return "CategoryRequestDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", color1='" + color1 + '\'' +
                ", color2='" + color2 + '\'' +
                '}';
    }
}
