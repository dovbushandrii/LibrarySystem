/**
 * @file ItemController.java
 * @brief This file contains Controller fow CRUD operations for Item class objects
 *
 * @author Andrii Dovbush
 */

package webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webapp.model.entities.Item;
import webapp.model.enums.ItemType;
import webapp.modelDAO.daos.ItemDAO;

import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Value("${application.url.prefix}")
    private String urlPrefix;
    private final ItemDAO itemDAO;

    @Autowired
    public ItemController(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("urlPrefix",urlPrefix);
        model.addAttribute("items", itemDAO.read());
        return "items/showall";
    }

    @GetMapping("/new")
    public String newItem(Model model) {
        model.addAttribute("urlPrefix",urlPrefix);
        model.addAttribute("item", new Item());
        model.addAttribute("types", ItemType.values());
        return "items/register";
    }

    @PostMapping()
    public String createItem(@Valid @ModelAttribute("item") Item item,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("urlPrefix",urlPrefix);
            model.addAttribute("types", ItemType.values());
            return "items/register";
        }
        itemDAO.create(item);
        return "redirect:/items";
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable("id") long id) {
        itemDAO.delete(id);
        return "redirect:/items";
    }
}
