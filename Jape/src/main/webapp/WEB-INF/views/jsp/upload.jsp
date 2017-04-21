<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Upload file please</h1>
<form method="POST" enctype="multipart/form-data">
	<input type="file" id="file" name="failche" accept="image/*">
	<input type="submit" value="Upload now">
</form>
<h2>File uploaded with name = ${filename}</h2>	

<img src="image/${filename}">
</body>
</html>
