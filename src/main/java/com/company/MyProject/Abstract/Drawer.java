package com.company.MyProject.Abstract;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Drawer {
    private static String nameOfFile = "MyDiagram.xlsx";
    private static String nameOfSheet = "Results";

    // поменять оси


    public static void droweGraphic(ArrayList<String> methods, ArrayList<String> fields,
                                    ArrayList<ArrayList<ArrayList<Long>>> value, ArrayList<ArrayList<Integer>> sizes) {

        try (XSSFWorkbook wb = new XSSFWorkbook()) {// create book in format xlsx;



            Row row;
            Cell cell;

            for (int i = 0; i < fields.size(); i++) {
                XSSFSheet sheet = wb.createSheet(nameOfSheet + " for " + fields.get(i));// create page, named "Results";
                row = sheet.createRow(0);
                for (int collIndex = 1; collIndex <= sizes.size() + 1; collIndex++) {
                    cell = row.createCell((short) collIndex);
                    cell.setCellValue(sizes.get(i).get(collIndex - 1));

                }


                for (int rowIndex = 1; rowIndex <= value.get(i).size(); rowIndex++) {
                    row = sheet.createRow((short) rowIndex);
                    for (int collIndex = 0; collIndex <= methods.size(); collIndex++) {
                        cell = row.createCell((short) collIndex);
                        if (collIndex == 0) {
                            cell.setCellValue(methods.get(rowIndex - 1));
                        } else {
                            cell.setCellValue(value.get(i).get(rowIndex - 1).get(collIndex - 1));
                        }


                    }

                }


                XSSFDrawing drawing = sheet.createDrawingPatriarch();
                XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, value.get(0).size() + 2,
                        methods.size(), 3 * value.get(0).size() );

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
                        new CellRangeAddress(0, 0, 1, methods.size()));

               for (int j = 1; j <= methods.size(); j++) {
                    XDDFNumericalDataSource<Double> y = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                            new CellRangeAddress(j, j, 1, methods.size()));
                   data.add(y);
                }

                for (int j = 0; j < data.size(); j++) {
                    XDDFLineChartData.Series series = (XDDFLineChartData.Series) graphic.addSeries(x, data.get(j));
                    series.setTitle(methods.get(j), null);

                }

                chart.plot(graphic);


            }


            try (FileOutputStream fileOut = new FileOutputStream(nameOfFile)) {
                wb.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
