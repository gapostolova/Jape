<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page errorPage="errorPage.jsp" %>
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
    <link href="/Jape/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/Jape/css/blog-home.css" rel="stylesheet">
    
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    
    <script type="text/javascript">
    function openNewWindow()
    {
    window.open("/Jape/video")
    }
    </script>

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
                <a class="navbar-brand" href="/Jape/index">Jape</a>
                
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collaps
            e navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/Jape/index">Hot</a>
                    </li>
                    <li>
                        <a href="/Jape/trending">Trending</a>
                    </li>
                    <li>
                        <a href="/Jape/fresh">Fresh</a>
                    </li>
                     <li>
                         <a href="/Jape/cosplay">Cosplay</a>
                     </li>
                     <li>
                         <a href="/Jape/funny">Funny</a>
                     </li>
                     <li>
                         <a href="/Jape/gif">Gif</a>
                     </li>
                     <li>
                         <a href="/Jape/savage">Savage</a>
                     </li>
                     <li>
                         <a href="javaScript:{openNewWindow();}" >Video</a>
                     </li>
                     <li>
                         <a href="/Jape/wtf">WTF?!</a>
                     </li>
                     <li>
                         <a href="/Jape/yummy">Yummy</a>
                      </li>
                 
                    <c:if test ="${sessionScope.logged!=null }">
						<c:if test ="${sessionScope.logged == 'true' }">
						<li>
						<div class="dropdownP">
							<a href = "/Jape/profile"><img class="img-rounded" src="/Jape/profilePic/${sessionScope.user.getProfilePic() }" height="40" width="40" ></a>
									<div class="dropdownP-content">	
										<a href="/Jape/profile">Profile</a><br><br>	
									    <a href="/Jape/account">Settings</a><br><br>
									    <a href="/Jape/logout">LogOut</a>
 									 </div>
						</div>
						
							<li>
						&nbsp&nbsp&nbsp
						</li>
						
						</li>
						<li>
						<c:if test="${onVideoPage==null}">
						<div class="nav navbar-nav">
                        <a href="/Jape/upload" class="btn btn-primary" >+ Upload</a></div>
                        </c:if>
                        <c:if test="${onVideoPage==1 }">
						<div class="nav navbar-nav">
                        <a  id="myBtn" class="btn btn-primary" >+ Upload</a></div>
                        </c:if>
                    </li>

						</c:if>
						
						<c:if test ="${sessionScope.logged == 'false' }">
							 <li >
	                        	<a href="/Jape/login" >Log in</a>
	                   		 </li>
	                    	 <li >
	                        	<a   href="/Jape/register" class="btn btn-primary" >Sign up</a>
	                   		 </li>
						</c:if>
					</c:if>
					<c:if test ="${sessionScope.logged==null }">
					
                      <li >
                        <a href="/Jape/login" >Log in</a>
                    </li>
                     <li >
                     
                        <a href="/Jape/register" class="btn btn-primary" >Sign up</a>
                    </li>
                    
                    </c:if>
                    
 						<li>
						&nbsp&nbsp&nbsp
						</li>
						<li>
                     		<form action="search" method="GET">
                     		<div class="round-text">
                     		<!-- 	<div class="input-group">  -->
                   	 				<input type="text" class="form-control" name="keyword" placeholder="Search jape..." value="" required>
                   				</div>
 							
 						 </li>
						 <li>
                        
                             &nbsp<button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>
                                 
                                 </li>
                             <!--    </div> -->  
                                 </form>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

</body>
</html>