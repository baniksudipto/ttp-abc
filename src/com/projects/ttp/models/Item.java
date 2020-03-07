package com.projects.ttp.models;

public class Item {
    public double Profit, Weight;
    public int assignedCity;

    public Item(String s) {
        String[] ss = s.split("\\s+");
        Profit = Double.parseDouble(ss[1]);
        Weight = Double.parseDouble(ss[2]);
        assignedCity = Integer.parseInt(ss[3]) - 1; // -1 for resetting to zero based index
    }

    public Item(double profit, double weight, int assignedCity) {
        Profit = profit;
        Weight = weight;
        this.assignedCity = assignedCity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "Profit=" + Profit +
                ", Weight=" + Weight +
                ", AssignedNode=" + assignedCity +
                '}';
    }
}
