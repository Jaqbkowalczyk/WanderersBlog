<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit user details</title>
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/form.css">
</head>
<body>
	<header class="header">
		<%@ include file="header.jsp"%>
	</header>
    <main class="main">
      <div class="log-name">Edit Your Details</div>
        <div class="main-row">
        <div class="log-name-block">
          <div class="log-name-block-main">
            <form action="/editUserDetails" method="POST">
              <label for="email">Enter your email:</label><br>
              <input 
                type="email" 
                id="email" 
                name="email"
                class="second"
                value="${user.email}"
                
                
                placeholder="type here..."
              ><br>
              <label for="firstName">Name:</label><br>
              <input 
              	type="text"
              	id="firstName" 
				name="firstName" 
				class="second"
				value="${user.firstName}"
				
				placeholder="Name"
			  ><br>
			  <label for="surName">Surname:</label><br>
			  <input id="surName" 
			  	class="second"
			  	type="text"
				name="surName"
				value="${user.surName}" 
				
				placeholder="Surname"
			  ><br>	 
			  <div class="register-button">
           		<input type="submit" value="Save changes">
          	  </div>
            </form>
          </div>
 <!--         <div class="check"> 
             <form>
              <input type="checkbox" id="remain" name="remain" value="remain">
              <label for="remain">Remain logged in</label><br>
            </form>
          </div> -->
        </div>
      </div>
  
    </main>

    <footer class="footer-contact-information">
        <%@ include file ="footer.jsp" %>
    </footer>

</body>
</html>