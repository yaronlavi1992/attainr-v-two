package attainrvtwo

class Department {

    DepartmentOf name

    static hasMany = [committees: Committee]

    static constraints = {
    }

    @Override
    String toString() {
        return getName()
    }
}
