package iq.kurd.com.util.msg;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceMessage {

	@Resource(name="messageSource")
	protected MessageSource messageSource;
	
	public String getResourceMsg(String code) {
		
		MessageSourceAccessor msa = new MessageSourceAccessor (messageSource, LocaleContextHolder.getLocale());
		return msa.getMessage(code);
	}

	public String getResourceMsg(String code, Object[] obj) {
		
		MessageSourceAccessor msa = new MessageSourceAccessor (messageSource, LocaleContextHolder.getLocale());
		return msa.getMessage(code, obj);
	}
}
