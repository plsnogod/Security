package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/people/update_user/{id}")
    public String getUserFormUpdate(Model model,@PathVariable("id") long id) {
        model.addAttribute("upd_user", userService.getuserById(id));
        return "people/update_user";
    }

    @PostMapping(value = "/people/update_user/{id}")
    public String updateUser(@ModelAttribute("upd_user") Model model, User user) {

        userService.updateUser(user);
       model.addAttribute("upd_user", userService.showAllUsers());
        return "people/all_users";

    }

    @PostMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/people/all_users";
    }

}
