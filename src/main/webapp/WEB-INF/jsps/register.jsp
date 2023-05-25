<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/form.css">
  <title>Wanderers Blog</title>
</head>
<body>
<header class="header">
	<%@ include file="header.jsp"%>
</header>
<br><br>
  <div class="wrapper in-center">
    <main class="main">
      <div class="log-name"><h2>Sign up</h2></div>
        <div class="main-row-reg">
        <div class="log-name-block">
          <div class="register-block-main">
            <form action="/register" method="POST" class="form">
            <div class="form-container-main">
            <div class="special-mes">${message}</div>
            <div class="form-container">
            <div class="form-one">
              <label for="username">Create username:</label><br>
              <input 
                type="text" 
                id="username"
                name="username"
                class="first"
                placeholder="Username"
              ><br>
              <label for="password">Create password:</label><br>
              <input 
                type="password"
                id="password"
                name="password"
                class="second"
                placeholder="Password"
              ><br>
              <label for="confrimPassword">Confirm password:</label><br>
              <input 
                type="password"
                id="confirmPassword"
                name="confirmPassword"
                class="second"
                placeholder="Confirm password"
              ><br>
              <label for="email">Enter your email:</label><br>
              <input 
                type="email" 
                id="email" 
                name="email"
                class="second"
                placeholder="Email"
              ><br>
              <label for="firstName">Name:</label><br>
              <input 
              	type="text"
              	id="firstName" 
				name="firstName" 
				class="second"
				placeholder="Name"
			  ><br>
			  <label for="surName">Surname:</label><br>
			  <input
			  	id="surName" 
			  	class="second"
			  	type="text"
				name="surName" 
				placeholder="Surname"
			  ><br>
			</div>
			
			</div>
              </div>
			  <div class="register-button">
           		<input type="submit" value="Register">
          	  </div>
            </form>
            <div class="check"> 
            <form>
              <input type="checkbox" id="remain" name="remain" value="remain">
              <label for="remain">Remain logged in</label><br>
            </form>
            </div>
          </div>
        </div>
        </div>
    </main><br><br>
    <footer class="footer-contact-information">
        <%@ include file ="footer.jsp" %>
    </footer>
  </div>
</body>
</html>