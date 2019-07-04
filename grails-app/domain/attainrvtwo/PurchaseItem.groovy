package attainrvtwo

class PurchaseItem {

    Double financialSection
    String description
    Double packPrice
    Double packQuantity
    Double externalFunding
    Double totalItemPrice

    static belongsTo = [purchase: Purchase]

    static constraints = {
        externalFunding(nullable: true)
    }
}
