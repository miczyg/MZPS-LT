package com.mzps.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.mobile.device.Device;

@Controller
public class AppController {
	static private String view = "views/";

	@RequestMapping("/")
	String home(ModelMap model, Device device) {
		model.addAttribute("title","MZPS Youth");
		return "index";
	}

	@RequestMapping("/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return view + page;
	}

}
