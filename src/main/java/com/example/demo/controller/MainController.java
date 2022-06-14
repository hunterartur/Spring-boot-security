package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.Services;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    private Services<User> services;

    public MainController(Services<User> services) {
        this.services = services;
    }

    @GetMapping(path = "/")
    public String listUsers(ModelMap modelMap) {
        List<User> allUsers = services.getAll();
        modelMap.addAttribute("users", allUsers);
        return "listUsers";
    }

    @PostMapping(path = "/userPage")
    public String userPage(Model model, @RequestParam Long id) {
        User user = services.getById(id);
        model.addAttribute("user", user);
        return "userPage";
    }

    @GetMapping(path = "/createUserPage")
    public String createUserPage(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "createUserPage";
    }

    @PostMapping(path = "/createUser")
    public String createUser(@Valid @ModelAttribute(value = "newUser") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createUserPage";
        }
        services.save(user);
        return "redirect:/";
    }

    @PostMapping(path = "/removeUser")
    public String removeUser(@RequestParam Long id) {
        services.removeById(id);
        return "redirect:/";
    }

    @GetMapping(path = "/updateUserPage")
    public String updateUserPage(Model model, @RequestParam Long id) {
        User user = services.getById(id);
        model.addAttribute("user", user);
        return "updateUserPage";
    }

    @PostMapping(path = "/updateUser")
    public String updateUser(@ModelAttribute(value = "user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateUserPage";
        }
        services.update(user);
        return "redirect:/";
    }
}
