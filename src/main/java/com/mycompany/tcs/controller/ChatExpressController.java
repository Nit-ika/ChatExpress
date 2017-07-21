package com.mycompany.tcs.controller;

import com.mycompany.tcs.model.Adminorg;
import com.mycompany.tcs.model.EmployeeLogin;
import com.mycompany.tcs.model.Employees;
import com.mycompany.tcs.model.Forumanswer;
import com.mycompany.tcs.model.Forumques;
import com.mycompany.tcs.model.Messages;
import com.mycompany.tcs.service.AdminorgService;
import com.mycompany.tcs.service.EmployeeService;
import com.mycompany.tcs.service.ForumAnsService;
import com.mycompany.tcs.service.ForumQuesService;
import com.mycompany.tcs.service.MessageService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatExpressController 
{
    @Autowired
    private EmployeeService empservice;
    
    @Autowired
    private AdminorgService admservice;
    
    @Autowired
    private MessageService msgservice;    
    
    @Autowired
    private ForumQuesService fqservice;
    
    @Autowired
    private ForumAnsService faservice;
    
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
    @RequestMapping("/indexPage.htm")
    public String setUpForm(Map<String, Object> map)
    {
        Employees empRegister = new Employees();
        EmployeeLogin empLogin = new EmployeeLogin();
        
        map.put("empRegister", empRegister);
        map.put("empLogin", empLogin);
//        map.put("empList", empservice.getAllEmployees());
        return "indexPage";
    }
    
    @RequestMapping("/about.htm")
    public String aboutProject(Map<String, Object> map)
    {
        return "about";
    }
    
    @RequestMapping(value="/EmpRegister.htm", method = RequestMethod.POST)
    public String doActionsRegister(@ModelAttribute Employees empRegister, BindingResult result, @RequestParam String action, Map<String, Object> map)
    {                
        Employees empResult = new Employees();
        EmployeeLogin empLogin = new EmployeeLogin();
        
        if(action.toLowerCase().equals("register")||action.toLowerCase().equals("add")) 
        {
//            empRegister.setPassword(encoder.encode(empRegister.getPassword()));
            empRegister.setLoginStatus(0);
            
            Adminorg adminresult = admservice.findAdminById(empRegister.getEid());      
            if(adminresult!=null&&Objects.equals(adminresult.getAdminId(), empRegister.getEid()))
            {
                empRegister.setAdminEid(adminresult);
            }
                empservice.addEmployees(empRegister);
                empResult = empRegister;
        }        
        else if(action.toLowerCase().equals("edit")) 
        {
//            empRegister.setPassword(encoder.encode(empRegister.getPassword()));
            empRegister.setLoginStatus(0);
                empservice.editEmployees(empRegister);
                empResult = empRegister;
        }        
        else if(action.toLowerCase().equals("delete")) 
        {
                empservice.deleteEmployees(empRegister.getEid());
                empResult = new Employees();  
        }        
        else if(action.toLowerCase().equals("search")) 
        {     
                Employees searchedRegemp = empservice.findEmployeeById(empRegister.getEid());
                empResult = searchedRegemp!=null ? searchedRegemp : new Employees();
        } 
        
        map.put("empRegister", empResult);
        map.put("empLogin", empLogin);
        map.put("verifykey", empRegister.getVerifykey());
//        map.put("empList", empservice.getAllEmployees());
        map.put("message","you huv successfully registered ");
        return "indexPage";
    }
    
    @RequestMapping(value="/EmpLogin.htm", method = RequestMethod.POST)
    public String doActionsLogin(@ModelAttribute EmployeeLogin empLogin, BindingResult result, @RequestParam String action, Map<String, Object> map)
    {                
        Employees empResult = new Employees();
        
        if(action.toLowerCase().equals("login")) 
        {
//            empLogin.setLpassword(encoder.encode(empLogin.getLpassword()));
            Employees findEmployeeResult = empservice.findEmployeeById(empLogin.getLeid());
            
            if(findEmployeeResult!=null&&Objects.equals(findEmployeeResult.getVerifykey(), empLogin.getLverifykey()))
            {
                if(findEmployeeResult.getPassword().equals(empLogin.getLpassword()))
                {
                    empResult = findEmployeeResult;
                    
                    map.put("emp", empResult);
                    map.put("EmpName",empResult.getEname());
                    map.put("EmpId",empResult.getEid());
                    return "entryPage";
                }
            }
        }      
        
        map.put("empRegister", empResult);
        map.put("empLogin", empLogin);
        map.put("message","Invalid Login");
        map.put("empList", empservice.getAllEmployees());
        return "indexPage";
    }
    
    @RequestMapping(value="/offlineUsersPage.htm")
    public String doActionsOffline(@RequestParam("empId") int empId, Map<String, Object> map)
    {  
        Employees empResult = empservice.findEmployeeById(empId);
        map.put("emp",empResult);  
        map.put("EmpName",empResult.getEname());
        map.put("EmpId",empResult.getEid());   
        
        map.put("empList", empservice.getAllEmployees());
        Messages msg = msgservice.findMessageByEId(empResult.getEid());        
        map.put("msg", msg);
        return "offlineUsersPage";
    }
    
    @RequestMapping(value="/dashboard.htm")
    public String doActionsDashboard(@RequestParam("empId") int empId, Map<String, Object> map)
    {  
        Employees empResult = empservice.findEmployeeById(empId);
        
        map.put("EmpName",empResult.getEname());
        map.put("EmpId",empResult.getEid());
        map.put("emp",empResult);        
        map.put("empList", empservice.getAllEmployees());
        return "dashboard";
    }
    
    @RequestMapping(value="/broadcast.htm")
    public String doActionsBroadCast(@RequestParam("empId") int empId, Map<String, Object> map)
    {  
        Employees empResult = empservice.findEmployeeById(empId);
        
        map.put("EmpName",empResult.getEname());
        map.put("EmpId",empResult.getEid());
        map.put("emp",empResult);        
        map.put("empList", empservice.getAllEmployees());
        return "broadcast";
    }
    
    @RequestMapping(value="/discuss.htm")
    public String doActionsForumQues(@RequestParam("empId") int empId, Map<String, Object> map)
    {  
        Employees empResult = empservice.findEmployeeById(empId);
        
        map.put("EmpName",empResult.getEname());
        map.put("EmpId",empResult.getEid());
        map.put("emp",empResult);    
        
        map.put("quesList", fqservice.getAllQuestions());
        return "discuss";
    }
    
    @RequestMapping(value="/PostQues.htm", method = RequestMethod.POST)
    public String doPostForumQues(@RequestParam("empId") int empId, @RequestParam String action, @RequestParam("question") String question,Map<String,Object> map)
    {     
        Forumques question1 = new Forumques();
        Employees empResult = empservice.findEmployeeById(empId);
        
        if(action.toLowerCase().equals("post")) 
        {
            question1.setQuestion(question);
            question1.setPostEid(empResult);
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            question1.setDatePost(date);
            fqservice.addQues(question1);            
        
        map.put("EmpName",empResult.getEname());
        map.put("EmpId",empResult.getEid());
        map.put("emp",empResult);    
            map.put("quesList", fqservice.getAllQuestions());
        }  
          return "discuss";
    }
    
    @RequestMapping(value="/ans.htm")
    public String doActionsForumAnswer(@RequestParam("empId") int empId, @RequestParam("quesId") int quesId, Map<String, Object> map)
    {  
        Employees empResult = empservice.findEmployeeById(empId);
        
        map.put("EmpName",empResult.getEname());
        map.put("EmpId",empResult.getEid());
        map.put("emp",empResult);    
        
        Forumques quesResult = fqservice.findQuesbyId(quesId);
        map.put("ques",quesResult);                    
        map.put("ansList", fqservice.getAllAnswersByQuestion(quesResult)); 
        return "ans";
    }
    
    @RequestMapping(value="/PostAns.htm", method = RequestMethod.POST)
    public String doPostForumAnswer(@RequestParam("empId") int empId, @RequestParam("qId") int qId, @RequestParam String action, @RequestParam("answer") String answer, Map<String,Object> map)
    {   
        Forumanswer ans = new Forumanswer();
        
        Employees empResult = empservice.findEmployeeById(empId);
        Forumques quesResult = fqservice.findQuesbyId(qId);
        
        if(action.toLowerCase().equals("post")) 
        {
            ans.setAnswer(answer);
            ans.setPostEid(empResult);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            
            ans.setQid(quesResult);
            ans.setDatePost(date);
            ans.setRateEid(empResult);
            ans.setRate(0);
            
            faservice.addAnswer(ans); 
        }  
        
        map.put("EmpName",empResult.getEname());
        map.put("EmpId",empResult.getEid());
        map.put("emp",empResult);    
        map.put("ques",quesResult);    
        map.put("ansList", fqservice.getAllAnswersByQuestion(quesResult));
        
        return "ans";
    }
    
    @RequestMapping(value="/chatRoom.htm")
    public String doActionsChatRoom(@RequestParam("empId") int empId, Map<String, Object> map)
    {  
        Employees empResult = empservice.findEmployeeById(empId);
        
        map.put("EmpName",empResult.getEname());
        map.put("EmpId",empResult.getEid());
        map.put("emp",empResult);    
        return "entryPage";
    }
    
    @RequestMapping(value="/logoutSuccess.htm")
    public String doActionsLogout(@RequestParam("empId") int empId, Map<String, Object> map)
    {  
        return "logoutSuccess";
    }
}