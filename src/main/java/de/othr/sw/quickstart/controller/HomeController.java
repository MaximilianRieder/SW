package de.othr.sw.quickstart.controller;

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

    /*@RequestMapping("/registrieren")
    public String registrieren(
            @ModelAttribute("vorname") String vname,
            @ModelAttribute("nachname") String nname,
            Model model
    ) {
        Kunde kunde = new Kunde();
        kunde.setVorname(vname);
        kunde.setNachname(nname);
        kunde = kundeService.kundeAnlegen(kunde);
        model.addAttribute("kundennr", kunde.getKundenNr());

        return "kundenkonto";
    }

    @RequestMapping("/login")
    public String login(

    ) {
        return "startpage";
    }*/

}
