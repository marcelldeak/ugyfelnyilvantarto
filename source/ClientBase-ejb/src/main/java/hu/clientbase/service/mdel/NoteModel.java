package hu.clientbase.service.mdel;

import hu.clientbase.dto.BasicNoteDTO;
import hu.clientbase.entity.Note;
import hu.clientbase.facade.NoteFacade;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Stateless
@LocalBean
public class NoteModel {

    @Inject
    private NoteFacade nf;

    public List<BasicNoteDTO> getAllNotes() {
        List<Note> notes = nf.getAllNotes();
        List<BasicNoteDTO> ret = new LinkedList<>();

        notes.stream().forEach(n -> ret.add(new BasicNoteDTO(n)));

        return ret;
    }
}
