
//for button purpose

function fun1(status){
let type=status;
document.getElementById("b1").disabled=false;
document.getElementById("b2").disabled=false;
document.getElementById("b3").disabled=false;
document.getElementById("b4").disabled=false;
document.getElementById("pad").disabled=false;
document.getElementById("pad2").disabled=false;
document.getElementById("pad1").disabled=false;
document.getElementById("age").disabled=false;
document.getElementById("gender").disabled=false;
document.getElementById("gender1").disabled=false;
document.getElementById("state").disabled=false;
document.getElementById("pno1").disabled=true;


	if(type=="nep"){
		document.getElementById("b2").disabled=true;
		document.getElementById("b3").disabled=true;
		document.getElementById("b4").disabled=true;
		document.getElementById("pad2").disabled=true;
        document.getElementById("pad1").disabled=true;
	}
	else if(type=="dip"){
		document.getElementById("b1").disabled=true;
		document.getElementById("b3").disabled=true;
		document.getElementById("b4").disabled=true;
		document.getElementById("pad2").disabled=true;
        document.getElementById("pad").disabled=true;
        document.getElementById("age").disabled=true;
        document.getElementById("gender").disabled=true;
        document.getElementById("gender1").disabled=true;
        document.getElementById("state").disabled=true;
	}
	else if(type=="dep"){
	    document.getElementById("b1").disabled=true;
	    document.getElementById("b2").disabled=true;
	    document.getElementById("b4").disabled=true;
	    document.getElementById("pad").disabled=true;
        document.getElementById("pad1").disabled=true;
	    document.getElementById("age").disabled=true;
        document.getElementById("gender").disabled=true;
        document.getElementById("gender1").disabled=true;
        document.getElementById("state").disabled=true;
	}
	else if(type=="edit"){
	 document.getElementById("b1").disabled=true;
	 document.getElementById("b2").disabled=true;
	 document.getElementById("b3").disabled=true;
	 document.getElementById("pad").disabled=true;
     document.getElementById("pad1").disabled=true;
     document.getElementById("pad2").disabled=true;
	 document.getElementById("pno1").disabled=false;
	}
	}//close fun1() function
	
	
	
	function check(frm) {
	let name = frm.pname.value;
	let age = frm.page.value;
	let state = frm.state.value;
	let gender = frm.pgen.value;
	let adate=frm.pad.value;
	let didate=frm.pad1.value;
	let dedate=frm.pad2.value;
	let pstat=frm.pstat.value;
	let mob=frm.pmob.value;
	let pno=frm.pno.value;
	let flag = true;

	document.getElementById("sp1").innerHTML = "";
	document.getElementById("sp2").innerHTML = "";
	document.getElementById("sp3").innerHTML = "";
	document.getElementById("sp4").innerHTML = "";
	document.getElementById("sp5").innerHTML = "";
	document.getElementById("sp6").innerHTML = "";
	document.getElementById("sp7").innerHTML = "";
	document.getElementById("sp8").innerHTML = "";
	document.getElementById("sp9").innerHTML = "";
	document.getElementById("sp10").innerHTML = "";
	if (name == "" || name.length == 0) {
		document.getElementById("sp1").innerHTML = "<b>*Please Enter The Patient Name.</b>";
		flag = false;
	}

	else if (name.length < 5 || name.length > 15) {
		document.getElementById("sp1").innerHTML = "*<b>Please Enter The Min-5 Max-15 Of Char</b>";
		flag = false;
	}
	
	if(document.getElementById("age").disabled==false){
	if (age == "" || age.length == 0) {
		document.getElementById("sp2").innerHTML = "<b>*Please Enter The Patient Age.</b>";
		flag = false;
	} else if (age < 1 || age > 125) {
		document.getElementById("sp2").innerHTML = "<b>*Please Enter The Age Between Of 1-125</b>";
		flag = false;
	} else if (isNaN(age)){
		document.getElementById("sp2").innerHTML = "<b>*Please Enter Only Number In Age.</b>";
		flag = false;
	}
	}

    if(document.getElementById("gender").disabled==false || document.getElementById("gender1").disabled==false){
    if ( gender != "F" && gender!="M") {
		document.getElementById("sp3").innerHTML = "<b>*Please Choose Patient Gender.</b>";
		flag = false;
	}
	}
	
	if(mob=="" || mob.length==0){
	    document.getElementById("sp9").innerHTML="<b>*Please Enter Patient Mobile Number</b>";
	    flag = false; 
	} 
	else if(mob.length<10){
	    document.getElementById("sp9").innerHTML="<b>*Please Enter Patient Mobile Number Of 10 Digit</b>";
	    flag =false;
	}
	
	if(document.getElementById("state").disabled==false){
	if (state == "------Select State------") {
		document.getElementById("sp4").innerHTML = "<b>*Please Choose Patient Home State.</b>";
		flag = false;
	}
	}
	
	if(pstat=="" || pstat.length==0){
	    document.getElementById("sp5").innerHTML="<b>*Please Choose Patient Status</b>"; 
	    flag = false;
	} 
	
	
	if(document.getElementById("pad").disabled==false){
	if(adate=="" || adate.length==0){
	    document.getElementById("sp6").innerHTML="<b>*Please Choose Patient Admit Date</b>"; 
	    flag=false;
	} 
	}
	
	if(document.getElementById("pad1").disabled==false){
	if(didate=="" || didate.length==0){
	    document.getElementById("sp7").innerHTML="<b>*Please Choose Patient Discharged Date</b>";
	    flag = false; 
	} 
	}
	
	if(document.getElementById("pad2").disabled==false){
	if(dedate=="" || dedate.length==0){
	    document.getElementById("sp8").innerHTML="<b>*Please Choose Patient Death Date</b>"; 
	    flag = false;
	} 
	}
	
	if(document.getElementById("pno1").disabled==false){
	if (pno=="" || pno.length==0) {
		document.getElementById("sp10").innerHTML = "<b>*Please Enter Patient-Number.</b>";
		flag = false;
	}
	}
	//frm.hide.value="false";
	return flag;
}
