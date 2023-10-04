package net.example.CrudDemo.controller;

import net.example.CrudDemo.model.PhoneNumber;
import net.example.CrudDemo.service.PhoneService;
import net.example.CrudDemo.service.UserService;
import net.example.CrudDemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;
    private final PhoneService phoneService;

    private User lastUser;

    @Autowired
    public UserController(UserService userService, PhoneService phoneService) {
        this.userService = userService;
        this.phoneService = phoneService;
    }

    @GetMapping("/users")
    public String findAll(Model model,Model model1){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        lastUser = userService.saveUser(user);
        return "redirect:/phone-create";
    }

    @GetMapping("/phone-create")
    public String createPhoneForm( Model model, ArrayList<PhoneNumber> phones,User user){
        PhoneNumber phone = new PhoneNumber();
        phone.setUserID(lastUser.getId());

        List<PhoneNumber> phoneEntries = new ArrayList<>();
        phoneEntries.add(phone);
        user.setPhoneEntries(phoneEntries);
        model.addAttribute("user", user);

        return "phone-create";
    }

    @PostMapping("/phone-create")
    public String createUserPhones(@ModelAttribute("user") User user) {
        lastUser.setPhoneEntries(user.getPhoneEntries());

        for(PhoneNumber phone : lastUser.getPhoneEntries()) {
            phone.setUserID(lastUser.getId());
            phoneService.savePhone(phone);
        }

        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserFrom(@PathVariable("id") Long id, Model model){
        Optional<User> userOptional = userService.getUserById(id);
        User user = userOptional.get();
        model.addAttribute("user", user);
        return "/user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        for (int i = 0; i < user.getPhoneEntries().size(); i++){
            user.getPhoneEntries().get(i).setUserID(user.getPhoneEntries().get(0).getUserID());
        }
        userService.saveUser(user);
        return "redirect:/users";
    }
}
