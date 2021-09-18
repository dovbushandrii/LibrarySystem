package webapp.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @Value("${application.url.prefix}")
    private String urlPrefix;

    @RequestMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("urlPrefix",urlPrefix);
        return "main/error";
    }
}
