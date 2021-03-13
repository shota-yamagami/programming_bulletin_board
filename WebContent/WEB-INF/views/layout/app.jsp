<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>PG掲示板</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
		<div class="wrapper">
			<div class="header">
				<div class="header_left">
					<h1>PGハウス</h1>&emsp;&emsp;&emsp;&emsp;
					<p><c:out value="${sessionScope.login_participant.name}" />&nbsp;さん</p>
					</div>
				<div class="header_center">
					<form method="POST" action="<c:url value='/searches/search' />">
					     <div class="search">
					        <input type="text" class="searchTerm" name="id" placeholder="Search">
					        <button type="submit" class="searchButton"></button>
					     </div>
	           		</form>
           		</div>
           		<div class="header_right">
           				<a href="<c:url value='/tweets/new' />">投稿</a>&emsp;&emsp;&emsp;
	           			<a href="<c:url value='/tweets/myPage' />">Myページ</a>&emsp;&emsp;&emsp;
						<a href="<c:url value='/participants/logout' />">ログアウト</a>&emsp;&emsp;
	 			</div>
			</div>
			<div class="content">
				<div class="content_left">
			        <h2>言語メニュー</h2>
			        <ul>
			        	<li><a href="<c:url value='/languages?language_flag=${0}' />">JavaScript</a></li>
			        	<li><a href="<c:url value='/languages?language_flag=${1}' />">Java</a></li>
			        	<li><a href="<c:url value='/languages?language_flag=${2}' />">PHP</a></li>
			        	<li><a href="<c:url value='/languages?language_flag=${3}' />">Ruby</a></li>
			        	<li><a href="<c:url value='/languages?language_flag=${4}' />">Python</a></li>
			        	<li><a href="<c:url value='/languages?language_flag=${5}' />">C言語</a></li>
			        </ul>
		        </div>
		        <div class="content_right">
					${param.content}
				</div>
		    </div>
		    <div id="footer">
			    by gami.
		    </div>
		</div>
    </body>
</html>
