package com.hbcsoft.zdy.template.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.form.entity.FormFields;
import com.hbcsoft.zdy.common.HbcsoftJT;
import com.hbcsoft.zdy.common.LogBaseClass;

@Repository
public class TemplateDaoImp extends LogBaseClass<TemplateDaoImp> implements TemplateDao {

	@Override
	public List<List<FormFields>> queryAll(final List<FormFields> lstFF, final HbcsoftJT jt, final String sql, final Object... args) {
		final List<List<FormFields>> reV = new ArrayList<List<FormFields>>();
		List<Map<String, Object>> result;
		
		result = jt.queryForList(sql, args);
		
		if(result == null)
		{
			return reV;
		}
		
		result.forEach(map -> {
			final List<FormFields> row = new ArrayList<FormFields>();
			lstFF.forEach(ff -> {
				if(ff.getQueryisColumn() == 1){
					final FormFields temp = ff.copy();
					if(map.containsKey(temp.getFieldName()))
						temp.setInputValue(String.valueOf(map.get(temp.getFieldName())));
					
					row.add(temp);
				}
			});
			
			reV.add(row);
		});
		
		return reV;
	}

	@Override
	public void query(final List<FormFields> lstFF, final HbcsoftJT jt, final String sql, final Object... args) throws HbcsoftException {
		List<Map<String, Object>> result;
		
		result = jt.queryForList(sql, args);
		
		if(result == null)
		{
			throw new HbcsoftException(this.getClass().getName(), 101, "没有记录");
		}
		
		if(result.size() > 1)
		{
			throw new HbcsoftException(this.getClass().getName(), 101, "多条记录");
		}
		
		result.forEach(map -> {
			lstFF.forEach(ff -> {
				if(map.containsKey(ff.getFieldName()))
					ff.setInputValue(String.valueOf(map.get(ff.getFieldName())));
			});
		});
	}
}
