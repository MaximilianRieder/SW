package de.othr.sw.quickstart.remoteRequest;

import de.othr.sw.quickstart.dtos.Art;
import de.othr.sw.quickstart.dtos.Risikostufe;
import de.othr.sw.quickstart.dtos.RiskResponseDto;
import de.othr.sw.quickstart.entity.Customer;

import java.util.Optional;
import java.util.Random;

public class RemoteSchufaHandlerDummy implements RemoteSchufaHandlerIF {
    @Override
    public Optional<RiskResponseDto> getRiskEstimation(Customer customer, long amount) {
        RiskResponseDto riskResponseDto = new RiskResponseDto();
        //random risikostufe
        Risikostufe randomRisikoStufe = Risikostufe.values()[new Random().nextInt(Risikostufe.values().length)];
        riskResponseDto.setRisikostufe(randomRisikoStufe);

        Optional<RiskResponseDto> riskResponseDtoO = Optional.of(riskResponseDto);
        return riskResponseDtoO;
    }

    @Override
    public boolean updateUser(String name, Art art, int betrag) {
        return true;
    }
}
