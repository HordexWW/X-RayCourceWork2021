package com.sshmygin.xray;

import com.sshmygin.io.CsvIO;
import com.sshmygin.props.ApplicationProperties;
import com.sshmygin.xray.handler.ConvolutionFilterHandler;
import com.sshmygin.xray.handler.DevariativeHandler;
import com.sshmygin.xray.handler.TableDataToRawCsvDataConverter;
import com.sshmygin.xray.model.Kernel;
import com.sshmygin.xray.model.TableData;
import com.sshmygin.xray.printer.ConsoleDataPrinter;
import com.sshmygin.xray.printer.DataPrinter;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        String dataFileName = ApplicationProperties.getProperty("input.data.file.name");
        String dataInputPath = ApplicationProperties.getProperty("input.data.path") + dataFileName + ".csv";
        String dataOutputPath = ApplicationProperties.getProperty("output.data.path");
        CsvIO csvIO = new CsvIO(new TableDataToRawCsvDataConverter());

        List<String[]> rawData = csvIO.ReadCsvData(dataInputPath);

        TableData data = new TableData(rawData, dataFileName);

        ConvolutionFilterHandler convolutionFilterHandler = new ConvolutionFilterHandler();
        DevariativeHandler devariativeHandler = new DevariativeHandler();

        TableData dataDevarFirst = devariativeHandler.getHandledData(data);
        TableData dataDevarConv = convolutionFilterHandler.getDataHandledByConvolutionFilter(dataDevarFirst, Kernel.GAUSS);

        TableData dataConvFirst = convolutionFilterHandler.getDataHandledByConvolutionFilter(data, Kernel.GAUSS);
        TableData dataConvDevar = devariativeHandler.getHandledData(dataConvFirst);

        csvIO.WriteCsvData(dataDevarConv, dataOutputPath, "Chek21CutDevarConv");
        csvIO.WriteCsvData(dataConvDevar, dataOutputPath, "Chek21CutConvDevar");


    }
}
