package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.cover.JacobCoverService;

@RestController
@RequestMapping("/ajax/jacob")
public class JacobController {

	@Autowired
	private JacobCoverService jacobService;
	
	@RequestMapping("/partnumber")
	public String ShowResult() {
		
		return jacobService.getPartNumber();
	}
	
	@RequestMapping("/select")
	public String ShowSelect() {
		
		return jacobService.getSelectionName();
	}
}
