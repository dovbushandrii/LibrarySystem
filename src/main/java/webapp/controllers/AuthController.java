package webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapp.model.entities.SystemUser;
import webapp.modelDAO.daos.SystemUserDAO;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Value("${application.url.prefix}")
    private String urlPrefix;
    private SystemUserDAO systemUserDAO;

    @Autowired
    public AuthController(SystemUserDAO systemUserDAO) {
        this.systemUserDAO = systemUserDAO;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("urlPrefix",urlPrefix);
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
            systemUserDAO.create(user);
        }
        return "redirect:/auth/login";
    }

    @DeleteMapping("")
    public String deleteUser() {
        systemUserDAO.delete();
        return "redirect:/auth/login";
    }

}
