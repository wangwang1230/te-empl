package com.empl.mgr.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Logable implements java.io.Serializable {

	private static final long serialVersionUID = 5886331828984997039L;

	private final Logger log = LoggerFactory.getLogger(getClass());

	public void info(String str, Object... params) {
		log.info(str, params);
	}

	public void info(StringBuilder sb) {
		log.info(sb.toString());
	}

	public void warn(String str, Object... params) {
		log.warn(str, params);
	}

	public void warn(StringBuilder sb) {
		log.warn(sb.toString());
	}

	public void debug(String text, Object... params) {
		log.info(text, params);
	}

	public void debug(StringBuilder sb) {
		log.debug(sb.toString());
	}

	public void error(StringBuilder sb) {
		log.error(sb.toString());
	}

	public void error(String text, Object... params) {
		log.error(text, params);
	}
}
