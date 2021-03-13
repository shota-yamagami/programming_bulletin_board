<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<table>
            <tbody>
                <tr>
                    <th class="tweet_title">タイトル</th>
                    <th class="tweet_name">ニックネーム</th>
                    <th class="reply_count">コメント</th>
                    <th class="like_count">👍</th>
                    <th class="tweet_created_at">日時</th>
                </tr>
                <c:forEach var="tweetsLanguage" items="${tweetsLanguages}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="tweet_title"><a href="<c:url value='/tweets/show?tweet_id=${tweetsLanguage.tweet_id}' />">${tweetsLanguage.title}</a></td>
                        <td class="tweet_name"><c:out value="${tweetsLanguage.participant_id.name}" /></td>
                        <c:set var="tweet_id" value="${tweetsLanguage.tweet_id}" />
						<td class="reply_count"><a href="<c:url value='/tweets/show?tweet_id=${tweetsLanguage.tweet_id}' />">${reply_counts[tweet_id]}件</a></td>
                        <c:set var="tweet_id" value="${tweetsLanguage.tweet_id}" />
						<td class="like_count">${like_counts[tweet_id]}</td>
                        <td class="tweet_created_at"><fmt:formatDate value="${tweetsLanguage.created_at}" pattern="yyyy/MM/dd HH:mm" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="pagination">
        	(全 ${tweetsLanguage_count} 件)<br />
        	<c:forEach var="i" begin="1" end="${((tweetsLanguage_count - 1) / 15) + 1}" step="1">
        		<c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/languages?language_flag=${language_flag}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
        	</c:forEach>
        </div>
