package com.projects.ttp.models;

import com.projects.ttp.utils.math_utils;

enum Balancers {
    RANDOM,
    GREEDY
}

public class Sack {
    private double weight, cost;
    private boolean[] selection_list;

    public Sack(boolean[] _selection_list, Problem p) {
        this.selection_list = _selection_list;
        this.calculate_cost(p);
        this.calculate_weight(p);
        this.balance_sack(p, Balancers.RANDOM);
    }

    private void balance_sack(Problem p, Balancers balancer) {
        switch (balancer) {
            case RANDOM: {
                random_balancer(p);
                break;
            }
            case GREEDY: {
                greedy_balancer(p);
                break;
            }
        }
    }

    private void greedy_balancer(Problem p) {
        // TODO
    }

    private void random_balancer(Problem p) {
        for (int j = this.selection_list.length - 1; j > 0 && this.weight > p.Capacity; j--) {
            int random_index = math_utils.random(0, j);
            this.remove_item(random_index, p);
        }
    }

    public void calculate_cost(Problem p) {
        this.cost = 0;
        for (int i = 0; i < this.selection_list.length; i++) {
            if (this.selection_list[i]) {
                this.cost += p.items[i].Profit;
            }
        }
    }

    public void calculate_weight(Problem p) {
        this.weight = 0;
        for (int i = 0; i < this.selection_list.length; i++) {
            if (this.selection_list[i]) {
                this.weight += p.items[i].Weight;
            }
        }
    }

    public void remove_item(int j, Problem p) {
        if (0 > j || j >= this.selection_list.length || !this.selection_list[j]) {
            return;
        }
        this.selection_list[j] = false;
        this.weight -= p.items[j].Weight;
        this.cost -= p.items[j].Profit;
    }

    public void add_item(int j, Problem p) {
        if (0 > j || j >= this.selection_list.length || this.selection_list[j]) {
            return;
        }
        this.selection_list[j] = true;
        this.weight += p.items[j].Weight;
        this.cost += p.items[j].Profit;
    }

    public boolean is_taken(Integer item_index) {
        return this.selection_list[item_index];
    }
}
