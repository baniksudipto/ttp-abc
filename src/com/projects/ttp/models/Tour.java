package com.projects.ttp.models;

public class Tour {
    private int[] city_sequence;
    private double cost = 0;

    public Tour(int[] city_sequence, Problem p) {
        this.city_sequence = new int[city_sequence.length + 1];
        System.arraycopy(city_sequence, 0, this.city_sequence, 0, city_sequence.length);
        this.city_sequence[city_sequence.length] = city_sequence[0];
        this.calculate_cost(p);
    }

    public void calculate_cost(Problem p) {
        this.cost = 0;
        for (int i = 0; i < this.city_sequence.length - 1; i++) {
            this.cost += p.DistanceMatrix[city_sequence[i]][city_sequence[i + 1]];
        }
    }

    public void two_opt(int p1, int p2, int p3, int p4, Problem p) {
        // p1 -> p2 ------------> p3 ---> p4 ---------> p1
        this.reverse_path(p2, p3);
        // new path p1 -> p3 , p2 -> p4 , old paths removed
        this.cost += p.DistanceMatrix[p1][p3] + p.DistanceMatrix[p2][p4] - p.DistanceMatrix[p1][p2] - p.DistanceMatrix[p3][p4];
    }

    private void reverse_path(int p2, int p3) {
        for (int i = p2, j = p3; i < j; i++, j--) {
            int t = this.city_sequence[i];
            this.city_sequence[i] = this.city_sequence[j];
            this.city_sequence[j] = t;
        }
    }

    public double getCost() {
        return cost;
    }

    public int sequence(int i) {
        if (i < 0) {
            i = this.city_sequence.length - i;
        }
        if (i >= city_sequence.length) {
            System.out.println("Invalid City Sequence Trying to access");
            return 0;
        }
        return city_sequence[i];
    }

    public int length() {
        return this.city_sequence.length;
    }
}
