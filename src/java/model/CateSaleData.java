/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LNV
 */
public class CateSaleData {
    private String name;
    private int revernue;
    private double percentage;

    public CateSaleData() {
    }

    public CateSaleData(String name, int revernue, double percentage) {
        this.name = name;
        this.revernue = revernue;
        this.percentage = percentage;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRevernue(int revernue) {
        this.revernue = revernue;
    }

    public int getRevernue() {
        return revernue;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }


}
