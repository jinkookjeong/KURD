package iq.kurd.pt.ng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import iq.kurd.pt.com.NgFileCommon;
import iq.kurd.pt.ng.dao.NgBoardDAO;
import iq.kurd.pt.ng.vo.NgBoardVO;
import iq.kurd.pt.ng.vo.NgFileVO;
import iq.kurd.pt.ng.service.NgBoardSvc;
 
@Service("ngBoardService")  
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class NgBoardSvcImpl implements NgBoardSvc {
 
	@Resource(name = "ngBoardDAO")
	NgBoardDAO ngBoardDAO;
	
	@Resource(name = "ngFileCommon")
	NgFileCommon ngFileCommon;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public NgBoardVO selectNgBoardList(NgBoardVO ngBoardVO) throws Exception {
		// 게시판 등록 글 총 개수 구하기
		ngBoardVO.setTotalRecordCount(ngBoardDAO.selectSampleBoardNgListCnt(ngBoardVO));
		ngBoardVO.setNgBoardVOList(ngBoardDAO.selectSampleBoardNgList(ngBoardVO));
		return ngBoardVO; 
	}

	@Override
	public NgBoardVO selectNgBoardDetail(NgBoardVO ngBoardVO) throws Exception {
		
		ngBoardDAO.updateHitNgCnt(ngBoardVO); //update HIT
		ngBoardVO.setNgBoardVO(ngBoardDAO.selectSampleBoardNgDetail(ngBoardVO)); //select Master
		ngBoardVO.setNgFileVOList(ngBoardDAO.selectFileListInDetail(ngBoardVO.getNgBoardVO())); //select list file
		 
		return ngBoardVO;
	}

	@Override
	public void deleteNgBoard(NgBoardVO ngBoardVO) throws Exception {
		ngBoardDAO.deleteSampleNgBoard(ngBoardVO);
		
		//삭제할때 물리적인 파일삭제를 먼저해줌(삭제할 uuid를 구해야함, 먼저 db를 삭제하면 select할때 사라짐) 파일삭제와 db 삭제는 진짜 삭제할찌 고민필요함  		
		ngFileCommon.delPhyicalFile(ngBoardVO.getFileRefId());
		ngBoardDAO.deleteFileFromBoard(ngBoardVO);
		
	}
	
	@Override
	public void updateNgBoard(NgBoardVO ngBoardVO) throws Exception {
		
		ngBoardDAO.updateSampleNgBoard(ngBoardVO);
		//Update Attachment File
		List<NgFileVO> list = ngBoardVO.getNgFileVOList();
		log.info("SampleNgFileVO list: "+list);
		if(list != null && list.size() > 0) { //첨부파일이 있을때만
			ngBoardDAO.updateFile(ngBoardVO);
		}
		
		//저장할때 쓰레기 삭제함		
		//ngBoardVO boardVO = sampleNgBoardDAO.selectFileRefId(ngBoardVO); //File Ref ID 가져오기	
		ngFileCommon.delGarbageFile(ngBoardVO.getFileRefId());
	}

	/**
	 * 상세화면에서 status정도만 Sent로 업데이트
	 */	
	@Override
	public void updateNgBoardToSend(NgBoardVO ngBoardVO) throws Exception {
		
		ngBoardDAO.updateSampleNgBoard(ngBoardVO);
		ngBoardDAO.updateStatusFile(ngBoardVO);
		
	}

	
	@Override
	public void insertNgBoard(NgBoardVO ngBoardVO) throws Exception {
		
		//Insert Master
		ngBoardDAO.insertSampleNgBoard(ngBoardVO);
		
		//Update Attachment File
		List<NgFileVO> list = ngBoardVO.getNgFileVOList();
		log.info("NgFileVO list: "+list);
		if(list != null && list.size() > 0) { //첨부파일이 있을때만
			ngBoardDAO.updateFile(ngBoardVO);
		}
		
		//저장할때 쓰레기 삭제함		
		NgBoardVO boardVO = ngBoardDAO.selectFileRefId(ngBoardVO); //File Ref ID 가져오기	
		ngFileCommon.delGarbageFile(boardVO.getFileRefId());
		
	}
	
	@Override
	public void insertFile(NgFileVO sampleNgFileVO) throws Exception {
		ngBoardDAO.insertFile(sampleNgFileVO);
	} 
	
	@Override
	public NgFileVO selectFile(NgFileVO sampleNgFileVO) throws Exception{
		return ngBoardDAO.selectFile(sampleNgFileVO);
	} 
	
	
	@Override
	public NgFileVO selectFileList(NgFileVO sampleNgFileVO) throws Exception{		
		sampleNgFileVO.setNgFileVOList(ngBoardDAO.selectFileList(sampleNgFileVO));		
		return sampleNgFileVO;
	} 
	
	
	@Override
	public void deleteFile(NgFileVO sampleNgFileVO) throws Exception {
		ngBoardDAO.deleteFile(sampleNgFileVO);
	}
	
	@Override
	public void updateDelYnField(NgFileVO sampleNgFileVO) throws Exception {
		ngBoardDAO.updateDelYnField(sampleNgFileVO);
	}
	
}
