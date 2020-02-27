package com.projects.ttp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {
    public static void TTPFileParse(String filepath) throws IOException {
        String[] lines = Files.lines(new File(filepath).toPath()).map(String::trim).filter(s -> !s.isEmpty()).toArray(String[]::new);
        System.out.println(lines.length);
        Problem p = new Problem(lines);
        System.out.println(p);
    }
}

class Point {
    public double X, Y;

    //    public Point(double x,double y){
//        X = x;
//        Y = y;
//    }
    public Point(String s) {
        String[] words = s.split("\\s+");
        X = Double.parseDouble(words[1].trim());
        Y = Double.parseDouble(words[2].trim());
    }

    @Override
    public String toString() {
        return "Point{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}

class Item {
    public double Profit, Weight;
    public int AssignedNode;

    public Item(String s) {
        String[] ss = s.split("\\s+");
        Profit = Double.parseDouble(ss[1]);
        Weight = Double.parseDouble(ss[2]);
        AssignedNode = Integer.parseInt(ss[3]) - 1; // -1 for resetting to zero based index
    }

    public Item(double profit, double weight, int assignedNode) {
        Profit = profit;
        Weight = weight;
        AssignedNode = assignedNode;
    }

    @Override
    public String toString() {
        return "Item{" +
                "Profit=" + Profit +
                ", Weight=" + Weight +
                ", AssignedNode=" + AssignedNode +
                '}';
    }
}

class Problem {
    private static final int pointsStartAt = 10;
    public String ProblemName, DataType, EdgeWeightType;
    public int Dimension, NumberOfItems;
    public double Capacity, MinSpeed, MaxSpeed, RentingRatio;
    public Point[] points;
    public Item[] items;

    public Problem(String[] lines) {
        ProblemName = getValue(lines[0]);
        DataType = getValue(lines[1]);
        Dimension = Integer.parseInt(getValue(lines[2]));
        NumberOfItems = Integer.parseInt(getValue(lines[3]));
        Capacity = Double.parseDouble(getValue(lines[4]));
        MinSpeed = Double.parseDouble(getValue(lines[5]));
        MaxSpeed = Double.parseDouble(getValue(lines[6]));
        RentingRatio = Double.parseDouble(getValue(lines[7]));
        EdgeWeightType = getValue(lines[8]);
        points = makePoints(java.util.Arrays.stream(lines, pointsStartAt, pointsStartAt + Dimension));
        items = makeItems(java.util.Arrays.stream(lines, itemsStartAt(), itemsStartAt() + NumberOfItems));
    }

    private int itemsStartAt() {
        return Problem.pointsStartAt + Dimension + 1;
    }

    private String getValue(String value) {
        return value.trim().split(":")[1].trim();
    }

    private Point[] makePoints(Stream<String> stream) {
        return stream.map(Point::new).collect(Collectors.toList()).toArray(Point[]::new);
    }

    private Item[] makeItems(Stream<String> stream) {
        return stream.map(Item::new).collect(Collectors.toList()).toArray(Item[]::new);
    }

    @Override
    public String toString() {
        return "Problem{" +
                " \n ProblemName='" + ProblemName + '\'' +
                ",\n DataType='" + DataType + '\'' +
                ",\n EdgeWeightType='" + EdgeWeightType + '\'' +
                ",\n Dimension=" + Dimension +
                ",\n NumberOfItems=" + NumberOfItems +
                ",\n Capacity=" + Capacity +
                ",\n MinSpeed=" + MinSpeed +
                ",\n MaxSpeed=" + MaxSpeed +
                ",\n RentingRatio=" + RentingRatio +
                ",\n points=" + Arrays.toString(points) +
                ",\n items=" + Arrays.toString(items) +
                '}';
    }
}