package com.gloriousfury.africantime.model;

public class TimeView {


    private Integer id;

    private String formattedTime;
    private String digitalTime;
    private String design;
    private String language;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    public String getDigitalTime() {
        return digitalTime;
    }

    public void setDigitalTime(String digitalTime) {
        this.digitalTime = digitalTime;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}