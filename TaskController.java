package com.app.todoapp.controller;

import com.app.todoapp.models.Task;
import com.app.todoapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.LongToIntFunction;

@Controller
//@RequestMapping ("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(Model model){

        List<Task>tasks=taskService.getAllTasks();
        model.addAttribute("tasks",tasks); //Purpose: This makes the list of tasks available in the view (like a Thymeleaf template). In the view, you can access the tasks list by using ${tasks}.
        return "tasks";
    }
    @PostMapping

    public String createTask(@RequestParam String  title){

         taskService.createTask(title);
         return "redirect:/";

    }

   @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){

        taskService.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id); // Toggles the task's completion status
        return "redirect:/"; // Redirects back to the main page
    }
}
