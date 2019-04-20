package attainrvtwo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SummaryServiceSpec extends Specification {

    SummaryService summaryService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Summary(...).save(flush: true, failOnError: true)
        //new Summary(...).save(flush: true, failOnError: true)
        //Summary summary = new Summary(...).save(flush: true, failOnError: true)
        //new Summary(...).save(flush: true, failOnError: true)
        //new Summary(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //summary.id
    }

    void "test get"() {
        setupData()

        expect:
        summaryService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Summary> summaryList = summaryService.list(max: 2, offset: 2)

        then:
        summaryList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        summaryService.count() == 5
    }

    void "test delete"() {
        Long summaryId = setupData()

        expect:
        summaryService.count() == 5

        when:
        summaryService.delete(summaryId)
        sessionFactory.currentSession.flush()

        then:
        summaryService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Summary summary = new Summary()
        summaryService.save(summary)

        then:
        summary.id != null
    }
}
