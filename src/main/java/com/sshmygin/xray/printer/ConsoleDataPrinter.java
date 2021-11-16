package com.sshmygin.xray.printer;

import com.sshmygin.xray.model.TableData;

import java.util.List;

public class ConsoleDataPrinter implements DataPrinter {

    @Override
    public void printData(TableData data) {
        for (List<Integer> row : data.getData()) {
            System.out.println(row);
        }
        System.out.println();
    }

    @Override
    public void printDataPart(int[][] part) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(part[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
