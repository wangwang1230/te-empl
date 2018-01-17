package com.empl.mgr.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empl.mgr.service.RoleModuleService;

@Scope
@Service
@Transactional(readOnly = true)
public class RoleModuleServiceImpl implements RoleModuleService {

}
