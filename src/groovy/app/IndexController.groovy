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

import team18.*

class IndexController {
	void setPageIndex(VerticalLayout x) {
        VerticalLayout pageIndex = UI.getCurrent().getSession().setAttribute("page",x)
    }
    VerticalLayout getPageIndex() {
        VerticalLayout pageIndex = UI.getCurrent().getSession().getAttribute("page")
        return pageIndex
    }
    void login(String username,String password) {username = username.toUpperCase()
    	def login = Student.findAllWhere(username: username, password: password)
    	if(!login) {
    		println("Error")
    	}else {
    		println("Login Success")
    		def memberData  = UI.getCurrent().getSession().setAttribute("loginData",login)
    		def memberStatus  = UI.getCurrent().getSession().setAttribute("loginStatus",true)
    		Page.getCurrent().getJavaScript().execute("location.reload()")
    	}
    }
    Boolean getLogin() {
    	def loginStatus = UI.getCurrent().getSession().getAttribute("loginStatus")
    	return loginStatus
    }

}