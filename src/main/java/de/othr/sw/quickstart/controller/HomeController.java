package de.othr.sw.quickstart.controller;

import de.othr.sw.quickstart.entity.Address;
import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.repository.CustomerRepository;
import de.othr.sw.quickstart.service.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("session")
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

    //für testzwecke -> "wird zur startpage"
    @RequestMapping("/startPage")
    public String goStartPage(
            Model model
    ) {
        String username;
        username = customerService.getLoggedInCustomer().getMainResidence().getCity();
        model.addAttribute("username", username);
        return "startPage";
    }

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
        if((customerService.isUsernameUsed(username)) || (username.length() > 8)) {
            model.addAttribute("usernamecheck", "Username already taken or too long");
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
            customerService.createCustomer(customer);
            return "login";
        }
    }
}
