<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
	<c:param name="header">
		<div class="header_menu">
			<a href="<c:url value='/index.html' />">トップページ</a>&emsp;&emsp;
			<a href="<c:url value='/participants/login' />">ログイン</a>
		</div>
	</c:param>
	<c:param name="content">
		<h2>新規登録</h2>
		<form method="POST" action="<c:url value='/participants/create' />">
			<c:if test="${errors != null}">
				<div id="flush_error">
					入力内容にエラーがあります。<br />
					<c:forEach var="error" items="${errors}">
						・<c:out value="${error}" /><br />
					</c:forEach>
				</div>
			</c:if>
			<label for="name">ニックネーム</label><br />
			<input type="text" id="name" name="name" value="${participant.name}" />
			<br /><br />

			<label for="mail">メールアドレス</label><br />
			<input type="text" id="mail" name="mail" value="${participant.mail}" />
			<br /><br />

			<label for="password">パスワード</label><br />
			<input type="password" id="password" name="password" />
			<br /><br />

			<input type="hidden" name="_token" value="${_token}" />
			<button type="submit">登録</button>
		</form>
	</c:param>
</c:import>