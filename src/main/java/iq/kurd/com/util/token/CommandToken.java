package iq.kurd.com.util.token;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandToken {
	
	public static String set(HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		long systime = System.currentTimeMillis();
		byte[] time = new Long(systime).toString().getBytes();
		byte[] id = session.getId().getBytes();
		String token = "";

		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(id);
			md5.update(time);

			token = toHex(md5.digest());
			// req.setAttribute("TOKEN",token);
			session.setAttribute("token", token);
		} catch (Exception e) {
			System.err.println("Unable to calculate MD5 Digests");
		}
		
		System.out.println("==========================>> CommandToken:set:token["+token+"]");
		
		return token;
	}

	public static boolean isValid(HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		String requestToken = request.getParameter("token");
		String sessionToken = (String) session.getAttribute("token");

		System.out.println("==========================>> CommandToken:isValid:requestToken["+requestToken+"] sessionToken["+sessionToken+"]");

		if (requestToken == null || sessionToken == null)
			return false;
		else
			return requestToken.equals(sessionToken);
	}
	
	public static void remove(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.removeAttribute("token");
	}

	private static String toHex(byte[] digest) {
		
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < digest.length; i++)
			buf.append(Integer.toHexString((int) digest[i] & 0x00ff));
		return buf.toString();
	}
}
