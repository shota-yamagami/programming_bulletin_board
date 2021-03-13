<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
	<c:param name="content">
		<c:if test="${sessionScope.login_participant != null}">
			<c:if test="${flush != null}">
	            <div id="flush">
	                <c:out value="${flush}"></c:out>
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
									<c:when test="${tweet.language_flag == 1}">Java</c:when>
									<c:when test="${tweet.language_flag == 2}">PHP</c:when>
									<c:when test="${tweet.language_flag == 3}">Ruby</c:when>
									<c:when test="${tweet.language_flag == 4}">Python</c:when>
									<c:when test="${tweet.language_flag == 5}">C言語</c:when>
									<c:otherwise>JavaScript</c:otherwise>
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
