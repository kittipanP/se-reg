package app

import com.vaadin.server.*
import com.vaadin.terminal.*
import com.vaadin.ui.*
import com.vaadin.ui.MenuBar.MenuItem
import com.vaadin.shared.ui.MarginInfo
import com.vaadin.annotations.*
import com.vaadin.data.*

import java.util.*
import com.vaadin.grails.Grails


@Title("REG system")
@Theme("team18")
class MyUI extends UI {

    @Override

    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout page = new VerticalLayout()
        Link link1 = new Link("Result", new ExternalResource("http://localhost:8080/team18/Result/")) //Baze
        Link link2 = new Link("Registration", new ExternalResource("http://localhost:8080/team18/Registration/")) // Boom
        Link link3 = new Link("Subject", new ExternalResource("http://localhost:8080/team18/Subject/")) // Gus
        Link link4 = new Link("People", new ExternalResource("http://localhost:8080/team18/People/")) //Bon
        Link link5 = new Link("Demo", new ExternalResource("http://localhost:8080/team18/Demo/")) // Moss
        page.addComponent(link1)
        page.addComponent(link2)
        page.addComponent(link3)
        page.addComponent(link4)
        page.addComponent(link5)
        IndexController c = new IndexController()
        this.getSession().setAttribute("controller",c)
        //- Set Session Controller -//

