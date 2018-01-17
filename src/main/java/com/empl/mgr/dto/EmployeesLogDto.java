package com.empl.mgr.dto;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
public class EmployeesLogDto {

	private long id;
	private String note;
	private String creator;

	private String type;
	private String time;

	public EmployeesLogDto() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Demo [id:" + id + ", note:" + note + ", creator:" + creator + ", type:" + type + ", time:" + time + "]";
	}

}
