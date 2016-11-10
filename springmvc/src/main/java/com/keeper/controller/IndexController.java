package com.keeper.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class IndexController {
	@Autowired
	private ResourceBundleMessageSource messageSource;

	@RequestMapping(value = "/index")
	public String index(Model model) {
		model.addAttribute("msg", "Hello World");
		return "index";
	}

	@RequestMapping(value = "/i18")
	public String i18(Locale locale) {
		System.out.println(messageSource.getMessage("title.username", null, locale));
		return "message";
	}
	@RequestMapping(value = "/file")
	public void file(){
		
	}
	
	public String upload(@RequestParam(value = "file") CommonsMultipartFile file) {

		return "index";
	}
}
