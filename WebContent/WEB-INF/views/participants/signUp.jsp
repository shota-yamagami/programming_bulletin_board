<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/signUp.css' />">
        <title>Insert title here</title>
    </head>
<body>
	<div id="header">
		 <h1>PGハウス</h1>
	     <div class="header_menu">
			<a href="<c:url value='/index.html' />">トップページ</a>&emsp;&emsp;
			<a href="<c:url value='/participants/login' />">ログイン</a>
		</div>
	</div>
			<c:if test="${errors != null}">
				<div id="flush_error">
					入力内容にエラーがあります。<br />
					<c:forEach var="error" items="${errors}">
						・<c:out value="${error}" /><br />
					</c:forEach>
				</div>
		</c:if>
	     <div class="content">
		 <div class="signUp">
		 <form method="POST" action="<c:url value='/participants/create' />">
		 <h2> sign up </h2>
		    <input type="text" class="text" name="name" value="${participant.name}" />
		    <span>username</span>
		    <br>
		    <br>
		    <input type="text" class="text" name="mail" value="${participant.mail}" />
		    <span>mail</span>
		    <br>
		    <br>
		    <input type="password" class="text" name="password">
		    <span>password</span>
		    <br>
			<input type="hidden" name="_token" value="${_token}" />
		    <button class="signin">Sign Up</button>
		    <hr>
		</form>
		</div>
		</div>
		<div id="footer">
			by gami.
		</div>
    </body>
</html>
