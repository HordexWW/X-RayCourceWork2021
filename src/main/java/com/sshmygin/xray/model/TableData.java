package com.sshmygin.xray.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableData {
    private List<List<Integer>> data;

    private String fileName;

    public TableData(String fileName) {
        this.data = new ArrayList<>();
        this.fileName = fileName;
    }

    public TableData(List<String[]> rawData, String fileName) {
        this.fileName = fileName;
        this.data = new ArrayList<>();
        for ( String[] row : rawData) {
            List<Integer> tempIntRow= new ArrayList<>();
            for (String s : row) {
                tempIntRow.add(Integer.valueOf(s));
            }
            this.data.add(tempIntRow);
        }
    }

    public int[][] getPart(int i, int j) {
        return new int[][]{
                {this.data.get(i - 1).get(j - 1), this.data.get(i - 1).get(j), this.data.get(i - 1).get(j + 1)},
                {this.data.get(i).get(j - 1), this.data.get(i).get(j), this.data.get(i).get(j + 1)},
                {this.data.get(i + 1).get(j - 1), this.data.get(i + 1).get(j), this.data.get(i + 1).get(j + 1)}
        };
    }

    public void setData(List<List<Integer>> data) {
        this.data = data;
    }
}
