package com.kgisl.resume.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.kgisl.resume.service.ResumeService;

import gate.util.GateException;

@RestController
@CrossOrigin
public class ResumeRestController {
	
	@Autowired
	private ResumeService resumeService;

	@GetMapping("/api/resume/search")
	public List<HashMap<String, Object>> getMatchedResumes(@RequestParam(name = "searchText") String searchText, HttpServletRequest request) throws IOException, GateException, SAXException, TikaException{
		
		List<HashMap<String, Object>> matchedResumes = resumeService.getMatchedResumes(searchText, request);
		
		return matchedResumes; 
	}
	
	@PostMapping("/api/resume/download")
	public ResponseEntity<Boolean> downloadResume(@RequestBody String filePath, HttpServletResponse response) throws IOException{
		
		resumeService.downloadResumeByName(filePath, response);
		
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
	
	@PostMapping(value = { "api/resume" })
	public ResponseEntity<String> fileAttachment(@RequestParam("file") MultipartFile[] files) throws IOException{
		
		System.out.println(files.length);
		
		resumeService.saveResumes(files);
		
		return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
	}
}
