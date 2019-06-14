package attainrvtwo

class MyFile {

    String fileName
    byte[] myFile

    static hasMany = [quotes: Quote]

    static constraints = {
        fileName()
        myFile(maxSize: 2000000)
    }

    @Override
    String toString() {
        return getFileName()
    }

}