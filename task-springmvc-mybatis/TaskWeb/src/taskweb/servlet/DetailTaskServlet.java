package taskweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import org.apache.commons.lang3.StringUtils;

import controller.TaskController;

public class DetailTaskServlet extends HttpServlet {

	private static final long serialVersionUID = 4960673265183712144L;

	public void init() throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginuser = (User) session.getAttribute("taskuser");
		if (loginuser == null) {
			response.sendRedirect("login");
		} else {
			TaskController controller = new TaskController();
			controller.DetailTask(request, response);
			ServletContext sc = getServletContext();
			RequestDispatcher rd = null;
			String taskid = request.getParameter("id");
			if (StringUtils.isBlank(taskid)) {
				rd = sc.getRequestDispatcher("/AddTask.jsp");
			} else {
				rd = sc.getRequestDispatcher("/task.jsp");
			}
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
