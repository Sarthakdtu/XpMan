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
public class Category implements BaseEntity{

    private Integer id;
    private String icon;
    private String name;

    public Category(){}

    @Override
    public List toList() {
        return Arrays.asList(name, icon);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
