package com.empl.mgr.utils;

import java.io.Serializable;
import java.util.UUID;

public class SupportUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String findUUID() {
		return UUID.randomUUID().toString();
	}

}
