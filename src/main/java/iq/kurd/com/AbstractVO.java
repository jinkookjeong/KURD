package iq.kurd.com;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import lombok.Data;
 
@Data
@SuppressWarnings("serial")
public class AbstractVO implements Serializable{
	
	private Date rgtId;
	private Date rgtDt;
	private Date modId;
	private Date modDt;
	private int totalRecordCount;
	private String viewType;
	private String startDate; //검색시작일자
	private String endDate; //검색종료일자
	private int rownum; //테이블에 표시될 일련번호
  
	private HashMap<String,Object> cdMapList; //공통코드 맵핑정보 담는곳
	
	private String docStatus;
	private String errMsg = ""; //NULL is Success 
	
}
