package taskweb.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class LogoutController {

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String execute() throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getResponse();
		clearCookie(request, response, "/");
		request.getSession().removeAttribute("task_user");
		return "login";
	}

	public void clearCookie(HttpServletRequest request,
			HttpServletResponse response, String path) {
		Cookie[] cookies = request.getCookies();
		try {
			for (int i = 0; i < cookies.length; i++) {
				System.out.println(cookies[i].getName() + ":"
						+ cookies[i].getValue()+cookies[i].getPath());
				Cookie cookie = new Cookie(cookies[i].getName(), null);
				cookie.setMaxAge(0);
				cookie.setPath(path);// 根据你创建cookie的路径进行填写
				response.addCookie(cookie);
			}
		} catch (Exception ex) {
			System.out.println("清空Cookies发生异常！");
		}

	}
}
