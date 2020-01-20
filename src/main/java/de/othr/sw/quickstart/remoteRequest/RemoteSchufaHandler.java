package de.othr.sw.quickstart.remoteRequest;

import de.othr.sw.quickstart.dtos.ActivityDto;
import de.othr.sw.quickstart.dtos.Art;
import de.othr.sw.quickstart.dtos.RiskDto;
import de.othr.sw.quickstart.dtos.RiskResponseDto;
import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.helpclass.M26Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;

public class RemoteSchufaHandler implements RemoteSchufaHandlerIF {
    @Autowired
    private RestTemplate restClient;

    @Override
    public RiskResponseDto getRiskEstimation(Customer customer, long amount) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String birthday = format.format(customer.getBirthday());
        System.out.println(birthday);
        RiskDto riskDto = new RiskDto(M26Config.schufaId, customer.getFirstName() + " " + customer.getLastName(), birthday, (int)amount);
        RiskResponseDto riskResponseDto = restClient.postForObject("http://im-codd.oth-regensburg.de:8823/restapi/risk", riskDto, RiskResponseDto.class);
        return riskResponseDto;
    }

    @Override
    public boolean updateUser(String name, Art art, int betrag) {
        ActivityDto activityDto = new ActivityDto(name, art, betrag);
        return restClient.postForObject("", activityDto, boolean.class);
    }
}
