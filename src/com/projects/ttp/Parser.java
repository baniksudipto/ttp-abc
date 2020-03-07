package com.projects.ttp;

import com.projects.ttp.models.Problem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Parser {
    public static Problem TTPFileParse(String filepath) throws IOException {
        String[] lines = Files.lines(new File(filepath).toPath()).map(String::trim).filter(s -> !s.isEmpty()).toArray(String[]::new);
        Problem p = new Problem(lines);
        return p;
    }
}