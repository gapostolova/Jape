<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page errorPage="errorPage.jsp" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><c:out value="${sessionScope.user.getUsername()}"></c:out> - Jape</title>
<link href="css/bootstrap1.css" rel="stylesheet" type="text/css" media="all">
<link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />

  <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/blog-home.css" rel="stylesheet">
    
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="My Skills Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script src="js/jquery-1.11.1.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
 <script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event){		
				event.preventDefault();
				$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
			});
		});
	</script>
	
	
	
	
<!---End-smoth-scrolling---->
 
</head>
<body>

<jsp:include page="navigationBar.jsp" />
		<!--start-header-section-->
			<div class="header-section">
				<div class="continer">
					<img class="img-rounded" src="profilePic/${sessionScope.user.getProfilePic() }" height="150" >
						<h1> <c:out value="${sessionScope.user.getUsername()}"></c:out></p></h1>
							<p> <c:out value="${sessionScope.user.getDescription()}"></c:out></p>
							<a href="#contact" class="scroll top"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></a>
				</div>
			</div>
		<!--end header-section-->
		   <div class="containerCenter">
		    <div class="collaps
            e navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li style="font-size:20px">
                        <a href="#">Japes</a>
                    </li>
                    <li style="font-size:20px">
                        <a href="#">Liked</a>
                    </li>
                    <li style="font-size:20px">
                        <a href="#">Commented</a>
                    </li>
                    </ul>
                    </div>
     </div>
		
		
		
		
		
		
			
					<!--start-social-section-->
				<div class="social-icons">
				<h3>My Japes</h3>
			<div class="container">
						
						<c:forEach var="gag" items="${sessionScope.userGags }">
							<div class="container">
							<h2><c:out value="${gag.getTitle()}"></c:out></h2>

								<img src="profileJapes/${gag.getGagID()}"  width="650"/><br><br>

								
								<hr>
								<br>
								</div>
						</c:forEach>
						
						
							</div>
							<!--end-social-section-->
							<!--start-contact-section-->
						<div class="contact-section" id="contact">
				<div class="container">
					<h3>contact us</h3>
					 <div class="contact-details">
			 <form>
				 <div class="col-md-6 contact-left">
					 <input type="text" class="text" value="Name *" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Name *';}">
					 <input type="text" class="text" value="Email *" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email *';}">
					 <input type="text" class="text" value="phone *" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Phone *';}">
				 </div>
				 <div class="col-md-6 contact-right">
					 <textarea  value="Message" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Message';}">Message *</textarea>
					 <input type="submit" value="Send Message"/>
				 </div>
				 <div class="clearfix"> </div>
			 </form>
			</div>		 
		</div>
	</div>
	<!--end-contact-section-->
				<!--start-map-section-->
				<div class="google-map">
				<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d114829.39166857985!2d-80.19154352520549!3d25.92148032545394!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x88d9b2eec0a4b145%3A0x6fb7ea318103f481!2sCollins+Ave%2C+Sunny+Isles+Beach%2C+FL+33160%2C+USA!5e0!3m2!1sen!2sin!4v1436081255308"></iframe>
			</div>
			<!--end-map-section-->
			<!--start-footer-section-->
			<div class="footer-section">
						<div class="container">
							<div class="footer-top">
						<p>&copy; 2015 <span>Hello.</span> All rights reserved | Design by <a href="http://w3layouts.com">W3layouts</a></p>
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