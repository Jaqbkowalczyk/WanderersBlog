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
  
      <div class="log-name"><h2>Log in</h2></div>
      
        <div class="main-row">
        <div class="login-name-block">
        <div class="special-mes">${message2}</div>
          <div class="log-name-block-main">
            <form action="/login" method="post">
              <label for="username">Username:</label><br>
              <input 
                type="text" 
                id="username" 
                name="username"
                class="first"
                placeholder="type here..."
              ><br>
              <label for="password">Password:</label><br>
              <input 
                type="password" 
                id="password" 
                name="password"
                class="second"
                placeholder="type here..."
              >
              
              <div class="register-button">
                <a href=""><input type="submit" value="Log in"></a>
              </div>
            </form>
            <a class="forgot-pass" href="/forgottenPassword">Forgot password</a>
          </div>
          <div class="check"> 
            <form>
              <input type="checkbox" id="remain" name="remain" value="remain">
              <label for="remain">Remain logged in</label><br>
            </form>
          </div>
          <div class="log-in-button">
			<span class="button-reg">Not with us yet?</span> <a href="/register">Register here</a>
		  </div>
        </div>
      </div>
  
    </main>
    </div>
    <footer class="footer-contact-information">
        <%@ include file ="footer.jsp" %>
    </footer>
 
</body>
</html>