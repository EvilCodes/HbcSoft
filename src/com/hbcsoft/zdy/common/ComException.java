package com.hbcsoft.zdy.common;

import com.yaja.exception.YAJAException;

public class ComException extends YAJAException {
	private static final long serialVersionUID = 1066827938522422740L;
	
	public ComException(final String theErrorCatalog, final int theErrorNo,
			final String theErrorMessage) {
		super();
		this.errorAppend = theErrorCatalog;
		this.errorNo = theErrorNo;
		this.errorMessage = theErrorMessage;
	}

	public ComException(final String theErrorCatalog, final int theErrorNo,
			final String theErrorModule, final String theErrorMessage, final String theErrorAppend) {
		super();
		this.errorAppend = theErrorCatalog;
		this.errorNo = theErrorNo;
		this.errorModule = theErrorModule;
		this.errorMessage = theErrorMessage;
		this.errorAppend = theErrorAppend;
	}

	public ComException(final String theErrorCatalog, final int theErrorNo,
			final String theErrorModule, final String theErrorMessage) {
		super();
		this.errorAppend = theErrorCatalog;
		this.errorNo = theErrorNo;
		this.errorModule = theErrorModule;
		this.errorMessage = theErrorMessage;
	}
	public ComException(final String theErrorCatalog, final int theErrorNo,
			final Exception exc) {
		super();
		this.errorAppend = theErrorCatalog;
		this.errorNo = theErrorNo;
		this.errorMessage = exc.getMessage();
	}

	public ComException() {
	}
}
