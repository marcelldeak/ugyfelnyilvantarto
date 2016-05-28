package hu.clientbase.messageconsumer.web;

import hu.clientbase.shared.ejb.SharedEventDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topic/RemovedTopic"),
    @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class RemovedTopicMDB implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            SharedEventDTO sharedEventDTO = message.getBody(SharedEventDTO.class);
        } catch (JMSException ex) {
            Logger.getLogger(RemovedTopicMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
