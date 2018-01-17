package com.empl.mgr.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.empl.mgr.constant.PageConstant;
import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.dto.TrainingRecordDto;
import com.empl.mgr.model.TeTraining;


/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class TrainingDao extends AbstractDao<TeTraining> {

	@Override
	public Class<TeTraining> getEntityClass() {
		// TODO Auto-generated method stub
		return TeTraining.class;
	}

	@SuppressWarnings("unchecked")
	public List<TeTraining> findTrainingList(String searchValue, int page) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("from TeTraining ");
		query.append(StringUtils.isEmpty(searchValue) ? " " : " where name like '%" + searchValue + "%' ");
		query.append("order by id desc");
		return findSession().createQuery(query.toString()).setFirstResult((page - 1) * PageConstant.PAGE_LIST)
				.setMaxResults(PageConstant.PAGE_LIST).list();
	}

	public int findTrainingCount(String searchValue) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("select count(id) from TeTraining ");
		query.append(StringUtils.isEmpty(searchValue) ? " " : " where name like '%" + searchValue + "%' ");
		return Integer.parseInt(findSession().createQuery(query.toString()).uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	public List<TrainingRecordDto> findRecord(long training) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("select new com.empl.mgr.dto.TrainingRecordDto");
		query.append("(tog.id, empl.emFullName, empl.emIdentity, empl.emSex, tog.applyTime, tog.state, tog.note) ");
		query.append("from TeTraining tr, TeEmployeesBasic empl, TeEmployeesTrainingLog tog ");
		query.append("where empl.emId = tog.emplId and tr.id = tog.trainingItemId and tr.id = ? ");
		query.append("order by tog.id desc");
		return findSession().createQuery(query.toString()).setLong(0, training).list();
	}

}
