<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header_wrap">
 <header id="header">
 	<img src="<%=request.getContextPath()%>/images/asia_title_back.png" id="backImg">
	<hgroup id="h_title">
		<a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/images/asia_main_title.png"></a>
	</hgroup>
	<nav>
		<ul  id="h_inb">
			
			<c:choose>
				<c:when test="${empty loginUser}">
					<li><a href="<%=request.getContextPath()%>/Member.do?command=login_form">로그인</a></li>
					<li><a href="<%=request.getContextPath()%>/Member.do?command=join_form">회원가입</a></li>
				</c:when>
				<c:otherwise>
					<li>${loginUser.userid}님 환영합니다. <a href="<%=request.getContextPath()%>/Member.do?command=logout_action">로그아웃</a></li>	
					<li>	<a href="<%=request.getContextPath()%>/Member.do?command=edit_form&userid='${loginUser.userid}'">정보수정</a></li>		
				</c:otherwise>
			</c:choose>
			<li><a href="#">사이트맵</a></li>
		</ul>
	</nav>
	<nav>
		<ul id="h_gnb">
			<li>
				<a href="<%=request.getContextPath()%>/intro_00/intro_01.jsp"><img src="<%=request.getContextPath()%>/images/gnb1_out.png"></a>
				<ul class="gnb_sub01">
					<li><a href="<%=request.getContextPath()%>/intro_00/intro_01.jsp">·인사말</a></li>
					<li><a href="<%=request.getContextPath()%>/intro_00/intro_02.jsp">·아시아공동체란</a></li>
					<li><a href="<%=request.getContextPath()%>/intro_00/intro_03.jsp">·연혁</a></li>
					<li><a href="<%=request.getContextPath()%>/intro_00/intro_04.jsp">·조직도</a></li>
					<li><a href="<%=request.getContextPath()%>/intro_00/intro_05.jsp">·오시는길</a></li>
				</ul>
			</li>
			<li><a href="<%=request.getContextPath()%>/edu_00/edu_01.jsp"><img src="<%=request.getContextPath()%>/images/gnb2_out.png"></a>
			<ul class="gnb_sub02">
				<li><a href="<%=request.getContextPath()%>/edu_00/edu_01.jsp">·아시아공동체론 강좌</a></li>
				<li><a href="<%=request.getContextPath()%>/edu_00/edu_02.jsp">·J-BIT아카데미</a></li>
				<li><a href="<%=request.getContextPath()%>/edu_00/edu_03.jsp">·특별강연</a></li>
			</ul>
			</li>
			<li><a href="<%=request.getContextPath()%>/sch_00/sch_01.jsp"><img src="<%=request.getContextPath()%>/images/gnb3_out.png"></a>
			<ul class="gnb_sub03">
				<li><a href="<%=request.getContextPath()%>/sch_00/sch_01.jsp">·학술대회</a></li>
				<li><a href="<%=request.getContextPath()%>/sch_00/sch_02.jsp">·출판물</a></li>
				<li><a href="<%=request.getContextPath()%>/sch_00/sch_03.jsp">·J-GAP</a></li>
			</ul>
			</li>
			<li><a href="<%=request.getContextPath()%>/inter_00/inter_01.jsp"><img src="<%=request.getContextPath()%>/images/gnb4_out.png"></a>
			<ul class="gnb_sub04">
				<li><a href="<%=request.getContextPath()%>/inter_00/inter_01.jsp">·봉사활동</a></li>
				<li><a href="<%=request.getContextPath()%>/inter_00/inter_02.jsp">·니혼무라</a></li>
				<li><a href="<%=request.getContextPath()%>/inter_00/inter_03.jsp">·컨설팅업무</a></li>
			</ul>
			</li>
			<li><a href="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_list?1"><img src="<%=request.getContextPath()%>/images/gnb5_out.png"></a>
			<ul class="gnb_sub05">
				<li><a href="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_list?1">·공지사항</a></li>
				<li><a href="<%=request.getContextPath()%>/commu_00/commu_02.jsp">·자유게시판</a></li>
				<li><a href="<%=request.getContextPath()%>/commu_00/commu_03.jsp">·Q/A</a></li>
			</ul>
			</li>
		</ul>
	</nav>
 </header>
 </div>

