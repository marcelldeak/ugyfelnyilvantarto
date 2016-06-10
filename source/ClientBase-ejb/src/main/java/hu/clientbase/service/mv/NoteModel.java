package hu.clientbase.service.mv;


import hu.clientbase.dto.NoteDTO;
import hu.clientbase.entity.Note;
import hu.clientbase.facade.NoteFacade;
import hu.clientbase.validate.LoggerInterceptor;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import javax.interceptor.Interceptors;

@Stateless
@LocalBean
@Interceptors({LoggerInterceptor.class})
public class NoteModel {

    @Inject
    private NoteFacade nf;

    public List<NoteDTO> getAllNotes() {
        List<Note> notes = nf.getAllNotes();
        List<NoteDTO> ret = new LinkedList<>();

        notes.stream().forEach(n -> ret.add(new NoteDTO(n)));

        return ret;
    }
}
