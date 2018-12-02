package iq.kurd.com.co.zip.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import iq.kurd.com.AbstractVO;
import lombok.Data;
	
@SuppressWarnings("serial")
@Data
public class CoZipSVO extends AbstractVO{

	private String zipCode;

	private String addr1;

	private String addr2;

	private String addr3;

    private List<CoZipQVO> coZipQVOList;

    private List<CoZipQVO> coZipAddr1List;

    private List<CoZipQVO> coZipAddr2List;

    private List<CoZipQVO> coZipAddr3List;

	private String searchType;

}