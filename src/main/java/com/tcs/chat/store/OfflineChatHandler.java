package com.tcs.chat.store;

import java.io.IOException;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OfflineChatHandler")
public class OfflineChatHandler extends HttpServlet 
{
 String returnMessage = null;

 @Override
 protected void service(HttpServletRequest request,
 HttpServletResponse response) throws ServletException, IOException 
 {
     response.setContentType("application/json");
     
     String operation = request.getParameter("operation");
     String from = request.getParameter("from");
     String to = request.getParameter("to");
     String message = request.getParameter("message"); 
     
     try 
     {
           MessageHandlerDAO msgHdao = new MessageHandlerDAO();
           if (operation.equalsIgnoreCase("chat")) 
           {
               System.out.println("IN OfflineChatHandler chat");
               msgHdao.addMessage(message, from, to);
           }
           else if(operation.equalsIgnoreCase("broadCast"))
           {
               System.out.println("in OfflineChatHandler broadcast "+message);
               System.out.println("IN OfflineChatHandler broadCast");
               msgHdao.broadcast(message, from, to);
           }
           
            returnMessage ="";
            
           System.out.println("IN OfflineChatHandler "+returnMessage);
             
           new Gson().toJson(returnMessage, response.getWriter());    
     } 
     catch (Exception e) 
     {
         e.printStackTrace();
     }
 }

 @Override
 protected void doGet(HttpServletRequest request,
 HttpServletResponse response) throws ServletException, IOException {
 service(request, response);
 }

 @Override
 protected void doPost(HttpServletRequest request,
 HttpServletResponse response) throws ServletException, IOException {
 service(request, response);
 }

}