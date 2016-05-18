package hu.clientbase.dto;

import hu.clientbase.entity.Note;
import hu.clientbase.entity.Tag;

public class BasicNoteDTO {

    private Long id;
    private Tag tag;
    private String content;

    public BasicNoteDTO() {
        //default
    }

    public BasicNoteDTO(Note n) {
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
