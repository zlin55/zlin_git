package cn.zlin.demo.dao;

import cn.zlin.demo.domain.ComputerInfo;
import cn.zlin.demo.domain.TongJiData;
import cn.zlin.demo.domain.UserDomain;
import cn.zlin.demo.domain.YearLiRun;
import org.apache.ibatis.annotations.Param;
import  org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Description: 电脑参数表模块dao接口
 * @Author: linTH
 * @Date: 10-07 17:10:40
 * @Copyright: 2019
 */
public interface ComputerInfoMapper {
	
	/**
	 * 新增数据
	 * @param bo 电脑参数表业务对象
	 * @return 电脑参数表id
	 */
	public long insert(ComputerInfo bo);
	
	/**
	 * 修改数据
	 * @param bo 电脑参数表业务对象
	 * @return 修改的记录数
	 */
	public long update(ComputerInfo bo);

	/**
	 * 查询数据（分页）
	 * @param bo 查询参数
	 * @param rowBounds 分页对象
	 * @return 电脑参数表数据列表
	 */
	public List<ComputerInfo> searchList(ComputerInfo bo, RowBounds rowBounds);
	
	/**
	 * 查询数据总数
	 * @param bo 查询参数
	 * @return 电脑参数表数据总数
	 */
	public long countList(ComputerInfo bo);
	
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
	  public List<UserDomain>  getMoney(@Param("time") String time );

	/**
	 * 获得统计数据通过用户id
	 * @param id
	 * @return
	 */
	  public  TongJiData getTongJiDateByUserID(Long id);

	/**
	 * boss获得统计数据
	 * @param
	 * @return
	 */
	public  List<TongJiData> getTongJiDate(@Param("time") String time);
	/**
	 * updateKnockdownDate
	 *修改成交日期
	 */
	public long updateKnockdownDate(ComputerInfo bo);


	/**
	 * 查询今天/这月购买花费的钱数
	 */
	public  Long  getSpendMoney(@Param("time") String time,@Param("userid")Long userid);

	/**
	 * 这个月或者今天收入
	 */
	public  Long getIncome(@Param("time") String time,@Param("userid")Long userid);
	/**
	 * 这个月或者今天收入电脑 1为当天  2 为当月 3 为上个月  4 为 3三个月
	 */
	public  Long  getNumCom(@Param("time") String time,@Param("createUser")Long createUser);

	/**
	 * 报废数量
	 */
	public  Long  getScrapCom(@Param("time") String time);

	/**
	 * 统计年利润
	 * @param time
	 * @return
	 */
	public  List<YearLiRun>  getYingLi(@Param("time") String time);



	/**
	 * 员工发费多少钱
	 * @param time
	 * @return
	 */
	public  Long getPrivateSpendMoney(@Param("time") String time,@Param("userid") Long userid);
	/**
	 * 员工收多少电脑
	 * @param time
	 * @return
	 */
	public  Long getPrivateNumCom(@Param("time") String time,@Param("userid") Long userid);



	/**
	 * 员工收多少电脑统计分析
	 * @param time
	 * @return
	 */
	public List<YearLiRun>  getPersonComData(@Param("time") String time,@Param("userid") Long userid);
	/**
	 *获取员工的手上的折价的钱
	 * @param time
	 * @returngetZheJiaMoney
	 */
	public List<UserDomain> getZheJiaMoney(@Param("time") String time );

	/**
	 * 获取自己的手上的折价的钱
	 * @param userid
	 * @return
	 */
	public List<UserDomain> getPersionZheJiaMoney(@Param("userid") Long userid);


}