<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/header.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/intro_content.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css">
  <script type="text/javascript" src="<%=request.getContextPath()%>/script/lib/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/script/lib/jquery-migrate-1.2.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/script/header.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/script/aside.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/script/symantic.js"></script>
  <title>아시아공동체 연구소 - 커뮤니티</title>
 </head>
 <body>
<%@include file="../header.jsp"%>
<div id="content_wrap">
 <div id="content1">
	<section id="submain_section">
		<img src="<%=request.getContextPath()%>/images/asiansubmain05.png">
	</section>
 </div>

 <div id="content2">
	<section id="c_section">
		<h1>자유게시판</h1>
		<hr>
		<article class="c_article" id="c_article1">
			
		</article>
	</section>
	<aside id="c_aside">
		<img src="<%=request.getContextPath()%>/images/commu_title.png">
		<ul>			
			<a href="<%=request.getContextPath()%>/NoticeBoard.do?command=notice_board_list?1"><li>공지사항</li></a>
			<a href="<%=request.getContextPath()%>/commu_00/commu_02.jsp"><li>자유게시판</li></a>
			<a href="<%=request.getContextPath()%>/commu_00/commu_03.jsp"><li>Q/A</li></a>
		</ul>
	</aside>
 </div>
 </div>
 
 <%@include file="../footer.jsp"%>
 </body>
</html>
