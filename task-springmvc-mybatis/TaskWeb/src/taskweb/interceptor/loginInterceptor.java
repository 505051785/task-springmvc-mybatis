package taskweb.interceptor;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import service.TaskService;
import taskweb.common.exception.AuthorizationException;

public class loginInterceptor implements HandlerInterceptor {

	private List<String> excludedUrls;

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			org.springframework.web.servlet.ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String requestUri = request.getRequestURI();
		for (String url : excludedUrls) {
			if (requestUri.endsWith(url)) {
				return true;
			}
		}
		HttpSession session = request.getSession();
		User loginuser = (User) session.getAttribute("task_user");

		if (loginuser != null) {
			request.setAttribute("task_user", loginuser);
			return true;
		}
		String usercode = "";
		String password = "";
		Cookie curUserCodeCookie = getCookie(request, "task_userCode");
		Cookie curPasswordCookie = getCookie(request, "task_password");
		if (curUserCodeCookie != null) {
			usercode = curUserCodeCookie.getValue();
			password = curPasswordCookie.getValue();
		}
		if (StringUtils.isBlank(usercode) || StringUtils.isBlank(password)) {
			throw new AuthorizationException();
		}
		User user = TaskService.instance.Login(usercode);
		if (user.getUserCode() != null && user.getPassword().equals(password)) {
			session.setAttribute("task_user", user);
			if (curPasswordCookie == null) {
				Cookie userCodecookie = new Cookie("task_userCode", user.getUserCode());
				userCodecookie.setPath("/");
				Cookie passwordcookie = new Cookie("task_password", user.getPassword());
				passwordcookie.setPath("/");
				response.addCookie(userCodecookie);
				response.addCookie(passwordcookie);
			}
			request.setAttribute("task_user", user);
			return true;
		}
		response.sendRedirect("/login.jsp");
		return false;
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
