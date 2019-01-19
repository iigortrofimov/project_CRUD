package project.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience {
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;
    private final Link link;

    public Experience(String title, LocalDate startDate, LocalDate endDate, String description, String name, String url) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.link = new Link(name, url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience that = (Experience) o;

        if (!title.equals(that.title)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return link.equals(that.link);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + link.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", link=" + link +
                '}';
    }
}
