package attainrvtwo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CommitteeServiceSpec extends Specification {

    CommitteeService committeeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Committee(...).save(flush: true, failOnError: true)
        //new Committee(...).save(flush: true, failOnError: true)
        //Committee committee = new Committee(...).save(flush: true, failOnError: true)
        //new Committee(...).save(flush: true, failOnError: true)
        //new Committee(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //committee.id
    }

    void "test get"() {
        setupData()

        expect:
        committeeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Committee> committeeList = committeeService.list(max: 2, offset: 2)

        then:
        committeeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        committeeService.count() == 5
    }

    void "test delete"() {
        Long committeeId = setupData()

        expect:
        committeeService.count() == 5

        when:
        committeeService.delete(committeeId)
        sessionFactory.currentSession.flush()

        then:
        committeeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Committee committee = new Committee()
        committeeService.save(committee)

        then:
        committee.id != null
    }
}
