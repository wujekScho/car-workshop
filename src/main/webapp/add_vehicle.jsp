<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 07.04.2019
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Dodaj pojazd</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Pojazdy</h2>
<form action="AddVehicle" method="post">
    <label>Marka <input name="brand" type="text" required/></label><br>
    <label>Model <input name="model" type="text" required/></label><br>
    <label>Rok produkcji <input name="manufactureYear" type="number" min="1900" max="${currentYear}"
                                required/></label><br>
    <label>Numer rejestracyjny<input name="registrationNumber" type="text" required/></label><br>
    <label>Data przeglądu <input name="serviceDate" type="date" required/></label><br>
    <label>Właściciel <select name="customer" required>
        <C:forEach items="${customers}" var="customer">
            <option value="${customer.id}">${customer.name} ${customer.surname}</option>
        </C:forEach>
    </select>
    </label><br>
    <button type="submit">Dodaj pojazd</button>
    <br>
</form>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>