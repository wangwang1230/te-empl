package com.empl.mgr.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.empl.mgr.constant.PageConstant;
import com.empl.mgr.dao.support.AbstractDao;
import com.empl.mgr.dto.PositionDto;
import com.empl.mgr.dto.PositionListDto;
import com.empl.mgr.model.TePosition;

/**
 * @author TE网络[WWW.TE5L.COM] [_Kiro]
 */
@Repository
public class PositionDao extends AbstractDao<TePosition> {

	@Override
	public Class<TePosition> getEntityClass() {
		// TODO Auto-generated method stub
		return TePosition.class;
	}

	@SuppressWarnings("unchecked")
	public List<PositionListDto> findPositionListInfo(int page, long deptId, String searchValue) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("select new com.empl.mgr.dto.PositionListDto");
		query.append("(po.poId, po.poName, po.poDepartment, po.poDescription, po.creator, po.createTime, dept.deptName)");
		query.append("from TePosition po, TeDepartment dept where dept.deptId = po.poDepartment");
		query.append(StringUtils.isNotEmpty(searchValue) ? " and po.poName like '%" + searchValue + "%'" : "");
		query.append(deptId > 0 ? " and po.poDepartment = " + deptId : "");
		query.append(" order by po.poId desc");
		return findSession().createQuery(query.toString()).setFirstResult((page - 1) * PageConstant.PAGE_LIST)
				.setMaxResults(PageConstant.PAGE_LIST).list();
	}

	public int findPositionPage(int page, long deptId, String searchValue) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("select count(po.poId) from TePosition po, TeDepartment dept where dept.deptId = po.poDepartment");
		query.append(StringUtils.isNotEmpty(searchValue) ? " and po.poName like '%" + searchValue + "%'" : "");
		query.append(deptId > 0 ? " and po.poDepartment = " + deptId : "");
		return Integer.parseInt(findSession().createQuery(query.toString()).uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	public List<PositionDto> findByDeptId(long deptId) {
		// TODO Auto-generated method stub
		String query = "select new com.empl.mgr.dto.PositionDto(poId, poName) from TePosition where poDepartment = ? order by poId desc";
		return findSession().createQuery(query).setLong(0, deptId).list();
	}

	public void deleteByDepartment(long deptId) {
		// TODO Auto-generated method stub
		findSession().createQuery("delete from TePosition where poDepartment = ?").setLong(0, deptId).executeUpdate();
	}

}
