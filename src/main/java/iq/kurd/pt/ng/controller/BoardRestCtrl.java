package iq.kurd.pt.ng.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import iq.kurd.com.co.code.service.CoCommonCdService;
import iq.kurd.com.co.code.vo.CoCommonCdVO;
import iq.kurd.pt.ng.vo.NgBoardVO;
import iq.kurd.pt.ng.service.NgBoardSvc;

@RestController("boardRestCtrl")
public class BoardRestCtrl {

	protected Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name = "ngBoardService")
	NgBoardSvc ngBoardService;
	
	@Resource(name="coCommonCdService")
	CoCommonCdService coCommonCdService;
	
	//Common Code Map List
	HashMap<String,Object> cdMapList = new HashMap<String,Object>();
	
	@RequestMapping(value = "/pt/ng/rest/getBoardList.do", 
			method = {RequestMethod.POST}, produces = { MediaType.APPLICATION_JSON_VALUE })
	public NgBoardVO getBoardList(@RequestBody NgBoardVO ngBoardVO) throws Exception{
		  
	    try { 
			ngBoardService.selectNgBoardList(ngBoardVO);
		}catch(Exception ex) {			
			ngBoardVO.setErrMsg("ERROR:"+ex.getMessage());
		}
		 return ngBoardVO;
	} 
 
	@RequestMapping(value = "/pt/ng/rest/ngNewBoardSave.do",
	          method = {RequestMethod.POST}, produces = { MediaType.APPLICATION_JSON_VALUE })	
	public NgBoardVO saveNewBoard(@RequestBody NgBoardVO ngBoardVO) throws Exception {
	    
		try {
			ngBoardService.insertNgBoard(ngBoardVO);
		}catch(Exception ex) {
			ngBoardVO.setErrMsg("ERROR:"+ex.getMessage());
		}
		return ngBoardVO;
	}
	
	@RequestMapping(value = "/pt/ng/rest/getBoardDetail.do",
	          method = {RequestMethod.POST}, produces = { MediaType.APPLICATION_JSON_VALUE })
	public NgBoardVO getBoardDetail(@RequestBody NgBoardVO ngBoardVO) throws Exception {
		
		 try {   
			  ngBoardService.selectNgBoardDetail(ngBoardVO);
			  
			  
			  CoCommonCdVO coCommonCdVO = new CoCommonCdVO(); //Common Code
			  coCommonCdVO.setUpCd("EP1830");			
			  List<CoCommonCdVO> listCommonCode = coCommonCdService.selectListCommonCode(coCommonCdVO);
			  
			  //공통코드 부분 검색하여 select/option에서 처리하기위함
			  cdMapList.clear();
			  cdMapList.put("tendTypeCd", (new Gson()).toJson(listCommonCode));  //Gson -> VO List to JSON
			  ngBoardVO.setCdMapList(cdMapList); 
			  
		 }catch(Exception ex) {			
			 ngBoardVO.setErrMsg("ERROR:"+ex.getMessage());
		 }
		 return ngBoardVO;
	}

	@RequestMapping(value = "/pt/ng/rest/delBoardDetail.do",
	          method = {RequestMethod.DELETE}, produces = { MediaType.APPLICATION_JSON_VALUE })
	public NgBoardVO delBoardDetail(
			@RequestParam("tendNo") String tendNo,
			@RequestParam("tendSeq") String tendSeq,
			@RequestParam("fileRefId") String fileRefId ) throws Exception {
		NgBoardVO ngBoardVO = new NgBoardVO();
		ngBoardVO.setTendNo(tendNo);
		ngBoardVO.setTendSeq(tendSeq);
		ngBoardVO.setFileRefId(fileRefId);
		 try {   
		  
			ngBoardService.deleteNgBoard(ngBoardVO);
		 }catch(Exception ex) {			
			ngBoardVO.setErrMsg("ERROR:"+ex.getMessage());
		 }
		 return ngBoardVO;
	}
 
	@RequestMapping(value = "/pt/ng/rest/updateBoardDetail.do",
	          method = {RequestMethod.POST}, produces = { MediaType.APPLICATION_JSON_VALUE })	
	public NgBoardVO updateBoardDetail(@RequestBody NgBoardVO ngBoardVO) throws Exception {
	  
	  ngBoardService.updateNgBoard(ngBoardVO);	  
	  return ngBoardService.selectNgBoardDetail(ngBoardVO);
	}
	
	@RequestMapping(value = "/pt/ng/rest/updateNgBoardToSend.do",
	          method = {RequestMethod.POST}, produces = { MediaType.APPLICATION_JSON_VALUE })	
	public NgBoardVO updateNgBoardToSend(@RequestBody NgBoardVO ngBoardVO) throws Exception {
	  
	  ngBoardService.updateNgBoardToSend(ngBoardVO);	  
	  return ngBoardService.selectNgBoardDetail(ngBoardVO);
	}	
	
}
