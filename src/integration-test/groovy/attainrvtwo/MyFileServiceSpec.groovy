package attainrvtwo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MyFileServiceSpec extends Specification {

    MyFileService myFileService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new MyFile(...).save(flush: true, failOnError: true)
        //new MyFile(...).save(flush: true, failOnError: true)
        //MyFile myFile = new MyFile(...).save(flush: true, failOnError: true)
        //new MyFile(...).save(flush: true, failOnError: true)
        //new MyFile(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //myFile.id
    }

    void "test get"() {
        setupData()

        expect:
        myFileService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<MyFile> myFileList = myFileService.list(max: 2, offset: 2)

        then:
        myFileList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        myFileService.count() == 5
    }

    void "test delete"() {
        Long myFileId = setupData()

        expect:
        myFileService.count() == 5

        when:
        myFileService.delete(myFileId)
        sessionFactory.currentSession.flush()

        then:
        myFileService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        MyFile myFile = new MyFile()
        myFileService.save(myFile)

        then:
        myFile.id != null
    }
}
