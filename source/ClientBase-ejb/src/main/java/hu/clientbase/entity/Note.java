package hu.clientbase.entity;

import hu.clientbase.dto.NoteDTO;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Note implements Serializable {

    private static final long serialVersionUID = 1191674170460862706L;

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @Enumerated(EnumType.STRING)
    private Tag tag;

    @Basic
    private String content;

    public Note() {
        // Entity - parameterless constructor
    }

    public Note(NoteDTO dto) {
        this.tag = dto.getTag();
        this.content = dto.getContent();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

}
