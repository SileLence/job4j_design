package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Set;

class SimpleConvertTest {
    
    private final SimpleConvert simpleConvert = new SimpleConvert();
    
    @Test
    void checkArray() {
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array)
            .hasSize(5)
            .contains("second")
            .contains("first", Index.atIndex(0))
            .containsAnyOf("zero", "second", "six")
            .doesNotContain("first", Index.atIndex(1));
    }
    
    @Test
    void checkList() {
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list)
            .anySatisfy(e -> {
                assertThat(e).isLowerCase();
                assertThat(e).isNotNull();
            })
            .doesNotContain("six")
            .isNotInstanceOf(Number.class)
            .endsWith("five");
    }
    
    @Test
    void checkSet() {
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set)
            .hasSizeGreaterThan(1)
            .doesNotContainNull()
            .element(0)
            .isExactlyInstanceOf(String.class);
    }
    
    @Test
    void checkMap() {
        Map<String, Integer> map = simpleConvert.toMap("zero", "first", "second", "three", "four", "five");
        assertThat(map)
            .isNotEmpty()
            .extractingByKey("three")
            .isEqualTo(3);
    }
}