package iq.kurd.pt.com;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import iq.kurd.com.co.login.vo.JonepsActor;
import iq.kurd.com.co.login.vo.UsrAuthoVo;
import iq.kurd.com.util.login.LoginUtil;
import iq.kurd.pt.com.AuthCdConstant;
 
public class PtCommon {
	
	public static boolean isPTAdmin(HttpServletRequest request) throws Exception {
		
		JonepsActor actor = LoginUtil.getActor(request);
		List<UsrAuthoVo> usrAuthList = actor.getAuthCdList();
		
		if(usrAuthList == null) return false;
		
		for(UsrAuthoVo usrAuthVo : usrAuthList){
			if(usrAuthVo.getAuthoCd().equals(AuthCdConstant.ROLE_ADM_PT)){
				return true;
			}
		}
		
   		return false;
   	}
	
	
	public static boolean isORGAdmin(HttpServletRequest request) throws Exception {
		
		JonepsActor actor = LoginUtil.getActor(request);
		List<UsrAuthoVo> usrAuthList = actor.getAuthCdList();
		
		if(usrAuthList == null) return false;
		
		for(UsrAuthoVo usrAuthVo : usrAuthList){
			if(usrAuthVo.getAuthoCd().equals(AuthCdConstant.ROLE_ADM_ORG)){
				return true;
			}
		}
		
		return false;
	}
}
