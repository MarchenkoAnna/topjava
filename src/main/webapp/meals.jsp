<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Marchelaga
  Date: 10.12.2016
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Meal List</title>
    <style>
        .green {
            color: green;
        }

        .red    {
            color: red;
        }
    </style>
</head>
<body>
<section>
<h2><a href="index.html">Home</a></h2>
<h3>Meal list</h3>
    <a href="meals?action=create">Add Meal</a>
<hr>
<table border = "1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <%--<c:set var="id" value="${0}"/>--%>
<c:forEach var="meal" items="${exceededMeal}">
    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <tr class="${meal.exceeded? 'red' : 'green'}">
            <td >${meal.dateTime.toLocalDate()}&nbsp;${meal.dateTime.toLocalTime()}</td>
            <td >${meal.description}</td>
            <td >${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
</c:forEach>
</table>
</section>
</body>
</html>
