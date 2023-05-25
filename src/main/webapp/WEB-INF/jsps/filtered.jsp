<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/style.css">
<title>Filtered stories</title>
</head>
<body>
	<div class="wrapper">
		<header class="header">
			<%@ include file="header.jsp"%>
		</header>

		<main>
			<section class="search-bar-field">
				<div class="search-bar">
					<p class="det__title">Filter stories</p>

					<div class="detailed_searchbar">
						<form action="/dropDownFilters" method="post">
							<div class="filters">
								<input type="hidden" name="filter" value="${filter}">
								<div class="location" style="display: inline-block;">
									<div class="filter-a">Location</div>
									<select name="location" style="width: 150px;">
										<option value="${filtering.location }" selected="selected">${filtering.location }</option>
										<option value=""></option>
										<option value="North America">North America</option>
										<option value="South America">South America</option>
										<option value="Africa">Africa</option>
										<option value="Europe">Europe</option>
										<option value="Oceania">Oceania</option>
										<option value="Asia">Asia</option>
										<option value="Middle East">Middle East</option>
										<option value="Arctic">Arctic</option>
									</select>
								</div>
								<div class="category" style="display: inline-block;">
									<div class="filter-a">Category</div>
									<select name="category" style="width: 150px;">
										<option value="${filtering.category}" selected="selected">${filtering.category}</option>
										<option value=""></option>
										<option value="Adventure">Adventure</option>
										<option value="Advices">Advices</option>
										<option value="Budget Travel">Budget Travel</option>
										<option value="Culture">Culture</option>
										<option value="Family Travel">Family Travel</option>
										<option value="Food">Food</option>
										<option value="Group Travel">Group Travel</option>
										<option value="Hitchhiking">Hitchhiking</option>
										<option value="Luxury Travel">Luxury Travel</option>
										<option value="Nightlife">Nightlife</option>
										<option value="Solo Travel">Solo Travel</option>
									</select>
								</div>
								<input class="button" type="submit" value="Search"
									style="margin-left: 10px;">
							</div>
						</form>
					</div>
					</div>
			</section>
	</div>
	<section class="page-section articles">
		<div class="articles-containter">
			<br>
			<h1 class="filtered-title">Filtered results</h1>
			<br>
			<div class="stories-display">
				<c:forEach items="${filteredStories}" var="story">
					<div id="col${status.index % 3 + 1}">
						<a class="story-picture" href="/story/${story.id}"><img
							src="${story.images[0]}" alt=""></a>
						<h4>Category: ${story.category}</h4>
						<h3>Location: ${story.location}</h3>
						<h5>
							Date:
							<fmt:formatDate value="${story.date}" pattern="dd/MM/yyyy" />
						</h5>
						<h2>
							<a href="/story/${story.id}">${story.title}</a>
						</h2>
						<h6>Rating: ${story.rating}</h6>
						<h6>${story.likes}likes</h6>
					</div>
				</c:forEach>

			</div>
		</div>
	</section>
	</main>

	<footer class="footer">
		<%@ include file="footer.jsp"%>
	</footer>
	</div>
</body>
</html>