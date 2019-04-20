package attainrvtwo

import grails.gorm.services.Service

@Service(Quote)
interface QuoteService {

    Quote get(Serializable id)

    List<Quote> list(Map args)

    Long count()

    void delete(Serializable id)

    Quote save(Quote quote)

}