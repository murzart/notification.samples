package de.codeset.gwt.client.notification.sample;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.codeset.gwt.client.notification.Notification;
import de.codeset.gwt.client.notification.Notification.NotificationOptions;


public class NotificationForm extends FlowPanel implements ClickHandler{


	private TextArea body;
	private TextBox title;


	public NotificationForm() {
		init();
	}


	private void init() {
		title = new TextBox();
		add(title);

		body = new TextArea();
		add(body);
		
		Button showBtn = new Button("create");
		showBtn.addClickHandler(this);
		add(showBtn);
		
		
	}


	@Override
	public void onClick(ClickEvent event) {
		String titleMsg = title.getText();
		String bodyMsg = body.getText();
		
		Notification.createIfSupported(titleMsg, NotificationOptions.create().body(bodyMsg));
	}

}
