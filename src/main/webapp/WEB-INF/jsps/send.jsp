<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
<div class="send-container">

  <form action="/sendMessage/<c:if test = "${not empty offer.id}">${offer.id}/${user.id}</c:if><c:if test = "${empty offer.id}">${conversationId}</c:if>" method="post">
 	<div class="send-message-input">
    <textarea 
      id="send-message-input" 
      name="content"
      rows="15" 
      cols="90"
      required
    ></textarea>
    </div>
    <div class="send-button">
      <button type="submit" >
   		<svg id="arrow" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
   		<path d="M73 39c-14.8-9.1-33.4-9.4-48.5-.9S0 62.6 0 80V432c0 17.4 9.4 33.4 24.5 41.9s33.7 8.1 48.5-.9L361 297c14.3-8.7 23-24.2 23-41s-8.7-32.2-23-41L73 39z"/>
   		</svg>
	</button>
    </div>
<!--     <button class="send-message-btn">Send</button> -->
  </form>
</div>
</body>
</html>

