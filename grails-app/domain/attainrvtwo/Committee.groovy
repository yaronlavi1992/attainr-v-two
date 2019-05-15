package attainrvtwo

class Committee {

    CommitteeOf name

    static hasMany = [summaries: Summary, users: User]
    static belongsTo = [department: Department]

    static constraints = {
        name()
        department()
        summaries(nullable: true)
    }

    @Override
    String toString() {
        return getName()
    }
}
