package com.hbcsoft.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.User;
import com.hbcsoft.table.entity.TableEntity;
import com.hbcsoft.table.entity.TableNameClass;
import com.hbcsoft.zdy.common.LogBaseClass;
import com.hbcsoft.zdy.sql.SqlTools;

/**
 * 创建连接数据库
 * @author zhanghaijiao
 *
 */
@Controller //负责注册一个bean 到spring 上下文中
public class BaseConfigur extends LogBaseClass<BaseConfigur>  {

	/**
	 * User实体
	 */
	private User user;
	
	/**
	 *引用SqlTools实体
	 */
	private transient final SqlTools sqlTools= new SqlTools();
	
	/**
	 * 创建表 -- 表名表
	 */
	private transient String tableNameSql;
	
	/**
	 * 创建表 -- 字段表
	 */
	private transient String tableEntitySql;
	
	/**
	 * 创建表 -- 表单表
	 */
	private transient String formNameSql;
	
	/**
	 * 创建表 -- 表单字段表
	 */
	private transient String formFieldsSql;
	
	/**
	 * 创建表--表单中间表
	 */
	private transient String formTableSql;
	
	/**
	 * 创建表--人员表
	 */
	private transient String userSql;
	
	/**
	 * 创建表--工作流配置表
	 */
	private transient String wfConfigSql;
	
	/**
	 * 创建表--工作流定义表
	 */
	private transient String wfDefineSql;
	
	/**
	 * 创建表--工作流节点表
	 */
	private transient String wfNodeSql;
	
	/**
	 * 创建表--工作流节点规则表
	 */
	private transient String wfNodeRuleSql;
	
	/**
	 * 创建表--工作流节点规则子表
	 */
	private transient String wfNRDSql;
	
	/**
	 * 创建人ID
	 */
	private static final String CREATEID="CREATEID";
	
	/**
	 * 创建时间
	 */
	private static final String CREATETIME="CREATETIME";
	
	/**
	 * 修改人ID
	 */
	private static final String UPDATEID = "UPDATEID";
	
	/**
	 * 修改时间
	 */
	private static final String UPDATETIME = "UPDATETIME";
	
	/**
	 * 表名
	 */
	private static final String TABNAME = "TABLENAME";
	
	/**
	 * 表ID
	 */
	private static final String TABLEID = "TABLEID";
	
	/**
	 * 创建人姓名
	 */
	private static final String CREATENAME="CREATENAME";
	
	/**
	 * 修改人姓名
	 */
	private static final String UPDATENAME="UPDATENAME";
	
	/**
	 * 部门ID
	 */
	private static final String DEPTID="DEPTID";
	
	/**
	 * 日志名称
	 */
	private static final String LOGNAME="表已经创建成功！";
	
	/**
	 * 路径
	 */
	private static final String DIRECTORY = "..\\..\\src\\develop\\hbcsoft\\src\\com\\hbcsoft\\properties\\";
	
	/**
	 * mysql
	 */
	private static final String MYSQL = "mysql";
	
	/**
	 * SqlServer
	 */
	private static final String SQLSERVER = "sqlserver";
	
	/**
	 * 成功
	 */
	private static final String SUCCES = "success";
	
	/**
	 * 为控制器指定可以处理哪些 URL 请求,此处处理的是login模块下的login请求
	 * @return
	 */
	@RequestMapping(value = "/baseConfigur")
	public String baseConfigur() {
		return "jdbc/baseConfigur";
	}
	
