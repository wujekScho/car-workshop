<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: schodziak
  Date: 15.03.19
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aktualne naprawy</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Aktualne naprawy</h2>
<table>
    <tr>
        <th>Marka</th>
        <th>Model</th>
        <th>Otrzymano</th>
        <th>Opis problemu</th>
        <th>Status</th>
        <th>Panowana data naprawy</th>
        <th>Data naprawy</th>
        <th>Pracownik</th>
        <th>Opis Naprawy</th>
        <th>Koszt naprawy</th>
        <th>Koszt części</th>
        <th>Stawka godzinowa</th>
        <th>Roboczogodziny</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${services}" var="service">
        <tr>
            <td>${service.vehicleBrand}</td>
            <td>${service.vehicleModel}</td>
            <td>${service.recived}</td>
            <td>${service.problemDescription}</td>
            <td>
                <c:choose>
                    <c:when test="${service.status == 'accepted'}">Zaakceptowano</c:when>
                    <c:when test="${service.status == 'repairCostApproved'}">Zaakceptowano koszty naprawy</c:when>
                    <c:when test="${service.status == 'underRepair'}">Podczas naprawy</c:when>
                    <c:when test="${service.status == 'readyToPickup'}">Gotowy do odebrania</c:when>
                    <c:when test="${service.status == 'completed'}">Zakończono</c:when>
                    <c:when test="${service.status == 'cancelled'}">Rezygnacja</c:when>
                    <c:otherwise>undefined</c:otherwise>
                </c:choose>
            </td>
            <td>${service.plannedRepairDate}</td>
            <td>${service.repairDate}</td>
            <td>${service.workerName} ${service.workerSurname}</td>
            <td>${service.repairDescription}</td>
            <td>${service.repairCost} zł</td>
            <td>${service.partsCost} zł</td>
            <td>${service.ratePerHour} zł</td>
            <td>${service.workHours}</td>
            <td>Status
                <form action="EditService" method="get">
                    <select name="status">
                        <option value='accepted'>Zaakceptowano</option>
                        <option value='repairCostApproved'>Zaakceptowano koszty naprawy</option>
                        <option value='underRepair'>Podczas naprawy</option>
                        <option value='readyToPickup'>Gotowy do odebrania</option>
                        <option value='completed'>Zakończono</option>
                        <option value='cancelled'>Rezygnacja</option>
                    </select>
                    <input type="hidden" name="serviceId" value="${service.id}">
                    <button type="submit">Zmień</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="AddService">Dodaj zlecenie</a>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>
