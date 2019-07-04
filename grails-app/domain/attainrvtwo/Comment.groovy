package attainrvtwo

class Comment {

    String user
    String content

    static belongsTo = [purchase: Purchase]
    static hasOne = [file: MyFile]

    static constraints = {
        user()
        content()
        file(nullable: true)
    }

    @Override
    String toString() {
        return getUser()
    }
}
