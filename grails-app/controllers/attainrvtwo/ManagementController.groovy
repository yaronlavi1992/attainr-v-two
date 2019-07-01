package attainrvtwo

class ManagementController {

    PurchaseService purchaseService
    CommitteeService committeeService
    DepartmentService departmentService
    List<Purchase> purchaseList

    def index(Integer max) {
        purchaseList = []
        params.max = Math.min(max ?: 10, 100)
        // shows purchases approved by all except ceo
        if (session.role == RoleOf.LEISURE_AND_SECRETARY_CEO) { // yoav
            List<Department> departmentList = [DepartmentOf.LEISURE_AND_COMMUNITY, DepartmentOf.SECRETARIAT]
            departmentList.each {
                List<Committee> committeeList = Committee.findAllByDepartment(Department.findByName(it))
                List<User> userList = User.findAllByCommitteeInList(committeeList)
                purchaseList.addAll(Purchase.findAllByUserInList(userList))
            }
            if (!purchaseList.isEmpty()) {
                purchaseList = purchaseList.intersect(Purchase.findAllByCommunityApprovalInList(Approval.findAllByApproved(true)))
                purchaseList = purchaseList.findAll { it.ceoApproval?.approved != true }
            }
        } else if (session.role == RoleOf.INFANCY_AND_YOUTH_CEO) { // amit
            List<Department> departmentList = [DepartmentOf.INFANCY, DepartmentOf.CHILDREN_AND_YOUTH]
            departmentList.each {
                List<Committee> committeeList = Committee.findAllByDepartment(Department.findByName(it))
                List<User> userList = User.findAllByCommitteeInList(committeeList)
                purchaseList.addAll(Purchase.findAllByUserInList(userList))
            }
            if (!purchaseList.isEmpty()) {
                purchaseList = purchaseList.intersect(Purchase.findAllByCommunityApprovalInList(Approval.findAllByApproved(true)))
                purchaseList = purchaseList.findAll { it.ceoApproval?.approved != true }
            }
        } else if (session.role == RoleOf.MAINTENANCE_AND_SPORT_CEO) { // ran
            List<Department> departmentList = [DepartmentOf.SPORTS_CENTER, DepartmentOf.MAINTENANCE]
            departmentList.each {
                List<Committee> committeeList = Committee.findAllByDepartment(Department.findByName(it))
                List<User> userList = User.findAllByCommitteeInList(committeeList)
                purchaseList.addAll(Purchase.findAllByUserInList(userList))
            }
            if (!purchaseList.isEmpty()) {
                purchaseList = purchaseList.intersect(Purchase.findAllByCommunityApprovalInList(Approval.findAllByApproved(true)))
                purchaseList = purchaseList.findAll { it.ceoApproval?.approved != true }
            }
        } else if (session.role == RoleOf.COMMUNITY_MANAGER) {
            // shows purchases approved by both department and accountant
            purchaseList = Purchase.findAllByAccountantApprovalInList(Approval.findAllByApproved(true))
            purchaseList = purchaseList.findAll { it.communityApproval?.approved != true }
        } else if (session.role == RoleOf.COMMUNITY_ACCOUNTANT) { // shows purchases approved only by department
            purchaseList = Purchase.findAllByDepartmentApprovalInList(Approval.findAllByApproved(true))
            purchaseList = purchaseList.findAll {
                it.accountantApproval?.approved != true || it.status == PurchaseStatus.PAYMENT_REQUIRED
            } // filter purchases which aren't approved by accountant or require payment
        }
        purchaseList = purchaseList.findAll { it.status != PurchaseStatus.COMPLETE }
        respond purchaseList, model: [purchaseCount: purchaseService.count()]
    }

    def edit(Long id) {
        respond purchaseService.get(id)
    }

    def filterByCommittee() {
        Committee selectedCommittee = committeeService.get(params.id)
        List<User> userList = User.findAllByCommittee(selectedCommittee)
        purchaseList = Purchase.findAllByUserInList(userList)
        respond purchaseList, model: [purchaseCount: purchaseService.count()]
    }

