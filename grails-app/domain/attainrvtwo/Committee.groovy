package attainrvtwo

class Committee {

    String name

    static hasMany = [users:User, summaries:Summary]

    static constraints = {
        name(unique:true)
        users(nullable: true)
        summaries(nullable: true)
    }
}
