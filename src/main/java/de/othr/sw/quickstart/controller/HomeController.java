package de.othr.sw.quickstart.controller;

import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.entity.Kunde;
import de.othr.sw.quickstart.service.CustomerServiceIF;
import de.othr.sw.quickstart.service.KundeService;
import de.othr.sw.quickstart.service.KundeServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private CustomerServiceIF customerService;

    @RequestMapping("/")
    public String start() {
        return "index";
    }

    @RequestMapping("/register")
    public String goRegister() { return "register";}

    @RequestMapping("/login")
    public String goLogin() { return "login";}

    @RequestMapping("/registernew")
    public String registerNew(
            @ModelAttribute("firstName") String fname,
            @ModelAttribute("lastName") String lname,
            @ModelAttribute("password") String pwd,
            @ModelAttribute("username") String username,
            Model model
    ) {
        Customer customer = new Customer();
        customer.setFirstName(fname);
        customer.setLastName(lname);
        customer.setPassword(pwd);
        customer.setUsername(username);
        customer.setAccounts(null);
        customer.setMainResidence(null);
        customerService.createCustomer(customer);
        return "login";
        /*ab hier alt
        Kunde kunde = new Kunde();
        kunde.setVorname(vname);
        kunde.setNachname(nname);
        kunde = kundeService.kundeAnlegen(kunde);
        model.addAttribute("kundennr", kunde.getKundenNr());

        return "kundenkonto";
        */
    }
}
