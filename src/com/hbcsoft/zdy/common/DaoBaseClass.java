package com.hbcsoft.zdy.common;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.zdy.inter.DaoClass;
import com.hbcsoft.zdy.util.PubTools;
import com.yaja.entity.sql.ref.ClassRefTable;
import com.yaja.validator.util.ValidatorUtil;

@SuppressWarnings("rawtypes")
public class DaoBaseClass<T> extends LogBaseClass<DaoBaseClass> implements DaoClass<T> {

	private final static String UNCHECKED = "unchecked";
	private transient final Class<T> entityClass;

	@SuppressWarnings(UNCHECKED)
	public DaoBaseClass() {
		super();
		final Type genType = getClass().getGenericSuperclass();
		final Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<T>) params[0];
	}

	@Override
	public T query(final String sql, final HbcsoftJT jt, final Object... param) throws InstantiationException, IllegalAccessException {
		getLogger().info("=========query======start==");
		
		final T tc = entityClass.newInstance();
		jt.query(sql, param, (RowCallbackHandler) tc);
		getLogger().info("=========query======end==");
		return tc;
	}

	@SuppressWarnings(UNCHECKED)
	public List<T> queryAll(final String sql, final HbcsoftJT jt)
			throws InstantiationException, IllegalAccessException {
		getLogger().info("=========queryAll======start==");
		final T tc = entityClass.newInstance();
		getLogger().info("=========queryAll======end==");
		return jt.queryZ(sql, 0, 999999, (RowMapper)tc);
	}

	@SuppressWarnings(UNCHECKED)
	@Override
	public List<T> queryAll(final String sql, final HbcsoftJT jt, final Object... param)
			throws InstantiationException, IllegalAccessException {
		getLogger().info("=========queryAll======start===");
		final T tc = entityClass.newInstance();
		getLogger().info("=========queryAll======end===");
		return jt.queryZ(sql, 0, 999999, (RowMapper)tc, param);
	}

	@SuppressWarnings(UNCHECKED)
	@Override
	public List<T> queryAll(final String sql, final HbcsoftJT jt, final int startRow, final int rowsCount)
			throws InstantiationException, IllegalAccessException {
		getLogger().info("=========queryAll======start====");
		final T tc = entityClass.newInstance();
		getLogger().info("=========queryAll======end====");
		return jt.queryZ(sql, startRow, rowsCount, (RowMapper)tc);
	}

	@SuppressWarnings(UNCHECKED)
	@Override
	public List<T> queryAll(final String sql, final HbcsoftJT jt, final String startRow, final String rowsCount)
			throws InstantiationException, IllegalAccessException {
		getLogger().info("=========queryAll======start=====");
		final T tc = entityClass.newInstance();
		getLogger().info("=========queryAll======end=====");
		return jt.queryZ(sql, Integer.valueOf(startRow), Integer.valueOf(rowsCount), (RowMapper)tc);
	}

	@SuppressWarnings(UNCHECKED)
	@Override
	public List<T> queryAll(final String sql, final HbcsoftJT jt, final int startRow, final int rowsCount, final Object... param)
			throws InstantiationException, IllegalAccessException {
		getLogger().info("=========queryAll======start======");
		final T tc = entityClass.newInstance();
		getLogger().info("=========queryAll======end======");
		return jt.queryZ(sql, startRow, rowsCount, (RowMapper)tc, param);
	}

	@Override
	public int save(final T tc, final HbcsoftJT jt) throws HbcsoftException {
		getLogger().info("=========save======start==");
		
		final ValidatorUtil<T> vu = new ValidatorUtil<T>();
		final String valiError = vu.validation(tc);
		
		if(!PubTools.isEmpty(valiError))
		{
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.ERROR_CODE, valiError);
		}
		
		final ClassRefTable crt = new ClassRefTable();
		final List<String> listV = new ArrayList<String>();

		try {
			final String sql = crt.getSaveSql(tc,listV);
			
			getLogger().info("=========save======end==");
			return jt.update(sql, listV.toArray());
		} catch (IllegalAccessException e) {
			getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		} catch (IllegalArgumentException e) {
			getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		} catch (InvocationTargetException e) {
			getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		} catch (IntrospectionException e) {
			getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
	}

	@Override
	public int update(final T tc, final HbcsoftJT jt) throws HbcsoftException
	{
		getLogger().info("=========update======start==");
		
		final ValidatorUtil<T> vu = new ValidatorUtil<T>();
		final String valiError = vu.validation(tc);
		
		if(!PubTools.isEmpty(valiError))
		{
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.ERROR_CODE, valiError);
		}
		
		final ClassRefTable crt = new ClassRefTable();
		final List<String> listV = new ArrayList<String>();

		try {
			final String sql = crt.getUpdateSql(tc, listV);
			getLogger().info("=========update======end==");
			return jt.update(sql, listV.toArray());
		} catch (IllegalAccessException e) {
			getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		} catch (IllegalArgumentException e) {
			getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		} catch (InvocationTargetException e) {
			getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		} catch (IntrospectionException e) {
			getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
	}

	@Override
	public int delete(final T tc, final HbcsoftJT jt) throws HbcsoftException
	{
		getLogger().info("=========delete======start==");
		
		final ClassRefTable crt = new ClassRefTable();

		try {
			final String sql = crt.getDeleteSql(tc);
			
			getLogger().info("=========delete======end==");
			return jt.update(sql);
		} catch (IllegalAccessException e) {
			getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		} catch (IllegalArgumentException e) {
			getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		} catch (InvocationTargetException e) {
			getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		} catch (IntrospectionException e) {
			getLogger().info(e);
			throw new HbcsoftException(this.getClass().getName(), ErrorConstant.EXCEPTION_CODE, e);
		}
	}

}
