package attainrvtwo

class CommitteeManagerController {

    def index() {
        Committee committee = Committee.findByName(session.committee)
        List<User> userList = User.findAllByCommittee(committee)
        List<Purchase> purchaseList = Purchase.findAllByUserInList(userList)
//        purchaseList = Purchase.findAllByDepartmentApprovalInList(Approval.findAllByApproved(true))
        respond purchaseList, model:[purchaseCount: purchaseList.size()]
    }
}
