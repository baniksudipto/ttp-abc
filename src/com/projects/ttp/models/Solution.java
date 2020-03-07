package com.projects.ttp.models;

public class Solution {
    private Tour tour;
    private Sack sack;
    private double profit = 0;

    public Solution(Tour tour, Sack sack, Problem p) {
        this.tour = tour;
        this.sack = sack;
        this.calculate_profit(p);
    }

    // TTP1 Objective
    private void calculate_profit(Problem p) {
        if (this.tour.sequence(0) != this.tour.sequence(-1)) {
            System.out.println("The last city must the same as the first city");
            return;
        }
        double weightSoFar = 0;
        double profitSoFar = 0;
        double travelTime = 0;
        for (int i = 0; i < tour.length() - 1; i++) {
            for (Integer item_index : p.cityToItemIndex.get(i)) {
                if (this.sack.is_taken(item_index)) {
                    weightSoFar += p.items[item_index].Weight;
                    profitSoFar += p.items[item_index].Profit;
                }
            }
            travelTime += Point.distance(p.cities[i], p.cities[i + 1]) / (p.MaxSpeed - (weightSoFar * (p.MaxSpeed - p.MinSpeed) / p.Capacity));
        }
        this.profit = profitSoFar - travelTime * p.RentingRatio;
    }
}
