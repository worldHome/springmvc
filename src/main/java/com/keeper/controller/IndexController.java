package com.keeper.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
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
		System.out.println(messageSource.getMessage("title.username", null,
				locale));
		return "message";
	}

	@RequestMapping(value = "/file")
	public String file() {
		return "file";
	}

	@RequestMapping(value = "/upload")
	public String upload(@RequestParam(value = "file") CommonsMultipartFile file)
			throws Exception {
		String fileName = file.getFileItem().getName();
		FileOutputStream outputStream = new FileOutputStream(new File("D:/"
				+ fileName));
		IOUtils.copy(file.getInputStream(), outputStream);
		outputStream.close();
		return "index";
	}
}