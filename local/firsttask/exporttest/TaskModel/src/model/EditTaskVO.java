package model;

import java.util.List;

public class EditTaskVO {
	
	private Task task;

	private List<User> users;
	
	private List<TaskType> typelist;
	
	private List<TaskStatus> taskStatusList;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<TaskType> getTypelist() {
		return typelist;
	}

	public void setTypelist(List<TaskType> typelist) {
		this.typelist = typelist;
	}

	public List<TaskStatus> getTaskStatusList() {
		return taskStatusList;
	}

	public void setTaskStatusList(List<TaskStatus> taskStatusList) {
		this.taskStatusList = taskStatusList;
	}
	
}

