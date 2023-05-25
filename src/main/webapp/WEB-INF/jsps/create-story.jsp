<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/create-story-and-offer.css">
<title>Wanderers Blog</title>>
</head>
<body>
	<div class="wrapper">
			<%@ include file="header.jsp"%>
		<main class="main">
			<div class="log-name-block">
				<div class="offer-block-main">
					<div class="Story-title">Create your Story here:</div>
					<form action="create-story" method="POST"
						enctype="multipart/form-data">
						<label for="title">Story Title:</label><br> <input
							type="text" id="title" name="title" class="first"
							placeholder="type here..."><br> <label
							for="description">Text:</label><br>
						<textarea id="description" name="description" rows="20" cols="100"
							class="textarea" class="first" placeholder="">
           				 </textarea>
						<br> <label for="category">Category:</label><br> <br>
						<select name="category" id="category">
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
						</select> <br> <br> <label for="location">Location:</label><br>
						<br> <select name="location" id="location">
							<option value="North America">North America</option>
							<option value="South America">South America</option>
							<option value="Africa">Africa</option>
							<option value="Europe">Europe</option>
							<option value="Oceania">Oceania</option>
							<option value="Asia">Asia</option>
							<option value="Middle East">Middle East</option>
							<option value="Arctic">Arctic</option>
						</select> <br> <br>

						<div class="register-button">
							<label>Photos: </label> <input class="photo-input" type="file"
								name="images" accept="image/png, image/jpeg, image/jpg"
								multiple="multiple" /> <input type="submit" value="Submit">

						</div>
					</form>
				</div>

			</div>
		</main>
		<footer class="footer">
			<%@ include file="footer.jsp"%>
		</footer>
	</div>
</body>
</html>