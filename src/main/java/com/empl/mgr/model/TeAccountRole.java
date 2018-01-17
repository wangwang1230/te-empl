package com.empl.mgr.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "te_account_role")
public class TeAccountRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long aoId;
	private Date timestamp;
	private String acctName;
	private String roleLabel;

	/** default constructor */
	public TeAccountRole() {
	}

	@Override
	public String toString() {
		return "TeAccountRole [aoId:" + aoId + ", timestamp:" + timestamp + ", acctName:" + acctName + ", roleLabel:"
				+ roleLabel + "]";
	}

	/** full constructor */
	public TeAccountRole(String acctName, String roleLabel) {
		this.acctName = acctName;
		this.roleLabel = roleLabel;
	}

	public TeAccountRole(long aoId, Date timestamp, String acctName, String roleLabel) {
		super();
		this.aoId = aoId;
		this.timestamp = timestamp;
		this.acctName = acctName;
		this.roleLabel = roleLabel;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "aoId", unique = true, nullable = false)
	public long getAoId() {
		return this.aoId;
	}

	public void setAoId(long aoId) {
		this.aoId = aoId;
	}

	@Version
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "acctName", length = 64)
	public String getAcctName() {
		return this.acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	@Column(name = "roleLabel", length = 64)
	public String getRoleLabel() {
		return this.roleLabel;
	}

	public void setRoleLabel(String roleLabel) {
		this.roleLabel = roleLabel;
	}

}