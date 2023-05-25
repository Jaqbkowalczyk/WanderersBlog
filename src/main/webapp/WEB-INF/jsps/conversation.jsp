<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/style.css">
<title>Conversation</title>
</head>
<body>
	<header class="header">
		<%@ include file="header.jsp"%>
	</header>
	<section class="chatbox">

		<div class="convo">
			<p>
				Conversation between <span class="convo-name">
					${sender.username} </span> and <span class="convo-name-two">
					${recipient.username} </span>
			</p>
		</div>
		<div class="sub-convo">

			<span class="sub-convo-span">Subject:</span> ${subject}

		</div>
		<div class="chat-window">
			<c:forEach var="message" items="${messages}">
				<div class="message">
					<div
						class="<c:if test="${message.sender.username!=loggedUser.username}">message-left</c:if><c:if test="${message.sender.username==loggedUser.username}">message-right</c:if>">
						<table class="messages-convo">
							<tr class="time-sent">
								<td><fmt:formatDate value="${message.timeSent}"
										pattern="hh:mm dd/MM" /></td>
							</tr>
							<tr class="message-main">
								<!--<th>Message:</th>  -->
								<td>${message.content}</td>
							</tr>

							<tr
								class="<c:if test="${message.isRead}">is-read</c:if><c:if test="${not message.isRead}">not-read</c:if>">
								<td><svg xmlns="http://www.w3.org/2000/svg"
										viewBox="0 0 512 512">
										<!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
										<path
											d="M374.6 86.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 178.7l-57.4-57.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l80 80c12.5 12.5 32.8 12.5 45.3 0l160-160zm96 128c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 402.7 86.6 297.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l128 128c12.5 12.5 32.8 12.5 45.3 0l256-256z" /></svg></td>
							</tr>
						</table>
					</div>
				</div>
			</c:forEach>
		</div>
		<c:if test="${sender.username != 'Wanderer'}">
			<%@ include file="send.jsp"%>
		</c:if>

	</section>
	<%@ include file="footer.jsp"%>
	<script type="text/javascript">
		document.addEventListener("DOMContentLoaded", function() {
			var chatWindow = document.querySelector(".chat-window");
			chatWindow.scrollTop = chatWindow.scrollHeight;
		});
	</script>
</body>
</html>
