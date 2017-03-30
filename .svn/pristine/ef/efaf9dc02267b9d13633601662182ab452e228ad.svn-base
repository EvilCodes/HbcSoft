package com.hbcsoft.report.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
import com.hbcsoft.report.entity.ReportRow;
import com.hbcsoft.report.service.ReportConfigService;
import com.hbcsoft.report.service.ReportRowService;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.util.PubTools;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
 * 报表表体配置控制层
 */
@Controller
@RequestMapping(value = "/reportRow")
public class ReportRowController extends BaseController<ReportRowController> {
	/**
	 * 注入表体配置逻辑层
	 */
	@Autowired
	private transient ReportRowService reportRowService;
	/**
	 * 注入报表配置Service方法
	 */
	@Autowired
	private transient ReportConfigService configService;
	/**
	 * 总记录数
	 */
	private int totalNum;
	/**
	 * session
	 */
	public static final Map<Long, ReportRow> sessionInfoMap = new ConcurrentHashMap<Long, ReportRow>();

	/**
	 * 树
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@RequestMapping(value = "/treegrid", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> showData()throws HbcsoftException{
		List<TreeNode> treeNode = new ArrayList<TreeNode>();
		final SessionInfo info = (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			try {
				treeNode = reportRowService.getRepRowTreeMenu(String.valueOf(info.getCompany().getId()));
			} catch (InstantiationException e) {
				this.getLogger().info(e);
			} catch (IllegalAccessException e) {
				this.getLogger().info(e);
			}
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		return treeNode;
	}
	/**
	 * 首页跳转
	 */
	@RequestMapping(value = "/queryReportRows")
	public String queryRows() {
		return "report/row/row";
	}

