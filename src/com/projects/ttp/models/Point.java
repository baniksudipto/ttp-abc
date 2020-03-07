package com.projects.ttp.models;

import com.projects.ttp.utils.math_utils;

public class Point {
    public double X, Y;

    public Point(String s) {
        String[] words = s.split("\\s+");
        X = Double.parseDouble(words[1].trim());
        Y = Double.parseDouble(words[2].trim());
    }

    public Point(double x, double y) {
        this.X = x;
        this.Y = y;
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.X - p2.X, 2) + Math.pow(p1.Y - p2.Y, 2));
    }

    public static boolean crossover(Point p1, Point p2, Point p3, Point p4) {
        // point1---->point2   point3---->point4
        // line1: u1 x+v1 y=w1
        double u1 = p2.Y - p1.Y;
        double v1 = p1.X - p2.X;
        double w1 = math_utils.determinant(p1.X, p1.Y, p2.X, p2.Y);

        // line2: u2x+v2y=w2
        double u2 = p4.Y - p3.Y;
        double v2 = p3.X - p4.X;
        double w2 = math_utils.determinant(p3.X, p3.Y, p4.X, p4.Y);

        if (math_utils.determinant(u1, v1, u2, v2) == 0) {
            return false;
        } else {
            double x = math_utils.determinant(v2, v1, w2, w1) / math_utils.determinant(u1, v1, u2, v2);
            double y = math_utils.determinant(u1, u2, w1, w2) / math_utils.determinant(u1, v1, u2, v2);
            return (Point.distance(new Point(x, y), p1) < Point.distance(p2, p1)) &&
                    (Point.distance(new Point(x, y), p3) < Point.distance(p4, p3));
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }

}