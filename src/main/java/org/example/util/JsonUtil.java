package org.example.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class JsonUtil {
    private static final Logger logger = LogManager.getLogger(JsonUtil.class.getName());
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(ZonedDateTime.class, new TypeAdapter<ZonedDateTime>() {
                private final DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

                @Override
                public void write(JsonWriter out, ZonedDateTime value) throws IOException {
                    if (value == null) {
                        out.nullValue();
                    } else {
                        out.value(formatter.format(value));
                    }
                }

                @Override
                public ZonedDateTime read(JsonReader in) throws IOException {
                    return ZonedDateTime.parse(in.nextString(), formatter);
                }
            })
            .create();

    private JsonUtil() {
        throw new UnsupportedOperationException("Класс-утилита, не может быть создан");
    }

    public static <T> String toJson(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            logger.warn("Попытка сериализации пустой или null коллекции");
            return "[]";
        }
        String json = gson.toJson(collection);
        logger.debug("Сериализация коллекции в JSON: {}", json);
        return json;
    }

    public static <T> String toJson(T object) {
        if (object == null) {
            logger.warn("Попытка сериализации null объекта");
            return "{}";
        }
        String json = gson.toJson(object);
        logger.debug("Сериализация объекта в JSON: {}", json);
        return json;
    }
}