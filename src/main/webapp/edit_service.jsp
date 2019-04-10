<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 10.04.2019
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edycja zlecenia</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Edycja zlecenia</h2>
<form method="post" action="EditService">
    <input type="hidden" name="status" value="${status}">
    <label>Pojazd <select name="vehicleId">
        <c:forEach items="${vehicles}" var="vehicle">
            <option value="${vehicle.id}">${vehicle.brand} ${vehicle.model} ${vehicle.registrationNumber}</option>
        </c:forEach>
    </select></label><br>
    <label>Pracownik <select name="workerId">
        <c:if test="${service.workerId==0}">
            <option value=0 selected disabled hidden>Wybierz pracownika</option>
        </c:if>
        <c:forEach items="${workers}" var="worker">
            <option value="${worker.id}">${worker.name} ${worker.surname}</option>
        </c:forEach>
    </select></label><br>
    <label>Opis problemu <textarea name="problemDescription"
                                   required>${service.problemDescription}</textarea></label><br>
    <label>Opis naprawy <textarea name="repairDescription">${service.repairDescription}</textarea></label><br>
    <label>Planowana data naprawy <input type="date" name="plannedRepairDate"
                                         value="${service.plannedRepairDate}"/></label><br>
    <label>Rzeczywista data naprawy <input type="date" name="repairDate" value="${service.repairDate}"/></label><br>
    <label>Koszt naprawy <input type="number" name="repairCost" step="0.01" min="0"
                                value="${service.repairCost}"/></label>
    <label>Koszt części <input type="number" name="partsCost" step="0.01" min="0" value="${service.partsCost}"/></label>
    <label>Roboczogodziny <input type="number" name="workHours" step="0.5" min="0"
                                 value="${service.workHours}"/></label>
    //todo tu skończyłem, dopisać jsa który będzie pokazywał pola i wymuszał wypałnianie w zależności od statusu, i
    potem posta
    <button type="submit">Edytuj zlecenie</button>
    <br>
</form>
<a href="AddService">Dodaj zlecenie</a>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>