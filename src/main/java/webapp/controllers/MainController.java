/**
 * @file MainController.java
 * @brief This file contains Main page Controller
 *
 * @author Andrii Dovbush
 */

package webapp.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @Value("${application.url.prefix}")
    private String urlPrefix;

    @GetMapping()
    public String getMainPage(Model model) {
        model.addAttribute("urlPrefix",urlPrefix);
        return "main/index";
    }

}
