package com.seniorproj.WebDaw.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Kick",
        "Snare",
        "HiHat",
        "HiHatOpen"
})
public class TrackStepState {
    @JsonProperty("Kick")
    private int[] kick;
    @JsonProperty("Snare")
    private int[] snare;
    @JsonProperty("HiHat")
    private int[] HiHat;
    @JsonProperty("HiHatOpen")
    private int[] HiHatOpen;

    @JsonProperty("Kick")
    public int[] getKick() {
        return this.kick;
    }

    @JsonProperty("Kick")
    public void setKick(int[] kick) {
        this.kick = kick;
    }

    @JsonProperty("Snare")
    public int[] getSnare() {
        return this.snare;
    }

    @JsonProperty("Snare")
    public void setSnare(int[] snare) {
        this.snare = snare;
    }

    @JsonProperty("HiHat")
    public int[] getHiHat() {
        return this.HiHat;
    }

    @JsonProperty("HiHat")
    public void setHiHat(int[] HiHat) {
        this.HiHat = HiHat;
    }

    @JsonProperty("HiHatOpen")
    public int[] getHiHatOpen() {
        return this.HiHatOpen;
    }

    @JsonProperty("HiHatOpen")
    public void setHiHatOpen(int[] HiHatOpen) {
        this.HiHatOpen = HiHatOpen;
    }
}
