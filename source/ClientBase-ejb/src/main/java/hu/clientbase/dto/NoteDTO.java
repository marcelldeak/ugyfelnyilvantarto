package hu.clientbase.dto;

import hu.clientbase.entity.Note;
import hu.clientbase.entity.Tag;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import hu.clientbase.validate.ValidatorAnnotation;

@ValidatorAnnotation
public class NoteDTO implements Serializable {

    private static final long serialVersionUID = 645249867234114601L;

    private Long id;
    private Tag tag;
    @NotNull
    private String content;

    public NoteDTO() {
        //default
    }

    public NoteDTO(Tag tag, String content) {
        this.tag = tag;
        this.content = content;
    }

    public NoteDTO(Note n) {
        this.id = n.getId();
        this.tag = n.getTag();
        this.content = n.getContent();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
