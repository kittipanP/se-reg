package app.Result

import com.vaadin.server.*
import com.vaadin.terminal.*
import com.vaadin.ui.*
import com.vaadin.ui.MenuBar.MenuItem
import com.vaadin.shared.ui.MarginInfo
import com.vaadin.annotations.*
import com.vaadin.data.*

import app.*
import app.Result.*
import team18.*

import java.util.*
import com.vaadin.grails.Grails


@Title("Result system")
@Theme("team18")
class MyUI extends UI {

    String getUserLogin(){
        Object c = UI.getCurrent().getSession().getAttribute("login")
        return c
    }
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        def demoUser = Student.executeQuery("from Student where id = 1")
        for(demo in demoUser){
            UI.getCurrent().getSession().setAttribute("login",demo)
        }
        //Object c = getUserLogin()

        ResultController controller = new ResultController()
        VerticalLayout body = new VerticalLayout()
        body.setWidth("100%")
        VerticalLayout page = new VerticalLayout()
        page.setSpacing(true)
        page.setMargin(true)
        page.setWidth("80%")
        body.addComponent(page)
        body.setComponentAlignment(page,Alignment.TOP_CENTER)
        HorizontalLayout headLayout = new HorizontalLayout()
        headLayout.setWidth("100%")
        headLayout.setHeight("100px")
        VerticalLayout titleLayout = new VerticalLayout()
        titleLayout.setWidth("100%")
        ThemeResource imgadd = new ThemeResource("img/sut.png")
        Image img = new Image("\n",imgadd)
        //img.setSizeFull()
        img.setWidth("60px")
        img.setHeight("80px")
        headLayout.addComponent(img)
        headLayout.addComponent(titleLayout)
        headLayout.setComponentAlignment(img,Alignment.TOP_LEFT)
        headLayout.setComponentAlignment(titleLayout,Alignment.MIDDLE_LEFT)
        headLayout.setExpandRatio(img,2.0f)
        headLayout.setExpandRatio(titleLayout,8.0f)
        Label titleLabel = new Label("Registration System")
        titleLabel.setWidth("100%")
        titleLabel.addStyleName("h2")
        Label sutLabel = new Label("Suranaree University of Technology")
        sutLabel.setWidth("100%")
        //sutLabel.addStyleName("h2")
        titleLayout.addComponent(titleLabel)
        titleLayout.addComponent(sutLabel)

        titleLayout.setComponentAlignment(titleLabel,Alignment.MIDDLE_LEFT)
        titleLayout.setComponentAlignment(sutLabel,Alignment.MIDDLE_LEFT)

        Button homeButton = new Button("Home")
        homeButton.setWidth("100px")

        Resource res = new ExternalResource("http://localhost:8080/team18/")
        final ResourceReference rr = ResourceReference.create(res, content, "email")
        homeButton.addClickListener({ event -> 
	        	Page.getCurrent().open(rr.getURL(), null)
		} as Button.ClickListener)

        /*homeButton.addClickListener({event -> 

        	} as Button.ClickListener)*/
        HorizontalLayout gMenuLayout = new HorizontalLayout()
        gMenuLayout.setWidth("100%")
        gMenuLayout.setSpacing(true)
		gMenuLayout.addComponent(homeButton)
        page.addComponent(headLayout)
        page.addComponent(gMenuLayout)
        VerticalLayout profileLayout = new VerticalLayout()
        profileLayout.setMargin(true)
        Panel profilePanel = new Panel("STUDENT PROFILE")
        profilePanel.setContent(profileLayout)
        HorizontalLayout menuLayout = new HorizontalLayout()
        menuLayout.setSpacing(true)
        menuLayout.setMargin(true)
        Panel menuPanel = new Panel("OPTIONAL MENU")
        menuPanel.setContent(menuLayout)
        page.addComponent(profilePanel)
        page.addComponent(menuPanel)
        ComboBox pick = new ComboBox("pick a semester / year")
        pick.setNullSelectionAllowed(false)
        pick.setWidth("200px")
        menuLayout.addComponent(pick)
        Button searchButton = new Button("SHOW")
        searchButton.setWidth("100px")
        menuLayout.addComponent(searchButton)
        menuLayout.setComponentAlignment(searchButton,Alignment.BOTTOM_LEFT)
        searchButton.addClickListener({ event ->
            if(pick.getValue() != null)
                controller.chooseSem(pick.getValue().sem)
        } as Button.ClickListener)
        Panel rrPanel = new Panel("REGISTERED AND RESULT")
        VerticalLayout rrLayout = new VerticalLayout()
        rrLayout.setWidth("100%")
        rrPanel.setContent(rrLayout)
        HorizontalLayout resultLayout = new HorizontalLayout()
        resultLayout.setWidth("100%")

        Grid regisGrid = new Grid()
        regisGrid.setHeight("300px")
        regisGrid.setWidth("100%")
        regisGrid.addColumn("semester", String.class)
        regisGrid.addColumn("subject ID", String.class)
        regisGrid.addColumn("title", String.class)
        regisGrid.addColumn("credit", Integer.class)
        regisGrid.addColumn("grade", String.class)
        rrLayout.addComponent(regisGrid)
        rrLayout.addComponent(resultLayout)
        Grid resultS = new Grid()
        resultS.setHeight("90px")
        resultS.setWidth("100%")
        resultS.addColumn("Credit Reg", Integer.class)
        resultS.addColumn("Credit Earn", Integer.class)
        resultS.addColumn("GPA", Double.class)
        Grid resultY = new Grid()
        resultY.setHeight("90px")
        resultY.setWidth("100%")
        resultY.addColumn("Credit Reg", Integer.class)
        resultY.addColumn("Credit Earn", Integer.class)
        resultY.addColumn("GPAX", Double.class)
        resultLayout.addComponent(resultS)
        resultLayout.addComponent(resultY)
        resultLayout.setExpandRatio(resultS,1.0f)
        resultLayout.setExpandRatio(resultY,1.0f)
        page.addComponent(rrPanel)
        controller.setRrLayout(rrLayout)
        controller.setResultLayout(resultLayout)
        controller.setProfileLayout(profileLayout)
        //controller.setPickS(pickS)
        controller.setPick(pick)
        controller.setRegisGrid(regisGrid)
        controller.setResultS(resultS)
        controller.setResultY(resultY)

        controller.loadProfile()
        controller.loadReg()
        controller.loadResult()
        println("loaded Page.")
        this.setContent(body)
    }
}
