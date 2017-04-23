<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jape</title>
</head>
<body>
<img  src="data:image/jpeg;base64,${sessionScope.gag.getEncode()}" alt="" width="900"><br>
<h2> Uploaded by: </h2> <c:out value="${sessionScope.gag.getUserId() }"></c:out>
<h2>Points: </h2> <c:out value="${sessionScope.gag.getUpvotes() }"></c:out>
<h2>Comments:</h2>
<c:forEach var="comment" items="${sessionScope.gag.getComments() }">
<c:out value="${ comment.getContent() }"></c:out>
</c:forEach>
</body>
</html>