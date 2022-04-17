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
public class Account implements BaseEntity{
    private Integer id;
    private String name;
    private String color;
    private Double amount;
    private Double expenditure = 0.0;

    public Account(){ }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", amount=" + amount +
                ", expenditure=" + expenditure +
                '}';
    }

    @Override
    public List toList(){
        return Arrays.asList(name, color, amount, expenditure);
    }
}
