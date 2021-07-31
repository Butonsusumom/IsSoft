package com.tsybulka.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
    public static List<String[]> readCSV(String fileName) {
        String line = "";
        String splitBy = ",";
        List<String[]> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            line = br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] element = line.split(splitBy);
                list.add(element);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
