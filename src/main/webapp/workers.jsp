<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 24.03.2019
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pracownicy</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Pracownicy</h2>
<table>
    <tr>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Adres</th>
        <th>Numer telefonu</th>
        <th>Notatka</th>
        <th>Stawka za roboczogodzinę</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${workers}" var="worker">
        <tr>
            <td>${worker.name}</td>
            <td>${worker.surname}</td>
            <td>${worker.address}</td>
            <td>${worker.phoneNumber}</td>
            <td>${worker.note}</td>
            <td>${worker.ratePerHour} zł</td>
            <td><a href="DeleteWorker?id=${worker.id}">Usuń</a> <a href="EditWorker?id=${worker.id}">Edytuj</a>
                <a href="ShowWorkerServices?id=${worker.id}">Zlecenia</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a id="addWorkerBtn" href="AddWorker">Dodaj pracownika</a>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>

