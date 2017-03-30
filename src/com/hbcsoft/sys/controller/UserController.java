package com.hbcsoft.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
//import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hbcsoft.common.HBCSoftConstant;
//import com.hbcsoft.common.TreeNode;
import com.hbcsoft.common.model.Datagrid;
import com.hbcsoft.common.model.Pager;
import com.hbcsoft.common.model.Result;
import com.hbcsoft.common.utils.ControllerUtils;
import com.hbcsoft.common.utils.ServletUtils;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Org;
//import com.hbcsoft.sys.entity.Role;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.entity.User;
//import com.hbcsoft.sys.entity.UserRole;
//import com.hbcsoft.sys.service.OrgService;
//import com.hbcsoft.sys.service.RoleService;
//import com.hbcsoft.sys.service.UserRoleService;
import com.hbcsoft.sys.service.UserService;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.common.HbcsoftMD;
import com.hbcsoft.zdy.util.PubTools;
import com.hbcsoft.zdy.util.SequenceUtil;

/**
* 用户管理
* 
* @author Administrator
*
*/
@Controller
@RequestMapping(value = "/sys/user")
public class UserController extends BaseController<UserController> {
	/**
	* 用户service
	*/
	@Autowired
	private transient UserService userService;
	/**
	* 机构Service
	*/
//	@Autowired
//	private transient OrgService orgService;
//	/**
//	* 角色Service
//	*/
//	@Autowired
//	private transient RoleService roleService;
//	/**
//	* 用户角色Service
//	*/
//	@Autowired
//	private transient UserRoleService userRoleService;
	/**
	* 总记录数
	*/
	private int totalNum;
	/**
	* id
	*/
	private int id;

	/**
	 * pageTimes
	 */
	private int pageTimes;

	/**
	* 首页跳转
	* 
	* @return
	*/
	@RequestMapping(value = "/users")
	public String toUsers() {
		return "/sys/user/user";
	}

