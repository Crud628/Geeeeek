package com.jkshop.mysqldemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jkshop.mysqldemo.util.DBUtils;

/**
 * 控制层  接收前端选择
 * 
 * TODO
 * @date 2021年11月8日
 * @author Keason
 */
@Controller
public class SQLTestController {
	
	@Autowired
	private DBUtils dbUtils;
	
	
}
