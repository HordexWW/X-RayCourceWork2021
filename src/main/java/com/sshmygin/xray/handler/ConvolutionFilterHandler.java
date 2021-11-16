package com.sshmygin.xray.handler;

import com.sshmygin.xray.model.TableData;

import java.util.ArrayList;
import java.util.List;


public class ConvolutionFilterHandler {

    public TableData getDataHandledByConvolutionFilter(TableData beforeData, float[][] kernel) {

        List<List<Integer>> tempData = new ArrayList<>();

        int cols = beforeData.getData().get(0).size();
        int rows = beforeData.getData().size();

        for (int i = 0; i < rows; i++) {
            List<Integer> tempRow = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                tempRow.add(getFilteredValueOf(beforeData, i, j, kernel));
            }
            tempData.add(tempRow);
        }

        TableData newData = new TableData(beforeData.getFileName() + "Conv");
        newData.setData(tempData);

        return newData;
    }

    public Integer getFilteredValueOf(TableData data, int i, int j, float[][] kernel) {

        if ((i >= 1 && i < data.getData().size() - 1) && (j > 0 && j < data.getData().get(0).size() - 1)) {
            int[][] part = data.getPart(i, j);

            int result = (int) (part[0][0] * kernel[0][0] + part[0][1] * kernel[0][1] + part[0][2] * kernel[0][2] +
                    part[1][0] * kernel[1][0] + part[1][1] * kernel[1][1] + part[1][2] * kernel[1][2] +
                    part[2][0] * kernel[2][0] + part[2][1] * kernel[2][1] + part[2][2] * kernel[2][2]);

            return result;
        } else {
            return 0;
        }
    }

}
