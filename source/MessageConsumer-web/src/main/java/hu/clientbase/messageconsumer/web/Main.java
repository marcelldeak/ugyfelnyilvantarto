
package hu.clientbase.messageconsumer.web;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class Main {
    
    @Inject 
    private CreatedTopicReaderBean reader;
    
    @PostConstruct
    private void init(){
        reader.readCreatedTopic("Meeting", "XXX");
    }
}
