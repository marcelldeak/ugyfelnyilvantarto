package hu.clientbase.entity;

public enum Tag {
    IMPORTANT("Important"),
    OBLIGATORY("Obligatory"),
    OPTIONAL("Optional"),
    COMMENT("Comment"),
    SUGGESTION("Suggestion"),
    OFFTOPIC("Off topic");

    private String tagName;

    private Tag(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return tagName;
    }
}
