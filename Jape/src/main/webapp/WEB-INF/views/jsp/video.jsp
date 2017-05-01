<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jape</title>

<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  
      <link rel="stylesheet" href="/Jape/css/style.css">


</head>
<body style="background-color:#1F1E1E">

<!--  <iframe width="560" height="315" src="https://www.youtube.com/embed/JntTS-7uMXg" frameborder="0" allowfullscreen></iframe>
-->


<jsp:include page="navigationBar.jsp" />
<style>
/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color:none;
    margin: auto;
    padding: 20px;
   
    width: 35%;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}
</style>


 <style>
.popup {
    border: 1px solid black;
    position:absolute;
    left: 50%;
    top: 50%;
    width:100px;
    height:100px;
    background-color: green;
}
 </style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>

<script type="text/javascript">
 $(document).ready(function () {
     $('#popup').hide()
 });

 $('#open').on('click', function () {
     $('#popup').show(500)
 });

 $(document).mouseup(function (e) {
     var popup = $("#popup");
     if (!$('#open').is(e.target) && !popup.is(e.target) && popup.has(e.target).length == 0) {
         popup.hide(500);
     }
 });

 </script>
 


 <div id="gags" class="container">
<c:if test ="${problem!=null}">
			<h1 style="color:black"> <c:out value="${problem}"></c:out></h1>
			
	</c:if>
<c:if test="${sessionScope.videos == null}">
    			<c:out value="No videos to show!"></c:out>
    			</c:if>
				<c:forEach var="gag" items="${sessionScope.videos }">
				<h3><a style="color:white;" href="/Jape/jape/${gag.getGagID()}"><c:out value="${gag.getTitle() }"></c:out></a></h3> 
				<iframe width="560" height="315" src="${gag.getGag()}" frameborder="0" allowfullscreen></iframe>
				 
				<c:out value="${gag.getUpvotes() }"></c:out>
				<a href="/Jape/upvote?gagId=${gag.getGagID()}">vote</a>
				<a href="/Jape/downvote?gagId=${gag.getGagID()}">downvote</a>
				
				<hr style="color:black;border-top:2px solid #000;"> <br> <br> <br>
				</c:forEach>

</div>


<!-- The Modal -->
<div id="myModal" class="modal"  >

  <!-- Modal content -->
  <div class="modal-content" style="background-color:transparent;box-shadow:none; border:none;">
    <span class="close">&times;</span>
    
   <div class="form">
      
      
      
        
        
        <div id="login">
        <c:if test ="${problem!=null}">
			<h1> <c:out value="${problem}"></c:out></h1>
			
	</c:if>
	     

          <form action="uploadVideo" method="post">
          
           <div class="field-wrap">
            <label>
             Title<span class="req">*</span>
            </label>
            <input type="text" required autocomplete="off"  name="title"/>
          </div>
          
            <div class="field-wrap">
            <label>
             URL<span class="req">*</span>
            </label>
            <input type="text" required autocomplete="off"  name="url"/>
          </div>
          
          
          
        <!--    <p class="forgot"><a href="#">Forgot Password?</a></p> -->
          
          <button type="submit" class="btn btn-default">Upload</button>
          
          </form>

        </div>
        
        
        
      <!-- tab-content -->
      
</div> <!-- /form -->
    
    
    
  </div>

</div>

<script>
// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="/Jape/js/index.js"></script>


</body>
</html>