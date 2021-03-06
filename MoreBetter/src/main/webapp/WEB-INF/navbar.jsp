<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a href="home.do"><img src="style/images/morebetterlandscape.png"
					alt="MoreBetter Logo" class="img-thumbnail"></a>
			<!-- <a class="navbar-brand" href="#">Logo</a> -->
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<!-- <li></li> -->
				<!-- <li class="active"><a href="home.do">Home</a></li> -->
				<li><a href="#">About</a></li>
				<c:choose>
				<c:when test="${sessionScope.loggedInUser.role == 'MENTOR'}">
				<li><a href="userStatus.do">Manage Users</a></li>
				</c:when>
				</c:choose>
				<li><a href="#">Contact</a></li>
			</ul>

			<c:choose>
				<c:when test="${sessionScope.loggedInUser == null || sessionscope.loggedInUser.enabled==false}">

					<ul class="nav navbar-nav navbar-right">
						<li><a href="userCreateProfile.do"><span
								class="glyphicon glyphicon-log-in"></span> Register</a></li>
						<li><a href="userLogin.do"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</ul>
				</c:when>


				<c:otherwise>
					<ul class="nav navbar-nav navbar-right">

						<li><a href="userProfile.do"><span
								class="glyphicon glyphicon-log-in"></span> Profile</a></li>
						<li><a href="userLogout.do"><span
								class="glyphicon glyphicon-log-in"></span> Log Out</a></li>
						<li><a href="userEvents.do"><span
								class="glyphicon glyphicon-log-in"></span> Events</a></li>
					</ul>
				</c:otherwise>


			</c:choose>

		</div>
	</div>
</nav>