package com.projects.ttp;

import com.projects.ttp.models.Problem;

public class Main {

    public static void main(String[] args) {
        try {
            Problem p = Parser.TTPFileParse("/home/sudipta/IdeaProjects/TravellingTheifProblem/test/files/sample.ttp");
            System.out.println(p);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
