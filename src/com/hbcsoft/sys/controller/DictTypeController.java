package com.hbcsoft.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import com.hbcsoft.sys.entity.DictType;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.service.DictTypeQueryService;
import com.hbcsoft.sys.service.DictTypeService;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.util.PubTools;

/**
 * 数据字典 controller层
 * 
 * @author Administrator
 *
 */
@Controller
// 负责注册一个bean 到spring 上下文中
@RequestMapping(value = "/sys/dictType")
public class DictTypeController extends BaseController<DictTypeController> {
	/**
	 * 字典类型 service
	 */
	@Autowired
	private transient DictTypeService dictTypeService;
	/**
	 * 字典类型 分页查询
	 */
	@Autowired
	private transient DictTypeQueryService dictTypeQueryService;
	/**
	 * 字典类型
	 */
	private DictType dictType;
	/**
	 * 总记录数
	 */
	private int totalNum;
	/**
	 * id
	 */
	private int id;
	/**
	 * 父节点
	 */
	private String parentId;
	/**
	* 
	*/
	private int pageTimes;

	/**
	 * 添加、修改 弹出dictType-input.jsp页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/input")
	public ModelAndView input(final HttpServletRequest request,
			final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView(
				"sys/dictType/dictType-input");
		DictType dictType;
		final ModelMap modmm = model.getModelMap();
		try {
			if (PubTools.isEmpty(request.getParameter("id"))) {
				// 添加
				dictType = new DictType();
				final DictType parentDictType = dictTypeService
						.queryIdAndDtname(request.getParameter("parentId"));
				dictType.setParentDictType(parentDictType);
			} else {
				dictType = dictTypeService.queryDictTypeById(request
						.getParameter("id"));// 根据主键查询，修改
				final String pd = String.valueOf(dictType.getParentId());
				final DictType parentDictType = dictTypeService
						.queryIdAndDtname(pd);
				dictType.setParentDictType(parentDictType);
			}
			modmm.put("dictType", dictType);
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
	 * @throws Exception
	 */

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(final HttpServletRequest request, final DictType dictType) {
		final String id = request.getParameter("id");
		try {
			if (PubTools.isEmpty(id)) {// 添加
				dictTypeQueryService.save(dictType, (SessionInfo) session
						.getAttribute(HBCSoftConstant.SESSIONINFO));
			} else {// 修改
				dictTypeQueryService.update(dictType, (SessionInfo) session
						.getAttribute(HBCSoftConstant.SESSIONINFO));
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
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteDictType", method = RequestMethod.POST)
	public String delete(final HttpServletRequest request,
			final HttpServletResponse response) {
		try {
			dictTypeService.queryIds(request.getParameter("ids"));
			ControllerUtils.renderJson(Result.successResult());
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			ControllerUtils.renderJson(Result.errorResult());
			this.getLogger().info(e);
		}
		return null;
	}

	/**
	 * 模糊查询弹出dictType-search.jsp页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		final ModelAndView model = new ModelAndView();
		model.setViewName("sys/dictType/dictType-search");
		return model;
	}

	/**
	 * 分页查询字典类型
	 * 
	 * @param dtCode
	 * @param dtName
	 * @param currentPage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pageDictList", method = RequestMethod.POST)
	/* public void pageDictList(final String dtCode, final String dtName, */
	public void pageDictList(final DictType dictType,
			final HttpServletRequest request, final HttpServletResponse response) {
		final String id = request.getParameter("id");
		final int pageSize = Integer.parseInt(request.getParameter("rows"));
		final int currentPage = Integer.parseInt(request.getParameter("page"));
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {

			/*
			 * final List<DictType> allDictType = dictTypeQueryService.queryAll(
			 * dtCode, dtName, id,String.valueOf(info.getCompany().getId()));
			 */
			final List<DictType> allDictType = dictTypeQueryService.queryAll(
					dictType, id, String.valueOf(info.getCompany().getId()));// String.valueOf(info.getCompany().getId())
			totalNum = allDictType.size();
			if (totalNum % pageSize == 0) {
				pageTimes = totalNum / pageSize;
			} else {
				pageTimes = totalNum / pageSize + 1;
			}
			final int startRow = (currentPage - 1) * pageSize;
			/*
			 * final List<DictType> list =
			 * dictTypeQueryService.queryAllDictType( dtCode, dtName,
			 * id,String.valueOf(info.getCompany().getId()), startRow,
			 * pageSize);
			 */
			final List<DictType> list = dictTypeQueryService.queryAllDictType(
					dictType, id, String.valueOf(info.getCompany().getId()),
					startRow, pageSize);
			final Pager<DictType> pager = new Pager<DictType>();
			pager.setTotalCount(totalNum);
			pager.setResult(list);
			final Datagrid<DictType> dg = new Datagrid<DictType>(
					pager.getTotalCount(), pager.getResult());
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
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		List<TreeNode> treeNode = new ArrayList<TreeNode>();
		try {
			treeNode = dictTypeService.getDictTypeTreeMenu(String.valueOf(info
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
	@RequestMapping(value = "/dictTypes")
	public String dictTypes() {
		return "/sys/dictType/dictType";
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
	public String checkCode(final DictType dtpage,
			final HttpServletRequest request)
			throws UnsupportedEncodingException {
		final String dtCode = URLDecoder.decode(dtpage.getDtCode(), "utf-8");
		dtpage.setDtCode(dtCode);
		String json = "0";// 不存在
		try {
			final SessionInfo info = (SessionInfo) session
					.getAttribute(HBCSoftConstant.SESSIONINFO);
			final DictType dt = dictTypeQueryService.queryByCodeNameId(dtpage,
					String.valueOf(info.getCompany().getId()));
			// String.valueOf(info.getCompany().getId()), id);

			if (dtCode.equals(dt.getDtCode())) {
				json = "1";// 存在
			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return json;
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
	public String checkName(final DictType dtpage, final String id,
			final HttpServletRequest request)
			throws UnsupportedEncodingException {
		String json = "0";// 不存在
		final String dtName = URLDecoder.decode(dtpage.getDtName(), "utf-8");
		dtpage.setDtName(dtName);
		try {
			final SessionInfo info = (SessionInfo) session
					.getAttribute(HBCSoftConstant.SESSIONINFO);
			final DictType dt = dictTypeQueryService.queryByCodeNameId(dtpage,
					String.valueOf(info.getCompany().getId()));
			// String.valueOf(info.getCompany().getId()), id);

			if (dtName.equals(dt.getDtName())) {
				json = "1";// 存在

			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return json;
	}

	// private String getDtCode(final DictType dt) {
	// return dt.getDtCode();
	// }

	/**
	 * getter and setter 方法
	 */

	/**
	 * 父节点的getter 方法
	 * 
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 父节点setter方法
	 * 
	 * @param parentId
	 */
	public void setParentId(final String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 字典类型getter方法
	 * 
	 * @return
	 */
	public DictType getDictType() {
		return dictType;
	}

	/**
	 * 字典类型setter 方法
	 * 
	 * @param dictType
	 */
	public void setDictType(final DictType dictType) {
		this.dictType = dictType;
	}

	/**
	 * 总记录 getter方法
	 * 
	 * @return
	 */
	public int getTotalNum() {
		return totalNum;
	}

	/**
	 * 总记录 setter 方法
	 * 
	 * @param totalNum
	 */
	public void setTotalNum(final int totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * id getter 方法
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * id setter 方法
	 * 
	 * @param id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * pageTimes
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
