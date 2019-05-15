package attainrvtwo

class DevController {

    PurchaseService purchaseService

    def index() { }

    def comapp(String pidString) {
        Long pid = pidString as Long
        println "comapp got id: $pid jkfdsjfkds"
        Purchase purchase = purchaseService.get(pid)
//        Purchase purchase = Purchase.findById(pid)
        Approval committeeApp = new Approval()
        committeeApp.approved = true

        purchase.departmentApproval = committeeApp
        purchaseService.save(purchase)

        //redirect (controller: "Purchase", action: "show/$pid", )
        render "Purchase: ${purchase}, ${purchase.departmentApproval}"
    }
}
