package attainrvtwo

import grails.databinding.BindingFormat

class Purchase {

    String description
    PurchaseStatus status
    Approval departmentApproval
    Approval accountantApproval
    Approval communityApproval
    Approval ceoApproval
    @BindingFormat('dd-MM-yyyy')
    Date paymentDate

    static hasMany = [quotes: Quote, receipts: Receipt]
    static belongsTo = [user: User]

    static constraints = {
        description()
        status()
        departmentApproval(nullable: true)
        accountantApproval(nullable: true)
        communityApproval(nullable: true)
        ceoApproval(nullable: true)
        paymentDate(nullable: true)
//        user(display: false)
    }

    @Override
    public String toString() {
        return description
    }
}
