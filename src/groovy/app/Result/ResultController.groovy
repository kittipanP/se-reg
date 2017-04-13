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

class ResultController {
    VerticalLayout rrLayout
    HorizontalLayout resultLayout
    VerticalLayout profileLayout
    ComboBox pick
    Grid regisGrid
    Grid resultS
    Grid resultY
    def reg = Registration.executeQuery("from Registration")
    Student user = UI.getCurrent().getSession().getAttribute("login")
    def student = Student.executeQuery("from Student")
    public void loadProfile(){  
        Label name
        Label id
        for(s in student){
            if(s.id == user.id){
                name = new Label(s.fname +"  "+ s.lname)
                id = new Label(s.stu_id)
            }
        }
        name.setContentMode(Label.CONTENT_XHTML)
        name.setWidth("100%")
        id.setWidth("100%")

        profileLayout.addComponent(id)
        profileLayout.addComponent(name)
        
        profileLayout.setComponentAlignment(name,Alignment.TOP_CENTER)
        profileLayout.setComponentAlignment(id,Alignment.TOP_CENTER)
        //profileLayout.setExpandRatio(name)
    }
    public void loadReg(){
        def semall = Semester.executeQuery("from Semester where sem like :s",[s: "%all semester%"])
        for(s in semall){
            pick.addItem(s)
        }

        for(r in reg){
            if(r.stu.id == user.id){
                pick.addItem(r.sem)
            }
            
        }
    }
    public void loadResult() {
        regisGrid.getContainerDataSource().removeAllItems()
        resultY.getContainerDataSource().removeAllItems()
        int cReg = 0
        int cEarn = 0
        double gpa = 0.0
        double point = 0.0
        for(r in reg){
            if(r.stu.id == user.id){
                regisGrid.addRow(r.sem.sem, r.sub.sub_id, r.sub.title, r.sub.credit, r.grade)
                cReg += r.sub.credit
                if(r.grade != "F"){
                    cEarn += r.sub.credit
                    if(r.grade == "A"){
                        point += 4.0 * r.sub.credit
                    }else if(r.grade == "B+"){
                        point += 3.5 * r.sub.credit
                    }else if(r.grade == "B"){
                        point += 3.0 * r.sub.credit
                    }else if(r.grade == "C+"){
                        point += 2.5 * r.sub.credit
                    }else if(r.grade == "C"){
                        point += 2.0 * r.sub.credit
                    }else if(r.grade == "D+"){
                        point += 1.5 * r.sub.credit
                    }else if(r.grade == "D"){
                        point += 1.0 * r.sub.credit
                    }
                }

            }
        }
        gpa = point/cReg
        resultY.addRow(cReg,cEarn,gpa)
        println("calculated gpax")
    }
    public void chooseSem(String str){
        def reglist = Registration.executeQuery("from Registration where sem.sem like :s",[s: "%${str}%"])
        regisGrid.getContainerDataSource().removeAllItems()
        resultS.getContainerDataSource().removeAllItems()
        int cReg = 0
        int cEarn = 0
        double gpa = 0.0
        double point = 0.0
        for(r in reglist){
            if(r.stu.id == user.id){
                regisGrid.addRow(r.sem.sem, r.sub.sub_id, r.sub.title, r.sub.credit, r.grade)
                cReg += r.sub.credit
                if(r.grade != "F"){
                    cEarn += r.sub.credit
                    if(r.grade == "A"){
                        point += 4.0 * r.sub.credit
                    }else if(r.grade == "B+"){
                        point += 3.5 * r.sub.credit
                    }else if(r.grade == "B"){
                        point += 3.0 * r.sub.credit
                    }else if(r.grade == "C+"){
                        point += 2.5 * r.sub.credit
                    }else if(r.grade == "C"){
                        point += 2.0 * r.sub.credit
                    }else if(r.grade == "D+"){
                        point += 1.5 * r.sub.credit
                    }else if(r.grade == "D"){
                        point += 1.0 * r.sub.credit
                    }
                }
            }
        }
        
        if(str == "all semester"){
            loadResult()
        }else{
            gpa = point/cReg
            resultS.addRow(cReg,cEarn,gpa)
            println("calculated gpa")
        }

    }
}
