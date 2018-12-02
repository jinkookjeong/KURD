package  iq.kurd.com.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * <pre>
 * FileName      : ExcelManagerTest.java
* Package       : iq.kurd.pt.sample.board.web
 *
 * System Name   : 
 * Business Name : 
 * Description   : 
 *
 *
 * << Modification Information >>
 *   
 *  update date        updater       description
 *  ------------	  --------		---------------------------
 *  2018. 2. 17.		BSLEE         First Create
 *
 * </pre>
 * @author : 
 * @date   : 2018. 2. 17.
 *
 */
public class ExcelManager {
	 private int mStartRow = 1; //행의 시작점 초기값
	    private int mStartCol = 1; //열의 시작점 초기값
	    private int mWidth = 5000; // 각 열의 넓이 초기값
	    
	    private IndexedColors mHeaderColor =  IndexedColors.LIGHT_CORNFLOWER_BLUE; //헤더부분의 배경색
	    private IndexedColors mDataColor =  IndexedColors.WHITE; //데이터부분의 배경색

	    private XSSFWorkbook mWorkbook;

	    private String mSheetName = "sheet1";

	    private List<Object> mHeader; // 헤더 데이터 리스트 
	    private List<List<Object>> mData; // 데이터 정보 리스트
	    
	    private  Map<String, Object> map; // 컨트롤에서 map 형태로 list에 헤더정보 담을 시 사용
	    private  String headerTitle ; // Map의 key 정보
	    private  Object headerAlign; // Map의 value정보(정렬에 관한 value)
	    
	    private InputStream mReadFile;

	    public ExcelManager(List<Object> header, List<List<Object>> data) {
	        mHeader = header;
	        mData = data;
	    }
	    
	    public ExcelManager() {

	    }
	    
	    public ExcelManager(InputStream excelFile) {
	    	mReadFile = excelFile;
	    }

	    public void setStartRow(int startRow) {
	        mStartRow = startRow;
	    }

	    public void setStartCol(int startCol) {
	        mStartCol = startCol;
	    }

	    public void setSheetName(String sheetName) {
	        mSheetName = sheetName;
	    }

	    public void setWidth(int width) {
	        mWidth = width;
	    }

	    public void setHeaderColor(IndexedColors headerColor) {
	        mHeaderColor = headerColor;
	    }

