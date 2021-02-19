<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
	<c:param name="header">
		<div class="header_menu">
			<a href="<c:url value='/index.html' />">トップページ</a>&emsp;&emsp;
			<a href="<c:url value='/participants/signUp' />">新規登録</a>
		</div>
	</c:param>
	<c:param name="content">
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
		<h2>ログイン画面</h2>
		<form method="POST" action="<c:url value='/participants/create' />">
			<label for="name">ニックネーム</label><br />
			<input type="text" id="name" name="name" value="${participant.name}" />
			<br /><br />

			<label for="password">パスワード</label><br />
			<input type="password" id="password" name="password" />
			<br /><br />

			<input type="hidden" name="_token" value="${_token}" />
			<button type="submit">ログイン</button>
		</form>
	</c:param>
</c:import>
