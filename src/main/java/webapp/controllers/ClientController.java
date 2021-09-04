/**
 * @file ClientController.java
 * @brief This file contains Controller fow CRUD operations for Client class objects
 *
 * @author Andrii Dovbush
 */

package webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webapp.model.entities.Client;
import webapp.modelDAO.daos.ClientDAO;

import javax.validation.Valid;


@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientDAO clientDAO;

    @Autowired
    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("clients", clientDAO.read());
        return "clients/showall";
    }

    @GetMapping("/new")
    public String newClient(Model model) {
        model.addAttribute("client", new Client());
        return "clients/register";
    }

    @PostMapping()
    public String createClient(@Valid @ModelAttribute("client") Client client,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "clients/register";
        }
        clientDAO.create(client);
        return "redirect:/clients";
    }

    //TODO: Delete mapping issue
    @PostMapping("/{id}")
    public String deleteClient(@PathVariable("id") long id) {
        clientDAO.delete(id);
        return "redirect:/clients";
    }
}
