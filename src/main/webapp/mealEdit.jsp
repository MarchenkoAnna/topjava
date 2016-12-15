<%--
  Created by IntelliJ IDEA.
  User: Marchelaga
  Date: 12.12.2016
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add New Meal</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }
        dt {
            display: inline-block;
            width: 170px;
        }
        dd{
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
        </style>
</head>
<body>
<section>
    <h2><a href="">Home</a></h2>
    <h3>Edit meal</h3>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form action="meals" method="POST">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>Время:</dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime"></dd>
        </dl>
        <dl>
            <dt>Описание:</dt>
            <dd><input type="text" value="${meal.description}" size = "40" name="description"></dd>
        </dl>
        <dl>
            <dt>Калории:</dt>
            <dd><input type="number" value="${meal.calories}" name="calories"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onClick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
