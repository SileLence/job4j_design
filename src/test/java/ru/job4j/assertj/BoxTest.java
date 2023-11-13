package ru.job4j.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import org.junit.jupiter.api.Test;

class BoxTest {
    
    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 1);
        String type = box.whatsThis();
        assertThat(type)
            .isNotEmpty()
            .isEqualTo("Tetrahedron");
    }
    
    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String type = box.whatsThis();
        assertThat(type)
            .isMixedCase()
            .isEqualTo("Cube");
    }
    
    @Test
    void isCubeHasEightVertices() {
        Box box = new Box(8, 12);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices)
            .isEven()
            .isNotZero()
            .isEqualTo(8);
    }
    
    @Test
    void isSphereHasNoVertices() {
        Box box = new Box(0, 5);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices)
            .isZero()
            .isEqualTo(0);
    }
    
    @Test
    void isCubeExist() {
        Box box = new Box(8, 12);
        boolean isExist = box.isExist();
        assertThat(isExist)
            .isTrue()
            .isEqualTo(true);
    }
    
    @Test
    void isTetrahedronExist() {
        Box box = new Box(4, 6);
        boolean isExist = box.isExist();
        assertThat(isExist)
            .isTrue()
            .isNotEqualTo(false);
    }
    
    @Test
    void getAreaOfTetrahedron() {
        Box box = new Box(4, 6);
        double area = box.getArea();
        assertThat(area)
            .isGreaterThan(62d)
            .isLessThan(63d)
            .isEqualTo(62.35, withPrecision(2d));
    }
    
    @Test
    void getAreaOfSphere() {
        Box box = new Box(0, 6);
        double area = box.getArea();
        assertThat(area)
            .isCloseTo(452d, withPrecision(2d))
            .isEqualTo(452.39, withPrecision(2d));
    }
}
