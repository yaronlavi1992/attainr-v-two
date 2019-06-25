package attainrvtwo

class Summary {

    String description

    static belongsTo = [committee: Committee]
    static hasOne = [file: MyFile]

    static constraints = {
        description()
    }

    @Override
    String toString() {
        return getDescription()
    }
}
