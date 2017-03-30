package com.hbcsoft.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.hbcsoft.common.model.Datagrid;
import com.hbcsoft.common.model.Pager;
import com.hbcsoft.common.model.Result;
import com.hbcsoft.common.utils.ControllerUtils;
import com.hbcsoft.common.utils.ServletUtils;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.DataDict;
import com.hbcsoft.sys.entity.DictType;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.service.DataDictQueryService;
import com.hbcsoft.sys.service.DataDictService;
import com.hbcsoft.zdy.common.BaseController;
import com.hbcsoft.zdy.util.PubTools;

/**
 * 数据字典controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/sys/dataDict")
public class DataDictController extends BaseController<DataDictController> {
	/**
	 * 数据字典service
	 */
	@Autowired
	private transient DataDictService datadictService;
	/**
	 * 字典类型 service
	 */
	// @Autowired
	// private transient DictTypeService dictTypeService;
	/**
	 * 字典类型 dataDictQueryService
	 */
	@Autowired
	private transient DataDictQueryService dataDictQueryService;
	/**
	 * 总记录数
	 */
	private int totalNum;
	/**
	 * pageTimes
	 */
	private int pageTimes;

	/**
	 * 添加、修改 弹出dataDict-input.jsp页面
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
				"sys/dataDict/dataDict-input");
		DataDict dataDict;
		final String id = request.getParameter("id");
		final ModelMap modmm = model.getModelMap();
		try {
			if (PubTools.isEmpty(id)) {// 添加
				dataDict = new DataDict();
				dataDict.setDictType(datadictService.queryDictType(request
						.getParameter("dictTypeId")));
			} else {// 修改
				dataDict = dataDictQueryService.queryDataDictById(id);// 根据主键查询，修改
				final DictType dictType = datadictService.queryDictType(String
						.valueOf(dataDict.getDtId()));
				dataDict.setDictType(dictType);
			}
			modmm.put("dataDict", dataDict);
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
	 * @param dataDict
	 * @return
	 * @throws HbcsoftException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(final HttpServletRequest request, final DataDict dataDict) {
		final String id = request.getParameter("id");
		try {
			if (PubTools.isEmpty(id)) {
				datadictService.save(dataDict, (SessionInfo) session
						.getAttribute(HBCSoftConstant.SESSIONINFO), dataDict
						.getDictType());
			} else {
				datadictService.update(dataDict, (SessionInfo) session
						.getAttribute(HBCSoftConstant.SESSIONINFO), dataDict
						.getDictType());
			}
			ControllerUtils.render(ServletUtils.TEXT_TYPE,
					Result.successResult());
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			ControllerUtils
					.render(ServletUtils.TEXT_TYPE, Result.errorResult());
			this.getLogger().info(e);
		}
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String delete(final HttpServletRequest request,
			final HttpServletResponse response) {
		try {
			datadictService.queryIds(request.getParameter("ids"));
			ControllerUtils.renderJson(Result.successResult());
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		ControllerUtils.renderJson(Result.successResult());
		return null;
	}

	/**
	 * 分页查询
	 * 
	 * @param dd
	 * @param request
	 * @param response
	 * @throws HbcsoftException
	 */
	@ResponseBody
	@RequestMapping(value = "/pageDictList", method = RequestMethod.POST)
	public void pageDictList(final DataDict dd,
			final HttpServletRequest request, final HttpServletResponse response)
			throws HbcsoftException {
		// final String id = request.getParameter("id");
		final int pageSize = Integer.parseInt(request.getParameter("rows"));
		final int currentPage = Integer.parseInt(request.getParameter("page"));
		final String dtid = request.getParameter("dtId");
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		// Result result=null;
		final List<DataDict> allDataDict = dataDictQueryService.queryAll(dd,
				dtid, String.valueOf(info.getCompany().getId()));
		totalNum = allDataDict.size();
		// int pageTimes;
		if (totalNum % pageSize == 0) {
			pageTimes = totalNum / pageSize;
		} else {
			pageTimes = totalNum / pageSize + 1;
		}
		final int startRow = (currentPage - 1) * pageSize;
		final List<DataDict> list = dataDictQueryService.queryAllDataDict(dd,
				dtid, String.valueOf(info.getCompany().getId()), startRow,
				pageSize);
		for (final DataDict dataList : list) {
			final DictType dictType = datadictService.queryDictType(String
					.valueOf(dataList.getDtId()));
			dataList.setDictType(dictType);
		}

		final Pager<DataDict> pager = new Pager<DataDict>();
		pager.setTotalCount(totalNum);
		pager.setResult(list);
		final Datagrid<DataDict> dg = new Datagrid<DataDict>(
				pager.getTotalCount(), pager.getResult());
		ControllerUtils.renderText(dg);
	}



	/**
	 * 模糊查询弹出dataDict-search.jsp页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		final ModelAndView model = new ModelAndView();
		model.setViewName("sys/dataDict/dataDict-search");
		return model;
	}

	/**
	 * 树 tree
	 */
	// @RequestMapping(value = "/treegrid", method = RequestMethod.POST)
	// @ResponseBody
	// public List<TreeNode> showData(final HttpSession session,
	// final HttpServletRequest request) {
	// List<TreeNode> treeNode = new ArrayList<TreeNode>();
	// final SessionInfo info = (SessionInfo) session
	// .getAttribute(HBCSoftConstant.SESSIONINFO);
	// try {
	// treeNode = dictTypeService.getDictTypeTreeMenu(String.valueOf(info
	// .getCompany().getId()));
	// } catch (HbcsoftException e) {
	// // TODO Auto-generated catch block
	// this.getLogger().info(e);
	// }
	// return treeNode;
	// }

	/**
	 * 判断选择的数据字典分类是否有数据字典项
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "haveDictData")
	public String haveDictData(final HttpServletRequest request) {
		Result result = null;
		String ids = request.getParameter("ids");
		boolean haveRecord = false;
		try {
			// if (ids != "" && !"".equals(ids)) {
			if (!PubTools.isEmpty(ids)) {
				ids = ids.substring(0, ids.length() - 1);
				final String[] strId = ids.split(",");
				for (int i = 0; i < strId.length; i++) {
					haveRecord = dataDictQueryService.haveRecord(ids);
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
			ControllerUtils.renderJson(Result.errorResult());
			this.getLogger().info(e);
		}
		return null;
	}

	/**
	 * 查询数据字典（下拉框）
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "getDataDictList")
	public void getDataDictList(final HttpServletRequest request) {
		final String dictCode = request.getParameter("dictCode");
		final String showType = request.getParameter("showType");
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);
		try {
			final List<DataDict> list = dataDictQueryService.getDataDictList(
					dictCode, showType,
					String.valueOf(info.getCompany().getId()));
			ControllerUtils.renderJson(list);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
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
	public String checkName(final DataDict ddpage,
			final HttpServletRequest request)
			throws UnsupportedEncodingException {
		String json = "0";// 不存在
		final String ddName = URLDecoder.decode(ddpage.getDictName(), "utf-8");
		ddpage.setDictName(ddName);
		try {
			final SessionInfo info = (SessionInfo) session
					.getAttribute(HBCSoftConstant.SESSIONINFO);
			final DataDict dd = datadictService.queryByCodeNameId(ddpage,
					String.valueOf(info.getCompany().getId()));
			// String.valueOf(info.getCompany().getId()), id);

			if (ddName.equals(dd.getDictName())) {
				json = "1";// 存在

			}
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
		}
		return json;
	}

	/**
	 * 首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dataDict")
	public String dataDicts() {
		return "/sys/dataDict/dataDict";
	}

	/**
	 * gettter、setter方法
	 * 
	 * @return
	 */
	public int getTotalNum() {
		return totalNum;
	}

	/**
	 * 总记录数
	 * 
	 * @param totalNum
	 */
	public void setTotalNum(final int totalNum) {
		this.totalNum = totalNum;
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
