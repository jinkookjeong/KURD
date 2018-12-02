package iq.kurd.com.uploader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import iq.kurd.com.util.format.StringUtil;
import iq.kurd.pt.ng.vo.NgFileVO;
import iq.kurd.pt.ng.service.NgBoardSvc;


@WebServlet("/pt/ng/rest/download")
public class DownloadServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	
	protected NgBoardSvc ngBoardService = null;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
   @Override
    public void init() throws ServletException
    {   
    	WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    	ngBoardService = (NgBoardSvc) wac.getBean("ngBoardService");	 		
    }
    
    protected void setCharacter(HttpServletRequest req, HttpServletResponse resp, String fileName)
    		throws ServletException, IOException
    {
        if(req.getProtocol().compareTo("HTTP/1.0")==0){
        	resp.setHeader("Pragma","no-cache");
        }else if(req.getProtocol().compareTo("HTTP/1.1")==0){
        	resp.setHeader("Cache-Control","no-cache");
        }
        resp.setDateHeader("Expires",0);
        resp.setContentType("text/plan; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-disposition", "attachment; filename="+fileName);
    }
    
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		File file = null;
		PrintWriter out = null;
		FileInputStream fis = null;
        BufferedInputStream fin = null;
        OutputStream opt = null;
        BufferedOutputStream fout = null;
        try {
			String fileRefId = StringUtil.nullConvert(request.getParameter("fileRefId"));
			String fileUuid = StringUtil.nullConvert(request.getParameter("fileUuid"));
			NgFileVO sampleNgFileVO = new NgFileVO();
			sampleNgFileVO.setFileRefId(fileRefId);
			sampleNgFileVO.setFileUuid(fileUuid);
			sampleNgFileVO = ngBoardService.selectFile(sampleNgFileVO);
			String savedUuid =  sampleNgFileVO.getFileUuid();
			String originFileNm =  sampleNgFileVO.getOriginFileNm();
			String phyFileNm =  sampleNgFileVO.getPhyFileNm();
			String phyUrlPath =  sampleNgFileVO.getPhyUrlPath();
			String ext =  sampleNgFileVO.getExt();
			
			file = new File(phyUrlPath + File.separator +savedUuid + File.separator + phyFileNm);
	        byte[] buffer = new byte[1024*4];
	
	        log.info("realPath: "+phyUrlPath + File.separator +savedUuid + File.separator + phyFileNm);
	
	        if(file.exists() && file.isFile() && file.canRead()){
	
	        	if(request.getHeader("User-Agent").indexOf("MSIE") > -1 ||
	                 request.getHeader("User-Agent").indexOf("Chrome") > -1){
	        		 originFileNm = URLEncoder.encode(originFileNm, "UTF-8").replace('+', ' ');
	            }else if(request.getHeader("User-Agent").indexOf("Firefox") > -1){
	            	originFileNm = "\"" + new String(originFileNm.getBytes("UTF-8"), "8859_1") + "\"";
	            }
	        	
	        	log.info("originFileNm: "+originFileNm);
	        	
	            response.setHeader("Content-Disposition", "attachment; filename="+originFileNm+";");
	            response.addHeader("Content-Length" , Long.toString(file.length()));
	
	            fis = new FileInputStream(file);
	            fin = new BufferedInputStream(fis);
	            opt = response.getOutputStream();
	            fout = new BufferedOutputStream(opt);
	
	            int read=0;
	            while((read =fin.read(buffer, 0, buffer.length)) != -1){
	                fout.write(buffer,0,read);
	            }
	        }else {
	            throw new Exception("The file is not exist.");
	        }
        }catch(Exception ex) {
        	log.error(ex.getMessage());
        	
        	String noDataMessage = "There is no file.";
           	response.reset();
            response.setContentType("text/html; charset=utf-8");
            out = response.getWriter();
            String reqURL = request.getRequestURL().toString();
            reqURL = reqURL.replaceAll(request.getServletPath(), "");
            out.println(noDataMessage);
            
        }finally {
        	try{if(fout != null){fout.close();}}catch(Exception ex) {log.error(ex.getMessage());};
        	try{if(fin != null ){fin.close();}}catch(Exception ex) {log.error(ex.getMessage());};
        	try{if(fis != null ){fis.close();}}catch(Exception ex) {log.error(ex.getMessage());};
        	try{if(opt != null ){opt.close();}}catch(Exception ex) {log.error(ex.getMessage());};
        	try{if(out != null ){out.close();}}catch(Exception ex) {log.error(ex.getMessage());};
        }
	}
	
}