package iq.kurd.com.uploader;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import iq.kurd.com.util.format.StringUtil;
import iq.kurd.pt.ng.vo.NgFileVO;
import iq.kurd.pt.ng.service.NgBoardSvc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet("/pt/ng/rest/upload")
public class UploadReceiver extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static String rootPath = "d:/temp/upload/";
	//private static String saveFilePath = "d:/temp/upload/2018/11/09";
	//private static String saveFileTempPath = "d:/temp/upload/2018/11/09/temp";
	
    //private static File UPLOAD_DIR = new File(saveFilePath);
    private static File UPLOAD_DIR = null;
    //private static File TEMP_DIR = new File(saveFileTempPath);
    private static File TEMP_DIR = null;

    private static String CONTENT_LENGTH = "Content-Length";
    private static int SUCCESS_RESPONSE_CODE = 200;

    final Logger log = LoggerFactory.getLogger(UploadReceiver.class);
    
    protected NgBoardSvc ngBoardService = null;
   
    @Override
    public void init() throws ServletException
    {
        //UPLOAD_DIR.mkdirs();    	
    	initMakeDir(); //Make Directory
    	WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    	ngBoardService = (NgBoardSvc) wac.getBean("ngBoardService");
    }
    
    protected void setCharacter(HttpServletRequest req, HttpServletResponse resp)
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
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
    {	
    	 setCharacter(req,resp);
    	 PrintWriter writer = null;
    	 JSONArray jsonList = new JSONArray();
    	 JSONObject obj = null;
    	 String fileRefId = StringUtil.nullConvert((String)req.getParameter("fileRefId"));
    	 String userId = StringUtil.nullConvert((String)req.getParameter("userId"));
         String pageId = StringUtil.nullConvert((String)req.getParameter("pageId"));
         String init = StringUtil.nullConvert((String)req.getParameter("init"));
         
         log.info("userId=>"+userId+" pageId : "+pageId+" fileRefId: "+fileRefId);
         if(userId.equals("")) {
     	 	throw new IOException("parameter Error");
     	}
         
    	//상세 페이지일 경우 이미 저장된 첨부파일을 DB에서 읽어온다.    	
    	try {	
	    	if(init.equals("N")) { //초기 화면은 그냥 넘김, 저장됬던거 수정화면 넘어갈때 처리할 부분만 처리함!	    			  
	    		  
	    		NgFileVO ngFileVO = new NgFileVO();
	    		ngFileVO.setUserId(userId);
	    		ngFileVO.setPageId(pageId);
	    		ngFileVO.setFileRefId(fileRefId);
	    		if(init.equals("Y")) { //초기 신규생성화면일때
	    			ngFileVO.setDelYn("N");
	    		}else {
	    			ngFileVO.setStatus("Y"); //상세화면일땐 저장했던것만 읽어옴 	
	    		}
	    		
	    		log.debug(ngFileVO.toString());
	    		ngFileVO = ngBoardService.selectFileList(ngFileVO);
	    		List<NgFileVO> list = ngFileVO.getNgFileVOList();
	    		for (int i = 0; i < list.size(); i++) {
	    			NgFileVO tempVO = (NgFileVO)list.get(i);
	    			
	    			String fileName = (String)tempVO.getOriginFileNm();
	    			String fileUuid = (String)tempVO.getFileUuid();
	    			int fileSize = (int)tempVO.getFileSize();
	    			
	    	    	obj = new JSONObject();
	    	        obj.put("name", fileName);
	    	        obj.put("uuid", fileUuid);
	    	        obj.put("size", fileSize);
	    	        obj.put("thumbnailUrl", "/js/fine-uploader/placeholders/document.png");
	    	        jsonList.add(obj);
				}
    		}
    	}catch(Exception ex) {
    		log.error(ex.getMessage());
    		throw new IOException(ex.getMessage());    		
    	}finally {
    		writer = resp.getWriter();
    		String jsonFile = jsonList.toJSONString();        
 	        log.info(jsonFile); 	        
	        writer.print(jsonFile);
	        
    		try{ if(writer!= null) writer.close(); writer = null; }catch(Exception ex) { ex.printStackTrace();};
    	}
    }
    
    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
    {	
    	setCharacter(req,resp);
    	String uuid = "", fileRefId = ""; //uuid는 FineUploader에서 자동으로 붙어버림 
    	
        String userId = StringUtil.nullConvert((String)req.getParameter("userId"));
        String pageId = StringUtil.nullConvert((String)req.getParameter("pageId"));
        String realDel = StringUtil.nullConvert((String)req.getParameter("realDel")); //Y: 진짜 삭제, N : DB상태만 바꿈
        String rcvFileRefId = StringUtil.nullConvert((String)req.getParameter("fileRefId"));
        log.info("userId=>"+userId+", rcvPageId : "+pageId+", rcvFileRefId : "+rcvFileRefId+", realDel: "+realDel);
        
    	int idx = rcvFileRefId.indexOf("/");
    	if(idx > -1) {
    	  fileRefId = rcvFileRefId.substring(0, idx);
          uuid = rcvFileRefId.substring(idx+1);
    	}
    	if(userId.equals("") || pageId.equals("") || uuid.equals("")) {
    		throw new IOException("parameter Error");
    	}
    	//String uuid = req.getPathInfo().replaceAll("/", "");
    	log.info("pageID: "+pageId+", uuid: "+uuid);
        handleDeleteFileRequest(userId, pageId, fileRefId, uuid, realDel, resp);
    }

    private void handleDeleteFileRequest(String userId, String pageId, String fileRefId, String uuid, String realDel, HttpServletResponse resp) throws IOException
    {
        try {
        	NgFileVO ngFileVO = new NgFileVO();
        	ngFileVO.setUserId(userId);
        	ngFileVO.setPageId(pageId);
        	ngFileVO.setFileUuid(uuid);
        	ngFileVO.setFileRefId(fileRefId);
        	ngFileVO.setDelYn("N"); //삭제안된것
     		
        	ngFileVO = ngBoardService.selectFileList(ngFileVO);
     		
     		if(realDel.equals("N")) { //only db update
     			log.info("update del_yn -> y ");
     			ngBoardService.updateDelYnField(ngFileVO);
	     		
     		}else{  //Real delete(include DB) 
     			
     			List<NgFileVO> list = ngFileVO.getNgFileVOList();     			
     			
	     		log.info("select list to delete data "+list);
	     		if(list != null) {
		     		for (int i = 0; i < list.size(); i++) {
		    			NgFileVO tempVO = (NgFileVO)list.get(i);
						String savedFilePath = (String)tempVO.getPhyUrlPath();
			    	
			     		File SAVED_DIR = new File(savedFilePath);
			    		log.info("DELETE SAVED_DIR: "+savedFilePath+ ", uuid: "+uuid+" : "+i);
			    		
				    	FileUtils.deleteDirectory(new File(SAVED_DIR, uuid));
				
				        if (new File(SAVED_DIR, uuid).exists()){
				            log.warn("couldn't find or delete " + uuid);
				        }else{
				            log.info("deleted " + uuid);
				        }
		     		}
	     		}
	        	ngBoardService.deleteFile(ngFileVO);
     		}
     		
        }catch(Exception ex) {
        	ex.printStackTrace();
        	log.error(ex.getMessage());
    		throw new IOException(ex.getMessage());
        }finally {
        	
        	resp.setStatus(SUCCESS_RESPONSE_CODE);
        }
    }

    @Override
    public void doOptions(HttpServletRequest req, HttpServletResponse resp)
    {
        resp.setStatus(SUCCESS_RESPONSE_CODE);
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:8888");
        //resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.addHeader("Access-Control-Allow-Methods", "POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "x-requested-with, cache-control, content-type");
    }
    
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
    {
		setCharacter(req,resp);
    	Map<String, String> paramMap = null;
    	String userId = "", pageId = "", fileRefId = "";
    	RequestParser requestParser = null;
    	boolean isIframe = req.getHeader("X-Requested-With") == null || !req.getHeader("X-Requested-With").equals("XMLHttpRequest");
    	
    	 String remoteAddr = req.getHeader("X-FORWARDED-FOR");
         if (remoteAddr == null || "".equals(remoteAddr)) {
             remoteAddr = req.getRemoteAddr(); // request IP, Address, information
         }
         
    	String inputTime = StringUtil.getTimeStamp();
    	log.info("remoteAddr=> "+remoteAddr+", inputTime=>"+inputTime);
    	
        try {
        	
        	initMakeDir(); //Make Directory
        	
        	resp.setContentType("text/plain");
            resp.setStatus(SUCCESS_RESPONSE_CODE);
            //resp.setContentType(isIframe ? "text/html" : "text/plain");
            //resp.addHeader("Access-Control-Allow-Origin", "http://192.168.130.118:8080");
            //resp.addHeader("Access-Control-Allow-Credentials", "true");
            //resp.addHeader("Access-Control-Allow-Origin", "*");
            fileRefId = StringUtil.nullConvert((String)req.getParameter("fileRefId"));
            userId = StringUtil.nullConvert((String)req.getParameter("userId"));
            pageId = StringUtil.nullConvert((String)req.getParameter("pageId"));
            String signature = StringUtil.nullConvert((String)req.getParameter("signature"));
            System.out.println("signature => "+signature);
            
            log.debug("userId=>"+userId+" pageId : "+pageId);
            if(userId.equals("") || pageId.equals("")) {
        		throw new IOException("parameter Error");
        	}
            
            if (ServletFileUpload.isMultipartContent(req)){
            	
                MultipartUploadParser multipartUploadParser = new MultipartUploadParser(req, TEMP_DIR, getServletContext());
                requestParser = RequestParser.getInstance(req, multipartUploadParser);
                writeFileForMultipartRequest(requestParser);
                paramMap = (Map<String, String>)multipartUploadParser.getParams();
                writeResponse(resp.getWriter(), requestParser.generateError() ? "Generated error" : null, isIframe, false, requestParser);
            }else{
                requestParser = RequestParser.getInstance(req, null);

                //handle POST delete file request
                if (requestParser.getMethod() != null && requestParser.getMethod().equalsIgnoreCase("DELETE")) {
                    String uuid = requestParser.getUuid();
                    handleDeleteFileRequest(userId, pageId, fileRefId, uuid, "Y", resp); //Y : real delete
                }else{
                    writeFileForNonMultipartRequest(req, requestParser);                                        
                    writeResponse(resp.getWriter(), requestParser.generateError() ? "Generated error" : null, isIframe, false, requestParser);
                }
            }
        } catch (Exception e) {
            log.error("Problem handling upload request", e);
            if (e instanceof MergePartsException){
                writeResponse(resp.getWriter(), e.getMessage(), isIframe, true, requestParser);
            }else{
                writeResponse(resp.getWriter(), e.getMessage(), isIframe, false, requestParser);
            }
        }finally {
        	try {
        		String extension = "";
        		String fileName = (String)paramMap.get("qqfilename");
        		int idx = fileName.lastIndexOf(".") +1; 
        		if(idx > 0) {
        			extension = fileName.substring(idx, fileName.length() );
        		}
                
        		NgFileVO sampleNgFileVO = new NgFileVO();
        		sampleNgFileVO.setUserId(userId);
        		sampleNgFileVO.setPageId(pageId);
        		sampleNgFileVO.setFileUuid(paramMap.get("qquuid"));
        		sampleNgFileVO.setOriginFileNm(fileName);
        		sampleNgFileVO.setFileRefId(fileRefId);
        		sampleNgFileVO.setPhyUrlPath(UPLOAD_DIR.getPath());
        		sampleNgFileVO.setPhyFileNm(paramMap.get("qquuid")+"."+extension);
        		sampleNgFileVO.setFileSize(Integer.parseInt((paramMap.get("qqtotalfilesize"))));
        		sampleNgFileVO.setExt(extension);
        		sampleNgFileVO.setReqIp(remoteAddr);
        		
        		log.debug(sampleNgFileVO.toString());
        		ngBoardService.insertFile(sampleNgFileVO);
			} catch (Exception ex) {				
				log.error(ex.getMessage());
				throw new IOException(ex.getMessage());
			}
        }
    }

    private void writeFileForNonMultipartRequest(HttpServletRequest req, RequestParser requestParser) throws Exception
    {
        File dir = new File(UPLOAD_DIR, requestParser.getUuid());
        dir.mkdirs();

        String contentLengthHeader = req.getHeader(CONTENT_LENGTH);
        long expectedFileSize = Long.parseLong(contentLengthHeader);

        if (requestParser.getPartIndex() >= 0)
        {
            writeFile(req.getInputStream(), new File(dir, requestParser.getUuid() + "_" + String.format("%05d", requestParser.getPartIndex())), null);

            if (requestParser.getTotalParts()-1 == requestParser.getPartIndex()){
                File[] parts = getPartitionFiles(dir, requestParser.getUuid());
                File outputFile = new File(dir, requestParser.getFilename());
                for (File part : parts){
                    mergeFiles(outputFile, part);
                }

                assertCombinedFileIsVaid(requestParser.getTotalFileSize(), outputFile, requestParser.getUuid());
                deletePartitionFiles(dir, requestParser.getUuid());
            }
        }else{
            writeFile(req.getInputStream(), new File(dir, requestParser.getFilename()), expectedFileSize);
        }
    }

    private void writeFileForMultipartRequest(RequestParser requestParser) throws Exception
    {
        File dir = new File(UPLOAD_DIR, requestParser.getUuid());
        dir.mkdirs();

        if (requestParser.getPartIndex() >= 0){
            writeFile(requestParser.getUploadItem().getInputStream(), new File(dir, requestParser.getUuid() + "_" + String.format("%05d", requestParser.getPartIndex())), null);

            if (requestParser.getTotalParts()-1 == requestParser.getPartIndex()){
                File[] parts = getPartitionFiles(dir, requestParser.getUuid());
                File outputFile = new File(dir, requestParser.getOriginalFilename());
                for (File part : parts){
                    mergeFiles(outputFile, part);
                }
                assertCombinedFileIsVaid(requestParser.getTotalFileSize(), outputFile, requestParser.getUuid());
                deletePartitionFiles(dir, requestParser.getUuid());
            }
        }else{
            String extension = "";
        	String fileName = requestParser.getFilename();
    		int idx = fileName.lastIndexOf(".") +1; 
    		if(idx > 0) {
    			extension = fileName.substring(idx, fileName.length() );
    		}
    		//writeFile(requestParser.getUploadItem().getInputStream(), new File(dir, requestParser.getFilename()), null);
            writeFile(requestParser.getUploadItem().getInputStream(), new File(dir, requestParser.getUuid()+"."+extension), null);
        }
    }

    private void assertCombinedFileIsVaid(long totalFileSize, File outputFile, String uuid) throws MergePartsException
    {
        if (totalFileSize != outputFile.length()){
            deletePartitionFiles(UPLOAD_DIR, uuid);
            outputFile.delete();
            throw new MergePartsException("Incorrect combined file size!");
        }
    }


    private static class PartitionFilesFilter implements FilenameFilter
    {
        private String filename;
        PartitionFilesFilter(String filename){
            this.filename = filename;
        }

        @Override
        public boolean accept(File file, String s)
        {
            return s.matches(Pattern.quote(filename) + "_\\d+");
        }
    }

    private static File[] getPartitionFiles(File directory, String filename)
    {
        File[] files = directory.listFiles(new PartitionFilesFilter(filename));
        Arrays.sort(files);
        return files;
    }

    private static void deletePartitionFiles(File directory, String filename)
    {
        File[] partFiles = getPartitionFiles(directory, filename);
        for (File partFile : partFiles){
            partFile.delete();
        }
    }

    private File mergeFiles(File outputFile, File partFile) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(outputFile, true);

        try{
            FileInputStream fis = new FileInputStream(partFile);

            try{
                IOUtils.copy(fis, fos);
            }finally{
                IOUtils.closeQuietly(fis);
            }
        }finally{
            IOUtils.closeQuietly(fos);
        }

        return outputFile;
    }

    private File writeFile(InputStream in, File out, Long expectedFileSize) throws IOException
    {
        FileOutputStream fos = null;

        try{
            fos = new FileOutputStream(out);

            IOUtils.copy(in, fos);

            if (expectedFileSize != null){
                Long bytesWrittenToDisk = out.length();
                
                if (!expectedFileSize.equals(bytesWrittenToDisk)){
                	
                    log.warn("Expected file {} to be {} bytes; file on disk is {} bytes", new Object[] { out.getAbsolutePath(), expectedFileSize, 1 });
                    out.delete();
                    throw new IOException(String.format("Unexpected file size mismatch. Actual bytes %s. Expected bytes %s.", bytesWrittenToDisk, expectedFileSize));
                }
            }

            return out;
        }catch (Exception e){
            throw new IOException(e);
        }finally{
            IOUtils.closeQuietly(fos);
        }
    }

    private void writeResponse(PrintWriter writer, String failureReason, boolean isIframe, boolean restartChunking, RequestParser requestParser)
    {
    	JSONObject obj = new JSONObject();
        if (failureReason == null){        	
        	obj.put("success", "true");
        	String result = obj.toJSONString();        	
        	writer.print(result);
            //writer.print("{\"success\": true}");
        }else{
            if (restartChunking){
            	obj.put("error", failureReason);
            	obj.put("reset", "true");
            	String result = obj.toJSONString();
            	writer.print(result);            	
                //writer.print("{\"error\": \"" + failureReason + "\", \"reset\": true}");
            }else{
            	obj.put("error", failureReason);            	
            	String result = obj.toJSONString();
            	writer.print(result);
                //writer.print("{\"error\": \"" + failureReason + "\"}");
            }
        }
    }

    private class MergePartsException extends Exception
    {
        MergePartsException(String message){
            super(message);
        }
    }
    
    public void initMakeDir() {    	
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		String dateStr = df.format(new Date());

		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(4, 6);
		String day = dateStr.substring(6, 8);
		String uploadDir = rootPath + year + File.separator + month + File.separator + day;
		String uploadTmpDir = rootPath + year + File.separator + month + File.separator + day+File.separator+"temp";
		File fd = new File(uploadTmpDir);
		if(!fd.exists()) {
			fd.mkdirs();
		}
		UPLOAD_DIR = new File(uploadDir);
		TEMP_DIR = new File(uploadTmpDir);
		log.debug("UPLOAD_DIR =>"+UPLOAD_DIR.getPath());
	}
}