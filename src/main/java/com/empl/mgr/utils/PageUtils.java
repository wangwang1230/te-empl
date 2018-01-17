package com.empl.mgr.utils;

import java.io.Serializable;

import com.empl.mgr.dto.PageDto;

public class PageUtils implements Serializable {

	private static final long serialVersionUID = 1L;

	public static PageDto calculatePage(int nowPage, int count, int number) {
		PageDto dto = new PageDto();
		dto.setNumber(number);
		dto.setCount(count);
		dto.setNowPage(nowPage < 1 ? 1 : nowPage);
		dto.setTotalPage(count % number > 0 ? ((count / number) + 1) : count / number);
		return dto;
	}

}
