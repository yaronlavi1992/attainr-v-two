package attainrvtwo

import grails.gorm.services.Service

@Service(Summary)
interface SummaryService {

    Summary get(Serializable id)

    List<Summary> list(Map args)

    Long count()

    void delete(Serializable id)

    Summary save(Summary summary)

}