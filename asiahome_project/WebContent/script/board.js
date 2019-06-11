function boardCheck(){
	if(document.frm.userid.value.length==0){
		alert("작성자를 입력하세요");
		return false;
	}
	if(document.frm.title.value.length==0){
		alert("제목을 입력하세요");
		return false;
	}
	return true;
}
