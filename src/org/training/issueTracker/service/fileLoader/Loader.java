package org.training.issueTracker.service.fileLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import org.apache.log4j.Logger;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issueTracker.service.exceptions.InputException;



public class Loader {

	private final String errRecieve  	= "Problem whith receiving file";
	private final String errCloseUpl 	= "Problem whith closing resource when try to upload file  ";
	private final String errReadFile 	= "Problem whith reading file";
	private final String errFileName 	= "File not found ";
	private final String errFileNFound 	= "File not found on server ";
	private final String errReadSvrFile = "Can't read file from server " ;
	private final String errCloseStream = " streamings for downloading files that were closed with error ";
	private Logger logger= Logger.getLogger(Loader.class);
	
	private  String filename = ""; 
	
	
		
	
	
	
	public String getFilename() {
		return filename;
	}
	
	
	
	public void	chekFile(String fileName , String savePath ) throws InputException{
		
		File file = new File(savePath+"resources\\Attachments\\" + fileName);
	

		
		if (!file.isFile()){
			
			throw new InputException(errFileNFound);
			
		}
		

	
	}
	
	public void	uploadFile(HttpServletRequest request, String savePath ) throws InputException{
		
		
		ServletInputStream in = null;
		
		try{
			in = request.getInputStream(); 
			
			byte[] line = new byte[128]; 
			int i = in.readLine(line, 0, 128); 
			int delimiterLength = i - 2; 
			String delimiter = new String(line, 0, delimiterLength); 
			
				while (i != -1) {
					String newLine = new String(line, 0, i); 
					
					if (newLine.startsWith("Content-Disposition: form-data; name=\"")) {
						
						String s = new String(line, 0, i-2); 
						
						int pos = s.indexOf("filename=\""); 
					
						if (pos != -1) {
							

							String filepath = s.substring(pos+10, s.length()-1); 
					       							
							
							pos = filepath.lastIndexOf("\\"); 
							
							if (pos != -1) {
								filename = filepath.substring(pos + 1);
							}else{ 
								filename = filepath; 
							} 
							
							
							i = in.readLine(line, 0, 128); 
							i = in.readLine(line, 0, 128); 
							  
							
							i = in.readLine(line, 0, 128); 
	  
	    
							ByteArrayOutputStream buffer = new ByteArrayOutputStream(); 

							
							newLine = new String(line, 0, i); 
							
							while (i != -1 && !newLine.startsWith(delimiter)) {
						    
						        buffer.write(line, 0, i); 
						        i = in.readLine(line, 0, 128); 
						        newLine = new String(line, 0, i); 
							} 
							
							 RandomAccessFile file = null;
							 
							try {
						      if (!filename.trim().isEmpty()) {
						    	 

						        file = new RandomAccessFile((savePath+"resources\\Attachments\\") + filename, "rw"); 
						      

						        byte[] bytes = buffer.toByteArray(); 
						        file.write(bytes, 0, bytes.length - 2); 
						      }else {
						    	  throw new InputException(errFileName);
						      }
														
							} catch(IOException e){ 
								

								throw new InputException(errRecieve);
							
							}finally{
								try{
							        if(file!= null){
										file.close();
									}
							        if(buffer!= null){
							        	buffer.close();
									}
						        }catch(IOException e){ 
						        	logger.error( errCloseUpl + e.getMessage() );

								}
						       
						    }
						}
					} 
					
					i = in.readLine(line, 0, 128); 
	
				} 
		}catch(IOException e){
				

				throw new InputException(errReadFile);
		}finally{
			
			try{
		        if(in!= null){
					in.close();
				}
		   			
	        }catch(IOException e){ 
	        	logger.error( errCloseUpl + e.getMessage() );

			}
		}
		
	}
		
	
	
	public void	downloadFile(HttpServletResponse response ,String fileName, String savePath ) throws InputException {
		
		FileInputStream in = null; 
		PrintWriter out= null;
		
		try{
			
			out = response.getWriter();
		  
			in =   new java.io.FileInputStream(savePath+"resources\\Attachments\\" + fileName ); 
		  
		
			int i; 
			while ((i=in.read()) != -1) {
				

				out.write(i); 
			} 
			in.close(); 
			out.close(); 
		}catch(FileNotFoundException e ){
			
			throw new InputException(errFileNFound);
		}catch(IOException e ){
			
			logger.error(errReadSvrFile+ e.getMessage());
		}finally{
			try {
				if(in !=null){
				in.close();
				}
				
				if(out !=null){
					out.close();
					}
			} catch (IOException e) {
				
				logger.error(errCloseStream);
	
			}
			
			
		}
		 	
	 	

	
	
	
}

}
