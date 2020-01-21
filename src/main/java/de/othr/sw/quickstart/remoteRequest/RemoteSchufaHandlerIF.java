package de.othr.sw.quickstart.remoteRequest;

import de.othr.sw.quickstart.dtos.Art;
import de.othr.sw.quickstart.dtos.RiskResponseDto;
import de.othr.sw.quickstart.entity.Customer;

import java.util.Optional;

public interface RemoteSchufaHandlerIF {
    public Optional<RiskResponseDto> getRiskEstimation(Customer customer, long amount);
    public boolean updateUser(String name, Art art, int betrag);
}
