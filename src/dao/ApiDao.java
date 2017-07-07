package dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import chok.devwork.BaseDao;
import entity.Api;


@Repository
public class ApiDao extends BaseDao<Api,Long>
{
	@Override
	public Class<Api> getEntityClass()
	{
		return Api.class;
	}

	public List<Api> getAppByUserId(Map<String, Object> param) 
	{
		return this.getSqlSession().selectList(getStatementName("getAppByUserId"), param);
	}
	public List<Api> getMenuByUserId(Map<String, Object> param) 
	{
		return this.getSqlSession().selectList(getStatementName("getMenuByUserId"), param);
	}
	public List<Api> getBtnByUserId(Map<String, Object> param) 
	{
		return this.getSqlSession().selectList(getStatementName("getBtnByUserId"), param);
	}
	public List<Api> getActByUserId(Map<String, Object> param) 
	{
		return this.getSqlSession().selectList(getStatementName("getActByUserId"), param);
	}
}
