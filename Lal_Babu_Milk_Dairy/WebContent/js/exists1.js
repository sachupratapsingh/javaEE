function fun1(frm){
    let name = frm.opt.value;
	let allmonth = frm.allmont.value;
	let month = frm.mon.value;
	let flag = true;

	document.getElementById("sp1").innerHTML = "";
	document.getElementById("sp2").innerHTML = "";
	document.getElementById("sp3").innerHTML = "";
	
	if (name == "" || name.length == 0 || name== "---select customer name---") {
		document.getElementById("sp1").innerHTML = "<b>*Please Choose The Customer Name..</b>";
		flag = false;
	}
	
	if (month == "" || month.length == 0 ) {
		document.getElementById("sp2").innerHTML = "<b>*Please Enter The Customer month.</b>";
		flag = false;
	}
	
	if (allmonth == "" || allmonth.length == 0) {
		document.getElementById("sp3").innerHTML = "<b>*Please Enter Milk allmonth.</b>";
		flag = false;
	}

   
	return flag;
}