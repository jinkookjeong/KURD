
function util_getMonthArray(m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11) {
	this[0] = m0;
	this[1] = m1;
	this[2] = m2;
	this[3] = m3;
	this[4] = m4;
	this[5] = m5;
	this[6] = m6;
	this[7] = m7;
	this[8] = m8;
	this[9] = m9;
	this[10] = m10;
	this[11] = m11;
}

function util_getCurrentTime(){

    var CurrentTime = new Date(Number(systimeStr.substring(0,4)), Number(systimeStr.substring(4,6)) - 1, Number(systimeStr.substring(6,8)),
            Number(systimeStr.substring(8,10)), Number(systimeStr.substring(10,12)), Number(systimeStr.substring(12,14)), Number(systimeStr.substring(14,17)));;
    var Year    = CurrentTime.getYear();
    var Month   = CurrentTime.getMonth();
    var day     = CurrentTime.getDate();
    var Hour    = CurrentTime.getHours();
    var Minutes = CurrentTime.getMinutes();
    var Seconds = CurrentTime.getSeconds();

    var ctime    = String(Year)+ String(Month)+ String(day)+ String(Hour)+ String(Minutes) + String(Seconds);
	return ctime;
}

function util_getDate(value){
	var year  = value.substring(4,8);
	var month = value.substring(2,4);
	var day   = value.substring(0,2);
	return ( util_isValidYear(year) && util_isValidMonth(month) && util_isValidDay(year,month,day) );
}

function util_getDateFromCal(value){
	var year  = value.substring(0,4);
	var month = value.substring(5,7);
	var day   = value.substring(8,10);

	return year + month + day;
}



function util_getMonthInterval(time1,time2) {
	var date1 = toTimeObject(time1);
	var date2 = toTimeObject(time2);

	var years = date2.getFullYear() - date1.getFullYear();
	var months = date2.getMonth() - date1.getMonth();
	var days = date2.getDate() - date1.getDate();

	return (years * 12 + months + (days >= 0 ? 0 : -1) );
}

function util_getDayInterval(time1,time2) {
	var date1 = toTimeObject(time1);
	var date2 = toTimeObject(time2);
	var day = 1000 * 3600 * 24;

	return parseInt((date2 - date1) / day, 10);
}

function util_getHourInterval(time1,time2) {
	var date1 = toTimeObject(time1);
	var date2 = toTimeObject(time2);
	var hour = 1000 * 3600;

	return parseInt((date2 - date1) / hour, 10);
}

/**
 * 오늘 날짜를 포맷팅해서 가져온다.
 *
 */
function util_getTodayWithFormat(){

	var date = new Date(Number(systimeStr.substring(0,4)), Number(systimeStr.substring(4,6)) - 1, Number(systimeStr.substring(6,8)),
            Number(systimeStr.substring(8,10)), Number(systimeStr.substring(10,12)), Number(systimeStr.substring(12,14)), Number(systimeStr.substring(14,17)));

	var today = dateFormat;
	today = today.replace("yyyy", systimeStr.substring(0,4));
	today = today.replace("MM", systimeStr.substring(4,6));
	today = today.replace("dd", systimeStr.substring(6,8));

	return today;

}

/*16112015 et 20112015 AJOUT DES FONCTIONS DE VERIFICATION DE WEKEND PAR LA DSI */

//Gérer le week-end et Jour férié
function checkWeekEnd(theDate, locale) {
	var theDate = theDate.split('/');
	var arrayDaysExclude = new Array("01/01/2016", "11/02/2016", "01/05/2016", "20/05/2016", "15/08/2016", "25/12/2015", "25/12/2016");
	var noWorkDay = false;
	
	if(locale == 'fr') {
		theDate = new Date(theDate[2],theDate[1]-1,theDate[0]);
	}
	else {
		theDate = new Date(theDate[0],theDate[1]-1,theDate[2]);
	}
	if(theDate.getDay() == 6 || theDate.getDay() == 0) {//Week End
		theDate = (theDate.getDay() == 6) ? addDays_dsi(theDate, -1) : addDays_dsi(theDate, 1);
	}	
	
	for(var i=0; i<arrayDaysExclude.length; i++) {
		if( (new Date(arrayDaysExclude[i].split('/')[2],arrayDaysExclude[i].split('/')[1]-1,arrayDaysExclude[i].split('/')[0])).getTime() == theDate.getTime() ) 
			noWorkDay = true;	
    }
	
	if(noWorkDay == true) {//No work day
		if(theDate.getDay() == 5){//vendredi
			theDate = addDays_dsi(theDate, -1);
		}	
		else {
			theDate = addDays_dsi(theDate, 1);
		}	
	}	
	
	//alert (formateDate(theDate));
	return formateDate(theDate);
}

