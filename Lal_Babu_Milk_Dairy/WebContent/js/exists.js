function fun1(frm){
    let name = frm.opt.value;
	let date = frm.date.value;
	let quantity = frm.quan.value;
	let flag = true;

	document.getElementById("sp1").innerHTML = "";
	document.getElementById("sp2").innerHTML = "";
	document.getElementById("sp3").innerHTML = "";
	
	if (name == "" || name.length == 0 || name== "---select customer name---") {
		document.getElementById("sp1").innerHTML = "<b>*Please Choose The Customer Name..</b>";
		flag = false;
	}
	
	if (date == "" || date.length == 0) {
		document.getElementById("sp2").innerHTML = "<b>*Please Enter The Customer date.</b>";
		flag = false;
	}
	
	if (quantity == "" || quantity.length == 0) {
		document.getElementById("sp3").innerHTML = "<b>*Please Enter Milk quantity.</b>";
		flag = false;
	}

   
	return flag;
}