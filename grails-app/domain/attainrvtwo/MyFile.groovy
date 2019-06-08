package attainrvtwo

class MyFile {

    String fileName
    byte[] myFile

    static belongsTo = [summary: Summary]

    static constraints = {
        fileName()
        myFile(maxSize: 1024 * 1024 * 20)
    }

    @Override
    String toString() {
        return getFileName()
    }

}