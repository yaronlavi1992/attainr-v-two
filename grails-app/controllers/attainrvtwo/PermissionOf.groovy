package attainrvtwo

enum PermissionOf {
    LOW("נמוך"),
    MID("בינוני"),
    HIGH("גבוה")
    String permissionLiteral

    PermissionOf(String value) {
        this.permissionLiteral = value
    }

    String getPermissionLiteral() {
        return this.permissionLiteral
    }

    @Override
    String toString() {
        return getPermissionLiteral()
    }
}
