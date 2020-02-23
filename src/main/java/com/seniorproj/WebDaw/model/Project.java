package com.seniorproj.WebDaw.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project {

    @Id
    String id;
    String projName;
    String projDuration;
    String projDateCreated;

    int selectedKit;
    int vol;
    int tempo;
    // stepState
    int kick[] = new int[16];
    int snare[] = new int[16];

    public Project() {
    }

    public Project(String projName, String projDuration, String projDateCreated, int selectedKit, int vol, int tempo, int[] kick, int[] snare) {
        super();
        this.projName = projName;
        this.projDuration = projDuration;
        this.projDateCreated = projDateCreated;
        this.selectedKit = selectedKit;
        this.vol = vol;
        this.tempo = tempo;
        // stepState
        this.kick = kick;
        this.snare = snare;
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

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int[] getKick() {
        return kick;
    }

    public void setKick(int[] kick) {
        this.kick = kick;
    }

    public int[] getSnare() {
        return snare;
    }

    public void setSnare(int[] snare) {
        this.snare = snare;
    }
}
