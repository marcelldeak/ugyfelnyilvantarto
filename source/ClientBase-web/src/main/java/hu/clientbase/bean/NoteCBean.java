package hu.clientbase.bean;

import hu.clientbase.bean.mv.EventBean;
import hu.clientbase.dto.BasicEventDTO;
import hu.clientbase.dto.NoteDTO;
import hu.clientbase.entity.Tag;
import hu.clientbase.service.EventService;
import hu.clientbase.service.UserService;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Ajax;

@Named("noteC")
@ViewScoped
public class NoteCBean implements Serializable {

    private static final long serialVersionUID = 4734724353391544609L;
    
    @Inject
    private EventService eventService;
    
    @Inject
    private EventBean eventBean;
    
    @Inject
    private UserService userService;
    
    private Tag tag;
    
    private String content;
    
    private BasicEventDTO selectedEvent;
    
    public void openAddDialog(BasicEventDTO dto)
    {
        selectedEvent = dto;
        Ajax.oncomplete("$('#event_details_dialog').modal('hide');$('#note_add_dialog').modal('show')");
    }
    
    public void add()
    {
       String userEmail = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        try {
            eventService.addNoteToEvent(selectedEvent, new NoteDTO(tag, content + "\n(" + userService.getUserByEmail(userEmail).getName() + ")"));
        } catch (NoSuchAlgorithmException ex) {
            FacesContext.getCurrentInstance().getExternalContext().setResponseStatus(404);
        }
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Note added succesfully."));
       eventBean.update();
       Ajax.update("notes_form");
       Ajax.oncomplete("clearAndCloseAddNoteDialog(true);");
       
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public Tag[] getTags()
    {
        return Tag.values();
    }
    
}
