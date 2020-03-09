package com.seniorproj.WebDaw.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.seniorproj.WebDaw.model.TrackStepState;
import com.seniorproj.WebDaw.model.TrackVol;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "config",
        "vol",
        "bpm",
        "playing",
        "stepState",
        "trackVol",
        "currentStep"
})
@Document(collection = "projects")
public class Project {

    @Id
    String id;
    String projName;
    String projDuration;
    String projDateCreated;

    int selectedKit;
    int vol;
    int bpm;
    // stepState -- Rename these to generic types in case the user wants to rename them.
    @JsonProperty("stepState")
    private TrackStepState stepState = null;

    //trackVol -- Rename these to generic types in case the user wants to rename them.
    @JsonProperty("trackVol")
    private TrackVol trackVol = null;

    public Project() {
    }

    public Project(String projName, String projDuration, String projDateCreated, int selectedKit, int vol, int bpm, TrackStepState stepState, TrackVol trackVol) {
        super();
        this.projName = projName;
        this.projDuration = projDuration;
        this.projDateCreated = projDateCreated;
        this.selectedKit = selectedKit;
        this.vol = vol;
        this.bpm = bpm;
        this.stepState = stepState;
        this.trackVol = trackVol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjDuration() {
        return projDuration;
    }

    public void setProjDuration(String projDuration) {
        this.projDuration = projDuration;
    }

    public String getProjDateCreated() {
        return projDateCreated;
    }

    public void setProjDateCreated(String projDateCreated) {
        this.projDateCreated = projDateCreated;
    }

    public int getSelectedKit() {
        return selectedKit;
    }

    public void setSelectedKit(int selectedKit) {
        this.selectedKit = selectedKit;
    }

    @JsonProperty("vol")
    public int getVol() {
        return vol;
    }

    @JsonProperty("vol")
    public void setVol(int vol) {
        this.vol = vol;
    }

    @JsonProperty("bpm")
    public int getBpm() {
        return bpm;
    }

    @JsonProperty("bpm")
    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    @JsonProperty("stepState")
    public TrackStepState getStepState() {
        return stepState;
    }

    @JsonProperty("stepState")
    public void setStepState(TrackStepState stepState) {
        this.stepState = stepState;
    }

    @JsonProperty("trackVol")
    public TrackVol getTrackVol() {
        return trackVol;
    }

    @JsonProperty("trackVol")
    public void setTrackVol(TrackVol trackVol) {
        this.trackVol = trackVol;
    }
}
