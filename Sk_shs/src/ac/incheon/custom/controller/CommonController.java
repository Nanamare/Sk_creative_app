package ac.incheon.custom.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ac.incheon.custom.model.JoinModel;
import ac.incheon.custom.service.CommonService;
import java.sql.PreparedStatement;  
import java.sql.*;
@Controller
@RequestMapping("/")
public class CommonController {
	
	public static String USER_ID_ATTR = "user_id";
	public static String LOGIN_ATTR = "login_flag";
	
	public static String GUEST_ID = "GUEST";
	
	@Autowired
    @Qualifier("commonService")
    private CommonService commonService;
	

	public static String GetUserId(HttpServletRequest request)
	{
		if( "Y".equals(request.getSession().getAttribute(CommonController.LOGIN_ATTR) ) )
		{
			return request.getSession().getAttribute(CommonController.USER_ID_ATTR).toString();	
		}
		else
		{
			return CommonController.GUEST_ID;
		}
	}
	
	@RequestMapping(value = "/view-login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewLogin(HttpServletRequest request, HttpServletResponse response
    ) {
        ModelAndView retModel = new ModelAndView("login");
                
        return retModel;
    }	
/*
	@RequestMapping(value = "/auth/do-login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "user_id", required = true) String userId,
    		@RequestParam(value = "password", required = true) String password
    ) {
        ModelAndView retModel = new ModelAndView("main");
        
        
        
        String loginResult = "";
        
        if( commonService.getUserPassword(userId).equals(password) )
        {	
        	request.getSession().setAttribute(CommonController.USER_ID_ATTR, userId);
        	request.getSession().setAttribute(CommonController.LOGIN_ATTR, "Y");
        	
        	loginResult = "A";
        }
        else
        {
        	loginResult = "F";
        }
        
        
     
        retModel.addObject("result", loginResult );
        
        return retModel;
    }
*/	

	@RequestMapping(value = "/auth/do-logout", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response
    ) {
        ModelAndView retModel = new ModelAndView("main");
     
        request.getSession().invalidate();        
        
        return retModel;
    }
	@RequestMapping(value = "/view-membership", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewMembership(HttpServletRequest request, HttpServletResponse response
    ) {
        ModelAndView retModel = new ModelAndView("membership");
                
        return retModel;
    }	
	/*
	@RequestMapping(value = "/auth/do-membership", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView doMembership(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "user_id", required = true) String userId,
    		@RequestParam(value = "password", required = true) String password,
    		@RequestParam(value = "name", required = true) String name,
    		@RequestParam(value = "password_confirm", required = true) String password_confirm
    ) {
        ModelAndView retModel = new ModelAndView("membership");
        String PasswordConfirm = "";
		if(password.equals(password_confirm)){
			PasswordConfirm = "Y";
		}
		else{
			PasswordConfirm = "N";
		}
		
        retModel.addObject("result", PasswordConfirm );
        return retModel;
    }
    */
	

	
	
	
	
	@RequestMapping(value = "/main", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewMain(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "board_name", required = false) String boardName
    ) {
        ModelAndView retModel = new ModelAndView("main");
        
        
        if( boardName != null )
    	{
        	retModel.addObject("board_name", boardName);
        	Cookie cookie = new Cookie("board_name", boardName);
            cookie.setMaxAge(30*60);
            response.addCookie(cookie);        	
    	}
        
        
        return retModel;
    }
	
	
	
}
