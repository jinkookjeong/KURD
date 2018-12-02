package iq.kurd.com.util.format;

import iq.kurd.com.util.format.DateUtil;

/**
 * 포맷유효성체크 에 대한 Util 클래스 : Format Check Util
 * @fileName  : FormatCheckUtil.java
 * @package   : iq.kurd.com.util.format
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class FormatCheckUtil {


    /**
     * 유효한 전화번호 형식 : Valid phone number format
     * @methodName : checkFormatTell
     * @return     : boolean
     * @param tell1
     * @param tell2
     * @param tell3
     */
    public static boolean checkFormatTell(String tell1, String tell2, String tell3) {

	 String[] check = {"02", "031", "032", "033", "041", "042", "043", "051", "052", "053", "054", "055", "061",
				 "062", "063", "070", "080", "0505"};	//존재하는 국번 데이터
	 String temp = tell1 + tell2 + tell3;

	 for(int i=0; i < temp.length(); i++){
    		if (temp.charAt(i) < '0' || temp.charAt(i) > '9')
    			return false;
	 }	//숫자가 아닌 값이 들어왔는지를 확인

	 for(int i = 0; i < check.length; i++){
		 if(tell1.equals(check[i])) break;
		 if(i == check.length - 1) return false;
	 }	//국번입력이 제대로 되었는지를 확인

	 if(tell2.charAt(0) == '0') return false;

	 if(tell1.equals("02")){
		 if(tell2.length() != 3 && tell2.length() !=4) return false;
		 if(tell3.length() != 4) return false;	//서울지역(02)국번 입력때의 전화 번호 형식유효성 체크
	 }else{
		 if(tell2.length() != 3) return false;
		 if(tell3.length() != 4) return false;
	 }	//서울을 제외한 지역(국번 입력때의 전화 번호 형식유효성 체크

	 return true;
    }


    /**
     * 유효한 전화번호 형식 : Valid phone number format
     * @methodName : checkFormatTell
     * @return     : boolean
     * @param tellNumber
     * @return
     */
    public static boolean checkFormatTell(String tellNumber) {

	 String temp1;
	 String temp2;
	 String temp3;
	 String tell = tellNumber;

	 tell = tell.replace("-", "");

	 if(tell.length() < 9 || tell.length() > 11  || tell.charAt(0) != '0') return false;	//전화번호 길이에 대한 체크

	 if(tell.charAt(1) =='2'){	//서울지역 (02)국번의 경우일때
		 temp1 = tell.substring(0,2);
		 if(tell.length() == 9){
			 temp2 = tell.substring(2,5);
			 temp3 = tell.substring(5,9);
		 }else if(tell.length() == 10){
			 temp2 = tell.substring(2,6);
			 temp3 = tell.substring(6,10);
		 }else
			 return false;
	 } else if(tell.substring(0,4).equals("0505")){ //평생번호(0505)국번의 경우일때
		 if(tell.length() != 11) return false;
		 temp1 = tell.substring(0,4);
		 temp2 = tell.substring(4,7);
		 temp3 = tell.substring(7,11);
	 } else {	// 서울지역 및 "0505" 를 제외한 일반적인 경우일때
		 if(tell.length() != 10) return false;
		 temp1 = tell.substring(0,3);
		 temp2 = tell.substring(3,6);
		 temp3 = tell.substring(6,10);
	 }

	 return checkFormatTell(temp1, temp2, temp3);
    }

    /**
     * 유효한 휴대폰 형식 : Valid Mobile phone number format
     * @methodName : checkFormatCell
     * @return     : boolean
     * @param cell1
     * @param cell2
     * @param cell3
     */
    public static boolean checkFormatCell(String cell1, String cell2, String cell3) {
	 String[] check = {"010", "011", "016", "017", "018", "019"}; //유효한 휴대폰 첫자리 번호 데이터
	 String temp = cell1 + cell2 + cell3;

	 for(int i=0; i < temp.length(); i++){
    		if (temp.charAt(i) < '0' || temp.charAt(i) > '9')
    			return false;
         }	//숫자가 아닌 값이 들어왔는지를 확인

	 for(int i = 0; i < check.length; i++){
	     if(cell1.equals(check[i])) break;
	     if(i == check.length - 1) return false;
	 }	// 휴대폰 첫자리 번호입력의 유효성 체크

	 if(cell2.charAt(0) == '0') return false;

	 if(cell2.length() != 3 && cell2.length() !=4) return false;
	 if(cell3.length() != 4) return false;

	 return true;
    }


    /**
     * 유효한 휴대폰 형식 : Valid Mobile phone number format
     * @methodName : checkFormatCell
     * @return     : boolean
     * @param cellNumber
     */
    public static boolean checkFormatCell(String cellNumber) {

	 String temp1;
	 String temp2;
	 String temp3;

	 String cell = cellNumber;
	 cell = cell.replace("-", "");

	 if(cell.length() < 10 || cell.length() > 11  || cell.charAt(0) != '0') return false;

	 if(cell.length() == 10){	//전체 10자리 휴대폰 번호일 경우
		 temp1 = cell.substring(0,3);
		 temp2 = cell.substring(3,6);
		 temp3 = cell.substring(6,10);
	 }else{		//전체 11자리 휴대폰 번호일 경우
		 temp1 = cell.substring(0,3);
		 temp2 = cell.substring(3,7);
		 temp3 = cell.substring(7,11);
	 }

	 return checkFormatCell(temp1, temp2, temp3);
    }

    /**
     * 이메일형식인지 여부 : Whether the e-mail form(True/False)
     * @methodName : checkFormatMail
     * @return     : boolean
     * @param mail1
     * @param mail2
     */
    public static boolean checkFormatMail(String mail1, String mail2) {

	 int count = 0;

	 for(int i = 0; i < mail1.length(); i++){
		 if(mail1.charAt(i) <= 'z' && mail1.charAt(i) >= 'a') continue;
		 else if(mail1.charAt(i) <= 'Z' && mail1.charAt(i) >= 'A') continue;
		 else if(mail1.charAt(i) <= '9' && mail1.charAt(i) >= '0') continue;
		 else if(mail1.charAt(i) == '-' && mail1.charAt(i) == '_') continue;
		 else return false;
	 }	// 유효한 문자, 숫자인지 체크

	 for(int i = 0; i < mail2.length(); i++){
		 if(mail2.charAt(i) <= 'z' && mail2.charAt(i) >= 'a') continue;
		 else if(mail2.charAt(i) == '.'){ count++;  continue;}
		 else return false;
	 }	// 메일 주소의 형식 체크(XXX.XXX 형태)

	 if(count == 1) return true;
	 else  return false;

    }


    /**
     * 이메일형식인지 여부 : Whether the e-mail form(True/False)
     * @methodName : checkFormatMail
     * @return     : boolean
     * @param mail
     */
    public static boolean checkFormatMail(String mail) {

	 String[] temp = mail.split("@");	// '@' 를 기점으로 앞, 뒤 문자열 구분

	 if(temp.length == 2) return checkFormatMail(temp[0], temp[1]);
	 else return false;
    }

    /**
     * 유효한 주민번호인지 여부 : check Jumin Number (True/False)
     * @methodName : checkJuminNumber
     * @return     : boolean
     * @param jumin1
     * @param jumin2
     */
    public static boolean checkJuminNumber(String jumin1, String jumin2) {

    	DateUtil dateUtil = new DateUtil();
    	String juminNumber = jumin1 + jumin2;
    	String  idAdd = "234567892345"; 	// 주민등록번호에 가산할 값

		int countNum = 0;
    	int addNum = 0;
        int totalId = 0;      //검증을 위한 변수선언

        if (juminNumber.length() != 13) return false;	 // 주민등록번호 자리수가 맞는가를 확인

       	for (int i = 0; i <12 ; i++){
       		if(juminNumber.charAt(i)< '0' || juminNumber.charAt(i) > '9') return false;		//숫자가 아닌 값이 들어왔는지를 확인
       		countNum = Character.getNumericValue(juminNumber.charAt(i));
       		addNum = Character.getNumericValue(idAdd.charAt(i));
       		totalId += countNum * addNum;      //유효자리 검증식을 적용
        }

       	if(Character.getNumericValue(juminNumber.charAt(0)) == 0 || Character.getNumericValue(juminNumber.charAt(0)) == 1){
       		if(Character.getNumericValue(juminNumber.charAt(6)) > 4) return false;
       		String temp = "20" + juminNumber.substring(0,6);
       		if(!dateUtil.checkDate(temp)) return false;
       	}else{
       		if(Character.getNumericValue(juminNumber.charAt(6)) > 2) return false;
       		String temp = "19" + juminNumber.substring(0,6);
       		if(!dateUtil.checkDate(temp)) return false;
       	}	//주민번호 앞자리 날짜유효성체크 & 성별구분 숫자 체크

       	if(Character.getNumericValue(juminNumber.charAt(12)) == (11 - (totalId % 11)) % 10) //마지막 유효숫자와 검증식을 통한 값의 비교
        	return true;
        else
        	return false;

    }

    /**
     * 유효한 주민번호인지 여부 : check Jumin Number (True/False)
     * @methodName : checkJuminNumber
     * @return     : boolean
     * @param jumin
     */
    public static boolean checkJuminNumber(String jumin) {

    	if(jumin.length() != 13) return false;

        return checkJuminNumber(jumin.substring(0,6), jumin.substring(6,13));	//주민번호

    }

 
    /**
     * 유효한 법인번호인지 여부 : check Bubin Number (True/False)
     * @methodName : checkBubinNumber
     * @return     : boolean
     * @param bubin1
     * @param bubin2
     */
    public static boolean checkBubinNumber(String bubin1, String bubin2) {

    	String bubinNumber = bubin1 + bubin2;

    	int hap = 0;
    	int temp = 1;	//유효검증식에 사용하기 위한 변수

    	if(bubinNumber.length() != 13) return false;	//법인번호의 자리수가 맞는 지를 확인

    	for(int i=0; i < 13; i++){
    		if (bubinNumber.charAt(i) < '0' || bubinNumber.charAt(i) > '9') //숫자가 아닌 값이 들어왔는지를 확인
    			return false;
    	}



    	for ( int i=0; i<13; i++){
    		if(temp ==3) temp = 1;
    		hap = hap + (Character.getNumericValue(bubinNumber.charAt(i)) * temp);
    		temp++;
    	}	//검증을 위한 식의 계산

    	if ((10 - (hap%10))%10 == Character.getNumericValue(bubinNumber.charAt(12))) //마지막 유효숫자와 검증식을 통한 값의 비교
    		return true;
    	else
    		return false;
    	}


    /**
     * 유효한 법인번호인지 여부 : check Bubin Number
     * @methodName : checkBubinNumber
     * @return     : boolean
     * @param bubin
     */
    public static boolean checkBubinNumber(String bubin) {

    	if(bubin.length() != 13) return false;

    	return checkBubinNumber(bubin.substring(0,6), bubin.substring(6,13));
    	}

	/**
	 * 	 * 유효한 사업자번호인지 여부 : Check Company Number
	 * @methodName : checkCompNumber
	 * @return     : boolean
	 * @param comp1
	 * @param comp2
	 * @param comp3
	 * <p>XXX - XX - XXXXX 형식의 사업자번호 앞,중간, 뒤 문자열 3개 입력 받아 유효한 사업자번호인지 검사한다.</p>
	 */
	public static boolean checkCompNumber(String comp1, String comp2, String comp3) {

		String compNumber = comp1 + comp2 + comp3;

		int hap = 0;
		int temp = 0;
		int check[] = {1,3,7,1,3,7,1,3,5};  //사업자번호 유효성 체크 필요한 수

		if(compNumber.length() != 10)    //사업자번호의 길이가 맞는지를 확인한다.
			return false;

		for(int i=0; i < 9; i++){
			if(compNumber.charAt(i) < '0' || compNumber.charAt(i) > '9')  //숫자가 아닌 값이 들어왔는지를 확인한다.
				return false;

			hap = hap + (Character.getNumericValue(compNumber.charAt(i)) * check[temp]); //검증식 적용
			temp++;
		}

		hap += (Character.getNumericValue(compNumber.charAt(8))*5)/10;

		if ((10 - (hap%10))%10 == Character.getNumericValue(compNumber.charAt(9))) //마지막 유효숫자와 검증식을 통한 값의 비교
			return true;
		else
			return false;
 	}


	/**
	 * 유효한 사업자번호인지 여부 : Check Company Number
	 * @methodName : checkCompNumber
	 * @return     : boolean
	 * @param comp
	 */
	public static boolean checkCompNumber(String comp) {

		if(comp.length() != 10) return false;
		return checkCompNumber(comp.substring(0,3), comp.substring(3,5), comp.substring(5,10));

 	}




	/**
	 * 유효한 외국인등록번호인지 여부 (True/False) : check foreign Number(True/False)
	 * @methodName : checkforeignNumber
	 * @return     : boolean
	 * @param foreign1
	 * @param foreign2
	 */
	public static boolean checkforeignNumber( String foreign1, String foreign2  ) {

		DateUtil dateUtil = new DateUtil();
		String foreignNumber = foreign1 + foreign2;
		int check = 0;

		if( foreignNumber.length() != 13 )   //외국인등록번호의 길이가 맞는지 확인한다.
			return false;

		for(int i=0; i < 13; i++){
    		if (foreignNumber.charAt(i) < '0' || foreignNumber.charAt(i) > '9') //숫자가 아닌 값이 들어왔는지를 확인한다.
    			return false;
    	}

     	if(Character.getNumericValue(foreignNumber.charAt(0)) == 0 || Character.getNumericValue(foreignNumber.charAt(0)) == 1){
       		if(Character.getNumericValue(foreignNumber.charAt(6)) == 5 && Character.getNumericValue(foreignNumber.charAt(6)) == 6) return false;
       		String temp = "20" + foreignNumber.substring(0,6);
       		if(!dateUtil.checkDate(temp)) return false;
       	}else{
       		if(Character.getNumericValue(foreignNumber.charAt(6)) == 5 && Character.getNumericValue(foreignNumber.charAt(6)) == 6) return false;
       		String temp = "19" + foreignNumber.substring(0,6);
       		if(!dateUtil.checkDate(temp)) return false;
       	}	//외국인등록번호 앞자리 날짜유효성체크 & 성별구분 숫자 체크

		for( int i = 0 ; i < 12 ; i++ ) {
			check += ( ( 9 - i % 8 ) * Character.getNumericValue( foreignNumber.charAt( i ) ) );
		}

		if ( check % 11 == 0 ){
			check = 1;
		}else if ( check % 11==10 ){
			check = 0;
		}else
			check = check % 11;

		if ( check + 2 > 9 ){
			check = check + 2- 10;
		}else check = check+2;	//검증식을 통합 값의 도출

		if( check == Character.getNumericValue( foreignNumber.charAt( 12 ) ) ) //마지막 유효숫자와 검증식을 통한 값의 비교
			return true;
		else
			return false;
		}


	/**
	 * 13자리 외국인등록번호 문자열검사 : check foreign Number
	 * @methodName : checkforeignNumber
	 * @return     : boolean
	 * @param foreign
	 */
	public static boolean checkforeignNumber( String foreign  ) {

		if(foreign.length() != 13) return false;
		return checkforeignNumber(foreign.substring(0,6), foreign.substring(6,13));
	}

}

