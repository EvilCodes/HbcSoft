package com.hbcsoft.zdy.template.dao;

import java.util.List;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.form.entity.FormFields;
import com.hbcsoft.zdy.common.HbcsoftJT;

public interface TemplateDao {
	List<List<FormFields>> queryAll(List<FormFields> lstFF, HbcsoftJT jt, String sql, Object... args);
	void query(final List<FormFields> lstC, final HbcsoftJT jt, final String sql, final Object... args) throws HbcsoftException;
}
