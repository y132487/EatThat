function loginCheck(){
	if(document.frm.userid.value.length==0){
		alert("아이디를 써주세요");
		frm.userid.focus();
		return false;
	}
	if(document.frm.pass.value==""){
		alert("암호는 반드시 입력해야 합니다");
		frm.pass.focus();
		return false;
	}
	return true;	
}

function idCheck(){
	if(document.frm.userid.value==""){
		alert("아이디를 입력해 주세요");
		document.frm.userid.focus();
		return;
	}
	if(document.frm.userid.value.length<4){
		alert("아이디는 4글자 이상이어야 합니다")
		document.frm.userid.focus();
		return;
	}
	var url="Member.do?command=idcheck&userid="+document.frm.userid.value;
	window.open(url,"_blank_1","toolbar=no, menubar=no,scrollbars=yes,resizable=no,width=450,height=200");
}

function setCheckId(){
	document.frm.userid.value=document.frm.userid.value;
}

function idok(){
	opener.frm.userid.value=frm.checked.value;
	opener.frm.reid.value=frm.checked.value;
	self.close();
}


function joinCheck(){
	if(document.frm.name.value.length==0){
		alert("이름을 써주세요");
		frm.name.focus();
		return false;
	}
	if(document.frm.userid.value.length==0){
		alert("아이디를 써주세요");
		frm.userid.focus();
		return false;
	}
	if(document.frm.userid.value.length<4){
		alert("아이디는 4글자 이상이어야 합니다");
		frm.userid.focus();
		return false;
	}
	if(document.frm.pass.value==""){
		alert("암호는 반드시 입력해야 합니다");
		frm.pass.focus();
		return false;
	}
	if(document.frm.pass.value!=document.frm.pass_check.value){
		alert("암호가 일치하지 않습니다");
		frm.pass.focus();
		return false;
	}
	if(document.frm.reid.value.length==0){
		alert("중복 체크를 하지 않았습니다");
		frm.userid.focus();
		return false;
	}
	return true;
}

function deleteMember(){
	var url="Member.do?command=quit_action&userid="+document.frm.userid.value;
	location.href=url;
}