package action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chok.devwork.BaseController;
import entity.Api;
import service.ApiService;

@Scope("prototype")
@Controller
@RequestMapping("/")
public class ApiAction extends BaseController<Api>
{
	@Autowired
	private ApiService service;
	
	@RequestMapping("/getAppByUserId")
	public void getAppByUserId() 
	{
		printJson(service.getAppByUserId(req.getParameterValueMap(false, true)));
	}
	@RequestMapping("/getMenuByUserId")
	public void getMenuByUserId() 
	{
		printJson(service.getMenuByUserId(req.getParameterValueMap(false, true)));
	}
	@RequestMapping("/getBtnByUserId")
	public void getBtnByUserId() 
	{
		printJson(service.getBtnByUserId(req.getParameterValueMap(false, true)));
	}
	@RequestMapping("/getActByUserId")
	public void getActByUserId() 
	{
		printJson(service.getActByUserId(req.getParameterValueMap(false, true)));
	}
}