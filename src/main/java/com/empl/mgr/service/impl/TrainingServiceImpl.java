package com.empl.mgr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empl.mgr.constant.PageConstant;
import com.empl.mgr.constant.TimeFormatConstant;
import com.empl.mgr.constant.TrainLogState;
import com.empl.mgr.constant.TrainingState;
import com.empl.mgr.dao.EmployeesBasicDao;
import com.empl.mgr.dao.EmployeesTrainingLogDao;
import com.empl.mgr.dao.TrainingDao;
import com.empl.mgr.dao.TrainingNoteDao;
import com.empl.mgr.dto.TrainingListDto;
import com.empl.mgr.dto.TrainingRecordDto;
import com.empl.mgr.field.TeTrainingField;
import com.empl.mgr.field.TeTrainingNoteField;
import com.empl.mgr.model.TeEmployeesBasic;
import com.empl.mgr.model.TeEmployeesTrainingLog;
import com.empl.mgr.model.TeTraining;
import com.empl.mgr.model.TeTrainingNote;
import com.empl.mgr.service.TrainingService;
import com.empl.mgr.support.JSONReturn;
import com.empl.mgr.utils.CompareUtil;
import com.empl.mgr.utils.DateTimeUtil;
import com.empl.mgr.utils.PageUtils;
import com.empl.mgr.utils.VerifyUtils;

