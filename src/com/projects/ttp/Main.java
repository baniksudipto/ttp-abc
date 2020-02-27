package com.projects.ttp;

public class Main {

    public static void main(String[] args) {
        try {
            Parser.TTPFileParse("/home/sudipta/IdeaProjects/TravellingTheifProblem/test/files/sample.ttp");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
