function check_Login() {
	var username = document.getElementsByName("username")[0].value;
	var password = document.getElementsByName("password")[0].value;
	
	if (username === "" && password === ""){
		alert("Vui lòng nhập username và password");
		return false;
	}
	if (username === ""){
		alert("Vui lòng nhập username");
		return false;
	}
	if (password === ""){
		alert("Vui lòng nhập password");
		return false;
	}
	return true;
}