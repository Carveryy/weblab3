package weblab3.model;

import java.util.ArrayList;
import java.util.List;

public class FunctionCalculator {
    private final double a = 2.8;
    private final double b = -0.3;
    private final double c = 4.0;

    // Список точок (вимога п.5: private + метод доступу)
    private List<Point> points = new ArrayList<>();

    // п.2: Обчислення значення функції
    public double calculateY(double x) {
        // Щоб уникнути проблем з точністю чисел з плаваючою крапкою (наприклад, 1.399999999 замість 1.4)
        double epsilon = 1e-9;

        if (x < 1.4 - epsilon) {
            return a * x * x + b * x + c;
        } else if (Math.abs(x - 1.4) <= epsilon) {
            return (a / x) + Math.sqrt(x * x + 1);
        } else {
            return (a + b * x) / Math.sqrt(x * x + 1);
        }
    }

    // п.3: Обчислення кількості кроків
    public int calculateSteps(double start, double end, double step) {
        return (int) Math.round((end - start) / step) + 1;
    }

    // п.5: Формування списку точок
    public void calculatePoints(double start, double end, double step) {
        points = new ArrayList<>();
        int steps = calculateSteps(start, end, step);

        for (int i = 0; i < steps; i++) {
            double currentX = start + i * step;
            double currentY = calculateY(currentX);
            points.add(new Point(currentX, currentY));
        }
    }

    // Метод доступу до списку
    public List<Point> getPoints() {
        return points;
    }

    // п.6: Пошук номера елемента з найменшим Y
    public int getMinIndex() {
        if (points.isEmpty()) return -1;
        int minIndex = 0;
        for (int i = 1; i < points.size(); i++) {
            if (points.get(i).y() < points.get(minIndex).y()) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    // п.6: Пошук номера елемента з найбільшим Y
    public int getMaxIndex() {
        if (points.isEmpty()) return -1;
        int maxIndex = 0;
        for (int i = 1; i < points.size(); i++) {
            if (points.get(i).y() > points.get(maxIndex).y()) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
