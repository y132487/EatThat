<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/member.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/reset.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/intro_content.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/footer.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/board.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/lib/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/lib/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/header.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/aside.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/member.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/symantic.js"></script>
<title>아시아공동체 연구소 - 로그인</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<div id="content_wrap">
		<div id="member">
			<h1>로그인</h1>
			<hr>
			<form method="post"
				action="<%=request.getContextPath()%>/Member.do?command=login_action"
				name="frm">
				<table class="member_table">
					<table align="center" height="40" width="200" border="0"
						style="border: solid 4px #050099; margin-top: 4%"  class="member_table">
						<tr>
							<td><input type="text" name="userid" value="${userid }" placeholder="아이디" style="height: 40px; width: 200px;"></td>
						</tr>
					</table>
					<table align="center" height="40" width="200" border="0"
						style="border: solid 4px #050099; margin-top: 1%" class="member_table">
						<tr>
							<td><input type="password" name="pass" value=""
								placeholder="비밀번호" style="height: 40px; width: 200px"></td>
						</tr>
					</table>

					<table align="center" height="40" style="margin-top: 1%" class="member_table">
						<tr>
							<td><input type="submit" name="login" value="로그인"
								style="height: 50px; width: 200px; background-color: #050099; color: white; font-size: 16px; border: solid 1px #050099;"
								onclick="return loginCheck()"></td>
						</tr>
					</table>

					<table align="center" height="0" width="440" border="1"
						style="border: solid 1px #050099; margin-top: 2%" class="member_table">
					</table>

					<table align="center" height="50" style="margin-top: 1%" class="member_table">
						<tr>
							<td><a style="text-decoration: none; color:#000000" href="<%=request.getContextPath()%>/Member.do?command=join_form"">회원가입&nbsp;&nbsp;&nbsp;</td>
							<td><input type="checkbox" name="idRemember">아이디기억</td>
						</tr>
					</table>
				</table>
			</form>
			<div align="center">${message}</div>
		</div>
	</div>
	<%@include file="../footer.jsp"%>
</body>
</html>
