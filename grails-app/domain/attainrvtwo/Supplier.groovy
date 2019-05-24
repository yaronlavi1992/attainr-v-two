package attainrvtwo

class Supplier {

    String businessName
    Long phone
    String email

    static belongsTo = [quote: Quote]

    static constraints = {
        phone()
        businessName(nullable: true)
        email(nullable: true, email: true)
    }
}
