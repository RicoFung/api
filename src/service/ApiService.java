package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chok.devwork.BaseDao;
import chok.devwork.BaseService;
import dao.ApiDao;
import entity.Api;

@Service
public class ApiService extends BaseService<Api,Long>
{
	@Autowired
	private ApiDao apiDao;

	@Override
	public BaseDao<Api,Long> getEntityDao() {
		return apiDao;
	}

	public List<Api> getAppByUserId(Map<String, Object> param) 
	{
		return apiDao.getAppByUserId(param);
	}
	public List<Map> getMenuByUserId(Map<String, Object> param) 
	{
		List<Map> treeNodes = new ArrayList<Map>();
		try
		{
			List<Long> menuIds = new ArrayList<Long>();
			List<Api> menus = new ArrayList<Api>();
			Long userid = Long.parseLong(param.get("tc_user_id").toString());
			// 1. 查找目的菜单
			List<Api> targetMenus = apiDao.getMenuByUserId(param);
			for(Api o : targetMenus)
			{
				menuIds.add(o.getLong("id"));
			}
			// 2. 根据目的菜单递归检索所有父菜单和子菜单
			if (param.containsKey("tc_name") && param.get("tc_name").toString().length()>0)
			{
				// 2.1 递归检索父菜单
				List<Long> pids = new ArrayList<Long>(); // 缓存所有父节点id
				for(Api o : targetMenus)
				{
					menuIds.addAll(getParentMenus(userid, o.getLong("pid"), pids));
				}
				// 2.2 递归检索子菜单
				List<Long> cids = new ArrayList<Long>(); // 缓存所有子节点id
				for(Api o : targetMenus)
				{
					menuIds.addAll(getChildMenus(userid, o.getLong("id"), cids));
				}
			}
			// 3. 合并菜单集合
			// 3.1 去重复
			HashSet set  =   new  HashSet(menuIds); 
			menuIds.clear(); 
			menuIds.addAll(set); 
			for(Long id : menuIds)
			{
				param.clear();
				param.put("id", id);
				param.put("tc_user_id", userid);
				menus.add((Api) apiDao.getMenuByUserId(param).get(0));
			}
			// 3.2 格式化为json
			for(int i=0; i<menus.size(); i++)
			{
				Api o = menus.get(i);
				treeNodes.add(o.getM());
			}
			// 3.3 按tc_order排序
			Collections.sort(treeNodes,new Comparator<Map>(){
	            public int compare(Map arg0, Map arg1) {
	                return (arg0.get("tc_order").toString()).compareTo(arg1.get("tc_order").toString());
	            }
	        });
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return treeNodes;
	}
	
	// 递归检索父菜单id集合
	public List<Long> getParentMenus(Long userid, Long pid, List<Long> pids)
	{
		Map m = new HashMap();
		m.put("tc_user_id", userid);
		m.put("id",pid);
		List<Api> parentMenus = apiDao.getMenuByUserId(m);
		
		if(parentMenus.size()==1)
		{
			pids.add(parentMenus.get(0).getLong("id"));
			getParentMenus(userid, parentMenus.get(0).getLong("pid"), pids);
		}
		return pids;
	}
	
	// 递归检索子菜单id集合
	public List<Long> getChildMenus(Long userid, Long id, List<Long> cids)
	{
		Map m = new HashMap();
		m.put("tc_user_id", userid);
		m.put("pid",id);
		List<Api> childMenus = apiDao.getMenuByUserId(m);
		
		if(childMenus.size()>0)
		{
			for (int i=0; i<childMenus.size(); i++)
			{
				cids.add(childMenus.get(i).getLong("id"));
				getChildMenus(userid, childMenus.get(i).getLong("id"), cids);
			}
		}
		return cids;
	}
	
	public List<Map<String, Object>> getBtnByUserId(Map<String, Object> param) 
	{
		List<Api> btnPermitData = apiDao.getBtnByUserId(param);
		List<Map<String, Object>> treeNodes = new ArrayList<Map<String, Object>>();
		for(int i=0; i<btnPermitData.size(); i++)
		{
			Api o = btnPermitData.get(i);
			treeNodes.add(o.getM());
		}
		return treeNodes;
	}
	
	public List<Map<String, Object>> getActByUserId(Map<String, Object> param) 
	{
		List<Api> actPermitData = apiDao.getActByUserId(param);
		List<Map<String, Object>> treeNodes = new ArrayList<Map<String, Object>>();
		for(int i=0; i<actPermitData.size(); i++)
		{
			Api o = actPermitData.get(i);
			treeNodes.add(o.getM());
		}
		return treeNodes;
	}
}
