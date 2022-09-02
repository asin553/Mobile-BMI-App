package com.example.bmicalculatormvc;

import java.util.List;

public class WebPageResult {
    private double bmi;
    private List<String> more;
    private String risk;

    public double getBodyMassIndex() {
        return bmi;
    }

    public void setBodyMassIndex(double bmi) {
        this.bmi = bmi;
    }

    public List<String> getWebLinks() {
        return more;
    }

    public void setWebLinks(List<String> more) {
        this.more = more;
    }

    public String getRiskStatus() {
        return risk;
    }

    public void setRiskStatus(String risk) {
        this.risk = risk;
    }
}
