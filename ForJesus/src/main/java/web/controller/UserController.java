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

    @GetMapping("/")
    public String showAllUsers(ModelMap model) {
        model.addAttribute("all_us",userService.showAllUsers());
//        model.addAttribute("user",new User());


    return "people/all_users";
    }


    @PostMapping("/people/add_users")
    public String addUser(@ModelAttribute User user, Model model){
        userService.addUser(user);
        model.addAttribute("add_users", userService.showAllUsers());
        model.addAttribute("user", new User());
        return "people/all_users";

    }

    @PostMapping(value = "/users/update")
    public String updateUser(@ModelAttribute User user, Model model){
        userService.updateUser(user);
        model.addAttribute("update/users", userService.showAllUsers());
        return "redirect:/people/all_users";

    }

    @PostMapping(value = "/users/delete")
    public String deleteUser(@ModelAttribute User user, Model model){
        userService.deleteUser(user.getId());
       model.addAttribute("users", userService.showAllUsers());
        return "redirect:/all_users";
    }

}
