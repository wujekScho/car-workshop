<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 07.04.2019
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Edytuj pojazd</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Pojazdy</h2>
<form action="EditVehicle" method="post">
    <label>Marka <input name="brand" type="text" value="${vehicle.brand}" required/></label><br>
    <label>Model <input name="model" type="text" value="${vehicle.model}" required/></label><br>
    <label>Rok produkcji <input name="manufactureYear" type="number" min="1900" max="${currentYear}"
                                value="${vehicle.manufactureYear}" required/></label><br>
    <label>Numer rejestracyjny<input name="registrationNumber" type="text" value="${vehicle.registrationNumber}"
                                     required/></label><br>
    <label>Data przeglądu <input name="serviceDate" type="date" value="${vehicle.serviceDate}" required/></label><br>
    <label>Właściciel <select name="customer" required>
        <C:forEach items="${customers}" var="customer">
            <option value="${customer.id}">${customer.name} ${customer.surname}</option>
        </C:forEach>
    </select></label><br>
    <input type="hidden" name="id" value="${vehicle.id}">
    <button type="submit">Edytuj pojazd</button>
    <br>
</form>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>