<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<label for="name">ニックネーム</label><br />
<c:out value="${sessionScope.login_participant.name}" />
<br /><br />

<label for="language_flag">言語</label><br />
<select name="language_flag">
    <option value="0"<c:if test="${tweet.language_flag == 0}"> selected</c:if>>JavaScript</option>
    <option value="1"<c:if test="${tweet.language_flag == 1}"> selected</c:if>>Java</option>
    <option value="2"<c:if test="${tweet.language_flag == 2}"> selected</c:if>>PHP</option>
    <option value="3"<c:if test="${tweet.language_flag == 3}"> selected</c:if>>Ruby</option>
    <option value="4"<c:if test="${tweet.language_flag == 4}"> selected</c:if>>Python</option>
    <option value="5"<c:if test="${tweet.language_flag == 5}"> selected</c:if>>C言語</option>
</select>
<br /><br />

<label for="tweet_title">タイトル</label><br />
<input type="text" id="tweet_title" name="title" value="${tweet.title}" />
<br /><br />

<label for="tweet_content">内容</label><br />
<textarea id="tweet_content" name="content" rows="10" cols="50">${tweet.content}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
