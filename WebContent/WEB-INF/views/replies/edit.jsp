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
	    <c:choose>
	   		<c:when test="${reply != null}">
			        <h2>編集ページ</h2>
			        <form method="POST" action="<c:url value='/replies/update' />">
			            <c:import url="_form.jsp" />
			            <button type="submit">更新</button>
			        </form>
		            <p><a href="#" onclick="confirmDestroy();">この投稿を削除する</a></p>
		            <form method="POST" action="<c:url value='/replies/destroy' />">
		            	<input type="hidden" name="_token" value="${_token}" />
		            </form>
	            <script>
	            	function confirmDestroy() {
	            		if(confirm("本当に削除してよろしいですか？")) {
	            			document.forms[1].submit();
	            		}
	            	}
	            </script>
    	  </c:when>
    	  <c:otherwise>
             <h2>お探しのデータは見つかりませんでした。</h2>
          </c:otherwise>
    	</c:choose>
    </c:param>
</c:import>
