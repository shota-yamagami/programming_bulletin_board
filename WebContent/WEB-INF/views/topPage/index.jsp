<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
	<c:param name="header">
		<div class="header_menu">
			<a href="<c:url value='/pdf/pdf_topic.pdf' />">注意事項</a>
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			<a href="<c:url value='/participants/signUp' />">新規登録</a>&emsp;&emsp;
			<a href="<c:url value='/participants/login' />">ログイン</a>
		</div>
	</c:param>
	<c:param name="content">
		<h2>ようこそ！ PGハウスへ</h2>
		<P class="PR">ここではプログラミングの経験が豊富な方から未経験者まで、経歴を問わず<br />
		自分の興味のあるプログラミング言語のページへ入り知識、学習したこと、<br />経験したこと、質問や疑問に思ったことなどつぶやいて<br />
		コミュニケーションをとり気軽にご利用いただけることの出来る<br /><span>プログラミング専用掲示板サイト</span>です。</P>
	</c:param>
</c:import>
