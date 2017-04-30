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
    
    			<div class="continer">
			<h2>
				<b><c:out value="${currentOpenGag.getTitle()}"></c:out></b>
				<h6 style="color:grey;">
			&nbsp&nbsp&nbsp<c:out value="${currentOpenGag.getUpvotes() }"></c:out> points</h6>
				
			</h2>
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp	<img src="/Jape/image/${currentOpenGag.getGagID()}" height="450">
		<h6 style="color:grey;">Uploaded by:<a href="/Jape/profile/${currentOpenGag.getUserId()}">  <c:out value="${currentOpenGag.userName() }"></c:out></a></h6>
			<hr>
			
			
		</div>
		
		<h4><b>Comments:</b></h4>
		<hr>
		<c:forEach var="comment" items="${currentOpenGag.motherShips() }">
		<a href="/Jape/profile/${comment.getUserId()}"><b><c:out value="${comment.userName()}"></c:out></a></b><br>
		
		<a href="/Jape/profile/${comment.getUserId()}"><img class="img-rounded" src="/Jape/commentPic/${comment.getProfilePicName()}/${comment.getProfilePicType()}" height="40" ></a>
						
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp	<c:out value="${ comment.getContent() }"></c:out><br><br>
</c:forEach>


<div class="well">
					<h4>Leave a comment</h4>
					<form action="/Jape/comment/${currentOpenGag.getGagID()}" method="Post" >

						<div class="field-wrap">
							<textarea class="form-control" name="message" maxlength="200" required></textarea>
						</div>
						
						<button type="submit" class="btn btn-primary" >Post</button>

					</form>
				</div>

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
