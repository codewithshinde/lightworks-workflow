package com.lw.spring.model;

import lombok.Data;

@Data
public class DishRequest {
    private String uniqueId;
    private String dishName;
    private String dishCost;
    private String description;
}
