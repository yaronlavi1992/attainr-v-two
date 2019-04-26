package attainrvtwo

class BootStrap {

    def init = { servletContext ->
        def sport = new attainrvtwo.Committee(name: 'ספורט').save()
        def education = new attainrvtwo.Committee(name: 'חינוך').save()
        def revaha = new attainrvtwo.Committee(name: 'רווחה').save()
        def community_management = new attainrvtwo.Committee(name: 'ניהול קהילה').save()
        def system_management = new attainrvtwo.Committee(name: 'ניהול מערכת').save()

        def nir = new User(committee: system_management, name: 'ניר', password: '1', phone: '555',
                email: 'a@blah.com', role: 'מפתח המערכת', permission: 'גבוה').save()
        def yaron = new User(committee: system_management, name: 'ירון', password: '1', phone: '666',
                email: 'b@blah.com', role: 'מפתח המערכת', permission: 'גבוה').save()

        def shai = new User(committee: sport, name: 'שי', password: '1', phone: '777',
                email: 'c@blah.com', role: 'מתנדב', permission: 'נמוך').save()
        def ben = new User(committee: sport, name: 'בן', password: '1', phone: '123',
                email: 'd@blah.com', role: 'מתנדב', permission: 'בינוני').save()
        def shlomit = new User(committee: community_management, name: 'שלומית', password: '1', phone: '888',
                email: 'e@blah.com', role: 'מנהלת הקהילה', permission: 'גבוה').save()
        def avi = new User(committee: community_management, name: 'אבי', password: '1', phone: '999',
                email: 'f@blah.com', role: 'חשב הקהילה', permission: 'גבוה').save()
        def ran = new User(committee: community_management, name: 'רן', password: '1', phone: '444',
                email: 'g@blah.com', role: 'מנכל', permission: 'גבוה').save()

        Approval kadureiKadursal = new Approval(approved: true)
        new attainrvtwo.Purchase(user: shai, description: 'כדורי כדורסל', status: 'בתהליך', committeeApproval: kadureiKadursal,
        accountantApproval: kadureiKadursal).save()

        Approval kadurRogby = new Approval(approved: false)
        new attainrvtwo.Purchase(user: shai, description: 'כדור רוגבי', status: 'בתהליך', committeeApproval: kadurRogby).save()

        new attainrvtwo.Purchase(user: shai, description: 'נעלי רוגבי', status: 'הושלם').save()

        Approval magenZea = new Approval(approved: true)
        new attainrvtwo.Purchase(user: ben, description: 'מגן זיעה', status: 'נרכש', committeeApproval: magenZea).save()

        new attainrvtwo.Purchase(user: ben, description: 'פינג פונג', status: 'לא_אושר').save()
        new attainrvtwo.Purchase(user: nir, description: 'חבילת אינטרנט', status: 'נדרש_תשלום').save()
        new attainrvtwo.Purchase(user: yaron, description: 'שניצל', status: 'אושר').save()
    }
    def destroy = {
    }
}
