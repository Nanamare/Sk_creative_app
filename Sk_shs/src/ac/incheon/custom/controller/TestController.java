package ac.incheon.custom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import ac.incheon.custom.model.TestModel;
import ac.incheon.custom.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
    @Qualifier("testService")
    private TestService testService;
	
	@RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response
    ) {
        ModelAndView retModel = new ModelAndView("jsonView");
     
        
        return retModel;
    }
	
	@RequestMapping(value = "/testView", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView testView(HttpServletRequest request, HttpServletResponse response
    ) {
        ModelAndView retModel = new ModelAndView("jsonView");
     
        retModel.addObject("test", "value");
        
        return retModel;
    }
	
	@RequestMapping(value = "/testViewForJsp", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView testViewForJsp(HttpServletRequest request, HttpServletResponse response
    ) {
        ModelAndView retModel = new ModelAndView("test");
     
        retModel.addObject("greeting", "HelloWorld");
        
        return retModel;
    }
	
	@RequestMapping(value = "/testDbInsert", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView testDbInsert(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "id", required = true) String id,
    		@RequestParam(value = "name", required = true) String name
    ) throws Exception {
        ModelAndView retModel = new ModelAndView("jsonView");
        ObjectMapper objectMapper = new ObjectMapper();

        TestModel testModel = new TestModel();
        testModel.setId(Integer.parseInt(id));
        testModel.setName(name);
        
        testService.insertTestModel(testModel);
        
        retModel.addObject("testList", testService.getTestList());
        
        return retModel;
    }
	
	
	
	@RequestMapping(value = "/testDbGet", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView testDbGet(HttpServletRequest request, HttpServletResponse response
    ) {
        ModelAndView retModel = new ModelAndView("jsonView");
       
        retModel.addObject("testList", testService.getTestList());
        
        return retModel;
    }
	
	@RequestMapping(value = "/testDbGetByName", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView testDbGetByName(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "name", required = true) String name
    		
    ) {
        ModelAndView retModel = new ModelAndView("jsonView");
       
        retModel.addObject("testList", testService.getTestList(name));
        
        return retModel;
    }	
	
	
	
	@RequestMapping(value = "/testDbUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView testDbUpdate(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "id", required = true) String id,
    		@RequestParam(value = "name", required = true) String name
    ) {
        ModelAndView retModel = new ModelAndView("jsonView");

        TestModel testModel = new TestModel();
        testModel.setId(Integer.parseInt(id));
        testModel.setName(name);
        
        testService.updateTestModel(testModel);
       
        retModel.addObject("testList", testService.getTestList());
        
        return retModel;
    }
	
	@RequestMapping(value = "/testDbDelete", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView testDbDelete(HttpServletRequest request, HttpServletResponse response,
		@RequestParam(value = "id", required = true) String id
    ) {
        ModelAndView retModel = new ModelAndView("jsonView");
        ObjectMapper objectMapper = new ObjectMapper();
        
        TestModel testModel = new TestModel();
        testModel.setId(Integer.parseInt(id));
        
        testService.deleteTestModel(testModel);
       
        retModel.addObject("testList", testService.getTestList());
        
        return retModel;
    }

}
