package iq.kurd.com.co.transMail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import iq.kurd.com.KurdProperties;


/**
 * 메일 전송 : send mail
 * @fileName  : SendMail.java
 * @package   : iq.kurd.com.co.mail
 * @since     : 2018. 2. 26.
 * @author    : Jin Kook JEONG
 */
public class SendMail {

	//private final Logger log = Logger.getLogger(getClass());
	
	//private static String HOST = "201.193.78.1"; //smtp inbound
	//private static String HOST = "211.47.239.9"; //smtp local domain ip
	//private static String host = UrlConstant.CDOMAIN; //smtp local domain ip --> devl
	private String host; //smtp local domain ip --> real

	private String portocal;
	private int port;
	
//	private static String id = "dG9tY2F0Ng==";
//	private static String password = "dG9tITAwMg==";
	private String id;
	private String password;
	 
	//milliseconds
	private int connectionTimeout; //10 seconds
	private int transportTimeout; //10 seconds
	
	
	private static String defaultContentType = "text/html; charset=utf-8";
	//do not change frommailaddress
	private String defaultFrommailAddress = "master@joneps.gov.jo";
	private static String defaultFromName = "admin";
	
	private String contentType;
	
	private String fromName;
	
	private String toMailAddress;
	private String toName;
	private String subject;
	private String content;
	private MimeBodyPart attachPart;
	
	private Session session;
	
