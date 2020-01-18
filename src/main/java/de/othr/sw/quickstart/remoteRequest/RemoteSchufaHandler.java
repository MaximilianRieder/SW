package de.othr.sw.quickstart.remoteRequest;

import de.othr.sw.quickstart.dtos.ActivityDto;
import de.othr.sw.quickstart.dtos.Art;
import de.othr.sw.quickstart.dtos.RiskDto;
import de.othr.sw.quickstart.dtos.RiskResponseDto;
import de.othr.sw.quickstart.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class RemoteSchufaHandler implements RemoteSchufaHandlerIF {
    @Autowired
    private RestTemplate restClient;

    @Override
    public RiskResponseDto getRiskEstimation(Customer customer, long amount) {
        RiskDto riskDto = new RiskDto(customer.getSchufaId(), customer.getFirstName() + " " + customer.getLastName(), customer.getBirthday().toString(), (int)amount);
        RiskResponseDto riskResponseDto = restClient.postForObject("", riskDto, RiskResponseDto.class);
        return riskResponseDto;
    }

    @Override
    public boolean updateUser(String name, Art art, int betrag) {
        ActivityDto activityDto = new ActivityDto(name, art, betrag);
        return restClient.postForObject("", activityDto, boolean.class);
    }
}
