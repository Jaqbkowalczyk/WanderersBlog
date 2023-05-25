<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/style.css">
<link href="${jstlCss}" rel="stylesheet">
<title>Wanderers Blog</title>
</head>
<body>
	<div class="wrapper">

			<%@ include file="header.jsp"%>

		<!--                Main Body                        -->
		<main>
			<section class="main-image">
				<img class="main-image" src="/img/main-img.jpg" alt="">
			</section>

			<!-- 				filters 				-->
			<section class="search-bar-field">
				<div class="search-bar">
					<p class="det__title">Search a story!</p>
					<div>
						<div class="search-field">
							<form action="/filtered" method="post" class="search-form">
								<input class="searchbar" type="search" name="filter"
									placeholder="Type here..."> <input class="button"
									type="submit" value="Search">
							</form>
						</div>
					</div>


					<div class="detailed_searchbar">

						<form action="/dropDownFilters" method="post">
							<div class="filters">
								<input type="hidden" name="filter" value="${filter}">
								<div class="location" style="display: inline-block;">
									<div class="filter-a">Location</div>
									<select name="location" style="width: 150px;">
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
								<div class="category" style="display: inline-block;">
									<div class="filter-a">Category</div>
									<select name="category" style="width: 150px;">
										<option value="${filtering.category}" selected="selected">${filtering.category}</option>
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
			<section class="page-section articles">
				<div class="articles-containter">
					<h2>MOST RECENT</h2>
					<c:forEach items="${recentStories}" var="recentStory">
						<article>
							<div class="article-div">
								<a href="/story/${recentStory.id}"><img class="art-img shadow"
									src="${recentStory.images[0]}" /></a>
									<h4>
									<a href="/Category/${recentStory.category}">${recentStory.category}</a>
								</h4>
								<h4>
									<a href="/Location/${recentStory.location}">${recentStory.location}</a>
								</h4>
								<h3>
									<a href="/story/${recentStory.id}">${recentStory.title}</a>
								</h3>
								<h5>
									<fmt:formatDate value="${recentStory.date}"
										pattern="dd/MM/yyyy" />
								</h5>
								<h5>${recentStory.likes } likes</h5>
							</div>
						</article>
					</c:forEach>
				</div>
			</section>
			<section class="videos">
				<div class="videos-containter">
					<h2>MOST POPULAR</h2>
					<c:forEach items="${mostLikedStories}" var="mostLikedStory">
						<article>
							<div class="article-div">
								<a href="/story/${mostLikedStory.id}"><img class="art-img shadow"
									src="${mostLikedStory.images[0]}" /></a>
								<h4>
									<a href="/Category/${mostLikedStory.category}">${mostLikedStory.category}</a>
								</h4>
								<h4>
									<a href="/Location/${mostLikedStory.location}">${mostLikedStory.location}</a>
								</h4>
								<h3>
									<a href="/story/${mostLikedStory.id}">${mostLikedStory.title}</a>
								</h3>
								<h5>
									<fmt:formatDate value="${mostLikedStory.date}"
										pattern="dd/MM/yyyy" />
								</h5>
								<h5>${mostLikedStory.likes } likes</h5>
							</div>
						</article>
					</c:forEach>
				</div>
			</section>
			<section class="page-section countries">
				<div class="articles-containter">
					<h2>RECENT OFFERS</h2>
					<c:forEach items="${recentOffers}" var="recentOffer">
						<article>
							<div class="article-div">
								<a href="/offer/${recentOffer.id}"><img class="art-img shadow"
									src="${recentOffer.images[0]}" /></a>
								<h4>${recentOffer.location}</h4>
								<h3>
									<a href="/offer/${recentOffer.id}">${recentOffer.title}</a>
								</h3>
								<h4>
									<fmt:formatDate value="${recentOffer.startDate}"
										pattern="dd/MM/yyyy" />

									-
									<fmt:formatDate value="${recentOffer.endDate}"
										pattern="dd/MM/yyyy" />
								</h4>
								<h4>Companions for Adventure:
									${recentOffer.howManyLookingFor}</h4>
								<h5>
									<fmt:formatDate value="${recentOffer.date}"
										pattern="dd/MM/yyyy" />
								</h5>
							</div>
						</article>
					</c:forEach>
				</div>
			</section>
		</main>


		<footer class="footer">
			<%@ include file="footer.jsp"%>
		</footer>
	</div>
</body>
</html>
