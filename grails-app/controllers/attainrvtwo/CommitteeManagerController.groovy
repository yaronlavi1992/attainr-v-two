package attainrvtwo

class CommitteeManagerController {

    def index() {
//        println "session.name: ${session.user}"
//        User user = User.findByName(session.user)
//        println "${session.committee}"
//        Committee committee = Committee.findById(session.committee.id)
        def userList = User.findAllByCommittee(session.committee)
//        List<Purchase> purchaseList = Purchase.findAllByCommittee(userIds)*.id
        List<Purchase> purchaseList = Purchase.findAllByUserInList(userList)
//        List<Purchase> purchaseList = Purchase.createCriteria().list{
//            'user'{
//                eq('committee', ${session.committee})
//            }
//        }

//        List<Purchase> purchaseList = Purchase.findAllByUserAndName(committee, user.committee)
//        List<Purchase> purchaseList = Purchase.findAllByUser(committee == num)
//        userList.each {
//            purchaseList.addAll((List<Purchase>) Purchase.findAllByUser(it))
//        }
//        println "user $user"
//        List<Purchase> purchaseList = Purchase.findAllByUser(user)
//        println purchaseList
        respond purchaseList, model:[purchaseCount: purchaseList.size()]
    }
}
