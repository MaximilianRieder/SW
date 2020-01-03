package de.othr.sw.quickstart.controller;

import de.othr.sw.quickstart.entity.Address;
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

    @RequestMapping("/startPage")
    public String goStartPage() { return "startPage";}

    @RequestMapping("/registernew")
    public String registerNew(
            @ModelAttribute("firstName") String fname,
            @ModelAttribute("lastName") String lname,
            @ModelAttribute("password") String pwd,
            @ModelAttribute("username") String username,
            @ModelAttribute("street") String street,
            @ModelAttribute("number") String number,
            @ModelAttribute("zip") String zip,
            @ModelAttribute("city") String city,
            @ModelAttribute("country") String country,
            Model model
    ) {
        //check if username is used already
        if(customerService.isUsernameUsed(username)) {
            model.addAttribute("usernamecheck", "Username already taken");
            return "register";
        } else {
            //register customer
            //and add address
            Address address = new Address();
            address.setStreet(street);
            address.setNumber(number);
            address.setZip(zip);
            address.setCity(city);
            address.setCountry(country);
            Customer customer = new Customer();
            customer.setFirstName(fname);
            customer.setLastName(lname);
            customer.setPassword(pwd);
            customer.setUsername(username);
            customer.setAccounts(null);
            customer.setMainResidence(address);
            customerService.createAddress(address);
            customerService.createCustomer(customer);
            return "login";
        }

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
