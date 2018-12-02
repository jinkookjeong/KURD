package iq.kurd.com.co.zip.vo;

import java.util.Map;
import java.util.HashMap;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class CoZipQVO extends AbstractVO{

	private String zipCode;

	private String addr1;

	private String addr2;

	private String addr3;

}