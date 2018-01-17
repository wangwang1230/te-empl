package com.empl.mgr.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * TeRoleModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "te_role_module")
public class TeRoleModule implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Date timestamp;
	private String roleLabel;
	private String moduleCode;
	private String mosuleSuperCode;
	private boolean finds;
	private boolean adds;
	private boolean deletes;
	private boolean modifys;

	// Constructors

	/** default constructor */
	public TeRoleModule() {
	}

	/** full constructor */
	public TeRoleModule(String roleLabel, String moduleCode, String mosuleSuperCode, boolean finds, boolean adds,
			boolean deletes, boolean modifys) {
		this.roleLabel = roleLabel;
		this.moduleCode = moduleCode;
		this.mosuleSuperCode = mosuleSuperCode;
		this.finds = finds;
		this.adds = adds;
		this.deletes = deletes;
		this.modifys = modifys;
	}

	public TeRoleModule(long id, Date timestamp, String roleLabel, String moduleCode, String mosuleSuperCode,
			boolean finds, boolean adds, boolean deletes, boolean modifys) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.roleLabel = roleLabel;
		this.moduleCode = moduleCode;
		this.mosuleSuperCode = mosuleSuperCode;
		this.finds = finds;
		this.adds = adds;
		this.deletes = deletes;
		this.modifys = modifys;
	}

	@Override
	public String toString() {
		return "TeRoleModule [id=" + id + ", timestamp=" + timestamp + ", roleLabel=" + roleLabel + ", moduleCode="
				+ moduleCode + ", mosuleSuperCode=" + mosuleSuperCode + ", finds=" + finds + ", adds=" + adds
				+ ", deletes=" + deletes + ", modifys=" + modifys + "]";
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Version
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "roleLabel", length = 64)
	public String getRoleLabel() {
		return this.roleLabel;
	}

	public void setRoleLabel(String roleLabel) {
		this.roleLabel = roleLabel;
	}

	@Column(name = "moduleCode", length = 12)
	public String getModuleCode() {
		return this.moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	@Column(name = "mosuleSuperCode", length = 12)
	public String getMosuleSuperCode() {
		return this.mosuleSuperCode;
	}

	public void setMosuleSuperCode(String mosuleSuperCode) {
		this.mosuleSuperCode = mosuleSuperCode;
	}

	@Column(name = "finds")
	public boolean getFinds() {
		return this.finds;
	}

	public void setFinds(boolean finds) {
		this.finds = finds;
	}

	@Column(name = "adds")
	public boolean getAdds() {
		return this.adds;
	}

	public void setAdds(boolean adds) {
		this.adds = adds;
	}

	@Column(name = "deletes")
	public boolean getDeletes() {
		return this.deletes;
	}

	public void setDeletes(boolean deletes) {
		this.deletes = deletes;
	}

	@Column(name = "modifys")
	public boolean getModifys() {
		return this.modifys;
	}

	public void setModifys(boolean modifys) {
		this.modifys = modifys;
	}

}