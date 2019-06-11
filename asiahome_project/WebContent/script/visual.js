$(function(){
  /*터치 슬라이드 비쥬얼 영역*/
	window.mySwipe=$('#mySwipe').Swipe({
		auto:3000,
		continuous:true,
		callback:function(index,element){
			$(".touch_bullet .active")
			.attr("src",$(".touch_bullet .active").attr("src").replace("on.png","off.png"))
			.removeClass("active");

			$(".touch_bullet img").eq(index)
			.attr("src",$(".touch_bullet img").eq(index).attr("src").replace("off.png","on.png")).addClass("active");
		}
	}).data('Swipe');
    
    
  /*비쥬얼 이전, 다음 버튼*/
	$(".touch_left_btn a").on("click",function(){
		mySwipe.prev();
		return false;
	});
    $(".touch_right_btn a").on("click",function(){
		mySwipe.next();
		return false;
	});
});