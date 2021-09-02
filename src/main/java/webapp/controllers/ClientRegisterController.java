package webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapp.model.entities.Client;
import webapp.modelDAO.daos.ClientDAO;

import java.util.List;


@Controller
@RequestMapping("/clients")
public class ClientRegisterController {

    private ClientDAO clientDAO;

    @Autowired
    public ClientRegisterController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @GetMapping()
    @ResponseBody
    public List<Client> showAll() {
        return clientDAO.read();
    }

    @GetMapping("/{id}")
    public String showClient(@PathVariable("id") long id, Model model) {
        model.addAttribute("client", clientDAO.read(id));
        return "clients/register";
    }

    @GetMapping("/new")
    public String newClient(Model model) {
        model.addAttribute("client", new Client());
        return "clients/register";
    }

    @PostMapping()
    public String createClient(@ModelAttribute("client") Client client) {
        clientDAO.create(client);
        return "redirect:/loans/new";
    }

    @PatchMapping("/{id}")
    public String updateClient(@ModelAttribute("client") Client client,
                         @PathVariable("id") long id) {
        clientDAO.update(client,id);
        return "redirect:/loans/new";
    }

}
