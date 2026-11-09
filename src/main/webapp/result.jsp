<%@ page import="weblab3.model.Point" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Отримуємо дані від сервлета
    List<Point> points = (List<Point>) request.getAttribute("points");
    Integer minIndex = (Integer) request.getAttribute("minIndex");
    Integer maxIndex = (Integer) request.getAttribute("maxIndex");
    Point minPoint = (Point) request.getAttribute("minPoint");
    Point maxPoint = (Point) request.getAttribute("maxPoint");
%>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Результати обчислень</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table, th, td { border: 1px solid #ccc; border-collapse: collapse; padding: 8px; text-align: center; }
        th { background-color: #f4f4f4; }
        .extremum { background-color: #e9f5ff; padding: 15px; border-radius: 5px; margin-bottom: 20px; display: inline-block;}
    </style>
</head>
<body>
<h2>Результати табулювання функції</h2>

<!-- Виведення екстремумів (пункт 7) -->
<div class="extremum">
    <p><b>Найбільше значення функції:</b> Точка № <%= maxIndex %> <br>
        (X = <%= String.format("%.3f", maxPoint.x()) %>, Y = <%= String.format("%.5f", maxPoint.y()) %>)</p>

    <p><b>Найменше значення функції:</b> Точка № <%= minIndex %> <br>
        (X = <%= String.format("%.3f", minPoint.x()) %>, Y = <%= String.format("%.5f", minPoint.y()) %>)</p>
</div>

<p><a href="index.html">← Ввести нові значення</a></p>

<!-- Таблиця точок -->
<table>
    <tr>
        <th>№ елемента</th>
        <th>Значення X</th>
        <th>Значення Y</th>
    </tr>
    <% for (int i = 0; i < points.size(); i++) {
        Point p = points.get(i);
    %>
    <tr>
        <td><%= i %></td>
        <td><%= String.format("%.3f", p.x()) %></td>
        <td><%= String.format("%.5f", p.y()) %></td>
    </tr>
    <% } %>
</table>
</body>
</html>