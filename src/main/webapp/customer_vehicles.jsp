<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 08.04.2019
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Klienci</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Pojazdy klienta: ${customer.name} ${customer.surname}</h2>
<c:choose>
    <c:when test="${empty vehicles}">
        <div>Dany klient nie ma przypisanych pojazdów</div>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>Marka</th>
                <th>Model</th>
                <th>Rok produkcji</th>
                <th>Numer rejestracjny</th>
                <th>Data kolejnego przeglądu</th>
            </tr>
            <c:forEach items="${vehicles}" var="vehicle">
                <tr>
                    <td>${vehicle.brand}</td>
                    <td>${vehicle.model}</td>
                    <td>${vehicle.manufactureYear}</td>
                    <td>${vehicle.registrationNumber}</td>
                    <td>${vehicle.serviceDate}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>