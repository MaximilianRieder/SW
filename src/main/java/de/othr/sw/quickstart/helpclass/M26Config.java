package de.othr.sw.quickstart.helpclass;

import org.springframework.context.annotation.Configuration;


@Configuration
public class M26Config {
    //choose true to switch off schufa requests -> false to turn on
    public static final boolean testWithouthSchufa = true;

    // ID from dream schufa for authorisation
    public static final int schufaId = 0;

    //name of bank as user
    public static final String bankName = "m26";
    //in months
    public static final int standardRepaymentTime = 12;
    //interest Rate in Promille -> how much a customer has to pay back more for a credit (50 = 5%)
    public static final int standardInterestRate = 50;
    // would usually be once per month but for showcase every ten seconds
    public static final String creditRepayRate = "*/10 * * * * *";
}