    def filterByDepartment() {
        Department selectedDepartment = departmentService.get(params.id)
        List<Committee> CommitteeList = Committee.findAllByDepartment(selectedDepartment)
        List<User> userList = User.findAllByCommitteeInList(CommitteeList)
        purchaseList = Purchase.findAllByUserInList(userList)
        respond purchaseList, model: [purchaseCount: purchaseService.count()]
    }

//    def filterByDate() {
//        purchaseList = Purchase.list()
//        Date formattedStartingDate
//        Date formattedClosingDate
//        if (params.startingDate_day) {
//            // convert to Date class
//            formattedStartingDate = Date.parse('dd MM yyyy', (params.startingDate_day + " " + params.startingDate_month + " " + params.startingDate_year))
//            formattedClosingDate = Date.parse('dd MM yyyy', (params.closingDate_day + " " + params.closingDate_month + " " + params.closingDate_year))
//        }
////        purchaseList = Purchase.findAll {
////            ((it.paymentDateApproval).date >= formattedStartingDate) &&
////                    ((it.paymentCloseDateApproval).date <= formattedClosingDate)
////        }
//        purchaseList = Purchase.findAllByPaymentDateApprovalAndPaymentCloseDateApproval(Approval.findAllByDateGreaterThanEquals(formattedStartingDate),Approval.findAllByDateLessThanEquals(formattedClosingDate))
//        respond purchaseList, model: [purchaseCount: purchaseService.count()]
//    }

    def choice(Long id) {
        Purchase purchase = purchaseService.get(id)
        if (session.role == RoleOf.LEISURE_AND_SECRETARY_CEO ||
                session.role == RoleOf.MAINTENANCE_AND_SPORT_CEO ||
                session.role == RoleOf.INFANCY_AND_YOUTH_CEO) {
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
            committeeApp.approved = (params.choice)?.toBoolean() ? true : false
            purchase.departmentApproval = committeeApp
        }
        purchaseService.save(purchase)
        statusUpdate(purchase)
        redirect(controller: "user", action: "statusDisplay")
    }

    def alreadyPurchased(Long id) {
        Purchase purchase = purchaseService.get(id)
        statusUpdate(purchase)
        redirect(controller: "user", action: "statusDisplay")
    }

    def statusUpdate(Purchase purchaseInstance) {
        switch (purchaseInstance.status) {
            case PurchaseStatus.IN_PROGRESS:
                if (purchaseInstance.ceoApproval?.approved) { // ceo approved
                    purchaseInstance.status = PurchaseStatus.APPROVED
                } else if (purchaseInstance.communityApproval?.approved && purchaseInstance.totalPurchasePrice < 5000) {
                    // community manager approved and all quotes below 5000 ILS -> community manager approved
                    purchaseInstance.status = PurchaseStatus.APPROVED
                }
                break
            case PurchaseStatus.APPROVED:
                if ((params.isPurchased)?.toBoolean()) {
                    purchaseInstance.status = PurchaseStatus.PURCHASED
                    params.isPurchased = false
                }
                break
            case PurchaseStatus.PAYMENT_REQUIRED:
                if (session.role == RoleOf.COMMUNITY_ACCOUNTANT) {
                    purchaseInstance.status = PurchaseStatus.COMPLETE
                }
                break
        //TODO: add other cases such as 'payment received', 'purchase complete' etc.
        }
        purchaseService.save(purchaseInstance)
    }

    def statusComplete(Long id) {
        Purchase purchase = purchaseService.get(id)
        if (session.role == RoleOf.COMMUNITY_ACCOUNTANT) {
            purchase.status = PurchaseStatus.COMPLETE
            purchase.paymentCloseDateApproval = new Approval(approved: true)
        }
        purchaseService.save(purchase)
        redirect(controller: "user", action: "statusDisplay")
    }
}
