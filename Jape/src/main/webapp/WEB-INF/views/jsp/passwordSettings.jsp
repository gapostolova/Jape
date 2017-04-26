<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page errorPage="errorPage.jsp" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Settings</title>

<link href="css/bootstrap1.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/bootstrapProfileSettings.min.css" rel="stylesheet"
	type="text/css" media="all" />


<!-- Settings css -->

<link
	href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/normalize.min.css">


<link rel="stylesheet" href="css/style.css">
<!-- end of settings css -->




<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="My Skills Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<script src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
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


<!-- SHOW  DIFF CONTENT -->
<script type="text/javascript">
	function show(elementId) {
		document.getElementById("id1").style.display = "none";
		document.getElementById("id2").style.display = "none";
		document.getElementById("id3").style.display = "none";
		document.getElementById(elementId).style.display = "block";
		//onclick="show('id2');"
	}
	
</script>


</head>
<body>
	<jsp:include page="navigationBar.jsp" />

	<!--start-study-section-->
	<div class="study-section">
		<div class="container">
			<div class="study-grids">
				<div class="col-md-4 study-grid">
					<h3>
						study..<span>!</span>
					</h3>
					<div class="collaps
            e navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li style="font-size: 20px"><a href="account"
								>Account</a></li>
							<br>
							<li  style="font-size: 20px"><a class="focus" href="password"
								>Password</a></li>
							<br>
							<li style="font-size: 20px"><a href="settings"
								>Profile</a></li>
						</ul>
					</div>


				</div>
				<div class="col-md-6 study-grid">


					
					<!--  *********************************************** FORM ************************************* -->



					
						<div class="form">
							<div id="profilesettings">
								<h1></h1>

								<!-- imeto na servlet-a -->

								<form action="password" method="post">
								  <c:if test ="${sessionScope.passwordChangeMessage!=null}">
								<h1><c:out value="${sessionScope.passwordChangeMessage}"></c:out></h1>
								<c:set var="passwordChangeMessage" value="" scope="session"></c:set>   
								</c:if>
									<div class="field-wrap">
										<label><h5>Old Password *</h5>
										</label> <input type="password" required autocomplete="off"
											name="oldPassword" />
									</div>

									<div class="field-wrap">
										<label> <h5>New password *</h5>
										</label> <input type="password" required autocomplete="off"
											name="password" />
									</div>

									<div class="field-wrap">
										<label> <h5>Confirm password *</h5></span>
										</label> <input type="password" required autocomplete="off"
											name="passConfirm" />
									</div>

									<!--    <p class="forgot"><a href="#">Forgot Password?</a></p> -->


									<button type="submit"  class="button button-block">
									Save Changes
									</button>

								</form>

							</div>

						</div>
						<!-- /form  CHANGE PASS-->
					

					<!--  *********************************************** FORM CHANGE PASS************************************* -->
					
			<!-- end col-md-6 study-grid -->
				</div>
				
				
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--end study-section-->
	<!--start-services-section-->
	<div class="service-section" id="service">
		<div class="container">

			<div class="service-grids">
				<div class="col-md-4 service-grid">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					<h4>THE BEST DESIGN</h4>
					<span> </span>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
						do eiusmod Lorem Ipsum passages containing of Letraset sheets</p>
				</div>
				<div class="col-md-4 service-grid">
					<span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
					<h4>THE BEST SUPPORT</h4>
					<span> </span>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
						do eiusmod Lorem Ipsum passages containing of Letraset sheets</p>
				</div>
				<div class="col-md-4 service-grid">
					<span class="glyphicon glyphicon-signal" aria-hidden="true"></span>
					<h4>THE BEST SOLUTIONS</h4>
					<span> </span>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
						do eiusmod Lorem Ipsum passages containing of Letraset sheets</p>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--end services-section-->
	<!--start-social-section-->
	<div class="social-icons">
		<h3>Social Network</h3>
		<div class="container">
			<div class="col-md-3 face">
				<p>
					<i class="facebook"> </i> 733k
				</p>
				<h4>facebook likes</h4>
			</div>
			<div class="col-md-3 face">
				<p>
					<i class="twitter"> </i> 620k
				</p>
				<h4>twitter followers</h4>
			</div>
			<div class="col-md-3 face">
				<p>
					<i class="google"> </i> 412k
				</p>
				<h4>google+ followers</h4>
			</div>
			<div class="col-md-3 face">
				<p>
					<i class="beh"> </i> 322k
				</p>
				<h4>behance followers</h4>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!--end-social-section-->
	<!--start-contact-section-->
	<div class="contact-section" id="contact">
		<div class="container">
			<h3>contact us</h3>
			<div class="contact-details">
				<form>
					<div class="col-md-6 contact-left">
						<input type="text" class="text" value="Name *"
							onfocus="this.value = '';"
							onblur="if (this.value == '') {this.value = 'Name *';}">
						<input type="text" class="text" value="Email *"
							onfocus="this.value = '';"
							onblur="if (this.value == '') {this.value = 'Email *';}">
						<input type="text" class="text" value="phone *"
							onfocus="this.value = '';"
							onblur="if (this.value == '') {this.value = 'Phone *';}">
					</div>
					<div class="col-md-6 contact-right">
						<textarea value="Message" onfocus="this.value = '';"
							onblur="if (this.value == '') {this.value = 'Message';}">Message *</textarea>
						<input type="submit" value="Send Message" />
					</div>
					<div class="clearfix"></div>
				</form>
			</div>
		</div>
	</div>
	<!--end-contact-section-->
	<!--start-map-section-->
	<div class="google-map">
		<iframe
			src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d114829.39166857985!2d-80.19154352520549!3d25.92148032545394!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x88d9b2eec0a4b145%3A0x6fb7ea318103f481!2sCollins+Ave%2C+Sunny+Isles+Beach%2C+FL+33160%2C+USA!5e0!3m2!1sen!2sin!4v1436081255308"></iframe>
	</div>
	<!--end-map-section-->
	<!--start-footer-section-->
	<div class="footer-section">
		<div class="container">
			<div class="footer-top">
				<p>
					&copy; 2015 <span>Hello.</span> All rights reserved | Design by <a
						href="http://w3layouts.com">W3layouts</a>
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
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="js/index.js"></script>

</body>
</html>
