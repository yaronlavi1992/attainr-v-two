package attainrvtwo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PurchaseItemServiceSpec extends Specification {

    PurchaseItemService purchaseItemService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PurchaseItem(...).save(flush: true, failOnError: true)
        //new PurchaseItem(...).save(flush: true, failOnError: true)
        //PurchaseItem purchaseItem = new PurchaseItem(...).save(flush: true, failOnError: true)
        //new PurchaseItem(...).save(flush: true, failOnError: true)
        //new PurchaseItem(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //purchaseItem.id
    }

    void "test get"() {
        setupData()

        expect:
        purchaseItemService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PurchaseItem> purchaseItemList = purchaseItemService.list(max: 2, offset: 2)

        then:
        purchaseItemList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        purchaseItemService.count() == 5
    }

    void "test delete"() {
        Long purchaseItemId = setupData()

        expect:
        purchaseItemService.count() == 5

        when:
        purchaseItemService.delete(purchaseItemId)
        sessionFactory.currentSession.flush()

        then:
        purchaseItemService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PurchaseItem purchaseItem = new PurchaseItem()
        purchaseItemService.save(purchaseItem)

        then:
        purchaseItem.id != null
    }
}
