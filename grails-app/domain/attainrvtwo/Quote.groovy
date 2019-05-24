package attainrvtwo

class Quote {

    Double pricePerUnit
    Double qty
    Double totalPrice
    String remarks

    static hasMany = [suppliers: Supplier]
    static final belongsTo = [purchase: Purchase]

    static constraints = {
        qty()
        pricePerUnit()
        totalPrice(editable: false)
        remarks(nullable: true)
    }
}
