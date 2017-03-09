package com.iiifi.framework.server.common.common.mongo;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * mongo基础公共参数
 * 
 * @author xiaoyu 2016年5月11日
 */
@Document
public class MongoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 所有参数都有初始化,这样所有的参数都会存入mongo 这样看起来像mysql多点,习惯些,没有实际性的用处
	 */
	@Transient
	Date date = new Date();

	@Id
	private String id;
	private String delFlag = "0";// 删除标志 0正常 1删除
	private Date createDate = date;
	private Date updateDate = date;
	private Date deleteDate = date;
	@Transient
	private String dataRange;// 数据权限范围
	
	
	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public MongoBean() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
