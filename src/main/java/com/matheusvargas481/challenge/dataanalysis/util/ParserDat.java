package com.matheusvargas481.challenge.dataanalysis.util;

import java.util.ArrayList;
import java.util.List;

public class ParserDat {

    private String separator;

    public ParserDat(String separator) {
        this.separator = separator;
    }

    public List<String[]> createObjects(List<String> lines) {
        List<String[]> objects = new ArrayList<>();
        lines.forEach(line -> objects.add(line.split(separator)));
        return objects;
    }
}
