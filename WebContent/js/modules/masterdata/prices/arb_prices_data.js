//var pinNum=document.getElementById("pinNO").value;
if(!pinNum) {
	document.getElementById("contentDiv").style.display="none";
	document.getElementById("displayDiv").style.display="block";
}else {
	document.getElementById("displayDiv").style.display="none";
	document.getElementById("contentDiv").style.display="block";
}

//Construct Category Type html
ctdatahtml = "<OPTION VALUE='0'>SELECT</OPTION>";
if(cat_types_data.length>0) {
	for(var z=0; z<cat_types_data.length; z++){
		if(cat_types_data[z].deleted == 0){
		ctdatahtml=ctdatahtml+"<OPTION VALUE='"+cat_types_data[z].id+"'>"
		+getARBType(cat_types_data[z].prod_code)+" - "+cat_types_data[z].prod_brand+" - "+cat_types_data[z].prod_name
		+"</OPTION>";
		}
	}
}

//Construct Month html
mvdatahtml = "<OPTION VALUE='0'>SELECT</OPTION>";
for(var z=0; z<months.length; z++){
	mvdatahtml=mvdatahtml+"<OPTION VALUE='"+z+"'>"+months[z]+"</OPTION>";
}

yvdatahtml = "<OPTION VALUE='-1'>SELECT</OPTION>";
for(var z=0; z<years.length; z++){
	yvdatahtml=yvdatahtml+"<OPTION VALUE='"+z+"'>"+years[z]+"</OPTION>";
}
var checkNum=checkDisplay;
if(checkNum=="1"){
	var tbody = document.getElementById('page_data_table_body');
	document.getElementById("myModalarbPin").style="none";
	var tbody = document.getElementById('page_data_table_body');
	for(var f=page_data.length-1; f>=0; f--) {
		var tblRow = tbody.insertRow(-1);
		tblRow.style.height="20px";
		tblRow.align="left";
		var	spd = fetchARBProductDetails(cat_types_data, page_data[f].arb_code);
		tblRow.innerHTML = "<tr>"+
			"<td>"+ spd +"</td>"+
			"<td align='center'>"+ page_data[f].rsp +"</td>"+
			"<td align='center'>"+ page_data[f].base_price +"</td>"+
			"<td align='center'>"+ page_data[f].cgst_price +"</td>"+
			"<td align='center'>"+ page_data[f].sgst_price +"</td>"+			
			"<td align='center'>"+ months[page_data[f].montha] +"</td>"+
			"<td align='center'>"+ years[page_data[f].yeara] +"</td>"+
			"<td align='center'><img src='images/delete.png' onclick='deleteItem("+page_data[f].id+")'></td>"+
			"</tr>";
	};
}else {
	var tbody = document.getElementById('page_data_table_body');
	for(var f=page_data.length-1; f>=0; f--){
		var tblRow = tbody.insertRow(-1);
		tblRow.style.height="20px";
		tblRow.align="left";
		var spd = fetchARBProductDetails(cat_types_data, page_data[f].arb_code);
		tblRow.innerHTML = "<tr>"+
			"<td>"+ spd +"</td>"+
			"<td align='center'>"+ page_data[f].rsp +"</td>"+
			"<td align='center'>"+ page_data[f].base_price +"</td>"+
			"<td align='center'>"+ page_data[f].cgst_price +"</td>"+
			"<td align='center'>"+ page_data[f].sgst_price +"</td>"+			
			"<td align='center'>"+ months[page_data[f].montha] +"</td>"+
			"<td align='center'>"+ years[page_data[f].yeara] +"</td>"+
			"<td align='center'><img src='images/delete.png' onclick='deleteItem("+page_data[f].id+")'></td>"+
			"</tr>";
	};
}
	


