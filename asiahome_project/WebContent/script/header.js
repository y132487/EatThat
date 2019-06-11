$(function(){
	//gnb 메뉴----------------------------------------
	var beforeGNB;
	$("#h_gnb>li>a").on("mouseover focus",function(){
		if(beforeGNB){
			beforeGNB.children("img").attr("src",beforeGNB.children("img").attr("src").replace("over.png","out.png"));
		}
		$("#h_gnb ul:visible").slideUp("fast");
		$("img",this).attr("src",$("img",this).attr("src").replace("out.png","over.png"));
		$(this).next().stop().slideDown("fast");
		beforeGNB=$(this);
	});

	$("#h_gnb").on("mouseleave",function(){
		$("#h_gnb ul:visible").slideUp("fast");
		if(beforeGNB){
			beforeGNB.children("img").attr("src",beforeGNB.children("img").attr("src").replace("over.png","out.png"));
		}
	});

	// 로그인 폼---------------------------------------------
	$("#login_btn").on("click",function(){
		$("#login_form").animate({top:"30px"},500);
		return false;
	});

	$("#login_close_btn").on("click",function(){
		$("#login_form").animate({top:"-500px"},500);
		return false;
	});

});