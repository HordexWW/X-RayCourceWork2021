package com.sshmygin.xray.printer;

import com.sshmygin.xray.model.TableData;

public interface DataPrinter {
    void printData(TableData data);

    void printDataPart(int[][] part);
}
