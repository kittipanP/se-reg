package team18

class Registration {
	//Integer id
	String grade
	
	static belongsTo = [stu: Student, sub: Subject, sem: Semester]
	/*String toString() {
    	return "$semester"
    }*/
    static constraints = {
    	//semester(matches:/\d{1,3}/)
    }
}
