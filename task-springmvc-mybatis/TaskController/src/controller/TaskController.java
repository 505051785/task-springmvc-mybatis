package controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import controller.cenum.ExcuteStatusEnum;
import controller.cenum.TaskTypeEnum;
import controller.tools.StringTool;
import model.AddTaskVO;
import model.EditTaskVO;
import model.LoginVO;
import model.Task;
import model.TaskStatus;
import model.TaskType;
import model.TaskVO;
import model.TasksVO;
import model.User;
import service.TaskService;

public class TaskController {

	/**
	 * 新增任务
	 * 
	 * @param request
	 * @param response
	 */
	public void AddTask(HttpServletRequest request, HttpServletResponse response) {
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
	}

	/**
	 * 修改任务
	 * 
	 * @param request
	 * @param response
	 */
	public void ModifyTask(HttpServletRequest request, HttpServletResponse response) {
		String taskid = request.getParameter("id");
		AddTaskVO addTaskVO = TaskService.instance.DetailTask(taskid);
		String title = request.getParameter("title").toString();
		String description = request.getParameter("description").toString();
		String executor = request.getParameter("executor").toString();
		String executestatus = request.getParameter("executestatus").toString();
		String executendtime = request.getParameter("executendtime").toString();
		String type = request.getParameter("type").toString();
		Task task = addTaskVO.getTask();
		task.setTitle(StringTool.ToUTF(title));
		task.setDescription(StringTool.ToUTF(description));
		task.setExecutor(StringTool.ToUTF(executor));
		task.setExecutestatus(executestatus);
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
		TaskService.instance.ModifyTask(task);
	}

	/**
	 * 单个任务详细信息展示
	 * 
	 * @param request
	 * @param response
	 */
	public void DetailTask(HttpServletRequest request, HttpServletResponse response) {
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
	}

	/**
	 * 单个任务详细信息展示
	 * 
	 * @param request
	 * @param response
	 */
	public void EditTask(HttpServletRequest request, HttpServletResponse response) {
		String taskid = request.getParameter("id");
		EditTaskVO editTaskVO = TaskService.instance.EditTask(taskid);
		List<TaskType> typelist = new ArrayList<TaskType>();
		TaskType defaulttype = new TaskType();
		defaulttype.setCode(editTaskVO.getTask().getType());
		defaulttype.setName(TaskTypeEnum.resStatus(Integer.parseInt(editTaskVO.getTask().getType())));
		typelist.add(defaulttype);
		for (TaskTypeEnum item : TaskTypeEnum.values()) {
			if (item.getCode().equals(editTaskVO.getTask().getType()))
				continue;
			TaskType tasktype = new TaskType();
			tasktype.setCode(item.getCode());
			tasktype.setName(item.getName());
			typelist.add(tasktype);
		}
		editTaskVO.setTypelist(typelist);
		List<User> users = TaskService.instance.getAllUser();
		int changeIndex = 0;
		User changeUser = new User();
		for (User item : users) {
			if (item.getUserCode().equals(editTaskVO.getTask().getExecutor())) {
				changeUser = item;
				break;
			}
			changeIndex++;
		}
		users.set(changeIndex, users.get(0));
		users.set(0, changeUser);
		editTaskVO.setUsers(users);

		List<TaskStatus> taskStatusList = new ArrayList<TaskStatus>();
		TaskStatus defaultStaus = new TaskStatus();
		defaultStaus.setCode(editTaskVO.getTask().getExecutestatus());
		defaultStaus.setName(ExcuteStatusEnum.resStatus(Integer.parseInt(editTaskVO.getTask().getExecutestatus())));
		taskStatusList.add(defaultStaus);
		for (ExcuteStatusEnum item : ExcuteStatusEnum.values()) {
			if (item.getCode().equals(editTaskVO.getTask().getExecutestatus()))
				continue;
			TaskStatus taskStatus = new TaskStatus();
			taskStatus.setCode(item.getCode());
			taskStatus.setName(item.getName());
			taskStatusList.add(taskStatus);
		}
		editTaskVO.setTaskStatusList(taskStatusList);
		request.setAttribute("editTaskVO", editTaskVO);
	}

	/**
	 * 展示所有任务
	 * 
	 * @param request
	 * @param response
	 */
	public void ShowTasks(HttpServletRequest request, HttpServletResponse response) {
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
			taskList = TaskService.instance.GetTasks(perPageNum, intPageNo);
			for (TaskVO taskVO : taskList) {
				Task task = taskVO.getTask();
				task.setExecutestatus(ExcuteStatusEnum.resStatus(Integer.parseInt(task.getExecutestatus())));
				task.setType(TaskTypeEnum.resStatus(Integer.parseInt(task.getType())));
			}
			totalPages = TaskService.instance.GetTasksCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tasksVO.setTaskList(taskList);
		tasksVO.setPageFooter(calFooter(totalPages, perPageNum, intPageNo));
		request.setAttribute("tasksVO", tasksVO);
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

	/**
	 * login
	 * 
	 * @param request
	 * @param response
	 */
	public boolean Login(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User loginuser = (User) session.getAttribute("taskuser");
		if (loginuser != null) {
			return true;
		}
		String usercode = request.getParameter("usercode");
		String password = request.getParameter("password");
		if (StringUtils.isBlank(usercode) || StringUtils.isBlank(password)) {
			return false;
		}
		User user = TaskService.instance.Login(usercode);
		if (user.getUserCode() != null && user.getPassword().equals(password)) {
			session.setAttribute("taskuser", user);
			return true;
		} else {
			LoginVO loginVO = new LoginVO();
			loginVO.setError("用户名或密码错误！");
			request.setAttribute("loginVO", loginVO);
			return false;
		}
	}
}
