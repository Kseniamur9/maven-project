package org.example.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Root;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {
    private static final Logger logger = LogManager.getLogger(JsonWriter.class.getName());
    private static final String JSON_DIR = "jsonReqs";

    public static void writeToJson(Root root) {
        try {
            // Создание директории, если её нет
            File dir = new File(JSON_DIR);
            if (!dir.exists()) {
                dir.mkdir();
                logger.info("Создано директорию для JSON-файлов: {}", JSON_DIR);
            }

            // Формирование имени файла с датой (long-представление)
            long timestamp = System.currentTimeMillis();
            String fileName = String.format("req-%d.json", timestamp);
            File file = new File(dir, fileName);

            // Сериализация и запись в файл
            String json = JsonUtil.toJson(root);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(json);
                logger.info("JSON-файл успешно создан: {}/{}", JSON_DIR, fileName);
            }
        } catch (IOException e) {
            logger.error("Ошибка при записи JSON-файла: ", e);
        }
    }
}