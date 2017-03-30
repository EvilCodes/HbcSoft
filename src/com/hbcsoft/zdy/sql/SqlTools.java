package com.hbcsoft.zdy.sql;

import java.util.ArrayList;
import java.util.List;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.error.ErrorConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.form.entity.FormFields;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.util.PubTools;
import com.yaja.common.constant.YAJAConstant;
import com.yaja.common.entity.ColumnObject;
import com.yaja.common.util.YAJAUtil;

public class SqlTools extends LogBaseClass<SqlTools> {
	
	public String creatTableSql(final int dbType, final TableNameClass tname, 
			final List<TableEntity> lstTe) throws HbcsoftException {
		getLogger().info("=========creatTableSql======start==");

		checkDbType(dbType);

		if (tname.getTableName().trim().isEmpty())
		{
			throw new HbcsoftException(SqlTools.class.getName(), ErrorConstant.ERROR_CODE,
					ErrorConstant.SQLTOOLS_002);
		}

		final StringBuffer sbuffer = new StringBuffer(162);

		sbuffer.append("CREATE TABLE `").append(tname.getTableName()).append("` ("
				+ " `id` BIGINT(20) NOT NULL ,"
				+ " `zid` BIGINT(20) NOT NULL ,"
				+ " `COMPANYID` BIGINT(20) NOT NULL ,");

		if (lstTe == null || lstTe.isEmpty()) {
			throw new HbcsoftException(SqlTools.class.getName(), ErrorConstant.ERROR_CODE,
					ErrorConstant.SQLTOOLS_003);
		}

		for (int index = 0; index < lstTe.size(); index++) {
			sbuffer.append(getColumn(dbType, lstTe.get(index)));
		}
		
		sbuffer.append(" PRIMARY KEY (`id`)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");

		getLogger().info("=========creatTableSql======end==");
		return sbuffer.toString();
	}

	public String dropTableSql(final int dbType, final TableNameClass tname) throws HbcsoftException {
		getLogger().info("=========dropTableSql======start==");

		checkDbType(dbType);

		if (tname.getTableName().trim().isEmpty()){
			throw new HbcsoftException(SqlTools.class.getName(), ErrorConstant.ERROR_CODE,
					ErrorConstant.SQLTOOLS_002);
		}

		final StringBuffer sbuffer = new StringBuffer(162);
		sbuffer.append("DROP TABLE `" + tname.getTableName() + "` ;");

		getLogger().info("=========dropTableSql======end==");
		return sbuffer.toString();
	}

	public List<String> alterTableSql(final int dbType, final TableNameClass tname, 
			final List<TableEntity> lstTe) throws HbcsoftException {
		getLogger().info("=========alterTableSql======start==");
		checkDbType(dbType);

		if (tname.getTableName().trim().isEmpty()){
			throw new HbcsoftException(SqlTools.class.getName(), ErrorConstant.ERROR_CODE,
					ErrorConstant.SQLTOOLS_002);
		}

		if (lstTe == null || lstTe.isEmpty()) {
			throw new HbcsoftException(SqlTools.class.getName(), ErrorConstant.ERROR_CODE,
					ErrorConstant.SQLTOOLS_003);
		}
		
		final List<String> retV = new ArrayList<String>();

		for (int index = 0; index < lstTe.size(); index++) {
			final String tab = getColumnAlter(dbType, lstTe.get(index));
			
			if("".equals(tab))
			{
				continue;
			}
			
			retV.add("ALTER TABLE `" + tname.getTableName() + "` " + tab);
		}

		getLogger().info("=========alterTableSql======end==");
		return retV;
	}

	public String queryAllSql(final List<String> lsttn, final List<FormFields> lstff,
			final List<String> lstV,final String formType) throws HbcsoftException {
		getLogger().info("=========queryAllSql======start==");
		
		final StringBuffer sbuffer = new StringBuffer(32);
		final StringBuffer sbt = new StringBuffer(32);
		final StringBuffer sbw = new StringBuffer(32);
		
		sbuffer.append(" SELECT DISTINCT 1 ");

		sbt.append(" FROM " + lsttn.get(0));
		sbw.append(" WHERE 1=1 ");
		sbw.append(" AND FORMTYPE= '").append(formType).append("' ");
		
		for(int index = 1; index < lsttn.size(); index++)
		{
			sbt.append(YAJAConstant.CHARACTER_SQL_COMMA);
			sbt.append(lsttn.get(index));
			sbw.append(YAJAConstant.CHARACTER_SQL_AND + lsttn.get(index - 1) + ".ZID = "+ lsttn.get(index) + ".ZID");
		}
		
		lstff.forEach(ffield -> {
			sbuffer.append(getColumns(ffield));
			sbw.append(getConditions(ffield, lstV));
		});

		sbuffer.append(sbt).append(sbw);
		
		getLogger().info("=========queryAllSql======end==");
		return sbuffer.toString();
	}

