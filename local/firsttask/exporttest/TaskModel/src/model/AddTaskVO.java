package model;

import java.util.List;

public class AddTaskVO {
	
	private Task task;

	private List<User> users;
	
	private List<TaskType> typelist;

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
	
}

