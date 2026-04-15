package com.folder.controller;

import com.folder.model.User;
import com.folder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/user_list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("mode", "create");
        model.addAttribute("user", new User());
        return "admin/user_form";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute User user,
            @RequestParam("password") String password,
            Model model,
            RedirectAttributes redirect) {

        Map<String, String> errors = userService.create(user, password);

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("mode", "create");
            model.addAttribute("user", user);
            return "admin/user_form";
        }

        redirect.addFlashAttribute("successMessage", "Created successfully");
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Integer id, Model model) {

        User user = userService.findById(id);

        if (user == null) {
            return "redirect:/admin/users";
        }

        model.addAttribute("mode", "edit");
        model.addAttribute("user", user);
        return "admin/user_form";
    }

    @PostMapping("/{id}/edit")
    public String edit(
            @PathVariable("id") Integer id,
            @ModelAttribute User user,
            @RequestParam(value = "password", required = false) String password,
            Model model,
            RedirectAttributes redirect) {

        Map<String, String> errors = userService.update(id, user, password);

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("mode", "edit");
            model.addAttribute("user", user);
            return "admin/user_form";
        }

        redirect.addFlashAttribute("successMessage", "Updated successfully");
        return "redirect:/admin/users";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id,
                         RedirectAttributes redirect) {

        userService.delete(id);
        redirect.addFlashAttribute("successMessage", "Deleted successfully");
        return "redirect:/admin/users";
    }
}