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


    private Set<Role> methodRolei(String[] array) {
        HashSet<Role> hashSet = new HashSet<>();
        for (int i = 0; i< array.length; i++){
            hashSet.add(roleService.getRole( array [i]));
        }
        return hashSet;
    }
}
