package attainrvtwo

enum RoleOf {
    DEVELOPER("מפתח"),
    MANAGEMENT("הנהלה"),
    COMMUNITY_CEO("מנהל כללי"),
    COMMUNITY_MANAGER("מנהל הקהילה"),
    COMMUNITY_ACCOUNTANT("חשב הקהילה"),
    DEPARTMENT_MANAGER("מנהל מחלקה"),
    COMMITTEE_MANAGER("מנהל ועדה"),
    COMMUNITY_SECRETARY("מזכירת הקהילה"),
    LEISURE_AND_SECRETARY_CEO("מנהל כללי לפנאי ומזכירות"),
    INFANCY_AND_YOUTH_CEO("מנהל כללי לגיל הרך ונוער"),
    MAINTENANCE_AND_SPORT_CEO("מנהל כללי לאחזקה וספורט")
    String roleLiteral

    RoleOf(String value) {
        this.roleLiteral = value
    }

    String getRoleLiteral() {
        return this.roleLiteral
    }

    @Override
    String toString() {
        return getRoleLiteral()
    }
}