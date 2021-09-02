package webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapp.model.entities.Item;
import webapp.modelDAO.daos.ItemDAO;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemRegisterController {

    private ItemDAO itemDAO;

    @Autowired
    public ItemRegisterController(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @GetMapping()
    @ResponseBody
    public List<Item> showAll() {
        return itemDAO.read();
    }

    @GetMapping("/{id}")
    public String showItem(@PathVariable("id") long id, Model model) {
        model.addAttribute("item", itemDAO.read(id));
        return "items/register";
    }

    @GetMapping("/new")
    public String newItem(Model model) {
        model.addAttribute("item", new Item());
        return "items/register";
    }

    @PostMapping()
    public String createItem(@ModelAttribute("item") Item item) {
        itemDAO.create(item);
        return "redirect:/";
    }
}
