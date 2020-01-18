package de.othr.sw.quickstart.remoteRequest;

import de.othr.sw.quickstart.dtos.Art;
import de.othr.sw.quickstart.dtos.RiskResponseDto;
import de.othr.sw.quickstart.entity.Customer;

public interface RemoteSchufaHandlerIF {
    public RiskResponseDto getRiskEstimation(Customer customer, long amount);
    public boolean updateUser(String name, Art art, int betrag);
}
