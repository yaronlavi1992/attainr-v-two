package attainrvtwo

import grails.gorm.services.Service

@Service(PurchaseItem)
interface PurchaseItemService {

    PurchaseItem get(Serializable id)

    List<PurchaseItem> list(Map args)

    Long count()

    void delete(Serializable id)

    PurchaseItem save(PurchaseItem purchaseItem)

}