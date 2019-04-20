package attainrvtwo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PurchaseServiceSpec extends Specification {

    PurchaseService purchaseService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Purchase(...).save(flush: true, failOnError: true)
        //new Purchase(...).save(flush: true, failOnError: true)
        //Purchase purchase = new Purchase(...).save(flush: true, failOnError: true)
        //new Purchase(...).save(flush: true, failOnError: true)
        //new Purchase(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //purchase.id
    }

    void "test get"() {
        setupData()

        expect:
        purchaseService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Purchase> purchaseList = purchaseService.list(max: 2, offset: 2)

        then:
        purchaseList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        purchaseService.count() == 5
    }

    void "test delete"() {
        Long purchaseId = setupData()

        expect:
        purchaseService.count() == 5

        when:
        purchaseService.delete(purchaseId)
        sessionFactory.currentSession.flush()

        then:
        purchaseService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Purchase purchase = new Purchase()
        purchaseService.save(purchase)

        then:
        purchase.id != null
    }
}
