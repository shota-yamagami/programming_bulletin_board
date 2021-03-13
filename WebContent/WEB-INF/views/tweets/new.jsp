<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    	<c:if test="${errors != null}">
			<div id="flush_error">
				入力内容にエラーがあります。<br />
				<c:forEach var="error" items="${errors}">
			    	・<c:out value="${error}" /><br />
			    </c:forEach>
			</div>
		</c:if>
        <h2>新規投稿</h2>
        	<form method="POST" action="<c:url value='/tweets/create' />">
	            <c:import url="_form.jsp" />
	            <button type="submit">投稿</button>
        	</form>
    </c:param>
</c:import>