	public String querySql(final String tn) throws HbcsoftException {
		getLogger().info("=========querySql======start==");
		return " SELECT * FROM " + tn + " WHERE ZID = ? ";
	}

	public String insertSql(final String tname, final List<FormFields> lstff, 
			final List<Object> lstV) throws HbcsoftException {
		getLogger().info("=========insertSql======start==");
		
		final StringBuffer sbuffer = new StringBuffer(55);

		sbuffer.append("INSERT INTO ").append(tname);
		sbuffer.append(" (ZID, COMPANYID, ID ");
		
		getColumns(lstff, tname, lstV, "ZID");
		getColumns(lstff, tname, lstV, "COMPANYID");
		getColumns(lstff, tname, lstV, "ID");
		
		lstff.forEach(ffield -> {
			if(tname.equals(ffield.getTableName()))
				sbuffer.append(getColumns(ffield, lstV));
		});
		
		sbuffer.append(" )  VALUES(  ? ");
		
		for(int index = 1; index < lstV.size(); index++)
		{
			sbuffer.append(" ,? ");
		}
		
		sbuffer.append(" ) ");
		
		getLogger().info("=========insertSql======end==");
		return sbuffer.toString();
	}

	public String deleteSql(final String tname) throws HbcsoftException {
		getLogger().info("=========deleteSql======start==");
		final StringBuffer sbuffer = new StringBuffer(26);
		
		sbuffer.append("DELETE FROM ");
		sbuffer.append(tname);
		sbuffer.append(" WHERE ZID = ?");

		getLogger().info("=========deleteSql======end==");
		return sbuffer.toString();
	}

	public String updateSql(final String tname, final List<FormFields> lstff, final List<Object> lstV) throws HbcsoftException {
		getLogger().info("=========updateSql======start==");
		final StringBuffer sbuffer = new StringBuffer(33);

		sbuffer.append(" UPDATE ");
		sbuffer.append(tname);
		sbuffer.append(" SET ID = ?");
		
		getColumns(lstff, tname, lstV, "ID");
		
		lstff.forEach(ffield -> {
			sbuffer.append(getColumns(ffield, tname, lstV, "ID"));
		});
		
		sbuffer.append(" WHERE ID = ? ");
		
		lstV.add(lstV.get(0));
		getLogger().info("=========updateSql======end==");
		return sbuffer.toString();
	}
	
	private void checkDbType(final int dbType) throws HbcsoftException {
		getLogger().info("=========checkDbType======start==");

		switch (dbType) {
		case HBCSoftConstant.DBTYPE_MYSQL:
		case HBCSoftConstant.DBTYPE_SQLSERVER:
		case HBCSoftConstant.DBTYPE_ORACLE:
			break;
		default:
			throw new HbcsoftException(SqlTools.class.getName(), ErrorConstant.ERROR_CODE,
					ErrorConstant.SQLTOOLS_001);
		}

		getLogger().info("=========checkDbType======end==");
	}

	private String getColumnAlter(final int dbType, final TableEntity ten) throws HbcsoftException {
		getLogger().info("=========getColumnAlter======start==");

		if (ten.getName() == null || ten.getName().isEmpty()){
			throw new HbcsoftException(SqlTools.class.getName(), ErrorConstant.ERROR_CODE,
					ErrorConstant.SQLTOOLS_004);
		}
		
		if(ten.getChangeFlag() != 0 && ten.getChangeFlag() != 1)
		{
			return "";
		}

		final StringBuffer sbf = new StringBuffer(46);

		final ColumnObject co = getColumnObject(dbType, ten);
		
		sbf.append(getColumnAlter(ten));

		chkColumnObject(co, ten);

		sbf.append(co.getName());

		sbf.append(getColumnNumber(co, ten));

		if (ten.getIsNull() == 1) {
			sbf.append(" NOT NULL ");
		}

		if (ten.getDefaultValue() != null && !ten.getDefaultValue().trim().isEmpty()) {
			sbf.append(" DEFAULT '" + ten.getDefaultValue() + "'");
		}

		sbf.append(" ; ");

		getLogger().info("=========getColumnAlter======end==");

		return sbf.toString();
	}

