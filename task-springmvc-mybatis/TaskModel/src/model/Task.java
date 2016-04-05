package model;

import java.util.Date;

public class Task {
	
	private int id;
	
	/**
	 * 任务名称
	 */
	private String title;
	
	/**
	 * 任务描述
	 */
	private String description;
	
	/**
	 * 任务发起者
	 */
	private String sponsor;
	
	/**
	 * 任务执行者
	 */
	private String executor;
	
	/**
	 * 任务到期日
	 */
	private Date executendtime;
	
	/**
	 * 创建时间
	 */
	private Date starttime;
	
	/**
	 * 结束时间
	 */
	private Date endtime;
	
	/**
	 * 执行状态
	 */
	private String executestatus;
	
	/**
	 * 类型
	 */
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getExecutendtime() {
		return executendtime;
	}

	public void setExecutendtime(Date executendtime) {
		this.executendtime = executendtime;
	}

	public String getExecutestatus() {
		return executestatus;
	}

	public void setExecutestatus(String executestatus) {
		this.executestatus = executestatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
