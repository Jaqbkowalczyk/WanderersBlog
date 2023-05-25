<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Posts of category</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/style.css">
</head>
<body>

	<div class="wrapper">

		<header class="header">
			<%@ include file="header.jsp"%>
		</header>
<section class="page-section articles">
		<div class="articles-containter">
			<h2>${category}</h2>

			<div class="stories-display">
				<c:forEach items="${stories}" var="story">
					<div id="col${status.index % 3 + 1}">
						<a class="story-picture" href="/story/${story.id}"><img
							src="${story.images[0]}" alt=""></a>
						<h4>Category: ${story.category}</h4>
						<h3>Location: ${story.location}</h3>
						<h5>Date: <fmt:formatDate value="${story.date}" pattern="dd/MM/yyyy" /></h5>
						<h2>
							<a href="/story/${story.id}">${story.title}</a>
						</h2>
						<h6>${story.likes} likes</h6>
					</div>
				</c:forEach>
			</div>
		</div>

		<footer class="footer">
			<%@ include file="footer.jsp"%>
		</footer>
</section>
	</div>
</body>
</html>