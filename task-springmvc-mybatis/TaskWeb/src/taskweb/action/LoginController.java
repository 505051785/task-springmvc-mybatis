package taskweb.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import service.TaskService;
import model.LoginVO;
import model.User;

@Controller
public class LoginController {
	
	
	protected final Log logger = LogFactory.getLog(getClass());

	private LoginVO loginVO;

	public LoginVO getLoginVO() {
		return loginVO;
	}

	public void setLoginVO(LoginVO loginVO) {
		this.loginVO = loginVO;
	}

	@RequestMapping("/login")
	public String execute() throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getResponse();
		User loginuser = (User) session.getAttribute("task_user");

		if (loginuser != null) {
			return "redirect:/tasks";
		}
		String usercode = "";
		String password = "";
		Cookie curUserCodeCookie = getCookie(request, "task_userCode");
		Cookie curPasswordCookie = getCookie(request, "task_password");
		if (curUserCodeCookie != null) {
			usercode = curUserCodeCookie.getValue();
			password = curPasswordCookie.getValue();
		} else {
			usercode = request.getParameter("usercode");
			password = request.getParameter("password");
		}
		if (StringUtils.isBlank(usercode) || StringUtils.isBlank(password)) {
			return "login";
		}
		logger.debug("usercode:"+usercode+";password:"+password);
		logger.info("info:usercode:"+usercode+";password:"+password);
		logger.error("error:usercode:"+usercode+";password:"+password);
		User user = TaskService.instance.Login(usercode);
		if (user.getUserCode() != null && user.getPassword().equals(password)) {
			session.setAttribute("task_user", user);
			if (curUserCodeCookie == null) {
				Cookie userCodecookie = new Cookie("task_userCode",
						user.getUserCode());
				userCodecookie.setPath("/");
				Cookie passwordcookie = new Cookie("task_password",
						user.getPassword());
				passwordcookie.setPath("/");
				response.addCookie(userCodecookie);
				response.addCookie(passwordcookie);
			}
			request.setAttribute("task_user", user);
			return "redirect:/tasks";
		} else {
			loginVO = new LoginVO();
			loginVO.setError("用户名或密码错误！");
			request.setAttribute("loginVO", loginVO);
			request.setAttribute("task_user", user);
			return "login";
		}
	}

	private Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie result = null;
		Cookie[] cookies = request.getCookies();
		// cookies不为空，则清除
		if (cookies != null) {
			for (Cookie cookieTemp : cookies) {
				String tempcookieName = cookieTemp.getName();
				// 查找身份串
				if (tempcookieName.equals(cookieName)) {
					result = cookieTemp;
					break;
				}
			}
		}
		return result;
	}
}
