package model;

import java.util.List;

public class TasksVO {

	private List<TaskVO> taskList;
	
	private String pageFooter;

	public List<TaskVO> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskVO> taskList) {
		this.taskList = taskList;
	}

	public String getPageFooter() {
		return pageFooter;
	}

	public void setPageFooter(String pageFooter) {
		this.pageFooter = pageFooter;
	}
}
