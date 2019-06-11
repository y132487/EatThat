<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form method="post" action="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_search?1?true">
<table id="board">
	<tr class="list_border">
		<td colspan="5" style="border:white; text-align:right">
			<c:choose>
				<c:when test="${empty loginUser}">
					게시물을 작성하려면 <a href="<%=request.getContextPath()%>/Member.do?command=login_form">로그인</a>을 해야합니다
				</c:when>
				<c:otherwise>
					<a href="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_write_form">글쓰기</a>
				</c:otherwise>
			</c:choose>			
		</td>
	</tr>
	<tr class="list_border">
		<th id="th_num">번호</th>
		<th id="th_title">제목</th>
		<th id="th_writer">작성자</th>
		<th id="th_readcount">조회</th>
		<th id="th_date">날짜</th>
	</tr>
	<c:forEach var="board" items="${boardList }">
		<tr class="list_border">
			<td>${board.num}</td>
			<td style="text-align:left"><a href="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_view&num=${board.num}&userid=${loginUser.userid}">${board.title}</a></td>
			<td>${board.userid}</td>
			<td>${board.readcount}</td>
			<fmt:setLocale value="ko_kr"></fmt:setLocale>
			<td><fmt:formatDate value="${board.writedate }" type="date" dateStyle="short"/></td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="5" style="text-align:center">
			<c:if test="${beginPage>10 }">
				<a href="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_search?${beginPage-1}?false">이전</a>
			</c:if>
			<c:forEach var="pageNum" begin="${beginPage }" end="${endPage }">
				<c:choose>
					<c:when test="${settedPageNum eq pageNum }">
						[${pageNum}]
					</c:when>
					<c:otherwise>
						<a href="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_search?${pageNum}?false">[${pageNum}]</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${endPage<totalPageCount }">
				<a href="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_search?${endPage+1}?false">다음</a>
			</c:if>
		</td>
	</tr>
	<tr>		
		<td colspan="5" style="text-align:center">
			<select name="search_option">
				<option value="userid">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="text" name="search_bar" value="${search_bar}"><input type="submit" value="검색">
		</td>
	</tr>
</table>
</form>