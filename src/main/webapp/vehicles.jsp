<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 24.03.2019
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Pojazdy</h2>
<table>
    <tr>
        <th>Marka</th>
        <th>Model</th>
        <th>Rok produkcji</th>
        <th>Numer rejestracjny</th>
        <th>Data kolejnego przeglądu</th>
        <th>Właściciel</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${vehicles}" var="vehicle">
        <tr>
            <td>${vehicle.brand}</td>
            <td>${vehicle.model}</td>
            <td>${vehicle.manufactureYear}</td>
            <td>${vehicle.registrationNumber}</td>
            <td>${vehicle.serviceDate}</td>
            <td><a href="ShowCustomerDetails?id=${vehicle.customerId}">Właściciel</a></td>
            <td><a href="DeleteVehicle?id=${vehicle.id}">Usuń</a> <a href="EditVehicle?id=${vehicle.id}">Edytuj</a></td>
        </tr>
    </c:forEach>
</table>
<a href="AddVehicle">Dodaj pojazd</a>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>
