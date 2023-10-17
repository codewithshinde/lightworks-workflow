package com.lw.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="dishTable")
public class Dish {

    @Id
    private String id;
    private String dishName;
    private String dishCost;
    private String dishDescription;
    private String xmlData;
}