//Ajout des jours à une date
function addDays_dsi(theDate, days) {
    return new Date(theDate.getTime() + days*24*60*60*1000);
}

//Formatting to dd/mm/yyyy 
function formateDate(theDate) {	
	var dd = theDate.getDate();
	if(dd < 10){
        dd = '0'+dd;
    }
	var mm = theDate.getMonth() + 1;
	if(mm < 10){
        mm = '0'+mm;
    }
	var y = theDate.getFullYear();

	return y + '-'+ mm + '-'+ dd;
}

/* FIN AJOUT*/

/**
 * 현재일에서 value일만큼 뒤의 날짜를 구해 리턴한다
 * @param value, 몇일뒤
 * @returns 'YYYY/MM/DD' 현재일에서 value일만큼 뒤의 날짜
 */
function util_addDate(value){

	var currentDate = new Date(Number(systimeStr.substring(0,4)), Number(systimeStr.substring(4,6)) - 1, Number(systimeStr.substring(6,8)),
            Number(systimeStr.substring(8,10)), Number(systimeStr.substring(10,12)), Number(systimeStr.substring(12,14)), Number(systimeStr.substring(14,17)));
	var resultTimeMil = currentDate.getTime()+(Number(value)*24*3600*1000);
	var resultDate = new Date(Number(systimeStr.substring(0,4)), Number(systimeStr.substring(4,6)) - 1, Number(systimeStr.substring(6,8)),
            Number(systimeStr.substring(8,10)), Number(systimeStr.substring(10,12)), Number(systimeStr.substring(12,14)), Number(systimeStr.substring(14,17)));
	resultDate.setTime(resultTimeMil);

	var year = String(resultDate.getFullYear());
	var month;
	var date;
	
	/*16112015 AJOUT DE LA DSI */
	var locale = 'ko';
	/* FIN AJOUT*/
			
	if(resultDate.getMonth()+1<10){
		month = '0'+String(resultDate.getMonth()+1);
	}
	else{
		month = String(resultDate.getMonth()+1);
	}	if(resultDate.getDate()<10){
		date = '0'+String(resultDate.getDate());
	}
	else{
		date = String(resultDate.getDate());
	}
	
	/* 16112015 MODIFICATION PRENANT EN COMPTE LES WEEKENDS */
	return checkWeekEnd(date+'/'+month+'/'+year, locale );

	/* 16112015 CODE INITIAL COREEN
	 * return year  + dateSeparater +
    	   month  + dateSeparater +
           date;
           FIN COMMENTAIRE */
	
	/*return dateFormat.replace("yyyy", year)  + dateSeparater +
	       dateFormat.replace("MM", month)  + dateSeparater +
	       dateFormat.replace("dd", date);*/

}

/**
 * 현재일에서 value일만큼 뒤의 날짜를 구해 리턴한다
 * @param value, (YYYYMMDD)
 * @returns 'YYYY/MM/DD' 현재일에서 value일만큼 뒤의 날짜
 */
