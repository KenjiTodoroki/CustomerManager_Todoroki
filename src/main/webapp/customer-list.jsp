<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List, model.entity.CustomerBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客一覧</title>
<link rel="stylesheet" href="assets/stylesheets/customer-list.css">
</head>
<body>
	<%-- 
	c:import	(外部ファイルをインポート)
	<c:import url="リソースパス" ・・・・ />で外部ファイル呼び出し 
	--%>
	<c:import url="header.jsp"/>
	<main>
		<div class="main">
			<h1>顧客一覧</h1>
			<form action="customer-search" method="post">
				<input type="text" name="searchWord">
				<button type="submit" name="button" value="検索">検索</button>
			</form>
			<%-- 
			c:choose　（複数の条件分岐）
			<c:choose>
    			<c:when test="条件式">条件に一致した場合の処理</c:when>
    			<c:when test="条件式">条件に一致した場合の処理</c:when>
    			<c:otherwise>全ての条件に一致しなかった場合の処理</c:otherwise>
			</c:choose> 
			--%>
			<c:choose>
				<c:when test="${noCustomer != null}">
					<!-- 検索結果がなかった時に表示する -->
					<p class="main__noCustomer">${noCustomer}</p>
				</c:when>
				<c:when test="${errorMessage != null}">
					<!-- エラーメッセージを表示する -->
					<p class="main__errorMessage">${errorMessage}</p>
				</c:when>
				<c:otherwise>
					<!-- 上記2つの条件に該当しなかった場合(通常) -->
					<table class="main__table">
						<c:choose>
							<c:when test="${customerList != null && customerList.size() > 0}">
								<!-- 全体の顧客一覧を見てる時に表示する部分 -->
								<thead>
									<tr class="main__tableRow">
										<th class="main__tableHeader">顧客ID</th>
										<th class="main__tableHeader">氏名</th>
										<th class="main__tableHeader">かな</th>
										<th class="main__tableHeader">性別</th>
										<th class="main__tableHeader"></th>
									</tr>
								</thead>
								<%-- 
								c:forEach　（繰り返し）
								<c:forEach var="コレクション・配列の各要素を表す変数名" items="コレクション・配列を参照する変数名" > 
								--%>
								<c:forEach var="customer" items="${customerList}">
									<tbody>
										<tr class="main__tableRow">
											<td class="main__tableData">${customer.getCustomerId()}</td>
											<td class="main__tableData">${customer.getCustomerName()}</td>
											<td class="main__tableData">${customer.getCustomerNameKana()}</td>
											<td class="main__tableData">${customer.getGender()}</td>
											<td class="main__tableData">
												<form action="customer-detail" method="post">
													<input type="hidden" name="customerId" value="${customer.getCustomerId()}">
													<input type="submit" name="button" value="詳細">
												</form>
											</td>
										</tr>
									</tbody>
								</c:forEach>
							</c:when>
							<c:when test="${customerSearch != null && customerSearch.size() > 0}">
								<!-- 検索した時に表示する部分 -->
								<thead>
									<tr class="main__tableRow">
										<th class="main__tableHeader">顧客ID</th>
										<th class="main__tableHeader">氏名</th>
										<th class="main__tableHeader">かな</th>
										<th class="main__tableHeader">性別</th>
										<th class="main__tableHeader"></th>
									</tr>
								</thead>
								<c:forEach var="customer" items="${customerSearch}">
									<tbody>
										<tr class="main__tableRow">
											<td class="main__tableData">${customer.getCustomerId()}</td>
											<td class="main__tableData">${customer.getCustomerName()}</td>
											<td class="main__tableData">${customer.getCustomerNameKana()}</td>
											<td class="main__tableData">${customer.getGender()}</td>
											<td class="main__tableData">
												<form action="customer-detail" method="post">
													<input type="hidden" name="customerId" value="${customer.getCustomerId()}">
													<input type="submit" name="button" value="詳細">
												</form>
											</td>
										</tr>
									</tbody>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</c:otherwise>
			</c:choose>
			<input type="submit" value="メニュー画面へ" onclick="location.href='menu.jsp'" class="main__toMenu">
		</div>
	</main>
</body>
</html>