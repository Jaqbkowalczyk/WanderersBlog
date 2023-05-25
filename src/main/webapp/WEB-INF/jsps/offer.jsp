<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/story.css">
<title>Wanderers Blog: ${offer.title}</title>
</head>
<body>
	<header class="header">
		<%@ include file="header.jsp"%>
	</header>
	<div class="in-center story-page">
		<div class="post-content">
			<h3>${offer.location}</h3>
			<h1>${offer.title}</h1>
			<img src="${offer.images[0]}" alt="Blog Main Image">

			<div class="description">
				<p>${offer.description}</p>
			</div>
			<div class="dates">
				<h4>
					Leaving on:
					<fmt:formatDate value="${offer.startDate}" pattern="dd/MM/yyyy" />
				</h4>
				<h4>
					Coming back:
					<fmt:formatDate value="${offer.endDate}" pattern="dd/MM/yyyy" />
				</h4>
				<br>
				<h4>Looking for ${offer.howManyLookingFor} people.</h4>

			</div>

			<h2>Image Gallery</h2>
			<c:forEach items="${offer.images}" var="image" varStatus="loop">
				<c:if test="${loop.index > 0}">
					<img src="${image}" alt="Offer Image">
				</c:if>
			</c:forEach>
			<div class="author">
				<h4>
					Written by: <a href="/userProfile/${user.username}">${user.firstName}
						${user.surName} </a>
				</h4>
			</div>
			<div class="date">
				<h5>
					Posted:
					<fmt:formatDate value="${offer.date}" pattern="dd/MM/yyyy" />
				</h5><br>
			</div>
		</div>


		<c:if test="${loggedIn&&user_id!=user.id}">
			<div class="special-mes">${message}</div>
			<form action="/response-to-offer/${offer.id}" method="POST">
				<input class="like-button" type="submit" value="Join this trip!">
			</form>
			<br>
			<div class="join-trip-section">
				<%@ include file="send.jsp"%>
			</div>
		</c:if>


	</div>
	<footer class="footer">
		<%@ include file="footer.jsp"%>
	</footer>
</body>
</html>




