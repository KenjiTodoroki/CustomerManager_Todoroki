<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
<link rel="stylesheet" href="assets/stylesheets/menu.css">
</head>
<body>
	<%-- 
	c:import	(外部ファイルをインポート)
	<c:import url="リソースパス" ・・・・ />で外部ファイル呼び出し 
	--%>
	<c:import url="header.jsp" />
	<main>
		<div class="main">
			<h2>顧客管理システム</h2>
			<form action="menu" method="post">
				<input type="submit" name="button" value="顧客一覧" class="menu__button">
				<c:if test="${authorityCode != 'A0'}">
					<br>
					<input type="submit" name="button" value="顧客登録" class="menu__button">
				</c:if>
				<c:if test="${authorityCode == 'A2'}">
					<br>
					<input type="submit" name="button" value="権限編集" class="menu__button">
				</c:if>
			</form>
		</div>
	</main>
</body>
</html>
