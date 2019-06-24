package attainrvtwo

class CommitteeManagerController {

    def index() { // show all purchases of the session's committee where purchase is not COMPLETE
        Committee committee = Committee.findByName(session.committee)
        List<User> userList = User.findAllByCommittee(committee)
        List<Purchase> purchaseList = Purchase.findAllByUserInList(userList)
        purchaseList = purchaseList.findAll{it.status != PurchaseStatus.COMPLETE}
        respond purchaseList, model: [purchaseCount: purchaseList.size()]
    }
}
