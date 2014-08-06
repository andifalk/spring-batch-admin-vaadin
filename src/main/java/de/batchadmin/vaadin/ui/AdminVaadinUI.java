package de.batchadmin.vaadin.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.VaadinUI;
import org.vaadin.spring.navigator.SpringViewProvider;
import org.vaadin.spring.stuff.sidebar.SideBar;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
@VaadinUI
public class AdminVaadinUI extends UI {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminVaadinUI.class);
	
	@Autowired
    SpringViewProvider viewProvider;

    @Autowired
    SideBar sideBar;
    
	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("Batch Administration");
        final HorizontalSplitPanel rootLayout = new HorizontalSplitPanel();
        rootLayout.setStyleName(Reindeer.SPLITPANEL_SMALL);
        rootLayout.setSizeFull();
        setContent(rootLayout);

        final Navigator navigator = new Navigator(this, new ViewDisplay() {
            @Override
            public void showView(View view) {
                LOGGER.info("Showing view {}", view);
                rootLayout.setSecondComponent((com.vaadin.ui.Component) view);
            }
        });
        navigator.setErrorView(new ErrorView());
        navigator.addProvider(viewProvider);
        setNavigator(navigator);

        rootLayout.setFirstComponent(sideBar);
        rootLayout.setSplitPosition(150, Unit.PIXELS);

	}
	
/*	@VaadinView(name = "")
	@UIScope
	class MyDefaultView extends VerticalLayout implements View {
		
		public MyDefaultView() {
			// TODO Auto-generated constructor stub
		}
		
		

	    @Override
	    public void enter(ViewChangeListener.ViewChangeEvent event) {
	        addComponent(new Label("Test!"));

	    }
	}	
*/	
	private class ErrorView extends VerticalLayout implements View {
        private Label message;

        ErrorView() {
            setMargin(true);
            addComponent(message = new Label());
        }

        @Override
        public void enter(ViewChangeListener.ViewChangeEvent event) {
            message.setValue(String.format("No such view: %s", event.getViewName()));
        }
    }

}
