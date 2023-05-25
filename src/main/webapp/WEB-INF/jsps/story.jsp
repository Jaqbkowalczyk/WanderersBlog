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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/story.css">
<title>Wanderers Blog: ${story.title}</title>
</head>
<body>
	<header class="header">
		<%@ include file="header.jsp"%>
	</header>
	<div class="in-center story-page">
		<div class="post-content">
			<h4>${story.category}</h4>
			<h3>${story.location}</h3>
			<br>
			<h1>${story.title.toUpperCase()}</h1>
			<img src="${story.images[0]}" alt="Blog Main Image">
			<div class="description">
				<p>${story.description}</p>
			</div>
			<br>
			<h2>Image Gallery</h2>
			<c:forEach items="${story.images}" var="image" varStatus="loop">
				<c:if test="${loop.index > 0}">
					<img src="${image}" alt="Blog Image">
				</c:if>
			</c:forEach>

			<div class="like-subscribe-section">
				<div class="likes">
					<c:if test="${loggedIn&&!replyBox}">
						<form action="/like/${story.id}" method="post">
							<button class="like-button" type="submit">Like</button>
						</form>
					</c:if>
					<h3>${story.likes } people like it</h3>
				</div>

				<div class="author">
					<h4>
						Written by: <a href="/userProfile/${user.username}">${user.firstName}
							${user.surName} </a>

					</h4>
					<br>
					<h5>
						<fmt:formatDate value="${story.date}" pattern="dd/MM/yyyy" />
					</h5>
				</div>

				<div class="subscribe-section">
					<!-- You can only see Subscribe option if you are not a creator -->
					<c:if test="${loggedIn&& loggedUser.id!=user.id }">
						<c:if test="${empty subscription}">
							<form action="/subscribe/${story.id}" method="post">
								<input type="submit" value="Subscribe" class="subscribe-button" />
							</form>
						</c:if>
						<c:if test="${not empty subscription}">
							<form action="/unsubscribe/${story.id}" method="post">
								<input type="submit" value="Unsubscribe"
									class="subscribe-button" />
							</form>
							<form action="/notify/${story.id}/${subscription.id}"
								method="post">
								<button type="submit" class="notification-button">
									<c:if test="${subscription.notificationsOn}"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M38.8 5.1C28.4-3.1 13.3-1.2 5.1 9.2S-1.2 34.7 9.2 42.9l592 464c10.4 8.2 25.5 6.3 33.7-4.1s6.3-25.5-4.1-33.7l-87.5-68.6c.5-1.7 .7-3.5 .7-5.4c0-27.6-11-54.1-30.5-73.7L512 320c-20.5-20.5-32-48.3-32-77.3V208c0-77.4-55-142-128-156.8V32c0-17.7-14.3-32-32-32s-32 14.3-32 32V51.2c-42.6 8.6-79 34.2-102 69.3L38.8 5.1zM160 242.7c0 29-11.5 56.8-32 77.3l-1.5 1.5C107 341 96 367.5 96 395.2c0 11.5 9.3 20.8 20.8 20.8H406.2L160 222.1v20.7zM384 448H320 256c0 17 6.7 33.3 18.7 45.3s28.3 18.7 45.3 18.7s33.3-6.7 45.3-18.7s18.7-28.3 18.7-45.3z"/></svg></c:if><c:if test="${not subscription.notificationsOn}"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M224 0c-17.7 0-32 14.3-32 32V51.2C119 66 64 130.6 64 208v18.8c0 47-17.3 92.4-48.5 127.6l-7.4 8.3c-8.4 9.4-10.4 22.9-5.3 34.4S19.4 416 32 416H416c12.6 0 24-7.4 29.2-18.9s3.1-25-5.3-34.4l-7.4-8.3C401.3 319.2 384 273.9 384 226.8V208c0-77.4-55-142-128-156.8V32c0-17.7-14.3-32-32-32zm45.3 493.3c12-12 18.7-28.3 18.7-45.3H224 160c0 17 6.7 33.3 18.7 45.3s28.3 18.7 45.3 18.7s33.3-6.7 45.3-18.7z"/></svg></c:if>
								</button>
							</form>
						</c:if>
					</c:if>
				</div>
			</div>



			<div class="recommendedStories">
				<b>Check other stories from this category!</b><br>
				<div class="reccommended-stories">
					<c:forEach items="${otherStoriesFromCategory}"
						var="otherStoryFromCategory" varStatus="loop">
						<div class="reccommended-story">
							<a href="/story/${otherStoryFromCategory.id}">${otherStoryFromCategory.title}</a>
						</div>
						<p class="division">|</p>
					</c:forEach>

				</div>
			</div>



			<div class="comment-container">
				<div class="write-comment">
					<c:if test="${loggedIn&&!replyBox}">
						<h3>Leave a Comment</h3>
						<form action="/comment/${story.id}" method="post">
							<textarea class="comment-field" name="content" rows="5" cols="30"></textarea>
							<br> <input class="button1" type="submit"
								value="Send Comment">
						</form>
					</c:if>
				</div>

				<div class="comments-section">

					<c:forEach items="${story.comments}" var="comment">
						<div class="comments-and-replies">
							<div class="comment">
								<p>${comment.content}</p>
								<h5>Written by: ${comment.user.username}</h5>
								<c:if test="${loggedIn&&!replyBox}">
									<form action="/reply/${comment.id}" method="get">
										<input class="button1" type="submit" value="Reply">
									</form>
								</c:if>


							</div>
							<div class="reply">
								<c:if test="${replyBox&&comment.id==commentId}">
									<form action="/reply/${comment.id}" method="post">
										<textarea name="content" rows="5" cols="30"></textarea>
										<br> <input type="submit" value="Send Reply">
									</form>
								</c:if>

								<c:forEach items="${replies}" var="reply">
									<c:if test="${reply.parentComment==comment}">
										<div class="reply">
											<p>${reply.content}</p>
											<h5>Written by: ${reply.user.username}</h5>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

		</div>
	</div>
	<footer class="footer">
		<%@ include file="footer.jsp"%>
	</footer>
</body>
</html>




