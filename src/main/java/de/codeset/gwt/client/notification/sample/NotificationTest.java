package de.codeset.gwt.client.notification.sample;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.codeset.gwt.notification.api.client.Notification;
import de.codeset.gwt.notification.api.client.Notification.NotificationPermissionCallback;
import de.codeset.gwt.notification.api.client.NotificationPermission;

/**
 * @author MarZl 
 *
 * @see <a href="https://github.com/MarZl/">https://github.com/MarZl/</a>
 * 
 */ 
public class NotificationTest extends FlowPanel implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		if(Notification.isSupported())
			init();
		else
			Window.alert("Notifications are not supported by your browser");

	}

	
	public NotificationTest() {
		setStyleName("notificationTest");
	}
	
	
	private void init() {

		RootPanel.get().add(this);
		
		Button btn = new Button("requestPermission");
		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Notification.requestPermission(new NotificationPermissionCallback() {

					@Override
					public void call(NotificationPermission permission) {
						Window.alert("permission: " + permission);

					}
				});
			}
		});
		add(btn);


		
		Button btn2 = new Button("getPermission");
		btn2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Notification.requestPermission(new NotificationPermissionCallback() {

					@Override
					public void call(NotificationPermission permission) {
						Window.alert(Notification.getPermission().toString());

					}
				});
			}
		});
		add(btn2);

		add(new NotificationForm());
	}

}
