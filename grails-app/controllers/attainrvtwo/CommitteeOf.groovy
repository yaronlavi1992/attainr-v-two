package attainrvtwo

enum CommitteeOf {
    EARLY_CHILDHOOD_EDUCATION("חינוך לגיל הרך"),
    SOLDIERS("חיילים"),
    SPORT("ספורט"),
    MAINTENANCE("אחזקה"),
    CULTURE("תרבות"),
    TRADITION("מסורת"),
    ENVIRONMENT_AND_COMMUNITY("סביבה וקהילה"),
    STURDINESS("חוסן"),
    SPIRIT_AND_COMMUNITY("רוח וקהילה"),
    FIFTY_FIVE_PLUS("55+"),
    REGIONAL_PARTNERSHIP("שותפות אזורית"),
    DOGS("כלבים"),
    YOUTH("נוער"),
    REVIEW("ביקורת"),
    SECURITY("בטחון"),
    TZACHI('צח"י'),
    ARCHIVE("ארכיון"),
    MANAGEMENT("הנהלה")
    String committeeLiteral

    CommitteeOf(String value) {
        this.committeeLiteral = value
    }

    String getCommitteeLiteral() {
        return this.committeeLiteral
    }

    @Override
    String toString() {
        return getCommitteeLiteral()
    }
}