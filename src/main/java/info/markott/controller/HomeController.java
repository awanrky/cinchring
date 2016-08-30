package info.markott.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mark on 8/29/16.
 */

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
}
