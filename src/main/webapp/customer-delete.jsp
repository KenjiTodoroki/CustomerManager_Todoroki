<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List, model.entity.CustomerBean, model.entity.AreaBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認</title>
<link rel="stylesheet" href="assets/stylesheets/customer-detail-delete.css">
</head>
<body>
	<%-- 
	c:import	(外部ファイルをインポート)
	<c:import url="リソースパス" ・・・・ />で外部ファイル呼び出し 
	--%>
	<c:import url="header.jsp" />
	<main>
		<div class="main">
			<h1>顧客を削除してよろしいですか？</h1>
			<table class="main__table">
				<thead>
					<tr class="main__tableRow">
						<th class="main__tableHeader">顧客ID</th>
						<th class="main__tableHeader">氏名</th>
						<th class="main__tableHeader">かな</th>
						<th class="main__tableHeader">郵便番号</th>
						<th class="main__tableHeader">地区</th>
						<th class="main__tableHeader">性別</th>
						<th class="main__tableHeader">生年月日</th>
						<th class="main__tableHeader">電話番号</th>
						<th class="main__tableHeader">登録日時</th>
						<th class="main__tableHeader">更新日時</th>
					</tr>
				</thead>
				<%-- 
				c:forEach　（繰り返し）
				<c:forEach var="コレクション・配列の各要素を表す変数名" items="コレクション・配列を参照する変数名" > 
				--%>
				<c:forEach var="customer" items="${customers}">
					<tbody>
						<tr>
							<td class="main__tableData">${customer.getCustomerId()}</td>
							<td class="main__tableData">${customer.getCustomerName()}</td>
							<td class="main__tableData">${customer.getCustomerNameKana()}</td>
							<td class="main__tableData">${customer.getPostCode()}</td>
							<c:forEach var="area" items="${areas}">
								<td class="main__tableData">${area.getAreaName()}</td>
							</c:forEach>
							<td class="main__tableData">${customer.getGender()}</td>
							<td class="main__tableData">${customer.getBirthday()}</td>
							<td class="main__tableData">${customer.getPhoneNumber()}</td>
							<td class="main__tableData">${customer.getInsertDatetime()}</td>
							<td class="main__tableData">${customer.getUpdateDatetime()}</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			<form action="customer-delete" method="post" class="main__delete">
				<input type="submit" name="button" value="削除確定">
				<input type="hidden" name="customerId" value="${customerId}">
			</form>
			<form action="customer-detail" method="post">
				<input type="submit" name="button" value="詳細画面へ">
				<input type="hidden" name="customerId" value="${customerId}">
			</form>
		</div>
	</main>
</body>
</html>