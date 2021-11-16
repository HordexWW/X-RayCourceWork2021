package com.sshmygin.io;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.sshmygin.xray.handler.TableDataToRawCsvDataConverter;
import com.sshmygin.xray.model.TableData;
import lombok.AllArgsConstructor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CsvIO {

    private TableDataToRawCsvDataConverter dataConverter;

    public List<String[]> ReadCsvData(String csvDataPath) {
        List<String[]> rawData = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvDataPath))) {
            rawData = reader.readAll();
        } catch (IOException | CsvException e) {
            System.out.println("Could not to read data from file: " + csvDataPath);
        }
        return rawData;
    }

    public void WriteCsvData(TableData data, String csvDataPath, String fileName) {
        List<String[]> rawCsvData = dataConverter.getRawCsvData(data);
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvDataPath + fileName + ".csv"))) {
            writer.writeAll(rawCsvData);
        } catch (IOException e) {
            System.out.println("Could not to write data to file: " + csvDataPath + fileName);
        }
    }
}
