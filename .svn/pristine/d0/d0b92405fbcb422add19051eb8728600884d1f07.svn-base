package com.hbcsoft.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.common.TreeNode;
import com.hbcsoft.common.model.Result;
import com.hbcsoft.common.utils.ControllerUtils;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Role;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.entity.User;
import com.hbcsoft.sys.entity.UserRole;
import com.hbcsoft.sys.service.OrgService;
import com.hbcsoft.sys.service.RoleService;
import com.hbcsoft.sys.service.UserRoleService;
import com.hbcsoft.sys.service.UserService;
import com.hbcsoft.zdy.common.BaseController;
/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/sys/user")
public class UserRoleController extends BaseController<UserRoleController>{
	/**
	* 用户service
	*/
	@Autowired
	private transient UserService userService;
	/**
	* 机构Service
	*/
	@Autowired
	private transient OrgService orgService;
	/**
	* 角色Service
	*/
	@Autowired
	private transient RoleService roleService;
	/**
	* 用户角色Service
	*/
	@Autowired
	private transient UserRoleService userRoleService;
	/**
	* 树 tree
	*/
	@RequestMapping(value = "/treegrid", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> showData() {
		List<TreeNode> treeNode = new ArrayList<TreeNode>();
		final SessionInfo info = (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
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
	* 角色树
	* 
	* @param session
	* @param request
	* @return
	*/
	@RequestMapping(value = "/roleTreegrid", method = RequestMethod.POST)
	@ResponseBody
	public void roleTreegrid(final HttpSession session,
			final HttpServletRequest request) {
		final String id = request.getParameter("id");
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		final SessionInfo info = (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			final User user = userService.queryUserById(id,
					String.valueOf(info.getCompany().getId()));
			if (user != null) {
				final List<Role> roleList = roleService.queryRoleListbyUserId(
						String.valueOf(user.getId()),
						String.valueOf(info.getCompany().getId()));// 根据用户id查询所拥有的角色列表

				treeNodes = roleService.findRoleMenu(String.valueOf(info
						.getCompany().getId()));
				for (final TreeNode treeNode : treeNodes) {
					treeNode.setChecked(false);
					//if (roleList != null && roleList.size() > 0) {
					if (roleList != null) {
						if ("-1".equals(String.valueOf(treeNode.getId()))) {
							treeNode.setChecked(true);
						}
						for (final Role oRole : roleList) {
							if (treeNode.getId().equals(oRole.getId())) {
								treeNode.setChecked(true);
								treeNode.setOpen(true);
								break;
							}
						}
					}
				}
			}
			ControllerUtils.getRequest().removeAttribute("userId");
			ControllerUtils.renderJson(treeNodes);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
	}

	/**
	* 修改角色
	*/
	@RequestMapping(value = "/updateUserRole", method = RequestMethod.POST)
	public void updateUserRole(final HttpSession session,
			final HttpServletRequest request) {
		final String id = request.getParameter("id");// 用户id
		String ids = request.getParameter("ids");//
		final SessionInfo info = (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		User user = new User();
		final UserRole userRole= new UserRole();
		try {
			if (null != id && !"".equals(id)) {
				user = userService.getUser(id);// user 用户
			}
			// 根据用户id删除
			userRoleService.deleteByUserId(id);
			if ((ids.length() - 1) > 0) {
				ids = ids.substring(0, ids.length() - 1);// [-1,1]
				final String[] strId = ids.split(",");
				for (final String roleId : strId) {
					//if (!roleId.equals("-1")) {
						if("-1".equals(roleId)){
//						final Role role = roleService.queryRolesById(roleId,
//								String.valueOf(info.getCompany().getId()));// role
//						//userRole = new UserRole();
//						userRole.setRoleId(role.getId());
//						userRole.setUserId(user.getId());
//						userRoleService.addUserRole(userRole);// 增加
					}else{
						final Role role = roleService.queryRolesById(roleId,
								String.valueOf(info.getCompany().getId()));// role
						//userRole = new UserRole();
						userRole.setRoleId(role.getId());
						userRole.setUserId(user.getId());
						userRoleService.addUserRole(userRole);// 增加
					}

				}
			}
			ControllerUtils.renderText(Result.successResult());
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}

	}
}
