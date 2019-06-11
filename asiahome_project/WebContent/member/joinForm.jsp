<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/header.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/intro_content.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/board.css">
  <script type="text/javascript" src="<%=request.getContextPath()%>/script/lib/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/script/lib/jquery-migrate-1.2.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/script/header.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/script/aside.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/script/symantic.js"></script>
  <script type="text/javascript"
	src="<%=request.getContextPath()%>/script/member.js"></script>
  <title>아시아공동체 연구소 - 회원가입</title>
 </head>
 <body>
<%@include file="../header.jsp"%>
<div id="content_wrap">
	<div id="member">
		<h1>회원가입</h1>
		<hr>
		<form method="post" action="<%=request.getContextPath()%>/Member.do?command=join_action" name="frm">
			<table class="member_table">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" size="20" autofocus>*</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="userid" size="20">*
						<input type="hidden" name="reid" size="20">
						<input type="button" value="중복체크" onclick="idCheck()">
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pass" size="20">*</td>
				</tr>
				<tr height="30">
					<td width="120">비밀번호확인</td>
					<td><input type="password" name="pass_check" size="20">*</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" size="20"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="phone" size="20"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="submit" value="등록" onclick="return joinCheck()">
					&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
 	</div>
 </div> 
 <%@include file="../footer.jsp"%>
 </body>
</html>