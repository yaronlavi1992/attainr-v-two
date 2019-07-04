package attainrvtwo

enum DepartmentOf {
    INFANCY("גיל הרך"),
    LEISURE_COMMUNITY_AND_SPORT_CENTER("פנאי קהילה ומרכז ספורט"),
    CHILDREN_AND_YOUTH("ילדים ונוער"),
    MAINTENANCE("אחזקה"),
    CULTURE("תרבות"),
    SECRETARIAT("מזכירות"),
    MANAGEMENT("הנהלה")
    String departmentLiteral

    DepartmentOf(String value) {
        this.departmentLiteral = value
    }

    String getDepartmentLiteral() {
        return this.departmentLiteral
    }

    @Override
    String toString() {
        return getDepartmentLiteral()
    }
}