package com.sshmygin.xray.handler;

import com.sshmygin.xray.model.TableData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableDataToRawCsvDataConverter {
    public List<String[]> getRawCsvData(TableData data) {
        List<String[]> rawCsvData = new ArrayList<>();

        for (List<Integer> row : data.getData()) {
            List<String> stringList = row.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            String[] tempRow = stringList.toArray(String[]::new);
            rawCsvData.add(tempRow);
        }
        return rawCsvData;
    }
}
