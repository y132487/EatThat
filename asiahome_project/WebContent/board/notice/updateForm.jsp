<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form method="post" action="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_update&num=${board.num}" name="frm" enctype="multipart/form-data">
	<table id="board">
		<tr class="write_border">
			<th id="view_writer">작성자</th>
			<td><input type="text" size="12" name="userid"
				value="${board.userid }" readonly></td>
		</tr>
		<tr class="write_border">
			<th>제목</th>
			<td><input type="text" size="70" name="title"
				value="${board.title }"></td>
		</tr>
		<tr class="write_border">
			<th>내용</th>
			<td><textarea rows="15" cols="70" name="content">${board.content }</textarea></td>
		</tr>
		<tr class="write_border">
			<th>파일</th>
			<td><input type="file" name="fileurl">${board.fileurl}</td>
		</tr>
	</table>
	<br><br> 
	<div id="buttons">
	<input type="submit" value="등록">
	<input type="reset" value="다시작성"> 
	<input type="button" value="목록" onclick="location.href='<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_list?1'">
	</div>
</form>