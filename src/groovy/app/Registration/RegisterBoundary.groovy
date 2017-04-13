package app.Registration

import com.vaadin.server.*
import com.vaadin.terminal.*
import com.vaadin.ui.*
import com.vaadin.ui.MenuBar.MenuItem
import com.vaadin.shared.ui.MarginInfo
import com.vaadin.annotations.*
import com.vaadin.data.*

import java.util.*
import com.vaadin.grails.Grails


@Title("Registration Zone")
@Theme("team18")
class RegistrationBoundary extends UI {

    @Override

    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout page = new VerticalLayout()
        Button b = new Button("OK 123")
        page.addComponent(b)
        b.addClickListener( { event ->
        		println("test")
        	} as Button.ClickListener)
        this.setContent(page)
    }
}
