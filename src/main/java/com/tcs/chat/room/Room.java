package com.tcs.chat.room;

import com.tcs.chat.store.MessageHandlerDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.Session;

public class Room 
{
	private static Room instance = null;
	private List<Session> sessions = new ArrayList<Session>();
        MessageHandlerDAO msgHandlerdao = new MessageHandlerDAO();

	public synchronized void join(Session session) {
		sessions.add(session);
	}

	public synchronized void leave(Session session, String leaveID) {
//                msgHandlerdao.logoutEmp(leaveID);
		sessions.remove(session);
	}

	public synchronized void sendMessage(String message) 
        {
		for (Session session : sessions) 
                {
			if (session.isOpen()) 
                        {
				try 
                                {
					session.getBasicRemote().sendText(message);
				} 
                                catch (IOException e) 
                                {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void sendMessageToOne(String fromId, String toId, String strMessage, String message, String strToSessionId) 
        {
		for(Session session : sessions) 
                {
			if (session.isOpen()) 
                        {
				try 
                                {
					if (session.getId().equals(strToSessionId)) 
                                        {
                                            msgHandlerdao.addMessage(strMessage, fromId, toId); 
                                            session.getBasicRemote().sendText(message);                                      
					}
				} 
                                catch (IOException e) 
                                {
					e.printStackTrace();
				} 
			}
		}
	}
        
        public synchronized Session findByString(String ses)
        {
                for(Session session : sessions) 
                {
			if (session.isOpen()) 
                        {
                            if (session.getId().equals(ses)) 
                            {
                                return session;
                            }
			}
		}
                return null;
        }

	public synchronized List<Session> getUserList() {
		return sessions;
	}

	public synchronized static Room getRoom() {
		if (instance == null) {
			instance = new Room();
		}
		return instance;
	}
}
