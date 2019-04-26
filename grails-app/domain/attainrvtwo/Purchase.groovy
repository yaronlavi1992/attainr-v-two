package attainrvtwo

class Purchase {

    String description
    String status
    Approval committeeApproval
    Approval accountantApproval
    Approval communityApproval
    Approval ceoApproval

    static hasMany = [quotes:Quote, receipts:Receipt]
    static belongsTo = [user:User]

    static constraints = {
        description()
        status(inList: ['בתהליך','הושלם','נרכש','לא אושר','נדרש תשלום','אושר'])
        committeeApproval(nullable : true)
        accountantApproval(nullable : true)
        communityApproval(nullable : true)
        ceoApproval(nullable : true)
//        user(display: false)
    }

    @Override
    public String toString() {
        return description
    }
}
