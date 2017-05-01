<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="errorPage.jsp" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><c:out value="${sessionScope.user.getUsername()}"></c:out> - Jape</title>
<link href="/Jape/css/bootstrap1.css" rel="stylesheet" type="text/css" media="all">
<link href="/Jape/css/style1.css" rel="stylesheet" type="text/css" media="all" />

  <!-- Bootstrap Core CSS -->
    <link href="/Jape/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/Jape/css/blog-home.css" rel="stylesheet">
    
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="My Skills Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script src="/Jape/js/jquery-1.11.1.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="/Jape/js/move-top.js"></script>
<script type="text/javascript" src="/Jape/js/easing.js"></script>
 <script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event){		
				event.preventDefault();
				$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
			});
		});
	</script>

<script>
$(document).ready(function(e) {
    var $input = $('#refresh');

    $input.val() == 'yes' ? location.reload(true) : $input.val('yes');
});
</script>

 <script type="text/javascript">
function show(elementId) { 
 document.getElementById("id1").style.display="none";
 document.getElementById("id2").style.display="none";
 document.getElementById("id3").style.display="none";
 document.getElementById("id4").style.display="none";
 document.getElementById(elementId).style.display="block";
}
</script>
 
</head>
<body>

<input type="hidden" id="refresh" value="no">

<jsp:include page="navigationBar.jsp" />
		<!--start-header-section-->
			<div class="header-section">
				<div class="continer">
					<img class="img-rounded" src="/Jape/commentPic/${profil.getProfilePicName()}/${profil.getProfilePicType()}" height="150" >
						<h1> <c:out value="${profil.getUsername()}"></c:out></p></h1>
							<p> <c:out value="${profil.getDescription()}"></c:out></p>
							<a href="#contact" class="scroll top"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></a>
				</div>
			</div>
		<!--end header-section-->
		   <div class="containerCenter">
		    <div class="collaps
            e navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li style="font-size:20px">
                        <a onclick="show('id1');">Japes</a>
                    </li>
                    <li style="font-size:20px">
                        <a onclick="show('id2');">Liked</a>
                    </li>
                    <li style="font-size:20px">
                        <a onclick="show('id3');">Commented</a>
                    </li>
                    <li style="font-size:20px">
                        <a onclick="show('id4');">Videos</a>
                    </li>
                    </ul>
                    </div>
     </div>
		
		
		
		
		
		
			
					<!--start-social-section-->
				<div class="social-icons">
				
				<div  id="id1">
				<c:if test="${profil.myGags().isEmpty() }">
						<h3>I don't have any japes yet..</h3>
						</c:if>
						<c:if test="${!profil.myGags().isEmpty() }">
						<h3>My Japes</h3>
						</c:if>
						
				
			<div class="container">
						
						<c:forEach var="gag" items="${profil.myGags()}">
							<div class="container">
							<h2><c:out value="${gag.getTitle()}"></c:out></h2>

								<a href="/Jape/jape/${gag.getGagID()}"><img src="/Jape/image/${gag.getGagID()}"  width="650"/></a><br><br>

								
								<hr>
								<br>
								</div>
						</c:forEach>
						
						
							</div>
					</div>
							
							
					<div style="display:none"  id="id2">	
						<c:if test="${profil.likedGags().isEmpty()}">
						<h3>No liked japes yet..</h3>
						</c:if>
						<c:if test="${!profil.likedGags().isEmpty()}">
						<h3>Liked Japes</h3>
						</c:if>
				<div class="container">
						
						<c:forEach var="gag" items="${profil.likedGags() }">
							<div class="container">
							<c:if test="${gag.isVideo()== true }">
								<a href="/Jape/jape/${gag.getGagID()}"> <h2><c:out value="${gag.getTitle()}"></c:out></h2></a>
								<iframe width="560" height="315" src="${gag.getGag()}" frameborder="0" allowfullscreen></iframe>
							</c:if>
							<c:if test="${gag.isVideo() == false }">
							<h2><c:out value="${gag.getTitle()}"></c:out></h2>

								<a href="/Jape/jape/${gag.getGagID()}"><img src="/Jape/image/${gag.getGagID()}"  width="650"/></a><br><br>
							</c:if>
								
								<hr>
								<br>
								</div>
						</c:forEach>
						
						
							</div>
						</div>
						
						
						
						<div  style="display:none"  id="id4">
				<c:if test="${profil.myVideos().isEmpty() }">
						<h3>No videos yet..</h3>
						</c:if>
						<c:if test="${!profil.myVideos().isEmpty() }">
						<h3>My Videos</h3>
						</c:if>
				
			<div class="container">
						
						<c:forEach var="gag" items="${profil.myVideos() }">
							<div class="container">
							<a href="/Jape/jape/${gag.getGagID()}"> <h2><c:out value="${gag.getTitle()}"></c:out></h2></a>
								<iframe width="560" height="315" src="${gag.getGag()}" frameborder="0" allowfullscreen></iframe>
								<br><br>

								
								<hr>
								<br>
								</div>
						</c:forEach>
						
						
							</div>
							
				</div>	
						 
						
						<div style="display:none" id="id3">	
						<c:if test="${profil.commented().isEmpty()}">
						<h3>No commented japes yet..</h3>
						</c:if>
						<c:if test="${!profil.commented().isEmpty()}">
						<h3>Commented Japes</h3>
						</c:if>
				<div class="container">
						
						<c:forEach var="gag" items="${profil.commented()}">
						
							<div class="container">
							<c:if test="${gag.isVideo()== true }">
								<a href="/Jape/jape/${gag.getGagID()}"> <h2><c:out value="${gag.getTitle()}"></c:out></h2></a>
								<iframe width="560" height="315" src="${gag.getGag()}" frameborder="0" allowfullscreen></iframe>
							</c:if>
							<c:if test="${gag.isVideo() == false }">
							<h2><c:out value="${gag.getTitle()}"></c:out></h2>

								<a href="/Jape/jape/${gag.getGagID()}"><img src="/Jape/image/${gag.getGagID()}"  width="650"/></a><br><br>
							</c:if>
								
								<hr>
								<br>
								</div>
						</c:forEach>
						
						
							</div>
						</div>
						</div>
							<!--end-social-section-->
							<!--start-contact-section-->
						<div class="contact-section" id="contact">
				
	</div>
	<!--end-contact-section-->
				<!--start-map-section-->
				
			<!--end-map-section-->
			<!--start-footer-section-->
			<div class="footer-section">
						<div class="container">
							<div class="footer-top">
						<p>&copy; 2017 <span>Hello.</span> All rights reserved </p>
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
							
							$().UItoTop({ easingType: 'easeOutQuart' });
							
						});
					</script>
				<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
				</div>
			</div>
	<!--end-footer-section-->


</body>
</html> 