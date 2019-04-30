package attainrvtwo

class CommitteeManagerController {

    def index() {
        List<User> userList = User.findAllByCommittee(session.committee)
        List<Purchase> purchaseList = Purchase.findAllByUserInList(userList)
        purchaseList = Purchase.findAllByCommitteeApprovalInList(Approval.findAllByApproved(true))
        respond purchaseList, model:[purchaseCount: purchaseList.size()]
    }
}
