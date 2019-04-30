package attainrvtwo

class User {

    String name
    String password
    Long phone
    String email
    String role
    String permission

    static hasMany = [purchases:Purchase]
    static belongsTo = [committee:Committee]

    static constraints = {
        phone(unique: true)
        name()
        password(password: true)
        email(nullable: true, email: true)
        role(inList: ["מתנדב", "מנהל מחלקה", "חשב הקהילה", "מנהל הקהילה", "מנהל כללי", "מפתח המערכת"])
        permission(inList: ["נמוך", "בינוני", "גבוה"])
    }

    @Override
    String toString() {
        return name
    }
}
