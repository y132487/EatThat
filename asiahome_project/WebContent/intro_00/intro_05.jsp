<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <link rel="stylesheet" type="text/css" href="../css/reset.css">
  <link rel="stylesheet" type="text/css" href="../css/header.css">
  <link rel="stylesheet" type="text/css" href="../css/intro_content.css">
  <link rel="stylesheet" type="text/css" href="../css/footer.css">
  <script type="text/javascript" src="../script/lib/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="../script/lib/jquery-migrate-1.2.1.min.js"></script>
  <script type="text/javascript" src="../script/header.js"></script>
  <script type="text/javascript" src="../script/aside.js"></script>
  <script type="text/javascript" src="../script/symantic.js"></script>
  <title>아시아공동체 연구소 - 연구소 소개</title>
 </head>
 <body>
 <%@include file="../header.jsp"%>
 <div id="content_wrap">
 <div id="content1">
	<section id="submain_section">
		<img src="../images/asiansubmain01.png">
	</section>
 </div>

 <div id="content2">
	<section id="c_section">
		<h1>오시는길</h1>
		<hr>
		<article class="c_article" id="c_article1">
			<img src="../images/map.jpg">
		</article>
	</section>
	<aside id="c_aside">
		<img src="../images/intro_title.png">
		<ul>			
			<a href="intro_01.jsp"><li>인사말</li></a>
			<a href="intro_02.jsp"><li>아시아공동체란</li></a>
			<a href="intro_03.jsp"><li>연혁</li></a>
			<a href="intro_04.jsp"><li>조직도</li></a>
			<a href="intro_05.jsp"><li>오시는길</li></a>
		</ul>
	</aside>
 </div>
 </div>
 <%@include file="../footer.jsp"%>
 </body>
</html>
