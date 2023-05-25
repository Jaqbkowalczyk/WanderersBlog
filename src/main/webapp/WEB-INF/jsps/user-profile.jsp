<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User profile</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/style-rating.css">
<link href="${jstlCss}" rel="stylesheet">
</head>
<body>
	<div class="wrapper">


		<%@ include file="header.jsp"%>
		<div class="profile-page">
			<div style="text-align: center;">
				<br>
				<h2 style="color:black">Profile of ${viewUser.username}</h2><br>
				<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100"
					fill="white" class="bi bi-person-circle" viewBox="0 0 16 16">
	  		<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
	  		<path fill-rule="evenodd"
						d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
			</svg>
			</div>

			<div class="user-profile">

				<div class="details">
					<h2>Details about ${viewUser.username}</h2>
					<br> <b>Username:</b> ${viewUser.username}<br> <br>
					<b>First Name:</b> ${viewUser.firstName}<br> <br> <b>Last
						Name:</b> ${viewUser.surName}<br> <br> <b>Email:</b>${viewUser.email}<br>
					<br>
					<br> <b>Rating:</b> ${userRating} <br>
					<br>

					<c:set var="starCount" value="${Math.round(userRating)}" />
					<c:choose>
						<c:when test="${userRating >= 0.5}">
							<c:forEach var="i" begin="1" end="${starCount}">
								<i class="fa fa-star" style="color: #FDCC0D; font-size: 1.5em;"></i>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<i class="fa fa-star-half"
								style="color: #FDCC0D; font-size: 1.5em;"></i>
						</c:otherwise>
					</c:choose>
					<br>
					<br>
				</div>



				<div class="details">
					<c:choose>
						<c:when
							test="${loggedIn == true && user.username.equals(viewUser.username)}">
							<br>
							<br>
							<a class="button" href="/editUserDetails">Edit details</a>
							<br>
							<br>
							<br>
							<br>
							<a class="button" href="/create-story">Create story</a>
							<br>
							<br>
							<br>
							<br>
							<a class="button" href="/create-offer">Create offer</a>
						</c:when>

						<c:otherwise>
							<h3>Send message to ${viewUser.username}</h3>
							<!-- code to send a message -->

							<form id="rating-form" action="/rate/${viewUser.username}"
								method="post" style="">
								<h3>Add your opinion about ${viewUser.username}</h3>
								<span class="rating"
									style="display: flex; flex-direction: row-reverse;"> <input
									class="rating" type="radio" name="value" value="5"
									id="rating-5" /> <label class="rating" for="rating-5"
									aria-label="Five"><i class="fa fa-star"></i></label> <input
									class="rating" type="radio" name="value" value="4"
									id="rating-4" /> <label class="rating" for="rating-4"
									aria-label="Four"><i class="fa fa-star"></i></label> <input
									class="rating" type="radio" name="value" value="3"
									id="rating-3" /> <label class="rating" for="rating-3"
									aria-label="Three"><i class="fa fa-star"></i></label> <input
									class="rating" type="radio" name="value" value="2"
									id="rating-2" /> <label class="rating" for="rating-2"
									aria-label="Two"><i class="fa fa-star"></i></label> <input
									class="rating" type="radio" name="value" value="1"
									id="rating-1" /> <label class="rating" for="rating-1"
									aria-label="One"><i class="fa fa-star"></i></label>
								</span> <br> <input type="text" name="comment"
									placeholder="Share details about your experience with ${viewUser.username}">
								<input class="button" type="submit" name="btn"
									value="Submit Rating">
							</form>
							<br>
							<br>


						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="posts">
				<h2>Stories of ${viewUser.username}:</h2>
				<c:forEach items="${stories}" var="story">
					<div class="post-container">
						<div>
							<a class="story-picture" href="/story/${story.id}"><img
								src="${story.images[0]}" alt=""></a>
						</div>
						<div>
							<h2>
								<a href="/story/${story.id}">${story.title}</a>
							</h2>

							<h4>Category: ${story.category}</h4>
							<h3>Location: ${story.location}</h3>
							<p>
								Date:
								<fmt:formatDate value="${story.date}" pattern="dd/MM/yyyy" />
							</p>
							<p>${story.likes}likes</p>
						</div>
						<div>
							<p>${story.description}</p>
						</div>
					</div>
				</c:forEach>

			</div>

			<div class="posts">
				<h2>Offers of ${viewUser.username}:</h2>
				<c:forEach items="${offers}" var="offer">
					<div class="post-container">
						<div>
							<a class="story-picture" href="/offer/${offer.id}"><img
								src="${offer.images[0]}" alt=""></a>
						</div>
						<div>
							<h2>
								<a href="/story/${offer.id}">${offer.title}</a>
							</h2>
							<h3>Location: ${offer.location}</h3>
							<p>
								Date:
								<fmt:formatDate value="${offer.startDate}" pattern="dd/MM/yyyy" />
								-
								<fmt:formatDate value="${offer.endDate}" pattern="dd/MM/yyyy" />
							</p>
							<p>Number of people: ${offer.howManyLookingFor}</p>
							<p>published: ${offer.date}</p>
						</div>
						<div>
							<p>${offer.description}</p>
						</div>
					</div>
				</c:forEach>

			</div>


			<div class="user-profile">
				<div>
					<h2>Opinions about ${viewUser.username}:</h2>
					<c:forEach items="${ratings}" var="rating">
						<div class="opinions">
							<div class="opinion">
								<c:forEach var="i" begin="1" end="${rating.value}" step="1">
									<i class="fa fa-star" style="color: #FDCC0D; font-size: 1.5em;"></i>
								</c:forEach>
							</div>
							<div class="opinion">
								<p>By: ${rating.rater.username }</p>

								<p>Details: ${rating.comment }</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

		</div>
		<footer class="footer">
			<%@ include file="footer.jsp"%>
		</footer>

	</div>
</body>
</html>