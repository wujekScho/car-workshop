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
    <input type="hidden" name="recived" value="${service.recived}"/>
    <input type="hidden" name="serviceId" value="${service.id}"/>
    <label>Pojazd <select class="field" name="vehicleId">
        <c:forEach items="${vehicles}" var="vehicle">
            <option value="${vehicle.id}">${vehicle.brand} ${vehicle.model} ${vehicle.registrationNumber}</option>
        </c:forEach>
    </select></label><br>
    <label>Pracownik <select class="field" name="workerId">
        <c:if test="${service.workerId==0}">
            <option value=0 selected disabled hidden>Wybierz pracownika</option>
        </c:if>
        <c:forEach items="${workers}" var="worker">
            <option value="${worker.id}">${worker.name} ${worker.surname}</option>
        </c:forEach>
    </select></label><br>
    <label>Opis problemu <textarea class="field"
                                   name="problemDescription">${service.problemDescription}</textarea></label><br>
    <label>Opis naprawy <textarea class="field" name="repairDescription">${service.repairDescription}</textarea></label><br>
    <label>Planowana data naprawy <input class="field" type="date" name="plannedRepairDate"
                                         value="${service.plannedRepairDate}"/></label><br>
    <label>Rzeczywista data naprawy <input class="field" type="date" name="repairDate"
                                           value="${service.repairDate}"/></label><br>
    <label>Koszt naprawy <input class="field" type="number" name="repairCost" step="0.01" min="0"
                                value="${service.repairCost}"/></label>
    <label>Koszt części <input class="field" type="number" name="partsCost" step="0.01" min="0"
                               value="${service.partsCost}"/></label>
    <label>Roboczogodziny <input class="field" type="number" name="workHours" step="0.5" min="0"
                                 value="${service.workHours}"/></label>
    <button type="submit">Edytuj zlecenie</button>
    <br>
</form>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/serviceStatus.js"></script>
</html>