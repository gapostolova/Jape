<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page errorPage="errorPage.jsp" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 
 <!--  We don't need this jsp.. maybe remove later -->

<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Sign-Up/Login Form</title>
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  
      <link rel="stylesheet" href="/Jape/css/style.css">

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

<div id="gags" class="container">
    <div class="col-md-8">
  <div class="form">
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="signup">   
        <c:if test ="${sessionScope.registerResult!=null}">
<h1> <c:out value="${sessionScope.registerResult}"></c:out></h1>
</c:if>
<c:set var="registerResult" value=" " scope="session"></c:set>
          <h1>Sign up for free!</h1>
          
          
          <form action="register" method="post" >
          
          
            <div class="field-wrap">
              <label>
                Username<span class="req">*</span>
              </label>
              <input type="text" required autocomplete="off"  name="username"/>
           </div>
        
        
          <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
            </label>
            <input type="email"required autocomplete="off" name="email"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Set A Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="password"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Confirm password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="passConfirm"/>
          </div>
          
          <button type="submit" class="button button-block"/>Get Started</button>
          
          </form>

        </div>
        
        <div id="login">   
          <h1>Welcome Back!</h1>
          
          <form action="login" method="post">
          
            <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
            </label>
            <input type="email"required autocomplete="off"  name="email"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="pass"/>
          </div>
          
          
          
         
          
          
        <!--    <p class="forgot"><a href="#">Forgot Password?</a></p> -->
          
          <button class="button button-block"/>Log In</button>
          
          </form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
</div>


<div class="container">
               <div class="row">
            <!-- Blog Sidebar Widgets Column -->
            <div class="col-md-4">

                <!-- Side Widget Well -->
                <div class="well">
                <h3 style="color:grey;"> Some random japes</h3>
                    <a href="/Jape/jape/1"><img src="/Jape/image/1" width="300"/></a> <br> <br>
                     <a href="/Jape/jape/2"><img src="/Jape/image/2" width="300"/></a> <br> <br>
                     <a href="/Jape/jape/3"><img src="/Jape/image/3" width="300"/></a> <br> <br>
                    
                </div>

            </div>

        </div>
        <!-- /.row -->
        </div>


  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="/Jape/js/index.js"></script>

</body>
</html>