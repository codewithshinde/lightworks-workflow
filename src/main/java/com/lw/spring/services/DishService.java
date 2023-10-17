package com.lw.spring.services;

import com.lw.spring.model.Dish;
import com.lw.spring.repository.DishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DishService {

    @Lazy
    @Autowired
    private DishRepo dishRepository;
    List<Dish> dishes = new ArrayList<>();

    public List<Dish> getAllDishes() {
        //return dishes;
        return (List<Dish>) dishRepository.findAll();
    }

    public Dish getDish(String dishName) {
        return dishRepository.findByDishName(dishName).get(0);
        //return dishes.stream().filter(t -> t.getDishName().equals(dishName)).findFirst().get();
    }

    public void addDish(Dish dish) {
        dishRepository.save(dish);
        //dishes.add(dish);
    }

    public void editDish(Dish dish) {
        for (Dish d : dishes) {
            if (d.getId().equals(dish.getId())) {
                d.setDishName(dish.getDishName());
                d.setDishCost(dish.getDishCost());
                d.setDishDescription(dish.getDishDescription());
            }
        }
    }

}
