package taskweb.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import controller.tools.StringTool;
import service.TaskService;

@Controller
public class AddTaskController {
	@RequestMapping("/addtask")
	public String execute() throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		if (request.getParameter("title") == null) {
			return "addtask";
		}
		String title = request.getParameter("title").toString();
		String description = request.getParameter("description").toString();
		String executor = request.getParameter("executor").toString();
		String executendtime = request.getParameter("executendtime").toString();
		String type = request.getParameter("type").toString();
		Task task = new Task();
		task.setTitle(StringTool.ToUTF(title));
		task.setDescription(StringTool.ToUTF(description));
		task.setExecutor(StringTool.ToUTF(executor));
		task.setExecutestatus("0");
		task.setType(type);
		Date date = new Date();
		task.setStarttime(date);
		task.setEndtime(date);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		executendtime = StringTool.ToUTF(executendtime);
		Date executendDate = null;
		try {
			executendDate = sdf.parse(executendtime.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		task.setExecutendtime(executendDate);
		TaskService.instance.AddTaks(task);
		return "redirect:/tasks";
	}
}
