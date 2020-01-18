package de.othr.sw.quickstart.controller;

import de.othr.sw.quickstart.entity.Address;
import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.service.AccountService;
import de.othr.sw.quickstart.service.AccountServiceIF;
import de.othr.sw.quickstart.service.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Scope("session")
public class HomeController {

    @Autowired
    private CustomerServiceIF customerService;
    @Autowired
    private AccountServiceIF accountService;


    @RequestMapping("/")
    public String start() {
        return "index";
    }

    @RequestMapping("/register")
    public String goRegister() { return "register";}

    @RequestMapping("/login")
    public String goLogin() { return "login";}

    @RequestMapping("/startPage")
    public String goStartPage() { return "startPage"; }

    @RequestMapping("/accountManagement")
    public String goAccountManagement() { return "accountManagement";}

    @RequestMapping("/banking")
    public String goBanking(Model model) {
        model.addAttribute("accounts", accountService.getAccountsByCustomer(customerService.getLoggedInCustomer()));
        return "banking";}

    @RequestMapping("/credit")
    public String goCredit() { return "credit";}

    @RequestMapping("/showKey")
    public String showKey(
            Model model
    ) {
        model.addAttribute("customerKey", customerService.getLoggedInCustomer().getCustomerKey());
        return "startPage";
    }

    @RequestMapping("/showAccounts")
    public String showAccounts(
            Model model
    ) {
        model.addAttribute("accounts", accountService.getAccountsByCustomer(customerService.getLoggedInCustomer()));
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
            @ModelAttribute("birthday") String birthday,
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
            //parse date to date
            Date date;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            try {
                date = formatter.parse(birthday);
            } catch (ParseException e) {
                model.addAttribute("falseDate", "you have to use the right format");
                return "register";
            }
            customer.setBirthday(date);
            customerService.createCustomer(customer);
            return "login";
        }
    }
}
