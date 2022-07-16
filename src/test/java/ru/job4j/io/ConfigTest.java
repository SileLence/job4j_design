package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigTest {

    @Test
    public void whenIsClean() {
        String path = "src/main/resources/test_clean.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("server.name"),is("test_server"));
        assertThat(
                config.value("server.url"),
                is("https://example.com/startpage")
        );
        assertThat(config.value("startup.keys"),is("one, two"));
        assertThat(config.value("access.level"),is("admin = 2"));
    }

    @Test
    public void whenHasCommentsAndEmptyLines() {
        String path = "src/main/resources/test_comments_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("server.name"),is("test_server"));
        assertThat(
                config.value("server.url"),
                is("https://example.com/startpage")
        );
        assertThat(config.value("startup.keys"),is("one, two"));
        assertThat(config.value("access.level"),is("admin = 2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenHasErrors() {
        String path = "src/main/resources/test_errors.properties";
        Config config = new Config(path);
        config.load();
    }
}