function util_addDateFromDateStr(dateStr, value){

/*
	var currentDate = new Date(Number(dateStr.substring(0,4)), Number(dateStr.substring(4,6)) - 1, Number(dateStr.substring(6,8)),
            Number(dateStr.substring(8,10)), Number(dateStr.substring(10,12)), Number(dateStr.substring(12,14)), Number(dateStr.substring(14,17)));
	var resultTimeMil = currentDate.getTime()+(Number(value)*24*3600*1000);
	var resultDate = new Date(Number(dateStr.substring(0,4)), Number(dateStr.substring(4,6)) - 1, Number(dateStr.substring(6,8)),
            Number(dateStr.substring(8,10)), Number(dateStr.substring(10,12)), Number(dateStr.substring(12,14)), Number(dateStr.substring(14,17)));
	*/
	var currentDate = new Date(Number(dateStr.substring(0,4)), Number(dateStr.substring(4,6)) - 1, Number(dateStr.substring(6,8)));
	
	var resultDate = new Date(currentDate.getTime()+(value*24*3600*1000));
	//resultDate.setTime(resultTimeMil);

	var year = String(resultDate.getFullYear());
	var month;
	var date;
	/*16112015 AJOUT DE LA DSI */
	var locale = 'fr'; 
	/* FIN AJOUT*/
	if(resultDate.getMonth()+1<10){
		month = '0'+String(resultDate.getMonth()+1);
	}
	else{
		month = String(resultDate.getMonth()+1);
	}	if(resultDate.getDate()<10){
		date = '0'+String(resultDate.getDate());
	}
	else{
		date = String(resultDate.getDate());
	}

	/* 16112015 MODIFICATION PRENANT EN COMPTE LES WEEKENDS */
	return checkWeekEnd(date+'/'+month+'/'+year, locale);
	
	/* 16112015 CODE INITIAL COREEN COMMENTE
	return year  + dateSeparater +
    	   month  + dateSeparater +
           date;
           FIN COMMENTAIRE */
	
	/*return dateFormat.replace("yyyy", year)  + dateSeparater +
	       dateFormat.replace("MM", month)  + dateSeparater +
	       dateFormat.replace("dd", date);*/

}

/**
 * 현재일에서 value일만큼 뒤의 날짜를 구해 리턴한다
 * @param value, 몇일뒤
 * @returns 'YYYY/MM/DD' 현재일에서 value일만큼 뒤의 날짜
 */
function util_addDateBySep(value, sep){

	var currentDate = new Date(Number(systimeStr.substring(0,4)), Number(systimeStr.substring(4,6)) - 1, Number(systimeStr.substring(6,8)),
			            Number(systimeStr.substring(8,10)), Number(systimeStr.substring(10,12)), Number(systimeStr.substring(12,14)), Number(systimeStr.substring(14,17)));

	var resultTimeMil = currentDate.getTime()+(Number(value)*24*3600*1000);
	var resultDate = new Date(Number(systimeStr.substring(0,4)), Number(systimeStr.substring(4,6)) - 1, Number(systimeStr.substring(6,8)),
            Number(systimeStr.substring(8,10)), Number(systimeStr.substring(10,12)), Number(systimeStr.substring(12,14)), Number(systimeStr.substring(14,17)));
	resultDate.setTime(resultTimeMil);

	var year = String(resultDate.getFullYear());
	var month;
	var date;

	if(resultDate.getMonth()+1<10){
		month = '0'+String(resultDate.getMonth()+1);
	}
	else{
		month = String(resultDate.getMonth()+1);
	}	if(resultDate.getDate()<10){
		date = '0'+String(resultDate.getDate());
	}
	else{
		date = String(resultDate.getDate());
	}

	var resultDateStr = year + sep + month + sep +date;


	return resultDateStr;
}

/**
 * 윤년확인 :: Check the Leap Year
 * @param year, 년도
 * @returns 윤년(2월>29일) : true , 평년(2월>28일) : false
 */
function util_ckLeapYear(year){

	return(((year % 4 === 0) && (year%100!==0)) || (year % 400 === 0));

}

/**
 * 월별날짜 :: Days each Month
 * @param year:년도, month: '해당 달 -1'
 * @returns 년도와 달에 따른 한달 일수
 */
function util_getDaysInMonth(year, month){


	return[31,
	       util_ckLeapYear(year)?29:28,
	       31,
	       30,
	       31,
	       30,
	       31,
	       31,
	       30,
	       31,
	       30,
	       31]
	[month];

}

/**
 * 달(month) 더하기 :: Add months
 * @author MinSung.park
 * @param year:년도, month: '해당 달 -1'
 * @returns 년도와 달에 따른 한달 일수
 */

