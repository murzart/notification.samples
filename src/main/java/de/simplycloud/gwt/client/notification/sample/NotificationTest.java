package de.simplycloud.gwt.client.notification.sample;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import de.simplycloud.gwt.client.notification.Notification;
import de.simplycloud.gwt.client.notification.Notification.NotificationOptions;
import de.simplycloud.gwt.client.notification.Notification.NotificationPermissionCallback;
import de.simplycloud.gwt.client.notification.NotificationPermission;
import de.simplycloud.gwt.client.notification.eventing.click.NotificationClickEvent;
import de.simplycloud.gwt.client.notification.eventing.click.NotificationClickHandler;
import de.simplycloud.gwt.client.notification.eventing.close.NotificationCloseEvent;
import de.simplycloud.gwt.client.notification.eventing.close.NotificationCloseHandler;
import de.simplycloud.gwt.client.notification.eventing.error.NotificationErrorEvent;
import de.simplycloud.gwt.client.notification.eventing.error.NotificationErrorHandler;
import de.simplycloud.gwt.client.notification.eventing.show.NotificationShowEvent;
import de.simplycloud.gwt.client.notification.eventing.show.NotificationShowHandler;

public class NotificationTest implements EntryPoint {

	@Override
	public void onModuleLoad() {

		Button btn = new Button("Request");
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
		RootPanel.get().add(btn);


		
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
		RootPanel.get().add(btn2);

		
		
		Button btn3 = new Button("create");
		btn3.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Notification.requestPermission(new NotificationPermissionCallback() {

					@Override
					public void call(NotificationPermission permission) {
						Notification noti = new Notification("HansWurst", NotificationOptions.create().body("HansI").dir("rtl").icon("http://www.personal.psu.edu/jyy5075/plant4.jpg"));
						noti.addCloseHandler(new NotificationCloseHandler() {

							@Override
							public void onClose(NotificationCloseEvent event) {
								Window.alert("closed");
							}
						});
						noti.addClickHandler(new NotificationClickHandler() {

							@Override
							public void onClick(NotificationClickEvent event) {
								Window.alert("click");
							}
						});
						noti.addErrorHandler(new NotificationErrorHandler() {

							@Override
							public void onError(NotificationErrorEvent event) {
								Window.alert("error");
							}
						});
						noti.addShowHandler(new NotificationShowHandler() {

							@Override
							public void onShow(NotificationShowEvent event) {
								Window.alert("show");
							}
						});
					}
				});
			}
		});

		RootPanel.get().add(btn3);
	}

}
