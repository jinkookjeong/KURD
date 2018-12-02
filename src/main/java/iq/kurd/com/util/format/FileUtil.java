package iq.kurd.com.util.format;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import iq.kurd.com.constant.Constant;
import iq.kurd.com.util.format.DateUtil;


/**
 * 파일 처리  util :  file utilities
 * @fileName  : FileUtil.java
 * @package   : iq.kurd.com.util.format
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class FileUtil
{


    public FileUtil()
    {
    }
    
	/**
	 * 디렉토리 생성 : make directory
	 * @methodName : makeDir
	 * @return     : boolean
	 * @param path
	 */
	public boolean makeDir(String path)
	{
		synchronized(java.lang.Object.class)
		{	
			File fd=null;
			File ff=null;
			int idx = 1;
			String part = null;
			ff = new File(path);
			if(ff.exists()){
				ff = null;
				return true;
			}
			
			try {
				while(true) {
					//첫번째 File separator가 있는 인덱스를 얻는다. Archivo de separación para obtener el primer índice.
					idx = path.indexOf(File.separator, idx + 1);
					//인덱스 값에 따라 디렉토리를 생성, Dependiendo del valor del directorio de creación del índice
					//idx가 0보다 작으면 File separator가 없는 것이므로 path가 part와 같게 한다.
					//Si idx es inferior a 0 separador de archivo, de modo que usted no tiene un camino que es igual parte.
					if (idx < 0){
						part = path;
					}else{
						//part는 path중의 상위디렉토리 명이 된다. parte de la ruta del directorio de los padres es la gente.
						part = path.substring(0,idx);
					}
					//File 객체 생성 Creación de archivos de objetos
					fd = new File(part);
	
					//파일이 있는지, 디렉토리가 존재하는지 검사
					//Se trata de un archivo, busque en el directorio existe
					if (fd.exists() == false || fd.isDirectory() == false){
						//디렉토리를 만든다. Crear un directorio.
						if (fd.mkdir() == false){
							//디렉토리 생성에 실패 했으므로 Debido a que no pudo crear el directorio
							return false;
						}
					}
					//더이상 File separator가 존재하지 않으면 idx는 0보다 작게 된다.
					//Separador de archivo ya no existe idx será menor que 0.
					//idx가 보다 작으면 루프를 빠져나간다. Si idx es menor que está recibiendo el bucle.
					if (idx < 0){
						break;
					}
				}
				//디렉토리 생성 성공, Éxito la creación de directorio
				return true;
			} catch(Exception e) {				  
				return false;
			} finally { 
				fd = null;
			}
		}
	}
	

	/**
	 * 백업 디렉토리생성 : Creating a backup directory
	 * @methodName : MakeBackDir
	 * @return     : String
	 * @param cpPrgName
	 * @param cpBackupRoot
	 * @param cpDirName
	 */
	public String MakeBackDir ( String cpPrgName , String cpBackupRoot , String cpDirName )
	{
	   synchronized(java.lang.Object.class)
	   {
		   String caBackupDir="";
		   String caDate ="";
		   caDate = DateUtil.formatDate("yyyyMMddHHmmss");
	
		   caBackupDir= cpBackupRoot+File.separator+caDate.substring(0, 4)+File.separator+caDate.substring(4, 6)+File.separator+ caDate.substring(6, 8) + File.separator + cpDirName;
		   
		   if(makeDir(caBackupDir)){
			   return caBackupDir;
		   }else{
			   return "fail";
		   }
	   } 
	}
	

	/**
	 * 파일에 누적  : append file
	 * @methodName : AppendFile
	 * @return     : int
	 * @param cpOldFile
	 * @param cpNewFile
	 * @param PRGNAME
	 * @return
	 */
	public int AppendFile( String cpOldFile, String cpNewFile , String PRGNAME) 
	{
		synchronized(java.lang.Object.class)
		{
			File iNewFile = null;
			File iOldFile = null;
			BufferedReader br = null;
			BufferedWriter bw = null;
	
			try {
				iOldFile = new File( cpOldFile );
				iNewFile = new File( cpNewFile );
				br = new BufferedReader( new FileReader( iOldFile ) );
				bw = new BufferedWriter( new FileWriter( iNewFile, true ) );
				int i;
				while(( i = br.read() )!= -1) {
					bw.write(i);
				}
				br.close();
				bw.close();
			} catch (Exception e) {				
				return Constant.FAIL;
			} finally{
				if(br != null){ try { br.close(); } catch (Exception e) {}}
				if(bw != null){ try { bw.close(); } catch (Exception e) {}}
			}
			return Constant.SUCCESS;
		}
	}
	

	/**
	 * 파일복사 : Copy file
	 * @methodName : CopyFtoF
	 * @return     : int
	 * @param cpBeforePath
	 * @param cpAfterPath
	 */
	public int CopyFtoF( String cpBeforePath, String cpAfterPath ) 
	{
		synchronized(java.lang.Object.class)
		{
			File iNewFile = null;
			File iOldFile = null;
			BufferedReader br = null;
			BufferedWriter bw = null;
	
			try {
				iOldFile = new File( cpBeforePath );
				iNewFile = new File( cpAfterPath );
				br = new BufferedReader( new FileReader( iOldFile ) );
				bw = new BufferedWriter( new FileWriter( iNewFile, false ) );
				int i;
				while(( i = br.read() )!= -1) {
					bw.write(i);
				}
				br.close();
				bw.close();
			} catch (Exception e) {				
				return Constant.FAIL;
			} finally{
				if(br != null){ try { br.close(); } catch (Exception e) {}}
				if(bw != null){ try { bw.close(); } catch (Exception e) {}}
			}
			return Constant.SUCCESS;
		}
	}

    /**
     * src에서 dst로 파일의 위치를 변경 : file move
     * @methodName : FilMove
     * @return     : boolean
     * @param src
     * @param dst
     * @param filePath
     */
    public boolean FilMove(String src, String dst,String filePath){  
	
		try{  
	  
			File tmpDir= new File(filePath);  
			if(!tmpDir.isDirectory()){
				tmpDir.mkdirs(); 
			}
			tmpDir= null;  
		  
			File sf = new File(src);  
			File df = new File(dst);  
		            
			if (sf.renameTo(df)==true)   
			{  
				 return true;  
			}else  
			{  
				 return false;  
			}
		}catch(Exception e)  
		{	  
			return false;  
		}
    }

	/**
	 * 파일을 복사하기: file copy
	 * @methodName : FileCopy
	 * @return     : void
	 * @param in
	 * @param out
	 * @throws Exception
	 */
	public void FileCopy(File in, File out) throws Exception{  
	  synchronized(java.lang.Object.class)
	  {
        FileInputStream fis  = null;
        FileOutputStream fos = null;

        try{
            fis  = new FileInputStream(in);
            fos = new FileOutputStream(out);

            byte[] buf = new byte[1024];

            int i = 0;
            
            while((i=fis.read(buf))!=-1) {
                fos.write(buf, 0, i);
            }
        }catch(Exception e){
            throw e;
        }finally{
            try{ if(fis !=null){fis.close();}}catch(Exception ex){}
            try{ if(fos !=null){fos.close();}}catch(Exception ex){}         
        }
	  }
    }

    /**
	 * 파일을 복사하기: file copy
     * @methodName : FileCopy
     * @return     : void
     * @param fileName
     * @param fromDir
     * @param toDir
     * @throws Exception
     */
    public void FileCopy(String fileName, String fromDir, String toDir) throws Exception{
     synchronized(java.lang.Object.class)
	 {
       FileCopy(new File(fromDir + File.separator + fileName), new File(toDir + File.separator + fileName));
	  }
    }
    
    /**
	 * 파일을 복사하기: file copy
     * @methodName : FileCopy
     * @return     : void
     * @param in
     * @param out
     * @throws IOException
     */
    public void FileCopy(InputStream in, OutputStream out) throws IOException
    {
    	synchronized(java.lang.Object.class)
		{    	
	        BufferedInputStream bin = new BufferedInputStream(in);
	        BufferedOutputStream bout = new BufferedOutputStream(out);
	        byte buffer[] = new byte[1024];
	        for(int read = -1; (read = bin.read(buffer, 0, 1024)) != -1;){
	            bout.write(buffer, 0, read);
	        }
	        bout.flush();
	        bout.close();
	        bin.close();
		}
    }
    

    /**
     * 디렉토리의 파일의 배열형태의 이름 : file name of directory(Array)
     * @methodName : getFileList
     * @return     : String[]
     * @param dir
     * @throws Exception
     */
    public String[] getFileList(String dir) throws Exception{  
    	synchronized(java.lang.Object.class)
		{
	        File fin = new File(dir);  
			if (fin.exists() == false || fin.isDirectory() == false){
				throw new Exception("Directory  does not exist!");  
			}
	       return fin.list();
		}
    }
    

    /**
     * 디렉토리의 파일의 리스트를 구함 : List of files in a directory
     * @methodName : getFileList
     * @return     : File[]
     * @param dir
     */
    public static File[] getFileList(File dir)
    {
        String ss[] = dir.list();
        if(ss == null){
            return null;
        }
        int n = ss.length;
        File fs[] = new File[n];
        for(int i = 0; i < n; i++){
            fs[i] = new File(dir.getPath(), ss[i]);
        }
        return fs;
    }
    
 
    /**
     * 디렉토리의 파일의 개수룰 구하는 함수 : Count of files in a directory
     * @methodName : getFileCount
     * @return     : int
     * @param dir
     */
    public int getFileCount(String dir) {
    	synchronized(java.lang.Object.class)
		{
	    	String inlist[];
	        File fin = new File(dir);
	        inlist = fin.list();
	        return inlist.length;
		}
     }
    

    /**
     * 파일을 읽어서 String으로 반환 : read file
     * @methodName : readFromFile
     * @return     : String
     * @param _FilePath
     */
    public static String readFromFile(String _FilePath) {
    	synchronized(java.lang.Object.class)
		{	
	        FileInputStream fin = null ;
	        InputStreamReader reader = null ;
	        BufferedReader bReader = null ;
	        String line = "" ;
	        String CRLF = "\r\n" ;
	
	        try {
	            fin = new FileInputStream(_FilePath) ;
	            reader = new InputStreamReader(fin) ;
	            bReader = new BufferedReader(reader) ;
	
	            StringBuffer buff = new StringBuffer(1024 * 4);
	
	            while((line = bReader.readLine()) != null) {
	                buff.append(line+CRLF);
	            }
	            return buff.toString();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	            return null ;
	        }
	        finally {
	            try {
	                if (bReader!=null){ bReader.close();}
	            }catch(IOException ex){}
	
	            try {
	                if (reader!=null){ reader.close();}
	            }catch(IOException ex){}
	
	            try {
	                if (fin!=null){ fin.close();}
	            }catch(IOException ex){}
	        }
		}
    }


	/**
	 * 입력받은 저장 경로에 있는 파일을 String으로 변환 :Save the string as a file 
	 * @methodName : getNormalString
	 * @return     : String
	 * @param xString
	 */
	public String getNormalString(String xString) {
		String xmlString = "";
		RandomAccessFile chkin = null;
		try {
			chkin = new RandomAccessFile(new File(xString), "r");

			int currentbyte = (int) chkin.length();
			byte[] buf = new byte[currentbyte];
			chkin.read(buf, 0, currentbyte);
			xmlString = new String(buf, 0, currentbyte); 
		} catch (Exception ex) {
			//  
		} finally {
			try {
				if (chkin != null){
					chkin.close();
				}
			} catch (IOException ioe) {
			}
		}
		return xmlString;
	}
	

    /**
     * 파일삭제 : delete file
     * @methodName : delete
     * @return     : boolean
     * @param file
     */
    public boolean delete(File file)
    {
    	synchronized(java.lang.Object.class)
		{
	        if(file.exists())
	        {
	            if(file.isDirectory())
	            {
	                if(clean(file)){
	                    return file.delete();
	                }else{
	                    return false;
	                }
	            } else
	            {
	                return file.delete();
	            }
	        } else
	        {
	            return true;
	        }
		}
    }


    /**
     * 파일삭제 : clean file
     * @methodName : clean
     * @return     : boolean
     * @param file
     */
    public static boolean clean(File file)
    {
    	synchronized(java.lang.Object.class)
		{
	        if(file.isDirectory())
	        {
	            String filen[] = file.list();
	            for(int i = 0; i < filen.length; i++)
	            {
	                File subfile = new File(file, filen[i]);
	                if(subfile.isDirectory() && !clean(subfile)){
	                    return false;
	                }
	                if(!subfile.delete()){
	                    return false;
	                }
	            }
	
	        }
	        return true;
		}
    }


    /**
     * 디렉토리 인지 판단 : Determines whether the directory
     * @methodName : isDirectory
     * @return     : boolean
     * @param path
     */
    public static boolean isDirectory(String path)
    {
        boolean dir = false;
        if(path != null)
        {
            File file = new File(path);
            dir = file.isDirectory();
        }
        return dir;
    }

    /**
     * 파일 인지 판단 :Determines whether the file
     * @methodName : isFile
     * @return     : boolean
     * @param path
     */
    public static boolean isFile(String path)
    {
        boolean file = false;
        if(path != null)
        {
            File f = new File(path);
            file = f.isFile();
        }
        return file;
    }


    /**
     * CanonicalPath 정보를 반환한다. : Return CanonicalPath 
     * @methodName : toCanonicalPath
     * @return     : String
     * @param in
     */
    public static String toCanonicalPath(String in)
    {
        String DOT = ".";
        String current = toCurrentPath(in);
        String out = current;
        int index = -1;
        index = in.indexOf(DOT + DOT);
        if(index < 0){
            index = current.indexOf(File.separator + '.');
        }
        if(index < 0){
            index = current.indexOf('.' + File.separator);
        }
        if(index > -1 || in.startsWith(DOT) || in.endsWith(DOT))
        {
            File file = new File(current);
            try
            {
                out = file.getCanonicalPath();
            }
            catch(Exception e)
            {
                out = current;
                e.printStackTrace();
            }
        }
        return out;
    }


    /**
     * 자바 패스 정보로 전환한다 : to Java Path
     * @methodName : toJavaPath
     * @return     : String
     * @param in
     */
    public static String toJavaPath(String in)
    {
        String path = in;
        path = toCurrentPath(path);
        return path.replace('\\', '/');
    }

    /**
     * 현재 패스 정보로 전환한다 : to Current Path
     * @methodName : toCurrentPath
     * @return     : String
     * @param path
     */
    public static String toCurrentPath(String path)
    {
        String cPath = path;
        if(File.separatorChar == '/'){
            cPath = toShellPath(cPath);
        }else{
            cPath = toWindowsPath(cPath);
        }
        File file = new File(cPath);
        file = new File(file.getAbsolutePath());
        if(file.exists()){
            cPath = file.getAbsolutePath();
        }
        return cPath.trim();
    }


    /**
     * CanonicalPath 정보를 반환한다. : Return Shell Pass 
     * @methodName : toShellPath
     * @return     : String
     * @param inPath
     */
    public static String toShellPath(String inPath)
    {
        StringBuffer path = new StringBuffer();
        int index = -1;
        inPath = inPath.trim();
        index = inPath.indexOf(":\\");
        inPath = inPath.replace('\\', '/');
        if(index > -1)
        {
            path.append("//");
            path.append(inPath.substring(0, index));
            path.append('/');
            path.append(inPath.substring(index + 2));
        } else
        {
            path.append(inPath);
        }
        return path.toString();
    }


    /**
     * 윈도우 패스정보를 반환한다. : Return Window Pass 
     * @methodName : toWindowsPath
     * @return     : String
     * @param path
     */
    public static String toWindowsPath(String path)
    {
        String winPath = path;
        int index = winPath.indexOf("//");
        if(index > -1){
            winPath = winPath.substring(0, index) + ":\\" + winPath.substring(index + 2);
        }
        index = winPath.indexOf(':');
        if(index == 1){
            winPath = winPath.substring(0, 1).toUpperCase() + winPath.substring(1);
        }
        winPath = winPath.replace('/', '\\');
        return winPath;
    }
    
    /**
     * 파일명 구하기 : get file names
     * @methodName : getFileNames
     * @return     : String[]
     * @param mptRequest
     */
    public static String[] getFileNames(MultipartHttpServletRequest mptRequest){
    	
		/*
		 * 정렬하자 시작
		 */ 
		@SuppressWarnings("unchecked")
		Iterator<String> sortFileIt = mptRequest.getFileNames();
		ArrayList<String> tempList = new ArrayList<String>();

		while(sortFileIt.hasNext()){
			tempList.add(sortFileIt.next()); 
		}
		String[] fileNameArray = new String[tempList.size()];
		tempList.toArray(fileNameArray ); 
		
		for(int i = 0; i < fileNameArray.length - 1; i++){
			for(int j = i + 1; j < fileNameArray.length; j++){
				
				if(fileNameArray[i].compareTo(fileNameArray[j]) > 0){
					
					String tempFileName = fileNameArray[i];
					fileNameArray[i] = fileNameArray[j];
					fileNameArray[j] = tempFileName;
					
				}
			}
		}
		
		return fileNameArray;
    	
    }
    
}
