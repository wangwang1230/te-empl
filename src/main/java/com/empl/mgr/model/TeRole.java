package com.empl.mgr.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * TeRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_role", uniqueConstraints = @UniqueConstraint(columnNames = "roleLabel"))
public class TeRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long roleId;
	private Date timestamp;
	private String roleName;
	private String roleDescription;
	private String roleLabel;
	private Date createTime;
	private String creator;

	// Constructors

	/** default constructor */
	public TeRole() {
	}

	/** full constructor */
	public TeRole(String roleName, String roleDescription, String roleLabel, Date createTime, String creator) {
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.roleLabel = roleLabel;
		this.createTime = createTime;
		this.creator = creator;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "roleId", unique = true, nullable = false)
	public long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Version
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "roleName", length = 128)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "roleDescription", length = 1024)
	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Column(name = "roleLabel", unique = true, length = 64)
	public String getRoleLabel() {
		return this.roleLabel;
	}

	public void setRoleLabel(String roleLabel) {
		this.roleLabel = roleLabel;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "creator", length = 64)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "TeRole [roleId=" + roleId + ", timestamp=" + timestamp + ", roleName=" + roleName
				+ ", roleDescription=" + roleDescription + ", roleLabel=" + roleLabel + ", createTime=" + createTime
				+ ", creator=" + creator + "]";
	}

}