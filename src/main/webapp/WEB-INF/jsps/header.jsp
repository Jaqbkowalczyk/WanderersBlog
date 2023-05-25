<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<body>
	<header>
		<div class="logo">
			<a href="/"><img class="logo" src="/img/logo.png" alt=""></a>
		</div>
		<div class="top-bar">

			<c:if test="${not loggedIn}">
			<div class="log-in-top">
				<a href="/login">Log in</a>
			</div>
			<div class="sign-up-top">
				<a href="/register">Sign up</a>
			</div>
			</c:if>
			<c:if test="${loggedIn}">
			  	<div class="user-greeting">
				<p>
					Hello, <span>${firstname}</span>!
				</p>
				</div>
				<div class="user-logo">
			    <a href="/userProfile/${loggedUser.username}">
			      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M304 128a80 80 0 1 0 -160 0 80 80 0 1 0 160 0zM96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM49.3 464H398.7c-8.9-63.3-63.3-112-129-112H178.3c-65.7 0-120.1 48.7-129 112zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3z"/></svg></a>
			  </div>
				<div class="envelope">
	                <a class="photo" href="/inbox">
					  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M64 112c-8.8 0-16 7.2-16 16v22.1L220.5 291.7c20.7 17 50.4 17 71.1 0L464 150.1V128c0-8.8-7.2-16-16-16H64zM48 212.2V384c0 8.8 7.2 16 16 16H448c8.8 0 16-7.2 16-16V212.2L322 328.8c-38.4 31.5-93.7 31.5-132 0L48 212.2zM0 128C0 92.7 28.7 64 64 64H448c35.3 0 64 28.7 64 64V384c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V128z"/></svg></a>
					<c:if test="${unread > 0}">
						<span>(${unread})</span>
					</c:if>
				</div>
				<div class="log-out-top">
				<a href="/logout">Log out</a>
				</div>
				
			</c:if>
		</div>	
	<nav>
      <div class="nav-containter">
        <div class="nav-bar">
          <ul class="nav-list">
            <li><a href="/Category/Adventure">Adventure</a></li>
            <li><a href="/Category/Advices">Advices</a></li>
            <li><a href="/Category/Hitchhiking">Hitchhiking</a></li>
            <li><a href="/Category/Culture">Culture</a></li>
            <li><a href="/Category/Nightlife">Nightlife</a></li>
            <li><a href="/Category/Food">Food</a></li>
            <li><a href="/Category/Luxury Travel">Luxury Travel</a></li>
            <li><a href="/Category/Family Travel">Family Travel</a></li>
            <li><a href="/Category/Group Travel">Group Travel</a></li>
            <li><a href="/Category/Budget Travel">Budget Travel</a></li>
            <li><a href="/Category/Solo Travel">Solo Travel</a></li>
            <li><a href="/FindCompany">Find Company</a></li>
          </ul>
        </div>
      </div>
    </nav>
		
	</header>
</body>
</html>