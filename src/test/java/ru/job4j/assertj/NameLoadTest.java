package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    
    private final NameLoad nameLoad = new NameLoad();
    
    @Test
    void checkEmptyInputParams() {
        assertThatThrownBy(nameLoad::parse)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("empty");
    }
    
    @Test
    void checkEmptyCollection() {
        assertThatThrownBy(nameLoad::getMap)
            .isInstanceOf(IllegalStateException.class)
            .message()
            .isNotEmpty();
    }
    
    @Test
    void checkWrongKeyValuePair() {
        String wrongKeyValueStr = "key-value";
        assertThatThrownBy(() -> nameLoad.parse(wrongKeyValueStr))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(wrongKeyValueStr);
    }
    
    @Test
    void checkEmptyKey() {
        String emptyKeyStr = "=value";
        assertThatThrownBy(() -> nameLoad.parse(emptyKeyStr))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(emptyKeyStr);
    }
    
    @Test
    void checkEmptyValue() {
        String emptyValueStr = "key=";
        assertThatThrownBy(() -> nameLoad.parse(emptyValueStr))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(emptyValueStr);
    }
}