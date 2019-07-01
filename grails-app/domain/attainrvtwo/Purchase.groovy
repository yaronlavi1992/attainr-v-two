package attainrvtwo

class Purchase {

    String name
    String description
    PurchaseStatus status
    Double totalPurchasePrice
    Approval departmentApproval
    Approval accountantApproval
    Approval communityApproval
    Approval ceoApproval
    Approval paymentDateApproval
    Approval paymentCloseDateApproval

    static hasMany = [quotes: Quote, receipts: Receipt, purchaseItems: PurchaseItem]
    static belongsTo = [user: User]
    static mapping = {
        status defaultValue: PurchaseStatus.IN_PROGRESS
    }

    static constraints = {
        name()
        description(nullable: true)
        status()
        departmentApproval(nullable: true)
        accountantApproval(nullable: true)
        communityApproval(nullable: true)
        ceoApproval(nullable: true)
        paymentDateApproval(nullable: true)
        paymentCloseDateApproval(nullable: true)
    }

    @Override
    public String toString() {
        return description
    }
}
