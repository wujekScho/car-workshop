<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 10.04.2019
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zlecenia</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Dodaj zlecenie</h2>
<form method="post" action="AddService">
    <label>Pojazd <select name="vehicleId">
        <c:forEach items="${vehicles}" var="vehicle">
            <option value="${vehicle.id}">${vehicle.brand} ${vehicle.model} ${vehicle.registrationNumber}</option>
        </c:forEach>
    </select></label><br>
    <label>Opis problemu <textarea name="problemDescription" required></textarea></label><br>
    <button type="submit">Dodaj zlecenie</button>
    <br>
</form>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>