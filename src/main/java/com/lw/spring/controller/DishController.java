package com.lw.spring.controller;

import com.lw.spring.model.Dish;
import com.lw.spring.services.DishService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.XML;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class DishController {

    @Autowired
    private DishService dishService;

//    @Autowired
//    private WorkFlowService wfService;

    @RequestMapping("/dishes")
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @RequestMapping("/dishes/{id}")
    public Dish getDish(@PathVariable String id){
        Dish dishWIthXML = dishService.getDish(id);
        String xmlPayload = dishWIthXML.getXmlData();
        JSONObject xmlJson = XML.toJSONObject(xmlPayload);
        log.info("xm to JSON here"+xmlJson+" Note  "+xmlJson.get("note"));
        return dishWIthXML;
    }


    public static JSONObject removeNamespacePrefix(JSONObject jsonObject) {
        JSONObject cleanedJsonObject = new JSONObject();

        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            String cleanedKey = key.replaceAll("^[^:]+:", "");
            if (value instanceof JSONObject) {
                cleanedJsonObject.put(cleanedKey, removeNamespacePrefix((JSONObject) value));
            } else if (value instanceof JSONArray jsonArray) {
                JSONArray cleanedArray = new JSONArray();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Object element = jsonArray.get(i);
                    if (element instanceof JSONObject) {
                        cleanedArray.put(removeNamespacePrefix((JSONObject) element));
                    } else {
                        cleanedArray.put(element);
                    }
                }
                cleanedJsonObject.put(cleanedKey, cleanedArray);
            } else {
                cleanedJsonObject.put(cleanedKey, value);
            }
        }
        return cleanedJsonObject;
    }

    @PostMapping("/dishes")
    public void setDish(@RequestBody Dish dish) {
        dishService.addDish(dish);
    }

    @PutMapping("/dishes")
    public void editDish(@RequestBody Dish dish) {
        dishService.editDish(dish);
    }

//    @PostMapping("/trigger_create_dish_wf")
//    public ResponseEntity<Map<String, Object>> triggerFlow(@RequestBody DishRequest dishItem) {
//        log.info("Starting create dish workflow");
//        return ResponseEntity.ok(wfService.startDishWorkFlow(dishItem));
//    }
}
