package de.othr.sw.quickstart.helpclassAndConfig;


public class M26Config {
    // would usually be once per month but for showcase every ten seconds (has to be static -> cant be initialized over yml)
    public static final String creditRepayRate = "*/30 * * * * *";
}
