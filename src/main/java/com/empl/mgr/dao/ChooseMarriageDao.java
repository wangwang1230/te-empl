package com.empl.mgr.dao;

import org.springframework.stereotype.Repository;

import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.model.TeChooseMarriage;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class ChooseMarriageDao extends AbstractDao<TeChooseMarriage> {

	@Override
	public Class<TeChooseMarriage> getEntityClass() {
		// TODO Auto-generated method stub
		return TeChooseMarriage.class;
	}

}
