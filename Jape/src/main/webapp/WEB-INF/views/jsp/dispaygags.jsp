<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
</head>

<body>

<jsp:include page="navigationBar.jsp" />

<c:forEach var="gag" items="${sessionScope.gags }">
<c:out value="${gag.getGagID() }"></c:out><br>
<img src="image/${gag.getGagID()}"/>
</c:forEach>
</body>

</html>
