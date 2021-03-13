<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>PG掲示板</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/topPage.css' />">
    </head>
    <body>
		<div class="wrapper">
			<div class="content">
				<div class="header">
					<h1> WELLCOME PG HOUSE</h1>
					<div class="header_menu">
						<a href="<c:url value='/pdf/precautions.pdf' />">注意事項</a>&emsp;&emsp;&emsp;&emsp;
						<a href="<c:url value='/participants/signUp' />">新規登録</a>&emsp;&emsp;&emsp;&emsp;
						<a href="<c:url value='/participants/login' />">ログイン</a>
					</div>
				</div>
				<div class="animation">
					<h2>プログラミング専用掲示板サイトへようこそ！</h2>
				</div>
			</div>
			<div class="main">
				<P>ここではプログラミングの経験が豊富な方から未経験者まで、経歴を問わず<br />
				自分の興味のあるプログラミング言語のページへ入り知識、学習したこと、<br />経験したこと、質問や疑問に思ったことなどつぶやいて<br />
				コミュニケーションをとり気軽にご利用いただける<br /><span>プログラミング専用掲示板サイト</span>です。</P>
			</div>
			<div class="bg"></div>
			<div class="bg bg2"></div>
			<div class="bg bg3"></div>
			<div class="footer">
				by gami.
			</div>
		</div>
    </body>
</html>
