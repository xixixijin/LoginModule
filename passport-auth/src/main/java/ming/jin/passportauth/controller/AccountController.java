package ming.jin.passportauth.controller;
import ming.jin.passportauth.annotation.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author haokun
 * 测试用例
 */
@RequestMapping("account")
@Controller
@Authentication
public class AccountController {
	@RequestMapping("getInfo")
	@ResponseBody
	public String getInfo() {
		return "getInfo success.";
	}
}
