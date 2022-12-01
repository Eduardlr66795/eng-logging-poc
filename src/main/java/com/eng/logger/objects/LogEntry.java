package com.eng.logger.objects;

import java.util.Map;

public class LogEntry {

    // A searchable high level tag that can be used to uniquely identify the given event.
    private final String tag;

    // A short description of the event that will provide additional information when debugging
    private final String description;

    // This attribute will be used to provide additional searchable context of the log
    private Map<String, ?> mdc;

    // Searchable term that will be used to uniquely identify the resource associated with the given event
    private Map<String, ?> attributes;

    public String getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, ?> getMdcAttributes() {
        return mdc;
    }

    public Map<String, ?> getAttributes() {
        return attributes;
    }

    /**
     * This method is used to check if a given logEntry has attributes associated with it.
     * True - If it has attributes associated with it.
     * False - If no attributes are associated with it.
     */
    public Boolean hasAttributes() {
        return attributes == null || !attributes.isEmpty();
    }

    /**
     * This method is used to check if a given logEntry has mdcAttributes associated with it.
     * True - If it has attributes associated with it.
     * False - If no attributes are associated with it.
     */
    public Boolean hasMdcAttributes() {
        return mdc == null || !mdc.isEmpty();
    }

    public LogEntry(String tag, String description, Map<String, ?> mdc, Map<String, ?> attributes) {
        this.description = description;
        this.tag = tag;

        // Set the attributes associated with the given log entry
        setAttributes(attributes);

        // Set the MDC attributes associated with the given log entry
        setMdcAttributes(attributes);
    }

    private void setAttributes(Map<String, ?> attributes) {
        if(!attributes.isEmpty()) {
            this.attributes = attributes;
        }
    }

    private void setMdcAttributes(Map<String, ?> mdc) {
        if(!mdc.isEmpty()) {
            this.mdc = mdc;
        }
    }
}