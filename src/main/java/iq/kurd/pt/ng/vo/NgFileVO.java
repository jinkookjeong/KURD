package iq.kurd.pt.ng.vo;

import java.util.Date;
import java.util.List;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@Data
@SuppressWarnings("serial")
public class NgFileVO extends AbstractVO {
	private String userId;
	private String pageId;
	private String fileUuid;
	private int fileSeq;
	private String originFileNm;
	private String phyFileNm;
	private String phyUrlPath;;
	private String ext;
	private int fileSize;
	private String status;
	private String delYn;
	private String fileRefId;
	private String reqIp;
	private Date rgtId;
	private Date rgtDt;
	private Date modId;
	private Date modDt;
	
	private List<NgFileVO> ngFileVOList;
	
}
