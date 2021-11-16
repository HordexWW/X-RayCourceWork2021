package com.sshmygin.xray.handler;

import com.sshmygin.xray.model.TableData;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DevariativeHandler {


    public TableData getHandledData(TableData beforeData) {

        List<List<Integer>> tempData = new ArrayList<>();

        int cols = beforeData.getData().get(1).size();
        int rows = beforeData.getData().size();


        for (int i = 0; i < rows; i++) {
            List<Integer> tempRow = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                tempRow.add(getHigherByAbs(beforeData, i, j));
            }
            tempData.add(tempRow);
        }

        TableData newData = new TableData(beforeData.getFileName() + "Devar");
        newData.setData(tempData);

        return newData;
    }

    private Integer getHigherByAbs(TableData data, int i, int j) {

        int downRightUpLeft, upRightDownLeft, rightLeft, downUp;

        if ((i > 0 && i < data.getData().size() - 1) && (j > 0 && j < data.getData().get(0).size() - 1)) {

            int[][] part = data.getPart(i, j);

            downRightUpLeft = (int) ((part[2][2] - part[0][0]) / 2 * Math.sqrt(2));
            upRightDownLeft = (int) ((part[0][2] - part[2][0]) / 2 * Math.sqrt(2));
            rightLeft = part[1][0] - part[1][2];
            downUp = part[2][1] - part[0][1];

            List<Integer> devariatives = new ArrayList<>(List.of(upRightDownLeft, downRightUpLeft, downUp, rightLeft));

            List<Integer> sorted = devariatives.stream()
                    .sorted((a, b) -> Integer.compare(Math.abs(b), Math.abs(a)))
                    .collect(Collectors.toList());

            return sorted.get(0);
        } else {
            return 0;
        }
    }
}
