package cn.zlin.demo.service;

import cn.zlin.demo.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @Description: 电脑参数表模块服务
 * @Author: linTH
 * @Date: 10-08 09:13:24
 * @Copyright: 2019 福富软件
 */
public interface ComputerInfoService {

	/**
	 * 新增数据
	 * @param bo 电脑参数表业务对象
	 * @return 电脑参数表id
	 */
	public Long insert(ComputerInfo bo);

	/**
	 * 修改数据
	 * @param bo 电脑参数表业务对象
	 * @return 是否修改成功
	 */
	public boolean update(ComputerInfo bo);

	/**
	 * 查询数据（分页）
	 * @param bo 查询参数
	 * @return 电脑参数表分页数据对象
	 */
	public EUDGPagination  searchList(ComputerInfo bo);
	
	/**
	 * 根据业务id查询数据
	 * @param id 电脑参数表id
	 * @return 电脑参数表业务对象
	 */
	public ComputerInfo searchById(Long id);

	/**
	 * 根据用户的id 进行查询用户的余额
	 * @param id
	 * @return
	 */
	public UserDomain getMoneyByUserid(Long id);

	/**
	 *
	 * boss查看用户余额信息
	 * @return
	 */
	public List<UserDomain> getMoney(String time);

	/**
	 * 获得统计数据通过用户id
	 * @param id
	 * @return
	 */
	public TongJiData getTongJiDateByUserID(Long id);

	/**
	 * boss获得统计数据
	 * @param
	 * @return
	 */
	public  List<TongJiData> getTongJiDate(String time);

	/**
	 * updateKnockdownDate
	 *修改成交日期
	 */
	public boolean updateKnockdownDate(ComputerInfo bo);
	/**
	 * 查询今天/这月购买花费的钱数
	 */
	public  Long  getSpendMoney( String time,Long userid);

	/**
	 * 这个月或者今天收入
	 */
	public  Long getIncome( String time,Long userid);
	/**
	 * 这个月或者今天收入电脑 1为当天  2 为当月 3 为上个月  4 为 3三个月
	 */
	public  Long  getNumCom(String time,Long createUser);

	/**
	 * 报废数量
	 */
	public  Long  getScrapCom(String time);


	/**
	 * 统计年利润
	 * @param time
	 * @return
	 */
	public  List<YearLiRun>  getYearLiRun( String time);



	/**
	 * 员工发费多少钱
	 * @param time
	 * @return
	 */
	public  Long getPrivateSpendMoney(String time, Long userid);
	/**
	 * 员工收多少电脑
	 * @param time
	 * @return
	 */
	public  Long getPrivateNumCom(String time, Long userid);

	/**
	 * 电脑分析统计
	 * @param time
	 * @param userid
	 * @return
	 */
	public  List<YearLiRun> getPersonComData( String time,Long userid);
}