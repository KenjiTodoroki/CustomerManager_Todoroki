<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 利用したいタグライブラリを宣言 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="assets/stylesheets/login.css">
</head>
<body>
	<main>
		<div class="main">
			<div class="main__imageBox">
				<img class="main__image" src="assets/images/seassist_logo.png" alt="ロゴ">
			</div>
			<h2>顧客管理システム</h2>
			<%-- 
			c:if　（単一の条件分岐）
			<c:if test="条件式" [var="条件式の結果を格納する変数" ] [scope="変数を格納するスコープ" ]>
    		条件式がtrueの場合の処理
			</c:if> 
		    --%>
			<c:if test="${errorMessage != null}">
				<p class="main__errorMessage">${errorMessage}</p>
			</c:if>
			<form action="login" method="post">
				<label for="userId">ユーザー名</label>
				<br>
				<input type="text" name="userId" id="userId">
				<br>
				<label for="password">パスワード</label>
				<br>
				<input type="password" name="password" id="password">
				<br>
				<br>
				<input type="submit" value="ログイン">
			</form>
		</div>
	</main>
</body>
</html>