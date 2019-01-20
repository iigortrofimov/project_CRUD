package project.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ExperienceSection implements Section {
    private final List<Experience> experienceList;

    public ExperienceSection(Experience... experiences){
        this(Arrays.asList(experiences));
    }

    public ExperienceSection(List<Experience> experienceList) {
        Objects.requireNonNull(experienceList, "experienceList must not be null");
        this.experienceList = experienceList;
    }

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExperienceSection that = (ExperienceSection) o;

        return experienceList.equals(that.experienceList);
    }

    @Override
    public int hashCode() {
        return experienceList.hashCode();
    }

    @Override
    public String toString() {
        return "ExperienceSection{" +
                "experienceList=" + experienceList +
                '}';
    }
}