	    public void setDataColor(IndexedColors dataColor) {
	        mDataColor = dataColor;
	    }

	    
	    @SuppressWarnings("unchecked")
		public byte[] makeExcel() {
	        mWorkbook = new XSSFWorkbook();
	        XSSFSheet sheet = mWorkbook.createSheet(mSheetName);
	        XSSFRow headerRow = sheet.createRow(mStartRow);

	        int headerCount = mHeader.size(); //헤더의 사이즈를 구함.
	     
	        for (int i=mStartCol; i<headerCount+mStartCol; i++) {
	            //XSSFCell headerCell = headerRow.createCell(i, XSSFCell.class.CELL_TYPE_STRING);
	            XSSFCell headerCell = headerRow.createCell(i);
	            
		            if(mHeader.get(i) instanceof Map<?, ?> ){ //넘어온 header 리스트의 값에  Map 데이터가 존재하는지.
		            	
		            	map = (Map<String, Object>) mHeader.get(i);
			            headerTitle =  (String) map.get("title");
		            	setCell(headerCell, headerTitle, mHeaderColor.getIndex()); //header의 데이터 셋팅과 스타일을 셋팅
		            	
		            } else { // header 리스트의 값이 String으로 넘어 왔는지.
		            	
		            	setCell(headerCell, String.valueOf(mHeader.get(i - mStartCol)), mHeaderColor.getIndex()); 
		            }
		            
	            sheet.setColumnWidth(i, mWidth);
	        }

	        int dataCount = mData.size();
	      
	        for (int i=mStartRow+1; i<dataCount+mStartRow+1; i++) {
	            XSSFRow dataRow = sheet.createRow(i);

	            List<Object> data = mData.get(i - (mStartRow + 1));

	            int count = data.size();

	            for (int j=mStartCol; j<count+mStartCol; j++) {
	                //XSSFCell dataCell = dataRow.createCell(j, XSSFCell.CELL_TYPE_STRING);
	                XSSFCell dataCell = dataRow.createCell(j);
	                
	                Object cell = data.get(j - mStartCol);
	                String cellStr;

	                if (cell instanceof Date) {
	                    cellStr = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",Locale.US).format(cell);
	                } else {
	                    cellStr = String.valueOf(cell);
	                }
	                if(mHeader.get(j) instanceof Map<?, ?> ){ //넘어온 header 리스트의 값에  Map 데이터가 존재하는지.
	                	
	                	map = (Map<String, Object>) mHeader.get(j);
	                	
	                	headerAlign =   map.get("alignment"); //header의 key 중 정렬에 대한 값이 존재하는지
	                	
	                	if(headerAlign == null){ //정렬에 대한 key  값이 없으면 기본값은 중앙 정렬로 지정
	                		
	                		
	                		//headerAlign = XSSFCellStyle.ALIGN_CENTER;
	                	} 
	                	
	                	setCell(dataCell, cellStr, mDataColor.getIndex(), (short) headerAlign);
	                	
	                } else {
	                	
	                	setCell(dataCell, cellStr, mDataColor.getIndex());
	                }
		          
	                sheet.setColumnWidth(i, mWidth);
	            }
	        }

	        byte[] bytes = new byte[0];

	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        try {
	            mWorkbook.write(out);
	            bytes = out.toByteArray();
	            out.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return bytes;
	    }
	    
	    public List<List<Object>> readExcel(){
	    	List<List<Object>> excelData = new ArrayList<List<Object>>();
	    	List<Object> cellData = null;
	    	
	        try {
				mWorkbook = new XSSFWorkbook(mReadFile);
				XSSFSheet sheet    =  null;
				XSSFRow row     =  null;
				XSSFCell cell    =  null;
				
				FormulaEvaluator evaluator = mWorkbook.getCreationHelper().createFormulaEvaluator();
				String data = "";
				
				int sheetNum =  mWorkbook.getNumberOfSheets();
				for(int i=0;i<sheetNum;i++){//시트가 여러개 있을 경우
				    sheet = mWorkbook.getSheetAt(i);
				    
				    int lastRowNum = sheet.getLastRowNum();
				    for(int r=sheet.getFirstRowNum();r<=lastRowNum;r++){//row를 읽는다.
				    	row = sheet.getRow(r);
				        if(row== null) continue;
				        
			    		int lastCellNum = row.getLastCellNum();
				        cellData = new ArrayList<Object>();
				        for(int c=row.getFirstCellNum();c<=lastCellNum;c++){//cell을 읽는다.
					        cell   =  row.getCell(c);
					        //if(cell== null) continue;
					        if(cell== null) {cellData.add(""); continue;}
					        
					        switch(cell.getCellType()){
					        
//						        case XSSFCell.CELL_TYPE_NUMERIC:
//						        
//						         
////						        	cellData.add(cell.getNumericCellValue());
//						        	cellData.add(new BigDecimal(cell.getNumericCellValue()).toPlainString());
//						        	break;
//						        case XSSFCell.CELL_TYPE_STRING:
//						        	cellData.add(cell.getStringCellValue());
//						        	break;
//						        case XSSFCell.CELL_TYPE_FORMULA :
//						        	//cellData.add(cell.getCellFormula());
//						        	if(!(cell.toString().equalsIgnoreCase("")) ){
//						        		if(evaluator.evaluateFormulaCell(cell)==HSSFCell.CELL_TYPE_NUMERIC){
//						        			double fddata = cell.getNumericCellValue();
//						        			DecimalFormat df = new DecimalFormat();
//						        			data = df.format(fddata);
//						        		}else if(evaluator.evaluateFormulaCell(cell)==HSSFCell.CELL_TYPE_STRING){
//						        			data = cell.getStringCellValue();
//						        		}else if(evaluator.evaluateFormulaCell(cell)==HSSFCell.CELL_TYPE_BOOLEAN){
//						        			boolean fbdata = cell.getBooleanCellValue();         
//						        			data = String.valueOf(fbdata);         
//						        		}
//						        		cellData.add(data);
//						        	}
//						        	break;
//						        default:
//						        	cellData.add("");
					       }
				        }
					    excelData.add(cellData);
				    }
					
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return excelData;
	    }

	    /**
	     * <pre>
	     * 
	     * Description
	     * 
	     * Header에 대한 list 값에 Map의 값이 없을 때 해당 함수 호출
	     * 
	     * </pre>
	     * 
	     * @author : 
	     * @date   : 2018. 2. 17.
	     * @param headerCell
	     * @param data
	     * @param index
	     */
	    private void setCell(XSSFCell headerCell, String data, short index) {
	    	//setCell(headerCell, data, index, XSSFCellStyle.ALIGN_CENTER);
	    }
	    
	    /**
	     * <pre>
	     * 
	     * Description
	     * 
	     * Cell에 대한 헤더와 데이터 정보를 setting 및  스타일 적용
	     * </pre>
	     * 
	     * @author : 
	     * @date   : 2018. 2. 17.
	     * @param headerCell
	     * @param data
	     * @param index
	     * @param align
	     */
	    private void setCell(XSSFCell headerCell, String data, short index, short align) {
	    	
	        headerCell.setCellValue(data);
	        
	        XSSFCellStyle cellStyle = mWorkbook.createCellStyle();

	        //cellStyle.setBorderBottom(XSSFCellStyle.this.getBorder . .BORDER_THIN);
	        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

	        //cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

	        //cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
	        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

	        //cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
	        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

	        //cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
	        cellStyle.setFillForegroundColor(index);

	        //cellStyle.setAlignment(align);

	        headerCell.setCellStyle(cellStyle);
	    }
}

