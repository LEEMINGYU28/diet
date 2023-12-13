package com.diets.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.diets.excel.domain.Food;

public class ExcelUtils {

    public static List<Food> readFromExcel(InputStream inputStream) throws IOException {
        List<Food> foods = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();

        Iterator<Row> rowIterator = sheet.iterator();
        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Food food = new Food();
            food.setFoodName(dataFormatter.formatCellValue(row.getCell(0)));
            food.setBrand(dataFormatter.formatCellValue(row.getCell(1)));
            food.setWeights((int) Double.parseDouble(dataFormatter.formatCellValue(row.getCell(2))));
            food.setCalorie(dataFormatter.formatCellValue(row.getCell(3)));
            food.setCarbohydrate(dataFormatter.formatCellValue(row.getCell(4)));
            food.setProtein(dataFormatter.formatCellValue(row.getCell(5)));
            food.setFat(dataFormatter.formatCellValue(row.getCell(6)));

            foods.add(food);
        }

        return foods;
    }
}