function util_addMonths(value){

		// 현재날짜 date2
		var date2 = new Date(Number(systimeStr.substring(0,4)), Number(systimeStr.substring(4,6)) , Number(systimeStr.substring(6,8)),
	            Number(systimeStr.substring(8,10)), Number(systimeStr.substring(10,12)), Number(systimeStr.substring(12,14)), Number(systimeStr.substring(14,17)));

		// 현재 month + 구하고자 하는 달 (+- month) n=-1
		var n = Number(systimeStr.substring(4,6))  + Number(value);

		var aMonth = 0;

		// Add Month(s)
		if(value >= 0){

			/** Add Year(a), Add Month(b) */
			var a = Math.floor(n/12);
			var b = n%12; //단, b=0이면 무조건 12월달
			if( b == 0){a = a -1;}

		}

		// Minus Months(s) 현재날짜에서 이전날짜를 구하는 경우
		if(value < 0){
			/** a년 전,  */
			if (n > 0){
				var a = 0; //년도의 변화가 없을 경우
				var b = value; //b=-1

			}else if(n <= 0){
				var a = -1 -  Math.floor(Math.abs(n)/12);
				var b = 0 - Math.abs(n)%12;


			}

		} //년도의 변화가 있는 경우



		// 해당년도를 벗어나는 몇달 전의 날짜를 구한다. n=11, value=-1
		if(n <= 0 && value < 0){
			if(b == 0){aMonth = 12;}
			else {aMonth = 13 - Number(systimeStr.substring(4,6)) + b;}

		}else{

			// 몇달 후를 구하거나, or 해당년도를 벗어나지 않는 몇달 전의 날짜를 구한다.
			if(b == 0){
				aMonth = 12;
			}
			else if(value < 0){
				aMonth = Number(systimeStr.substring(4,6)) + b;

				if (aMonth == 0){aMonth = 12;}
			}else {
				aMonth = b ;
			}
		}
		var date = new Date(Number(systimeStr.substring(0,4))+a, aMonth , Number(systimeStr.substring(6,8)),
		         Number(systimeStr.substring(8,10)), Number(systimeStr.substring(10,12)), Number(systimeStr.substring(12,14)), Number(systimeStr.substring(14,17)));
		var today = dateFormat;

		//Year
		today = today.replace("yyyy", Number(systimeStr.substring(0,4)) + a);

		//Month
			if(aMonth + 1 > 10){
				today = today.replace("MM", aMonth);
			}

			if(aMonth + 1 <= 10){
				today = today.replace("MM", '0' + aMonth);

			}
		//Date

			if((util_getDaysInMonth(Number(systimeStr.substring(0,4)),  Number(systimeStr.substring(4,6))-1	) == Number(systimeStr.substring(6,8)) && util_getDaysInMonth(Number(systimeStr.substring(0,4)),  Number(systimeStr.substring(4,6))-1	) >= util_getDaysInMonth(Number(systimeStr.substring(0,4))+a,  aMonth-1) )
					|| ( Number(systimeStr.substring(6,8)) >= util_getDaysInMonth(Number(systimeStr.substring(0,4))+a,  aMonth-1)) ){
				today = today.replace("dd",  util_getDaysInMonth(Number(systimeStr.substring(0,4))+a,  aMonth-1) );

			}else{
				today = today.replace("dd",  systimeStr.substring(6,8) );

			}


    return today;
}

function util_getDateFormat(dateValue){

	return dateFormat.replace("yyyy", dateValue.substring(0,4)).replace("MM", dateValue.substring(4,6)).replace("dd", dateValue.substring(6,8));

}

/**
 * 달(month) 더하기( 기호 제거 ) :: Add months
 * @author MinSung.park
 * @modyfy HyungJinLee
 * @param year:년도, month: '해당 달 -1'
 * @returns 년도와 달에 따른 한달 일수
 */

