package attainrvtwo

class User {

    String name
    String password
    Long phone
    String email
    RoleOf role
    PermissionOf permission

    static hasMany = [purchases: Purchase]
    static belongsTo = [committee: Committee]

    static constraints = {
        phone(unique: true)
        name()
        password(password: true)
        email(nullable: true, email: true)
        committee(nullable: true)
    }

    @Override
    String toString() {
        return name
    }
}
