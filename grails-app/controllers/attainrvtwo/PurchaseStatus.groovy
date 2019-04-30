package attainrvtwo

enum PurchaseStatus {
    IN_PROGRESS("בתהליך"),
    APPROVED("אושר"),
    DENIED("לא אושר"),
    PURCHASED("נרכש"),
    PAYMENT_REQUIRED("נדרש תשלום"),
    COMPLETE("הושלם")
    String statusLiteral

    PurchaseStatus(String value) {
        this.statusLiteral = value
    }

    String getStatusLiteral() {
        return this.statusLiteral
    }

    @Override
    String toString() {
//        return name()
        return getStatusLiteral()
    }
}