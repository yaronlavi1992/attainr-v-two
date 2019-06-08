package attainrvtwo

class Summary {

    String description

    static belongsTo = [committee: Committee]
    static hasMany = [myFiles: MyFile]

    static constraints = {
        description()
        myFiles(nullable: true)
    }

    @Override
    String toString() {
        return getDescription()
    }
}
