<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 08.04.2019
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Naprawy pracownika</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Aktualne naprawy pracownika: ${worker.name} ${worker.surname}</h2>
<c:choose>
    <c:when test="${empty services}">
        <div>Pracownik nie ma aktywnych zleceń.</div>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>Marka</th>
                <th>Model</th>
                <th>Otrzymano</th>
                <th>Opis problemu</th>
                <th>Status</th>
                <th>Panowana data naprawy</th>
                <th>Data naprawy</th>
                <th>Opis Naprawy</th>
                <th>Koszt naprawy</th>
                <th>Koszt części</th>
                <th>Stawka godzinowa</th>
                <th>Roboczogodziny</th>
            </tr>
            <c:forEach items="${services}" var="service">
                <tr>
                    <td>${service.vehicleBrand}</td>
                    <td>${service.vehicleModel}</td>
                    <td>${service.recived}</td>
                    <td>${service.problemDescription}</td>
                    <td>${service.status}</td>
                    <td>${service.plannedRepairDate}</td>
                    <td>${service.repairDate}</td>
                    <td>${service.repairDescription}</td>
                    <td>${service.repairCost} zł</td>
                    <td>${service.partsCost} zł</td>
                    <td>${service.ratePerHour} zł</td>
                    <td>${service.workHours}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>