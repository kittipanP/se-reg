class BootStrap {

    def init = { servletContext ->
    	//new team18.Student(username:"B5800000",password: "123",email:"b5800000@testing.in.th",status:1,gender:2,name:"คฤจภัค คิศถฤงคิษแคธ",nameEng:"KitchaPik Kittiungkitkat").save()
    	//new team18.Student(username:"B5800001",password: "123",email:"b5800001@testing.in.th",status:1,gender:1,name:"สมชาย ใจดี",nameEng:"Somchai Jaidee").save()
    	def r1 = new team18.Room(r_id: "B4101", capacity: 2000, building: "B").save()
    	println("Installed Room Database")
    //=============================================================================================================================================
    	def stu1 = new team18.Student(stu_id: "B5618514", pw: "1234", email: "Wazjakorn@gmail.com", sex: "male", fname: "Wadjakorn", lname: "Tonsri", tel: "081-954-3611").save()
        def stu2 = new team18.Student(stu_id: "B5618514", pw: "1234", email: "Wazjakorn@gmail.com", sex: "male", fname: "XXXXXXXXX", lname: "YYYYYY", tel: "081-954-3611").save()
    	println("Installed Student Database")
    //=============================================================================================================================================
    	def sub1 = new team18.Subject(sub_id: "523101", title: "Computer Programming I", credit: 2, inst: "Engineering", sch: "Computer Engineering", prevsub: "-").save()
        def sub2 = new team18.Subject(sub_id: "523102", title: "Computer Programming II", credit: 2, inst: "Engineering", sch: "Computer Engineering", prevsub: "523101").save()
        def sub3 = new team18.Subject(sub_id: "523103", title: "Computer Programming III", credit: 2, inst: "Engineering", sch: "Computer Engineering", prevsub: "523102").save()
        
        def sub4 = new team18.Subject(sub_id: "103101", title: "Calculus I", credit: 4, inst: "-", sch: "-", prevsub: "-").save()
        def sub5 = new team18.Subject(sub_id: "105101", title: "PHYSICS I", credit: 4, inst: "-", sch: "-", prevsub: "-").save()
        def sub6 = new team18.Subject(sub_id: "105113", title: "MAN AND TECHNOLOGY", credit: 3, inst: "-", sch: "-", prevsub: "-").save()
        def sub7 = new team18.Subject(sub_id: "105191", title: "PHYSICS LABORATORY I", credit: 1, inst: "-", sch: "-", prevsub: "-").save()
        def sub8 = new team18.Subject(sub_id: "202107", title: "USE OF COMPUTER AND INFORMATION", credit: 3, inst: "-", sch: "-", prevsub: "-").save()
        def sub9 = new team18.Subject(sub_id: "525101", title: "ENGINEERING GRAPHICS I", credit: 2, inst: "Engineering", sch: "-", prevsub: "-").save()

        def sub10 = new team18.Subject(sub_id: "103102", title: "CALCULUS II", credit: 4, inst: "-", sch: "-", prevsub: "103101").save()
        def sub11 = new team18.Subject(sub_id: "104113", title: "MAN AND ENVIRONMENT", credit: 3, inst: "-", sch: "-", prevsub: "-").save()
        def sub12 = new team18.Subject(sub_id: "105102", title: "PHYSICS II", credit: 4, inst: "-", sch: "-", prevsub: "105101").save()
        def sub13 = new team18.Subject(sub_id: "105192", title: "PHYSICS LABORATORY II", credit: 1, inst: "-", sch: "-", prevsub: "105191").save()
        def sub14 = new team18.Subject(sub_id: "203101", title: "ENGLISH I", credit: 3, inst: "Social Technology", sch: "-", prevsub: "-").save()
        def sub15 = new team18.Subject(sub_id: "531101", title: "ENGINEERING MATERIALS", credit: 4, inst: "-", sch: "-", prevsub: "-").save()
    	println("Installed Subject Database")
    //=============================================================================================================================================
    	def t1 = new team18.Teacher(t_id: "T01", fname: "McGraw", lname: "James", pw: "0000", email: "JamesMcGraw@gmail.com", sex: "male", tel: "086-451-7873").save()
    	println("Installed Teacher Database")
    //=============================================================================================================================================
    	def teaching1 = new team18.Teaching(time: "Mon 08.00-10.00", t: t1, r: r1, sub: sub1).save()
        def teaching2 = new team18.Teaching(time: "Tue 13.00-17.00", t: t1, r: r1, sub: sub1).save()
        def teaching3 = new team18.Teaching(time: "Wed 08.00-10.00", t: t1, r: r1, sub: sub2).save()
        //ยังไม่ใส่ database เพิ่มในส่วนนี้
    	println("Installed Teaching Database")
    //=============================================================================================================================================
        def sem1 = new team18.Semester(sem: "1/2015").save()
        def sem2 = new team18.Semester(sem: "2/2015").save()
        def sem3 = new team18.Semester(sem: "3/2015").save()
        def sem4 = new team18.Semester(sem: "1/2013").save()
        def sem5 = new team18.Semester(sem: "2/2013").save()
        def sem6 = new team18.Semester(sem: "3/2013").save()
        def semall = new team18.Semester(sem: "all semester").save()
    //=============================================================================================================================================
    	//new team18.Registration(stu: stu1, sem: sem1, grade: "B+", sub: sub1).save()
        //new team18.Registration(stu: stu1, sem: sem2, grade: "A", sub: sub2).save()
        new team18.Registration(stu: stu1, sem: sem4, grade: "C+", sub: sub4).save()
        new team18.Registration(stu: stu1, sem: sem4, grade: "B", sub: sub5).save()
        new team18.Registration(stu: stu1, sem: sem4, grade: "A", sub: sub6).save()
        new team18.Registration(stu: stu1, sem: sem4, grade: "B", sub: sub7).save()
        new team18.Registration(stu: stu1, sem: sem4, grade: "B", sub: sub8).save()
        new team18.Registration(stu: stu1, sem: sem4, grade: "A", sub: sub9).save()

        new team18.Registration(stu: stu1, sem: sem5, grade: "C+", sub: sub10).save()
        new team18.Registration(stu: stu1, sem: sem5, grade: "B+", sub: sub11).save()
        new team18.Registration(stu: stu1, sem: sem5, grade: "A", sub: sub12).save()
        new team18.Registration(stu: stu1, sem: sem5, grade: "B+", sub: sub13).save()
        new team18.Registration(stu: stu1, sem: sem5, grade: "A", sub: sub14).save()
        new team18.Registration(stu: stu1, sem: sem5, grade: "B+", sub: sub15).save()
        println("Installed Registration Database")
    //=============================================================================================================================================

    //=============================================================================================================================================
    }
    def destroy = {
    }
}
