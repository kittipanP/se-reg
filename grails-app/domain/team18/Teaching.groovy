package team18

class Teaching {
	//Integer //id
	String time
	//String semester
	//String year
	static belongsTo = [t: Teacher,r : Room,sub : Subject]
	String toString() {
    	return "$s.title, $t.fname, $r.r_id"
    }
    static constraints = {
    }
}
