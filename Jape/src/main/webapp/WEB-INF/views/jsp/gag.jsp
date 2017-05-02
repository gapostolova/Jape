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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  
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
        <page errorPage="errorPage.jsp" %>
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
			
					<c:if test="${sessionScope.logged }">
				
				<c:choose>
				    <c:when test="${!sessionScope.user.isVoted(currentOpenGag.getGagID()) }">
				    	<a id="upvote" href="/Jape/upvote?gagId=${currentOpenGag.getGagID()}"><img src="/Jape/commentPic/like/PNG" width="50"></a>
						<a id="downvote" href="/Jape/downvote?gagId=${currentOpenGag.getGagID()}"><img src="/Jape/commentPic/downvote/PNG" width="50"></a>
				    </c:when>
				    <c:when test="${sessionScope.user.isVoted(currentOpenGag.getGagID())}">
				    	<c:if test="${sessionScope.user.isLiked(currentOpenGag.getGagID())}">
				        <a id="upvote" href="/Jape/upvote?gagId=${currentOpenGag.getGagID()}"><img src="/Jape/commentPic/likeBlue/PNG" width="50"></a>
						<a id="downvote" href="/Jape/downvote?gagId=${currentOpenGag.getGagID()}"><img src="/Jape/commentPic/downvote/PNG" width="50"></a>
						</c:if>
						
						<c:if test="${!sessionScope.user.isLiked(currentOpenGag.getGagID())}">
				        <a id="upvote" href="/Jape/upvote?gagId=${currentOpenGag.getGagID()}"><img src="/Jape/commentPic/like/PNG" width="50"></a>
						<a id="downvote" href="/Jape/downvote?gagId=${currentOpenGag.getGagID()}"><img src="/Jape/commentPic/downvoteBlue/PNG" width="50"></a>
						</c:if>
						
				    </c:when>
				    
				</c:choose>
				</c:if> <br> <br>
				
				
			</h2>
			<c:if test="${currentOpenGag.isVideo()== false }">
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp	<img src="/Jape/image/${currentOpenGag.getGagID()}" height="450">
		</c:if>
		<c:if test="${currentOpenGag.isVideo()== true }">
		<iframe width="560" height="315" src="${currentOpenGag.getGag()}" frameborder="0" allowfullscreen></iframe>
		</c:if>
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
<h4>
					<c:if test="${commentError!=null }">
						<c:out value="${sessionScope.commentError }"></c:out><br>
						<c:set var="commentError" scope="session" value=""/>
					</c:if>
					Comment</h4>
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
 

<div id="myModal" class="modal"  >

  <!-- Modal content -->
  <div class="modal-content" style="background-color:transparent;box-shadow:none; border:none;">
    <span class="close">&times;</span>
    
   <div class="form">
      
      
      
        
        
        <div id="login">
        <c:if test ="${problem!=null}">
			<h1> <c:out value="${problem}"></c:out></h1>
			
	</c:if>
	     

          <form action="/Jape/uploadVideo" method="post">
          
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




</body>

</html>
