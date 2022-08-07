package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalyzeTest {

    @Test
    void whenUnavailable(@TempDir Path tempDir) {
        StringBuilder actual = new StringBuilder();
        File source = tempDir.resolve("source.txt").toFile();
        File target = tempDir.resolve("target.txt").toFile();
        String expected = "10:57:00;11:02:00;11:17:00;11:30:00;12:05:00;12:10:00;";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(source))) {
            writer.write(
                    """
                    200 10:56:00
                    500 10:57:00
                    400 10:58:00
                    500 10:59:00
                    400 11:01:00
                    200 11:02:00
                    200 11:15:00
                    500 11:17:00
                    400 11:21:00
                    200 11:30:00
                    500 12:05:00
                    200 12:10:00""");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(actual::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(actual.toString()).isEqualTo(expected);
    }
}
