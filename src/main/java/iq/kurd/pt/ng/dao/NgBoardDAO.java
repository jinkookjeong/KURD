package iq.kurd.pt.ng.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import iq.kurd.pt.ng.vo.NgBoardVO;
import iq.kurd.pt.ng.vo.NgFileVO;

@Mapper("ngBoardDAO")
public interface NgBoardDAO {
	
	//****************************************//	
	//NG Board 테이블에 저장
	//****************************************//
	//목록
	public int selectSampleBoardNgListCnt(NgBoardVO ngBoardVO) throws Exception;
	public List<NgBoardVO> selectSampleBoardNgList(NgBoardVO ngBoardVO) throws Exception;

	//상세
	public NgBoardVO selectSampleBoardNgDetail(NgBoardVO ngBoardVO) throws Exception;
	
	//파일 참조번호
	public NgBoardVO selectFileRefId(NgBoardVO ngBoardVO) throws Exception;
		
	//조회 증가
	public int updateHitNgCnt(NgBoardVO ngBoardVO) throws Exception;
	
	//입력
	public void insertSampleNgBoard(NgBoardVO ngBoardVO) throws Exception;
	//수정
	public void updateSampleNgBoard(NgBoardVO ngBoardVO) throws Exception;
	//삭제
	public void deleteSampleNgBoard(NgBoardVO ngBoardVO)throws Exception;
	//첨부파일 삭제
	public void deleteFileFromBoard(NgBoardVO ngBoardVO)throws Exception;
	
	//****************************************//	
	//Common File 테이블에 처리
	//****************************************//
	public List<NgFileVO> selectFileList(NgFileVO sampleNgFileVO) throws Exception;
	public List<NgFileVO> selectFileListInDetail(NgBoardVO ngBoardVO) throws Exception;
	
	public void insertFile(NgFileVO sampleNgFileVO) throws Exception;
	public NgFileVO selectFile(NgFileVO sampleNgFileVO) throws Exception;
	public void deleteFile(NgFileVO sampleNgFileVO)throws Exception;
	public void updateFile(NgBoardVO ngBoardVO)throws Exception;
	public void updateStatusFile(NgBoardVO ngBoardVO)throws Exception;
	public void updateDelYnField(NgFileVO sampleNgFileVO)throws Exception;
	
}
