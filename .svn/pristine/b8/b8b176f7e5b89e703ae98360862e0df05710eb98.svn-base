package com.hbcsoft.sys.controller;

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
import com.hbcsoft.common.model.Datagrid;
import com.hbcsoft.common.model.Pager;
import com.hbcsoft.common.model.Result;
import com.hbcsoft.common.utils.ControllerUtils;
import com.hbcsoft.exception.HbcsoftException;
import com.hbcsoft.sys.entity.Log;
import com.hbcsoft.sys.entity.SessionInfo;
import com.hbcsoft.sys.service.LogService;
import com.hbcsoft.zdy.common.BaseController;

/**
* LogController
* 
* @author Administrator
*
*/
@Controller
@RequestMapping(value = "/sys/log")
public class LogController extends BaseController<LogController> {
	/**
	* logService层
	*/
	@Autowired
	private transient LogService logService;
	/**
	* 日志
	*/
	//private Log log;
	/**
	* 总记录
	*/
	private int totalNum;
	/**
	* pageTimes
	*/
	private int pageTimes;

	/**
	* 分页查询
	* 
	* @param type
	* @param loginName
	* @param request
	* @param response
	*/
	@ResponseBody
	@RequestMapping(value = "/pageDictList", method = RequestMethod.POST)
	public void pageDictList(final String type, final String loginName,
			final HttpServletRequest request, final HttpServletResponse response)
			throws HbcsoftException {
		// String id = request.getParameter("id");
		final int pageSize = Integer.parseInt(request.getParameter("rows"));
		final int currentPage = Integer.parseInt(request.getParameter("page"));
		final SessionInfo info = (SessionInfo) session
				.getAttribute(HBCSoftConstant.SESSIONINFO);

		try {
			final List<Log> allLog = logService.queryAllCount(type, loginName,
					String.valueOf(info.getCompany().getId()));
			totalNum = allLog.size();
			// int pageTimes;
			if (totalNum % pageSize == 0) {
				pageTimes = totalNum / pageSize;
			} else {
				pageTimes = totalNum / pageSize + 1;
			}
			final int startRow = (currentPage - 1) * pageSize;
			final List<Log> list = logService.queryAllLog(type, loginName,
					String.valueOf(info.getCompany().getId()), startRow,
					pageSize);
			final Pager<Log> pager = new Pager<Log>();
			pager.setTotalCount(totalNum);
			pager.setResult(list);
			final Datagrid<Log> dg = new Datagrid<Log>(pager.getTotalCount(),
					pager.getResult());
			ControllerUtils.renderText(dg);
		} catch (HbcsoftException e) {
			// TODO Auto-generated catch block
			this.getLogger().info(e);
			ControllerUtils.renderJson(Result.errorResult());
			throw e;
		}
	}


	/**
	* 模糊查询弹出log-search.jsp页面
	* 
	* @return
	*/

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		final ModelAndView model = new ModelAndView();
		model.setViewName("sys/log/log-search");
		return model;
	}

	/**
	* 首页跳转
	* 
	* @return
	*/
	@RequestMapping(value = "/log")
	public String dictTypes() {
		return "/sys/log/log";
	}

	/**
	 * getter and setter
	 */
//	public Log getLog() {
//		return log;
//	}

	/**
	* 日志
	* 
	* @param log
	*/
//	public void setLog(final Log log) {
//		this.log = log;
//	}

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
