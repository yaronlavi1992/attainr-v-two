package attainrvtwo

import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import java.security.spec.KeySpec

class User {

    String name
    String password
    String phone
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
