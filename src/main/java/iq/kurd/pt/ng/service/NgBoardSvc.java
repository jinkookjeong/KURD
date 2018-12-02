package iq.kurd.pt.ng.service;

import iq.kurd.pt.ng.vo.NgBoardVO;
import iq.kurd.pt.ng.vo.NgFileVO;

public interface NgBoardSvc {

	NgBoardVO selectNgBoardList(NgBoardVO ngBoardVO) throws Exception;

	NgBoardVO selectNgBoardDetail(NgBoardVO ngBoardVO) throws Exception;
	
	void deleteNgBoard(NgBoardVO ngBoardVO) throws Exception;

	void updateNgBoard(NgBoardVO ngBoardVO) throws Exception;

	void insertNgBoard(NgBoardVO ngBoardVO) throws Exception;
	
	NgFileVO selectFile(NgFileVO sampleNgFileVO) throws Exception;
	
	void insertFile(NgFileVO sampleNgFileVO) throws Exception;
	
	NgFileVO selectFileList(NgFileVO sampleNgFileVO) throws Exception;
	
	void deleteFile(NgFileVO sampleNgFileVO) throws Exception;	    
	
	void updateDelYnField(NgFileVO sampleNgFileVO) throws Exception;	
	
	void updateNgBoardToSend(NgBoardVO ngBoardVO) throws Exception;
	
}
