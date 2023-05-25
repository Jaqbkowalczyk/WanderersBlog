<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/style.css">
    <title>Messages</title>
</head>
<body>
  <div class="wrapper">
    <header class="header">
      <%@ include file="header.jsp" %>
    </header>
    <div class="main">
      <div class="faq-title">Inbox</div>
	
  	    <table class="index-table-mes">
          <tr>
	        <th class="index-th">Subject</th>
	        <th class="index-th">From</th>
	        <th class="index-th">Message</th>
	        <th class="index-th">Conversation</th>
	        <th class="index-th">Unread messages</th>
          </tr>
          <c:forEach items="${conversations}" var="conversation" varStatus="loop">
            <tr class="index-tr">
              <td class="index-td">${conversation.subject}</td>
              <c:if test = "${conversation.recipient.id == user_id}"><td class="index-td">${conversation.sender.username}</td></c:if>
               <c:if test = "${conversation.sender.id == user_id}"><td class="index-td">${conversation.recipient.username}</td></c:if>
              <td class="index-td">
              <c:forEach 
      	          items="${lastMessages}" 
      	          var="message" 
      	          varStatus="messageLoop"
      	        >
      	        <c:if test = "${messageLoop.index == loop.index}">
      	          ${message} 
      	        </c:if>
      	        </c:forEach>
             
              </td>
              <td class="index-td">
                <a 
                  href="/conversation/${conversation.id}" 
                  class="link-details"
                >Go to conversation
                </a>
              </td>
      	      <td class="mark">
      	        <c:forEach 
      	          items="${unreadMarks}" 
      	          var="mark" 
      	          varStatus="markLoop"
      	        >
      	        <c:if test = "${markLoop.index == loop.index && mark>0}">
      	          ( ${mark} )
      	        </c:if>
      	        </c:forEach>
              </td>
            </tr> 
          </c:forEach>
        </table>
    </div>
    <footer>
  	  <%@ include file ="footer.jsp" %>
    </footer>
  </div>
</body>
</html>
