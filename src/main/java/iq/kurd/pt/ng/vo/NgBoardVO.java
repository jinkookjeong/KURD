package iq.kurd.pt.ng.vo;

import java.util.Date;
import java.util.List;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@Data
@SuppressWarnings("serial")
public class NgBoardVO extends AbstractVO{	
    private String tendNo;
	private String tendSeq;
	private String tendNm;
	private String fromUserId;
	private String toUserId;
	private String pageId;
	private String title;
	private String contents;
	private String id;
	private int hit;
	private Date wdate;
	private String status;
	private String tendType;
	private String tendTypeNm;
	private String d1;
	private String d2;
	private String d3;
	private String d4;
	private String d5;
	private String useYn;
	private String fileRefId;
				
	private NgBoardVO ngBoardVO;
	private List<NgBoardVO> ngBoardVOList;
	private List<NgFileVO> ngFileVOList;	
	
}
