package attainrvtwo

class Payment {

    String receiptName

    static belongsTo = [receipt: Receipt]

    static constraints = {
    }
}
