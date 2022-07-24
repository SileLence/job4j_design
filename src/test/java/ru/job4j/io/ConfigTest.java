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
        assertThat(config.value("server.name"), is("test_server"));
        assertThat(
                config.value("server.url"),
                is("https://example.com/startpage")
        );
        assertThat(config.value("startup.keys"), is("one,two"));
        assertThat(config.value("access.level"), is("admin=2"));
    }

    @Test
    public void whenHasCommentEmptyLineSpace() {
        String path
                = "src/main/resources/test_comment_empty_line_space.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("server.name"), is("test_server"));
        assertThat(
                config.value("server.url"),
                is("https://example.com/startpage")
        );
        assertThat(config.value("startup.keys"), is("one,two"));
        assertThat(config.value("access.level"), is("admin=2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoEqualsSymbol() {
        String path = "src/main/resources/test_no_equals_symbol.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoKey() {
        String path = "src/main/resources/test_no_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoValue() {
        String path = "src/main/resources/test_no_value.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoKeyNoValue() {
        String path = "src/main/resources/test_no_key_no_value.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongFormat() {
        String path = "src/main/resources/test_wrong_format.properties";
        Config config = new Config(path);
        config.load();
    }
}
