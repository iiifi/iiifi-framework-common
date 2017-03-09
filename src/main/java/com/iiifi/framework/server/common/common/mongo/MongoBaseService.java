/**
 * 不要因为走了很远就忘记当初出发的目的:whatever happened,be yourself
 */
package com.iiifi.framework.server.common.common.mongo;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.WriteResult;

/**
 * 封装mongodb基本的CRUD操作
 * 
 * @author xiaoyu 2016年5月31日
 */
@Service
public abstract class MongoBaseService<T extends MongoBean> {

	protected static Logger logger = Logger.getLogger(MongoBaseService.class);

	@Autowired
	protected MongoTemplate template;

	public static void main(String args[]) {
		String s = "TestBean";
		System.out.println(s.replace(s.charAt(0), (char) (s.charAt(0) + 32)));
	}

	/**
	 * 查找列表 需根据具体参数重写
	 * 
	 * @author xiaoyu
	 * @return
	 * @time 2016年5月31日上午10:54:39
	 */
	public List<T> findList(T t) {
		try {
			Sort sort = new Sort(Direction.DESC, new String[] {"createDate"});
			Query query = new Query();
			// TODO:其他查询参数
			query.with(sort);
			List<T> list = template.find(query, getTClass(), getTableName());
			return list;
		} catch (Exception e) {
			logger.debug("查找出现异常", e);
		}
		return null;
	}

	
	/**分页查询(页码从0开始)
	 *@author xiaoyu
	 *@param t
	 *@param pageNo
	 *@param pageSize
	 *@return
	 *@time 2016年5月31日下午3:11:27
	 */
	public List<T> findByPage(T t, int pageNo, int pageSize) {
		try {
			Sort sort = new Sort(Direction.DESC, new String[] {"createDate"});
			Pageable page = new PageRequest(pageNo-1, pageSize, sort);
			Query query = new Query();
			// TODO:其他查询参数
			query.with(page);
			List<T> list = template.find(query, getTClass(), getTableName());
			return list;
		} catch (Exception e) {
			logger.debug("查找出现异常", e);
		}
		return null;
	}

	/**根据id分页查询
	 * @param t
	 * @param targetId 分页id,回翻为页面第一个 后翻为页面最后一个
	 * @param flag 0回翻 1后翻
	 * @return
	 */
	public List<T> findByPageUseId(T t,String targetId,int flag,int pageSize) {
		try {
			Sort sort = new Sort(Direction.DESC, new String[]{"createDate"});
			Query query = new Query();
			if(0 == flag) {
				query.addCriteria(Criteria.where("_id").lt(targetId)).with(sort).limit(pageSize);				
			}
			else {
				query.addCriteria(Criteria.where("_id").gt(targetId)).with(sort).limit(pageSize);				
			}
			List<T> list = template.find(query, getTClass(),getTableName());
			return list;
		}
		catch(Exception e) {
			logger.debug("查找出现异常", e);
		}
		return null;
		
	}
	/**
	 * 根据id查找单个文档
	 * 
	 * @author xiaoyu
	 * @param id
	 * @return
	 * @time 2016年5月31日上午10:57:45
	 */
	public T get(String id) {
		try {
			T t = template.findById(id, getTClass(),getTableName());
			return t;
		} catch (Exception e) {
			logger.debug("查找出现异常", e);
		}
		return null;
	}

	/**
	 * 插入单个文档
	 * 
	 * @author xiaoyu
	 * @param t
	 * @return
	 * @time 2016年5月31日上午10:58:10
	 */
	@Transactional(readOnly=false)
	public T insert(T t) {
		try {
			Date date = new Date();
			t.setCreateDate(date);
			t.setUpdateDate(date);
			t.setDelFlag("0");
			t.setId(null);
			template.insert(t, getTableName());
			return t;

		} catch (Exception e) {
			logger.debug("插入出现异常", e);
		}
		return null;
	}

	/**
	 * 根据id删除单个文档(删除多个需重写)
	 * 
	 * @author xiaoyu
	 * @param t
	 * @return 删除的数据数量
	 * @time 2016年5月31日上午10:58:23
	 */
	@Transactional(readOnly=false)
	public int delete(T t) {
		try {
			Query query = new Query(Criteria.where("_id").is(t.getId()));
			//真删除
			//WriteResult result = template.remove(query, getTableName());
			//逻辑删除
			Update up = new Update();
			up.set("deleteDate",new Date());
			up.set("delFlag", "1");
			WriteResult result = template
					.updateMulti(query, up, getTableName());
			logger.info(result.toString());
			return result.getN();
		} catch (Exception e) {
			logger.debug("删除出现异常", e);
		}
		return 0;
	}
	
	/**
	 * 更新所有相关文档, 需根据具体参数重写
	 * 
	 * @author xiaoyu
	 * @param t
	 * @return 更新的数据数量
	 * @time 2016年5月31日上午10:59:01
	 */
	@Transactional(readOnly=false)
	public int update(T t) {
		try {
			Query query = new Query(Criteria.where("_id").is(t.getId()));
			Update up = new Update();
			up.set("updateDate",new Date());
			// TODO:其他需更新字段
			WriteResult result = template
					.updateMulti(query, up, getTableName());
			logger.info(result.toString());
			return result.getN();
		} catch (Exception e) {
			logger.debug("更新出现异常", e);
		}
		return 0;
	}

	/**
	 * 反射获取泛型T.class
	 * 
	 * @author xiaoyu
	 * @return
	 * @time 2016年5月11日下午2:26:01
	 */
	@SuppressWarnings({ "unchecked" })
	protected Class<T> getTClass() {
		return (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**获取bean的名称
	 *@author xiaoyu
	 *@return
	 *@time 2016年5月31日下午6:55:43
	 */
	protected String getTableName() {
		String s = getTClass().getSimpleName();
		return s;
		// return "t_biz_"+s.replace(s.charAt(0), (char)(s.charAt(0)+32));
	}

}
