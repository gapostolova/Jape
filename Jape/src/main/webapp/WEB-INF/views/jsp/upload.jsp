<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="Cache-control" content="no-cache">

    <title>Jape</title>

  
    <!-- Pop Up Login 
  
    <link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css"> -->
    
    <!-- Bootstrap Core CSS -->
    <link href="/Jape/css/bootstrap.min.css" rel="stylesheet">

	<!-- Bootstrap CSS -->
    <link href="/Jape/css/bootstrap.css" rel="stylesheet">
	
    <!-- Custom CSS -->
    <link href="/Jape/css/blog-home.css" rel="stylesheet">
    
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


<script>
$(document).ready(function(e) {
    var $input = $('#refresh');

    $input.val() == 'yes' ? location.reload(true) : $input.val('yes');
});
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>



</head>
<body>

<input type="hidden" id="refresh" value="no">

<jsp:include page="navigationBar.jsp" />

<div class="container">
<div class="col-md-8">

<h1>Upload jape:</h1><br>
<form method="POST" enctype="multipart/form-data">
	<input class="form-control" type="file" id="file" name="failche" accept="image/*" required> <br><br>
	<input class="form-control" type="text" id="title" name="title" placeholder="Enter title" size="30" required> <br><br>
	<input class="form-control" type="hidden" id="userId" name="userId" value="${sessionScope.userId }">
	<c:out value="Is it safe for work?"></c:out> <br>
	
	<input type="radio" id="nsfw" name="nsfw" value="false" checked> It's safe<br>
  	<input type="radio" id="nsfw" name="nsfw" value="true"> It's raunchy<br><br>
  
  <c:out value="Is this a private jape?"></c:out> <br>
  <input type="radio" id="isPublic" name="isPublic" value="true" checked> Show it to the world<br>
  <input type="radio" id="isPublic" name="isPublic" value="false"> It's private<br> <br>
  <c:out value="Select categories: "></c:out> <br>
  <c:forEach var="category" items="${ sessionScope.categories }">
  <input  type="checkbox" id="checkbox" name="category" value="${ category}"> <c:out value="${category.toLowerCase() }"></c:out> <br>
  </c:forEach>
  <br>
	<input type="submit" value="Upload now">
</form>

</div>
</div>

<script>
var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];    
function Validate(oForm) {
    var arrInputs = oForm.getElementsByTagName("input");
    for (var i = 0; i < arrInputs.length; i++) {
        var oInput = arrInputs[i];
        if (oInput.type == "file") {
            var sFileName = oInput.value;
            if (sFileName.length > 0) {
                var blnValid = false;
                for (var j = 0; j < _validFileExtensions.length; j++) {
                    var sCurExtension = _validFileExtensions[j];
                    if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                        blnValid = true;
                        break;
                    }
                }
                
                if (!blnValid) {
                    alert("Sorry, " + sFileName + " is invalid, allowed extensions are: " + _validFileExtensions.join(", "));
                    return false;
                }
            }
        }
    }
  
    return true;
}
</script>
</body>
</html>
