package com.empl.mgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empl.mgr.constant.TrainLogState;
import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.dto.EmployeesTrainingRecordDto;
import com.empl.mgr.model.TeEmployeesTrainingLog;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class EmployeesTrainingLogDao extends AbstractDao<TeEmployeesTrainingLog> {

	@Override
	public Class<TeEmployeesTrainingLog> getEntityClass() {
		// TODO Auto-generated method stub
		return TeEmployeesTrainingLog.class;
	}

	@SuppressWarnings("unchecked")
	public List<EmployeesTrainingRecordDto> findTrainingRecord(long emplId) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("select new com.empl.mgr.dto.EmployeesTrainingRecordDto");
		query.append("(tog.id, tr.name, tog.state, tog.applyTime, tog.note) from TeTraining tr, TeEmployeesTrainingLog tog ");
		query.append("where tog.emplId = ? and tr.id = tog.trainingItemId order by tog.id desc");
		return findSession().createQuery(query.toString()).setLong(0, emplId).list();
	}

	public TeEmployeesTrainingLog findByEmplTraining(long emplId, long trainingId) {
		// TODO Auto-generated method stub
		String query = "from TeEmployeesTrainingLog where emplId = ? and trainingItemId = ?";
		return (TeEmployeesTrainingLog) findSession().createQuery(query).setLong(0, emplId).setLong(1, trainingId)
				.uniqueResult();
	}

	public void modifyTrainingRecordStart(long trainId) {
		// TODO Auto-generated method stub
		String query = "update TeEmployeesTrainingLog set state = ? where trainingItemId = ?";
		findSession().createQuery(query).setInteger(0, TrainLogState.START).setLong(1, trainId).executeUpdate();
	}

	public void modifyTrainingRecordStop(long trainId, String note) {
		// TODO Auto-generated method stub
		String query = "update TeEmployeesTrainingLog set state = ?, note = ? where trainingItemId = ? and state = ?";
		findSession().createQuery(query).setInteger(0, TrainLogState.FINISH).setString(1, note).setLong(2, trainId)
				.setInteger(3, TrainLogState.START).executeUpdate();
	}

}
