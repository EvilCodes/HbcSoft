package com.hbcsoft.report.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.common.TreeNode;
import com.hbcsoft.common.model.Datagrid;
import com.hbcsoft.common.model.Pager;
import com.hbcsoft.common.model.Result;
import com.hbcsoft.common.utils.ControllerUtils;
import com.hbcsoft.common.utils.ServletUtils;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.report.entity.ReportConfig;
import com.hbcsoft.report.entity.ReportHead;
import com.hbcsoft.report.service.ReportConfigService;
import com.hbcsoft.report.service.ReportHeadService;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.util.PubTools;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 表头配置 controller层
 * 
 * @author Administrator
 *
 */
@Controller
// 负责注册一个bean 到spring 上下文中
@RequestMapping(value = "/report/head")
public class ReportHeadController extends BaseController<ReportHeadController> {
	/**
	 * 报表头service接口
	 */
	@Autowired
	private transient ReportHeadService headService;
	/**
	 * 注入Service方法
	 */
	@Autowired
	private transient ReportConfigService reportConfigService;
	/**
	 * 总页数记录
	 */
	private int totalNum;
	/**
	 * pageTimes
	 */
	private int pageTimes;
	/**
	 * 获取公司ID
	 * @param request
	 * @return
	 */
	public Long companyID(){
		return ((SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO)).getCompany().getId();
	}
	/**
	 * 树 tree
	 */
	@RequestMapping(value = "/treegrid", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> showData() {
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		List<TreeNode> treeNode = new ArrayList<TreeNode>();
		try {
			treeNode = headService.getReportHeadTreeMenu(String.valueOf(info
					.getCompany().getId()));
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return treeNode;
	}
	/**
	 * 首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reportHeader")
	public String reportHeader() {
		return "/report/head/reportHead";
	}
	/**
	 * 添加、修改时弹出reportHead-input.jsp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/input")
	public ModelAndView input(final HttpServletRequest request,
			final HttpServletResponse response){
		final ModelAndView model = new ModelAndView("report/head/reportHead-input");
		ReportHead head ;
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			
			if(PubTools.isEmpty(request.getParameter("id"))){//id为空，添加
				head = new ReportHead();
				
				final ReportConfig rootConfig = headService.queryReportConfigById(request.getParameter("parentId"));
					if(PubTools.isEmpty(rootConfig.getId())){//如果报表配置为空，就返回报表头名称
						final ReportHead parentHead = headService.queryReportHeadById(request.getParameter("parentId"));
						head.setParentHeader(parentHead);
						final ReportConfig reportConfig = reportConfigService.getReportConfig(String.valueOf(info.getCompany().getId()),parentHead.getReportId());//根据reportid查询报表配置的id和名称
						head.setReportId(reportConfig.getId());
						head.setRootConfig(reportConfig);
					}else{//如果报表配置不为空，就返回报表配置名称
						head.setRootConfig(rootConfig);
						head.setReportId(rootConfig.getId());
					}
			}else{//id不为空，修改
				head = headService.queryReportHeadById(request.getParameter("id"));//根据主键查询 
				final ReportHead parentHead = headService.queryReportHeadById(String.valueOf(head.getParentId()));
					if(PubTools.isEmpty(parentHead.getId())){//如果报表配置为空，就返回报表头名称
						final ReportConfig rootConfig = headService.queryReportConfigById(String.valueOf(head.getParentId()));//String.valueOf(head.getReportId())
						head.setRootConfig(rootConfig);
						head.setReportId(rootConfig.getId());
					}else{//如果报表配置不为空，就返回报表配置名称
						final ReportConfig reportConfig = reportConfigService.getReportConfig(String.valueOf(info.getCompany().getId()),parentHead.getReportId());//根据reportid查询报表配置的id和名称
						head.setReportId(reportConfig.getId());
						head.setRootConfig(reportConfig);
						head.setParentHeader(parentHead);
					}
				//head.setParentHeader(parentHead);
			}
			model.getModelMap().put("head", head);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return model;
	}
	/**
	 * 保存（添加、修改）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(final HttpServletRequest request,final ReportHead head){
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String id = request.getParameter("id");//主键id
		
		try {
			if(PubTools.isEmpty(id)){//添加
				
				final Long fromid = SequenceUtil.getTableId("F_FORMNAME");
				head.setId(fromid);
				head.setCompanyId(info.getCompany().getId());//公司id
				head.setCreateID(info.getUser().getId());//创建id
				head.setCreateTime(new Date());//创建时间
				if(PubTools.isEmpty(head.getParentHeader())){
					head.setParentId(head.getRootConfig().getId());
					head.setLevelcount(1);
					final ReportHead rh=headService.queryMaxSort(String.valueOf(head.getCompanyId()),String.valueOf(head.getParentId()));
					if(PubTools.isEmpty(rh.getId())){
						head.setSort(1);
					}else{
						head.setSort(rh.getSort()+1);
					}
					
				}else{
					head.setParentId(head.getParentHeader().getId());
					final List<ReportHead> hlist = headService.queryByParentId(String.valueOf(head.getParentHeader().getId()));
					//if(hlist.size()==0){
					if(PubTools.isEmpty(hlist)){
						final int count = head.getParentHeader().getLevelcount();
						head.setLevelcount(count+1);
						final ReportHead rh=headService.queryMaxSort(String.valueOf(head.getCompanyId()),String.valueOf(head.getParentId()));
						if(PubTools.isEmpty(rh.getId())){
							head.setSort(1);
						}else{
							head.setSort(rh.getSort()+1);
						}
					}else{
						head.setLevelcount(hlist.get(0).getLevelcount());
						final ReportHead rh=headService.queryMaxSort(String.valueOf(head.getCompanyId()),String.valueOf(head.getParentId()));
						if(PubTools.isEmpty(rh.getId())){
							head.setSort(1);
						}else{
							head.setSort(rh.getSort()+1);
						}
					}
					
				}
				//head.setParentId(head.getParentHeader().getReportId());
				headService.save(head);
			}else{//修改
				head.setCompanyId(info.getCompany().getId());//公司id
				head.setUpdateID(info.getUser().getId());//修改人
				head.setUpdateTime(new Date());//修改时间
				//head.setParentId(head.getParentHeader().getId());
				//head.setParentId(head.getParentHeader().getRootConfig().getId());
				if(PubTools.isEmpty(head.getParentHeader())){
					head.setParentId(head.getRootConfig().getId());
				}else{
					head.setParentId(head.getParentHeader().getId());
				}
				//head.setParentId(head.getParentHeader().getReportId());
				headService.update(head);
			}
			ControllerUtils.render(ServletUtils.TEXT_TYPE,
					Result.successResult());
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
			ControllerUtils.render(ServletUtils.TEXT_TYPE,
					Result.errorResult());
		}
		return null;
	}
	/**
	 * 分页查询
	 * @param ReportHeaderConfig
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/pageHeadList", method = RequestMethod.POST)
	public void pageHeadList(final ReportHead head,
			final HttpServletRequest request, final HttpServletResponse response) {
		final String id = request.getParameter("id");
		final int pageSize = Integer.parseInt(request.getParameter("rows"));
		final int currentPage = Integer.parseInt(request.getParameter("page"));
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			final List<ReportHead> allHead = headService.queryAll(
					head, id, String.valueOf(info.getCompany().getId()));
			totalNum = allHead.size();
			if (totalNum % pageSize == 0) {
				pageTimes = totalNum / pageSize;
			} else {
				pageTimes = totalNum / pageSize + 1;
			}
			final int startRow = (currentPage - 1) * pageSize;
			final List<ReportHead> list = headService.queryAllReportHeaderConfig(
					head, id, String.valueOf(info.getCompany().getId()),
					startRow, pageSize);
			final Pager<ReportHead> pager = new Pager<ReportHead>();
			pager.setTotalCount(totalNum);
			pager.setResult(list);
			final Datagrid<ReportHead> dg = new Datagrid<ReportHead>(
					pager.getTotalCount(), pager.getResult());
			ControllerUtils.renderText(dg);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			ControllerUtils.renderJson(Result.errorResult());
			this.getLogger().info(e);
		}
	}
	/**
	 * 模糊查询弹出dataDict-search.jsp页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		final ModelAndView model = new ModelAndView();
		model.setViewName("report/head/reportHead-search");
		return model;
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public String delete(final HttpServletRequest request){
		String ids = request.getParameter("ids");
		 Long parentId = null;
		try {
			if(!PubTools.isEmpty(ids)){
				ids = ids.substring(0, ids.length() - 1);
				final String[] strId = ids.split(",");
				for(int i = 0; i<strId.length;i++){
					final ReportHead head = headService.queryReportHeadById(strId[i]);
					 head.setDelflag(1);//删除
					 head.setCompanyId(companyID());
					 parentId = head.getParentId();
					 headService.delete(head);
				}
				final List<ReportHead> headlist = headService.querydelAfterReportHead(String.valueOf(companyID()),parentId);
				for(final ReportHead hlist:headlist){
					final ReportHead rh=headService.queryMaxSort(String.valueOf(companyID()),String.valueOf(hlist.getParentId()));
					if(PubTools.isEmpty(rh.getId())){
						hlist.setSort(1);
					}else{
						hlist.setSort(rh.getSort()+1);
					}
					headService.updateSortDesc(hlist.getSort(),hlist.getId());
				}
			}
			
			ControllerUtils.renderJson(Result.successResult());
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			ControllerUtils.renderJson(Result.errorResult());
			this.getLogger().info(e);
		}
		
		return null;
	}
	/**
	 * 查询父节点下是否有子节点
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/haveChildNode")
	public String haveChildNode(final HttpServletRequest request){
		String ids = request.getParameter("ids");
		boolean isHaveChild = false;
		Result result;
			try {
				if(!PubTools.isEmpty(ids)){
					ids = ids.substring(0, ids.length() - 1);
					final String[] strId = ids.split(",");
					final Result result1=new Result(2, "", null);
					for(int i = 0; i<strId.length;i++){
						isHaveChild = headService.queryReportHeadByParentId(strId[i]);
						if(isHaveChild){
							result = Result.successResult();// 存在
						} else {
							result = result1;// 不存在
						}
						ControllerUtils.renderJson(result);
					}
				}
				
				
			} catch (HbcsoftException e) {
				// TODO Auto-generated catch block
				ControllerUtils.renderJson(Result.errorResult());
				this.getLogger().info(e);
			}
		return null;
	}
	/**
	 * 查询报表配置的主键、报表名
	 * @param request
	 */
	/*@ResponseBody
	@RequestMapping(value = "getReportConfigList")
	public void getReportConfigList(final HttpServletRequest request){
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			final List<ReportConfig> list = reportConfigService.getReportConfig(String.valueOf(info.getCompany().getId()));
			ControllerUtils.renderJson(list);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
	}*/
	/**
	 * 判断头名称是否重复
	 * 
	 * @param dtCode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/checkhName", method = RequestMethod.POST)
	public String checkhName(final ReportHead rhead,
			final HttpServletRequest request)
			throws UnsupportedEncodingException {
		String json = "0";// 不存在
		final String hname = URLDecoder.decode(rhead.getHname(), "utf-8");
		rhead.setHname(hname);
		try {
			final SessionInfo info = (SessionInfo) session
					.getAttribute(HBCSoftConstant.SESSIONINFO);
			final ReportHead head = headService.queryhNameById(rhead,String.valueOf(info.getCompany().getId()));
			if (hname.equals(head.getHname())) {
				json = "1";// 存在

			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return json;
	}
	/**
	 * 总记录getter方法
	 * @return
	 */
	public int getTotalNum() {
		return totalNum;
	}
	/**
	 * 总记录setter方法
	 * @param totalNum
	 */
	public void setTotalNum(final int totalNum) {
		this.totalNum = totalNum;
	}
	/**
	 * pageTimes getter方法
	 * @return
	 */
	public int getPageTimes() {
		return pageTimes;
	}
	/**
	 * pageTimes setter方法
	 * @param pageTimes
	 */
	public void setPageTimes(final int pageTimes) {
		this.pageTimes = pageTimes;
	}

}
