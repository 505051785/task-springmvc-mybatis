package taskweb.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import model.Task;
import model.TaskVO;
import model.TasksVO;
import service.TaskService;
import controller.cenum.ExcuteStatusEnum;
import controller.cenum.TaskTypeEnum;

public class TasksAction {
	
	private TasksVO tasksVO ;

	public String execute() throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		TaskService taskService = new TaskService();
		String strPageNo = request.getParameter("p");
		int perPageNum = 5;
		int intPageNo = 1;
		if (!StringUtils.isBlank(strPageNo)) {
			intPageNo = Integer.parseInt(strPageNo);
		}
		TasksVO tasksVO = new TasksVO();
		List<TaskVO> taskList = null;
		int totalPages = 0;
		try {
			taskList = taskService.GetTasks(perPageNum, intPageNo);
			for (TaskVO taskVO : taskList) {
				Task task = taskVO.getTask();
				task.setExecutestatus(ExcuteStatusEnum.resStatus(Integer.parseInt(task.getExecutestatus())));
				task.setType(TaskTypeEnum.resStatus(Integer.parseInt(task.getType())));
			}
			totalPages = taskService.GetTasksCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tasksVO.setTaskList(taskList);
		tasksVO.setPageFooter(calFooter(totalPages, perPageNum, intPageNo));
		request.setAttribute("tasksVO", tasksVO);
		return "";
	}
	
	public String calFooter(int totalPages, int perPageNum, int intPageNo) {
		StringBuilder sb = new StringBuilder();

		if (intPageNo > 1) {
			sb.append("<span>");
			sb.append("<a href=tasks?p=");
			sb.append(intPageNo-1);
			sb.append(">");
			sb.append("上一页");
			sb.append("</a>");
			sb.append("</span>");
			sb.append("...");
		}else{
			sb.append("<span class=\"disabled\">上一页</span>");
		}
		int pageNum = totalPages % perPageNum == 0 ? totalPages / perPageNum : (totalPages / perPageNum + 1);
		if (intPageNo > (pageNum - 3)) {//默认3个页签
			for (int i = intPageNo; i <= pageNum; i++) {
				sb.append("<span>");
				sb.append("<a href=tasks?p=");
				sb.append(i);
				sb.append(">");
				sb.append(i);
				sb.append("</a>");
				sb.append("</span>");
			}
		} else {
			for (int i = intPageNo; i < intPageNo + 3; i++) {
				sb.append("<span>");
				sb.append("<a href=tasks?p=");
				sb.append(i);
				sb.append(">");
				sb.append(i);
				sb.append("</a>");
				sb.append("</span>");
			}
			sb.append("...");
		}
		sb.append("<span>");
		sb.append("<a href=tasks?p=");
		sb.append(intPageNo+1);
		sb.append(">");
		sb.append("下一页");
		sb.append("</a>");
		sb.append("</span>");
		
		sb.append("<span>");
		sb.append("共");
		sb.append(pageNum);
		sb.append("页");
		sb.append("</span>");
		sb.append("到第");
		return sb.toString();
	}

	public TasksVO getTasksVO() {
		return tasksVO;
	}

	public void setTasksVO(TasksVO tasksVO) {
		this.tasksVO = tasksVO;
	}

}
