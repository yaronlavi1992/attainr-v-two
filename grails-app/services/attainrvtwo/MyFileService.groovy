package attainrvtwo

import grails.gorm.services.Service

@Service(MyFile)
interface MyFileService {

    MyFile get(Serializable id)

    List<MyFile> list(Map args)

    Long count()

    void delete(Serializable id)

    MyFile save(MyFile myFile)

}