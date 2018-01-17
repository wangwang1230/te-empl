package com.empl.mgr.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.empl.mgr.constant.EmployeesState;
import com.empl.mgr.constant.PageConstant;
import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.dto.EemplByNoneTrainingLogDto;
import com.empl.mgr.dto.EmployeesInternshipListDto;
import com.empl.mgr.dto.SelectDto;
import com.empl.mgr.model.TeEmployeesBasic;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class EmployeesBasicDao extends AbstractDao<TeEmployeesBasic> {

	@Override
	public Class<TeEmployeesBasic> getEntityClass() {
		// TODO Auto-generated method stub
		return TeEmployeesBasic.class;
	}

	@SuppressWarnings("unchecked")
	public List<EmployeesInternshipListDto> findEmployeesList(int type, String value, long dept, long position,
			int page, int... emplType) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("select new com.empl.mgr.dto.EmployeesInternshipListDto");
		query.append("(empl.emId, empl.emFullName, empl.emIdentity, empl.emSex, empl.emParticipateTime, empl.emDeparemtn, empl.emPosition) ");
		query.append("from TeEmployeesBasic empl where empl.emState in(" + pack(emplType) + ") ");
		query.append(dept > 0 ? " and empl.emDeparemtn =  " + dept : "");
		query.append(position > 0 ? " and empl.emPosition = " + position : "");
		query.append(type == 1 && StringUtils.isNotEmpty(value) ? " and empl.emFullName like '%" + value + "%' " : "");
		query.append(type == 2 && StringUtils.isNotEmpty(value) ? " and empl.emIdentity like '%" + value + "%' " : "");
		query.append(" order by empl.emId desc ");
		return findSession().createQuery(query.toString()).setFirstResult((page - 1) * PageConstant.PAGE_LIST)
				.setMaxResults(PageConstant.PAGE_LIST).list();
	}

	public int findEmployeesPage(int type, String value, long dept, long position, int... emplType) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("select count(empl) from TeEmployeesBasic empl where empl.emState in(" + pack(emplType) + ")");
		query.append(dept > 0 ? " and empl.emDeparemtn =  " + dept : "");
		query.append(position > 0 ? " and empl.emPosition = " + position : "");
		query.append(type == 1 && StringUtils.isNotEmpty(value) ? " and empl.emFullName like '%" + value + "%' " : "");
		query.append(type == 2 && StringUtils.isNotEmpty(value) ? " and empl.emIdentity like '%" + value + "%' " : "");
		return Integer.parseInt(findSession().createQuery(query.toString()).uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	public List<SelectDto> findEmplByDeptId(long deptId) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("select new com.empl.mgr.dto.SelectDto");
		query.append("(emId as id, emFullName as name) ");
		query.append("from TeEmployeesBasic where emState = ? and emDeparemtn = ? order by emId desc");
		return findSession().createQuery(query.toString()).setInteger(0, EmployeesState.EMPL_FORMAL).setLong(1, deptId)
				.list();
	}

	public void clearEmplDepartmentByDeptId(long deptId) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("update TeEmployeesBasic set emDeparemtn = ? where emDeparemtn = ?");
		findSession().createQuery(query.toString()).setLong(0, 0L).setLong(1, deptId).executeUpdate();
	}

	public void clearEmplPositionByDeptId(long deptId) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("update TeEmployeesBasic set emPosition = ? where emDeparemtn = ?");
		findSession().createQuery(query.toString()).setLong(0, 0L).setLong(1, deptId).executeUpdate();
	}

	public void chearEmplPositionByPositionId(long id) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("update TeEmployeesBasic set emPosition = ? where emPosition = ?");
		findSession().createQuery(query.toString()).setLong(0, 0L).setLong(1, id).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<EemplByNoneTrainingLogDto> findEemplByNoneTrainingLog(long trainingId, long deptId, String searchVal) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("select new com.empl.mgr.dto.EemplByNoneTrainingLogDto");
		query.append("(empl.emId, empl.emFullName, empl.emSex, empl.emIdentity, empl.emBirthday, empl.emDeparemtn) ");
		query.append("from TeEmployeesBasic empl where not exists (from TeEmployeesTrainingLog tog ");
		query.append("where empl.emId = tog.emplId and tog.trainingItemId = " + trainingId + ") ");
		query.append(deptId > 0 ? " and empl.emDeparemtn = " + deptId : "");
		query.append(StringUtils.isNotBlank(searchVal) ? " and empl.emFullName like '%" + searchVal + "%'" : "");
		query.append(" order by empl.emId desc ");
		return findSession().createQuery(query.toString()).list();
	}

	public int findEemplByNoneTrainingLogCount(long trainingId, long deptId, String searchVal) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("select count(empl.emId) from TeEmployeesBasic empl where not exists (from TeEmployeesTrainingLog tog ");
		query.append("where empl.emId = tog.emplId and tog.trainingItemId = " + trainingId + ") ");
		query.append(deptId > 0 ? " and empl.emDeparemtn = " + deptId : "");
		query.append(StringUtils.isNotBlank(searchVal) ? " and empl.emFullName like '%" + searchVal + "%'" : "");
		return Integer.parseInt(findSession().createQuery(query.toString()).uniqueResult().toString());
	}

	private String pack(int... type) {
		String result = "";
		for (int key : type)
			result += key + ",";
		return StringUtils.isEmpty(result) ? "" : result.substring(0, result.length() - 1);
	}
}
