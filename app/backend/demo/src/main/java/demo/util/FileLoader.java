package demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

public class FileLoader {
    private static final Map<String, String> cache = new ConcurrentHashMap<>();

    public static String load(String file) {
        return cache.computeIfAbsent(file, f -> {
            try (final InputStream stream = new ClassPathResource(file).getInputStream()) {
                return StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }
}