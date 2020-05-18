
package com.infosys.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infosys.demo.utils.MenuItem;
import com.infosys.demo.utils.R;

/**
/** 
 *系统页面视图
 *author： Dean Peng email：dean.peng@infosys.com Date:May 16, 2020 5:01:02 PM
 */
@Controller
public class PageController {

	@RequestMapping("modules/{url}.html")
	public String module(@PathVariable("url") String url) {
		return "modules/" + url;
	}

	@RequestMapping(value = { "/", "index.html" })
	public String index() {
		return "index";
	}

	@RequestMapping("main.html")
	public String main() {
		return "main";
	}

	@RequestMapping("404.html")
	public String notFound() {
		return "404";
	}
	
	
	@ResponseBody
	@RequestMapping("menu/nav")
	public R nav() {
		List<MenuItem> menuList =new ArrayList<MenuItem>();

		MenuItem menu1=new MenuItem();
		menu1.setName("Transactions");
		menu1.setType(1);
		menu1.setUrl("modules/transactions.html");
		menu1.setIcon("fa fa-list");


		MenuItem menu2=new MenuItem();
		menu2.setName("Position");
		menu2.setType(1);
		menu2.setUrl("modules/position.html");
		menu2.setIcon("fa fa-cog");

		menuList.add(menu1);
		menuList.add(menu2);
		return R.ok().put("menuList", menuList);
	}
}
