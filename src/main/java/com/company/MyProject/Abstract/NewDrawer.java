package com.company.MyProject.Abstract;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public abstract class NewDrawer {
    public static void main(ArrayList<String> methods, ArrayList<String> fields, ArrayList<ArrayList<ArrayList<Double>>> value) {

        try (XSSFWorkbook wb = new XSSFWorkbook()) {// create book in format xlsx;
            XSSFSheet sheet = wb.createSheet("Results");// create page, named "Results";


            Row row;
            Cell cell;
            int offset = 0;

            for (int i = 0; i < fields.size(); i++) {
                row = sheet.createRow(0 + offset); // пишем название типа массива
                cell = row.createCell(0);
                cell.setCellValue(fields.get(i));

                for (int collIndex = 2; collIndex <= methods.size(); collIndex++) {
                    cell = row.createCell((short) collIndex);
                    cell.setCellValue(methods.get(collIndex - 2));
                }// заполняем имена методов

                int size = 1;
                for (int rowIndex = 1 + offset; rowIndex <= value.get(i).size() + offset; rowIndex++) {
                    row = sheet.createRow((short) rowIndex);
                    for (int collIndex = 1; collIndex <= methods.size(); collIndex++) {
                        cell = row.createCell((short) collIndex);
                        if (collIndex == 1) {
                            cell.setCellValue(size);
                        } else {
                            cell.setCellValue(value.get(i).get(rowIndex - 1 - offset).get(collIndex - 1));
                        }


                    }
                    size++;
                }


                XSSFDrawing drawing = sheet.createDrawingPatriarch();
                XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, value.get(0).size() + 2 + offset,
                        methods.size(), 3 * value.get(0).size() + offset);

                XSSFChart chart = drawing.createChart(anchor);
                XDDFChartLegend legend = chart.getOrAddLegend();
                legend.setPosition(LegendPosition.LEFT);

                XDDFCategoryAxis bottomX = chart.createCategoryAxis(AxisPosition.BOTTOM);
                bottomX.setTitle("Size of ARRAy");

                XDDFValueAxis leftY = chart.createValueAxis(AxisPosition.LEFT);
                leftY.setTitle("Time");
                leftY.setCrosses(AxisCrosses.AUTO_ZERO);


                XDDFLineChartData graphic = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomX, leftY);
                ArrayList<XDDFNumericalDataSource<Double>> data = new ArrayList<>();

                XDDFNumericalDataSource x = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                        new CellRangeAddress(1 + offset, value.get(i).size() + offset, 1, 1));

                for (int j = 2; j < methods.size(); j++) {
                    XDDFNumericalDataSource<Double> y = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(
                            1 + offset, value.get(0).size() + offset, j, j));
                    data.add(y);
                }

                for (int j = 0; j < data.size(); j++) {
                    XDDFLineChartData.Series series = (XDDFLineChartData.Series) graphic.addSeries(x, data.get(j));
                    series.setTitle(methods.get(j), null);

                }

                chart.plot(graphic);
                offset += 2 * value.get(i).size() + 10;


            }


            try (FileOutputStream fileOut = new FileOutputStream("Result.xlsx")) {
                wb.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
