package com.empl.mgr.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.empl.mgr.constant.PageConstant;
import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.dto.DepartmentListDto;
import com.empl.mgr.dto.DepartmentSelectDto;
import com.empl.mgr.model.TeDepartment;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class DepartmentDao extends AbstractDao<TeDepartment> {

	@Override
	public Class<TeDepartment> getEntityClass() {
		// TODO Auto-generated method stub
		return TeDepartment.class;
	}

	@SuppressWarnings("unchecked")
	public List<DepartmentListDto> findDepartmentList(int page, String searchValue) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("select new com.empl.mgr.dto.DepartmentListDto");
		query.append("(dept.deptId, dept.deptName, dept.createTime, dept.creator, dept.deptDescription, dept.deptPrincipal) ");
		query.append("from TeDepartment dept ");
		query.append(StringUtils.isNotBlank(searchValue) ? " where deptName like '%" + searchValue + "%'" : "");
		query.append("order by deptId desc");
		return findSession().createQuery(query.toString()).setFirstResult((page - 1) * PageConstant.PAGE_LIST)
				.setMaxResults(PageConstant.PAGE_LIST).list();
	}

	@SuppressWarnings("unchecked")
	public List<DepartmentSelectDto> findAllDepartment() {
		// TODO Auto-generated method stub
		String query = "select new com.empl.mgr.dto.DepartmentSelectDto (deptId, deptName) from TeDepartment order by deptId desc";
		return findSession().createQuery(query).list();
	}

}