	private String getColumn(final int dbType, final TableEntity ten) throws HbcsoftException {
		getLogger().info("=========getColumn======start==");

		if (ten.getName() == null || ten.getName().isEmpty())
			throw new HbcsoftException(SqlTools.class.getName(), ErrorConstant.ERROR_CODE,
					ErrorConstant.SQLTOOLS_004);

		final StringBuffer sbuffer = new StringBuffer(29);

		final ColumnObject coj = getColumnObject(dbType, ten);
		
		sbuffer.append(" `" + ten.getName() + "` ");

		chkColumnObject(coj, ten);

		sbuffer.append(coj.getName());

		sbuffer.append(getColumnNumber(coj, ten));

		if (ten.getIsNull() == 1) {
			sbuffer.append(" NOT NULL ");
		}

		if (ten.getDefaultValue() != null && !ten.getDefaultValue().trim().isEmpty()) {
			sbuffer.append(" DEFAULT '" + ten.getDefaultValue() + "'");
		}

		sbuffer.append(YAJAConstant.CHARACTER_SQL_COMMA);

		getLogger().info("=========getColumn======end==");

		return sbuffer.toString();
	}
	
	private String getColumns(final FormFields ff, final String tn, final List<Object> cv, final String fieldName)
	{
		if(tn.equals(ff.getTableName()) && !fieldName.equals(ff.getFieldName().toUpperCase()))
		{
			cv.add(ff.getInputValue());
			return YAJAConstant.CHARACTER_SQL_COMMA + ff.getFieldName() + " = ?";
		}
		
		return "";
	}
	
	private String getColumns(final List<FormFields> lstff, final String tn, final List<Object> cv, final String fieldName)
	{
		lstff.forEach(ff -> {
			if(tn.equals(ff.getTableName()) && fieldName.equals(ff.getFieldName().toUpperCase()))
			{
				cv.add(ff.getInputValue());
			}
		});
		
		return "";
	}
	
	private String getColumns(final FormFields ff, final List<Object> cv)
	{
		if("ZID".equals(ff.getFieldName().toUpperCase())
				||
				"COMPANYID".equals(ff.getFieldName().toUpperCase())
				||
				"ID".equals(ff.getFieldName().toUpperCase()))
		{
			return "";
		}
		
		cv.add(ff.getInputValue());
		
		return YAJAConstant.CHARACTER_SQL_COMMA + ff.getFieldName();
	}
	
	private String getColumns(final FormFields ff)
	{
		if(ff.getQueryisColumn() == 1)
		{
			return YAJAConstant.CHARACTER_SQL_COMMA + ff.getTableName() + "." + ff.getFieldName();
		}
		
		return "";
	}
	
