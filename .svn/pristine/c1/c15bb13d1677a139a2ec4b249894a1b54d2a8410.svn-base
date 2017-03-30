package com.hbcsoft.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.hbcsoft.sys.entity.Org;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.entity.User;
import com.hbcsoft.sys.service.OrgService;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.util.PubTools;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
* 机构
* 
* @author Administrator
*
*/
@Controller
// 负责注册一个bean 到spring 上下文中
@RequestMapping(value = "/sys/org")
public class OrgController extends BaseController<OrgController> {
	/**
	* 机构service
	*/
	@Autowired
	private transient OrgService orgService;

	/**
	* 机构
	*/
	// private Org org;
	/**
	* 总记录数
	*/
	private int totalNum;
	/**
	* id
	*/
	// private int id;
	/**
	* 父节点
	*/
	// private String parentId;
	/**
	* pageTimes
	*/
	private int pageTimes;
	/**
	 * 所有末尾节点集合
	 */
	private transient static List<TreeNode> listAll = new ArrayList<TreeNode>();
	/**
	* 首页跳转
	* 
	* @return
	*/
	@RequestMapping(value = "/orgs")
	public String dictTypes() {
		return "/sys/org/org";
	}

	/**
	* 模糊查询弹出org-search.jsp页面
	* 
	* @return
	*/
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		final ModelAndView model = new ModelAndView();
		model.setViewName("sys/org/org-search");
		return model;
	}

	/**
	* 机构菜单
	* 
	* @return
	*/
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public ModelAndView menu() {
		final ModelAndView model = new ModelAndView();
		model.setViewName("sys/org/org-menu");
		return model;
	}
	
	/**
	* 点选菜单中：机构菜单多选
	* @return
	*/
	@RequestMapping(value = "/menuCheck", method = RequestMethod.GET)
	public ModelAndView menuCheck() {
		final ModelAndView model = new ModelAndView();
		final String ids = request.getParameter("ids");
		model.setViewName("sys/org/org-menuCheck");
		model.getModel().put("ids", ids);
		return model;
	}

	/**
	* 弹出org-input.jsp
	* 
	* @param request
	* @param response
	* @return
	*/
	@RequestMapping(value = "/input")
	public ModelAndView input(final HttpServletRequest request,
			final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView("sys/org/org-input");
		Org org;
		// final String id = request.getParameter("id");
		// final String parentid = request.getParameter("parentId");
		// Long pid=Long.parseLong(parentid=="" ? 0 : parentid);
		final ModelMap modmm = model.getModelMap();
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			if (PubTools.isEmpty(request.getParameter("id"))) {
				// 添加
				org = new Org();
				// dictType.setEnable(1);
				final Org parentOrg = orgService.queryDataByIds(request
						.getParameter("parentId"));
				org.setCreateUser(info.getUser());
				org.setParentOrg(parentOrg);
			} else {
				org = orgService.queryOrgById(request.getParameter("id"));// 根据主键查询，修改
				final Org parentOrg = orgService.queryDataByIds(String
						.valueOf(org.getParentId()));
				org.setCreateUser(info.getUser());
				org.setParentOrg(parentOrg);
			}
			modmm.put("org", org);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return model;
	}

	/**
	* 保存
	* 
	* @param request
	* @param dictType
	* @return
	*/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(final HttpServletRequest request, final Org org) {
		final String id = request.getParameter("id");
		/*
		 * final String code = request.getParameter("code"); final String name =
		 * request.getParameter("name");
		 */
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			if (PubTools.isEmpty(id)) {// 添加
				final Long fromid = SequenceUtil.getTableId("F_FORMNAME");
				final Long userId = info.getUser().getId();
				org.setId(fromid);
				org.setCompanyId(info.getCompany().getId());
				org.setCreateID(userId);
				org.setCreateTime(new Date());// 创建时间
				org.setUpdateID(userId);
				org.setUpdateTime(new Date());
				org.setParentId(org.getParentOrg().getId());
				org.setUserId(org.getCreateUser().getId());

				orgService.save(org);
			} else {// 修改
				final Long userId = info.getUser().getId();
				org.setCompanyId(info.getCompany().getId());
				org.setUpdateID(userId);
				org.setUpdateTime(new Date());
				org.setParentId(org.getParentOrg().getId());
				org.setUserId(org.getCreateUser().getId());
				orgService.update(org);
			}
			ControllerUtils.render(ServletUtils.TEXT_TYPE,
					Result.successResult());
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
			ControllerUtils
					.render(ServletUtils.TEXT_TYPE, Result.errorResult());
		}
		return null;
	}

	/**
	* 判断编号是否重复
	* 
	* @param dtCode
	* @return
	* @throws UnsupportedEncodingException
	*/
	@ResponseBody
	@RequestMapping(value = "/checkCode", method = RequestMethod.POST)
	public String checkCode(final Org org, final HttpServletRequest request)
			throws UnsupportedEncodingException {
		String json = "0";// 不存在
		final String code = URLDecoder.decode(org.getCode(), "utf-8");
		org.setCode(code);
		try {
			final SessionInfo info = (SessionInfo) session
					.getAttribute(HBCSoftConstant.SESSIONINFO);
			final Org orgg = orgService.queryByOrgCodeNameId(org,
					String.valueOf(info.getCompany().getId()));
			// String.valueOf(info.getCompany().getId()), id);

			if (org.getCode().equals(orgg.getCode())) {
				json = "1";// 存在
			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return json;
	}

	/**
	* 判断编号是否重复
	* 
	* @param dtCode
	* @return
	* @throws UnsupportedEncodingException
	*/
	@ResponseBody
	@RequestMapping(value = "/checkName", method = RequestMethod.POST)
	public String checkName(final Org org, final String id,
			final HttpServletRequest request)
			throws UnsupportedEncodingException {
		String json = "0";// 不存在
		final String name = URLDecoder.decode(org.getName(), "utf-8");
		org.setName(name);
		try {
			final SessionInfo info = (SessionInfo) session
					.getAttribute(HBCSoftConstant.SESSIONINFO);
			final Org orgg = orgService.queryByOrgCodeNameId(org,
					String.valueOf(info.getCompany().getId()));
			// String.valueOf(info.getCompany().getId()), id);

			if (org.getName().equals(orgg.getName())) {
				json = "1";// 存在

			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return json;
	}

	/**
	* 删除
	* 
	* @return
	*/
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String delete(final HttpServletRequest request,
			final HttpServletResponse response) {
		String ids = request.getParameter("ids");
		try {
			if (!PubTools.isEmpty(ids)) {
				ids = ids.substring(0, ids.length() - 1);
				final String[] strId = ids.split(",");
				for (int i = 0; i < strId.length; i++) {
					final String idss = strId[i];
					final Org org = orgService.queryDataByIds(idss);
					orgService.deletebyIds(org);
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
	* 分页查询
	* 
	* @param Code
	* @param Name
	* @param request
	* @param response
	*/
	@ResponseBody
	@RequestMapping(value = "/pageDictList", method = RequestMethod.POST)
	public void pageDictList(final Org org, final HttpServletRequest request,
			final HttpServletResponse response) {
		final String id = request.getParameter("parentId");
		final int pageSize = Integer.parseInt(request.getParameter("rows"));
		final int currentPage = Integer.parseInt(request.getParameter("page"));
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			final List<Org> allOrg = orgService.queryAll(org, id,
					String.valueOf(info.getCompany().getId()));
			totalNum = allOrg.size();
			if (totalNum % pageSize == 0) {
				pageTimes = totalNum / pageSize;
			} else {
				pageTimes = totalNum / pageSize + 1;
			}
			final int startRow = (currentPage - 1) * pageSize;
			final List<Org> list = orgService.queryAllOrg(org, id,
					String.valueOf(info.getCompany().getId()), startRow,
					pageSize);
			for (final Org orgList : list) {
				final Org parentOrg = orgService.getParentOrgByParentId(String
						.valueOf(orgList.getParentId()));
				orgList.setParentOrg(parentOrg);
				final User user = orgService.getUserByUserId(String
						.valueOf(orgList.getUserId()));
				orgList.setCreateUser(user);
			}

			final Pager<Org> pager = new Pager<Org>();
			pager.setTotalCount(totalNum);
			pager.setResult(list);
			final Datagrid<Org> dg = new Datagrid<Org>(pager.getTotalCount(),
					pager.getResult());
			ControllerUtils.renderText(dg);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			ControllerUtils.renderJson(Result.errorResult());
			this.getLogger().info(e);
		}
	}

	

	/**
	* 树 tree
	*/
	@RequestMapping(value = "/treegrid", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> showData() {
		List<TreeNode> treeNode = new ArrayList<TreeNode>();
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			treeNode = orgService.getOrgTreeMenu(String.valueOf(info
					.getCompany().getId()));
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return treeNode;
	}
	/**
	* 树 tree
	* 自定义标签中：点选机构
	*/
	@RequestMapping(value = "/orgTreegrid", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> getOrgTree() {
		List<TreeNode> treeNode = new ArrayList<TreeNode>();
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		final String ids = request.getParameter("ids");
		final String [] str = ids.split("[,，]");
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			treeNode = orgService.getOrgTreeMenu(String.valueOf(info
					.getCompany().getId()));
			treeNodes = getAllOrg(treeNode);
			for(final TreeNode tn : treeNodes){
				for(int i=0; i<str.length; i++){
					if(tn.getCode().toString().equals(str[i])){
						tn.setChecked(true);
						tn.setOpen(true);
						break;
					}
				}
			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return treeNode;
	}
	/**
	 * 获取所有的末尾节点
	 * @param list
	 * @return
	 */
	public List<TreeNode> getAllOrg(final List<TreeNode> list){
		for(final TreeNode tn : list){
			if(tn.getChildren().size()>0){
				tn.setOpen(true);
				getAllOrg(tn.getChildren());
			}else{
				listAll.add(tn);
			}
		}
		return listAll;
	}
	/**
	* getter and setter 方法
	* 
	* @return
	*/
	/*
	* public Org getOrg() { return org; }
	*/
	/**
	* 机构
	* 
	* @param org
	*/
	/*
	* public void setOrg(Org org) { this.org = org; }
	*/
	/**
	* 总记录
	* 
	* @return
	*/
	public int getTotalNum() {
		return totalNum;
	}

	/**
	* 总记录
	* 
	* @param totalNum
	*/
	public void setTotalNum(final int totalNum) {
		this.totalNum = totalNum;
	}

	/**
	* 父id
	* 
	* @return
	*/
	/*
	* public String getParentId() { return parentId; }
	*/

	/*
	* public void setParentId(String parentId) { this.parentId = parentId; }
	*/
	/**
	* getPageTimes
	* 
	* @return
	*/
	public int getPageTimes() {
		return pageTimes;
	}

	/**
	* 
	* @param pageTimes
	*/
	public void setPageTimes(final int pageTimes) {
		this.pageTimes = pageTimes;
	}

	// public int getId() {
	// return id;
	// }
	//
	// public void setId(int id) {
	// this.id = id;
	// }

}
