<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload</title>
</head>
<body>



<h1>Upload jape:</h1><br>
<form method="POST" enctype="multipart/form-data">
	<input type="file" id="file" name="failche" accept="image/*" required> <br><br>
	<input type="text" id="title" name="title" placeholder="Enter title" size="30" required> <br><br>
	<input type="hidden" id="userId" name="userId" value="${sessionScope.userId }">
	<c:out value="Is it safe for work?"></c:out> <br>
	<input type="radio" id="nsfw" name="nsfw" value="false" checked> It's safe<br>
  <input type="radio" id="nsfw" name="nsfw" value="true"> It's raunchy<br><br>
  <c:out value="Is this a private jape?"></c:out> <br>
  <input type="radio" id="isPublic" name="isPublic" value="true" checked> Show it to the world<br>
  <input type="radio" id="isPublic" name="isPublic" value="false"> It's private<br> <br>
  <c:out value="Select categories: "></c:out> <br>
  <c:forEach var="category" items="${ sessionScope.categories }">
  <input type="checkbox" id="checkbox" name="category" value="${ category}"> <c:out value="${category.toLowerCase() }"></c:out> <br>
  </c:forEach>
  <br>
	<input type="submit" value="Upload now">
</form>
</body>
</html>
