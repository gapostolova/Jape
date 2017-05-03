<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="errorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	}
</script>
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

	<!--start-study-section-->
	<div class="study-section">
		<div class="container">
			<div class="study-grids">
				<div class="col-md-4 study-grid">
					
					<div class="collaps
            e navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li style="font-size: 20px"><a  href="account">Account</a></li>
							<br>
							<li style="font-size: 20px"><a href="password">Password</a></li>
							<br>
							<li style="font-size: 20px"><a class="focus" href="settings">Profile</a></li>
						</ul>
					</div>


				</div>
				
				
				
				
				
				
				
				
				<div class="col-md-6 study-grid">
				
					<form class="form" style=" background-color:transparent; box-shadow:none ;color:black" action="settings" method="post" enctype="multipart/form-data">
									<h2><b>Profile</b></h2><br>
									<c:if test ="${sessionScope.settingsChangedMessage!=null}">
								<h1 style="color:black"><c:out value="${settingsChangedMessage}"></c:out></h1>
								<c:set var="settingsChangedMessage" value="" scope="session"></c:set>  
								</c:if>
								
								<img class="img-rounded" src="/Jape/profilePic/${sessionScope.user.getProfilePic() }" height="100" width="100" /><br><br>
								<input class="btn btn-default"  style="color:black" type="file" id="file" name="failche" accept="image/*" > <br><br>
										<h5 style="color:black">Username</h5>
										<div class="field-wrap">
											
											 <input style="color:black" type="text" required autocomplete="off"
												name="username" value="${sessionScope.user.getUsername() }" />
												<h6 style="color:grey">This is the name that will be visible to other users on Jape.</h6>
												
										</div>
										
									<!--  	<h4>Email</h4>
										<div class="field-wrap">
											
											 <input type="text" required autocomplete="off"
												name="email" value="${sessionScope.user.getEmail() }" />
										
										-->
										
										<h5 style="color:black">Tell people who you are</h5>
										<div class="field-wrap">
											
											 <input style="color:black; height:90px;" type="text" required autocomplete="off"
												name="description" value="${sessionScope.user.getDescription() }" />
										</div>
										
										
										<h5 style="color:black">Enter password to save changes</h5>
										<div class="field-wrap">
											<label>    &nbsp   <span
												class="req">*</span>
											</label> <input style="color:black" type="password" required autocomplete="off"
												name="password" />
										</div>

										<button type="submit" class="btn btn-defaultk" >
										Save Changes
										</button>

									</form>

					

					<!-- tab-content -->

				</div>
				<!-- /form -->

			</div>


			<div class="clearfix"></div>
		</div>
	</div>
	</div>
	<!--end study-section-->
	<!--start-services-section-->
	<div class="service-section" id="service">
		<div class="container">

			
		</div>
	</div>
	<!--end services-section-->
	<!--start-social-section-->
	<div class="social-icons">
		
		
	</div>
	<!--end-social-section-->
	<!--start-contact-section-->
	<div class="contact-section" id="contact">
		<div class="container">
			
			<div class="contact-details">
				
			</div>
		</div>
	</div>
	<!--end-contact-section-->
	<!--start-map-section-->
	
	<!--end-map-section-->
	<!--start-footer-section-->
	<div class="footer-section">
		<div class="container">
			<div class="footer-top">
				<p>
					&copy; 2017 <span>Hello.</span> All rights reserved 
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
