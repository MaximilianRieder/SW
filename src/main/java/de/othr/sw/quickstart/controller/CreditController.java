package de.othr.sw.quickstart.controller;

import de.othr.sw.quickstart.dtos.Risikostufe;
import de.othr.sw.quickstart.remoteRequest.RemoteSchufaHandlerIF;
import de.othr.sw.quickstart.service.AccountServiceIF;
import de.othr.sw.quickstart.service.CreditServiceIF;
import de.othr.sw.quickstart.service.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Scope("session")
@RequestMapping("/credit")
public class CreditController {
    @Autowired
    CreditServiceIF creditService;
    @Autowired
    AccountServiceIF accountService;
    @Autowired
    CustomerServiceIF customerService;
    @Autowired
    RemoteSchufaHandlerIF remoteSchufaHandler;

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public String requestCredit(
            Model model,
            @ModelAttribute("amount") long amount,
            @ModelAttribute("senderIban") String iban
    ) {
        model.addAttribute("accounts", accountService.getAccountsByCustomer(customerService.getLoggedInCustomer()));
        if((accountService.getAccountByIban(iban).isEmpty()) || (amount <= 0)) {
            return "credit";
        }

        //returns embargo if schufa not reachable (change yml config to turn schufa off)
        Risikostufe risikostufe = creditService.getRisikoStufe(iban, amount);

        switch (risikostufe) {
            case EMBARGO:
                model.addAttribute("riskEstimation", "Denied, because of schufa estimation: embargo");
                break;
            case HOHESRISIKO:
                model.addAttribute("riskEstimation", "Denied, because of schufa estimation: high risk");
                break;
            case KEINRISIKO:
                creditService.requestCredit(iban, amount);
                model.addAttribute("riskEstimation", "Accepted, because of schufa estimation: no risk");
                break;
            case MITTLERESRISIKO:
                creditService.requestCredit(iban, amount);
                model.addAttribute("riskEstimation", "Accepted, because of schufa estimation: medium risk");
                break;
            default:
                model.addAttribute("riskEstimation", "There was a problem with the Schufa Service");
        }
        return "credit";
    }
}

