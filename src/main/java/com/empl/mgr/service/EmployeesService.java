package com.empl.mgr.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.empl.mgr.dto.EmployeesInfoDto;
import com.empl.mgr.support.JSONReturn;

public abstract interface EmployeesService {

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @return
	 */
	public abstract JSONReturn findChoose();

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param dto
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn saveEmployeesInfo(EmployeesInfoDto dto, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param serType
	 * @param serVal
	 * @param department
	 * @param position
	 * @param page
	 * @param acctName
	 * @param emplType
	 * @return
	 */
	public abstract JSONReturn findEmployeesList(int serType, String serVal, long department, long position, int page,
			String acctName, int... emplType);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param serType
	 * @param serVal
	 * @param department
	 * @param position
	 * @param page
	 * @param acctName
	 * @param emplType
	 * @return
	 */
	public abstract JSONReturn findEmployeesPage(int serType, String serVal, long department, long position, int page,
			String acctName, int... emplType);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param emId
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn enroll(long emId, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param emId
	 * @param note
	 * @param string
	 * @return
	 */
	public abstract JSONReturn eliminate(long emId, String note, String string);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param emplId
	 * @return
	 */
	public abstract JSONReturn findEmployeesInfo(long emplId);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param dto
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn modifyEmployeesInfo(EmployeesInfoDto dto, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param id
	 * @param note
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn departure(long id, String note, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param emplId
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn findTrainingRecord(long emplId, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param trainingId
	 * @param deptId
	 * @param searchVal
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn findEmployeesList(long trainingId, long deptId, String searchVal, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param trainingId
	 * @param deptId
	 * @param searchVal
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn addAllTraining(long trainingId, long deptId, String searchVal, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param emplId
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn destroy(long emplId, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param emplId
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn findDepartureNote(long emplId, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param emplId
	 * @param note
	 * @param acctName
	 * @return
	 */
	public abstract JSONReturn enrollEmployees(long emplId, String note, String acctName);

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param imgFile
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public abstract JSONReturn uploadImg(MultipartFile imgFile, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	/**
	 * @author TE网络[WWW.TE5L.COM] [_Kiro]
	 * @param emplId
	 * @return
	 */
	public abstract JSONReturn findEmployeesRecord(long emplId);

}