	/**
	 * 分页查询
	 * 
	 * @throws HbcsoftException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/pageRepRowList", method = RequestMethod.POST)
	public void pageRepRowList(final ReportRow repRow, final HttpServletRequest request,
			final HttpServletResponse response)
			throws HbcsoftException, InstantiationException, IllegalAccessException {
		final SessionInfo info = (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String id = request.getParameter("id");
		final int pageSize = Integer.parseInt(request.getParameter("rows"));
		final int currentPage = Integer.parseInt(request.getParameter("page"));
		Result result = null;
		try {
			final List<ReportRow> allRoleType = reportRowService.queryAll(repRow, id,
					String.valueOf(info.getCompany().getId()));
			totalNum = allRoleType.size();
			int pageTimes;
			if (totalNum % pageSize == 0) {
				pageTimes = totalNum / pageSize;
			} else {
				pageTimes = totalNum / pageSize + 1;
			}
			final int startRow = (currentPage - 1) * pageSize;
			final List<ReportRow> list = reportRowService.queryAllPage(repRow, id, String.valueOf(info.getCompany().getId()), startRow, pageSize);
			final Pager<ReportRow> page = new Pager<ReportRow>();
			page.setTotalCount(totalNum);
			page.setResult(list);
			this.getLogger().info("page.getTotalCount():" + page.getTotalCount() + "////page.getResult():" + page.getResult());
			final Datagrid<ReportRow> dg = new Datagrid<ReportRow>(page.getTotalCount(), page.getResult());
			ControllerUtils.renderText(dg);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
			result = Result.errorResult();
			ControllerUtils.renderJson(result);
			throw e;
		}
	}

	/**
	 * 模糊查询弹出row-search.jsp页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/repRowSearch", method = RequestMethod.GET)
	public ModelAndView search()throws HbcsoftException {
		final ModelAndView model = new ModelAndView();
		model.setViewName("report/row/row-search");
		return model;
	}

	/**
	 * 添加、修改
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/repRowInput")
	public ModelAndView input(final HttpServletRequest request, final HttpServletResponse response)
			throws HbcsoftException {
		final ModelAndView model = new ModelAndView("report/row/row-input");
		ReportRow reportRow;
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		final String id = request.getParameter("id");
		final String parentId = request.getParameter("parentId");
		try {
			if (PubTools.isEmpty(id)) {// 添加
				reportRow = new ReportRow();
				final ReportConfig config = reportRowService.queryConfigByIds(parentId);
				if(PubTools.isEmpty(config.getId())){
					final ReportRow parentRepRow = reportRowService.queryById(parentId);
					reportRow.setParentRepRow(parentRepRow);
					final ReportConfig reportConfig = configService.getReportConfig(String.valueOf(info.getCompany().getId()),parentRepRow.getReportId());//根据reportid查询报表配置的id和名称
					reportRow.setReportId(reportConfig.getId());
					reportRow.setConfig(reportConfig);
				}else{
					reportRow.setReportId(config.getId());//默认当前的报表名称
					reportRow.setConfig(config);
				}
			} else {// 修改
				reportRow = reportRowService.queryById(id);
				final Long pid = reportRow.getParentId();
				ReportRow parentRow = new ReportRow();
				parentRow = reportRowService.queryById(String.valueOf(pid));
				if(PubTools.isEmpty(parentRow.getId())){//如果报表配置为空，就返回报表体名称
					final ReportConfig config = reportRowService.queryConfigByIds(String.valueOf(reportRow.getParentId()));
					reportRow.setReportId(config.getId());//默认当前的报表名称
					reportRow.setConfig(config);
				}else{//如果报表配置不为空，就返回报表配置名称
					final ReportConfig config = configService.getReportConfig(String.valueOf(info.getCompany().getId()),reportRow.getReportId());//根据reportid查询报表配置的id和名称
					reportRow.setReportId(config.getId());//默认当前的报表名称
					reportRow.setConfig(config);
					reportRow.setParentRepRow(parentRow);
				}
			}
				model.getModelMap().put("reportRow", reportRow);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		return model;
	}
	/**
	* 保存
	* @param request
	* @return
	 * @throws HbcsoftException 
	*/
	@RequestMapping(value = "/repRowSave", method = RequestMethod.POST)
	@ResponseBody
	public String save(final HttpServletRequest request, final ReportRow reportRow,final String companyId) throws HbcsoftException {
		final String id = request.getParameter("id");
		final SessionInfo info = (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		if (PubTools.isEmpty(id)) {// 添加
			setDate(reportRow);
			reportRow.setIsEnd(1);//每添加一个子节点就是一个末级节点
			if(PubTools.isEmpty(reportRow.getParentRepRow())){//二级菜单，父节点
				reportRow.setParentId(reportRow.getConfig().getId());
				reportRow.setParentId(reportRow.getReportId());//二级菜单根据报表名称更改上级类型
				reportRow.setLevelcount(1);//级次为1
				final List<ReportRow> rows = reportRowService.getChildrenRow(reportRow.getParentId());
				reportRow.setSort(rows.size()+1);
			}else{//子节点
				reportRow.setParentId(reportRow.getParentRepRow().getId());
				final int pLevcou = reportRow.getParentRepRow().getLevelcount();
				reportRow.setLevelcount(pLevcou+1);//级次依次加1
				final ReportRow parentRepRow = reportRowService.queryById(String.valueOf(reportRow.getParentId()));
				reportRowService.updatePIsEnd(parentRepRow.getId());
				final List<ReportRow> rows = reportRowService.getChildrenRow(reportRow.getParentId());
				reportRow.setSort(rows.size()+1);//查出子节点个数，排序递增
			}
			final ReportRow parentRepRow = reportRowService.queryById(String.valueOf(reportRow.getParentId()));
			if(parentRepRow.getStatus()==null){
				final String stat = PubTools.getStatus("", reportRow.getSort());
				reportRow.setStatus(stat);
			}else{
				final String stat = PubTools.getStatus(parentRepRow.getStatus(), reportRow.getSort());
				reportRow.setStatus(stat);
			}
			reportRowService.saveReportRow(reportRow);
		} else {// 修改
			reportRow.setCompanyId(info.getCompany().getId());
			reportRow.setUpdateID(info.getUser().getId());
			reportRow.setUpdateTime(new Date());
			if(PubTools.isEmpty(reportRow.getParentRepRow())){//二级菜单，父节点
				reportRow.setParentId(reportRow.getConfig().getId());
			}else{
				reportRow.setParentId(reportRow.getParentRepRow().getId());
			}
			reportRowService.update(reportRow);
		}
		ControllerUtils.render(ServletUtils.TEXT_TYPE,Result.successResult());
		return null;
	}
	
	/**
	 * 新增时设置日期字段值
	 */
	public ReportRow setDate(final ReportRow reportRow){
		final SessionInfo info = (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final Long fromid = SequenceUtil.getTableId("F_FORMNAME");
		reportRow.setId(fromid);
		reportRow.setCompanyId(info.getCompany().getId());
		reportRow.setCreateID(fromid);// 创建人id
		reportRow.setCreateTime(new Date());// 创建时间
		reportRow.setUpdateID(fromid);// 修改人id
		reportRow.setUpdateTime(new Date());
		return reportRow;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/deleteRepRow",method = RequestMethod.POST)
	public String delete(final HttpServletRequest request)throws HbcsoftException{
		final SessionInfo info = (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		String ids = request.getParameter("ids");
		Long parentId = null;
		try {
			if(!PubTools.isEmpty(ids)){
				ids = ids.substring(0, ids.length() - 1);
				final String[] strId = ids.split(",");
				for(int i = 0; i<strId.length;i++){
					final ReportRow row = reportRowService.queryById(strId[i]);
					row.setDelflag(1);
					row.setCompanyId(info.getCompany().getId());
					parentId = row.getParentId();
					reportRowService.deleteById(row);
					final List<ReportRow> rows = reportRowService.getChildrenRow(parentId);
					if(rows.isEmpty()){//删除字节点后查询父节点下是否还有子节点，没有时更新isEnd状态
						reportRowService.updatePIsEndAfterDel(parentId);
					}
				}
				final List<ReportRow> rows = reportRowService.queryAfterDel(String.valueOf(info.getCompany().getId()),parentId);
				for(final ReportRow row:rows){
					final ReportRow rr=reportRowService.queryMaxSort(String.valueOf(info.getCompany().getId()),String.valueOf(row.getParentId()));
					if(PubTools.isEmpty(rr.getId())){
						row.setSort(1);
					}else{
						row.setSort(rr.getSort()+1);
					}
					final ReportRow parentRepRow = reportRowService.queryById(String.valueOf(row.getParentId()));
					if(parentRepRow.getStatus()==null){
						final String stat = PubTools.getStatus("", row.getSort());
						row.setStatus(stat);
					}else{
						final String stat = PubTools.getStatus(parentRepRow.getStatus(), row.getSort());
						row.setStatus(stat);
					}
					reportRowService.updateSortDesc(row.getSort(),row.getStatus(),row.getId());
				}
			}
			ControllerUtils.renderJson(Result.successResult());
		} catch (HbcsoftException e) {
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
	public String haveChildNode(final HttpServletRequest request)throws HbcsoftException{
		String ids = request.getParameter("ids");
		boolean isHaveChild = false;
		Result result;
			try {
				if(!PubTools.isEmpty(ids)){
					ids = ids.substring(0, ids.length() - 1);
					final String[] strId = ids.split(",");
					final Result result1=new Result(2, "", null);
					for(int i = 0; i<strId.length;i++){
						isHaveChild = reportRowService.queryByParentId(strId[i]);
						if(isHaveChild){
							result = Result.successResult();// 存在
						} else {
							result = result1;// 不存在
						}
						ControllerUtils.renderJson(result);
					}
				}
			} catch (HbcsoftException e) {
				ControllerUtils.renderJson(Result.errorResult());
				this.getLogger().info(e);
			}
		return null;
	}
	/**
	 * 总记录数get
	 */
	public int getTotalNum() {
		return totalNum;
	}

	/**
	 * 总记录数set
	 */
	public void setTotalNum(final int totalNum) {
		this.totalNum = totalNum;
	}


}
