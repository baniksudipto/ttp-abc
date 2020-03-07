package com.projects.ttp.utils;

public class math_utils {
    public static int random(int start, int end) {
        return (int) Math.round(Math.random() * (end - start + 1));
    }

    public static double determinant(double a, double b, double c, double d) {
        //  |a  b|
        //  |c  d|
        return a * d - b * c;
    }
}