@Scope
@Service
@Transactional(readOnly = true)
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingDao trainingDao;
	@Autowired
	private TrainingNoteDao trainingNoteDao;
	@Autowired
	private EmployeesTrainingLogDao employeesTrainingLogDao;
	@Autowired
	private EmployeesBasicDao employeesBasicDao;

	@Transactional
	public JSONReturn addTraining(String name, String desc, String start, String end, boolean isInsertAttend,
			String acctName) {
		// TODO Auto-generated method stub
		if (!VerifyUtils.verifyDate(start))
			return JSONReturn.buildFailure("开始日期错误!");
		if (!VerifyUtils.verifyDate(end))
			return JSONReturn.buildFailure("结束日期错误!");
		if (!VerifyUtils.verdictSize(start, end, true))
			return JSONReturn.buildFailure("开始日期 不能超过 结束日期!");
		List<TeTraining> studieList = trainingDao.findByProperty(TeTrainingField.NAME, name);
		if (CollectionUtils.isNotEmpty(studieList))
			return JSONReturn.buildFailure("添加失败, 存在同名的培训项目!");
		trainingDao.save(new TeTraining(name, desc, 0, start, end, isInsertAttend, TrainingState.NOT_STARTED,
				DateTimeUtil.getCurrentTime(), acctName));
		return JSONReturn.buildSuccess("添加成功!");
	}

	@Transactional
	public JSONReturn modifyTraining(long id, String name, String description, String start, String end,
			boolean isInsertAttend, String acctName) {
		// TODO Auto-generated method stub
		TeTraining training = trainingDao.findUniqueByProperty(TeTrainingField.ID, id);
		if (CompareUtil.isEmpty(training))
			return JSONReturn.buildFailure("修改失败, 数据源不存在!");
		if (!training.getName().equals(name)) {
			List<TeTraining> studieList = trainingDao.findByProperty(TeTrainingField.NAME, name);
			if (CollectionUtils.isNotEmpty(studieList))
				return JSONReturn.buildFailure("修改失败, 存在同名的培训项目!");
		}
		if (!VerifyUtils.verifyDate(start))
			return JSONReturn.buildFailure("开始日期错误!");
		if (!VerifyUtils.verifyDate(end))
			return JSONReturn.buildFailure("结束日期错误!");
		if (!VerifyUtils.verdictSize(start, end, true))
			return JSONReturn.buildFailure("开始日期 不能超过 结束日期!");
		training.setName(name);
		training.setDescription(description);
		training.setStartTime(start);
		training.setEndTime(end);
		training.setIsInsertAttend(isInsertAttend);
		return JSONReturn.buildSuccess("培训项目修改成功!");
	}

	public JSONReturn findTrainingList(String searchValue, int page, String acctName) {
		// TODO Auto-generated method stub
		List<TeTraining> trainingList = trainingDao.findTrainingList(searchValue, page);
		if (CollectionUtils.isEmpty(trainingList))
			return JSONReturn.buildFailure("未获取到相关数据!");
		return JSONReturn.buildSuccess(conversionTrainingItem(trainingList));
	}

	/**
	 * @param s
	 * @return
	 */
	private List<TrainingListDto> conversionTrainingItem(List<TeTraining> trainingList) {
		List<TrainingListDto> dtoList = new ArrayList<TrainingListDto>();
		if (CollectionUtils.isEmpty(trainingList))
			return dtoList;
		for (TeTraining s : trainingList) {
			TrainingListDto dto = new TrainingListDto();
			dto.setId(s.getId());
			dto.setName(s.getName());
			dto.setDescription(s.getDescription());
			dto.setNumber(s.getNumber() == null ? 0 : s.getNumber());
			dto.setStart(s.getStartTime());
			dto.setEnd(s.getEndTime());
			dto.setCreator(s.getCreator());
			dto.setInsertion(s.getIsInsertAttend());
			dto.setTime(DateTimeUtil.conversionTime(s.getCreateTime(), TimeFormatConstant.YYYY_MM_DD));
			dto.setState(s.getState() == null ? 0 : s.getState());
			dto.setStateKey(TrainingState.findVal(dto.getState()));
			dtoList.add(dto);
		}
		return dtoList;
	}

	public JSONReturn findTrainingPage(String searchValue, int page, String acctName) {
		// TODO Auto-generated method stub
		int count = trainingDao.findTrainingCount(searchValue);
		return JSONReturn.buildSuccess(PageUtils.calculatePage(page, count, PageConstant.PAGE_LIST));
	}

	@Transactional
	public JSONReturn delete(long id, String acctName) {
		// TODO Auto-generated method stub
		TeTraining training = trainingDao.findUniqueByProperty(TeTrainingField.ID, id);
		if (CompareUtil.isEmpty(training))
			return JSONReturn.buildFailure("删除失败, 数据源不存在!");
		// 删除当前和当前培训项目所有关联的记录
		trainingDao.delete(training);
		return JSONReturn.buildSuccess("删除成功!");
	}

	public JSONReturn findTrainingInfoById(long id, String acctName) {
		// TODO Auto-generated method stub
		TeTraining training = trainingDao.findUniqueByProperty(TeTrainingField.ID, id);
		if (CompareUtil.isEmpty(training))
			return JSONReturn.buildFailure("操作失败, 未获取到数据!");
		TrainingListDto dto = new TrainingListDto();
		dto.setId(training.getId());
		dto.setName(training.getName());
		dto.setNumber(training.getNumber() == null ? 0 : training.getNumber());
		dto.setDescription(training.getDescription());
		dto.setStart(training.getStartTime());
		dto.setEnd(training.getEndTime());
		dto.setTime(DateTimeUtil.conversionTime(training.getCreateTime(), TimeFormatConstant.YYYY_MM_DD));
		dto.setState(training.getState() == null ? 0 : training.getState());
		dto.setStateKey(TrainingState.findVal(dto.getState()));
		dto.setInsertion(training.getIsInsertAttend());
		TeTrainingNote note = trainingNoteDao.findUniqueByProperty(TeTrainingNoteField.TRAINING_ID, id);
		if (CompareUtil.isNotEmpty(note))
			dto.setTrainingResult(note.getNote());
		return JSONReturn.buildSuccess(dto);
	}

	@Transactional
	public JSONReturn start(long trainId, String acctName) {
		// TODO Auto-generated method stub
		TeTraining teTraining = trainingDao.findById(trainId);
		if (CompareUtil.isEmpty(teTraining))
			return JSONReturn.buildFailure("操作失败, 项目不存在!");
		if (teTraining.getState() == TrainingState.HAVE_BEGUN)
			return JSONReturn.buildFailure("操作失败, 该培训项目已经启动!");
		if (teTraining.getState() == TrainingState.FINISH)
			return JSONReturn.buildFailure("操作失败, 该培训项目已经完成!");
		teTraining.setState(TrainingState.HAVE_BEGUN);
		// 修改当前培训下的所有记录状态为培训中
		employeesTrainingLogDao.modifyTrainingRecordStart(trainId);
		return JSONReturn.buildSuccess("项目开始成功!");
	}

	@Transactional
	public JSONReturn stop(long trainId, String note, String enddate, String acctName) {
		// TODO Auto-generated method stub
		TeTraining teTraining = trainingDao.findById(trainId);
		if (CompareUtil.isEmpty(teTraining))
			return JSONReturn.buildFailure("操作失败, 项目不存在!");
		if (!VerifyUtils.verifyDate(enddate))
			return JSONReturn.buildFailure("结束日期错误!");
		if (!VerifyUtils.verdictSize(teTraining.getStartTime(), enddate, false))
			return JSONReturn.buildFailure("结束日期不得小于开始日期");
		if (teTraining.getState() == TrainingState.FINISH)
			return JSONReturn.buildFailure("操作失败, 该培训项目已经结束!");
		teTraining.setState(TrainingState.FINISH);
		trainingNoteDao.save(new TeTrainingNote(trainId, TrainingState.FINISH, note, new Date(), acctName));
		// 修改当前培训下的所有培训中的状态为已完成
		employeesTrainingLogDao.modifyTrainingRecordStop(trainId, note);
		return JSONReturn.buildSuccess("项目停止成功!");
	}

	public JSONReturn findRecord(long trainingId, String acctName) {
		// TODO Auto-generated method stub
		TeTraining training = trainingDao.findById(trainingId);
		if (CompareUtil.isEmpty(training))
			return JSONReturn.buildFailure("操作失败, 培训项目不存在!");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", training.getState());
		map.put("attend", training.getIsInsertAttend());
		List<TrainingRecordDto> dtoList = trainingDao.findRecord(trainingId);
		if (CollectionUtils.isEmpty(dtoList))
			return JSONReturn.buildSuccess(map);
		for (TrainingRecordDto dto : dtoList)
			dto.setStatus(TrainLogState.findStatus(dto.getState()));
		map.put("dtoList", dtoList);
		return JSONReturn.buildSuccess(map);
	}

	@Transactional
	public JSONReturn addTrainingByEmplId(long emplId, long trainingId, String acctName) {
		// TODO Auto-generated method stub
		TeEmployeesBasic empl = employeesBasicDao.findById(emplId);
		if (CompareUtil.isEmpty(empl))
			return JSONReturn.buildFailure("操作失败, 员工信息不存在!");
		TeTraining training = trainingDao.findById(trainingId);
		if (CompareUtil.isEmpty(training))
			return JSONReturn.buildFailure("操作失败, 培训项目不存在!");
		if (training.getState() == TrainingState.FINISH)
			return JSONReturn.buildFailure("操作失败, 该培训已经结束!");
		if (training.getState() == TrainingState.HAVE_BEGUN && !training.getIsInsertAttend())
			return JSONReturn.buildFailure("操作失败, 该培训已经开始且不允许中途参加");
		TeEmployeesTrainingLog tog = employeesTrainingLogDao.findByEmplTraining(emplId, trainingId);
		if (CompareUtil.isNotEmpty(tog))
			return JSONReturn.buildFailure("操作失败, 该员工已经报名, 不能重复添加!");
		tog = new TeEmployeesTrainingLog();
		tog.setCreateTime(DateTimeUtil.getCurrentTime());
		tog.setCreator(acctName);
		tog.setEmplId(emplId);
		tog.setTrainingItemId(trainingId);
		tog.setApplyTime(DateTimeUtil.conversionTime(DateTimeUtil.getCurrentTime(), TimeFormatConstant.YYYY_MM_DD));
		tog.setNote("");
		tog.setState(training.getState() == TrainingState.NOT_STARTED ? TrainLogState.APPLY : TrainLogState.START);
		employeesTrainingLogDao.save(tog);
		training.setNumber(training.getNumber() == null ? 0 + 1 : training.getNumber() + 1);
		return JSONReturn.buildSuccessWithEmptyBody();
	}

	@Transactional
	public JSONReturn delEmployeesTrainingRecord(long id, String acctName) {
		// TODO Auto-generated method stub
		TeEmployeesTrainingLog tog = employeesTrainingLogDao.findById(id);
		if (CompareUtil.isEmpty(tog))
			return JSONReturn.buildFailure("删除失败, 报名记录不存在!");
		TeTraining training = trainingDao.findById(tog.getTrainingItemId());
		if (CompareUtil.isEmpty(training))
			return JSONReturn.buildFailure("操作失败, 服务项目不存在!");
		employeesTrainingLogDao.delete(tog);
		training.setNumber(training.getNumber() - 1);
		return JSONReturn.buildSuccess("删除成功!");
	}

	@Transactional
	public JSONReturn stopEmployeesTraining(long id, String note, String acctName) {
		// TODO Auto-generated method stub
		TeEmployeesTrainingLog log = employeesTrainingLogDao.findById(id);
		if (CompareUtil.isEmpty(log))
			return JSONReturn.buildFailure("操作失败, 数据源不存在!");
		else if (log.getState() == TrainLogState.EXIT)
			return JSONReturn.buildFailure("操作失败, 重复退出!");
		else if (log.getState() == TrainLogState.FINISH)
			return JSONReturn.buildFailure("操作失败, 培训已经完成, 不能退出!");
		log.setState(TrainLogState.EXIT);
		log.setNote(note);
		return JSONReturn.buildSuccess("退出成功!");
	}

	@Transactional
	public JSONReturn evaluationEmployeesTraining(long id, String note, String acctName) {
		// TODO Auto-generated method stub
		TeEmployeesTrainingLog log = employeesTrainingLogDao.findById(id);
		if (CompareUtil.isEmpty(log))
			return JSONReturn.buildFailure("操作失败, 数据源不存在!");
		if (log.getState() != TrainLogState.FINISH)
			return JSONReturn.buildFailure("操作失败, 该记录非培训完成状态!");
		log.setState(TrainLogState.EVALUATION); // 修改状态为已评分
		log.setNote(note);
		return JSONReturn.buildSuccess("打分成功!");
	}
}
