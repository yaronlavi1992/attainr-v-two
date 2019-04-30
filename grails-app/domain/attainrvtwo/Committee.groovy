package attainrvtwo

class Committee {

    String name

    static hasMany = [users:User, summaries:Summary]

    static constraints = {
        name(unique:true, inList: ['ספורט','חינוך','רווחה','ניהול מערכת','ניהול קהילה'])
        users(nullable: true)
        summaries(nullable: true)
    }

    @Override
    public String toString() {
        return name
    }
}