function util_addMonthsMark(value){

		// 현재날짜 date2
		var date2 = new Date(Number(systimeStr.substring(0,4)), Number(systimeStr.substring(4,6)) , Number(systimeStr.substring(6,8)),
	            Number(systimeStr.substring(8,10)), Number(systimeStr.substring(10,12)), Number(systimeStr.substring(12,14)), Number(systimeStr.substring(14,17)));

		// 현재 month + 구하고자 하는 달 (+- month) n=-1
		var n = Number(systimeStr.substring(4,6))  + Number(value);

		var aMonth = 0;

		// Add Month(s)
		if(value >= 0){

			/** Add Year(a), Add Month(b) */
			var a = Math.floor(n/12);
			var b = n%12; //단, b=0이면 무조건 12월달
			if( b == 0){a = a -1;}

		}

		// Minus Months(s) 현재날짜에서 이전날짜를 구하는 경우
		if(value < 0){
			/** a년 전,  */
			if (n > 0){
				var a = 0; //년도의 변화가 없을 경우
				var b = value; //b=-1

			}else if(n <= 0){
				var a = -1 -  Math.floor(Math.abs(n)/12);
				var b = 0 - Math.abs(n)%12;


			}

		} //년도의 변화가 있는 경우



		// 해당년도를 벗어나는 몇달 전의 날짜를 구한다. n=11, value=-1
		if(n <= 0 && value < 0){
			if(b == 0){aMonth = 12;}
			else {aMonth = 13 - Number(systimeStr.substring(4,6)) + b;}

		}else{

			// 몇달 후를 구하거나, or 해당년도를 벗어나지 않는 몇달 전의 날짜를 구한다.
			if(b == 0){
				aMonth = 12;
			}
			else if(value < 0){
				aMonth = Number(systimeStr.substring(4,6)) + b;

				if (aMonth == 0){aMonth = 12;}
			}else {
				aMonth = b ;
			}
		}
		var date = new Date(Number(systimeStr.substring(0,4))+a, aMonth , Number(systimeStr.substring(6,8)),
		         Number(systimeStr.substring(8,10)), Number(systimeStr.substring(10,12)), Number(systimeStr.substring(12,14)), Number(systimeStr.substring(14,17)));
		var today = "yyyyMMdd";

		//Year
		today = today.replace("yyyy", Number(systimeStr.substring(0,4)) + a);

		//Month
			if(aMonth + 1 > 10){
				today = today.replace("MM", aMonth);
			}

			if(aMonth + 1 <= 10){
				today = today.replace("MM", '0' + aMonth);

			}
		//Date

			if((util_getDaysInMonth(Number(systimeStr.substring(0,4)),  Number(systimeStr.substring(4,6))-1	) == Number(systimeStr.substring(6,8)) && util_getDaysInMonth(Number(systimeStr.substring(0,4)),  Number(systimeStr.substring(4,6))-1	) >= util_getDaysInMonth(Number(systimeStr.substring(0,4))+a,  aMonth-1) )
					|| ( Number(systimeStr.substring(6,8)) >= util_getDaysInMonth(Number(systimeStr.substring(0,4))+a,  aMonth-1)) ){
				today = today.replace("dd",  util_getDaysInMonth(Number(systimeStr.substring(0,4))+a,  aMonth-1) );

			}else{
				today = today.replace("dd",  systimeStr.substring(6,8) );

			}


    return today;
}

function util_getReverseDateFormat(dateStr){
	
	
	var year = dateStr.substring(dateFormat.indexOf("yyyy"), dateFormat.indexOf("yyyy") + 4);
	var month = dateStr.substring(dateFormat.indexOf("MM"), dateFormat.indexOf("MM") + 2);
	var day = dateStr.substring(dateFormat.indexOf("dd"), dateFormat.indexOf("dd") + 2);
	
	return year + month + day;

}

function util_translateDbFormatDate(locale, dateStr){
	
	if( dateStr!=null && dateStr.indexOf("-")==-1 ){
	
//		if( locale=='fr' ){
//			
//			return dateStr.substring(4, 8)+dateStr.substring(2,4)+dateStr.substring(0,2);
//		}else if( locale=='en' ){
//			
//			return dateStr.substring(4, 8)+dateStr.substring(0,2)+dateStr.substring(2,4);
//		}else{
			
			return dateStr;
		//}
	}else if(dateStr!=null && dateStr.indexOf("-")!=-1){
		
//		if( locale=='fr' ){
//			
//			return dateStr.substring(6, 10)+dateStr.substring(3,5)+dateStr.substring(0,2);
//		}else if( locale=='en' ){
//			
//			return dateStr.substring(6, 10)+dateStr.substring(0,2)+dateStr.substring(3,5);
//		}else{
//			
			return dateStr.substring(0,4)+dateStr.substring(5, 7)+dateStr.substring(8, 10);
//		}
	}
}

