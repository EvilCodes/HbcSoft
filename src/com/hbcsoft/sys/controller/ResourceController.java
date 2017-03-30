package com.hbcsoft.sys.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.hbcsoft.common.HBCSoftConstant;
import com.hbcsoft.common.TreeNode;
import com.hbcsoft.common.model.*;
import com.hbcsoft.common.utils.ControllerUtils;
import com.hbcsoft.common.utils.ServletUtils;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Resource;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.service.ResourceService;
import com.hbcsoft.sys.service.ResourceService2;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.util.PubTools;
import com.hbcsoft.zdy.util.SequenceUtil;
/**
 * 资源管理控制层
 * */
@Controller
@RequestMapping(value = "/sys/resource")
public class ResourceController extends BaseController<Resource> {
	/**
	 * 注入菜单service
	 * */
	@Autowired
	private transient ResourceService resourceService;
	
	/**
	 * 注入CRUD service
	 * */
	@Autowired
	private transient ResourceService2 resourceService2;
	
	/**
	 * 总记录数
	 * */
	private int totalNum;
	
	/**
	 * sessionInfoMap
	 * */
	public static final Map<Long,Resource> sessionInfoMap = new ConcurrentHashMap<Long, Resource>();
	
	/**
	 * 父资源
	 * */
	private Long parentResource;
	
