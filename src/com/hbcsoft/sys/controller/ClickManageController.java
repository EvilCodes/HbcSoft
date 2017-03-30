package com.hbcsoft.sys.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.ClickManage;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.service.ClickManageService;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.common.ComConstant;
import com.hbcsoft.zdy.util.SequenceUtil;

@Controller
@RequestMapping(value = "/clickManage")
public class ClickManageController extends BaseController<ClickManageController> {

	/**
	 * UserService接口 
	 */
	@Autowired
	private transient ClickManageService manageService;
	/**
	 * 每页显示的条数
	 */
	private int pageSize = 10;//每页显示的条数
	/**
	 * 总记录数
	 */
	private int totalNum;//总记录数
	/**
	 * 第几页
	 */
	private int currentPage;//第几页
	/**
	 * pageList方法中变量
	 */
	private final transient JSONObject jsonObject = new JSONObject();
	/**
	 * 跳转到新增数据表页面：添加页面
	 * @return
	 */
	@RequestMapping(value = "/clickSave")
	public ModelAndView addClick() throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("sys/clickManage/clickManage-add");
		return mav;
	}
	/**
	 * 跳转到新增数据表页面：编辑页面
	 * @return
	 */
	@RequestMapping(value = "/editClick")
	public ModelAndView editClick(String id) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("sys/clickManage/clickManage-edit");
		ClickManage cm = new ClickManage();
		//表信息
		if(id==null||"null".equals(id)){
			id = request.getParameter("id");
		}
		cm=manageService.queryCm(id);
		mav.getModelMap().put("id", cm.getId());
		mav.getModelMap().put("key", cm.getClickKey());
		mav.getModelMap().put("values", cm.getClickValue());
		return mav;
	}
	/**
	 * 修改页面点击提交
	 * @return
	 */
	@RequestMapping(value = "/editClickSubmit")
	public ModelAndView editClickSubmit() throws HbcsoftException{
		final String id=request.getParameter("cid");
		final String key=request.getParameter("key");
		final String keys=request.getParameter("keys");
		final String values=request.getParameter("values");
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final Long companyId = sessionInfo.getCompany().getId();
		request.setAttribute(HBCSoftConstant.MESSAGE, 0);//添加成功
		try{
			ClickManage cm=manageService.queryCm(id);
			if(key.equals(keys)){//key没发生变化
				cm.setClickValue(values);
				int intV=manageService.updateClick(cm);
				if(intV==0){
					request.setAttribute(HBCSoftConstant.MESSAGE, 2);//修改失败
				}
			}else{//key值发生变化
				final List<ClickManage> count = manageService.queryClick(keys,Long.toString(companyId));
				if(!count.isEmpty()){
					request.setAttribute(HBCSoftConstant.MESSAGE, 1);//当前Key存在
				}else{//没有重复的  进行添加
					cm.setClickKey(keys);
					cm.setClickValue(values);
					int intV=manageService.updateClick(cm);
					if(intV==0){
						request.setAttribute(HBCSoftConstant.MESSAGE, 2);//修改失败
					}
				}
			}
		}catch(HbcsoftException e){
			this.getLogger().info(e);
		}
		return new ModelAndView("forward:/clickManage/editClick.hbc?id="+id);
	}
	/**
	 * 查询所有 
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/queryClick")
	public ModelAndView queryClick() throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("sys/clickManage/clickManage-list");
		final String keys = request.getParameter("keys");
		final String values = request.getParameter("values");
		final List<ClickManage> allClick = manageService.queryAll(keys, values);
		totalNum = this.getSize(allClick);
		int pageTimes;//总页数
		if(totalNum%pageSize == 0){
			pageTimes = totalNum/pageSize;
		}else{
			pageTimes = totalNum/pageSize + 1;
		}
		final String cu = request.getParameter(ComConstant.paraCurrentPage)==null ? "" : request.getParameter(ComConstant.paraCurrentPage);
		if("".equals(cu)){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(cu);
		}
		final int startRow = (currentPage-1) * pageSize;
		final List<ClickManage> list = manageService.queryAllClick(keys, values, startRow, pageSize);
		mav.getModelMap().put("pageSize", pageSize);
		mav.getModelMap().put("currentPage", currentPage);
		mav.getModelMap().put("pageTimes", pageTimes);
		mav.getModelMap().put("totalNum", totalNum);
		mav.getModelMap().put("searchTable", keys);
		mav.getModelMap().put("searchMemo", values);
		mav.getModelMap().put(ComConstant.mavList, list);
		return mav;
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 * @throws HbcsoftException
	 */
	//TODO:调用zdy公用方法删除数据
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		final ModelAndView mav = new ModelAndView("redirect:/clickManage/queryClick.hbc");
		String ids = request.getParameter("del");
		if(ids != null && !"".equals(ids)){
			ids = ids.substring(0, ids.length()-1);
			final String[] strId = ids.split(",");
			for(int i=0; i<strId.length; i++){
				manageService.updateClick(strId[i]);
			}
		}
		return mav;
	}
	/**
	 * 校验Key不能重复存在
	 * @param tableName
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	//TODO：校验数据库表名
	@RequestMapping(value = "/checkClick")
	public ModelAndView checkName() throws UnsupportedEncodingException{
		final String key=request.getParameter("keys");
		final String values=request.getParameter("values");
		final SessionInfo sessionInfo = (SessionInfo) this.session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final Long companyId = sessionInfo.getCompany().getId();
		request.setAttribute(HBCSoftConstant.MESSAGE, 0);//添加成功
		try{
			final List<ClickManage> count = manageService.queryClick(key,Long.toString(companyId));
			if(!count.isEmpty()){
				request.setAttribute(HBCSoftConstant.MESSAGE, 1);//当前Key有值
			}else{//没有重复的  进行添加
				ClickManage clickManage=new ClickManage();
				final Long fromid = SequenceUtil.getTableId("T_SYS_CLICKMANAGE");
				clickManage.setId(fromid);
				clickManage.setZid(fromid);
				clickManage.setCompanyId(companyId);
				clickManage.setClickKey(key);
				clickManage.setClickValue(values);
				clickManage.setMark(HBCSoftConstant.INT_FALSE);//默认为0
				clickManage.setCreateID(sessionInfo.getUser().getId());
				int intV=manageService.savaClick(clickManage);
				this.getLogger().info("=====service:savaClick=========end==");
				if(intV==0){
					request.setAttribute(HBCSoftConstant.MESSAGE, 2);//添加失败
				}
			}
		}catch(HbcsoftException e){
			this.getLogger().info(e);
		}
		return new ModelAndView("sys/clickManage/clickManage-add");
	}
	/**
	 * 分页方法
	 * @param searchTable
	 * @param searchMemo
	 * @param currentPage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	public Object pageList(final String searchTable, final String searchMemo,final int currentPage){ 
		try{
			final List<ClickManage> allTable = manageService.queryAll(searchTable, searchMemo);
			totalNum = this.getSize(allTable);
			int pageTimes;//
			if(totalNum%pageSize == 0){
				pageTimes = totalNum/pageSize;
			}else{
				pageTimes = totalNum/pageSize + 1;
			}
			final int startRow = (currentPage-1) * pageSize;
			final List<ClickManage> list = manageService.queryAllClick(searchTable, searchMemo, startRow, pageSize);
			jsonObject.put("pageSize", pageSize);
			jsonObject.put("currentPage", currentPage);
			jsonObject.put("pageTimes", pageTimes);
			jsonObject.put("totalNum", totalNum);
			jsonObject.put("list", list);
			
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			this.getLogger().info(e);
		}
		return jsonObject;
	}
	/**
	 * 用来获取list的大小，避免PMD问题
	 * @param list
	 * @return
	 */
	private int getSize(final List<?> list){
		return list.size();
	}
	
}
