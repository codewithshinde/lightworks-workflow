package com.lw.spring.worker;

import com.lw.spring.repository.DishRepo;
import com.lw.spring.services.DishService;
import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

@Component
public class TestWorker implements Worker {

    @Autowired
    private DishService ds;

    @Override
    public String getTaskDefName() {
        return "create_dish_task";
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        String name = (String) task.getInputData().get("dishName");
        result.addOutputData("message", "Hello"+ name);
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
