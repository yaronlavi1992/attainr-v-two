package attainrvtwo

class MyFile {

    String fileName
    byte[] myFile

    static hasMany = [quotes: Quote, receipts: Receipt, summaries: Summary]

    static constraints = {
        fileName()
        myFile(maxSize: 2000000)
    }

    @Override
    String toString() {
        return getFileName()
    }

}