package com.keeper.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.keeper.vo.User;

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
		System.out.println("file");
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

	@RequestMapping(value = "/download")
	public void download(HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ URLEncoder.encode("file.png", "UTF-8") + "\"");
		IOUtils.copy(new FileInputStream(new File("D:/file.png")),
				response.getOutputStream());
		response.flushBuffer();
		response.getOutputStream().close();
	}

	@RequestMapping(value = "/getUserList")
	@ResponseBody
	public AjaxResult getUserList() {
		List<User> userList = new ArrayList<User>();
		User user = new User();
		user.setPassword("aabbcc");
		user.setUserName("CCC");
		userList.add(user);
		user = new User();
		user.setPassword("ffggmm得");
		user.setUserName("FFF");
		userList.add(user);
		return new AjaxResult().with(userList);
	}
}