package attainrvtwo

import grails.databinding.BindingFormat

class Purchase {

    String name
    String description
    PurchaseStatus status
    Double totalPurchasePrice
    Approval departmentApproval
    Approval accountantApproval
    Approval communityApproval
    Approval ceoApproval
    @BindingFormat('dd-MM-yyyy')
    Date paymentDate

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
        paymentDate(nullable: true)
    }

    @Override
    public String toString() {
        return description
    }
}
