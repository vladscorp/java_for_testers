package ru.stqa.jft.mantis.model;

public class IssueRest {
    private int id;
    private String subject;
    private String description;
    private int state;

    public int getId() {
        return id;
    }

    public IssueRest withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public IssueRest withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public IssueRest withDescription(String description) {
        this.description = description;
        return this;
    }

    public int getState() {
        return state;
    }

    public IssueRest withState(int state) {
        this.state = state;
        return this;
    }

    @Override
    public String toString() {
        return "IssueRest{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssueRest issueRest = (IssueRest) o;

        if (id != issueRest.id) return false;
        if (subject != null ? !subject.equals(issueRest.subject) : issueRest.subject != null) return false;
        return description != null ? description.equals(issueRest.description) : issueRest.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
