package hu.clientbase.dto;

import hu.clientbase.entity.Project;

public class BasicProjectDTO {

    private Long id;

    private String name;

    public BasicProjectDTO() {
        // default
    }

    public BasicProjectDTO(Project p) {
        this.id = p.getId();
        this.name = p.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
