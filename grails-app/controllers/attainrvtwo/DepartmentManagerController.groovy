package attainrvtwo

class DepartmentManagerController {

    def index() {
        List<Committee> committeeList = Committee.findAllByDepartment(session.department)
        List<User> userList = User.findAllByCommitteeInList(committeeList)
        List<Purchase> purchaseList = Purchase.findAllByUserInList(userList)
//        purchaseList = Purchase.findAllByDepartmentApprovalInList(Approval.findAllByApproved(true))
        respond purchaseList, model:[purchaseCount: purchaseList.size()]
    }
}
