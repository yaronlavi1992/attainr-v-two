package attainrvtwo

import grails.gorm.services.Service

@Service(Committee)
interface CommitteeService {

    Committee get(Serializable id)

    List<Committee> list(Map args)

    Long count()

    void delete(Serializable id)

    Committee save(Committee committee)

}