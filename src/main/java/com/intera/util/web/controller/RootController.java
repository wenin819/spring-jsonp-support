/**
 * Copyright (c) 2013, SuZhou USTC Star Information Technology CO.LTD
 * All Rights Reserved.
 */

package com.intera.util.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 *
 * @author   wenin819
 * @Date	 2013年9月7日 上午11:10:33
 */
@RequestMapping()
@Controller
public class RootController {
	
	@RequestMapping("getJson")
	@ResponseBody
	public List<String> getJson(String context) {
		return Arrays.asList((null != context) ? context : "test jsonp");
	}
	
	@RequestMapping("getJsonStr")
	@ResponseBody
	public String getJsonStr(String context) {
		return (null != context) ? context : "test jsonp";
	}
	
	@RequestMapping("getJsonObj")
	@ResponseBody
	public Object getJsonObj(String context) {
		return (null != context) ? context : "test jsonp";
	}
	
	@RequestMapping("getJsonArr")
	@ResponseBody
	public String[] getJsonArr(String context) {
		return new String[]{(null != context) ? context : "test jsonp"};
	}

}

