<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
	<c:param name="header">
		<div class="header_center">
			<form method="POST" action="<c:url value='/searches/search' />">
			     <div class="search">
			        <input type="text" class="searchTerm" name="id" placeholder="Search">
			        <button type="submit" class="searchButton"></button>
			     </div>
	   		</form>
		</div>
	</c:param>
	<c:param name="content">
		<c:if test="${sessionScope.login_participant != null}">
			<c:if test="${search_flush != null}">
	            <div id="flush_error">
	                <c:out value="${search_flush}"></c:out>
	            </div>
	        </c:if>

   			<h2>検索結果</h2>
			<c:forEach var="search" items="${searches}">
			<c:if test="${searches != null}">
    			<table class="result">
                    <tbody>
                        <tr>
                            <th>言語</th>
                            <td>
                                <c:choose>
									<c:when test="${search.language_flag == 0}">HTML、CSS</c:when>
									<c:when test="${search.language_flag == 1}">JavaScript</c:when>
									<c:when test="${search.language_flag == 2}">Java</c:when>
									<c:when test="${search.language_flag == 3}">PHP</c:when>
									<c:when test="${search.language_flag == 4}">Ruby</c:when>
									<c:when test="${search.language_flag == 5}">Python</c:when>
									<c:when test="${search.language_flag == 5}">C言語</c:when>
									<c:otherwise>その他言語</c:otherwise>
								</c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>タイトル</th>
                            <td>
                                <a href="<c:url value='/tweets/show?tweet_id=${search.tweet_id}' />"><c:out value="${search.title}" /></a>
                            </td>
                        </tr>
                        <tr>
                            <th>ニックネーム</th>
                            <td><c:out value="${search.participant_id.name}" /></td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${search.updated_at}" pattern="yyyy-MM-dd HH:mm" />
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br />
               </c:if>
               </c:forEach>
          </c:if>
	</c:param>
</c:import>
