package com.kgisl.resume.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tika.exception.TikaException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import gate.util.GateException;

@Service
public class ResumeService {
	
	@Autowired
	private ResumeParserProgram resumeParserProgram;

	public List<HashMap<String, Object>> getMatchedResumes(String searchText, HttpServletRequest request) throws IOException, GateException, SAXException, TikaException {
		
		List<HashMap<String, Object>> matchedResumers = new ArrayList<>();
		
		String pathDir = System.getProperty("catalina.home"); 
		
		File resumeDirectory = new File(pathDir + File.separator + "Resumes"); 
		
		List<File> files = Arrays.asList(resumeDirectory.listFiles());
		
		files.stream().forEach(file -> {
			
			String[] fileName = file.getName().split("\\.(?=[^\\.]+$)");

			try {
				File tikkaConvertedFile = File.createTempFile(fileName[0], "." + fileName[1]);
				
				tikkaConvertedFile = resumeParserProgram.parseToHTMLUsingApacheTikka(file.getAbsolutePath());
				
				JSONObject parsedJSON = resumeParserProgram.loadGateAndAnnie(tikkaConvertedFile, request);
				
				if (parsedJSON.toString().contains(searchText)) {
					HashMap<String, Object> resume = new HashMap<>();

					resume.put("FileName", file.getName());
					resume.put("NoOfOccurrence", StringUtils.countMatches(parsedJSON.toString(), searchText));
					resume.put("FilePath", file.getAbsolutePath());
					resume.put("JSON", parsedJSON.toJSONString());

					matchedResumers.add(resume);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return matchedResumers;
	}

	public void downloadResumeByName(String filePath, HttpServletResponse response) throws IOException {
		
		File resume = new File(filePath); 
		
		filePath = filePath.replace("\\", "/");

		
		ServletOutputStream out = response.getOutputStream();

		response.setContentType("application/pdf");

		response.addHeader("Set-Cookie","fileDownload=true; path=/");
		response.addHeader("content-disposition", "attachment; filename=Receipt.pdf");

		InputStream inputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(resume));

		int octet;
		while ((octet =inputStream.read()) != -1)
			out.write(octet);

		inputStream.close();
		out.close();
	}

	public void saveResumes(MultipartFile[] files) throws IllegalStateException, IOException {
		
        String pathDir = System.getProperty("catalina.home"); 
		
        for(MultipartFile file : files){
			
        	File resume = new File(pathDir + File.separator + "Resumes" + File.separator + file.getOriginalFilename());
        	
        	file.transferTo(resume);
		}
	}
	
}
