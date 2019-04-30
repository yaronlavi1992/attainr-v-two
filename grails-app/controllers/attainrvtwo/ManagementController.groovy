package attainrvtwo

import com.sun.xml.internal.bind.v2.TODO

class ManagementController {

    PurchaseService purchaseService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        List<Purchase> purchaseList
        if (session.role == 'מנהל כללי') { // shows purchases approved by all except ceo
            purchaseList = Purchase.findAllByCommunityApprovalInList(Approval.findAllByApproved(true))
        } else if (session.role == 'מנהל הקהילה') { // shows purchases approved by both committee and accountant
            purchaseList = Purchase.findAllByAccountantApprovalInList(Approval.findAllByApproved(true))
        } else if (session.role == 'חשב הקהילה') { // shows purchases approved only by committee
            purchaseList = Purchase.findAllByCommitteeApprovalInList(Approval.findAllByApproved(true))
        }
        respond purchaseList, model:[purchaseCount: purchaseService.count()]
    }

    def edit(Long id) {
        respond purchaseService.get(id)
    }

    def filterByCommittee() {
//        Committee selectedCommittee = Committee.findByName(params.committees)
        Committee selectedCommittee = Committee.findByName(selectedCommittee)
        List<User> userList = User.findAllByCommittee(selectedCommittee)
        List<Purchase> purchaseList = Purchase.findAllByUserInList(userList)
        respond purchaseList, model:[purchaseCount: purchaseService.count()]
        //TODO: fix this with the stackoverflow answer.
    }

    def choice(Long id) {
        Purchase purchase = purchaseService.get(id)
        if (session.role == 'מנהל כללי') {
            Approval ceoApp = new Approval()
            ceoApp.approved = (params.choice).toBoolean() ? true : false
            purchase.ceoApproval = ceoApp
        } else if (session.role == 'מנהל הקהילה') {
            Approval communityApp = new Approval()
            communityApp.approved = (params.choice).toBoolean() ? true : false
            purchase.communityApproval = communityApp
        } else if (session.role == 'חשב הקהילה') {
            Approval accountantApp = new Approval()
            accountantApp.approved = (params.choice).toBoolean() ? true : false
            purchase.accountantApproval = accountantApp
        } else if (session.role == 'מנהל מחלקה') {
            Approval committeeApp = new Approval()
            committeeApp.approved = (params.choice).toBoolean() ? true : false
            purchase.committeeApproval = committeeApp
        }
        purchaseService.save(purchase)
        statusUpdate(purchase)
        redirect(controller: "Purchase", action: "index")
    }

    def statusUpdate(Purchase purchaseInstance) {
        switch (purchaseInstance.status) {
            case 'בתהליך':
                if (purchaseInstance.ceoApproval?.approved) { // ceo approved
                    purchaseInstance.status = PurchaseStatus.APPROVED
                } else if (purchaseInstance.communityApproval?.approved && purchaseInstance.quotes?.asList().every{ it.totalPrice < 5000 }) { // community manager approved and all quotes below 5000 ILS
                    purchaseInstance.status = PurchaseStatus.APPROVED
                }
                //TODO: add other cases such as 'payment received', 'purchase complete' etc.
        }
        purchaseService.save(purchaseInstance)
    }
}
