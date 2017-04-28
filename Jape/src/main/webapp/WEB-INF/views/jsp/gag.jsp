<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
  <%@ page errorPage="errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><c:out value="${sessionScope.user.getUsername()}"></c:out>
	- Jape</title>
<link href="/Jape/css/bootstrap1.css" rel="stylesheet" type="text/css"
	media="all">
<link href="/Jape/css/style1.css" rel="stylesheet" type="text/css"
	media="all" />


<!-- register/login css && js -->

<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  
      <link rel="stylesheet" href="/Jape/css/style.css">
      
       <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="/Jape/js/index.js"></script>
      
<!--  end register/login css -->


<!-- Bootstrap Core CSS -->
<link href="/Jape/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="/Jape/css/blog-home.css" rel="stylesheet">

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="My Skills Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<script src="/Jape/js/jquery-1.11.1.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="/Jape/js/move-top.js"></script>
<script type="text/javascript" src="/Jape/js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1200);
		});
	});
</script>
<!---End-smoth-scrolling---->
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
	<!--start-header-section-->
	<div class="header-section">
		<div class="continer">
			<h1>
				<c:out value="${currentOpenGag.getTitle()}"></c:out>
				</p>
			</h1>
			<img src="/Jape/image/${currentOpenGag.getGagID()}" height="500">
			<h6>Uploaded by:</h6>
			<a href="/Jape/profile/${currentOpenGag.getUserId()}"> <h6><c:out value="${currentOpenGag.userName() }"></c:out></h6></a>
			<h2>Points:</h2>
			<c:out value="${currentOpenGag.getUpvotes() }"></c:out>
			
		</div>
	</div>
	<!--end header-section-->








	<!--start-social-section-->
	
	<div  class="social-icons">
		<h3>Comments</h3>
		<c:forEach var="comment" items="${currentOpenGag.motherShips() }">
		<a href="/Jape/profile/${comment.getUserId()}"><b><c:out value="${comment.userName()}"></c:out></a></b><br>
		
		<img class="img-rounded" src="/Jape/commentPic/${comment.getProfilePicName()}/${comment.getProfilePicType()}" height="40" >
						
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp	<c:out value="${ comment.getContent() }"></c:out><br><br>
			
				<!-- when comment a comment starts working 
                          <button class="btn btn-default"style="font-size : 11px;height:30px;width:50px" type="submit" onClick ="<c:set var="repliedTo" value="${comment.getCommentId()}" scope="session"  />"><a href="#contact" class="scroll top">Reply</a></button><br><br>
				<c:set var="i" value="${comment.getCommentId()}" scope="session"  />
				<!-- child = child comment 
				
					<c:forEach var="child" items="${currentOpenGag.commentsOfMother(sessionScope.i) }">
					<c:out value="${ child.getUserId()}"></c:out><br>
					&nbsp&nbsp&nbsp<c:out value="${child.getContent()}"></c:out><br>
						 
				<!--  <form action="/Jape/comment" method="post" >
						
					
					  &nbsp&nbsp&nbsp<button class="btn btn-default"style="font-size : 11px;height:30px;width:50px" type="submit" id="submitButton" onClick ="<c:set var="repliedTo" value="${child.getCommentId()}" scope="session" />"><a href="#contact" class="scroll top">Reply</a></button><br><br>
					
				
         
         <!--  </FORM> 
					</c:forEach> -->
		</c:forEach>
	</div>
	<!--end-social-section-->
	
	<!--start-contact-section-->
	<div class="contact-section" id="contact">
	
		<div class="container">
			<div class="contact-details">
				 <form action="/Jape/comment/${currentOpenGag.getGagID()}" method="Post" >
					<div class="col-md-6 contact-left">
				<h4>Comment</h4>	     
             <div class="field-wrap">
              <textarea  type="text" required autocomplete="off"  name="message"></textarea>
            </div>
							<input type="submit" value="Post" />
					</div>
								
					<div class="clearfix"></div>
				</form>
			</div>
		</div>
	</div>
	<!--end-contact-section-->
	
	<!--start-footer-section-->
	<div class="footer-section">
		<div class="container">
			<div class="footer-top">
				<p>
					&copy; 2017 All rights reserved  
				</p>
			</div>
			<script type="text/javascript">
				$(document).ready(function() {
					/*
					var defaults = {
						containerID: 'toTop', // fading element id
						containerHoverID: 'toTopHover', // fading element hover id
						scrollSpeed: 1200,
						easingType: 'linear' 
					};
					 */

					$().UItoTop({
						easingType : 'easeOutQuart'
					});

				});
			</script>
			<a href="#" id="toTop" style="display: block;"> <span
				id="toTopHover" style="opacity: 1;"> </span></a>
		</div>
	</div>
	<!--end-footer-section-->


</body>
</html>