function util_transDateFormat(locale, dateStr){
	
	var splitter = "-";
	
	if( dateStr.length>=8 ){
	
		if( dateStr!=null && dateStr.indexOf("-")==-1 ){
			
			//if( locale!="fr" ){
				
				return dateStr.substring(0,4)+splitter+dateStr.substring(4,6)+splitter+dateStr.substring(6,8);
//			}
//			else {
//				
//				return dateStr.substring(0,2)+splitter+dateStr.substring(2,4)+splitter+dateStr.substring(4,8);
//			}
		}else if(dateStr!=null && dateStr.indexOf("-")!=-1 ){
			
			return dateStr;
		}else{
			
			return null;
		}
	}else{
		
		return null;
	}
}

function util_transDateFormatToInput(locale, dateStr){
	
	var splitter = "-";
	
	if( dateStr.length>=8 ){
	
		if( dateStr!=null && dateStr.indexOf("-")==-1 ){
			
			//if( locale!="fr" ){
				
				return dateStr.substring(0,4)+splitter+dateStr.substring(4,6)+splitter+dateStr.substring(6,8);
//			}else {
//				
//				return dateStr.substring(6,8)+splitter+dateStr.substring(4,6)+splitter+dateStr.substring(0,4);
//			}
		}else{
			
			return null;
		}
	}else{
		
		return null;
	}
}

// param = YYYYMMDD
function util_isBiggerAthanB(aDate, bDate){
	
	return new Date(aDate.substring(0,4), Number(aDate.substring(4,6))-1, aDate.substring(6,8)).getTime()>new Date(bDate.substring(0,4), Number(bDate.substring(4,6))-1,bDate.substring(6,8)).getTime()?true:false;
}
/*
 * param format : YYYYMMDD
 * 
 */
function util_dayInterval(date1, date2){
	
	if( date1.indexOf("-")!=-1 )date1 = util_getReverseDateFormat(date1);
	if( date2.indexOf("-")!=-1 )date2 = util_getReverseDateFormat(date2);
	
	var before = new Date(date1.substring(0,4), Number(date1.substring(4,6))-1, date1.substring(6,8));
	var after  = new Date(date2.substring(0,4), Number(date2.substring(4,6))-1, date2.substring(6,8));
	if( before.getTime()>after.getTime() ){
		
		return 0;
	}else{
	
		return ((after.getTime() - before.getTime())/1000/60/60/24)+1;
	}
}

/*
 * param : 'YYYYMMDD', 20140000
 */
function util_changeFormatDate(format, dateStr){
	
	var returnValue = null;
	if( format == 'YYYYMMDD' || format=='ko' || format=='en'){
		
		returnValue = (dateStr.indexOf("-")==-1)?dateStr.substring(0, 4)+dateStr.substring(4, 6)+dateStr.substring(6, 8):dateStr.substring(0, 4)+"/"+dateStr.substring(5, 7)+"/"+dateStr.substring(8, 10);
	}else if( format=='MMDDYYYY'){
		
		returnValue = (dateStr.indexOf("-")==-1)?dateStr.substring(4, 6)+dateStr.substring(6, 8)+dateStr.substring(0, 4):dateStr.substring(5, 7)+"/"+dateStr.substring(8, 10)+"/"+dateStr.substring(0, 4);
	}else if( format=='DDMMYYYY' || format=='fr'){
		
		returnValue = (dateStr.indexOf("-")==-1)?dateStr.substring(6, 8)+dateStr.substring(4, 6)+dateStr.substring(0, 4):dateStr.substring(8, 10)+"/"+dateStr.substring(5, 7)+"/"+dateStr.substring(0, 4);
		
	}
	return returnValue;
}

