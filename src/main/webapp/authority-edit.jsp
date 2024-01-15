<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List, model.entity.UserBean, model.entity.AuthorityBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客編集</title>
<link rel="stylesheet" href="assets/stylesheets/authority-edit.css">
</head>
<body>
	<%-- 
	c:import	(外部ファイルをインポート)
	<c:import url="リソースパス" ・・・・ />で外部ファイル呼び出し 
	--%>
	<c:import url="header.jsp" />
	<main>
		<div class="main">
			<h1>権限編集フォーム</h1>
			<%-- 
			c:if　（単一の条件分岐）
			<c:if test="条件式" [var="条件式の結果を格納する変数" ] [scope="変数を格納するスコープ" ]>
    		条件式がtrueの場合の処理
			</c:if> 
		    --%>
			<c:if test="${errorMessage != null}">
				<p class="main__errorMessage">${errorMessage}</p>
			</c:if>
			<form action="authority-edit" method="post">
			<table class="main__table">
				<thead>
					<tr class="main__tableRow">
						<th class="main__tableHeader">ユーザー名</th>
						<th class="main__tableHeader">権限</th>
					</tr>
				</thead>
				<tbody>
					<tr class="main__tableRow">
						<td class="main__tableData">
							<select name="userId">
								<%-- 
								c:forEach　（繰り返し）
								<c:forEach var="コレクション・配列の各要素を表す変数名" items="コレクション・配列を参照する変数名" > 
								--%>
								<c:forEach var="user" items="${users}">
									<option value="${user.getUserId()}">${user.getUserId()}</option>
								</c:forEach>
							</select>
						</td>
						<td class="main__tableData">
							<select name="authorityCode">
								<%-- 
								c:forEach　（繰り返し）
								<c:forEach var="コレクション・配列の各要素を表す変数名" items="コレクション・配列を参照する変数名" > 
								--%>
								<c:forEach var="authority" items="${authorities}">
									<option value="${authority.getAuthorityCode()}">${authority.getAuthorityName()}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
				<!-- 編集ボタン -->
				<input type="submit" name="button" value="権限編集確定">
			</form>
			<input type="submit" value="メニュー画面へ" onclick="location.href='menu.jsp'" class="main__toMenu">
		</div>
	</main>
</body>
</html>