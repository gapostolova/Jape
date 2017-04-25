<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    

    <title>Jape</title>

  
    <!-- Pop Up Login 
  
    <link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css"> -->
    
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/blog-home.css" rel="stylesheet">
    
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>


 <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index">Jape</a>
                
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collaps
            e navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="index">Hot</a>
                    </li>
                    <li>
                        <a href="trending">Trending</a>
                    </li>
                    <li>
                        <a href="fresh">Fresh</a>
                    </li>
                    <li>
                    </li>
                 
                    <c:if test ="${sessionScope.logged!=null }">
						<c:if test ="${sessionScope.logged == 'true' }">
						<li>
						<div class="dropdownP">
							<a href = "profile"><img src="profilePic/${sessionScope.user.getProfilePic() }" height="48" width="48"></a>
									<div class="dropdownP-content">	
										<a href="profile">Profile</a><br><br>	
									    <a href="#">Settings</a><br><br>
									    <a href="logout">LogOut</a>
 									 </div>
						</c:if>
						
						<c:if test ="${sessionScope.logged == 'false' }">
							 <li >
	                        	<a href="login" >Log in</a>
	                   		 </li>
	                    	 <li >
	                        	<a   "href="register" class="btn btn-primary" >Sign up</a>
	                   		 </li>
						</c:if>
					</c:if>
					<c:if test ="${sessionScope.logged==null }">
					
                      <li >
                        <a href="login" >Log in</a>
                    </li>
                     <li >
                     
                        <a href="register" class="btn btn-primary" >Sign up</a>
                    </li>
                    </c:if>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

</body>
</html>