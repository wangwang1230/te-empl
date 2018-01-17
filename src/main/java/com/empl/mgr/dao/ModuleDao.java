package com.empl.mgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.model.TeModule;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class ModuleDao extends AbstractDao<TeModule> {

	@Override
	public Class<TeModule> getEntityClass() {
		// TODO Auto-generated method stub
		return TeModule.class;
	}

	@SuppressWarnings("unchecked")
	public List<TeModule> findMgrModule(String acctName) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("from TeModule tm where exists ");
		query.append("(from TeAccountRole tar, TeRoleModule trm ");
		query.append("where tar.roleLabel = trm.roleLabel and ");
		query.append("(tm.moduleCode = trm.moduleCode or tm.moduleCode = trm.mosuleSuperCode) ");
		query.append("and trm.finds=1 and tar.acctName = ?)");
		return findSession().createQuery(query.toString()).setString(0, acctName).list();
	}

}
