package com.empl.mgr.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.empl.mgr.constant.AddressType;
import com.empl.mgr.constant.EmployeesLogType;
import com.empl.mgr.constant.EmployeesState;
import com.empl.mgr.constant.PageConstant;
import com.empl.mgr.constant.PhoneImageSize;
import com.empl.mgr.constant.TimeFormatConstant;
import com.empl.mgr.constant.TrainLogState;
import com.empl.mgr.constant.TrainingState;
import com.empl.mgr.dao.AddressDao;
import com.empl.mgr.dao.ChooseEducationDao;
import com.empl.mgr.dao.ChooseMarriageDao;
import com.empl.mgr.dao.ChooseNationalDao;
import com.empl.mgr.dao.ChoosePoliticsDao;
import com.empl.mgr.dao.CityDao;
import com.empl.mgr.dao.CountyDao;
import com.empl.mgr.dao.DepartmentDao;
import com.empl.mgr.dao.EmployeesBasicDao;
import com.empl.mgr.dao.EmployeesCompanyDao;
import com.empl.mgr.dao.EmployeesDetailDao;
import com.empl.mgr.dao.EmployeesLogDao;
import com.empl.mgr.dao.EmployeesTrainingLogDao;
import com.empl.mgr.dao.PositionDao;
import com.empl.mgr.dao.ProvinceDao;
import com.empl.mgr.dao.TownshipDao;
import com.empl.mgr.dao.TrainingDao;
import com.empl.mgr.dao.VillageDao;
import com.empl.mgr.dto.EemplByNoneTrainingLogDto;
import com.empl.mgr.dto.EmployeesAddressDto;
import com.empl.mgr.dto.EmployeesBasicInfoDto;
import com.empl.mgr.dto.EmployeesCompnayDto;
import com.empl.mgr.dto.EmployeesInfoDto;
import com.empl.mgr.dto.EmployeesInternshipListDto;
import com.empl.mgr.dto.EmployeesLogDto;
import com.empl.mgr.dto.EmployeesTrainingRecordDto;
import com.empl.mgr.field.TeAddressField;
import com.empl.mgr.field.TeDepartmentField;
import com.empl.mgr.field.TeEmployeesBasicField;
import com.empl.mgr.field.TeEmployeesCompanyField;
import com.empl.mgr.field.TeEmployeesDetailField;
import com.empl.mgr.field.TeEmployeesLogField;
import com.empl.mgr.field.TeEmployeesTrainingLogField;
import com.empl.mgr.field.TePositionField;
import com.empl.mgr.model.TeAddress;
import com.empl.mgr.model.TeDepartment;
import com.empl.mgr.model.TeEmployeesBasic;
import com.empl.mgr.model.TeEmployeesCompany;
import com.empl.mgr.model.TeEmployeesDetail;
import com.empl.mgr.model.TeEmployeesLog;
import com.empl.mgr.model.TeEmployeesTrainingLog;
import com.empl.mgr.model.TePosition;
import com.empl.mgr.model.TeTraining;
import com.empl.mgr.service.EmployeesLogService;
import com.empl.mgr.service.EmployeesService;
import com.empl.mgr.support.JSONReturn;
import com.empl.mgr.utils.CompareUtil;
import com.empl.mgr.utils.DateTimeUtil;
import com.empl.mgr.utils.PageUtils;

@Scope
@Service
@Transactional(readOnly = true)
public class EmployeesServiceImpl implements EmployeesService {

