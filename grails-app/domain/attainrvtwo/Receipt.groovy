package attainrvtwo

class Receipt {

    Double sum

    static hasOne = [file: MyFile]

    static belongsTo = [purchase: Purchase]

    static constraints = {
    }
}