	/**
	 * 登录验证
	 * @param request
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/baseConfigurSubmit")
	public String baseConfigurSubmit(final HttpServletRequest request) throws HbcsoftException{
		this.getLogger().info("=======baseConfigurSubmit=====start===");
		String status="";
		//获取页面输入信息
		final String driverClassName = request.getParameter("driverClassName");
		final String url = request.getParameter("url");
		final String username = request.getParameter("username");
		final String password = request.getParameter("password");
		final String initialSize = request.getParameter("initialSize");
		final String maxActive = request.getParameter("maxActive");
		final String maxIdle = request.getParameter("maxIdle");
		final String minIdle = request.getParameter("minIdle");
		try{
			//组装文件内容
			final String data = "driverClassName="+driverClassName+"\rurl="+url+"\rdbusername="+username+"\rdbpassword="+password
					+"\rinitialSize="+initialSize+"\rmaxActive="+maxActive+"\rmaxIdle="+maxIdle+"\rminIdle="+minIdle;
			final File file =new File(DIRECTORY,"hbcsoft_jdbc.properties");
			//判断目录或文件是否存在
			if(flagFile(file)){
				file.createNewFile();
			}else {
				//判断是否为文件
				if(fFile(file)){//为文件时调用删除文件方法
					deleteFile(DIRECTORY+"hbcsoft_jdbc.properties");
				}else{//为目录时调用删除目录方法
					deleteDirectory(DIRECTORY+"hbcsoft_jdbc.properties");
				}
			}
			final FileWriter fileWritter = new FileWriter(file,true);
			final BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(data);
			bufferWritter.close();
			//连接并创建数据库及系统表
			status = getConnMySQL(driverClassName, url, username, password);
		}catch(IOException e){
			this.getLogger().info(e);
		}
		this.getLogger().info("=======baseConfigurSubmit=====end===");
		if(statusJDBC(status)){
			status = "jdbc/success";
		}else{
			status = "jdbc/failure";
		}
		return status;
	}
	
	/**
	 * 判断状态是否为成功
	 * @param status
	 * @return
	 */
	public boolean statusJDBC(final String status){
		return status.equals(SUCCES) ? true : false;
	}
	
	/**
	 * 判断目录或文件是否存在
	 * @param file
	 * @return
	 */
	public boolean flagFile(final File file){
		return file.exists() ? false : true;
	}
	
	/**
	 * 判断是否为文件
	 * @param file
	 * @return
	 */
	public boolean fFile(final File file){
		return file.isFile() ? true : false;
		
	}
	
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public boolean deleteDirectory(String sPath) {  
		this.getLogger().info("=======deleteDirectory=====start===");
		boolean flag = true;  
		boolean isreturn;
		//如果sPath不以文件分隔符结尾，自动添加文件分隔符  
		if (!sPath.endsWith(File.separator)) {  
			sPath = sPath + File.separator;  
		}  
		final File dirFile = new File(sPath);  
		//如果dir对应的文件不存在，或者不是一个目录，则退出  
		if(dirFileExis(dirFile)){  
			isreturn = false;  
		}else if(dirFileDir(dirFile)){
			isreturn = false;
		}else{
			//删除文件夹下的所有文件(包括子目录)  
			final File[] files = dirFile.listFiles();  
			for (int i = 0; i < files.length; i++) {  
				//删除子文件  
				final File iffile = files[i];
				if (isFile(iffile)) {
					flag = deleteFile(files[i].getAbsolutePath());  
					if (!flag) break;  
				} //删除子目录  
				else {  
					flag = deleteDirectory(files[i].getAbsolutePath());  
					if (!flag) break;  
				}  
			}
		}
		if (!flag) isreturn=false;  
		//删除当前目录  
		if (dirFileDel(dirFile)) {  
			isreturn=true;  
		} else {  
			isreturn=false;  
		}
		this.getLogger().info("=======deleteDirectory=====end===");
		return isreturn;
	}  

	/**
	 * 判断是否有子文件
	 * @return
	 */
	public boolean isFile(final File file){
		return file.isFile() ? true : false;
	}
	/**
	 * 判断是否为的文件
	 * @param dirFile
	 * @return
	 */
	public boolean dirFileExis(final File dirFile){
		return dirFile.exists() ? false : true;
	}
	
	/**
	 * 判断是否为路径
	 * @param dirFile
	 * @return
	 */
	public boolean dirFileDir(final File dirFile){
		return dirFile.isDirectory() ? false : true;
	}
	
	/**
	 * 判断删除当前目录
	 * @param dirFile
	 * @return
	 */
	public boolean dirFileDel(final File dirFile){
		return dirFile.delete() ? true : false;
	}
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public boolean deleteFile(final String sPath) {  
		this.getLogger().info("=======deleteFile=====start===");
		boolean flag = false;  
		final File file = new File(sPath);
		// 路径为文件且不为空则进行删除  
		if (file.isFile() && file.exists()) {
			file.delete();  
			flag = true;  
		}
		this.getLogger().info("=======deleteFile=====end===");
		return flag;  
	} 
	
	/**
	 * 获取User
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 赋值User 
	 * @param user
	 */
	public void setUser(final User user) {
		this.user = user;
	}
	
