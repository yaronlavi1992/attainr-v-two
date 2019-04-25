package attainrvtwo

class CommitteeManagerController {

    def index() {
        def userList = User.findAllByCommittee(session.committee)
        List<Purchase> purchaseList = Purchase.findAllByUserInList(userList)
        respond purchaseList, model:[purchaseCount: purchaseList.size()]
    }
}
