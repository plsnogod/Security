package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("all_us", userService.showAllUsers());
        return "people/all_users";
    }

    @GetMapping("/people/add_user")
    public String getUserForm() {

        return "people/add_user";
    }

    @PostMapping("people/add_user")
    public String saveUser(@ModelAttribute("new_user") User user) {
        userService.addUser(user);


        return "redirect:/";

    }

    @GetMapping("/people/update_user")
    public String getUserFormUpdate(@PathVariable("id") long id, Model model) {
        model.addAttribute("update_user", userService.getuserById(id));
        return "people/update_user";
    }

    @PostMapping(value = "/people/update_user/{id}")
    public String updateUser(@ModelAttribute("upd_user") Model model, User user) {

        userService.updateUser(user);
        model.addAttribute("update_users", userService.showAllUsers());
        return "redirect:/";

    }

    @PostMapping(value = "/people/all_users")
    public String deleteUser(@ModelAttribute User user, Model model) {
        userService.deleteUser(user.getId());
        model.addAttribute("users", userService.showAllUsers());
        return "redirect:/people/all_users";
    }

}
