package com.empl.mgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.model.TeAccountRole;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class AccountRoleDao extends AbstractDao<TeAccountRole> {

	@Override
	public Class<TeAccountRole> getEntityClass() {
		// TODO Auto-generated method stub
		return TeAccountRole.class;
	}

	@SuppressWarnings("unchecked")
	public List<TeAccountRole> findByAcctNameAndRoleLabel(String account, String roleLabel) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("from TeAccountRole where acctName = ? and roleLabel = ?");
		return findSession().createQuery(query.toString()).setString(0, account).setString(1, roleLabel).list();
	}

	public void delByAcctNameAndRoleLabel(String account, String roleLabel) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("delete from TeAccountRole where acctName = ? and roleLabel = ?");
		findSession().createQuery(query.toString()).setString(0, account).setString(1, roleLabel).executeUpdate();
	}

}
