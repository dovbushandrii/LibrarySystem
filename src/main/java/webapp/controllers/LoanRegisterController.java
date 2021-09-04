package webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webapp.model.entities.Loan;
import webapp.modelDAO.daos.ClientDAO;
import webapp.modelDAO.daos.ItemDAO;
import webapp.modelDAO.daos.LoanDAO;

import javax.validation.Valid;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@RequestMapping("/loans")
public class LoanRegisterController {

    private final LoanDAO loanDAO;
    private final ClientDAO clientDAO;
    private final ItemDAO itemDAO;

    @Autowired
    public LoanRegisterController(LoanDAO loanDAO,
                                  ClientDAO clientDAO,
                                  ItemDAO itemDAO) {
        this.loanDAO = loanDAO;
        this.clientDAO = clientDAO;
        this.itemDAO = itemDAO;
    }

    @GetMapping()
    public String showAllLoans(Model model) {
        model.addAttribute("loans", loanDAO.read());
        return "loans/showall";
    }

    @GetMapping("/{id}")
    public String showLoan(@PathVariable("id") long id, Model model) {
        model.addAttribute("loan", loanDAO.read(id));
        return "loans/show";
    }

    @GetMapping("new")
    public String newLoan(Model model) {
        model.addAttribute("loan", new Loan());
        model.addAttribute("clients", clientDAO.read());
        model.addAttribute("items", itemDAO.read());
        return "loans/register";
    }

    @PostMapping()
    @Transactional
    public String createLoan(@Valid @ModelAttribute("loan") Loan loan,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("clients", clientDAO.read());
            model.addAttribute("items", itemDAO.read());
            return "loans/register";
        }
        loanDAO.create(loan);
        return "redirect:/loans";
    }

    @GetMapping("/{id}/edit")
    public String editLoan(@PathVariable("id") long id,
                           Model model) {
        Loan loan = loanDAO.read(id);
        model.addAttribute("id", id);
        model.addAttribute("clients", clientDAO.read());
        model.addAttribute("items", Stream
                .concat(itemDAO.read().stream(),loan.getItems().stream()).collect(Collectors.toList()));
        model.addAttribute("loan", loan);
        return "loans/edit";
    }

    //TODO: Patch mapping issue
    @PostMapping("/{id}/update")
    public String updateLoan(@Valid Loan loan,
                             BindingResult bindingResult,
                             @PathVariable("id") long id,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            model.addAttribute("clients", clientDAO.read());
            model.addAttribute("items", itemDAO.read());
            return "loans/edit";
        }
        loanDAO.update(loan, id);
        return "redirect:/loans";
    }

    //TODO: Delete mapping issue
    @PostMapping("/{id}/delete")
    public String deleteLoan(@PathVariable("id") long id) {
        loanDAO.delete(id);
        return "redirect:/loans";
    }
}
