<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List, model.entity.AreaBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客登録</title>
<link rel="stylesheet" href="assets/stylesheets/customer-register-edit.css">
</head>
<body>
	<%-- 
	c:import	(外部ファイルをインポート)
	<c:import url="リソースパス" ・・・・ />で外部ファイル呼び出し 
	--%>
	<c:import url="header.jsp"/>
	<main>
		<div class="main">
			<h1>顧客登録フォーム</h1>
			<%-- 
			c:if　（単一の条件分岐）
			<c:if test="条件式" [var="条件式の結果を格納する変数" ] [scope="変数を格納するスコープ" ]>
    		条件式がtrueの場合の処理
			</c:if> 
		    --%>
			<c:if test="${errorMessage != null}">
				<p class="main__errorMessage">${errorMessage}</p>
			</c:if>
			<form action="customer-register" method="post">
				<label for="customerName">氏名</label>
				<input type="text" name="customerName" id="customerName" placeholder="山田太郎" required>
				<br>
				<label for="customerNameKana">かな</label>
				<input type="text" name="customerNameKana" id="customerNameKana" placeholder="やまだたろう" required>
				<br>
				<label for="postCode">郵便番号</label>
				<input type="text" name="postCode" id="postCode" placeholder="8120037" required>
				<br>
				<label for="areaCode">地区</label> <select name="areaCode" id="areaCode" required>
					<%-- 
					c:forEach　（繰り返し）
					<c:forEach var="コレクション・配列の各要素を表す変数名" items="コレクション・配列を参照する変数名" > 
					--%>
					<c:forEach var="area" items="${areas}">
						<option value="${area.getAreaCode()}">${area.getAreaName()}</option>
					</c:forEach>
				</select>
				<br>
				<label>性別</label>
				<input type="radio" name="gender" value="男" id="male">
				<label for="male">男</label>
				<input type="radio" name="gender" value="女" id="female">
				<label for="female">女</label>
				<br>
				<label for="birthday">生年月日</label>
				<input type="text" name="birthday" id="birthday" placeholder="19910105" required>
				<br>
				<label for="phoneNumber">電話番号</label>
				<input type="text" name="phoneNumber" id="phoneNumber" placeholder="09011112222" required>
				<br>
				<input type="submit" name="button" value="顧客登録確定">
				<input type="reset" value="クリア">
				<br>
				<input type="submit" value="メニュー画面へ" onclick="location.href='menu.jsp'" class="main__toMenu">
			</form>
		</div>
	</main>
</body>
</html>