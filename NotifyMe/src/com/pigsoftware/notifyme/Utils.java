package com.pigsoftware.notifyme;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.actionbarsherlock.view.ActionMode;

public class Utils {

	public final static String HOST = "http://192.168.1.6/nots/Server.php";
	
	public final static String HOST_ROOT = "http://192.168.1.6/nots/";

	private static final int YOUR_PI_REQ_CODE = 0;
	
	public static String USER_ID;
	
	public static Group GROUPS[]=null;
	
	public static Notification NOTS[]=null;
	
	public static boolean serverBusy = false;
	
	public static ActionMode mMode;
	
	
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
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void createNotificationAndroid(Context context) {
		Intent notificationIntent = new Intent(context, LoginActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context,
		        YOUR_PI_REQ_CODE, notificationIntent,
		        PendingIntent.FLAG_CANCEL_CURRENT);

		NotificationManager nm = (NotificationManager) context
		        .getSystemService(context.NOTIFICATION_SERVICE);

		
		
		Resources res = context.getResources();
		android.app.Notification.Builder builder = new android.app.Notification.Builder(context);

		builder.setContentIntent(contentIntent)
		            .setSmallIcon(R.drawable.nots)
		            .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.nots))
		            .setTicker("NEW NOTIFICATION")
		            .setWhen(System.currentTimeMillis())
		            .setAutoCancel(true)
		            .setContentTitle("NUEVA NOTIFICACION")
		            .setContentText("REVIZAR");
		android.app.Notification n = builder.build();

		nm.notify("NOTS",0, n);
		
	
	}
	
}
