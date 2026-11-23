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

    @Test
    void testCalculateY() {
        assertEquals(4.0, calculator.calculateY(0.0), 1e-4);
        assertEquals(3.720465, calculator.calculateY(1.4), 1e-4);
        assertEquals(0.983869, calculator.calculateY(2.0), 1e-4);
    }

    @Test
    void testCalculateSteps() {
        assertEquals(1001, calculator.calculateSteps(0.0, 2.0, 0.002));
    }

    @Test
    void testCalculatePointsSpecificIndices() {
        calculator.calculatePoints(0.0, 2.0, 0.002);
        List<Point> points = calculator.getPoints();

        assertNotNull(points);
        assertEquals(1001, points.size());
        assertEquals(0.0, points.get(0).x(), 1e-4);
        assertEquals(4.0, points.get(0).y(), 1e-4);
        assertEquals(1.4, points.get(700).x(), 1e-4);
        assertEquals(3.720465, points.get(700).y(), 1e-4);
        assertEquals(2.0, points.get(1000).x(), 1e-4);
        assertEquals(0.983869, points.get(1000).y(), 1e-4);
    }

    @Test
    void testMinMaxIndices() {
        calculator.calculatePoints(0.0, 2.0, 0.002);

        int minIndex = calculator.getMinIndex();
        int maxIndex = calculator.getMaxIndex();

        List<Point> points = calculator.getPoints();
        assertTrue(minIndex >= 0 && minIndex < points.size());
        assertTrue(maxIndex >= 0 && maxIndex < points.size());
        assertEquals(699, maxIndex);
        assertEquals(1000, minIndex);
    }
}