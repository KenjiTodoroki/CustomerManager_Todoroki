<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>
<link rel="stylesheet" href="assets/stylesheets/logout.css">
</head>
<body>
	<%-- タグライブラリを使用していないので、${}の記述のみ --%>
	<h1>${logoutMessage}</h1>
	<input type="submit" value="ログイン画面へ" onclick="location.href='login.jsp'">
</body>
</html>