package attainrvtwo

class Quote {

    String number
    String name
    Double price

    static hasOne = [file: MyFile]

    static hasMany = [suppliers: Supplier]

    static belongsTo = [purchase: Purchase]

    static constraints = {
    }
}
