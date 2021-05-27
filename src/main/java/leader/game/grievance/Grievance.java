package leader.game.grievance;

import leader.game.Town;

import java.util.ArrayList;
import java.util.List;

/**
 * A Grievance is a complaint/issue that a citizen has and would like the monarch to resolve
 */
public class Grievance {
    // Person with grievance
    private byte[] person;
    private Town originatingTown;

    // Grievance description
    private String summary;
    private String description;

    // Person the dispute is with
    private byte[] otherPerson;
    private Town otherTown;

    private List<GrievanceResolution> possibleResolutions = new ArrayList<>();
    private GrievanceResolution resolution;

    public Grievance(){}

    public Grievance(byte[] person, Town town, String summary, String description, byte[] otherPerson, Town otherTown){
        this.person = person;
        this.originatingTown = town;
        this.summary = summary;
        this.description = description;
        this.otherPerson = otherPerson;
        this.otherTown = otherTown;
    }

    public byte[] getPerson() {
        return person;
    }

    public void setPerson(byte[] person) {
        this.person = person;
    }

    public Town getOriginatingTown() {
        return originatingTown;
    }

    public void setOriginatingTown(Town originatingTown) {
        this.originatingTown = originatingTown;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getOtherPerson() {
        return otherPerson;
    }

    public void setOtherPerson(byte[] otherPerson) {
        this.otherPerson = otherPerson;
    }

    public Town getOtherTown() {
        return otherTown;
    }

    public void setOtherTown(Town otherTown) {
        this.otherTown = otherTown;
    }

    public GrievanceResolution getResolution() {
        return resolution;
    }

    public void setResolution(GrievanceResolution resolution) {
        this.resolution = resolution;
    }

    public List<GrievanceResolution> getPossibleResolutions() {
        return possibleResolutions;
    }

    public void addPossibleResolution(GrievanceResolution resolution){
        this.possibleResolutions.add(resolution);
    }
}
