package de.codeset.gwt.client.notification.sample;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.codeset.gwt.notification.api.client.Notification;
import de.codeset.gwt.notification.api.client.Notification.NotificationOptions;
import de.codeset.gwt.notification.api.client.eventing.click.NotificationClickEvent;
import de.codeset.gwt.notification.api.client.eventing.click.NotificationClickHandler;
import de.codeset.gwt.notification.api.client.eventing.close.NotificationCloseEvent;
import de.codeset.gwt.notification.api.client.eventing.close.NotificationCloseHandler;
import de.codeset.gwt.notification.api.client.eventing.error.NotificationErrorEvent;
import de.codeset.gwt.notification.api.client.eventing.error.NotificationErrorHandler;
import de.codeset.gwt.notification.api.client.eventing.show.NotificationShowEvent;
import de.codeset.gwt.notification.api.client.eventing.show.NotificationShowHandler;

/**
 * @author MarZl
 * 
 * @see <a href="https://github.com/MarZl/">https://github.com/MarZl/</a>
 * 
 */
public class NotificationForm extends FlowPanel implements ClickHandler {

	private TextBox inputTitle;
	private ListBox inputDir;
	private CheckBox inputEnableHandlers;
	private TextArea inputBody;
	private LongBox inputDuration;
	private TextBox inputIcon;

	public NotificationForm() {
		init();
	}

	private void init() {

		Label lblTitle = new Label("title:");
		add(lblTitle);

		inputTitle = new TextBox();
		add(inputTitle);
		
		Label lblIcon = new Label("icon [url]:");
		add(lblIcon);

		inputIcon = new TextBox();
		add(inputIcon);
		
		Label lblDir = new Label("direction:");
		add(lblDir);

		inputDir = new ListBox();
		inputDir.addItem("auto");
		inputDir.addItem("ltr");
		inputDir.addItem("rtl");
		add(inputDir);

		Label lblEnableHandlers = new Label("enable handlers:");
		add(lblEnableHandlers);

		inputEnableHandlers = new CheckBox();
		add(inputEnableHandlers);

		Label lblBody = new Label("body:");
		add(lblBody);

		inputBody = new TextArea();
		add(inputBody);

		Label lblDuration = new Label("duration [ms]:");
		add(lblDuration);

		inputDuration = new LongBox();
		inputDuration.setText("5000");
		add(inputDuration);

		Button showBtn = new Button("create");
		showBtn.addClickHandler(this);
		add(showBtn);
	}

	@Override
	public void onClick(ClickEvent event) {
		String title = inputTitle.getText();
		String dir = inputDir.getValue(inputDir.getSelectedIndex());
		String body = inputBody.getText();
		String icon = inputIcon.getText();

		final Notification notification = Notification.createIfSupported(title, NotificationOptions.create().body(body).dir(dir).icon(icon));

		if (inputEnableHandlers.getValue()) {
			notification.addCloseHandler(new NotificationCloseHandler() {

				@Override
				public void onClose(NotificationCloseEvent event) {
					Window.alert("closed");
				}
			});
			notification.addClickHandler(new NotificationClickHandler() {

				@Override
				public void onClick(NotificationClickEvent event) {
					Window.alert("click");
				}
			});
			notification.addErrorHandler(new NotificationErrorHandler() {

				@Override
				public void onError(NotificationErrorEvent event) {
					Window.alert("error");
				}
			});
			notification.addShowHandler(new NotificationShowHandler() {

				@Override
				public void onShow(NotificationShowEvent event) {
					Window.alert("show");
				}
			});
		}
		
		try {
			int duration = Integer.parseInt(inputDuration.getText());

			new Timer() {

				@Override
				public void run() {
					notification.close();
				}
			}.schedule(duration);

		} catch (Exception e) {
			Window.alert("error parsing duration");
		}
	}

}
