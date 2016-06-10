package hu.clientbase.messageconsumer.web;

import hu.clientbase.shared.ejb.SharedEventDTO;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

@Stateless
public class CreatedTopicReaderBean {
    
    @Inject
    private JMSContext context;
    
    @Resource(lookup = "java:/jms/topic/CreatedTopic")
    private Topic createdTopic;
    
    public void readCreatedTopic(String eventType, String customerName) {
        String selector = "customerName = " + customerName + " AND eventType = " + eventType;
        
        while (true) {
            SharedEventDTO sharedEventDTO = context.createConsumer(createdTopic, selector).receiveBody(SharedEventDTO.class);
            MessageStorage.addCreatedEvent(sharedEventDTO);
        }
    }
    
}
