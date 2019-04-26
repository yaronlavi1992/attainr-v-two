package attainrvtwo

import org.apache.commons.lang.ObjectUtils

class ManagementController {

    PurchaseService purchaseService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        List<Purchase> purchaseList
        if(session.user == 'שלומית') { // shows purchases approved by both committee and accountant
            purchaseList = Purchase.findAllByAccountantApprovalInList(Approval.findAllByApproved(true))
        } else if(session.user == 'אבי') { // shows purchases approved only by committee
            purchaseList = Purchase.findAllByCommitteeApprovalInList(Approval.findAllByApproved(true))
        }
        respond purchaseList, model:[purchaseCount: purchaseService.count()]
    }

    def edit(Long id) {
        respond purchaseService.get(id)
    }

    def approve(Long id) {
        Purchase purchase = purchaseService.get(id)
        if(session.user == 'שלומית') {
            Approval communityApp = new Approval()
            communityApp.approved = true
            purchase.communityApproval = communityApp
        } else if(session.user == 'אבי') {
            Approval accountantApp = new Approval()
            accountantApp.approved = true
            purchase.accountantApproval = accountantApp
        } else if(session.permission == 'בינוני') {
            Approval committeeApp = new Approval()
            committeeApp.approved = true
            purchase.committeeApproval = committeeApp
        }
        purchaseService.save(purchase)
        redirect(controller: "Purchase", action: "index")
    }

    def deny(Long id) {
        Purchase purchase = purchaseService.get(id)
        if(session.user == 'שלומית') {
            Approval communityApp = new Approval()
            communityApp.approved = false
            purchase.communityApproval = communityApp
        } else if(session.user == 'אבי') {
            Approval accountantApp = new Approval()
            accountantApp.approved = false
            purchase.accountantApproval = accountantApp
        } else if(session.permission == 'בינוני') {
            Approval committeeApp = new Approval()
            committeeApp.approved = false
            purchase.committeeApproval = committeeApp
        }
        purchaseService.save(purchase)
        redirect(controller: "Purchase", action: "index")
    }
}
