package cn.zlin.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import cn.zlin.demo.dao.ComputerInfoMapper;
import cn.zlin.demo.domain.*;
import cn.zlin.demo.service.ComputerInfoService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 电脑参数表模块服务实现
 * @Author: linTH
 * @Date: 10-08 09:13:24
 * @Copyright: 2019 福富软件
 */
@Service("computerInfoServiceImpl")
@Transactional
public class ComputerInfoServiceImpl implements ComputerInfoService {

	@Autowired
	private ComputerInfoMapper computerInfoMapper; //注入电脑参数表模块dao

	/**
	 * 新增数据
	 * @param bo 电脑参数表业务对象
	 * @return 电脑参数表id
	 */
	@Override
	public Long insert(ComputerInfo bo) {
		Long result=computerInfoMapper.insert(bo);
		return result;
	}

	/**
	 * 修改数据
	 * @param bo 电脑参数表业务对象
	 * @return 是否修改成功
	 */
	@Override
	public boolean update(ComputerInfo bo) {
		long result = computerInfoMapper.update(bo);
		return result > 0;
	}



	/**
	 * 查询数据（分页）
	 * @param bo 查询参数
	 * @return 电脑参数表分页数据对象
	 */
	@Override
	public EUDGPagination searchList(ComputerInfo bo) {
		RowBounds rowBounds = new RowBounds((bo.getPage() - 1) * bo.getLimit(), bo.getLimit());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<ComputerInfo> list = computerInfoMapper.searchList(bo, rowBounds);
		////转化采购日期
		for(ComputerInfo computerInfo : list){
			computerInfo.setCreateDateStr(sdf.format(computerInfo.getCreateDate()));
			//转化成交日期
			if(computerInfo.getKnockdownDate()!=null&&!"".equals(computerInfo.getKnockdownDate())){
				computerInfo.setCreateDateStr(sdf.format(computerInfo.getKnockdownDate()));
			}
		}
		long count = computerInfoMapper.countList(bo);
		EUDGPagination pagination = new EUDGPagination(0L,"",count,list);
		return pagination;
	}

	/**
	 * 根据业务id查询数据
	 * @param id 电脑参数表id
	 * @return 电脑参数表业务对象
	 */
	@Override
	public ComputerInfo searchById(Long id) {
		ComputerInfo bo = computerInfoMapper.searchById(id);
		return bo;
	}

	/**
	 * 客服手上的余额是 折价加上 退货和退单的
	 * @param id
	 * @return
	 */
	@Override
	public UserDomain getMoneyByUserid(Long id) {
		UserDomain user=computerInfoMapper.getMoneyByUserid(id);
		return user;
	}
	@Override
	public List<UserDomain> getMoney(String  time) {
		List<UserDomain> list=computerInfoMapper.getMoney(time);
		return list;
	}

	@Override
	public TongJiData getTongJiDateByUserID(Long id) {
		return computerInfoMapper.getTongJiDateByUserID(id);
	}

	@Override
	public List<TongJiData> getTongJiDate(String time)
	{
		return computerInfoMapper.getTongJiDate(time);
	}

	/**
	 * 修改成交日期
	 * @param bo
	 * @return
	 */
	@Override
	public boolean updateKnockdownDate(ComputerInfo bo) {
	      	Long  result =computerInfoMapper.updateKnockdownDate(bo);
         return result > 0;
	}

	/**
	 * 获取花费的钱
	 * @param time
	 * @param userid
	 * @return
	 */
	@Override
	public Long getSpendMoney(String time, Long userid) {
		Long result=computerInfoMapper.getSpendMoney(time,userid);
		return result;
	}

	/**
	 * 获得的收入
	 * @param time
	 * @param userid
	 * @return
	 */
	@Override
	public Long getIncome(String time, Long userid) {
		Long result=computerInfoMapper.getIncome(time,userid);
		return result;
	}

	/**
	 * 收获电脑数量
	 * @param time
	 * @param createUser
	 * @return
	 */
	@Override
	public Long getNumCom(String time, Long createUser) {
		Long result=computerInfoMapper.getNumCom(time,createUser);
		return result;
	}

	/**
	 * 报废的电脑
	 * @param time
	 * @return
	 */
	@Override
	public Long getScrapCom(String time) {
		Long result=computerInfoMapper.getScrapCom(time);
		return result;
	}

	@Override
	public List<YearLiRun> getYearLiRun(String time) {
		List<YearLiRun>  list=computerInfoMapper.getYingLi(time);
		return list;
	}

	@Override
	public Long getPrivateSpendMoney(String time, Long userid) {

		return computerInfoMapper.getPrivateSpendMoney(time,userid);
	}

	@Override
	public Long getPrivateNumCom(String time, Long userid) {
		return computerInfoMapper.getPrivateNumCom(time,userid);
	}

	@Override
	public  List<YearLiRun> getPersonComData(String time, Long userid) {
		return computerInfoMapper.getPersonComData(time,userid);
	}


}