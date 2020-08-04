       //javaScript-Code

		function fun2(value){
		let val=value;
		
		document.getElementById("patname1").disabled=true;
		document.getElementById("status1").disabled=true;
		document.getElementById("state1").disabled=true;
		if(value=="state"){
		 document.getElementById("state1").disabled=false;
				}
		else if(value=="name"){
		 document.getElementById("patname1").disabled=false;
				}
		else if(value=="status"){
		 document.getElementById("status1").disabled=false;
				}}
		
				
	//function2
	function check(frm) {
	let option1=frm.option.value;
	let name = frm.patname.value;
	let state = frm.state1.value;
	let status=frm.status.value;
	let flag = true;

	document.getElementById("sp1").innerHTML = "";
	document.getElementById("sp2").innerHTML = "";
	document.getElementById("sp3").innerHTML = "";
	document.getElementById("sp4").innerHTML = "";
	if(option1=="spby"){
	    document.getElementById("sp1").innerHTML="<b>**Please Choose Any One Option From Search BY Option</b>"; 
	    flag = false;
	} 
	if(document.getElementById("patname1").disabled==false){
	if (name == "-----SELECT PATIENT-NAME-----" ) {
		document.getElementById("sp3").innerHTML = "<b>**Please Select The Name.</b>";
		flag = false;
	}}
	
    if(document.getElementById("status1").disabled==false){
    if ( status=="-----SELECT PATIENT-STATUS-----") {
		document.getElementById("sp4").innerHTML = "<b>**Please Select The Status.</b>";
		flag = false;
	}}
	
	if(document.getElementById("state1").disabled==false){
	if (state == "-----SELECT STATE-----") {
		document.getElementById("sp2").innerHTML = "<b>**Please Select The State.</b>";
		flag = false;
	}}
	
	
	
	
	//frm.hide.value="false";
	return flag;
}

     //function 3rd

     function ondelete(){
     if(confirm("YOUR DATA IS GOING TO DELETED..."))
     return true;
     else 
     return false;
}
			