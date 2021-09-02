package webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapp.model.entities.Loan;
import webapp.modelDAO.daos.LoanDAO;


@Controller
@RequestMapping("/loans")
public class LoanRegisterController {

    private final LoanDAO loanDAO;

    @Autowired
    public LoanRegisterController(LoanDAO loanDAO) {
        this.loanDAO = loanDAO;
    }

    @GetMapping()
    public String showAllLoans() {
        return "loans/showall";
    }

    @GetMapping("/{id}")
    public String showLoan(@PathVariable("id") long id, Model model) {
        model.addAttribute("loan", loanDAO.read(id));
        return "loans/register";
    }

    @GetMapping("/new")
    public String newLoan(Model model) {
        model.addAttribute("loan", new Loan());
        return "loans/register";
    }

    @PostMapping()
    public String createLoan(@ModelAttribute("loan") Loan loan) {
        loanDAO.create(loan);
        return "redirect:/loans";
    }

    @GetMapping("/{id}/edit")
    public String editLoan(@PathVariable("id") long id,
                           Model model) {
        model.addAttribute("loan", loanDAO.read(id));
        return "loans/edit";
    }

    @PatchMapping("/{id}")
    public String updateLoan(@ModelAttribute("loan") Loan loan,
                             @PathVariable("id") long id) {
        loanDAO.update(loan,id);
        return "redirect:/loans";
    }
}
