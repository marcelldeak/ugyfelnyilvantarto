package hu.clientbase.messageconsumer.web;

import hu.clientbase.shared.ejb.SharedEventDTO;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageStorage {

    private static final List<SharedEventDTO> CREATED_EVENTS = new CopyOnWriteArrayList<>();
    private static final List<SharedEventDTO> REMOVED_EVENTS = new CopyOnWriteArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageStorage.class);

    public static List<SharedEventDTO> getCreatedEvents() {
        return CREATED_EVENTS;
    }

    public static List<SharedEventDTO> getRemovedEvents() {
        return REMOVED_EVENTS;
        
    }

    public static void addCreatedEvent(SharedEventDTO dto) {
        LOGGER.error("Created event added");
        CREATED_EVENTS.add(dto);
    }
   

    public static void addRemovedEvent(SharedEventDTO dto) {
        LOGGER.error("REMOVED EVENT ADDED");
        REMOVED_EVENTS.add(dto);
    }
}
