package org.example.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.Statistics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XlsWriter {
    public static void writeStatisticsToExcel(List<Statistics> statisticsList, String filePath) {
        // Создаём новую книгу Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            // Создаём лист
            Sheet sheet = workbook.createSheet("Statistics");

            // Создаём стиль для заголовков
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);

            // Создаём заголовок
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Profile", "Average Exam Score", "Student Count", "University Count", "University Names"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Заполняем данными
            int rowNum = 1;
            for (Statistics stats : statisticsList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(stats.getStudyProfile().getProfileName());
                row.createCell(1).setCellValue(stats.getAvgExamScoreAsString());
                row.createCell(2).setCellValue(stats.getStudentCount());
                row.createCell(3).setCellValue(stats.getUniversityCount());
                row.createCell(4).setCellValue(String.join(", ", stats.getUniversityNames()));
            }

            // Автоматическая настройка ширины столбцов
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Записываем файл
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write Excel file: " + filePath, e);
        }
    }
}
