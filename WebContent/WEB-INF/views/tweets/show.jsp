<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
    	<c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
    	<c:choose>
    		<c:when test="${tweet != null}">

    			<h2>投稿内容</h2>

    			<table class="details">
                    <tbody>
                        <tr>
                            <th>タイトル</th>
                            <td><c:out value="${tweet.title}" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${tweet.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>ニックネーム</th>
                            <td><c:out value="${tweet.participant_id.name}" /></td>
                        </tr>
                        <tr>
                            <th>日時</th>
                            <td>
                                <fmt:formatDate value="${tweet.created_at}" pattern="yyyy/MM/dd HH:mm" />
                            </td>
                        </tr>
                    </tbody>
                </table>
				<c:choose>
					<c:when test="${like > 0}">
						<p><a href="<c:url value='/likes/like' />"><img src="https://img.icons8.com/material/24/4a90e2/hearts--v1.png"/></a></p>
					</c:when>
					<c:otherwise>
						<p><a href="<c:url value='/likes/like' />"><img src="https://img.icons8.com/fluent-systems-filled/24/4a90e2/hearts.png"/></a></p>
					</c:otherwise>
				</c:choose>
				<p><a href="<c:url value='/replies/new?tweet_id=${tweet.tweet_id}' />">コメントする</a></p>
                <c:if test="${sessionScope.login_participant.name == tweet.participant_id.name}">
                    <p><a href="<c:url value="/tweets/edit?tweet_id=${tweet.tweet_id}" />">この投稿を編集する</a></p>
                </c:if>
                <br />
				<h3>コメント 一覧</h3>
    		</c:when>
    		<c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
    	</c:choose>
    	<c:forEach var="reply" items="${replys}" varStatus="status">
    	<c:choose>
    		<c:when test="${reply != null}">

    			<table class="reply">
                    <tbody>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${reply.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>ニックネーム</th>
                            <td><c:out value="${reply.participant_id.name}" /></td>
                        </tr>
                        <tr>
                            <th>日時</th>
                            <td>
                                <fmt:formatDate value="${reply.created_at}" pattern="yyyy/MM/dd HH:mm" />
                            </td>
                        </tr>
                    </tbody>
                </table>
	                <c:if test="${sessionScope.login_participant.name == reply.participant_id.name}">
	                    <p><a href="<c:url value="/replies/edit?reply_id=${reply.reply_id}" />">この内容を編集する</a></p>
	                </c:if>
                <br />
    		</c:when>
    		<c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
    	</c:choose>
    	</c:forEach>
    </c:param>
</c:import>