package com.pigsoftware.notifyme;

import java.util.ArrayList;

public class Utils {

	public final static String HOST = "http://192.168.1.6/Server.php";
	
	public final static String HOST_ROOT = "http://192.168.1.6/";
	
	public static String USER_ID;
	
	public static Group GROUPS[]=null;
	
	public static Notification NOTS[]=null;
	
	
	public static Group getGroup(String idGroup){
		for(Group group:GROUPS){
			if(group.GROUP_ID.equals(idGroup))
				return group;
		}		
		return null;
	}
	
	
	public static Notification[] getNotsFromGroup(String idGroup){
		ArrayList<Notification> arrayNots = new ArrayList<Notification>();
		for(Notification not:NOTS){
			if(not.GROUP_ID.equals(idGroup)){
				arrayNots.add(not);
			}
				
		}		
		
		Notification notsTempo[] = new Notification[arrayNots.size()];
		for (int i = 0; i < notsTempo.length; i++) {
			notsTempo[i] = arrayNots.get(i);
		}
		
		
		return notsTempo;
	}
	
}
