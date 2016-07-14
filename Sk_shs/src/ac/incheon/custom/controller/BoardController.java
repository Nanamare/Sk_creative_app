package ac.incheon.custom.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ac.incheon.custom.service.CommonService;
import ac.incheon.custom.model.BoardModel;
import ac.incheon.custom.model.JoinModel;
import ac.incheon.custom.service.BoardService;

import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	public static String USER_ID_ATTR = "user_id";
	public static String LOGIN_ATTR = "login_flag";
	
	public static String GUEST_ID = "GUEST";
	
	public static String GetUserId(HttpServletRequest request)
	{
		if( "Y".equals(request.getSession().getAttribute(BoardController.LOGIN_ATTR) ) )
		{
			return request.getSession().getAttribute(BoardController.USER_ID_ATTR).toString();	
		}
		else
		{
			return BoardController.GUEST_ID;
		}
	}
	
	
	@Autowired
    @Qualifier("boardService")
    private BoardService boardService;
	
	@Qualifier("commonService")
	private CommonService commonService;
	
	@Value("#{app['attach.base.path']}")
    private String attachBasePath;
	
	@Value("#{app['attach.web.path']}")
    private String attachWebPath;
	
	@RequestMapping(value = "/view-list", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewList(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "board_name", required = true) String boardName
    ) {
        ModelAndView retModel = new ModelAndView("list_board");
        
        List<BoardModel> boardList = boardService.getBoardList(boardName);
     
        retModel.addObject("board_list", boardList );
        retModel.addObject("board_name", boardName );
        
        return retModel;
    }
	
	
	@RequestMapping(value = "/view-detail", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewDetail(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "id", required = true) String idString,
    		@RequestParam(value = "board_name", required = true) String boardName
    ) {
        ModelAndView retModel = new ModelAndView("board_detail");
     
        BoardModel boardInfo = boardService.getBoardInfo(Integer.parseInt(idString));
        
        retModel.addObject("board_info", boardInfo );
        retModel.addObject("board_name", boardName );
        
        return retModel;
    }
	
	@RequestMapping(value = "/down-attach-file", method = {RequestMethod.GET, RequestMethod.POST})
    public void viewAttachFile(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "path_name", required = true) String pathName
    ) {
		try {
			
			pathName = pathName.substring(0,pathName.lastIndexOf(File.separator));
			
	        File fileDir = new File(attachBasePath + pathName);
	        File downFile = fileDir.listFiles()[0];
	        
	        InputStream in = new BufferedInputStream(new FileInputStream(downFile));

	        String fileName = java.net.URLEncoder.encode(downFile.getName(), "UTF-8");
	        fileName = fileName.replaceAll("\\+", "%20");
	        
	        response.setContentType("application");
	        response.setHeader("Content-Disposition", "attachment; filename="+fileName); 

	        ServletOutputStream out = response.getOutputStream();
	        IOUtils.copy(in, out);
	        response.flushBuffer();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
    }
	
	@RequestMapping(value = "/view-insert", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewInsert(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "board_name", required = true) String boardName
    ) {
        ModelAndView retModel = new ModelAndView("write_board");
        
        retModel.addObject("board_name", boardName );
        
        return retModel;
    }
	
	@RequestMapping(value = "/view-insert-file", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewInsertFile(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "board_name", required = true) String boardName
    ) {
        ModelAndView retModel = new ModelAndView("write_board_file");
     
        retModel.addObject("board_name", boardName );
        
        return retModel;
    }
	
	@RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "board_name", required = true) String boardName,
    		@RequestParam(value = "name", required = true) String name,
    		@RequestParam(value = "content", required = true) String content
    ) throws Exception {
        
        BoardModel boardModel = new BoardModel();
        boardModel.setName(name);
        boardModel.setContent(content);
        boardModel.setBoardName(boardName);       
    	boardModel.setRegiUserId(CommonController.GetUserId(request));        
        boardService.insertBoard(boardModel);    
        
        return viewList(request,response,boardName);
    }
	
	
	
	@RequestMapping(value = "/insert-with-file", method = { RequestMethod.POST})
    public ModelAndView insertWithFile(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "board_name", required = true) String boardName,
    		@RequestParam(value = "name", required = true) String name,
    		@RequestParam(value = "content", required = true) String content
    ) throws Exception {
        
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multipartRequest.getFileNames();
        MultipartFile multipartFile = null;
        if (fileNames.hasNext()) {
            String fileName = fileNames.next();
            multipartFile = multipartRequest.getFile(fileName);
        } else {
            
        }
        
        byte[] imageBytes = multipartFile.getBytes();
        String fileName = multipartFile.getOriginalFilename();
        
        String savePathName = File.separator + String.valueOf((new Date().getTime())) 
                + File.separator +fileName;
        
        FileUtils.writeByteArrayToFile(new File(attachBasePath + savePathName), imageBytes );

        
        BoardModel boardModel = new BoardModel();
        boardModel.setName(name);
        boardModel.setContent(content);
        boardModel.setBoardName(boardName);
        boardModel.setAttachUrl(savePathName);
        
    	boardModel.setRegiUserId(CommonController.GetUserId(request));

        boardService.insertBoard(boardModel);
     
        
        return viewList(request,response,boardName);
    }	
	
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "id", required = true) String idString,
    		@RequestParam(value = "board_name", required = true) String boardName
    ) {
     
        boardService.deleteBoard(Integer.parseInt(idString));       
        
        return viewList(request,response,boardName);
    }
	
	@RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response
    ) {
        ModelAndView retModel = new ModelAndView("jsonView");
     
        
        return retModel;
    }	
	
	@RequestMapping(value = "/membership", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView membership(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "board_name", required = true) String boardName,
    		@RequestParam(value = "user_id", required = true) String user_id,
    		@RequestParam(value = "password", required = true) String password,
    		@RequestParam(value = "user_name", required = true) String user_name
    		
    ) throws Exception {
			
		ModelAndView retModel = new ModelAndView("login");
        JoinModel joinModel = new JoinModel();
        joinModel.setId(user_id);
        joinModel.setPassword(password);
        joinModel.setBoardName(boardName);
        joinModel.setUserName(user_name);
        boardService.joinBoard(joinModel);
       
        //return viewList(request,response,boardName);
        return retModel;
    }
	

	@RequestMapping(value = "/auth/do-login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView authdoLogin(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "user_id", required = true) String user_id,
    		@RequestParam(value = "password", required = true) String password
    ) throws Exception {
		
        ModelAndView retModel = new ModelAndView("main");
                
        try{
        String passwd = boardService.getPasswd(user_id).getPassWord().toString();
        String board_name = boardService.getPasswd(user_id).getBoardName().toString();

        
       String loginResult = "";
       
        if( passwd.equals(password) )
        {	
        	
        	request.getSession().setAttribute(BoardController.USER_ID_ATTR, user_id);
        	request.getSession().setAttribute(CommonController.LOGIN_ATTR, "Y");
        	
        	loginResult = "A";
        }
        else
        {
        	loginResult = "F";
        	
        }
        retModel.addObject("result", loginResult );
        retModel.addObject("board_name", board_name);
        
        return retModel;
        }
        catch (RuntimeException  e){
        	   String loginResult = "";
        	   loginResult = "F";
               retModel.addObject("result", loginResult );
            
               return retModel;
        }
    }
	
	
	@RequestMapping(value = "/modifyok", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView modifyok(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "id", required = true) String idString,
    		@RequestParam(value = "board_name", required = true) String boardName,
    		@RequestParam(value = "name", required = true) String name,
    		@RequestParam(value = "content", required = true) String content
    ) throws Exception {
               		
		ModelAndView retModel = new ModelAndView("main");
        BoardModel boardModel = new BoardModel();
        boardModel.setName(name);
        boardModel.setContent(content);
        boardModel.setBoardName(boardName);       
    	boardModel.setRegiUserId(CommonController.GetUserId(request));  
    	boardModel.setId(Integer.parseInt(idString));
        boardService.updateBoard(boardModel);
        
        retModel.addObject("id", Integer.parseInt(idString) );
        retModel.addObject("board_name", boardName );
        
        return retModel;
    }
	
	
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView modify(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "board_name", required = true) String boardName,
    		@RequestParam(value = "name", required = true) String name,
    		@RequestParam(value = "content", required = true) String content,
    		@RequestParam(value = "id", required = true) String idString
    ) throws Exception {
        
		ModelAndView retModel = new ModelAndView("modify");		
        retModel.addObject("id", Integer.parseInt(idString) );       
        retModel.addObject("board_name", boardName );
        
        return retModel;
    }
	
	 
	@RequestMapping(value = "/search-list", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response,
	          @RequestParam(value = "name", required = true) String name,
	          @RequestParam(value = "board_name", required = true) String boardName
	    ) throws Exception {
	        
	      ModelAndView retModel = new ModelAndView("list_board");
	      
	       List<BoardModel> searchList = boardService.getSearchList(name);	     
	        retModel.addObject("board_list", searchList );
	        retModel.addObject("name",name);
	        retModel.addObject("board_name",boardName);
	        
	        return retModel;
	        
	   }
	   

	

}
