package com.projects.ttp.models;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem {
    private static final int pointsStartAt = 10;
    public String ProblemName, DataType, EdgeWeightType;
    public int NumberOfCities, NumberOfItems;
    public double Capacity, MinSpeed, MaxSpeed, RentingRatio;
    public Point[] cities;
    public Item[] items;
    public Double[][] DistanceMatrix;

    public Map<Integer, List<Integer>> cityToItemIndex;

    public Problem(String[] lines) {
        ProblemName = getValue(lines[0]);
        DataType = getValue(lines[1]);
        NumberOfCities = Integer.parseInt(getValue(lines[2]));
        NumberOfItems = Integer.parseInt(getValue(lines[3]));
        Capacity = Double.parseDouble(getValue(lines[4]));
        MinSpeed = Double.parseDouble(getValue(lines[5]));
        MaxSpeed = Double.parseDouble(getValue(lines[6]));
        RentingRatio = Double.parseDouble(getValue(lines[7]));
        EdgeWeightType = getValue(lines[8]);
        setCities(java.util.Arrays.stream(lines, pointsStartAt, pointsStartAt + NumberOfCities));
        setItems(java.util.Arrays.stream(lines, itemsStartAt(), itemsStartAt() + NumberOfItems));
        DistanceMatrix = makeDistanceMatrix();
        createCityToItemMapping();

    }

    private void createCityToItemMapping() {
        this.cityToItemIndex = new HashMap<>();
        Integer j = 0;
        for (Item item : this.items) {
            List<Integer> itemList;
            if (cityToItemIndex.containsKey(item.assignedCity)) {
                itemList = cityToItemIndex.get(item.assignedCity);
            } else {
                itemList = new ArrayList<>();
            }
            itemList.add(j);
            cityToItemIndex.put(item.assignedCity, itemList);
        }
    }

    private Double[][] makeDistanceMatrix() {
        this.DistanceMatrix = new Double[NumberOfCities][NumberOfCities];
        for (int i = 0; i < NumberOfCities; i++) {
            for (int j = 0; j < NumberOfCities; j++) {
                this.DistanceMatrix[i][j] = Point.distance(this.cities[i], this.cities[j]);
            }
        }
        return this.DistanceMatrix;
    }

    private int itemsStartAt() {
        return Problem.pointsStartAt + NumberOfCities + 1;
    }

    private String getValue(String value) {
        return value.trim().split(":")[1].trim();
    }

    private void setCities(Stream<String> stream) {
        this.cities = stream.map(Point::new).collect(Collectors.toList()).toArray(Point[]::new);
    }

    private void setItems(Stream<String> stream) {
        this.items = stream.map(Item::new).collect(Collectors.toList()).toArray(Item[]::new);
    }

    @Override
    public String toString() {
        return "Problem{" +
                "\nProblemName='" + ProblemName + '\'' +
                ",\n DataType='" + DataType + '\'' +
                ",\n EdgeWeightType='" + EdgeWeightType + '\'' +
                ",\n NumberOfCities=" + NumberOfCities +
                ",\n NumberOfItems=" + NumberOfItems +
                ",\n Capacity=" + Capacity +
                ",\n MinSpeed=" + MinSpeed +
                ",\n MaxSpeed=" + MaxSpeed +
                ",\n RentingRatio=" + RentingRatio +
                ",\n cities=" + Arrays.toString(cities) +
                ",\n items=" + Arrays.toString(items) +
                "\n}";
    }
}