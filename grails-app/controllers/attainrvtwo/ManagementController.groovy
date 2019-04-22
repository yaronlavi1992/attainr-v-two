package attainrvtwo

class ManagementController {

    PurchaseService purchaseService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond purchaseService.list(params), model:[purchaseCount: purchaseService.count()]
//        println "session.name: ${session.user}"
//        User user = User.findByName(session.user)
//        println "user $user"
//        List<Purchase> purchaseList = Purchase.findAllByUser(user)
//        println purchaseList
//        respond purchaseList, model:[purchaseCount: purchaseList.size()]
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
