package de.othr.sw.quickstart.remoteRequest;

import de.othr.sw.quickstart.helpclass.M26Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteSchufaHandlerFactory {
    //decides if "dummy" testclass should be implemented or if schufa should be built in
    @Bean
    public RemoteSchufaHandlerIF createSchufaHandler() {
        RemoteSchufaHandler remoteSchufaHandler = new RemoteSchufaHandler();
        RemoteSchufaHandlerDummy remoteSchufaHandlerdummy = new RemoteSchufaHandlerDummy();
        return M26Config.testWithouthSchufa ? remoteSchufaHandlerdummy : remoteSchufaHandler;
    }
}