        if(getController().getLogin() == null) {
            
        	HorizontalLayout layout = new HorizontalLayout()
            VerticalLayout boxRight = new VerticalLayout()
            VerticalLayout boxCenter = new VerticalLayout()
            VerticalLayout boxLeft = new VerticalLayout()
            //-Create Box-//

            Panel panelLeft = new Panel()
            Panel panelCenter = new Panel()
            Panel panelRight = new Panel()
            //-Create Panel-//

            panelLeft.setContent(boxLeft)
            panelRight.setContent(boxRight)
            panelCenter.setContent(boxCenter)
            //-Set Layout in Panel-//

            boxLeft.setWidth("200px")
            boxCenter.setWidth("250px")
            boxRight.setWidth("200px")
            boxLeft.setHeight("300px")
            boxRight.setHeight("300px")
            boxCenter.setHeight("400px")
            layout.setMargin(true);
            //-Set Properties-//

            layout.addComponent(panelLeft)
            layout.addComponent(panelCenter)
            layout.addComponent(panelRight)
            page.addComponent(layout)
            //-Set Component-//

            layout.setComponentAlignment(panelLeft,Alignment.MIDDLE_CENTER)
            layout.setComponentAlignment(panelCenter,Alignment.MIDDLE_CENTER)
            layout.setComponentAlignment(panelRight,Alignment.MIDDLE_CENTER)
            page.setComponentAlignment(layout,Alignment.MIDDLE_CENTER)
            //- Set Position -//

            boxCenter.addComponent(blockCenter())
            boxLeft.addComponent(blockSide("student"))
            boxRight.addComponent(blockSide("teacher"))

            

            getController().setPageIndex(page)
            //-Save Page to Session-/
    		setContent(getController().getPageIndex())
            //-Load Page from Session -//
            }else {
                //- login Success-//
                Button logout = new Button("Log out")
                page.addComponent(logout)
                getController().setPageIndex(page)
                //-Save Page to Session-/
                setContent(getController().getPageIndex())
                //-Load Page from Session -//
                logout.addClickListener({ event -> 
                    UI.getCurrent().getSession().setAttribute("loginData",null)
                    UI.getCurrent().getSession().setAttribute("loginStatus",null)
                    Page.getCurrent().getJavaScript().execute("location.reload()")
                } as Button.ClickListener)
                //-Create Action-//    

                
            }

        
    }
    
    public VerticalLayout blockCenter() {
        VerticalLayout layout = new VerticalLayout()
        VerticalLayout headerLayout = new VerticalLayout()
        VerticalLayout dataLayout = new VerticalLayout()
        //-Create Box-//

        Panel headerPanel = new Panel()
        Panel dataPanel = new Panel()
        //-Create Panel-//

        Label title = new Label("Lastest News")
        //-Create Label-//

        headerPanel.setContent(headerLayout)
        dataPanel.setContent(dataLayout)
        //-Set Layout in Panel-//

        layout.addComponent(headerPanel)
        layout.addComponent(dataPanel)
        dataLayout.addComponent(testConvertNews())
        headerLayout.addComponent(title)

        //-Set Component-//

        dataLayout.setSizeUndefined()
        //-Set Scrollbar in Panel-//

        headerLayout.setMargin(true)
        dataPanel.setHeight("350px")
        //-Set Properties-//

        return layout
        //-Return Component-//
    }


    public VerticalLayout blockSide(String mode) {
        VerticalLayout layout = new VerticalLayout()
        VerticalLayout headerLayout = new VerticalLayout()
        VerticalLayout dataLayout = new VerticalLayout()
        //-Create Box-//

        Panel headerPanel = new Panel()
        Panel dataPanel = new Panel()
        //-Create Panel-//

        Label title = new Label()
        if(mode == "student") {
            title.setValue("Student Zone")
        }else if(mode == "teacher"){
            title.setValue("Teacher Zone")
        }
        //-Create Label-//

        Button beforeLoginButton = new Button("Click for Login.")
        //-Create Button-//

        beforeLoginButton.addClickListener({ event -> 
                dataLayout.removeComponent(beforeLoginButton)
                dataLayout.addComponent(loginBlock())
            } as Button.ClickListener)
        //-Create Action-//    

        headerPanel.setContent(headerLayout)
        dataPanel.setContent(dataLayout)
        //-Set Layout in Panel-//

        layout.addComponent(headerPanel)
        layout.addComponent(dataPanel)
        dataLayout.addComponent(beforeLoginButton)
        headerLayout.addComponent(title)
        //-Set Component-//

        dataLayout.setMargin(true)
        dataLayout.setComponentAlignment(beforeLoginButton,Alignment.MIDDLE_CENTER)
        //- Set Position -//

        headerLayout.setMargin(true)
        //-Set Properties-//

        return layout
        //-Return Component-//
    }

    public VerticalLayout loginBlock() {
        VerticalLayout layout = new VerticalLayout()
        HorizontalLayout buttonLayout = new HorizontalLayout()
        //-Create Box-//


        TextField username = new TextField("Username")
        PasswordField password = new PasswordField("Password")
        Button confirm = new Button("Login")
        Button cancel = new Button("Back")
        Button beforeLoginButton = new Button("Click for Login.")
        //-Create Objects-//

        layout.addComponent(username)
        layout.addComponent(password)
        layout.addComponent(new Label(""))
        buttonLayout.addComponent(confirm)
        buttonLayout.addComponent(new Label(" || "))
        buttonLayout.addComponent(cancel)
        layout.addComponent(buttonLayout)
        //-Set Layout-//

        layout.setComponentAlignment(buttonLayout,Alignment.MIDDLE_CENTER)
        
        cancel.addClickListener({ event -> 
                layout.removeAllComponents()
                
                layout.addComponent(beforeLoginButton)
                layout.setComponentAlignment(beforeLoginButton,Alignment.MIDDLE_CENTER)
                
            } as Button.ClickListener)
        confirm.addClickListener({ event -> 
                String strUser = username.getValue()
                String srtPass = password.getValue()
                getController().login(strUser,srtPass)
                
            } as Button.ClickListener)
        beforeLoginButton.addClickListener({ event -> 
                    layout.removeComponent(beforeLoginButton)
                    layout.addComponent(loginBlock())
                } as Button.ClickListener)
        //-Create Action-//    

        return layout
    }

    public VerticalLayout testConvertNews() {
        VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        int x=8,y=2
        String[][] arr = new String[x][y];
        int i,j=0
        for(i=0;i<x;i++) {
            arr[i][j++] = "Title " + (i+1)
            arr[i][j--] = "Data " + (i+1)
        }
        j=0
        for(i=0;i<x;i++) {
            layout.addComponent(new Label("News title : " + arr[i][j++]))
            layout.addComponent(new Label("...." + arr[i][j--]))
        }
        return layout
    }
    IndexController getController() {
        Object c = UI.getCurrent().getSession().getAttribute("controller")
        return (IndexController)c
    }
}
