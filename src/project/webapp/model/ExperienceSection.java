package project.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ExperienceSection extends Section {
    private static final long serialVersionUID = 1L;

    private final List<Experience> experiences;

    public ExperienceSection(Experience... experiences) {
        this(Arrays.asList(experiences));
    }

    public ExperienceSection(List<Experience> experienceList) {
        Objects.requireNonNull(experienceList, "experiences must not be null");
        this.experiences = experienceList;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExperienceSection that = (ExperienceSection) o;

        return experiences.equals(that.experiences);

    }

    @Override
    public int hashCode() {
        return experiences.hashCode();
    }

    @Override
    public String toString() {
        return experiences.toString();
    }
}