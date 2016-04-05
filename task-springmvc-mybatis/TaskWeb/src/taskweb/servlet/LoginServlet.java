package taskweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.TaskController;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 4960673265183712144L;

	public void init() throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TaskController controller = new TaskController();
		boolean flag = controller.Login(request, response);
		ServletContext sc = getServletContext();
		RequestDispatcher rd = null;
		if (flag) {
			response.sendRedirect("tasks");
		} else {
			rd = sc.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
