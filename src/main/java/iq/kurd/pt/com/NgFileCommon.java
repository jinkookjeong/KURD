package iq.kurd.pt.com;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import iq.kurd.pt.ng.dao.NgBoardDAO;
import iq.kurd.pt.ng.vo.NgFileVO;

@Repository
public class NgFileCommon {

	@Resource(name = "ngBoardDAO")
	NgBoardDAO ngBoardDAO;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 저장할때 쓰레기 삭제함
	 * @param fileRefId
	 * @throws Exception
	 */
	public void delGarbageFile(String fileRefId) throws Exception { 
				
		NgFileVO sampleNgFileVO = new NgFileVO();
		sampleNgFileVO.setDelYn("Y"); //삭제 그리고 status가 '' 인거 삭제함
		sampleNgFileVO.setFileRefId(fileRefId);
		List<NgFileVO>  sampleNgFileList = ngBoardDAO.selectFileList(sampleNgFileVO);
		
		log.info("delGarbageFile list: "+sampleNgFileList);
		if(sampleNgFileList != null && sampleNgFileList.size() > 0) { //첨부파일이 있을때만
			for (int i = 0; i < sampleNgFileList.size(); i++) {
				NgFileVO tempVO = sampleNgFileList.get(i);
				ngBoardDAO.deleteFile(tempVO); //DB 삭제
				
				String filePath = tempVO.getPhyUrlPath();
				String delUuid = tempVO.getFileUuid();
				File SAVED_DIR = new File(filePath);
		    	FileUtils.deleteDirectory(new File(SAVED_DIR, delUuid)); //File 삭제
		        if (new File(SAVED_DIR, delUuid).exists()){
		        	log.info("couldn't find or delete " + delUuid);
		        }else{
		        	log.info("deleted " + delUuid);
		        }
			}
		}
	}
	
	/**
	 * 물리적인 파일 삭제함(주위요망!)	
	 * @param fileRefId
	 * @throws Exception
	 */
	public void delPhyicalFile(String fileRefId) throws Exception{
		
		NgFileVO sampleNgFileVO = new NgFileVO();
		sampleNgFileVO.setFileRefId(fileRefId);
		
		List<NgFileVO> sampleNgFileList = ngBoardDAO.selectFileList(sampleNgFileVO);
		log.info("delPhyicalFile list: "+sampleNgFileList);
		for (int i = 0; i < sampleNgFileList.size(); i++) {
			NgFileVO tempVO = sampleNgFileList.get(i);
			
			String filePath = tempVO.getPhyUrlPath();
			String delUuid = tempVO.getFileUuid();
			File SAVED_DIR = new File(filePath);
	    	FileUtils.deleteDirectory(new File(SAVED_DIR, delUuid)); //File 삭제
	        if (new File(SAVED_DIR, delUuid).exists()){
	        	log.info("couldn't find or delete " + delUuid);
	        }else{
	        	log.info("deleted " + delUuid);
	        }
		}
	}
	
}
