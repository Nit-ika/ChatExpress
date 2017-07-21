package com.tcs.chat.room;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint(value = "/joinRoom")
public class ChatEndpoint 
{    
	private Logger log = Logger.getLogger(ChatEndpoint.class.getSimpleName());
	private Room room = Room.getRoom();
	private static final Set<Room> connections = new CopyOnWriteArraySet<Room>();
	private Session session;

	@OnOpen
	public void open(final Session session, EndpointConfig config) 
        {
            int i=0;
            for(Session ses : session.getOpenSessions())
            {
                System.out.println("open --> i "+i+" "+ses);
                i++;
            }
	}

	@OnMessage
	public void onMessage(final Session session, final String messageJson) 
        {
		ObjectMapper mapper = new ObjectMapper();
                System.out.println("onMessage 1");
		ChatMessage chatMessage = null;
		try 
                {
                System.out.println("onMessage 2");
			chatMessage = mapper.readValue(messageJson, ChatMessage.class);
		}
                catch (IOException e) 
                {
                System.out.println("onMessage catch 1");
			String message = "Badly formatted message";
			try 
                        {
                System.out.println("onMessage catch 2");
				session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, message));
			} 
                        catch (IOException ex) 
                        {
                System.out.println("onMessage catch catch");
				log.severe(ex.getMessage());
			}
		}
                
                System.out.println("onMessage 3");
		Map<String, Object> properties = session.getUserProperties();
                
                System.out.println("onMessage 4");                 
		if (chatMessage.getMessageType().equals(MessageType.LOGIN))
                {
                    
                System.out.println("onMessage Login 5");
			String name = chatMessage.getMessage();
			properties.put("id_name", name);
                        
                        
                System.out.println("onMessage Login 6 "+name);
                        
			room.join(session);
			room.sendMessage("join^" +name +"^"+ session.getId());                        
                        
                System.out.println("onMessage Login 7 ");
                        
			this.session = session;
			connections.add(room);
                        
                        
                System.out.println("onMessage Login 8 ");
			sendBroadcast();
                        
                System.out.println("onMessage Login 9 end ");
		} 
                else if(chatMessage.getMessageType().equals(MessageType.MESSAGE)) 
                {
                    
                System.out.println("onMessage MSG 5 ");
			String strMsg = chatMessage.getMessage();
			String strFrom = chatMessage.getFromPerson();
			String strTo = chatMessage.getChatPerson();
			String strToSession = chatMessage.getChatPersonSession();
			
                System.out.println("onMessage MSG 6 "+ strToSession+"  "+strTo+"  "+strFrom+" "+strMsg);
                        
			sendToOne(strToSession, strTo, strFrom, strMsg);	
                System.out.println("onMessage MSG 7 ");		
		}
	}

	private void sendBroadcast() 
        {
                System.out.println("onMessage BRoadCast 1 ");
		for(Session ses : room.getUserList()) 
                {
                System.out.println("onMessage BRoadCast 2 ");
                    String str = ses.getUserProperties().get("id_name").toString();
                    
                System.out.println("onMessage BRoadCast 3 ");
                    String strSessionId = ses.getId();
                    room.sendMessage("join^"+ str +"^"+ strSessionId);
                    
                System.out.println("onMessage BRoadCast 4 join^"+ str +"^"+strSessionId);
		}
	}
        
	public void sendToOne(String strCurSessionId,  String strTo, String strFrom, String strMessage)
        {
            String strMsg = "msg^" + strFrom + "^" + strTo + "^" + strMessage;
            
            Session strFromSession = room.findByString(strFrom);
            strFrom = (String) strFromSession.getUserProperties().get("id_name");
            
            String[] detailsFrom = strFrom.split("-");
            String fromId = detailsFrom[0];            
            
            String[] detailsTo = strTo.split("-");
            String toId = detailsTo[0];
            
            System.out.println("sendToOne "+strMsg+" --> "+strFrom+" == "+fromId+" == "+toId);
            
            room.sendMessageToOne(fromId, toId, strMessage, strMsg, strCurSessionId);
	}

	@OnClose
	public void onClose(Session session, CloseReason reason) 
        {
            System.out.println("onclose 1 ");
            String strSessionId = session.getId();
                
                
                String leave = (String) session.getUserProperties().get("id_name");
                String[] details = leave.split("-");
                String leaveId = details[0];
                
		room.leave(session, leaveId);
                
            System.out.println("onclose 2 ");
		room.sendMessage("left^" + leave +"^"+ strSessionId);
	    System.out.println("onclose 3 ");
        }

	@OnError
	public void onError(Session session, Throwable ex) 
        {
                System.out.println(" OnError 1 ");
		log.info("Error: " + ex.getMessage());
                System.out.println(" OnError 2 ");
	}
}
