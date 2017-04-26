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


<!-- Settings css -->

<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  
      <link rel="stylesheet" href="css/style.css">
      <!-- end of settings css -->

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
					<img  class="img-rounded" src="profilePic/${sessionScope.user.getProfilePic() }" height="150" >
						<h1> <c:out value="${sessionScope.user.getUsername()}"></c:out></p></h1>
							<p> <c:out value="${sessionScope.user.getDescription()}"></c:out></p>
							<a href="#contact" class="scroll top"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></a>
				</div>
			</div>
		<!--end header-section-->
	
		
		
		
		<div class="service-section" id="service">
				<div class="container">

		<div class="service-grids">
							<div class="col-md-4 service-grid">
								 <div class="collaps
            e navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li style="font-size:20px">
                        <a href="#">Account</a>
                    </li><br>
                    <li style="font-size:20px">
                        <a href="#">Password</a>
                    </li><br>
                    <li style="font-size:20px">
                        <a href="#">Profile</a>
                    </li>
                    </ul>
                    </div>
							<div class="clearfix"> </div>
						</div>
					</div>
				</div>
					<!--end services-section-->
		
		
		<div class="col-md-6 study-grid">
						<h3>skills..<span>!</span></h3>
						<div class="study2">
						
  <div class="form">
       <a style="float:right" href="profile.jsp">Back to Profile</a> <br><br>
      <ul class="tab-group">
        <li class="tab active"><a href="#profilesettings">Profile Settings</a></li>
        <li class="tab"><a href="#changepass">Change Password</a></li>
       
      </ul>
      
      <div class="tab-content">
        <div id="profilesettings">   
          <h1></h1>
          
          <!-- imeto na servlet-a -->
          <form action="setProfile" method="post" >
          <h1> <%out.print(session.getAttribute("settingsChange")); %> </h1>
          <div class="field-wrap">
              <label>
                First Name<span class="req"></span>
              </label>
              <input type="text"  autocomplete="off"  name="firstName"/>
            </div>
        
            <div class="field-wrap">
              <label>
                Last Name<span class="req"></span>
              </label>
              <input type="text" autocomplete="off" name="lastName"/>
            </div>
            
             <div class="field-wrap">
              <label>
                Mini biography<span class="req"></span>
              </label>
              <input type="text" autocomplete="off" name="miniBio"/>
            </div>
            
            
            <div class="field-wrap">
            
          <select  name="yearOfBirth" required="required" >
          <option value="" selected="selected">Year of birth</option>
          <option value="1999">1999</option><option value="1998">1998</option>
          <option value="1918">1918</option>
          <option value="1917">1917</option></select>
          </div>

          
          <div class="field-wrap">
            <label>
              Enter password to save changes<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="password"/>
          </div>
          
           <a  href="car.jsp">Car Settings</a> <br><br>
          
          <button type="submit" class="button button-block"/>Save Changes</button>
          
          </form>

        </div>
        
        <div id="changepass">   
          <h1></h1>
          
          <form action="changePass" method="post">
          <h1> <%out.print(session.getAttribute("passChange")); %> </h1>
            <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="oldPassword"/>
          </div>
          
          <div class="field-wrap">
            <label>
             New Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="password"/>
          </div>
          
          <div class="field-wrap">
            <label>
             Confirm new Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="passConfirm"/>
          </div>
               
       <!--    <p class="forgot"><a href="#">Forgot Password?</a></p> -->
          
          
          <button class="button button-block"/>Save Changes</button>
          
          </form>

        </div>
        
    
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

<!--end of col-md-6 study-grid -->
</div>

<!--  end of study2 -->
</div>

<!-- end of service section -->
</div>


		
		
			
					
				
			<!--start-footer-section-->
			<div class="footer-section">
						<div class="container">
							<div class="footer-top">
						<p>&copy; 2017 <span>Hello.</span> All rights reserved | Design by Gabrislava</a></p>
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