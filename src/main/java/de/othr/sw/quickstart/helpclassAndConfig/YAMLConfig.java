package de.othr.sw.quickstart.helpclassAndConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {

    private boolean testWithouthSchufa;

    // ID from dream schufa for authorisation
    private String schufaId;

    //name of bank as user
    private String bankName;
    //in months
    private int standardRepaymentTime;
    //interest Rate in Promille -> how much a customer has to pay back more for a credit (50 = 5%)
    private int standardInterestRate;
    //how many transaction are shown in history
    private int historyNumber;

    public boolean isTestWithouthSchufa() {
        return testWithouthSchufa;
    }

    public void setTestWithouthSchufa(boolean testWithouthSchufa) {
        this.testWithouthSchufa = testWithouthSchufa;
    }

    public String getSchufaId() {
        return schufaId;
    }

    public void setSchufaId(String schufaId) {
        this.schufaId = schufaId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getStandardRepaymentTime() {
        return standardRepaymentTime;
    }

    public void setStandardRepaymentTime(int standardRepaymentTime) {
        this.standardRepaymentTime = standardRepaymentTime;
    }

    public int getHistoryNumber() {
        return historyNumber;
    }

    public void setHistoryNumber(int historyNumber) {
        this.historyNumber = historyNumber;
    }

    public int getStandardInterestRate() {
        return standardInterestRate;
    }

    public void setStandardInterestRate(int standardInterestRate) {
        this.standardInterestRate = standardInterestRate;
    }
}
