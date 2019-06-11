<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table id="board">
	<tr class="write_border">
		<th id="view_writer">작성자</th>
		<td>${board.userid }</td>
	</tr>
	<tr class="write_border">
		<th>날짜</th>
		<fmt:setLocale value="ko_kr"></fmt:setLocale>
		<td><fmt:formatDate value="${board.writedate }" type="both" dateStyle="full" timeStyle="long"/></td>
		<th id="view_readcount">조회</th>
		<td id="view_readcounting">${board.readcount }</td>
	</tr>
	<tr class="write_border">
		<th>제목</th>
		<td colspan="3">${board.title }</td>
	</tr>
	<tr class="write_border">
		<th>내용</th>
		<td colspan="3"><pre>${board.content }</pre></td>
	</tr>
	<tr class="write_border">
		<th>파일</th>
		<td><a href="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_download" target="_blank">${board.fileurl }</a></td>
		<c:set var="fileName"  scope="session" value="${board.fileurl}"/>
	</tr>
</table>
<br>
<br>
<div id="buttons">
<c:set var="result" value="${result}" />
<c:if test="${result==1}">
<input type="button" value="수정" onclick="location.href='<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_update_form&num=${board.num }'">
<input type="button" value="삭제" onclick="location.href='<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_delete&num=${board.num }'">
</c:if>
<input type="button" value="목록" 	onclick="location.href='<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_list?1'">
</div>