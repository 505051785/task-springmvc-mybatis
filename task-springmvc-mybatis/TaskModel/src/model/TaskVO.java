package model;

public class TaskVO {
	
	private Task task;
	
	/**
	 * 执行者
	 */
	private User user; 

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
