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

 
</head>
<body>


<input type="hidden" id="refresh" value="no">

<jsp:include page="navigationBar.jsp" />

    <!-- Page Content -->
    <div id="gags" class="container">
    
    			<c:if test="${sessionScope.gags == null}">
    			<c:out value="No japes to show!"></c:out>
    			</c:if>
				<c:forEach var="gag" items="${sessionScope.gags }">
				<c:out value="${gag.getTitle() }"></c:out><br> <br>
				<a href="/Jape/jape/${gag.getGagID()}"><img src="/Jape/image/${gag.getGagID()}" width="500"/></a> <hr> <br> <br> <br>
				</c:forEach>

            <!-- Blog Sidebar Widgets Column -->
            <div class="col-md-4">

               
                    <!-- /.row -->
                </div>

    <!-- jQuery -->
    <script src="/Jape/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/Jape/js/bootstrap.min.js"></script>
   
    <!-- PopUp login 
    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>-->
    

</body>

</html>
