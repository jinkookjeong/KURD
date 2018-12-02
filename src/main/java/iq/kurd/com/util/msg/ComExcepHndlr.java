package iq.kurd.com.util.msg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;
 
/**
 * 공통서비스의 exception 처리 : Common Exception Handling
 * @fileName  : ComExcepHndlr.java
 * @package   : iq.kurd.common.util.msg
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class ComExcepHndlr implements ExceptionHandler {

    protected Log log = LogFactory.getLog(this.getClass());
 
    /*
    @Resource(name = "otherSSLMailSender")
    private SimpleSSLMail mailSender;
     */
    /**
     * 발생된 Exception을 처리한다.
     */
    public void occur(Exception ex, String packageName) {

		try {
		    log.error(packageName, ex);
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
} 
