<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/login.css' />">
        <title>Insert title here</title>
    </head>
	<body>
	     <div id="header">
			 <h1>PGハウス</h1>
		     <div class="header_menu">
				<a href="<c:url value='/index.html' />">トップページ</a>&emsp;&emsp;
				<a href="<c:url value='/participants/signUp' />">新規登録</a>
			</div>
		</div>

		<c:if test="${hasError}">
	        <div id="flush_error">
	            ニックネームかパスワードが間違っています。
	        </div>
	     </c:if>
	     <c:if test="${flush != null}">
	         <div id="flush_success">
	             <c:out value="${flush}"></c:out>
	         </div>
	     </c:if>

	     <div class="content">
		 <div class="login">
		 <form method="POST" action="<c:url value='/participants/login' />">
		    <h2 class="active"> sign in </h2>
			    <input type="text" class="text" name="name" value="${participant.name}" />
			    <span>username</span>
			    <br>
			    <br>
			    <input type="password" class="text" name="password">
			    <span>password</span>
			    <br>
			    <input type="checkbox" id="checkbox-1-1" class="custom-checkbox" />
			    <label for="checkbox-1-1">Keep me Signed in</label>
				<input type="hidden" name="_token" value="${_token}" />
			    <button class="signin">Sign In</button>
			    <hr>
		</form>
		</div>
		</div>

		<div id="footer">
			by gami.
		</div>
    </body>
</html>
