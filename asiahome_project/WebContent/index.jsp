<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <link rel="stylesheet" type="text/css" href="./css/reset.css">
  <link rel="stylesheet" type="text/css" href="./css/main_content.css">
  <link rel="stylesheet" type="text/css" href="./css/header.css">
  <link rel="stylesheet" type="text/css" href="./css/footer.css">
  <script type="text/javascript" src="script/lib/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="script/swipe.js"></script>
  <script type="text/javascript" src="js/jquery.bxslider.min.js"></script> 
  <script type="text/javascript" src="js/jquery-ui-1.10.4.custom.min.js"></script> 
  <script type="text/javascript" src="js/jquery.cookie.js"></script>  
  <script type="text/javascript" src="script/header.js"></script>  
  <script type="text/javascript" src="script/visual.js"></script>
  <script type="text/javascript" src="script/symantic.js"></script>
  <title>아시아공동체 메인페이지 - 환영합니다!</title>
</head>
<body>
<%@include file="header.jsp"%>
	<div id="content_wrap">
		<div id="content">
			<section id="c_section">
				<article class="c_article1">
					<div id="visual">
						<div id="mySwipe" class="swipe">
							<ul class="touch_banner swipe-wrap">
								<li><a href="#"><img src="images/asiamain1.png"></a></li>
								<li><a href="#"><img src="images/asiamain2.png"></a></li>
								<li><a href="#"><img src="images/asiamain3.png"></a></li>
							</ul>
						</div>
						<ul class="touch_bullet">
							<li><img src="images/visual_bullet_on.png" class="active"></li>
							<li><img src="images/visual_bullet_off.png" ></li>
							<li><img src="images/visual_bullet_off.png" ></li>
						</ul>
						<p class="touch_left_btn">
						<a href="#"><img src="images/visual_btn_left.png"></a>
						</p>
						<p class="touch_right_btn">
							<a href="#"><img src="images/visual_btn_right.png"></a>
						</p>
					</div>
				</article>
				<article class="c_article2">
					<jsp:include page="board/main/notice.jsp"/>
				</article>
				<article class="c_article2">
					<jsp:include page="board/main/notice.jsp"/>
				</article>
				<article class="c_article2">
					<img src="images/banner.jpg" usemap="#banner_map">
					<map id="banner_map" name="banner_map">
						<area shape="rect" coords="0,0,350,60" href="http://bufs.ac.kr/html/00_main/" target="_blank">
						<area shape="rect" coords="0,60,350,120" href="<%=request.getContextPath()%>/edu_00/edu_02.jsp">
					</map>
				</article>
				<br>
				<article class="c_article3">
					<img src="images/board2.png">
				</article>
			</section>
		 </div>
		</div>
	<%@include file="footer.jsp"%>
</body>
</html>
