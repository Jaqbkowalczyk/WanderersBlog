<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
					<div class="Story-title">Create your Find Company offer here:</div>
					<form action="create-offer" method="POST"
						enctype="multipart/form-data">
						<label for="title">Offer Title:</label><br> <input
							type="text" id="title" name="title" class="first"
							placeholder="type here..."><br> <label
							for="description">Text:</label><br>
						<textarea id="description" name="description" rows="20" cols="100"
							class="textarea" class="first" placeholder="">
            			</textarea>
						<br> <br> <label for="location">Location:</label><br>
						<br> <select name="location" id="location">
							<option value="North America">North America</option>
							<option value="South America">South America</option>
							<option value="Africa">Africa</option>
							<option value="Europe">Europe</option>
							<option value="Oceania">Oceania</option>
							<option value="Asia">Asia</option>
							<option value="Middle East">Middle East</option>
							<option value="Arctic">Arctic</option>
						</select> <br> <br> <label for="value">How many
							companions do you want to find?</label><br> <input
							class="rate-input" id="value" class="second" type="number"
							name="howManyLookingFor" min="1" max="10"
							placeholder="Type in number of companions (max 10)" required><br>
						<br> 
						<div class="choose-date">
						<label for="startDate">Start date:</label> <input
							type="date" id="startDate" name="startDate" required><br>
						<br> <label for="endDate">End date:</label> <input
							type="date" id="endDate" name="endDate" required><br>
						<br>
						</div>

						<div class="register-button">
							<label>Photos: </label> <input class="photo-input" type="file"
								name="images" accept="image/png, image/jpeg, image/jpg"
								multiple="multiple" /> <input class="add-story" type="submit" value="Submit">
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