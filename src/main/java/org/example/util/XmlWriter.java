package org.example.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Root;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlWriter {
    private static final Logger logger = LogManager.getLogger(XmlWriter.class.getName());
    private static final String XML_DIR = "xmlReqs";

    public static void writeToXml(Root root) {
        try {
            // Создание директории, если её нет
            File dir = new File(XML_DIR);
            if (!dir.exists()) {
                dir.mkdir();
                logger.info("Создано директорию для XML-файлов: {}", XML_DIR);
            }

            // Формирование имени файла с датой (long-представление)
            long timestamp = System.currentTimeMillis();
            String fileName = String.format("req-%d.xml", timestamp);
            File file = new File(dir, fileName);

            // Инициализация JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // Форматированный вывод
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            // Маршаллинг и запись в файл
            marshaller.marshal(root, file);
            logger.info("XML-файл успешно создан: {}/{}", XML_DIR, fileName);
        } catch (JAXBException e) {
            logger.error("Ошибка при маршаллинге XML: ", e);
        }
    }
}