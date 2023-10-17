package com.lw.spring.repository;
import com.lw.spring.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface DishRepo extends JpaRepository<Dish, String> {
    List<Dish> findByDishName(String dishName);
}
