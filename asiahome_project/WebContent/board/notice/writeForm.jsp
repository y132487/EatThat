<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form method="post" action="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_write" name="frm" enctype="multipart/form-data">
	<%-- <input type="hidden" name="command" value="notice_board_write">--%>
	<table id="board">
		<tr class="write_border">
			<th>작성자</th>
			<td><input type="text" name="userid" value="${loginUser.userid}" readonly></td>
		</tr>
		<tr class="write_border">
			<th>제목</th>
			<td><input type="text" size="70" name="title">*필수</td>
		</tr>
		<tr class="write_border">
			<th>내용</th>
			<td><textarea cols="70" rows="15" name="content"></textarea></td>
		</tr>
		<tr class="write_border">
			<th>파일</th>
			<td><input type="file" name="fileurl">
		</tr>
	</table>
	<br> <br>
	<div id="buttons">
	<input type="submit" value="등록">
	<input type="reset" value="다시작성">
	<input type="button" value="목록" onclick="location.href='<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_list?1'">
	</div>
</form>