	private String getConditions(final FormFields ff, final List<String> cv)
	{
		String returnV = "";
		String printV = "";
		
		if(ff.getQueryisConditions() == 1)
		{
			// 类型：0：输入框 1：下拉框 2：按钮 3：多选框 4：点选 5:日期 6：文本域 7：金额
			switch(ff.getInputType())
			{
			case HBCSoftConstant.FORM_INPUT_TYPE_0:
			case HBCSoftConstant.FORM_INPUT_TYPE_4:
			case HBCSoftConstant.FORM_INPUT_TYPE_6:

				if(PubTools.isEmpty(ff.getInputValue()))
				{
					break;
				}
				
				returnV = YAJAConstant.CHARACTER_SQL_AND + ff.getTableName() + "." + ff.getFieldName() + " like ? ";
				printV = YAJAConstant.CHARACTER_SQL_AND + ff.getTableName() + "." + ff.getFieldName() + " like '%" + ff.getInputValue() + "%' ";
				
				cv.add("%" + ff.getInputValue() + "%");
				break;
			case HBCSoftConstant.FORM_INPUT_TYPE_2:
				break;
			case HBCSoftConstant.FORM_INPUT_TYPE_3:
			case HBCSoftConstant.FORM_INPUT_TYPE_1:

				if(PubTools.isEmpty(ff.getInputValue()))
				{
					break;
				}
				
				final String[] tempA = ff.getInputValue().split(",");
				final StringBuffer tempV = new StringBuffer(32);
				final StringBuffer temp = new StringBuffer(32);
				for(int index = 0; index < tempA.length; index++)
				{
					if(index > 0)
					{
						temp.append(YAJAConstant.CHARACTER_SQL_COMMA);
						tempV.append(YAJAConstant.CHARACTER_SQL_COMMA);
					}
					temp.append(" '" + tempA[index] + "' ");
					tempV.append(" ? ");
					cv.add(tempA[index]);
				}
				
				if(!YAJAUtil.isEmpty(temp.toString()))
				{
					returnV = YAJAConstant.CHARACTER_SQL_AND + ff.getTableName() + "." + ff.getFieldName() + " in (" + tempV.toString() + ") ";
					printV = YAJAConstant.CHARACTER_SQL_AND + ff.getTableName() + "." + ff.getFieldName() + " in (" + temp.toString() + ") ";
				}
				break;
			case HBCSoftConstant.FORM_INPUT_TYPE_5:
			case HBCSoftConstant.FORM_INPUT_TYPE_7:

				final boolean bstart = PubTools.isEmpty(ff.getStartValue());
				final boolean bend = PubTools.isEmpty(ff.getEndValue());
				
				if( bstart && bend)
				{
					break;
				}else if(!bstart && bend)
				{
					returnV = YAJAConstant.CHARACTER_SQL_AND + ff.getTableName() + "." + ff.getFieldName() 
						+ " >= ?";
					printV = YAJAConstant.CHARACTER_SQL_AND + ff.getTableName() + "." + ff.getFieldName() 
						+ " >= '" + ff.getStartValue() + "'";
					
					cv.add(ff.getStartValue());
				}else if(bstart && !bend)
				{
					returnV = YAJAConstant.CHARACTER_SQL_AND + ff.getTableName() + "." + ff.getFieldName() 
						+ " <= ?";
					printV = YAJAConstant.CHARACTER_SQL_AND + ff.getTableName() + "." + ff.getFieldName() 
						+ " <= '" + ff.getEndValue() + "'";
					
					cv.add(ff.getEndValue());
				}else{
					returnV = YAJAConstant.CHARACTER_SQL_AND + ff.getTableName() + "." + ff.getFieldName() 
							+ " BETWEEN ? AND ? ";
					printV = YAJAConstant.CHARACTER_SQL_AND + ff.getTableName() + "." + ff.getFieldName() 
							+ " BETWEEN '" + ff.getStartValue() + "' AND '" + ff.getEndValue() + "'";
					
					cv.add(ff.getStartValue());
					cv.add(ff.getEndValue());
				}
				break;
			default:
				break;
			}
		}
		getLogger().info(printV);
		return returnV;
	}
	
	private ColumnObject getColumnObject(final int dbType, final TableEntity ten)
	{
		switch (dbType) {
		case HBCSoftConstant.DBTYPE_MYSQL:
			return HBCSoftConstant.COKUMN_M_M.get(ten.getType().toString());
		case HBCSoftConstant.DBTYPE_SQLSERVER:
			return HBCSoftConstant.COKUMN_M_S.get(ten.getType().toString());
		case HBCSoftConstant.DBTYPE_ORACLE:
			return HBCSoftConstant.COKUMN_M_O.get(ten.getType().toString());
		default:
			return null;
		}
	}
	
	private String getColumnAlter(final TableEntity ten)
	{
		final StringBuffer sbf = new StringBuffer(32);
		
		if (ten.getChangeFlag() == 0) {
			// 新增
			sbf.append("ADD  ");
		}else if (ten.getChangeFlag() == 1){
			// 修改
			sbf.append("MODIFY COLUMN ");
		}

		sbf.append(" `" + ten.getName() + "` ");
		
		return sbf.toString();
	}
	
	private String getColumnNumber(final ColumnObject coj, final TableEntity ten)
	{
		final StringBuffer sbuffer = new StringBuffer(32);
		
		switch (coj.getType()) {
		case 1:
			sbuffer.append("(" + ten.getNumber() + ") ");
			break;
		case 2:
			sbuffer.append("(" + ten.getNumber() + "," + ten.getDecimalDigits() + ") ");
			break;
		default:
			break;
		}
		
		return sbuffer.toString();
	}
	
	private void chkColumnObject(final ColumnObject co, final TableEntity ten) throws HbcsoftException
	{
		if (co == null){
			throw new HbcsoftException(SqlTools.class.getName(), ErrorConstant.ERROR_CODE,
					YAJAUtil.formateString(ErrorConstant.SQLTOOLS_005, ten.getType().toString()));
		}

		if (co.getName() == null || co.getName().trim().isEmpty())
		{
			throw new HbcsoftException(SqlTools.class.getName(), ErrorConstant.ERROR_CODE,
					ErrorConstant.SQLTOOLS_006);
		}
	}
}
