<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page errorPage="errorPage.jsp" %>
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
<div class="col-md-5">
	<input class="form-control" type="file" id="file" name="failche" accept="image/*" required> <br>
	<input class="form-control" type="text" id="title" name="title" placeholder="Enter title" size="30" required> <br>
	<input class="form-control" type="hidden" id="userId" name="userId" value="${sessionScope.userId }">
	</div><br><br><br><br><br><br>
	
	
	<h3>&nbsp&nbsp<c:out value="Is it safe for work?"></c:out></h3>
	<div class="radio">
	<input type="radio" id="nsfw" name="nsfw" value="false" checked><h4>&nbsp&nbspIt's safe</h4></div>
	<div class="radio">
  	<input type="radio" id="nsfw" name="nsfw" value="true"><h4>&nbsp&nbspIt's raunchy</h4></div><br>
  
  <h3>&nbsp&nbsp<c:out value="Is this a private jape?"></c:out></h3>
  <div class="radio">
  <input type="radio" id="isPublic" name="isPublic" value="true" checked><h4>&nbsp&nbspShow it to the world</h4></div>
  <div class="radio">
  <input type="radio" id="isPublic" name="isPublic" value="false"><h4>&nbsp&nbspIt's private</h4></div><br>
  <h3>&nbsp&nbsp<c:out value="Select categories: "></c:out></h3>
  
  <c:forEach var="category" items="${ sessionScope.categories }">
  <c:if test="${!category.equalsIgnoreCase('youtube') }">
  <div class="checkbox">
  <input  type="checkbox" id="checkbox" name="category" value="${ category}"><h5>&nbsp&nbsp<c:out value="${category.toLowerCase() }"></c:out></h5>
  </div>
   </c:if>
  </c:forEach>
  

  <br>
	&nbsp<button class="btn btn-primary" type="submit">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspUpload now&nbsp&nbsp&nbsp<span class="glyphicon glyphicon-upload"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</button>
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
