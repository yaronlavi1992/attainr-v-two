package attainrvtwo

enum DepartmentOf {
    INFANCY("גיל הרך"),
    LEISURE_AND_COMMUNITY("פנאי וקהילה"),
    CHILDREN_AND_YOUTH("ילדים ונוער"),
    MAINTENANCE("אחזקה"),
    SPORTS_CENTER("מרכז ספורט"),
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