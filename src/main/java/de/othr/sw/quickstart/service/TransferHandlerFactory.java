package de.othr.sw.quickstart.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransferHandlerFactory {
    @Bean
    @Qualifier("TransferHandlerCustomer")
    public TransferHandlerIF createTHCustomer() {
        return new TransferHandlerCustomer();
    }
    @Bean
    @Qualifier("TransferHandlerCredit")
    public TransferHandlerIF createTHCredit() {
        return new TransferHandlerCredit();
    }
}
