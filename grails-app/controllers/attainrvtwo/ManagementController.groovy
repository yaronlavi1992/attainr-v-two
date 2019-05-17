package attainrvtwo

class ManagementController {

    PurchaseService purchaseService
    CommitteeService committeeService
    List<Purchase> purchaseList

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
            if (session.role == RoleOf.COMMUNITY_CEO) { // shows purchases approved by all except ceo
                purchaseList = Purchase.findAllByCommunityApprovalInList(Approval.findAllByApproved(true))
            } else if (session.role == RoleOf.COMMUNITY_MANAGER) { // shows purchases approved by both committee and accountant
                purchaseList = Purchase.findAllByAccountantApprovalInList(Approval.findAllByApproved(true))
            } else if (session.role == RoleOf.COMMUNITY_ACCOUNTANT) { // shows purchases approved only by committee
                purchaseList = Purchase.findAllByDepartmentApprovalInList(Approval.findAllByApproved(true))
            }
        respond purchaseList, model:[purchaseCount: purchaseService.count()]
    }

    def edit(Long id) {
        respond purchaseService.get(id)
    }

    def filterByCommittee() {
        session.filterPurchases = true
        Committee selectedCommittee = committeeService.get(params.id)
        List<User> userList = User.findAllByCommittee(selectedCommittee)
        purchaseList = Purchase.findAllByUserInList(userList)
        respond purchaseList, model:[purchaseCount: purchaseService.count()]
    }

    def choice(Long id) {
        Purchase purchase = purchaseService.get(id)
        if ((params.isPurchased)?.toBoolean()) {
            // skip to statusUpdate
        } else if (session.role == RoleOf.COMMUNITY_CEO) {
            Approval ceoApp = new Approval()
            ceoApp.approved = (params.choice).toBoolean() ? true : false
            purchase.ceoApproval = ceoApp
        } else if (session.role == RoleOf.COMMUNITY_MANAGER) {
            Approval communityApp = new Approval()
            communityApp.approved = (params.choice).toBoolean() ? true : false
            purchase.communityApproval = communityApp
        } else if (session.role == RoleOf.COMMUNITY_ACCOUNTANT) {
            Approval accountantApp = new Approval()
            accountantApp.approved = (params.choice).toBoolean() ? true : false
            purchase.accountantApproval = accountantApp
        } else if (session.role == RoleOf.DEPARTMENT_MANAGER) {
            Approval committeeApp = new Approval()
            committeeApp.approved = (params.choice).toBoolean() ? true : false
            purchase.departmentApproval = committeeApp
        }
        purchaseService.save(purchase)
        statusUpdate(purchase)
        redirect(controller: "Purchase", action: "index")
    }

    def statusUpdate(Purchase purchaseInstance) {
        switch (purchaseInstance.status) {
            case PurchaseStatus.IN_PROGRESS:
                if (purchaseInstance.ceoApproval?.approved) { // ceo approved
                    purchaseInstance.status = PurchaseStatus.APPROVED
                } else if (purchaseInstance.communityApproval?.approved && purchaseInstance.quotes?.asList().every{ it.totalPrice < 5000 }) { // community manager approved and all quotes below 5000 ILS
                    purchaseInstance.status = PurchaseStatus.APPROVED
                }
            case PurchaseStatus.APPROVED:
                if((params.isPurchased)?.toBoolean()) {
                    purchaseInstance.status = PurchaseStatus.PURCHASED
                    params.isPurchased = false
                }
                //TODO: add other cases such as 'payment received', 'purchase complete' etc.
        }
        purchaseService.save(purchaseInstance)
    }
}
