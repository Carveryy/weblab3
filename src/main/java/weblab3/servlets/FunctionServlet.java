package weblab3.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import weblab3.model.FunctionCalculator;
import weblab3.model.Point;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FunctionServlet", value = "/calculate")
public class FunctionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double start = Double.parseDouble(request.getParameter("start"));
        double end = Double.parseDouble(request.getParameter("end"));
        double step = Double.parseDouble(request.getParameter("step"));

        FunctionCalculator calculator = new FunctionCalculator();
        calculator.calculatePoints(start, end, step);

        List<Point> points = calculator.getPoints();
        int minIndex = calculator.getMinIndex();
        int maxIndex = calculator.getMaxIndex();

        request.setAttribute("points", points);
        request.setAttribute("minIndex", minIndex);
        request.setAttribute("maxIndex", maxIndex);
        request.setAttribute("minPoint", points.get(minIndex));
        request.setAttribute("maxPoint", points.get(maxIndex));
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}