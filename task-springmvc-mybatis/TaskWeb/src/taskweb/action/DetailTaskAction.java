package taskweb.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.AddTaskVO;
import model.TaskType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import service.TaskService;
import controller.cenum.TaskTypeEnum;

public class DetailTaskAction {
	
	private AddTaskVO addTaskVO ;

	

	public String execute() throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		TaskService taskService = new TaskService();
		String taskid = request.getParameter("id");
		if (StringUtils.isBlank(taskid)) {
			return "addtask";
		}
		AddTaskVO addTaskVO = taskService.DetailTask(taskid);
		List<TaskType> typelist = new ArrayList<TaskType>();
		for (TaskTypeEnum item : TaskTypeEnum.values()) {
			TaskType tasktype = new TaskType();
			tasktype.setCode(item.getCode());
			tasktype.setName(item.getName());
			typelist.add(tasktype);
		}
		addTaskVO.setTypelist(typelist);
		request.setAttribute("addTaskVO", addTaskVO);
		return "";
	}

	public AddTaskVO getAddTaskVO() {
		return addTaskVO;
	}

	public void setAddTaskVO(AddTaskVO addTaskVO) {
		this.addTaskVO = addTaskVO;
	}

}
