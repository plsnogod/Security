package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showAllUsers(Model model) {
        List<User> list_user = userService.showAllUsers();
        model.addAttribute("all_us", list_user);
        return "all_users";
    }


    @GetMapping("/add_user")
    public String getUserForm() {
        return "add_user";
    }

    @PostMapping("/add")
    public String saveUser(@ModelAttribute("new_user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/update_user/{id}")
    public String getUserFormUpdate(Model model, @PathVariable("id") long id) {
        model.addAttribute("list_roles");
        model.addAttribute("upd_user", userService.getUserById(id));
        return "update_user";
    }

    @PostMapping(value = "/edit/{id}")
    public String updateUser(@ModelAttribute("upd_user") ModelMap model, User user) {
        userService.updateUser(user);
        model.addAttribute("upd_user", userService.showAllUsers());
        return "redirect:/admin";
    }


    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
//    private Set<Role> methodRoles(String[] array) {
//        HashSet<Role> hashSet = new HashSet<>();
//        for (int i = 0; i< array.length; i++){
//            hashSet.add(roleService.getRole( array [i]));
//        }
//        return hashSet;
//    }
