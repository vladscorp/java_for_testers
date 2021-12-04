package ru.stqa.jft.mantis.model;

public class IssueSoap {
    private int id;
    private String summary;
    private String description;
    private Project project;
    private String name;

    public int getId() {
        return id;
    }

    public IssueSoap withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public IssueSoap withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public IssueSoap withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public IssueSoap withProject(Project project) {
        this.project = project;
        return this;
    }

    public String getName() {
        return name;
    }

    public IssueSoap withName(String name) {
        this.name = name;
        return this;
    }

}
