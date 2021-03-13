<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
		<c:param name="content">
			<c:if test="${sessionScope.login_participant != null}">
				<c:if test="${flush != null}">
		            <div id="flush_success">
		                <c:out value="${flush}"></c:out>
		            </div>
		        </c:if>
		        <c:if test="${search_flush != null}">
		            <div id="flush_error">
		                <c:out value="${search_flush}"></c:out>
		            </div>
		        </c:if>
		        <h2>自分の投稿 一覧</h2>
				<table>
					<tr>
						<th class="language_flag">言語</th>
						<th class="title">タイトル</th>
						<th class="reply_count">コメント</th>
						<th class="like_count">👍</th>
						<th class="tweet_created_at">日時</th>
					</tr>
					<c:forEach var="tweet" items="${tweets}" varStatus="status">
						<tr class="row${status.count % 2}">
							<td class="language_flag">
								<c:choose>
									<c:when test="${tweet.language_flag == 1}">Java</c:when>
									<c:when test="${tweet.language_flag == 2}">PHP</c:when>
									<c:when test="${tweet.language_flag == 3}">Ruby</c:when>
									<c:when test="${tweet.language_flag == 4}">Python</c:when>
									<c:when test="${tweet.language_flag == 5}">C言語</c:when>
									<c:otherwise>JavaScript</c:otherwise>
								</c:choose>
							</td>
							<td class="title"><a href="<c:url value='/tweets/show?tweet_id=${tweet.tweet_id}' />">${tweet.title}</a></td>
							<c:set var="tweet_id" value="${tweet.tweet_id}" />
							<td class="reply_count"><a href="<c:url value='/tweets/show?tweet_id=${tweet.tweet_id}' />">${count[tweet_id]}件</a></td>
							<c:set var="tweet_id" value="${tweet.tweet_id}" />
							<td class="myPage_like">${like_count[tweet_id]}</td>
							<td class="tweet_created_at"><fmt:formatDate value="${tweet.created_at}" pattern="yyyy/MM/dd HH:mm" /></td>
						</tr>
					</c:forEach>
				</table>
				<div class="pagination">
					(全 ${tweets_count} 件)<br />
					<c:forEach var="i" begin="1" end="${((tweets_count - 1) / 15) + 1 }" step="1">
						<c:choose>
							<c:when test="${i == page}">
								<c:out value="${i}" />&nbsp;
							</c:when>
							<c:otherwise>
								<a href="<c:url value='/tweets/myPage?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</c:if>
		</c:param>
</c:import>
