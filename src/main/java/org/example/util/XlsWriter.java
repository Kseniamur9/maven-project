package org.example.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.Statistics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XlsWriter {
    private static final Logger logger = LogManager.getLogger(XlsWriter.class.getName());

    private XlsWriter() {
        throw new UnsupportedOperationException("Utility class, cannot be instantiated");
    }

    public static void writeStatisticsToExcel(List<Statistics> statistics, String filePath) throws IOException {
        logger.info("Starting to write statistics to Excel file: {}", filePath);
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Statistics");

            // Создание стиля для заголовков
            CellStyle headerStyle = workbook.createCellStyle();
            XSSFFont font = ((XSSFWorkbook) workbook).createFont();
            font.setFontName("Calibri");
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
            headerStyle.setFont(font);

            // Создание заголовков
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Profile", "Average Exam Score", "Student Count", "University Count", "University Names"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Заполнение данных
            int rowNum = 1;
            for (Statistics stat : statistics) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(stat.getStudyProfile().getProfileName());
                row.createCell(1).setCellValue(stat.getAvgExamScoreAsString());
                row.createCell(2).setCellValue(stat.getStudentCount());
                row.createCell(3).setCellValue(stat.getUniversityCount());
                row.createCell(4).setCellValue(stat.getUniversityNames().toString());
            }

            // Авторазмер столбцов
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Запись в файл
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                logger.info("Successfully wrote statistics to Excel file: {}", filePath);
            }
        } catch (IOException e) {
            logger.error("Failed to write statistics to Excel file: {}", filePath, e);
            throw e;
        }
    }
}