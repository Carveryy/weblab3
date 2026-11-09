package weblab3;

import weblab3.model.FunctionCalculator;
import weblab3.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FunctionCalculatorTest {

    private FunctionCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new FunctionCalculator();
    }

    // п. 2.1: Тестування методу calculateY(x)
    @Test
    void testCalculateY() {
        // x < 1.4 (наприклад x = 0.0) -> a*x^2 + b*x + c = 4.0
        assertEquals(4.0, calculator.calculateY(0.0), 1e-4);

        // x = 1.4 -> a/x + sqrt(x^2 + 1) = (2.8/1.4) + sqrt(1.96 + 1) = 2 + sqrt(2.96) ≈ 3.720465
        assertEquals(3.720465, calculator.calculateY(1.4), 1e-4);

        // x > 1.4 (наприклад x = 2.0) -> (a + b*x) / sqrt(x^2 + 1) = (2.8 - 0.6) / sqrt(5) ≈ 0.9838699
        assertEquals(0.983869, calculator.calculateY(2.0), 1e-4);
    }

    // п. 3.1: Тестування кількості кроків
    @Test
    void testCalculateSteps() {
        // [0; 2] з кроком 0.002 має дати 1001 точку (включно з 0)
        assertEquals(1001, calculator.calculateSteps(0.0, 2.0, 0.002));
    }

    // п. 5.1: Тестування формування списку та ПЕРЕВІРКА КОНКРЕТНИХ ІНДЕКСІВ (0, 700, 1000)
    @Test
    void testCalculatePointsSpecificIndices() {
        calculator.calculatePoints(0.0, 2.0, 0.002);
        List<Point> points = calculator.getPoints();

        assertNotNull(points);
        assertEquals(1001, points.size());

        // Перевірка елемента з індексом 0 (x = 0.0)
        assertEquals(0.0, points.get(0).x(), 1e-4);
        assertEquals(4.0, points.get(0).y(), 1e-4);

        // Перевірка елемента з індексом 700 (x = 0.0 + 700 * 0.002 = 1.4)
        assertEquals(1.4, points.get(700).x(), 1e-4);
        assertEquals(3.720465, points.get(700).y(), 1e-4);

        // Перевірка елемента з індексом 1000 (x = 0.0 + 1000 * 0.002 = 2.0)
        assertEquals(2.0, points.get(1000).x(), 1e-4);
        assertEquals(0.983869, points.get(1000).y(), 1e-4);
    }

    // п. 6.1: Тестування пошуку номерів найбільшого та найменшого елементів
    @Test
    void testMinMaxIndices() {
        calculator.calculatePoints(0.0, 2.0, 0.002);

        int minIndex = calculator.getMinIndex();
        int maxIndex = calculator.getMaxIndex();

        List<Point> points = calculator.getPoints();

        // Переконуємось, що індекси знаходяться в межах списку
        assertTrue(minIndex >= 0 && minIndex < points.size());
        assertTrue(maxIndex >= 0 && maxIndex < points.size());

        // Для цієї конкретної функції максимум буде на початку (x=0, y=4.0),
        // а мінімум - в кінці (x=2.0, y≈0.98)
        assertEquals(0, maxIndex);
        assertEquals(1000, minIndex);
    }
}