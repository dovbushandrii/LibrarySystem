package webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapp.model.entities.SystemUser;
import webapp.modelDAO.daos.SystemUserDAO;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private SystemUserDAO systemUserDAO;
    private PasswordEncoder encoder;

    @Autowired
    public AuthController(SystemUserDAO systemUserDAO,
                          PasswordEncoder encoder) {
        this.systemUserDAO = systemUserDAO;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        if (systemUserDAO.isDBEmpty()) {
            model.addAttribute("user", new SystemUser());
            return "users/register";
        } else {
            return "main/login";
        }
    }

    @PostMapping("")
    public String createUser(@ModelAttribute("user") SystemUser user) {
        System.out.println(systemUserDAO.isDBEmpty());
        if (systemUserDAO.isDBEmpty()) {
            user.setPassword(encoder.encode(user.getPassword()));
            systemUserDAO.create(user);
        }
        return "redirect:/auth/login";
    }

    @DeleteMapping("")
    @PreAuthorize("hasAuthority('admin')")
    public String deleteUser() {
        systemUserDAO.delete();
        return "redirect:/auth/login";
    }

}
