package de.othr.sw.quickstart.remoteRequest;

import de.othr.sw.quickstart.helpclass.M26Config;
import de.othr.sw.quickstart.helpclass.YAMLConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RemoteSchufaHandlerFactory {
    @Autowired
    YAMLConfig yamlConfig;
    //decides if "dummy" testclass should be implemented or if schufa should be built in
    @Bean
    @Scope("singleton")
    public RemoteSchufaHandlerIF createSchufaHandler() {
        RemoteSchufaHandler remoteSchufaHandler = new RemoteSchufaHandler();
        RemoteSchufaHandlerDummy remoteSchufaHandlerdummy = new RemoteSchufaHandlerDummy();
        return yamlConfig.isTestWithouthSchufa() ? remoteSchufaHandlerdummy : remoteSchufaHandler;
    }
}
