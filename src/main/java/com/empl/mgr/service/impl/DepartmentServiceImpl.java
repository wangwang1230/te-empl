package com.empl.mgr.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empl.mgr.constant.EmployeesState;
import com.empl.mgr.constant.PageConstant;
import com.empl.mgr.constant.TimeFormatConstant;
import com.empl.mgr.dao.DepartmentDao;
import com.empl.mgr.dao.EmployeesBasicDao;
import com.empl.mgr.dao.PositionDao;
import com.empl.mgr.dto.DepartmentListDto;
import com.empl.mgr.dto.DepartmentSelectDto;
import com.empl.mgr.dto.SelectDto;
import com.empl.mgr.field.TeDepartmentField;
import com.empl.mgr.field.TeEmployeesBasicField;
import com.empl.mgr.model.TeDepartment;
import com.empl.mgr.model.TeEmployeesBasic;
import com.empl.mgr.service.DepartmentService;
import com.empl.mgr.support.JSONReturn;
import com.empl.mgr.utils.CompareUtil;
import com.empl.mgr.utils.DateTimeUtil;
import com.empl.mgr.utils.PageUtils;

@Scope
@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private PositionDao positionDao;
	@Autowired
	private EmployeesBasicDao employeesBasicDao;

	public JSONReturn findDepartmentList(int page, String searchValue, String acctName) {
		// TODO Auto-generated method stub
		List<DepartmentListDto> dtoList = departmentDao.findDepartmentList(page, searchValue);
		if (CollectionUtils.isEmpty(dtoList))
			return JSONReturn.buildFailure("未获取到数据!");
		TeEmployeesBasic empl = null;
		for (DepartmentListDto dto : dtoList) {
			dto.setTime(DateTimeUtil.conversionTime(dto.getCreateTime(), TimeFormatConstant.YYYY_MM_DD));
			dto.setCreateTime(null);
			if (dto.getPrincipal() < 1)
				continue;
			empl = employeesBasicDao.findUniqueByProperty(TeEmployeesBasicField.EM_ID, dto.getPrincipal());
			if (CompareUtil.isEmpty(empl) || empl.getEmState() != EmployeesState.EMPL_FORMAL)
				continue;
			dto.setFullName(empl.getEmFullName());
		}
		return JSONReturn.buildSuccess(dtoList);
	}

	public JSONReturn findDepartmentCount(int page, String searchValue) {
		// TODO Auto-generated method stub
		int count = departmentDao.findCountLike(TeDepartmentField.DEPT_NAME, searchValue);
		return JSONReturn.buildSuccess(PageUtils.calculatePage(page, count, PageConstant.PAGE_LIST));
	}

	public JSONReturn findDepartmentById(long deptId) {
		// TODO Auto-generated method stub
		TeDepartment dept = departmentDao.findUniqueByProperty(TeDepartmentField.DEPT_ID, deptId);
		if (CompareUtil.isEmpty(dept))
			return JSONReturn.buildFailure("获取源数据失败!");
		return JSONReturn.buildSuccess(dept);
	}

	@Transactional
	public JSONReturn modifyDepartment(long deptId, String name, String desc, String acctName) {
		// TODO Auto-generated method stub
		TeDepartment dept = departmentDao.findUniqueByProperty(TeDepartmentField.DEPT_ID, deptId);
		if (CompareUtil.isEmpty(dept))
			return JSONReturn.buildFailure("修改失败, 数据源不存在!");
		dept.setDeptName(name);
		dept.setDeptDescription(desc);
		return JSONReturn.buildSuccess("修改成功!");
	}

	@Transactional
	public JSONReturn addDepartment(String name, String desc, String acctName) {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(name))
			return JSONReturn.buildFailure("添加失败, 部门名称不能为空!");
		TeDepartment dept = departmentDao.findUniqueByProperty(TeDepartmentField.DEPT_NAME, name);
		if (CompareUtil.isNotEmpty(dept))
			return JSONReturn.buildFailure("添加部门失败, 已存在同名称部门!");
		departmentDao.save(new TeDepartment(name, DateTimeUtil.getCurrentTime(), acctName, desc, 0));
		return JSONReturn.buildSuccess("添加成功!");
	}

	@Transactional
	public JSONReturn deleteDepartment(long deptId, String acctName) {
		// TODO Auto-generated method stub
		employeesBasicDao.clearEmplPositionByDeptId(deptId);
		employeesBasicDao.clearEmplDepartmentByDeptId(deptId);
		positionDao.deleteByDepartment(deptId);
		departmentDao.deleteByProperty(TeDepartmentField.DEPT_ID, deptId);
		List<TeEmployeesBasic> emplList = employeesBasicDao.findByProperty(TeEmployeesBasicField.EM_DEPAREMTN, deptId);
		if (CollectionUtils.isEmpty(emplList))
			return JSONReturn.buildSuccess("删除部门成功!");
		for (TeEmployeesBasic empl : emplList) {
			empl.setEmDeparemtn(0); // 将该部门下的员工部门字段设置为0
			empl.setEmPolitics(0); //
		}
		return JSONReturn.buildSuccess("删除部门成功!");
	}

	public JSONReturn findAllDepartment() {
		// TODO Auto-generated method stub
		List<DepartmentSelectDto> dtoList = departmentDao.findAllDepartment();
		if (CollectionUtils.isEmpty(dtoList))
			return JSONReturn.buildFailure("未获取到部门!");
		return JSONReturn.buildSuccess(dtoList);
	}

	public JSONReturn findDeptEmplList(long deptId, String acctName) {
		// TODO Auto-generated method stub
		List<SelectDto> dtoList = employeesBasicDao.findEmplByDeptId(deptId);
		if (CollectionUtils.isEmpty(dtoList))
			return JSONReturn.buildFailure("操作失败, 该部下不存在员工!<br /><span class='placeholder'>PS: 只有正式员工才能提升为部门经理!</span>");
		return JSONReturn.buildSuccess(dtoList);
	}

	@Transactional
	public JSONReturn setPrincipal(long deptId, long emplId, String acctName) {
		// TODO Auto-generated method stub
		TeDepartment dept = departmentDao.findUniqueByProperty(TeDepartmentField.DEPT_ID, deptId);
		if (CompareUtil.isEmpty(dept))
			return JSONReturn.buildFailure("非法操作, 部门不存在!");
		TeEmployeesBasic empl = employeesBasicDao.findUniqueByProperty(TeEmployeesBasicField.EM_ID, emplId);
		if (CompareUtil.isEmpty(empl))
			return JSONReturn.buildFailure("非法操作, 员工不存在!");
		if (empl.getEmDeparemtn() != dept.getDeptId())
			return JSONReturn.buildFailure("非法操作, 该员工不属性 <b>" + dept.getDeptName() + "</b> 部门");
		dept.setDeptPrincipal(emplId);
		return JSONReturn.buildSuccess("设置成功!");
	}
}
