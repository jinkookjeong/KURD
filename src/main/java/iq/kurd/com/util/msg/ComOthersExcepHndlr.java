package iq.kurd.com.util.msg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;

/**
 * 
 * @fileName  : ComOthersExcepHndlr.java
 * @package   : iq.kurd.common.util.msg
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class ComOthersExcepHndlr implements ExceptionHandler {

    protected Log log = LogFactory.getLog(this.getClass());

    public void occur(Exception exception, String packageName) {
    	//log.debug(" ServiceExceptionHandler run...............");
    	log.error(packageName, exception);
    	try {
		    //mailSender. send(ex, packageName);
		    //log.debug(" sending a alert mail  is completed ");
		    log.error(packageName, exception);
		} catch (Exception e) {
		    e.printStackTrace();
		}

    }
}
