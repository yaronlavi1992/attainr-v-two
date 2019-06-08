package attainrvtwo

class PurchaseItem {

    Double rowNum
    String description
    Double packPrice
    Double packQuantity
    Double externalFunding
    Double totalItemPrice

    static belongsTo = [purchase: Purchase]

    static constraints = {
    }
}
