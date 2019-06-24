package attainrvtwo

class DepartmentManagerController {

    def index() { // show all purchases of the session's department where purchase is not COMPLETE
        List<Committee> committeeList = Committee.findAllByDepartment(session.department)
        List<User> userList = User.findAllByCommitteeInList(committeeList)
        List<Purchase> purchaseList = Purchase.findAllByUserInList(userList)
        purchaseList = purchaseList.findAll{it.status != PurchaseStatus.COMPLETE}
        respond purchaseList, model: [purchaseCount: purchaseList.size()]
    }
}
