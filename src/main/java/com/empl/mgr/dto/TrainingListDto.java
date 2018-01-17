package com.empl.mgr.dto;

import java.io.Serializable;

/**
 * 培训项目列表传输实体类
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class TrainingListDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id; // 培训项目ID号
	private String name; // 名称
	private String description; // 简介信息
	private int number; // 数量
	private String start; // 开始时间
	private String end; // 结束时间
	private String creator; // 创建者
	private boolean insertion; // 是否允许中途加入
	private String time; // 时间
	private int state;
	private String stateKey;
	private String trainingResult; // 培训结果

	public String getTrainingResult() {
		return trainingResult;
	}

	public void setTrainingResult(String trainingResult) {
		this.trainingResult = trainingResult;
	}

	public TrainingListDto() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean isInsertion() {
		return insertion;
	}

	public void setInsertion(boolean insertion) {
		this.insertion = insertion;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateKey() {
		return stateKey;
	}

	public void setStateKey(String stateKey) {
		this.stateKey = stateKey;
	}

	@Override
	public String toString() {
		return "TrainingListDto [id:" + id + ", name:" + name + ", description:" + description + ", number:" + number
				+ ", start:" + start + ", end:" + end + ", creator:" + creator + ", insertion:" + insertion + ", time:"
				+ time + ", state:" + state + ", stateKey:" + stateKey + ", trainingResult:" + trainingResult + "]";
	}

}
