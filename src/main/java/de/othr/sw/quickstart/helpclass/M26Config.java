package de.othr.sw.quickstart.helpclass;

import org.springframework.context.annotation.Configuration;


@Configuration
public class M26Config {
    //name of bank as user
    public static final String bankName = "m26";
    //in months
    public static final int standardRepaymentTime = 12;
    //interest Rate in Promille -> how much a customer has to pay back more for a credit (50 = 5%)
    public static final int standardInterestRate = 50;
}
