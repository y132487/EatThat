<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 중복 확인</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/member.js"></script>
</head>
<body>
<h2>아이디 중복 확인</h2>
<form action="<%=request.getContextPath()%>/Member.do?command=idcheck" method="post" name="frm">
아이디 <input type="text" name="userid">
		<input type="submit" value="중복체크" >
	<br>
	<c:if test="${result==1 }">
		<script type="text/javascript">
			opener.document.frm.userid.value="";
		</script>
		<input type="text" name="nonchecked" value="${userid}" readonly>는 이미 사용 중인 아이디입니다.
	</c:if>
	<c:if test="${result==-1 }">
		<input type="text" name="checked" value="${userid}" readonly>는 사용 가능한 아이디입니다.
		<input type="button" value="사용" class="cancel" onclick="idok()">
	</c:if>
</form>
</body>
</html>