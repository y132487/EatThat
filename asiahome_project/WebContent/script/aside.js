$(function(){
	var beforeEl;
	$("#c_aside>ul>li>a").on("mouseover focus",function(){
		if(beforeEl){
			beforeEl.children("img").attr("src",beforeEl.children("img").attr("src").replace("over.png","out.png"));
		}		
		$("img",this).attr("src",$("img",this).attr("src").replace("out.png","over.png"));
		beforeEl=$(this);
	});

	$("#c_aside>ul").on("mouseleave",function(){
		if(beforeEl){
			beforeEl.children("img").attr("src",beforeEl.children("img").attr("src").replace("over.png","out.png"));
		}
	});
});