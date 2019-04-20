package attainrvtwo

class VolunteerController {

    def index() {
//        println "session.name: ${session.user}"
        User user = User.findByName(session.user)
//        println "user $user"
        List<Purchase> purchaseList = Purchase.findAllByUser(user)
//        println purchaseList
        respond purchaseList, model:[purchaseCount: purchaseList.size()]
    }



}