function addRow() {
	document.getElementById("calc_data").disabled = false;	
    $("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');

	if(arb_data.length<1) {
		document.getElementById("dialog-1").innerHTML = "Please enter PRODUCT in BLPG/ARB/NFR MASTER";
		alertdialogueWithCollapse();
		//alert("Please enter PRODUCT in BLPG/ARB/NFR MASTER");
	}else {
		document.getElementById("save_data").disabled = true;
		document.getElementById('savediv').style.display="inline";
			
		var x = document.getElementById('myDIV');
		if (x.style.display === 'none')
			x.style.display = 'block';
		
		var trcount = document.getElementById('page_add_table_body').getElementsByTagName('tr').length;
		if(trcount>0){
			var trv=document.getElementById('page_add_table_body').getElementsByTagName('tr')[trcount-1];
			var saddv=trv.getElementsByClassName('sadd');
			var eaddv=trv.getElementsByClassName('eadd');
			
			var res=checkRowData(saddv,eaddv);
			if(res == false){
				document.getElementById("dialog-1").innerHTML = "Please enter all the values in current row,calculate and then add next row";
				alertdialogue();
				//alert("Please enter all the values in current row,calculate and then add next row");
				return;
			}
		}
		var ele = document.getElementsByClassName("bpc");
		if(ele.length < 4) {
			var tbody = document.getElementById('page_add_table_body');
			var newRow = tbody.insertRow(-1);

			var a = newRow.insertCell(0);
			var b = newRow.insertCell(1);
			var c = newRow.insertCell(2);
			var d = newRow.insertCell(3);
			var e = newRow.insertCell(4);
			var f = newRow.insertCell(5);
			var g = newRow.insertCell(6);
			var h = newRow.insertCell(7);
			
			a.innerHTML = "<td valign='top' height='4' align='center'><select name='pid' id='pid' class='form-control input_field select_dropdown sadd pid tp freez'>"+ ctdatahtml + "</select></td>";
			b.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='rsp' id='rsp' class='form-control input_field bpc freez eadd tp' placeholder='MRP' maxlength='7'></td>";
			c.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='bp' id='bp' class='form-control input_field eadd' placeholder='BASIC PRICE' readonly='readonly' style='background-color:#F3F3F3;'></td>";
			d.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='sgst' id='sgst'  class='form-control input_field eadd' placeholder='SGST' readonly='readonly' style='background-color:#F3F3F3;'></td>";
			e.innerHTML = "<td valign='top' height='4' align='center'><input type=text name='cgst' id='cgst' class='form-control input_field eadd' placeholder='CGST' readonly='readonly' style='background-color:#F3F3F3;'></td>";	
			f.innerHTML = "<td valign='top' height='4' align='center'><select name='mon' id='mon' class='form-control input_field select_dropdown  sadd'>"+ mvdatahtml + "</SELECT></td>";
			g.innerHTML = "<td valign='top' height='4' align='center'><select name='yr' id='yr' class='form-control input_field select_dropdown  sadd'>"+ yvdatahtml + "</SELECT></td>";
			h.innerHTML = "<td valign='top' height='4' align='center'><img src='images/delete.png' onclick='doRowDelete(this)'></td>";
		}else {
			document.getElementById("dialog-1").innerHTML = "Please save the data and add again.";
			alertdialogue();
			//alert("Please save the data and add again.");
			return;
		}
	}
}

function saveData(obj) {
	var formobj = document.getElementById('page_data_form');
	var ems = "";

	if(document.getElementById("pid") != null) {
		var elements = document.getElementsByClassName("bpc");
		if (elements.length == 1) {
			var epid = formobj.pid.selectedIndex;
			var ersp = formobj.rsp.value.trim();
			var esgst = formobj.sgst.value;
			var ecgst = formobj.cgst.value;
			var ebp = formobj.bp.value;
			var emon = formobj.mon.selectedIndex;
			var eyr = formobj.yr.selectedIndex;
			var eyrv = formobj.yr.options[eyr].value;
			var pidv = formobj.pid.value;
			var gstpercent=findGSTPercent(ersp, pidv);
			
			ems = validateArbEntries(epid, ersp, emon, eyr,eyrv, esgst, ecgst,gstpercent,ebp);
		}else if (elements.length > 1) {
			for (var i = 0; i < elements.length; i++) {
				var epid = formobj.pid[i].selectedIndex;
				var ersp = formobj.rsp[i].value.trim();
				var esgst = formobj.sgst[i].value;
				var ecgst = formobj.cgst[i].value;
				var ebp = formobj.bp[i].value;
				var emon = formobj.mon[i].selectedIndex;
				var eyr = formobj.yr[i].selectedIndex;
				var eyrv = formobj.yr[i].options[eyr].value;
				var pidv = formobj.pid[i].value;
				var gstpercent=findGSTPercent(ersp, pidv);
				ems = validateArbEntries(epid,ersp,emon,eyr,eyrv,esgst,ecgst,gstpercent, ebp);
				if (ems.length > 0)
					break;
			}
		}
	}else {
		document.getElementById("dialog-1").innerHTML = "PLEASE ADD DATA";
		alertdialogue();
		//alert("PLEASE ADD DATA");
		return;
	}

	if (ems.length > 0) {
		document.getElementById("dialog-1").innerHTML = ems;
		alertdialogue();
		//alert(ems);
		return;
	}
	var objId = obj.id;
	document.getElementById(objId).disabled = true;
	formobj.submit();
}

function validateArbEntries(prod, rsp, mon, yr,yrv, sgst, cgst,gstpercent, bp) {
	var errmsg = "";
	var formobj = document.getElementById('page_data_form');

	if (!(prod > 0))
		errmsg = "Please Select The PRODUCT <br>";
	else if (!validateProduct()) 
		errmsg = errmsg + "You have chosen the product already. Please select the new one.<br>";
//		errmsg = errmsg + "The PRICE has already been fixed for the product you have selected.<br>";

	if (!(rsp.length > 0))
		errmsg = errmsg + "Please enter RSP.<br>";
	else if (validateDot(rsp))
		errmsg = errmsg + "RSP must contain atleast one number. <br>";
	else if (validateNegatives(rsp))
		errmsg = errmsg + "RSP Must be a positive number.<br>";
	else if (!(isDecimalNumber(rsp)))
		errmsg = errmsg + "RSP must contain only numerics.<br>";
	else if (!(validateDecimalNumberMinMax(rsp, 0, 10000))) {
		errmsg = errmsg + "RSP must be less than 10 THOUSAND .<br>";
		errmsg = errmsg + "Please enter MRP correctly and then CALCULATE again.<br>";
	} else
		formobj.rsp.value = round(parseFloat(rsp), 2);

	if (!(sgst.length > 0))
		errmsg = errmsg + "please calculate SGST.<br>";
	else if(parseInt(gstpercent)!=0 && parseFloat(sgst)==0) 
		errmsg = errmsg + "Invalid SGST Amount For a non 0% GST.<br>";
	else if (!(validateDecimalNumberMinMax(sgst,-1, 100000))) 
		errmsg = errmsg + "SGST must be less than 1,00,000 and greater than 0.<br>";

	if (!(cgst.length > 0))
		errmsg = errmsg + "please calculate CGST.<br>";
	else if(parseInt(gstpercent)!=0 && parseFloat(cgst)==0) 
		errmsg = errmsg + "Invalid CGST Amount For a non 0% GST.<br>";
	else if (!(validateDecimalNumberMinMax(cgst,-1, 100000))) 
		errmsg = errmsg + "CGST must be less than 1,00,000 and greater than 0.<br>";

	if (!(bp.length > 0))
		errmsg = errmsg + "please calculate BASIC PRICE.<br>";
	else if (!(validateDecimalNumberMinMax(bp, 0, 100000))) 
		errmsg = errmsg + "BASIC PRICE must be less than 1,00,000 and greater than 0.<br>";

	var date= new Date;
	var curmonth=date.getMonth();
	var curyear=date.getFullYear();
	
	if (!(parseInt(mon) > 0))
		errmsg = errmsg + "please select MONTH.<br>";
	else if((parseInt(mon)>curmonth+1) && (parseInt(years[yrv])>=curyear)) 
		errmsg = errmsg + "Month can't be Future Month<br>";
	else if((parseInt(years[yrv])>curyear))
		errmsg = errmsg + "YEAR can't be Future year<br>";
	
	if (!(parseInt(yr) > 0))
		errmsg = errmsg + "please select YEAR.<br>";
/*	else if (parseInt(yr) == 1 && parseInt(mon) < 7) {
		errmsg = errmsg	+ "Select MONTH and YEAR from JULY,2017 onwards .<br>";
	} 
*/
	else {
		var yrval = years[yr-1];
		var cdate = new Date(parseInt(yrval),parseInt(mon-1),01,0,0,0);
/*		var ddate = new Date(dedate.getFullYear(),dedate.getMonth(),01,0,0,0);
		if(cdate < ddate) {
			errmsg = errmsg+ "MONTH and YEAR should be after DayEnd Date .<br>";
		}
*/
		
/*		var edate = new Date(effdate.getFullYear(),effdate.getMonth(),01,0,0,0);
		if(cdate < edate) {
			errmsg = errmsg+ "MONTH and YEAR is acceptable from the EFFECTIVE Date provided.<br>";
		}
*/
		
		var pcode = document.getElementById("pid").value.trim();
		var plongdate = 0;
		for(var a=0;a<arb_data.length;a++) {
			if(arb_data[a].id == pcode) {
				plongdate = parseFloat(arb_data[a].effective_date);
			}
		}
		if(plongdate !=0 ) {
			var pdate = new Date((new Date(plongdate)).getFullYear(),(new Date(plongdate)).getMonth(),01,0,0,0);
			if(cdate < pdate) {
				errmsg = errmsg+ "MONTH and YEAR is acceptable from the Effective Date provided for this product in its Master.<br>";
			}
		}else {
			var edate = new Date(effdate.getFullYear(),effdate.getMonth(),01,0,0,0);
			if(cdate < edate) {
				errmsg = errmsg+ "MONTH and YEAR is acceptable from the EFFECTIVE Date provided.<br>";
			}
		}
	}
	return errmsg;
}

function validateArbEntries2(rsp) {
	var errmsg = "";
	var formobj = document.getElementById('page_data_form');

	if (!(rsp.length > 0))
		errmsg =  "Please enter MRP.Enter NA in case of regulators.<br>";
	else if (validateDot(rsp))
		errmsg = errmsg + "MRP must contain atleast one number. <br>";
	else if (validateNegatives(rsp))
		errmsg = errmsg + "MRP Must be a positive number.<br>";
	else if (!(isDecimalNumber(rsp)))
		errmsg = errmsg + "MRP must contain only numerics.<br>";
	else if (!((parseFloat(rsp) > 0) && (parseFloat(rsp) <= 9999.99))) {
		errmsg = errmsg + "MRP must be less than 10 thousand and greater than 0.<br>";
		errmsg = errmsg + "please enter MRP correctly and then CALCULATE again.<br>";
	} else
		formobj.rsp.value = round(parseFloat(rsp), 2);
	
	return errmsg;
}

/*function deleteItem(id) {
	if (confirm("Are you sure you want to delete?") == true) {
		var formobj = document.getElementById('page_data_form');
		formobj.actionId.value = "3213";
		formobj.itemId.value = id;
		formobj.submit();
	}
}*/

function deleteItem(id){
	 $("#myDialogText").text("Are You Sure You Want To Delete?");
	 var formobj = document.getElementById('page_data_form');
	 confirmDialogue(formobj,3213,id);
}

function calculateValues() {
	var formobj = document.getElementById('page_data_form');
	if (document.getElementById("rsp") != null) {

		var elements = document.getElementsByClassName("bpc");
		if (elements.length == 1) {
			var rspv = formobj.rsp.value.trim();
			var pid = formobj.pid.value;

			if ((formobj.pid.selectedIndex > 0) && (rspv.length > 0)) {

				var ems = validateArbEntries2(rspv);				
				if (ems.length > 0) {
					//alert(ems);
					document.getElementById("dialog-1").innerHTML = ems;
					alertdialogue();
					return;
				}
				var gstp = calculateGSTPrice(rspv, pid);
				var gstav = (Math.round(gstp * 100)) / 100;

				formobj.sgst.value = gstav;
				formobj.cgst.value = gstav;
				formobj.bp.value = (+rspv - (+(2 * gstav))).toFixed(2);
				formobj.rsp.value = round(parseFloat(rspv), 2);
				
			} else {
				document.getElementById("dialog-1").innerHTML = "PLEASE FILL BOTH PRODUCT AND MRP, THEN CLICK ON CALCULATE.";
				alertdialogue();
				//alert("PLEASE FILL BOTH PRODUCT AND MRP, THEN CLICK ON CALCULATE.");
				return;
			}
		} else if (elements.length > 1) {

			for (var i = 0; i < elements.length; i++) {
				var rspv = formobj.rsp[i].value.trim();
				var pid = formobj.pid[i].value;
				if ((formobj.pid[i].selectedIndex > 0) && (rspv.length > 0)) {				
					var ems = validateArbEntries2(rspv);
					if (ems.length > 0) {
						document.getElementById("dialog-1").innerHTML = ems;
						alertdialogue();
						//alert(ems);
						return;
					}
					var gstp = calculateGSTPrice(rspv, pid);
					var gstav = (Math.round(gstp * 100)) / 100;					
					formobj.sgst[i].value = gstav;
					formobj.cgst[i].value = gstav;
					formobj.bp[i].value = (+rspv - (+(2 * gstav))).toFixed(2);
					formobj.rsp[i].value = round(parseFloat(rspv), 2);
				} else {
					//alert("PLEASE FILL BOTH PRODUCT AND MRP, THEN CLICK ON CALCULATE.");
					document.getElementById("dialog-1").innerHTML = "PLEASE FILL BOTH PRODUCT AND MRP, THEN CLICK ON CALCULATE.";
					alertdialogue();
					return;
				}
			}
		}
		document.getElementById("save_data").disabled = false;

	} else {
		//alert("PLEASE ADD ARB PRICE DATA AND CLICK CALCULATE");
		document.getElementById("dialog-1").innerHTML = "PLEASE ADD ARB PRICE DATA AND CLICK CALCULATE";
		alertdialogue();
		return;
	}
	restrictChangingAllValues(".tp");	
/*	var efrz = document.getElementsByClassName("freez");
	makeEleReadOnly(efrz,efrz.length);
	
	if (elements.length == 1) {
		var spdfrz= document.getElementById("pid").options;
		disableSelect(spdfrz,spdfrz.length);

	} else if (elements.length > 1) {
		for (var i = 0; i < elements.length; i++) {
			var spdfrz=formobj.pid[i].options;
			disableSelect(spdfrz,spdfrz.length);
		}		
	}
*/
	restrictChangingAllValues(".freez");
}

function calculateGSTPrice(ebp, spc) {
	var cv = 0;
	for (var z = 0; z < arb_data.length; z++) {
		var pid = arb_data[z].prod_code;
		if (arb_data[z].id == spc) {
			var vatv = arb_data[z].gstp;
			cv = ((ebp * vatv) / (100 + +vatv));
			cv = cv / 2;
		}
	}
	return cv;
}

function findGSTPercent(ebp, spc) {
	var cv = 0;
	for (var z = 0; z < arb_data.length; z++) {
		var pid = arb_data[z].prod_code;
		if (arb_data[z].id == spc) {
			cv = arb_data[z].gstp;
		}
	}
	return cv;
}

function validateProduct2(text) {
	if (page_data.length != 0) {
		for (var i = 0; i < page_data.length; i++) {
			var spd = fetchARBProductDetails(cat_types_data, page_data[i].arb_code);
			if (spd.localeCompare(text) == 0) {
				return false;
			}
		}
	}
	return true;
}

function validateProduct() {
	var e2 = document.getElementsByClassName("pid");
	if (e2.length == 1) {
		var e = document.getElementById("pid");
		var text = e.options[e.selectedIndex].text;
		var flag = validateProduct2(text);
		return flag;
	}else if (e2.length > 1) {
		var flag = false;
		var h = 1;
		for (i = 0; i < e2.length - 1; i++) {
			var e3 = e2[i].options[e2[i].selectedIndex].text;
			var flag = validateProduct2(e3);
			if (flag) {
				for (j = 0; j < e2.length - 1; j++) {
					var e4 = e2[j + 1].options[e2[j + 1].selectedIndex].text;
					flag = validateProduct2(e4);
					var k = 0;
					if (flag) {
						for (k; k < h && k < e2.length - 1; k++) {
							var e5 = e2[k].options[e2[k].selectedIndex].text;
							if (e5.localeCompare(e4) == 0) {
								flag = false;
								return flag;
							}
						}
						h = h + 1;
					}
					if (!flag)
						return flag;
				}
			}
			return flag;
		}
	}
}

/*function submitPinNumber(formobj) {
    var pinNO = formobj.pinNO.value.trim();
    var enteredPin = formobj.enteredPin.value.trim();
    var ems="";
    if(!(enteredPin.length>0))
    	ems= ems+"Please Enter PIN NUMBER<br>";
    else if(!validatePinNumber(enteredPin,4,4))
    	ems = ems+"PIN NUMBER Must Be Valid  4 DIGIT Number <br>";
    else if(enteredPin !== pinNO)
    	ems= ems+"Please Enter Valid PIN NUMBER<br>";
    else if(enteredPin != formobj.enteredPin.value)
    	ems= ems+ "No spaces are allowed in pin Number\n";
    else if(enteredPin == pinNO){
    	document.getElementById('myModalarbPin').style="none";
    	document.getElementById('pageDiv').style="block";
    }
    if (ems.length > 0) {
    	document.getElementById("dialog-1").innerHTML = ems;
		alertdialogue();
		return;
    }
}*/


function submitPinNumber() {
    var pinNO = document.getElementById("pinNO").value.trim();
    var enteredPin = document.getElementById("enteredPin").value.trim();
    var ems="";
    if(!(enteredPin.length>0))
    	ems= ems+"Please Enter PIN NUMBER<br>";
    else if(!validatePinNumber(enteredPin,4,4))
    	ems = ems+"PIN NUMBER Must Be Valid  4 DIGIT Number <br>";
    else if(enteredPin !== pinNO)
    	ems= ems+"Please Enter Valid PIN NUMBER<br>";
    else if(enteredPin != (document.getElementById("enteredPin").value))
    	ems= ems+ "No spaces are allowed in pin Number\n";
    else if(enteredPin == pinNO){
    	document.getElementById('myModalarbPin').style.display="none";
    	document.getElementById('pageDiv').style.display="block";
    }
    if (ems.length > 0) {
    	document.getElementById("dialog-1").innerHTML = ems;
		alertdialogue();
		return;
    }
}