	/**
	 * 获取连接创建数据库
	 */
	public String getConnMySQL(final String driverClassName,final String url,final String username,final String password) throws HbcsoftException {
		this.getLogger().info("=======getConnMySQL=====start===");
		final String databaseType=url.substring(url.indexOf(':')+1, url.indexOf("://"));//数据库类型
		String databaseName="";//数据库名称
		String newUrl="";
		final String portNum= url.substring(url.lastIndexOf(':')+1, url.lastIndexOf(':')+5);
		final String localhost=url.substring(url.indexOf("//")+2, url.lastIndexOf(':'));
		if(baseMySQL(databaseType)){
			newUrl = "jdbc:mysql://"+localhost+":"+portNum+"/test";
			databaseName = url.substring(url.lastIndexOf('/')+1, url.length());
		}else if(baseSqlServer(databaseType)){
			newUrl = "jdbc:sqlserver://"+localhost+":"+portNum+";databaseName=master";//sa身份连接
			databaseName = url.substring(url.lastIndexOf('=')+1, url.length());
		}
		Connection conn = null;
		Connection newConn = null;
		Statement stmt = null; 
		final ResultSet rs = null;
		try {
			Class.forName(driverClassName);//加载驱动
			final String databaseSql = "create database " + databaseName;//创建数据库sql
			if(baseMySQL(databaseType)){
				//创建系统表
				mysqlDataBase();
			}else if(baseSqlServer(databaseType)){
				//TODO 填写组装SQLServer创建表sql
			}
			conn = DriverManager.getConnection(newUrl, username, password);
			stmt = conn.createStatement();
			if (conn != null) {
				this.getLogger().info("数据库连接成功!");
				stmt.executeUpdate(databaseSql);
				newConn = DriverManager.getConnection(url,username, password);
				if (newConn != null) {
					this.getLogger().info("已经连接到新创建的数据库：");
					final Statement newSmt = newConn.createStatement();
					final int tn = newSmt.executeUpdate(tableNameSql);//DDL语句返回值为0;
					final int te = newSmt.executeUpdate(tableEntitySql);//DDL语句返回值为0;
					final int ft = newSmt.executeUpdate(formNameSql);//DDL语句返回值为0;
					final int ff = newSmt.executeUpdate(formFieldsSql);//DDL语句返回值为0;
					final int fm = newSmt.executeUpdate(formTableSql);//DDL语句返回值为0;
					final int user = newSmt.executeUpdate(userSql);//DDL语句返回值为0;
					final int wfc = newSmt.executeUpdate(wfConfigSql);//DDL语句返回值为0;
					final int wfd = newSmt.executeUpdate(wfDefineSql);//DDL语句返回值为0;
					final int wfn = newSmt.executeUpdate(wfNodeSql);//DDL语句返回值为0;
					final int wfnr = newSmt.executeUpdate(wfNodeRuleSql);//DDL语句返回值为0;
					final int wfnrd = newSmt.executeUpdate(wfNRDSql);//DDL语句返回值为0;
					if (tn == 0) {
						this.getLogger().info(tableNameSql + LOGNAME);
					}
					if (te == 0) {
						this.getLogger().info(tableEntitySql + LOGNAME);
					}
					if (ft == 0) {
						this.getLogger().info(formNameSql + LOGNAME);
					}
					if (ff == 0) {
						this.getLogger().info(formFieldsSql + LOGNAME);
					}
					if (fm == 0){
						this.getLogger().info(formTableSql + LOGNAME);
					}
					if (user == 0){
						this.getLogger().info(userSql + LOGNAME);
					}
					if (wfc == 0){
						this.getLogger().info(wfConfigSql + LOGNAME);
					}
					if (wfd == 0){
						this.getLogger().info(wfDefineSql + LOGNAME);
					}
					if (wfn == 0){
						this.getLogger().info(wfNodeSql + LOGNAME);
					}
					if (wfnr == 0){
						this.getLogger().info(wfNodeRuleSql + LOGNAME);
					}
					if (wfnrd == 0){
						this.getLogger().info(wfNRDSql + LOGNAME);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			this.getLogger().info(e);
		} catch (SQLException e) {
			this.getLogger().info(e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					this.getLogger().info(e);
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					this.getLogger().info(e);
				}
			if (newConn != null)
				try {
					newConn.close();
				} catch (SQLException e) {
					this.getLogger().info(e);
				}
		}
		this.getLogger().info("=======getConnMySQL=====end===");
		return "success";
	}
	
	/**
	 * 判断连接数据库是否为mySql
	 * @param databaseType
	 * @return
	 */
	private boolean baseMySQL(final String databaseType){
		return databaseType.equals(MYSQL) ? true : false;
	}
	
	/**
	 * 判断连接数据库是否为SQLServer
	 * @param databaseType
	 * @return
	 */
	private boolean baseSqlServer(final String databaseType){
		return databaseType.equals(SQLSERVER) ? true : false;
	}
	
	/**
	 * 创建数据库成功后跳转页面
	 * @return
	 */
	@RequestMapping(value = "/success")
	public String success() {
		return "jdbc/success";
	}
	
	/**
	 * 创建数据库失败后跳转页面
	 * @return
	 */
	@RequestMapping(value = "/failure")
	public String failure() {
		return "jdbc/failure";
	}
	
	/**
	 * 创建mySql数据库中的系统表
	 * @throws HbcsoftException
	 */
	public void mysqlDataBase() throws HbcsoftException{
		final String[] tabEnt = {"T_TABLEENTITY","主表","",""};
		final TableNameClass tableEntitiy= tableName(tabEnt, 0,lstTabEntity());
		tableEntitySql = sqlTools.creatTableSql(0, tableEntitiy, lstTabEntity());
		final String[] tabNam = {"T_TABLENAME","主表","",""};
		final TableNameClass tableName= tableName(tabNam, 0,lstTabName());
		tableNameSql = sqlTools.creatTableSql(0, tableName, lstTabName());
		final String[] forNam = {"F_FORMNAME","主表","",""};
		final TableNameClass formName= tableName(forNam, 0,lstFormName());
		formNameSql = sqlTools.creatTableSql(0, formName, lstFormName());
		final String[] forFie = {"F_FORMFIELDS","主表","",""};
		final TableNameClass formFields= tableName(forFie, 0,lstFormFields());
		formFieldsSql = sqlTools.creatTableSql(0, formFields, lstFormFields());
		final String[] forTab = {"F_FORMTABLE","主表","",""};
		final TableNameClass formTable= tableName(forTab, 0,lstFormTable());
		formTableSql = sqlTools.creatTableSql(0, formTable, lstFormTable());
		final String[] sysUser = {"T_SYS_USER","主表","",""};
		final TableNameClass user= tableName(sysUser, 0,lstUser());
		userSql = sqlTools.creatTableSql(0, user, lstUser());
		final String[] fConfig = {"HBC_F_CONFIG","主表","",""};
		final TableNameClass wfConfig= tableName(fConfig, 0,lstWfConfig());
		wfConfigSql = sqlTools.creatTableSql(0, wfConfig, lstWfConfig());
		final String[] fDefine = {"HBC_F_DEFINE","主表","",""};
		final TableNameClass wfDefine= tableName(fDefine, 0,lstWfDefine());
		wfDefineSql = sqlTools.creatTableSql(0, wfDefine, lstWfDefine());
		final String[] hbcNode = {"HBC_F_NODE","主表","",""};
		final TableNameClass wfNode= tableName(hbcNode, 0,lstWfNode());
		wfNodeSql = sqlTools.creatTableSql(0, wfNode, lstWfNode());
		final String[] hbcNR = {"HBC_F_NODERULE","主表","",""};
		final TableNameClass wfNodeRule= tableName(hbcNR, 0,lstWfNodeRule());
		wfNodeRuleSql = sqlTools.creatTableSql(0, wfNodeRule, lstWfNode());
		final String[] hbcNRD = {"HBC_F_NODERULE_D","主表","",""};
		final TableNameClass wfNRD= tableName(hbcNRD, 0,lstWfNRD());
		wfNRDSql = sqlTools.creatTableSql(0, wfNRD, lstWfNRD());
		
	}
	
	/**
	 * 创建表信息
	 * @return
	 */
	public TableNameClass tableName(final String[] tabValue,final long Id,final List<TableEntity> lstTabEnt){
		final TableNameClass tableName = new TableNameClass();
		final String tabName = tabValue[0];
		final String isMainTab = tabValue[1];
		final String mainNam = tabValue[2];
		final String memo = tabValue[3];
		tableName.setTableName(tabName);
		tableName.setIsMainTable(isMainTab);
		tableName.setId(Id);
		tableName.setMainName(mainNam);
		tableName.setMemo(memo);
		tableName.setTableEntityList(lstTabEnt);
		return tableName;
	}
	
	/**
	 * 创建TableEntity表中的字段信息
	 * @return
	 */
	public List<TableEntity> lstTabEntity(){
		List<TableEntity> listTabEntity;
		listTabEntity = new ArrayList<TableEntity>();
		TableEntity te = new TableEntity();
		te.setName(CREATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName(CREATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName(UPDATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName(UPDATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName("NAME");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(1);
		te.setDefaultValue("");
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName("TITLE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(1);
		te.setDefaultValue("");
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName("TYPE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(1);
		te.setDefaultValue("");
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName("NUMBER");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName("DECIMALDIGITS");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName("ISDISPLAY");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(1);
		te.setDefaultValue("0");
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName("ISEDIT");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(1);
		te.setDefaultValue("0");
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName("ISNULL");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(1);
		te.setDefaultValue("0");
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName("DEFAULTVALUE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(500);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName("FLAG");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(1);
		te.setDefaultValue("0");
		listTabEntity.add(te);
		te = new TableEntity();
		te.setName("MAINID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(1);
		te.setDefaultValue("");
		listTabEntity.add(te);
		return listTabEntity;
	}
	
	/**
	 * 创建TableName表中字段信息
	 * @return
	 */
	public List<TableEntity> lstTabName(){
		List<TableEntity> listTabName;
		listTabName = new ArrayList<TableEntity>();
		TableEntity te = new TableEntity();
		te.setName(CREATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabName.add(te);
		te = new TableEntity();
		te.setName(CREATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabName.add(te);
		te = new TableEntity();
		te.setName(UPDATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabName.add(te);
		te = new TableEntity();
		te.setName(UPDATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabName.add(te);
		te = new TableEntity();
		te.setName(TABNAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(1);
		te.setDefaultValue("");
		listTabName.add(te);
		te = new TableEntity();
		te.setName("ISMAINTABLE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(10);
		te.setIsNull(1);
		te.setDefaultValue("");
		listTabName.add(te);
		te = new TableEntity();
		te.setName("MAINID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabName.add(te);
		te = new TableEntity();
		te.setName("MAINNAME");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabName.add(te);
		te = new TableEntity();
		te.setName("MEMO");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(1000);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listTabName.add(te);
		te = new TableEntity();
		te.setName("FLAG");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(1);
		te.setDefaultValue("0");
		listTabName.add(te);
		return listTabName;
	}
	
	/**
	 * 创建FormName表中字段信息
	 * @return
	 */
	public List<TableEntity> lstFormName(){
		List<TableEntity> listFormName;
		listFormName = new ArrayList<TableEntity>();
		TableEntity te = new TableEntity();
		te.setName(CREATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormName.add(te);
		te = new TableEntity();
		te.setName(CREATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormName.add(te);
		te = new TableEntity();
		te.setName(UPDATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormName.add(te);
		te = new TableEntity();
		te.setName(UPDATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormName.add(te);
		te = new TableEntity();
		te.setName("FORMNAMEF");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(1);
		te.setDefaultValue("");
		listFormName.add(te);
		te = new TableEntity();
		te.setName("ACTIONURL");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(1);
		te.setDefaultValue("");
		listFormName.add(te);
		te = new TableEntity();
		te.setName("METHOD");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(50);
		te.setIsNull(1);
		te.setDefaultValue("");
		listFormName.add(te);
		te = new TableEntity();
		te.setName("MEMO");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(1000);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormName.add(te);
		te = new TableEntity();
		te.setName("FORMTYPE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormName.add(te);
		te = new TableEntity();
		te.setName("VERSNUM");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(50);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormName.add(te);
		te = new TableEntity();
		te.setName("ISENABLED");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormName.add(te);
		return listFormName;
	}
	
	/**
	 * 创建FormFields表中字段信息
	 * @return
	 */
	public List<TableEntity> lstFormFields(){
		List<TableEntity> listFormFields;
		listFormFields = new ArrayList<TableEntity>();
		TableEntity te = new TableEntity();
		te.setName(CREATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName(CREATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName(UPDATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName(UPDATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName(TABNAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(1);
		te.setDefaultValue("");
		listFormFields.add(te);
		te = new TableEntity();
		te.setName(TABLEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("TABLEMEMO");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(1000);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("FIELDNAME");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(1);
		te.setDefaultValue("");
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("FIELDID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("TITLE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("FORMID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(1);
		te.setDefaultValue("0");
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("SORT");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(1);
		te.setDefaultValue("0");
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("INPUTISDISPLAY");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(1);
		te.setDefaultValue("0");
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("INPUTTYPE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(1);
		te.setDefaultValue("0");
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("INPUTDEFAULTVALUE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(500);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("INPUTVALUE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(500);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("QUERYISCONDITIONS");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("QUERYISCOLUMN");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("ISMODIFY");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("SOURCEMODE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		te = new TableEntity();
		te.setName("SOURCECONTENT");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(500);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormFields.add(te);
		return listFormFields;
	}
	
	/**
	 * 创建FormTable表中字段信息
	 * @return
	 */
	public List<TableEntity> lstFormTable(){
		List<TableEntity> listFormTable;
		listFormTable = new ArrayList<TableEntity>();
		TableEntity te = new TableEntity();
		te.setName(CREATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormTable.add(te);
		te = new TableEntity();
		te.setName(CREATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormTable.add(te);
		te = new TableEntity();
		te.setName(UPDATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormTable.add(te);
		te = new TableEntity();
		te.setName(UPDATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormTable.add(te);
		te = new TableEntity();
		te.setName("FORMID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(1);
		te.setDefaultValue("0");
		listFormTable.add(te);
		te = new TableEntity();
		te.setName(TABLEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(1);
		te.setDefaultValue("0");
		listFormTable.add(te);
		te = new TableEntity();
		te.setName(TABNAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(1);
		te.setDefaultValue("");
		listFormTable.add(te);
		te = new TableEntity();
		te.setName("ISMAINTABLE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormTable.add(te);
		te = new TableEntity();
		te.setName("TABLEMAINID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormTable.add(te);
		te = new TableEntity();
		te.setName("TABLEMAINNAME");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormTable.add(te);
		te = new TableEntity();
		te.setName("TABLEMEMO");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(1000);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listFormTable.add(te);
		return listFormTable;
	}
	
	/**
	 * 创建User表中字段信息
	 * @return
	 */
	public List<TableEntity> lstUser(){
		List<TableEntity> listUser;
		listUser = new ArrayList<TableEntity>();
		TableEntity te = new TableEntity();
		te.setName(CREATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName(CREATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName(UPDATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName(UPDATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("LOGIN_NAME");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("PASSWORD");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("NAME");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(1);
		te.setDefaultValue("");
		listUser.add(te);
		te = new TableEntity();
		te.setName("EMAIL");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("TEL");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(9);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("MOBILEPHONE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(20);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("IDENTITYNUMBER");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(18);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("ENABLE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(1);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("SEX");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(1);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("DUTY");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("DEPOSITBANK");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(50);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("PERSONTYPE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(50);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("CARDNUMBER");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(50);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("BUSINESSCARDNUMBER");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(50);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("PAGESIZE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("ISMANAGER");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(1);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("MAJOR");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(50);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("PUBSERVICEBANK");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(50);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		te = new TableEntity();
		te.setName("IMGPATH");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(50);                                                                                                                                                                                                               
		te.setIsNull(0);
		te.setDefaultValue(null);
		listUser.add(te);
		return listUser;
	}
	
	/**
	 * 创建工作流配置表字段
	 * @return
	 */
	public List<TableEntity> lstWfConfig(){
		List<TableEntity> listWfConfig;
		listWfConfig = new ArrayList<TableEntity>();
		TableEntity te = new TableEntity();
		te.setName(CREATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfConfig.add(te);
		te = new TableEntity();
		te.setName(CREATENAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfConfig.add(te);
		te = new TableEntity();
		te.setName(CREATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfConfig.add(te);
		te = new TableEntity();
		te.setName(UPDATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfConfig.add(te);
		te = new TableEntity();
		te.setName(UPDATENAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfConfig.add(te);
		te = new TableEntity();
		te.setName(UPDATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfConfig.add(te);
		te = new TableEntity();
		te.setName(DEPTID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfConfig.add(te);
		te = new TableEntity();
		te.setName(TABNAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfConfig.add(te);
		te = new TableEntity();
		te.setName(TABLEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfConfig.add(te);
		te = new TableEntity();
		te.setName("COLUMNNAME");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfConfig.add(te);
		te = new TableEntity();
		te.setName("COLUMNID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfConfig.add(te);
		return listWfConfig;
	}
	
	/**
	 * 创建工作流定义表中字段信息
	 * @return
	 */
	public List<TableEntity> lstWfDefine(){
		List<TableEntity> listWfDefine;
		listWfDefine = new ArrayList<TableEntity>();
		TableEntity te = new TableEntity();
		te.setName(CREATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName(CREATENAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName(CREATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName(UPDATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName(UPDATENAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName(UPDATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName(DEPTID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName("NAME");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName("ISENABLE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName("ISTRCAKFORM");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName("ISAUTOTITLE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName("DEFAULTTITLE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName("ISQUICK");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName("QUICKRULE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName("ISARCHIVES");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		te = new TableEntity();
		te.setName("ISSELNEXTUSER");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfDefine.add(te);
		return listWfDefine;
	}
	
	/**
	 * 创建工作流定义表中字段信息
	 * @return
	 */
	public List<TableEntity> lstWfNode(){
		List<TableEntity> listWfNode;
		listWfNode = new ArrayList<TableEntity>();
		TableEntity te = new TableEntity();
		te.setName(CREATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName(CREATENAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName(CREATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName(UPDATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName(UPDATENAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName(UPDATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("WFID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_BIGINT);
		te.setNumber(20);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("ISENABLE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("SORT");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("name");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName(DEPTID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("ROLEID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("ISCOUNTERSIGN");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("COUNTERSIGNTABLENAME");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("COUNTERSIGNRULE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("ISAUTOADOPT");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("ISDEPUTE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("ISSMS");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("ISEMAIL");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("ISAUTOSKIP");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		te = new TableEntity();
		te.setName("DEPTSTATUS");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNode.add(te);
		return listWfNode;
	}
	
	/**
	 * 创建工作流定义表中字段信息
	 * @return
	 */
	public List<TableEntity> lstWfNodeRule(){
		List<TableEntity> listWfNodeRule;
		listWfNodeRule = new ArrayList<TableEntity>();
		TableEntity te = new TableEntity();
		te.setName(CREATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName(CREATENAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName(CREATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName(UPDATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName(UPDATENAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName(UPDATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName(DEPTID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName("WFID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_BIGINT);
		te.setNumber(20);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName("NODEID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_BIGINT);
		te.setNumber(20);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName(TABNAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName(TABLEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName("COLUMNNAME");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName("COLUMNID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(10);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName("RULETYPE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName("RULEVALUE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName("TONODEID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_BIGINT);
		te.setNumber(20);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName("RULENAME");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		te = new TableEntity();
		te.setName("RULESORT");
		te.setType(HBCSoftConstant.COLUMN_TYPE_INT);
		te.setNumber(20);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNodeRule.add(te);
		return listWfNodeRule;
	}
	
	/**
	 * 创建工作流定义表中字段信息
	 * @return
	 */
	public List<TableEntity> lstWfNRD(){
		List<TableEntity> listWfNRD;
		listWfNRD = new ArrayList<TableEntity>();
		TableEntity te = new TableEntity();
		te.setName(CREATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNRD.add(te);
		te = new TableEntity();
		te.setName(CREATENAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNRD.add(te);
		te = new TableEntity();
		te.setName(CREATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNRD.add(te);
		te = new TableEntity();
		te.setName(UPDATEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(32);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNRD.add(te);
		te = new TableEntity();
		te.setName(UPDATENAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNRD.add(te);
		te = new TableEntity();
		te.setName(UPDATETIME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_DATETIME);
		te.setNumber(0);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNRD.add(te);
		te = new TableEntity();
		te.setName("RULEID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_BIGINT);
		te.setNumber(20);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNRD.add(te);
		te = new TableEntity();
		te.setName(TABNAME);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNRD.add(te);
		te = new TableEntity();
		te.setName(TABLEID);
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNRD.add(te);
		te = new TableEntity();
		te.setName("COLUMNNAME");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(100);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNRD.add(te);
		te = new TableEntity();
		te.setName("COLUMNID");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNRD.add(te);
		te = new TableEntity();
		te.setName("RULETYPE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		listWfNRD.add(te);
		te = new TableEntity();
		te.setName("RULEVALUE");
		te.setType(HBCSoftConstant.COLUMN_TYPE_TEXT);
		te.setNumber(200);
		te.setIsNull(0);
		te.setDefaultValue(null);
		return listWfNRD;
	}
}
