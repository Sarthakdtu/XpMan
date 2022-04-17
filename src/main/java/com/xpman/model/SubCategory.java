package com.xpman.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SubCategory implements BaseEntity{

    private Integer id;
    private String name;
    private String icon;
    private String category;

    public SubCategory(){}

    @Override
    public List toList() {
        return Arrays.asList(name, icon, category);
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
