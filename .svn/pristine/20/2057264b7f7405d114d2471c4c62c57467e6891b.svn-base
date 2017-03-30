package com.hbcsoft.zdy.inter;

import java.util.List;

import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.zdy.common.HbcsoftJT;

public interface DaoClass<T> {
	T query(String sql, HbcsoftJT jt, Object... param) throws InstantiationException, IllegalAccessException;
	List<T> queryAll(String sql, HbcsoftJT jt, int startRow, int rowsCount, Object... param) throws InstantiationException, IllegalAccessException;
	List<T> queryAll(String sql, HbcsoftJT jt, Object... param) throws InstantiationException, IllegalAccessException;
	List<T> queryAll(String sql, HbcsoftJT jt, String startRow, String rowsCount) throws InstantiationException, IllegalAccessException;
	List<T> queryAll(String sql, HbcsoftJT jt, int startRow, int rowsCount) throws InstantiationException, IllegalAccessException;
	int save(T tc, HbcsoftJT jt) throws HbcsoftException;
	int update(T tc, HbcsoftJT jt) throws HbcsoftException;
	int delete(T tc, HbcsoftJT jt) throws HbcsoftException;
}
