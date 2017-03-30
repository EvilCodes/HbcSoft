package com.hbcsoft.exception;

import com.hbcsoft.zdy.common.ComException;

/**
 * 异常
 * @author zhangdengyu
 *
 */
public class HbcsoftException extends ComException {

	private static final long serialVersionUID = 1L;

	public HbcsoftException(final String theErrorCatalog, final int theErrorNo,
			final String theErrorMessage) {
		super();
		this.errorAppend = theErrorCatalog;
		this.errorNo = theErrorNo;
		this.errorMessage = theErrorMessage;
	}

	public HbcsoftException(final String theErrorCatalog, final int theErrorNo,
			final String theErrorModule, final String theErrorMessage, final String theErrorAppend) {
		super();
		this.errorAppend = theErrorCatalog;
		this.errorNo = theErrorNo;
		this.errorModule = theErrorModule;
		this.errorMessage = theErrorMessage;
		this.errorAppend = theErrorAppend;
	}

	public HbcsoftException(final String theErrorCatalog, final int theErrorNo,
			final String theErrorModule, final String theErrorMessage) {
		super();
		this.errorAppend = theErrorCatalog;
		this.errorNo = theErrorNo;
		this.errorModule = theErrorModule;
		this.errorMessage = theErrorMessage;
	}
	public HbcsoftException(final String theErrorCatalog, final int theErrorNo,
			final Exception exc) {
		super();
		this.errorAppend = theErrorCatalog;
		this.errorNo = theErrorNo;
		this.errorMessage = exc.getMessage();
	}

	public void formart(final int errorNo, final String errorMessage, final String errorModule, final String errorAppend) {
		this.errorAppend = errorAppend;
		this.errorNo = errorNo;
		this.errorModule = errorModule;
		this.errorMessage = errorMessage;
	}
}

