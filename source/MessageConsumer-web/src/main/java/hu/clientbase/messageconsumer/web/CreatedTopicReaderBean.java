package hu.clientbase.messageconsumer.web;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

public class CreatedTopicReaderBean {

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:/jms/topic/CreatedTopic")
    private Topic createdTopic;
}
