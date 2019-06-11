<%@ page import="com.noticeboard.dao.*" %>
<%@ page import="com.noticeboard.dto.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	NoticeBoardDAO dao=NoticeBoardDAO.getInstance();
	List<NoticeBoardVO> list=dao.selectAllBoards(1, 5);	
	request.setAttribute("boardList",list);
%>

<div>
	<table style=TABLE-layout:fixed id="main_notice">
		<tr class="list_border">
			<th>공지사항</th>
			<th><a href="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_list?1">더보기</a></th>
		</tr>
		<tr>
			<c:forEach var="board" items="${boardList}" begin="0" end="4">
				<tr class="list_border">
					<td style="text-align:left;"><div style="width:200px; overflow:hidden; text-overflow:ellipsis;"><nobr><a href="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_view&num=${board.num}&userid=${loginUser.userid}">${board.title}</a></nobr></div></td>
					<fmt:setLocale value="ko_kr"></fmt:setLocale>
					<td><fmt:formatDate value="${board.writedate }" type="date" dateStyle="short"/></td>
				</tr>
			</c:forEach>
		</tr>
	</table>

</div>