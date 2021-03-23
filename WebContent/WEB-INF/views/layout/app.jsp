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
					<h1>PG HOUSE</h1>&emsp;&emsp;&emsp;&emsp;
					<p><c:out value="${sessionScope.login_participant.name}" />&nbsp;さん</p>
				</div>
				${param.header}
           		<div class="header_right">
           			<a href="<c:url value='/tweets/new' />">投稿</a>&emsp;&emsp;
       				<a href="<c:url value='/searches/search' />">検索ページ</a>&emsp;&emsp;
           			<a href="<c:url value='/tweets/myPage' />">Myページ</a>&emsp;&emsp;
					<a href="<c:url value='/participants/logout' />">ログアウト</a>&emsp;&emsp;
	 			</div>
			</div>
			<div class="content">
				<div class="content_left">
			        <h2>言語メニュー</h2>
			        <ul>
			        	<li><a href="<c:url value='/languages?language_flag=${0}' />">HTML、CSS</a></li>
			        	<li><a href="<c:url value='/languages?language_flag=${1}' />">JavaScript</a></li>
			        	<li><a href="<c:url value='/languages?language_flag=${2}' />">Java</a></li>
			        	<li><a href="<c:url value='/languages?language_flag=${3}' />">PHP</a></li>
			        	<li><a href="<c:url value='/languages?language_flag=${4}' />">Ruby</a></li>
			        	<li><a href="<c:url value='/languages?language_flag=${5}' />">Python</a></li>
			        	<li><a href="<c:url value='/languages?language_flag=${6}' />">C言語</a></li>
			        	<li><a href="<c:url value='/languages?language_flag=${7}' />">その他言語</a></li>
			        </ul>
		        </div>
		        <div class="content_right">
					${param.content}
				</div>
		    </div>
		    <div id="footer">
			    PG HOUSE.
		    </div>
		</div>
    </body>
</html>