	public static void main(String args[]){
	
		SendMail sendMail = new SendMail("kiljae.kim", "text/html; charset=utf-8");
		try {
			
			sendMail.setContent("content");
			sendMail.setRecipient("kiljae.kim@nate.com", "kiljae.kim");
			sendMail.setSubject("subject");
			
			sendMail.send();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public SendMail() {
		this(defaultFromName, defaultContentType);
	}

	/**
	 * @param fromName
	 * @param contentType
	 */
	public SendMail(String fromName, String contentType) {
		this.fromName = fromName;
		this.contentType = contentType;
		
		initialize();
	}

	/**
	 * 초기화 : initialize
	 * @methodName : initialize
	 * @return     : void
	 */
	public void initialize() {
		
		this.host = KurdProperties.getProperty("smtpHost");
		this.portocal = KurdProperties.getProperty("smtpPortocal");
		this.port = Integer.parseInt(KurdProperties.getProperty("smtpPort"));
		this.id = KurdProperties.getProperty("smtpId");
		this.password = KurdProperties.getProperty("smtpPassword");
		this.connectionTimeout = Integer.parseInt(KurdProperties.getProperty("smtpConnectionTimeout"));
		this.transportTimeout = Integer.parseInt(KurdProperties.getProperty("smtpTansportTimeout"));
		this.defaultFrommailAddress = KurdProperties.getProperty("smtpDefaultFrommailAddress");
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", portocal);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", Integer.toString(port));
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.connectiontimeout", Integer.toString(connectionTimeout));
		props.put("mail.smtp.timeout", Integer.toString(transportTimeout));
		
		Authenticator authenticator = new SMTPAuthenticator(KurdProperties.getProperty("smtpId"), KurdProperties.getProperty("smtpPassword"));
		
		session = Session.getInstance(props, authenticator);
		
		session.setDebug(true);
				
	}

	/**
	 * 메일주소, 이름 설정 : set mail and name
	 * @methodName : setRecipient
	 * @return     : void
	 * @param toMailAddress
	 * @param toName
	 */
	public void setRecipient(String toMailAddress, String toName) {
		this.toMailAddress = toMailAddress;
		this.toName = toName;
	}

	/**
	 * set Subject : set Subject
	 * @methodName : setSubject
	 * @return     : void
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * content 설정 : set content
	 * @methodName : setContent
	 * @return     : void
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	

	/**
	 * attachFile 설정 : set attachFile
	 * @methodName : setAttachPart
	 * @return     : void
	 * @param attachPart
	 */
	public void setAttachPart(MimeBodyPart attachPart) {
		this.attachPart = attachPart;
	}

	/**
	 * 파일첨부 된 메일전송 : send mail
	 * @methodName : send
	 * @return     : void
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 * @throws Exception
	 */
	public void send() throws UnsupportedEncodingException, MessagingException, Exception {
		
		try {
			MimeMessage message = new MimeMessage(session);
			//message.setFrom(new InternetAddress(reqerMailAddr.equals("")?defaultFrommailAddress:reqerMailAddr, reqerNm.equals("")?fromName:reqerNm));
			message.setFrom(new InternetAddress(defaultFrommailAddress,""));
			
			String[] toMailAAddress = toMailAddress.split(";");
			for(int i=0; i< toMailAAddress.length; i++){
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMailAAddress[i], toName));
			}
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMailAddress, toName));
			message.setSubject(subject);
			message.setHeader("X-Mailer", "sendMessage");
	        message.setSentDate(new Date());

	        Multipart multipart = new MimeMultipart();
	        
	        // 본문 내용을 붙이기 위한 영역
	        MimeBodyPart bodyPart = new MimeBodyPart();
	        
	        // 첨부파일을 붙이기.
	        if(attachPart != null){
	        	multipart.addBodyPart(attachPart);
	        }
	        
	        // 메일 내용 붙이기.
			content = content.replaceAll("\r\n", "<br>"); // 메일 내용 줄바꿈 처리
			content = content.replaceAll("\u0020", "&nbsp;"); // 메일 내용 스페이스바 처리
	        bodyPart.setContent(getContent(content), contentType);	        
	        multipart.addBodyPart(bodyPart);
	        
	        // 첨부파일과 본문내용을 메시지에 담아 보내기
	        message.setContent(multipart);
	        Transport.send(message);
	        
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw e;
		} catch (MessagingException e) {
			e.printStackTrace();
			throw e;		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;		
		}
	}
	
	/**
	 * 
	 * @methodName : getContent
	 * @return     : String
	 * @param content
	 * @return
	 */
	private String getContent(String content){
		
		StringBuffer buffer = new StringBuffer();
		
		String ppsUrl = KurdProperties.getProperty("JonepsPTUrl");

		buffer.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
		buffer.append("<html>");
		buffer.append("<head>");
		buffer.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		buffer.append("</head>");
		buffer.append("<body>	");
		buffer.append("<div id=\"pop_layer\">");
		buffer.append("<div style=\"padding: 20px;\">");
		buffer.append("<div class=\"mailbox\">");
		buffer.append("<div class=\"mail_txt\">");
		buffer.append("<p>" + content + "</p></br></br>");
		buffer.append("</div>");
		buffer.append("</div>");
		buffer.append("</div>");
		buffer.append("</div>");
		buffer.append("<table cellSpacing=0 cellPadding=0 width=705 align=left border=0>");
		buffer.append("<tbody>");
		buffer.append("<tr>");
		buffer.append("<td bgColor=\"#d9d9d9\" height=\"1\"></td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<td style=\"padding-right: 10px; padding-left: 10px; padding-bottom: 10px; padding-top: 10px\" background=\"" + ppsUrl + "/images/mail_footer_02.gif\">");
		buffer.append("<table cellSpacing=0 cellPadding=0 width=\"100%\" border=0>");
		buffer.append("<tbody>");
		buffer.append("<tr>");
		buffer.append("<td width=\"150\"><a href=\"" + ppsUrl + "\" name=\"" + ppsUrl + "\">");
		buffer.append("<img height=\"66px\" width=\"200px\" hspace=\"10\" src=\"http://www.marchespublics.cm/images/main/coleps.png\" border=\"0\"></a></td>");
		buffer.append("<td width=\"1\"");
		buffer.append("background=\"" + ppsUrl+ "/images/mail_footer_01.gif\"></td>");
		buffer.append("<td style=\"padding-left: 15px\">");
		buffer.append("<table cellSpacing=0 cellPadding=0 width=\"100%\" border=0>");
		buffer.append("<tbody>");
		buffer.append("<tr>");
		buffer.append("<td");
		buffer.append("style=\"font-size: 8pt; padding-bottom: 7px; color: #6b6b6b; line-height: 14px;font-family: Arial; text-decoration: none\">");
		buffer.append("This mail is just for notice Information. <br>"); // footer message 1 - 본 메일은 발신 전용 메일입니다.
		buffer.append("If you have any question, please contact <b> <a href=\"" + ppsUrl + "\" name=\"service_center\"> ");
		buffer.append("COLEPS</a></b>. <br>Thank you.<br>"); // footer message 2 - 상담을 원하시는 경우 고객센터로 문의하시기 바랍니다.
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<td background=\"" + ppsUrl + "/images/ad.jpg\" height=\"1\"></td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<td style=\"font-size: 8pt; color: #6b6b6b; line-height: 14px; padding-top: 7px; font-family: 돋움; TEXT-DECORATION: none\">");
		buffer.append("Copyright (C) 2014 Soft I Tech. All Rights Reserved.</td>");
		buffer.append("</tr>");
		buffer.append("</tbody>");
		buffer.append("</table>");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("</tbody>");
		buffer.append("</table>");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<td bgColor=#d9d9d9 height=1></td>");
		buffer.append("</tr>");
		buffer.append("</tbody>");
		buffer.append("</table>");
		buffer.append("</body>");
		buffer.append("</html>");
		
		return buffer.toString();
	}


	/**
	 * 메일 인증 : SMTP  Authenticator
	 * @fileName  : SendMail.java
	 * @package   : iq.kurd.com.co.mail
	 * @since     : 2018. 2. 26.
	 * @author    : Jin Kook JEONG
	 */
	private class SMTPAuthenticator extends Authenticator {
		
		PasswordAuthentication passwordAuthentication;
		
		SMTPAuthenticator(String userName, String password) {
			passwordAuthentication = new PasswordAuthentication(userName, password);
		}
		public PasswordAuthentication getPasswordAuthentication() {
			return passwordAuthentication;
		}
	}
	
	
}