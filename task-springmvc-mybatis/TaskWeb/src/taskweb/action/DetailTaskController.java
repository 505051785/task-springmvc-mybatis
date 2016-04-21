package taskweb.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.AddTaskVO;
import model.TaskType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import service.TaskService;
import controller.cenum.TaskTypeEnum;

@Controller
public class DetailTaskController {
	
	private AddTaskVO addTaskVO ;

	
	@RequestMapping(value="/detailtask", method=RequestMethod.GET)
	public String execute() throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		String taskid = request.getParameter("id");
		AddTaskVO addTaskVO = TaskService.instance.DetailTask(taskid);
		List<TaskType> typelist = new ArrayList<TaskType>();
		for (TaskTypeEnum item : TaskTypeEnum.values()) {
			TaskType tasktype = new TaskType();
			tasktype.setCode(item.getCode());
			tasktype.setName(item.getName());
			typelist.add(tasktype);
		}
		addTaskVO.setTypelist(typelist);
		request.setAttribute("addTaskVO", addTaskVO);
		if (StringUtils.isBlank(taskid)) {
			return "addtask";
		}
		return "task";
	}

	public AddTaskVO getAddTaskVO() {
		return addTaskVO;
	}

	public void setAddTaskVO(AddTaskVO addTaskVO) {
		this.addTaskVO = addTaskVO;
	}

}