	/**
	* 模糊查询弹出user-search.jsp页面
	* 
	* @return
	*/
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		final ModelAndView model = new ModelAndView();
		model.setViewName("sys/user/user-search");
		return model;
	}

	/**
	* 添加、修改 弹出user-input.jsp页面
	* 
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value = "/input")
	public ModelAndView input(final HttpServletRequest request,
			final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView("sys/user/user-input");
		User user;
		final String id = request.getParameter("id");
		final ModelMap modmm = model.getModelMap();
		try {
			if (PubTools.isEmpty(id)) {// 添加
				user = new User();
				user.setOrg(userService.queryOrgByOrgId(request
						.getParameter("orgId")));// 所属机构
			} else {// 修改
				user = userService.queryUserById(id);// 根据主键查询，修改
				final Org org = userService.queryOrgByOrgId(String.valueOf(user
						.getOrgId()));
				user.setOrg(org);
			}
			modmm.put("user", user);
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
	* @param user
	* @return
	*/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(final HttpServletRequest request, final User user) {
		final String id = request.getParameter("id");
		// final String loginName = request.getParameter("loginName");
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			if (PubTools.isEmpty(id)) {// 添加
				final Long fromid = SequenceUtil.getTableId("F_FORMNAME");
				final Long userId = info.getUser().getId();
				user.setId(fromid);
				user.setCompanyId(info.getCompany().getId());
				user.setCreateID(userId);// 创建人id
				user.setCreateTime(new Date());// 创建时间
				user.setUpdateID(userId);// 修改人id
				user.setUpdateTime(new Date());
				user.setOrgId(user.getOrg().getId());// 所属机构
				user.setPassword(HbcsoftMD.md5("123456"));// 默认密码为123456
				user.setCssPath("blue");//默认蓝色
				userService.save(user);
			} else {// 修改
				final Long userId = info.getUser().getId();
				user.setCompanyId(info.getCompany().getId());
				user.setUpdateID(userId);
				user.setUpdateTime(new Date());
				user.setOrgId(user.getOrg().getId());// 所属机构
				userService.update(user);
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
	* 判断名称是否重复
	* 
	* @param dtCode
	* @return
	* @throws UnsupportedEncodingException
	*/
	@ResponseBody
	@RequestMapping(value = "/checkName", method = RequestMethod.POST)
	public String checkName(final String loginName, final String id,
			final HttpServletRequest request)
			throws UnsupportedEncodingException {
		String json = "0";// 不存在
		final String loginNames = URLDecoder.decode(loginName, "utf-8");
		try {
			final SessionInfo info = (SessionInfo) session
					.getAttribute(HBCSoftConstant.SESSIONINFO);
			final User user = userService.queryByUserLoginNameId(loginNames,
					String.valueOf(info.getCompany().getId()), id);

			if (loginNames.equals(user.getLoginName())) {
				json = "1";// 存在

			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return json;
	}

	/**
	* 分页
	* 
	* @param user
	* @param request
	* @param response
	* @throws HbcsoftException
	*/
	@ResponseBody
	@RequestMapping(value = "/pageDictList", method = RequestMethod.POST)
	public void pageDictList(final User user, final HttpServletRequest request,
			final HttpServletResponse response) throws HbcsoftException {
		final int pageSize = Integer.parseInt(request.getParameter("rows"));
		final int currentPage = Integer.parseInt(request.getParameter("page"));
		final String orgId = request.getParameter("orgId");
		final SessionInfo info = (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final List<User> allUser = userService.queryAll(user, orgId,
				String.valueOf(info.getCompany().getId()));
		totalNum = allUser.size();
		if (totalNum % pageSize == 0) {
			pageTimes = totalNum / pageSize;
		} else {
			pageTimes = totalNum / pageSize + 1;
		}
		final int startRow = (currentPage - 1) * pageSize;
		final List<User> list = userService.queryAllUser(user, orgId,
				String.valueOf(info.getCompany().getId()), startRow, pageSize);
		for (final User userList : list) {
			final Org org = userService.queryOrgByOrgId(String.valueOf(userList
					.getOrgId()));
			userList.setOrg(org);
		}

		final Pager<User> pager = new Pager<User>();
		pager.setTotalCount(totalNum);
		pager.setResult(list);
		final Datagrid<User> dg = new Datagrid<User>(pager.getTotalCount(),
				pager.getResult());
		ControllerUtils.renderText(dg);
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
					final User user = userService.queryUserDataByIds(idss);
					userService.deletebyIds(user);
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
	* 判断选择的机构分类是否有用户项
	* 
	* @param request
	* @return
	*/
	@ResponseBody
	@RequestMapping(value = "haveUser")
	public String haveUser(final HttpServletRequest request) {
		Result result = null;
		String ids = request.getParameter("ids");
		boolean haveRecord = false;
		try {
			// if (ids != "" && !"".equals(ids)) {
			if (!PubTools.isEmpty(ids)) {
				ids = ids.substring(0, ids.length() - 1);
				final String[] strId = ids.split(",");
				for (int i = 0; i < strId.length; i++) {
					haveRecord = userService.haveRecord(ids);
				}
			}
			if (haveRecord) {
				result = Result.successResult();// 存在
			} else {
				result = new Result(2, "", null);// 不存在
			}
			ControllerUtils.renderJson(result);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			ControllerUtils.renderText(Result.errorResult());
			this.getLogger().info(e);
		}
		return null;
	}

	

	/**
	* 设置角色
	* 
	* @param request
	* @param response
	* @return
	*/
	@RequestMapping(value = "/role")
	public ModelAndView role(final HttpServletRequest request,
			final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView("sys/user/user-role");
		final ModelMap modmm = model.getModelMap();
		modmm.put("userId", request.getParameter("id"));
		return model;
	}

	/**
	* 自定义标签中：点选人员多选
	*/
	@RequestMapping(value = "/checkUsers", method = RequestMethod.GET)
	public ModelAndView getUsers() {
		ModelAndView mav = new ModelAndView("/sys/user/user-menuCheck");
		String ids = request.getParameter("ids");
		mav.getModel().put("ids", ids);
		return mav;
	}
	
	/**
	* 人员点选——分页查询
	* @param user
	* @param request
	* @param response
	* @throws HbcsoftException
	*/
	@ResponseBody
	@RequestMapping(value = "/checkUsersList", method = RequestMethod.POST)
	public void checkUsers(final HttpServletRequest request,
			final HttpServletResponse response) throws HbcsoftException {
		final int pageSize = Integer.parseInt(request.getParameter("rows"));
		final int currentPage = Integer.parseInt(request.getParameter("page"));
		final SessionInfo info = (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		final List<User> allUser = userService.findAllUsers(info.getCompany().getId());
		totalNum = allUser.size();
		if (totalNum % pageSize == 0) {
			pageTimes = totalNum / pageSize;
		} else {
			pageTimes = totalNum / pageSize + 1;
		}
		final int startRow = (currentPage - 1) * pageSize;
		final List<User> list = userService.checkUser(info.getCompany().getId(), startRow, pageSize);
		final Pager<User> pager = new Pager<User>();
		pager.setTotalCount(totalNum);
		pager.setResult(list);
		final Datagrid<User> dg = new Datagrid<User>(pager.getTotalCount(), pager.getResult());
		ControllerUtils.renderText(dg);
	}

	/**
	* setter and getter 方法
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
	* id
	* 
	* @return
	*/
	public int getId() {
		return id;
	}

	/**
	* id
	* 
	* @param id
	*/
	public void setId(final int id) {
		this.id = id;
	}

	/**
	* pagetimes
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

}
