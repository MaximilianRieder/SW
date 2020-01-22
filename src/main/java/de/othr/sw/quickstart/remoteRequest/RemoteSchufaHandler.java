package de.othr.sw.quickstart.remoteRequest;

import de.othr.sw.quickstart.dtos.ActivityDto;
import de.othr.sw.quickstart.dtos.Art;
import de.othr.sw.quickstart.dtos.RiskDto;
import de.othr.sw.quickstart.dtos.RiskResponseDto;
import de.othr.sw.quickstart.entity.Customer;
import de.othr.sw.quickstart.helpclassAndConfig.YAMLConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class RemoteSchufaHandler implements RemoteSchufaHandlerIF {
    @Autowired
    YAMLConfig yamlConfig;
    @Autowired
    private RestTemplate restClient;

    @Override
    public Optional<RiskResponseDto> getRiskEstimation(Customer customer, long amount) {
        RiskResponseDto riskResponseDto;
        Optional<RiskResponseDto> riskResponseDtoO = Optional.empty();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String birthday = format.format(customer.getBirthday());

        RiskDto riskDto = new RiskDto();
        riskDto.setApiKey(yamlConfig.getSchufaId());
        riskDto.setName(customer.getFirstName() + " " + customer.getLastName());
        riskDto.setGeburtsdatum(birthday);
        riskDto.setBetrag((int)amount);

        try {
            riskResponseDto = restClient.postForObject("http://im-codd.oth-regensburg.de:8823/restapi/risk", riskDto, RiskResponseDto.class);
        } catch (RestClientException e){
            e.printStackTrace();
            return riskResponseDtoO;
        }

        if(riskResponseDto.getRisikostufe() == null)
            return riskResponseDtoO;

        riskResponseDtoO = Optional.of(riskResponseDto);
        return riskResponseDtoO;
    }

    @Override
    public boolean updateUser(String name, Art art, int betrag, String nameKunde, Date geburtsdatum) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String birthday = format.format(geburtsdatum);
        ActivityDto activityDto = new ActivityDto(name,art, betrag, nameKunde, birthday);
        return restClient.postForObject("http://im-codd.oth-regensburg.de:8823/restapi/user", activityDto, boolean.class);
    }
}