	@Autowired
	private ChooseEducationDao chooseEducationDao;
	@Autowired
	private ChooseMarriageDao chooseMarriageDao;
	@Autowired
	private ChooseNationalDao chooseNationalDao;
	@Autowired
	private ChoosePoliticsDao choosePoliticsDao;
	@Autowired
	private PositionDao positionDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private EmployeesBasicDao employeesBasicDao;
	@Autowired
	private EmployeesCompanyDao employeesCompanyDao;
	@Autowired
	private EmployeesDetailDao employeesDetailDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private ProvinceDao provinceDao;
	@Autowired
	private CityDao cityDao;
	@Autowired
	private CountyDao countyDao;
	@Autowired
	private TownshipDao townshipDao;
	@Autowired
	private VillageDao villageDao;
	@Autowired
	private EmployeesTrainingLogDao employeesTrainingLogDao;
	@Autowired
	private TrainingDao trainingDao;
	@Autowired
	private EmployeesLogService employeesLogService;
	@Autowired
	private EmployeesLogDao employeesLogDao;

	public JSONReturn findChoose() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("educationList", chooseEducationDao.findAll());
		map.put("marriageList", chooseMarriageDao.findAll());
		map.put("nationalList", chooseNationalDao.findAll());
		map.put("politicList", choosePoliticsDao.findAll());
		return JSONReturn.buildSuccess(map);
	}

	@Transactional
	public JSONReturn saveEmployeesInfo(EmployeesInfoDto dto, String acctName) {
		// TODO Auto-generated method stub
		if (CompareUtil.isEmpty(dto))
			return JSONReturn.buildFailure("员工信息保存失败!");
		if (CollectionUtils.isNotEmpty(employeesBasicDao.findByProperty(TeEmployeesBasicField.EM_IDENTITY,
				dto.getIdentity())))
			return JSONReturn.buildFailure("保存员工信息失败, 身份证重复!");
		TeEmployeesBasic basic = conversionEmployeesBasic(null, dto, acctName);
		employeesBasicDao.save(basic); // 保存员工基本信息
		basic.setEmState(EmployeesState.EMPL_INTERNSHIP); // 默认为实习员工
		employeesDetailDao.save(conversionEmployeesDetail(null, dto, basic.getEmId())); // 保存员工详细信息
		conversionEmployeesCompany(dto.getCompany(), basic.getEmId()); // 保存员工工作经历信息
		basic.setEmCensusRegister(analyzeAddress(dto.getRegister(), basic.getEmId(), AddressType.REGISTER, null, true)); // 解析户籍地址
		basic.setEmCurrentAddress(analyzeAddress(dto.getCurrent(), basic.getEmId(), AddressType.CURRENT, null, true)); // 解析现居住地址
		employeesLogService.save(basic.getEmId(), acctName, EmployeesLogType.RECORDING_INFORMATION, "");
		return JSONReturn.buildSuccess(basic.getEmId());
	}

	/*
	 * 解析并转换员工地址信息
	 * te5l.com [K]
	 */
	private long analyzeAddress(EmployeesAddressDto dto, long emId, int type, TeAddress ads, boolean isSave) {
		// TODO Auto-generated method stub
		if (CompareUtil.isEmpty(ads))
			ads = new TeAddress();
		ads.setAdsType(type);
		ads.setAdsProvince(dto.getProvince());
		ads.setAdsCity(dto.getCity());
		ads.setAdsCounty(dto.getCounty());
		ads.setAdsTownship(dto.getTownship());
		ads.setAdsVillage(dto.getVillage());
		ads.setAdsDetailed(dto.getDetailed());
		if (isSave)
			addressDao.save(ads);
		return ads.getAdsId();
	}

	/*
	 * 解析并转换员工工作经历信息
	 * te5l.com [K]
	 */
	private boolean conversionEmployeesCompany(EmployeesCompnayDto[] companyArray, long emId) {
		// TODO Auto-generated method stub
		if (ArrayUtils.isEmpty(companyArray))
			return false;
		for (EmployeesCompnayDto dto : companyArray) {
			TeEmployeesCompany company = new TeEmployeesCompany();
			company.setEmplNo(emId);
			company.setComName(dto.getName());
			company.setComParticipateTime(dto.getState());
			company.setComLeaveTime(dto.getEnd());
			company.setComPosition(dto.getJobs());
			company.setComReason(dto.getReason());
			employeesCompanyDao.save(company);
		}
		return true;
	}

	/*
	 * 解析并转换员工详细信息
	 * te5l.com [K]
	 */
	private TeEmployeesDetail conversionEmployeesDetail(TeEmployeesDetail detail, EmployeesInfoDto dto, long emId) {
		// TODO Auto-generated method stub
		if (CompareUtil.isEmpty(detail))
			detail = new TeEmployeesDetail();
		detail.setEmplNo(emId);
		detail.setEmContact(dto.getContact());
		detail.setEmEmergencyContact(dto.getEmergencyContact());
		detail.setEmEmergencyPhone(dto.getEmergencyPhone());
		detail.setEmSchool(dto.getSchool());
		detail.setEmProfessional(dto.getProfessional());
		detail.setEmGraduationTime(dto.getGraduationTime());
		detail.setEmSchooling(dto.getSchooling());
		detail.setEmDegree(dto.getDegree());
		detail.setEmIsSocialSecurity(dto.isPaySocialSecurity());
		detail.setEmNote(dto.getNote());
		return detail;
	}

	/*
	 * 解析并转换员工基本信息
	 * te5l.com [K]
	 */
	private TeEmployeesBasic conversionEmployeesBasic(TeEmployeesBasic basic, EmployeesInfoDto dto, String acctName) {
		// TODO Auto-generated method stub'
		if (CompareUtil.isEmpty(basic))
			basic = new TeEmployeesBasic();
		basic.setEmPhoto(dto.getPhoto());
		basic.setEmFullName(dto.getFullName());
		basic.setEmSex(dto.isSex());
		basic.setEmIdentity(dto.getIdentity());
		basic.setEmBirthday(dto.getBirthday());
		basic.setEmParticipateTime(dto.getParticipate());
		basic.setEmPhone(dto.getPhone());
		basic.setEmSocialSecurity(dto.getSocialSecurity());
		basic.setEmDeparemtn(dto.getDeparemtn());
		basic.setEmPosition(dto.getPosition());
		basic.setEmEducation(dto.getEducation());
		basic.setEmMarriage(dto.getMarriage());
		basic.setEmPolitics(dto.getPolitics());
		basic.setEmNational(dto.getNational());
		// basic.setEmCurrentAddress(0);	// 现居住地址
		// basic.setEmCensusRegister(0);	// 户籍地址
		basic.setCreateTime(DateTimeUtil.getCurrentTime());
		basic.setCreator(acctName);
		return basic;
	}

	public JSONReturn findEmployeesList(int type, String value, long dept, long position, int page, String acctName,
			int... emplType) {
		// TODO Auto-generated method stub
		List<EmployeesInternshipListDto> dtoList = null;
		dtoList = employeesBasicDao.findEmployeesList(type, value, dept, position, page, emplType);
		if (CollectionUtils.isEmpty(dtoList))
			return JSONReturn.buildFailure("未获取到相关数据!");
		TeDepartment department = null;
		TePosition post = null;
		for (EmployeesInternshipListDto dto : dtoList) {
			if (dto.getDeptId() > 0) {
				department = departmentDao.findUniqueByProperty(TeDepartmentField.DEPT_ID, dto.getDeptId());
				dto.setDepartment(CompareUtil.isNotEmpty(department) ? department.getDeptName() : null);
			}
			if (dto.getPositionId() > 0) {
				post = positionDao.findUniqueByProperty(TePositionField.PO_ID, dto.getPositionId());
				dto.setPosition(CompareUtil.isNotEmpty(post) ? post.getPoName() : null);
			}
		}
		return JSONReturn.buildSuccess(dtoList);
	}

	public JSONReturn findEmployeesPage(int type, String value, long dept, long position, int page, String acctName,
			int... emplType) {
		// TODO Auto-generated method stub
		int count = employeesBasicDao.findEmployeesPage(type, value, dept, position, emplType);
		return JSONReturn.buildSuccess(PageUtils.calculatePage(page, count, PageConstant.PAGE_LIST));
	}

	@Transactional
	public JSONReturn enroll(long emId, String acctName) {
		// TODO Auto-generated method stub
		if (emId < 1)
			return JSONReturn.buildFailure("操作失败, 传入参数错误!");
		TeEmployeesBasic empl = employeesBasicDao.findUniqueByProperty(TeEmployeesBasicField.EM_ID, emId);
		if (CompareUtil.isEmpty(empl))
			return JSONReturn.buildFailure("操作失败, 员工信息不存在!");
		if (empl.getEmState() == EmployeesState.EMPL_FORMAL)
			return JSONReturn.buildFailure("操作失败, 该员工已经被录取!");
		if (empl.getEmDeparemtn() < 1)
			return JSONReturn.buildFailure("操作失败, <b>" + empl.getEmFullName() + "</b> 未被分配到某个部门下!");
		if (empl.getEmPosition() < 1)
			return JSONReturn.buildFailure("操作失败, 没有为该 <b>" + empl.getEmFullName() + "</b> 分配职位");
		employeesLogService.save(emId, acctName, EmployeesLogType.INDUCTION, "");
		empl.setEmState(EmployeesState.EMPL_FORMAL);
		return JSONReturn.buildSuccess("操作成功, 该员工已被录取!");
	}

	@Transactional
	public JSONReturn eliminate(long emId, String note, String acctName) {
		// TODO Auto-generated method stub
		if (emId < 1)
			return JSONReturn.buildFailure("操作失败, 传入参数错误!");
		TeEmployeesBasic empl = employeesBasicDao.findUniqueByProperty(TeEmployeesBasicField.EM_ID, emId);
		if (CompareUtil.isEmpty(empl))
			return JSONReturn.buildFailure("操作失败, 员工信息不存在!");
		if (empl.getEmState() == EmployeesState.EMPL_ELIMINATE)
			return JSONReturn.buildFailure("操作失败, 员工已经被淘汰!");
		if (empl.getEmState() == EmployeesState.EMPL_DEPARTURE)
			return JSONReturn.buildFailure("操作失败, 该员工已经离职!");
		employeesLogService.save(emId, acctName, EmployeesLogType.ELIMINATE, note);
		empl.setEmState(EmployeesState.EMPL_ELIMINATE);
		return JSONReturn.buildSuccess("操作成功, 该员工已经被淘汰, 可在离职员工列表中查看到该员工!");
	}

	public JSONReturn findEmployeesInfo(long emplId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		TeEmployeesBasic empl = employeesBasicDao.findUniqueByProperty(TeEmployeesBasicField.EM_ID, emplId);
		if (CompareUtil.isEmpty(empl))
			return JSONReturn.buildFailure("员工不存在!");
		TeEmployeesDetail detail = employeesDetailDao.findUniqueByProperty(TeEmployeesDetailField.EMPL_NO, emplId);
		EmployeesBasicInfoDto dto = new EmployeesBasicInfoDto();
		dto = conversionBasicInfo(empl, dto);
		dto = conversionDetailInfo(detail, dto);
		dto.setState(empl.getEmState());
		map.put("info", dto);
		map.put("educations", chooseEducationDao.findAll());
		map.put("marriages", chooseMarriageDao.findAll());
		map.put("nationals", chooseNationalDao.findAll());
		map.put("politics", choosePoliticsDao.findAll());
		map.put("departments", departmentDao.findAllDepartment());
		if (empl.getEmDeparemtn() > 0)
			map.put("positions", positionDao.findByDeptId(empl.getEmDeparemtn()));
		map.put("companies", employeesCompanyDao.findByProperty(TeEmployeesDetailField.EMPL_NO, empl.getEmId()));

		map.put("province", provinceDao.findAllProvince());
		TeAddress register = addressDao.findUniqueByProperty(TeAddressField.ADS_ID, empl.getEmCensusRegister());
		TeAddress current = addressDao.findUniqueByProperty(TeAddressField.ADS_ID, empl.getEmCurrentAddress());
		if (CompareUtil.isNotEmpty(register)) {
			map.put("register", register);
			map.put("registerCity", cityDao.findCityByProvinceId(register.getAdsProvince()));
			map.put("registerCounty", countyDao.findCountyByCityId(register.getAdsCity()));
			map.put("registerTownship", townshipDao.findTownshipByCountyId(register.getAdsCounty()));
			map.put("registerVillage", villageDao.findVillageByTownshipId(register.getAdsTownship()));
		}
		if (CompareUtil.isNotEmpty(current)) {
			map.put("current", current);
			map.put("currentCity", cityDao.findCityByProvinceId(current.getAdsProvince()));
			map.put("currentCounty", countyDao.findCountyByCityId(current.getAdsCity()));
			map.put("currentTownship", townshipDao.findTownshipByCountyId(current.getAdsCounty()));
			map.put("currentVillage", villageDao.findVillageByTownshipId(current.getAdsTownship()));
		}

		return JSONReturn.buildSuccess(map);
	}

	private EmployeesBasicInfoDto conversionDetailInfo(TeEmployeesDetail detail, EmployeesBasicInfoDto dto) {
		// TODO Auto-generated method stub
		if (CompareUtil.isEmpty(detail))
			return dto;
		dto.setContact(detail.getEmContact());
		dto.setEmergencyContact(detail.getEmEmergencyContact());
		dto.setEmergencyPhone(detail.getEmEmergencyPhone());
		dto.setSchool(detail.getEmSchool());
		dto.setProfessional(detail.getEmProfessional());
		dto.setGraduationTime(detail.getEmGraduationTime());
		dto.setSchooling(detail.getEmSchooling());
		dto.setDegree(detail.getEmDegree());
		dto.setSocialSecurity(detail.getEmIsSocialSecurity());
		dto.setNote(detail.getEmNote());
		return dto;
	}

	private EmployeesBasicInfoDto conversionBasicInfo(TeEmployeesBasic basic, EmployeesBasicInfoDto dto) {
		// TODO Auto-generated method stub
		if (CompareUtil.isEmpty(basic))
			return dto;
		dto.setId(basic.getEmId());
		dto.setPhoto(basic.getEmPhoto());
		dto.setName(basic.getEmFullName());
		dto.setSex(basic.getEmSex());
		dto.setIdentity(basic.getEmIdentity());
		dto.setBirthday(basic.getEmBirthday());
		dto.setParticipateTime(basic.getEmParticipateTime());
		dto.setPhone(basic.getEmPhone());
		dto.setSocialSecurity(basic.getEmSocialSecurity());
		dto.setDeparemtn(basic.getEmDeparemtn());
		dto.setPosition(basic.getEmPosition());
		dto.setEducation(basic.getEmEducation());
		dto.setMarriage(basic.getEmMarriage());
		dto.setPolitics(basic.getEmPolitics());
		dto.setNational(basic.getEmNational());
		dto.setCurrent(basic.getEmCurrentAddress());
		dto.setRegister(basic.getEmCensusRegister());
		return dto;
	}

	@Transactional
	public JSONReturn modifyEmployeesInfo(EmployeesInfoDto dto, String acctName) {
		// TODO Auto-generated method stub
		if (CompareUtil.isEmpty(dto))
			return JSONReturn.buildFailure("员工信息保存失败!");
		TeEmployeesBasic empl = employeesBasicDao.findUniqueByProperty(TeEmployeesBasicField.EM_ID, dto.getEmplId());
		if (CompareUtil.isEmpty(empl))
			return JSONReturn.buildFailure("保存失败, 员工信息不存在!");
		List<TeEmployeesBasic> basics = employeesBasicDao.findByProperty(TeEmployeesBasicField.EM_IDENTITY,
				dto.getIdentity());
		if (empl.getEmState() == EmployeesState.EMPL_DEPARTURE)
			return JSONReturn.buildFailure("非法操作, 该员工已经离职!");
		if (empl.getEmState() == EmployeesState.EMPL_ELIMINATE)
			return JSONReturn.buildFailure("非法操作, 该员工已经被淘汰, 不能修改信息!");
		if (!empl.getEmIdentity().equals(dto.getIdentity()) && CollectionUtils.isNotEmpty(basics))
			return JSONReturn.buildFailure("保存员工信息失败, 身份证重复!");
		empl = conversionEmployeesBasic(empl, dto, acctName); // 修改员工基本信息
		TeEmployeesDetail detail = employeesDetailDao.findUniqueByProperty(TeEmployeesDetailField.EMPL_NO,
				empl.getEmId());
		if (CompareUtil.isEmpty(detail)) {
			detail = conversionEmployeesDetail(null, dto, empl.getEmId());
			employeesDetailDao.save(detail); // 保存员工详细信息
		}
		detail = conversionEmployeesDetail(detail, dto, empl.getEmId());
		employeesCompanyDao.deleteByProperty(TeEmployeesDetailField.EMPL_NO, empl.getEmId()); // 删除该员工所有工作信息
		conversionEmployeesCompany(dto.getCompany(), empl.getEmId()); // 保存员工工作经历信息
		TeAddress register = addressDao.findUniqueByProperty(TeAddressField.ADS_ID, empl.getEmCensusRegister());
		TeAddress current = addressDao.findUniqueByProperty(TeAddressField.ADS_ID, empl.getEmCurrentAddress());
		// 解析户籍地址
		empl.setEmCensusRegister(analyzeAddress(dto.getRegister(), empl.getEmId(), AddressType.REGISTER, register,
				CompareUtil.isEmpty(register)));
		// 解析现居住地址 
		empl.setEmCurrentAddress(analyzeAddress(dto.getCurrent(), empl.getEmId(), AddressType.CURRENT, current,
				CompareUtil.isEmpty(current)));
		employeesLogService.save(empl.getEmId(), acctName, EmployeesLogType.MODIFY_INFORMATION, "");
		return JSONReturn.buildSuccess(empl.getEmId());
	}

	@Transactional
	public JSONReturn departure(long id, String note, String acctName) {
		// TODO Auto-generated method stub
		TeEmployeesBasic empl = employeesBasicDao.findById(id);
		if (CompareUtil.isEmpty(empl))
			return JSONReturn.buildFailure("操作失败, 员工信息不存在!");
		if (empl.getEmState() == EmployeesState.EMPL_DEPARTURE)
			return JSONReturn.buildFailure("重复操作, 该员工已经离职!");
		if (empl.getEmState() == EmployeesState.EMPL_ELIMINATE)
			return JSONReturn.buildFailure("操作失败, 该员工为淘汰状态, 不能离职!");
		empl.setEmState(EmployeesState.EMPL_DEPARTURE);
		employeesLogService.save(id, acctName, EmployeesLogType.DEPARTURE, note);
		return JSONReturn.buildSuccess("操作成功, 已将该员工离职!");
	}

	public JSONReturn findTrainingRecord(long emplId, String acctName) {
		// TODO Auto-generated method stub
		TeEmployeesBasic empl = employeesBasicDao.findById(emplId);
		if (CompareUtil.isEmpty(empl))
			return JSONReturn.buildFailure("操作失败, 员工不存在");
		List<EmployeesTrainingRecordDto> dtoList = employeesTrainingLogDao.findTrainingRecord(emplId);
		if (CollectionUtils.isEmpty(dtoList))
			return JSONReturn.buildFailure("还没有发现 <b>" + empl.getEmFullName() + "</b> 有任何的培训记录!");
		for (EmployeesTrainingRecordDto dto : dtoList)
			dto.setStatus(TrainLogState.findStatus(dto.getState()));
		return JSONReturn.buildSuccess(dtoList);
	}

	public JSONReturn findEmployeesList(long trainId, long deptId, String seVal, String acctName) {
		// TODO Auto-generated method stub
		List<EemplByNoneTrainingLogDto> dtoList = employeesBasicDao.findEemplByNoneTrainingLog(trainId, deptId, seVal);
		if (CollectionUtils.isEmpty(dtoList))
			return JSONReturn.buildFailure("没有获取到相关员工信息!");
		TeDepartment dept = null;
		for (EemplByNoneTrainingLogDto dto : dtoList) {
			if (dto.getEmplDept() < 1)
				continue;
			dept = departmentDao.findById(dto.getEmplDept());
			if (CompareUtil.isNotEmpty(dept))
				dto.setDepartment(dept.getDeptName());
		}
		return JSONReturn.buildSuccess(dtoList);
	}

	@Transactional
	public JSONReturn addAllTraining(long trainId, long deptId, String seVal, String acctName) {
		// TODO Auto-generated method stub
		List<EemplByNoneTrainingLogDto> dtoList = employeesBasicDao.findEemplByNoneTrainingLog(trainId, deptId, seVal);
		if (CollectionUtils.isEmpty(dtoList))
			return JSONReturn.buildFailure("没有获取到相关员工信息!");
		for (EemplByNoneTrainingLogDto dto : dtoList) {
			TeEmployeesBasic empl = employeesBasicDao.findById(dto.getId());
			if (CompareUtil.isEmpty(empl))
				return JSONReturn.buildFailure("操作失败, 员工信息不存在!");
			TeTraining training = trainingDao.findById(trainId);
			if (CompareUtil.isEmpty(training))
				return JSONReturn.buildFailure("操作失败, 培训项目不存在!");
			if (training.getState() == TrainingState.FINISH)
				return JSONReturn.buildFailure("操作失败, 该培训已经结束!");
			if (training.getState() == TrainingState.HAVE_BEGUN && !training.getIsInsertAttend())
				return JSONReturn.buildFailure("操作失败, 该培训已经开始且不允许中途参加");
			TeEmployeesTrainingLog tog = conversionTeEmployeesTrainingLog(dto, trainId, acctName, training.getState());
			if (CompareUtil.isEmpty(tog))
				continue;
			employeesTrainingLogDao.save(tog);
			training.setNumber(training.getNumber() == null ? 0 + 1 : training.getNumber() + 1);
		}
		return JSONReturn.buildSuccess("添加成功!");
	}

	private TeEmployeesTrainingLog conversionTeEmployeesTrainingLog(EemplByNoneTrainingLogDto dto, long trainId,
			String acctName, int trainState) {
		// TODO Auto-generated method stub
		if (CompareUtil.isEmpty(dto))
			return null;
		TeEmployeesTrainingLog tog = new TeEmployeesTrainingLog();
		tog = employeesTrainingLogDao.findByEmplTraining(dto.getId(), trainId);
		if (CompareUtil.isNotEmpty(tog))
			return null;
		tog = new TeEmployeesTrainingLog();
		tog.setCreateTime(DateTimeUtil.getCurrentTime());
		tog.setCreator(acctName);
		tog.setEmplId(dto.getId());
		tog.setTrainingItemId(trainId);
		tog.setApplyTime(DateTimeUtil.conversionTime(DateTimeUtil.getCurrentTime(), TimeFormatConstant.YYYY_MM_DD));
		tog.setNote("");
		tog.setState(trainState == TrainingState.NOT_STARTED ? TrainLogState.APPLY : TrainLogState.START);
		return tog;
	}

	@Transactional
	public JSONReturn destroy(long emplId, String acctName) {
		// TODO Auto-generated method stub
		TeEmployeesBasic employeesBasic = employeesBasicDao.findById(emplId);
		if (CompareUtil.isEmpty(employeesBasic))
			return JSONReturn.buildFailure("操作失败, 员工信息不存在!");
		employeesBasicDao.delete(employeesBasic);
		employeesDetailDao.deleteByProperty(TeEmployeesDetailField.EMPL_NO, emplId);
		employeesCompanyDao.deleteByProperty(TeEmployeesCompanyField.EMPL_NO, emplId);
		employeesLogDao.deleteByProperty(TeEmployeesLogField.EMPL_ID, emplId);
		employeesTrainingLogDao.deleteByProperty(TeEmployeesTrainingLogField.EMPL_ID, emplId);
		return JSONReturn.buildSuccess("员工信息销毁成功!");
	}

	public JSONReturn findDepartureNote(long emplId, String acctName) {
		// TODO Auto-generated method stub
		TeEmployeesLog eog = employeesLogDao.findDepartureNote(emplId);
		if (CompareUtil.isEmpty(eog))
			return JSONReturn.buildFailure("没有获取到相关信息!");
		return JSONReturn.buildSuccess(eog.getNote());
	}

	@Transactional
	public JSONReturn enrollEmployees(long emplId, String note, String acctName) {
		// TODO Auto-generated method stub
		TeEmployeesBasic empl = employeesBasicDao.findById(emplId);
		if (CompareUtil.isEmpty(empl))
			return JSONReturn.buildFailure("操作失败, 员工信息不存在!");
		if (empl.getEmState() != EmployeesState.EMPL_DEPARTURE && empl.getEmState() != EmployeesState.EMPL_ELIMINATE)
			return JSONReturn.buildFailure("操作失败, 该员工当前非离职或淘汰状态!");
		empl.setEmState(EmployeesState.EMPL_INTERNSHIP);
		employeesLogService.save(emplId, acctName, EmployeesLogType.AGAIN_INDUCTION, note);
		return JSONReturn.buildSuccess("操作成功!");
	}

	public JSONReturn uploadImg(MultipartFile imgFile, HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		if (CompareUtil.isEmpty(imgFile) || imgFile.isEmpty())
			return JSONReturn.buildFailure("上传头像失败, 文件不存在!");
		if (imgFile.getSize() > PhoneImageSize.PHONE_IMAGE_SIZE)
			return JSONReturn.buildFailure("上传头像失败, 文件过大!");
		String imgName = imgFile.getOriginalFilename();
		String extensionName = imgName.substring(imgName.lastIndexOf('.'));
		imgName = new Date().getTime() + extensionName;
		String rootPath = request.getSession().getServletContext().getRealPath("");
		File file = new File(rootPath + "/upload/photo/" + imgName);
		if (!file.exists())
			file.mkdirs();
		imgFile.transferTo(file);
		return JSONReturn.buildSuccess("/upload/photo/" + imgName);
	}

	public JSONReturn findEmployeesRecord(long emplId) {
		// TODO Auto-generated method stub
		List<TeEmployeesLog> logList = employeesLogDao.findByProperty(TeEmployeesLogField.EMPL_ID, emplId);
		if (CollectionUtils.isEmpty(logList))
			return JSONReturn.buildFailure("没有获取到该员工相关记录信息!!!");
		return JSONReturn.buildSuccess(conversionEmployeesRecord(logList));
	}

	private List<EmployeesLogDto> conversionEmployeesRecord(List<TeEmployeesLog> logs) {
		// TODO Auto-generated method stub
		List<EmployeesLogDto> list = new ArrayList<EmployeesLogDto>();
		EmployeesLogDto dto = null;
		for (int i = logs.size() - 1; i >= 0; i--) {
			dto = new EmployeesLogDto();
			dto.setId(logs.get(i).getId());
			dto.setCreator(logs.get(i).getCreator());
			dto.setNote(logs.get(i).getNote());
			dto.setTime(DateTimeUtil.conversionTime(logs.get(i).getCreateTime(), TimeFormatConstant.Y_M_D_H_M_S));
			dto.setType(EmployeesLogType.findStatus(logs.get(i).getType()));
			list.add(dto);
		}
		return list;
	}

}
