package hu.clientbase.entity;

public enum EventType {
    MEETING("Meeting"),
    BUSINESS_DINNER("Business dinner"),
    PRODUCT_LAUNCH("Product launch"),
    SEMINAR("Seminar"),
    TRAINING("Training");
    
    private String eventTypeName;

    private EventType(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    @Override
    public String toString() {
        return eventTypeName;
    }  
}
