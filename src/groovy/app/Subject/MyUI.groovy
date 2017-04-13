package app.Subject

import com.vaadin.server.*
import com.vaadin.terminal.*
import com.vaadin.ui.*
import com.vaadin.ui.MenuBar.MenuItem
import com.vaadin.shared.ui.MarginInfo
import com.vaadin.annotations.*
import com.vaadin.data.*

import java.util.*
import com.vaadin.grails.Grails


@Title("Subject system")
@Theme("team18")
class MyUI extends UI {

    @Override

    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout page = new VerticalLayout()
        Button b = new Button("OK")
        page.addComponent(b)
        this.setContent(page)
    }
}
