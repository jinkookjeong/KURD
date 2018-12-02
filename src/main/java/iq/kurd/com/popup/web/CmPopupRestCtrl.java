package iq.kurd.com.popup.web;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import iq.kurd.com.popup.service.CmPopupOrgInfoSvc;
import iq.kurd.com.popup.vo.CmOrgInfoVO;

@RestController("cmPopupRestCtrl")
public class CmPopupRestCtrl {

	@Resource(name = "cmPopupOrgInfoSvc")
	CmPopupOrgInfoSvc cmPopupOrgInfoSvc;
	
	public static void main(String[] args) {
		String code = "270100000";
		String var = code.substring(4, code.length());
		if(var.equals("00000")) {
			System.out.println(code.substring(0, 4));
		}
	}
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@RequestMapping(value = "/pt/ng/rest/getOrgInfoList.do", 
			method = {RequestMethod.POST}, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CmOrgInfoVO getOrgInfoList(@RequestBody CmOrgInfoVO cmOrgInfoVO) throws Exception{
		
		CmOrgInfoVO rcvVO = cmOrgInfoVO;
	    try { 
	    	
//	    	String upCode = cmOrgInfoVO.getOrgNo();
//	    	if(upCode != null && !upCode.equals("")) {
//	    		if(upCode.length() == 9) {
//		    		String substrOrgNo = upCode.substring(4, upCode.length());
//		    		if(substrOrgNo.equals("00000")) {//Master Code
//		    			cmOrgInfoVO.setOrgNo(upCode.substring(0, 4));
//		    		}
//	    		}	    		
//	    	}
	    	rcvVO = cmPopupOrgInfoSvc.selectOrgInfoList(cmOrgInfoVO);
	    	 
	    	HashMap<String,Object> cdMapList = new HashMap<String,Object>();
	    	cdMapList.put("upCodeJson", (new Gson()).toJson(rcvVO.getCmOrgInfoCdVOList()));  //Gson -> VO List to JSON
			rcvVO.setCdMapList(cdMapList);
			
			
		}catch(Exception ex) {			
			rcvVO.setErrMsg("ERROR:"+ex.getMessage());
		}
	
		return rcvVO;
	} 
	
	@RequestMapping(value = "/pt/ng/rest/getOrgMidCodeList.do", 
			method = {RequestMethod.POST}, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CmOrgInfoVO getOrgMidCodeList(@RequestBody CmOrgInfoVO cmOrgInfoVO) throws Exception{
		
	    try { 
	    	
	    	List<CmOrgInfoVO> cmOrgInfoVOList = cmPopupOrgInfoSvc.selectOrgCdList(cmOrgInfoVO);
	    	 
	    	HashMap<String,Object> cdMapList = new HashMap<String,Object>();
	    	cdMapList.put("midCodeJson", (new Gson()).toJson(cmOrgInfoVOList));  //Gson -> VO List to JSON
			cmOrgInfoVO.setCdMapList(cdMapList);
						
			
		}catch(Exception ex) {			
			cmOrgInfoVO.setErrMsg("ERROR:"+ex.getMessage());
		}
		return cmOrgInfoVO;
	} 
}
