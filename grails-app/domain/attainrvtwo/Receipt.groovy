package attainrvtwo

class Receipt {

    Double sum

    static belongsTo = [purchase: Purchase]
    static hasMany = [payments: Payment]

    static constraints = {
    }
}
