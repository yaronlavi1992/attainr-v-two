package attainrvtwo

import grails.gorm.services.Service

@Service(Purchase)
interface PurchaseService {

    Purchase get(Serializable id)

    List<Purchase> list(Map args)

    Long count()

    void delete(Serializable id)

    Purchase save(Purchase purchase)

}