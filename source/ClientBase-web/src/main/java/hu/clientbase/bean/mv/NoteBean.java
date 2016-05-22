package hu.clientbase.bean.mv;

import hu.clientbase.dto.BasicNoteDTO;
import hu.clientbase.entity.Note;
import hu.clientbase.service.mdel.NoteModel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named("noteView")
@ViewScoped
public class NoteBean implements Serializable {

    private BasicNoteDTO selectedNote;

    private List<BasicNoteDTO> notes;

    private List<BasicNoteDTO> filteredNotes;

    @Inject
    private NoteModel model;

    @PostConstruct
    private void init() {
        notes = model.getAllNotes();
    }

    public List<BasicNoteDTO> getFilteredNotes() {
        return filteredNotes;
    }

    public void setFilteredNotes(List<BasicNoteDTO> filteredNotes) {
        this.filteredNotes = filteredNotes;
    }

    public BasicNoteDTO getSelectedNote() {
        return selectedNote;
    }

    public void setSelectedNote(BasicNoteDTO selectedNote) {
        this.selectedNote = selectedNote;
    }

    public List<BasicNoteDTO> getNotes() {
        return notes;
    }

    public void setNotes(List<BasicNoteDTO> notes) {
        this.notes = notes;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Note Selected", ((Note) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
