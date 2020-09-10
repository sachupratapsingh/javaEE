
function fun1(frm){
    let name = frm.cname.value;
	let address = frm.caddress.value;
	let rate = frm.rate.value;
	let mob=frm.mob.value;
	let slot=frm.slot.value;
	let flag = true;

	document.getElementById("sp1").innerHTML = "";
	document.getElementById("sp2").innerHTML = "";
	document.getElementById("sp3").innerHTML = "";
	document.getElementById("sp4").innerHTML = "";
	document.getElementById("sp5").innerHTML = "";
	if (name == "" || name.length == 0) {
		document.getElementById("sp1").innerHTML = "<b>*Please Enter The Customer Name..</b>";
		flag = false;
	}

	else if (name.length < 5 || name.length > 15) {
		document.getElementById("sp1").innerHTML = "*<b>Please Enter The Min-5 Max-15 Of Char</b>";
		flag = false;
	}
	
	if (address == "" || address.length == 0) {
		document.getElementById("sp2").innerHTML = "<b>*Please Enter The Customer Address.</b>";
		flag = false;
	}
	
	if (rate == "" || rate.length == 0) {
		document.getElementById("sp4").innerHTML = "<b>*Please Enter Milk Rate.</b>";
		flag = false;
	}

   if (mob == "" || mob.length == 0) {
		document.getElementById("sp3").innerHTML = "<b>*Please Enter Mob No.</b>";
		flag = false;
	}
	
	if (slot == "" || slot.length == 0 || slot=="---CHOOSE SLOT---") {
		document.getElementById("sp5").innerHTML = "<b>*Please Choose The Slot.</b>";
		flag = false;
	}
	return flag;
}