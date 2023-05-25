<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find company</title>
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
				<h2>Find company for your trip!</h2>
				<br>
				<c:if test="${loggedIn}">
					<div class= "button">
					<form action="create-offer" method="GET">
						<input  class="companions-button" type="submit" value="Look for companions">
					</form>
					</div>
				</c:if>
				<div class="search-bar">
					<p class="det__title">Search a story!</p>
					<div>
						<form action="/filteredOffer" method="post" class="search-form">
							<input class="searchbar" type="search" name="filter"
								placeholder="Type here..."> <input class="button"
								type="submit" value="Search">
						</form>
					</div>


					<div class="detailed_searchbar">

						<form action="/dropDownFiltersOffer" method="post">

							<div class="filters">
								<input type="hidden" name="filter" value="${filter}">
								<div class="location">
									<div class="filter-a">Location</div>
									<select name="location">
										<option value="${filtering.location }" selected="selected">${filtering.location }</option>
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
								<div class="date-range">
									<div class="a">Date Range</div>
									<input type="date" name="startDate"> 
									<input type="date" name="endDate">
								</div>
								<div class="filter-a">How many people are you looking for?</div>
								<div class="how-many-looking-for">
									<input type="number" name="howManyLookingFor" min="1" max="10" value="1">
								</div>
								<input class="button" type="submit" value="Filter" class="filter-input">
							</div>
						</form>
					</div>
				</div>
				<div class="stories-display">
				<c:forEach items="${resultsOfSearch}" var="offer">
					<div id="col${status.index % 3 + 1}">
						<a class="story-picture" href="/offer/${offer.id}">
						<img src="${offer.images[0]}" alt=""></a>
						<h3>Location: ${offer.location}</h3>
						<h5>Date posted: <fmt:formatDate value="${offer.date}" pattern="dd/MM/yyyy" /></h5>
						<h2>
							<a href="/offer/${offer.id}">${offer.title}</a>
						</h2>
						<h3>Looking for: ${offer.howManyLookingFor}</h3>
						<h6>Adventure Starts: <fmt:formatDate value="${offer.startDate}" pattern="dd/MM/yyyy" /></h6>
						<h6>Adventure Ends: <fmt:formatDate value="${offer.endDate}" pattern="dd/MM/yyyy" /></h6>
					</div>
				</c:forEach>
				</div>
			</div>
		</section>


		<footer class="footer">
			<%@ include file="footer.jsp"%>
		</footer>

	</div>
</body>
</html>