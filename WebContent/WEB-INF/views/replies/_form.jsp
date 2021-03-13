<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<label for="name">ニックネーム</label><br />
<c:out value="${sessionScope.login_participant.name}" />
<br /><br />

<label for="tweet_content">内容</label><br />
<textarea id="tweet_content" name="content" rows="10" cols="50">${reply.content}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
