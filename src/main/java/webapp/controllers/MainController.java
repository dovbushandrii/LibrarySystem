/**
 * @file MainController.java
 * @brief This file contains Main page Controller
 *
 * @author Andrii Dovbush
 */

package webapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping()
    public String getMainPage() {
        return "main/index";
    }

}
