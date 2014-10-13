package com.p2p.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.p2p.global.Logger;

@Controller
public class TestController
{
	@Autowired
	private Logger logger;
	
	@RequestMapping(value="/string")
	@ResponseBody  
	public String string(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) 
	{
//		Logger.debug("debug");
//		Logger.info("info");
//		Logger.error("error");
//		Logger.error(new Exception("exception"));
		logger.info(name);
		return "hello 姓名： "+ name;
	}

	@RequestMapping("/jsp")
	public String jsp(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) 
	{
		model.addAttribute("name", name);
		return "hello";
	}

	@RequestMapping(value = "/json")
	@ResponseBody  
	public Map<String,String> json(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) 
	{
		Map<String,String> m = new HashMap<>();
		m.put("a","汉字");
		m.put("b",name+",OK");
		return m;
	}

	@RequestMapping(value="/err")
	@ResponseBody  
	public String err(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) 
	{
		throw new RuntimeException("发生错误");
	}

	@RequestMapping("/errjsp")
	public String errjsp(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) 
	{
		return "error";
	}

}