	/**
	 * id
	 * */
	private Long id;
	/**
	 * id-get
	 * */
	public Long getId() {
		return id;
	}
	/**
	 * id-set
	 * */
	public void setId(final Long id) {
		this.id = id;
	}
	/**
	 * 父资源get
	 * */
	public Long getParentResource() {
		return parentResource;
	}
	/**
	 * 父资源set
	 * */
	public void setParentResource(final Long parentResource) {
		this.parentResource = parentResource;
	}
	/**
	 * 总记录数getter
	 * */
	public int getTotalNum() {
		return totalNum;
	}
	/**
	 * 总记录数setter
	 * */
	public void setTotalNum(final int totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * 树 tree
	 * @throws HbcsoftException 
	 */
	@RequestMapping(value = "/srcTreeGrid", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> showData() throws HbcsoftException, IOException{
		List<TreeNode> treeNode = new ArrayList<TreeNode>();
		final SessionInfo info= (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			treeNode = resourceService.getResourceTreeMenu(String.valueOf(info.getCompany().getId()));
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		return treeNode;
	}

	/**
	 * 点选
	 */
	@RequestMapping(value = "/resourceMenu")
	public String resMenu() throws HbcsoftException {
		return "/sys/resource/resource-menu";
	}
	/**
	 * 首页跳转
	 */
	@RequestMapping(value = "/resources")
	public String resources() throws HbcsoftException {
		return "/sys/resource/resource";
	}
	
	/**
	 * 分页查询
	 * @param currentPage
	 * @throws IOException 
	 */
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/pageResourceList", method = RequestMethod.POST)
	public void pageResourceList(final Resource resource,final HttpServletRequest request,final HttpServletResponse response)
			throws HbcsoftException, IllegalAccessException, InstantiationException, IOException {
		final String id = request.getParameter("id");
		final int pageSize = Integer.parseInt(request.getParameter("rows"));
		final int currentPage = Integer.parseInt(request.getParameter("page"));
		final SessionInfo  info= (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		Result result = null;
		try {
			final List<Resource> resources = resourceService2.queryAll(resource, id,String.valueOf(info.getCompany().getId()), request);
			totalNum = resources.size();
			int pageTimes;
			if (totalNum % pageSize == 0) {
				pageTimes = totalNum / pageSize;
			} else {
				pageTimes = totalNum / pageSize + 1;
			}
			final int startRow = (currentPage - 1) * pageSize;
			final List<Resource> list = resourceService2.queryAllResourceType(resource, id,
					String.valueOf(info.getCompany().getId()), startRow, pageSize, request);
			
			final Pager<Resource> page = new Pager<Resource>();
			page.setTotalCount(totalNum);
			page.setResult(list);
			this.getLogger().info("page.getTotalCount():" + page.getTotalCount() + "page.getResult():" + page.getResult());
			final Datagrid<Resource> dg = new Datagrid<Resource>(page.getTotalCount(), page.getResult());
			ControllerUtils.renderText(dg);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
			result = Result.errorResult();
			ControllerUtils.renderJson(result);
			throw e;
		}
	}
	/**
	 * 模糊查询弹出resource-search.jsp页面
	 */
	@RequestMapping(value = "/resourceSearch", method = RequestMethod.GET)
	public ModelAndView search() throws HbcsoftException{
		final ModelAndView model = new ModelAndView();
		model.setViewName("sys/resource/resource-search");
		return model;
	}
	 /**
	* 添加、修改  弹出页面
	* @param request
	* @param response
	* @throws Exception
	*/
	@RequestMapping(value = "/resourceInput")
	public ModelAndView input(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException {
		final ModelAndView model = new ModelAndView("sys/resource/resource-input");
		Resource resource = null;
		final String id = request.getParameter("id");
		final String parentResourceId = request.getParameter("parentResourceId");
		try {
			if (PubTools.isEmpty(id)) {//添加
				resource = new Resource();
				resource.setEnable(0);
				final Resource parentResource= resourceService2.queryIdAndName(parentResourceId);
				resource.setParentResource(parentResource);
			} else {// 修改
				resource = resourceService2.queryIdAndName(id);
				final Long pid = resource.getParentResourceId();
				final String spid = String.valueOf(pid);
				Resource res = new Resource();
				res = resourceService2.queryIdAndName(spid);
				final String parentName = res.getName();
				resource.setParentResource(res);
				model.getModelMap().put("parentName", parentName);
			}
			 model.getModelMap().put("resource", resource);
		} catch (HbcsoftException e) {
			this.getLogger().info(e);
		}
		return model;	
	}
	/**
	* 保存
	* @param request
	* @param resource
	* @throws InstantiationException 
	* @throws IllegalAccessException 
	* @throws HbcsoftException
	*/
	@RequestMapping(value = "/resourceSave",method=RequestMethod.POST)
	@ResponseBody
	public String save(final HttpServletRequest request,final Resource resource) throws HbcsoftException, IllegalAccessException, InstantiationException{
		Result result=null;	
		try{
			final SessionInfo  info= (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
			final String id=request.getParameter("id");
			final String gId = request.getParameter("groupId");
			if(PubTools.isEmpty(id)){//添加
					final Long fromid = SequenceUtil.getTableId("F_FORMNAME");
					resource.setId(fromid);
					resource.setGroupId("1");
					resource.setCompanyId(info.getCompany().getId());
					resource.setCreateID(info.getUser().getCreateID());
					resource.setCreateTime(new Date());
					resource.setUpdateID(info.getUser().getUpdateID());
					resource.setUpdateTime(new Date());
					resource.setParentResourceId(resource.getParentResource().getId());
					resourceService2.addResource(resource);
				}else{//修改
					if("0".equals(gId)){
						result = new Result(Result.WARN,"系统自带资源，不允许修改!","groupId");
						this.getLogger().debug(result.toString());
						ControllerUtils.render(ServletUtils.HTML_TYPE,result.toString());
						return null;
					}else{
						resource.setUpdateID(info.getUser().getUpdateID());
						resource.setUpdateTime(new Date());
						resource.setCompanyId(info.getCompany().getId());
						resource.setParentResourceId(resource.getParentResource().getId());//把上级资源的id赋值给子资源，更改资源类型
						resourceService2.update(resource);
					}
				}
			result = Result.successResult();
			this.getLogger().debug(result.toString());
			ControllerUtils.render(ServletUtils.TEXT_TYPE,result.toString());
		}catch(HbcsoftException e){
			result = Result.errorResult();
			ControllerUtils.render(ServletUtils.HTML_TYPE,result.toString());
			throw e;
		}
		return null;
	}
	/**
	 * 新增时判断编码名称是否重复
	 * @param resourceCode
	 * @throws UnsupportedEncodingException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 */
	@ResponseBody
	@RequestMapping(value = "/checkCodeName", method = RequestMethod.POST)
	public String checkResourceCode(final Resource resource,final HttpServletRequest request) throws UnsupportedEncodingException {
		final String code = URLDecoder.decode(resource.getCode(), "utf-8");
		final String name = URLDecoder.decode(resource.getName(), "utf-8");
		resource.setCode(code);
		resource.setName(name);
		final String id=request.getParameter("id");
		String json = "0";// 不存在
		if(PubTools.isEmpty(id)){//添加的时候判断编码名称是否重复
			final SessionInfo  info= (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
			try {
				final Resource res = resourceService2.queryByCodeName(resource,String.valueOf(info.getCompany().getId()));
				if (code.equals(res.getCode())||name.equals(res.getName())) {
					json = "1";// 存在
				}
			} catch (HbcsoftException e) {
				this.getLogger().info(e);
			}
		}
		return json;
	}
	 /**
	 * 删除
	 */
	@RequestMapping(value = "/deleteResource",method=RequestMethod.POST)
	public String delete(final HttpServletRequest request,final HttpServletResponse response) throws HbcsoftException{
		Resource res = new Resource();
		Result result=null;
		final SessionInfo  info= (SessionInfo) session.getAttribute(HBCSoftConstant.SESSIONINFO);
		String ids = request.getParameter("ids");
		try {
			if(!PubTools.isEmpty(ids)){
				ids = ids.substring(0, ids.length()-1);
				final String [] strId = ids.split(",");
				for (int i = 0; i < strId.length; i++) {
					final String idss = strId[i];
					res = resourceService2.queryResourceByIds(idss, String.valueOf(info.getCompany().getId()));
					final String gId = res.getGroupId();
					if("0".equals(gId)){
						result = new Result(Result.WARN,"系统自带资源，不允许删除!","groupId");
						this.getLogger().debug(result.toString());
						ControllerUtils.render(ServletUtils.HTML_TYPE,result.toString());
						return null;
					}else {
						resourceService2.deletebyIds(res);
					}
				}
			}
			ControllerUtils.renderJson( Result.successResult());
		} catch (HbcsoftException e) {
			ControllerUtils.renderJson(Result.errorResult());
			throw e;
		}
		return null;
	}
	
}
