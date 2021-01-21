package cn.zlin.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.zlin.demo.config.Log;
import cn.zlin.demo.dao.UserMapper;
import cn.zlin.demo.domain.*;
import cn.zlin.demo.service.AdminService;
import cn.zlin.demo.service.ComputerInfoService;
import cn.zlin.demo.service.DictionaryService;
import cn.zlin.demo.util.Constant;
import cn.zlin.demo.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**   
 * @Description: 电脑参数表模块控制器
 * @Author: linTH
 * @Date: 10-08 09:13:24
 */ 
@Controller
@RequestMapping("/computerInfo")
public class ComputerInfoController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ComputerInfoService computerInfoService; //注入电脑参数表模块服务
	@Autowired
	private DictionaryService dictionaryService;

	/**
	 * 列表页面
	 */
	@RequestMapping("/index")
	public Object index(HttpServletRequest request, HttpSession session, ModelMap map) {

		//获取登录用户的信息
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		//获取电脑状态
		List<Dictionary> dictionary = dictionaryService.getDictionaryByCode(Constant.ZHUANGTAI);
		//包邮
		List<Dictionary> baoyou = dictionaryService.getDictionaryByCode(Constant.BAOYOU);
		//品牌
		List<Dictionary> diannaopinpai = dictionaryService.getDictionaryByCode(Constant.PINPAI);
		map.addAttribute("dictionary", dictionary);
		map.addAttribute("baoyou", baoyou);
		map.addAttribute("diannaopinpai", diannaopinpai);
		map.addAttribute("userid", user.getUserId());
		return "computerPage/listComputer";
	}


	/**
	 * 列表页面boss
	 */
	@RequestMapping("/bossIndex")
	public Object bossIndex(HttpServletRequest request, HttpSession session, ModelMap map) {
		//获取电脑状态
		List<Dictionary> dictionary = dictionaryService.getDictionaryByCode(Constant.ZHUANGTAI);
		//包邮
		List<Dictionary> baoyou = dictionaryService.getDictionaryByCode(Constant.BAOYOU);
		//品牌
		List<Dictionary> diannaopinpai = dictionaryService.getDictionaryByCode(Constant.PINPAI);
		map.addAttribute("dictionary", dictionary);
		map.addAttribute("baoyou", baoyou);
		map.addAttribute("diannaopinpai", diannaopinpai);
		return "adminPage/listComputer";
	}

	/**
	 * 编辑页面
	 */
	@RequestMapping("/edit")
	public Object getEdit(HttpServletRequest request, HttpSession session, ModelMap map, Long id) {
		//包邮
		List<Dictionary> baoyou = dictionaryService.getDictionaryByCode(Constant.BAOYOU);
		//品牌
		List<Dictionary> diannaopinpai = dictionaryService.getDictionaryByCode(Constant.PINPAI);
		ComputerInfo computerInfo = computerInfoService.searchById(id);
		map.addAttribute("diannaopinpai", diannaopinpai);
		map.addAttribute("baoyou", baoyou);
		map.put("bo", computerInfo);
		return "computerPage/editComputer";
	}

	/**
	 * 编辑页面
	 */
	@RequestMapping("/editSolePrice")
	public Object getEditSolePrice(HttpServletRequest request, HttpSession session, ModelMap map, Long id, Long price) {
		map.put("conId", id);
		map.put("comBugPrice", price);
		return "adminPage/editPrice";
	}

	/**
	 * 列表数据
	 */
	@ResponseBody
	@RequestMapping("/listData")
	public Object listData(HttpServletRequest request, HttpSession session, ComputerInfo bo, Long userid) {
		if (userid != null) {
			bo.setCreateUser(userid);
		}
		//去除空格
		if(bo.getConXinghao()!=null||"".equals(bo.getConXinghao())){
			bo.setConXinghao(bo.getConXinghao().trim());
		}
		//去除空格
		if(bo.getCreateUserName()!=null||"".equals(bo.getCreateUserName())){
			bo.setCreateUserName(bo.getCreateUserName().trim());
		}
		EUDGPagination pagination = computerInfoService.searchList(bo);
		return pagination;
	}


	/**
	 * 表单页面
	 */
	@RequestMapping("/form")
	public Object form(HttpServletRequest request, HttpSession session, ModelMap map) {
		//包邮
		List<Dictionary> baoyou = dictionaryService.getDictionaryByCode(Constant.BAOYOU);
		//品牌
		List<Dictionary> diannaopinpai = dictionaryService.getDictionaryByCode(Constant.PINPAI);
		map.addAttribute("baoyou", baoyou);
		map.addAttribute("diannaopinpai", diannaopinpai);
		return "computerPage/addComputer";
	}

	/**
	 * 保存数据
	 */
	@ResponseBody
	@RequestMapping("/save")
	@Log("添加/修改电脑")
	public Object save(HttpServletRequest request, HttpSession session, ModelMap map,
					   ComputerInfo bo) {
		//获取登录用户的信息
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = "fail";
		if (bo.getConId() == null) { //新增
			bo.setCreateUser(user.getUserId());
			bo.setUpdateUser(user.getUserId());
			bo.setCreateUserName(user.getUserName());
			Long id = computerInfoService.insert(bo);
			if (id != null && id > 0L) {
				result = "success";
			}
			;
		} else { //修改
			bo.setUpdateUser(user.getUserId());
			boolean updateResult = false;
			if ("4".equals(bo.getComZhuangTai())) {
				updateResult = computerInfoService.updateKnockdownDate(bo);
			} else {
				updateResult = computerInfoService.update(bo);
			}

			if (updateResult) {
				result = "success";
			}
		}
		resultMap.put("result", result);
		return resultMap;
	}

	/***
	 * 统计首页
	 */

	@RequestMapping("/tongji")
	public Object getTongJi(HttpServletRequest request, HttpSession session, ModelMap map) {
		//获取电脑参数
		Long Income = computerInfoService.getIncome("1", null);
		if (Income == null) {
			Income = 0l;
		}
		Long NumCom = computerInfoService.getNumCom("1", null);
		Long SpendMoney = computerInfoService.getSpendMoney("1", null);
		if (SpendMoney == null) {
			SpendMoney = 0l;
		}
		List<TongJiData> list = computerInfoService.getTongJiDate("1");
		Long ScrapCom = computerInfoService.getScrapCom("1");
		map.put("Income", Income);
		map.put("NumCom", NumCom);
		map.put("SpendMoney", SpendMoney);
		map.put("ScrapCom", ScrapCom);
		map.put("list", list);
		return "adminPage/yuangongIndex";

	}

	@ResponseBody
	@RequestMapping("/dayStyle")
	public Object getTongJiByDayStyle(HttpServletRequest request, HttpSession session, String dayStyle) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//获取电脑参数
		Long Income = computerInfoService.getIncome(dayStyle, null);
		if (Income == null) {
			Income = 0l;
		}
		Long NumCom = computerInfoService.getNumCom(dayStyle, null);
		Long SpendMoney = computerInfoService.getSpendMoney(dayStyle, null);
		if (SpendMoney == null) {
			SpendMoney = 0l;
		}
		Long ScrapCom = computerInfoService.getScrapCom(dayStyle);
		resultMap.put("Income", Income);
		resultMap.put("NumCom", NumCom);
		resultMap.put("SpendMoney", SpendMoney);
		resultMap.put("ScrapCom", ScrapCom);
		return resultMap;
	}

	/**
	 * 獲得每个人的数据
	 *
	 * @param dayStyle
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEveryOneDate")
	public Object getEveryOneDate(String dayStyle) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<TongJiData> list = computerInfoService.getTongJiDate(dayStyle);
		resultMap.put("list", list);
		return resultMap;
	}

	/**
	 * 獲得员工手上的余额
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getyue")
	public Object getyue(String dayStyle) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<UserDomain> list = computerInfoService.getMoney(dayStyle);
		resultMap.put("list", list);
		return resultMap;
	}

	/**
	 * 获取盈利
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getyeat")
	public Object getYearYiLi(String yearStyle) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int year = 0;
		if ("1".equals(yearStyle)) {
			year = DateUtil.getSysYear();
		} else {
			year = DateUtil.getSysYear() - 1;
		}
		List<YearLiRun> list = computerInfoService.getYearLiRun(String.valueOf(year));
		resultMap.put("list", list);
		return resultMap;
	}

	/**
	 * 获得个人的数据
	 */
	@RequestMapping("/personTongji")
	public Object personTongji(ModelMap map) {
		return "computerPage/yuangongIndex";
	}

	/**
	 * 获得个人的数据
	 */
	@ResponseBody
	@RequestMapping("/personComputerData")
	public Object getPersonageDayStyle(ModelMap map, String daystyle) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Long privateNumCom = computerInfoService.getPrivateNumCom(daystyle, user.getUserId());

		Long privateSpendMoney = computerInfoService.getPrivateSpendMoney(daystyle, user.getUserId());
		if (privateSpendMoney == null) {
			privateSpendMoney = 0l;
		}
		resultMap.put("privateNumCom", privateNumCom);
		resultMap.put("privateSpendMoney", privateSpendMoney);
		return resultMap;
	}


	/**
	 * 获得个人的数据
	 */
	@ResponseBody
	@RequestMapping("/personData")
	public Object getPersonageComData(ModelMap map, String yearStyle) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int year = 0;
		if ("1".equals(yearStyle)) {
			year = DateUtil.getSysYear();
		} else {
			year = DateUtil.getSysYear() - 1;
		}
		List<YearLiRun> list = computerInfoService.getPersonComData(String.valueOf(year), user.getUserId());
		resultMap.put("list", list);
		return resultMap;
	}

	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/editUserPwd")
	public Object editUserPwd(ModelMap map) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		User bo = userMapper.getPassword(user.getUserName());
		map.addAttribute("userId", bo.getUserId());
		return "computerPage/userEditPwd";
	}

	/**
	 * 获取首页
	 */

	@RequestMapping(value = "/getIndex")
	public Object getIndex(ModelMap map) {
		return "index/time";
	}

	//获取用户的的yue
	@ResponseBody
	@RequestMapping(value = "/getYueByuserId")
	public Object getYueByuserId(ModelMap map) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		User bo = userMapper.getPassword(user.getUserName());
		UserDomain userDomain = computerInfoService.getMoneyByUserid(bo.getUserId());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Long count = 0l;
		if (userDomain != null) {
			count = userDomain.getMoneyCount();
		}
		resultMap.put("count", count);
		return resultMap;
	}

	/**
	 * 修改密码
	 */
	@ResponseBody
	@RequestMapping("/userResetPwd")
	@Log("修改密码")
	public Object resetPwd(User user) {
		ResultObj resultObj = new ResultObj(false, null, user);
		Boolean bool = adminService.resetPwd(user);
		resultObj.setSuccess(bool);
		return resultObj;
	}

	/**
	 * 编辑页面
	 */
	@RequestMapping("/editBoss")
	public Object getEditBoss(HttpServletRequest request, HttpSession session, ModelMap map, Long id) {
		//包邮
		List<Dictionary> baoyou = dictionaryService.getDictionaryByCode(Constant.BAOYOU);
		//品牌
		List<Dictionary> diannaopinpai = dictionaryService.getDictionaryByCode(Constant.PINPAI);
		ComputerInfo computerInfo = computerInfoService.searchById(id);
		map.addAttribute("diannaopinpai", diannaopinpai);
		map.addAttribute("baoyou", baoyou);
		map.put("bo", computerInfo);
		return "adminPage/editComputer";
	}

	/**
	 * 折价页面
	 */

	@RequestMapping("/editZheJiaPrince")
	public Object zheJiaPrince(HttpServletRequest request, HttpSession session, ModelMap map, Long id, Long price,String comWaiGuan) {
		map.put("conId", id);
		map.put("comBugPrice", price);
		map.put("comWaiGuan" ,comWaiGuan);
		return "adminPage/convertPrice.html";
	}

	/**
	 * 修改折价的价格
	 */

	/**
	 * 保存数据
	 */
	@ResponseBody
	@RequestMapping("/ditPricew")
	@Log("折价电脑")
	public Object EditPricew(HttpServletRequest request, HttpSession session, ModelMap map,
							 ComputerInfo bo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//获取登录用户的信息
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		String result = "faile";
		Double count = bo.getComBugPrice() - bo.getComZheJia();
		bo.setComBugPrice(count);
		bo.setUpdateUser(user.getUserId());
		boolean updateResult = false;
		updateResult = computerInfoService.updateKnockdownDate(bo);

		if (updateResult) {
			result = "success";
		}
		resultMap.put("result", result);
		return resultMap;
	}
}