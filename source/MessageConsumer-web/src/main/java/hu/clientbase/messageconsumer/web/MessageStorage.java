package hu.clientbase.messageconsumer.web;

import hu.clientbase.shared.ejb.SharedEventDTO;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class MessageStorage {

    private static final List<SharedEventDTO> CREATED_EVENTS = new CopyOnWriteArrayList<>();
    private static final List<SharedEventDTO> REMOVED_EVENTS = new CopyOnWriteArrayList<>();

    public static List<SharedEventDTO> getCREATED_EVENTS() {
        return CREATED_EVENTS;
    }

    public static List<SharedEventDTO> getREMOVED_EVENTS() {
        return REMOVED_EVENTS;
    }

    public static void addCreatedEvent(SharedEventDTO dto) {
        CREATED_EVENTS.add(dto);
    }

    public static void addRemovedEvent(SharedEventDTO dto) {
        REMOVED_EVENTS.add(dto);
    }
}