function util_transDateLangcode(inputFormat, outputFormat, dateStr){
	
	var inputDate = dateStr;
	if( inputFormat=="YYYYMMDD" ){
		inputDate = inputDate.indexOf("-")==-1?dateStr:dateStr.substring(0,4)+dateStr.substring(5,2)+dateStr.substring(8,10);
	}else if( inputFormat=="DDMMYYYY" ){
		
		inputDate = inputDate.indexOf("-")==-1?dateStr.substring(4, 8)+dateStr.substring(2, 4)+dateStr.substring(0, 2):dateStr.substring(6, 10)+dateStr.substring(3, 5)+dateStr.substring(0, 2);
	}else if( inputFormat=="MMDDYYYY" ){
		
		inputDate = inputDate.indexOf("-")==-1?dateStr.substring(4, 8)+dateStr.substring(0, 2)+dateStr.substring(2, 4):dateStr.substring(6, 10)+dateStr.substring(0, 2)+dateStr.substring(3, 5);
	}
	
	if( outputFormat=="YYYYMMDD" ){
		
		return dateStr.indexOf("-")==-1?inputDate:inputDate.substring(0,4)+inputDate.substring(4,6)+inputDate.substring(6,8);
	}else if( outputFormat=="DDMMYYYY" ){
		
		return dateStr.indexOf("-")==-1?inputDate.substring(6,8)+inputDate.substring(4,6)+inputDate.substring(0,4):inputDate.substring(6,8)+"/"+inputDate.substring(4,6)+"/"+inputDate.substring(0,4)
	}else if( outputFormat=="MMDDYYYY" ){
		
		return dateStr.indexOf("-")==-1?inputDate.substring(4,6)+inputDate.substring(6,8)+inputDate.substring(0,4):inputDate.substring(4,6)+"/"+inputDate.substring(6,8)+"/"+inputDate.substring(0,4)
	}
}

function util_calcDateExceptWeekend(startDate, periodDay){
	
	periodDay = Number(periodDay);
	if( new String(startDate).trim().length>0 && new String(periodDay).trim().length>0 ){
	
		var endDate = new Date(Number(new String(startDate).substring(0,4)), Number(new String(startDate).substring(4,6))-1, Number(new String(startDate).substring(6,8)));
		var sDate = new Date(new String(startDate).substring(0,4),Number(new String(startDate).substring(4,6))-1,new String(startDate).substring(6,8));
		// case 2)
		if( periodDay+sDate.getDay()>5 && periodDay>0 ){
			
			if( sDate.getDay()==6 ){
				
				sDate.setDate(sDate.getDate()-1);
			}else if( sDate.getDay()==0 ){
				
				sDate.setDate(sDate.getDate()+1);
			}
			
			//var wValue = Math.floor(sDate.getDay()/5)+Math.floor((periodDay-(Math.floor(sDate.getDay()/5)))/5);
			var wValue = ( (sDate.getDay()==0?7:sDate.getDay())+periodDay>5?1+Math.floor((periodDay-((sDate.getDay()==0 || sDate.getDay()>5)?0:5-sDate.getDay()))/5):0);
			periodDay = periodDay + wValue*2;
		}
		
		sDate.setTime(sDate.getTime()+Number(periodDay*24*3600*1000));
		return new String(sDate.getFullYear())+new String((Number(sDate.getMonth()+1))<10?"0"+(Number(sDate.getMonth()+1)):(Number(sDate.getMonth()+1)))+new String(Number(sDate.getDate())<10?"0"+sDate.getDate():sDate.getDate());	
	}else{
		
		return null;
	}
}

function util_calcPeriodExceptWeekend(startDate, endDate){
	
	if( new String(endDate).trim().length>0 && new String(startDate).trim().length>0 ){
		
		var sDate = new Date(new String(startDate).substring(0,4),Number(new String(startDate).substring(4,6))-1,new String(startDate).substring(6,8));
		var eDate = new Date(new String(endDate).substring(0,4),Number(new String(endDate).substring(4,6))-1,new String(endDate).substring(6,8));
		
		// case 2)
		var period = Number( (eDate.getTime() - sDate.getTime())/1000/60/60/24 );
		
		if( sDate.getDay()==6 ){
			
			sDate.setDate(sDate.getDate()-1);
		}
		if( eDate.getDay()==6 ){
			
			eDate.setDate(eDate.getDate()+1);
			period = Number( (eDate.getTime() - sDate.getTime())/1000/60/60/24 );
		}
		
		var wValue = ((((sDate.getDay()==0 || sDate.getDay()>5)?1:sDate.getDay())+period)>5?1+Math.floor((Number((eDate.getTime() - sDate.getTime())/1000/60/60/24)-(7-sDate.getDay()) )/7):0); 
		if( period>0 ){
			
		    //period = period - wValue*2;			// case 2)
		    period = Number((eDate.getTime() - sDate.getTime())/1000/60/60/24) - wValue*2;
			return period>0?period:0;
		}else{
			
			return 0;
		}
	}else{ 
		
		return null;
	}
}