package attainrvtwo

class BootStrap {

    def init = { servletContext ->
        ////        infancy department manager and one committee user
        def infancy = new Department(
                name: DepartmentOf.INFANCY
        ).save()

        def early_childhood_education = new Committee(
                name: CommitteeOf.EARLY_CHILDHOOD_EDUCATION,
                department: infancy
        ).save()

        def infancyManagement = new Committee(
                name: CommitteeOf.MANAGEMENT,
                department: infancy
        ).save()

        def irit = new User(
                name: 'אירית',
                password: PasswordHasher.encrypt('1'),
                phone: '777',
                email: 'c@blah.com',
                role: RoleOf.DEPARTMENT_MANAGER,
                permission: PermissionOf.MID,
                committee: infancyManagement
        ).save()

        def moshe = new User(
                name: 'משה',
                password: PasswordHasher.encrypt('1'),
                phone: '756',
                email: 'm@blah.com',
                role: RoleOf.COMMITTEE_MANAGER,
                permission: PermissionOf.LOW,
                committee: early_childhood_education
        ).save()

//        leisure department manager and one committee user
        def leisure_and_community = new Department(
                name: DepartmentOf.LEISURE_COMMUNITY_AND_SPORT_CENTER
        ).save()

        def soldiers = new Committee(
                name: CommitteeOf.SOLDIERS,
                department: leisure_and_community
        ).save()

        def leisure_and_community_management = new Committee(
                name: CommitteeOf.MANAGEMENT,
                department: leisure_and_community
        ).save()

        def karin = new User(
                name: 'קרין',
                password: PasswordHasher.encrypt('1'),
                phone: '123',
                email: 'd@blah.com',
                role: RoleOf.DEPARTMENT_MANAGER,
                permission: PermissionOf.MID,
                committee: leisure_and_community_management
        ).save()

        def zuchmir = new User(
                name: 'זוכמיר',
                password: PasswordHasher.encrypt('1'),
                phone: '675',
                email: 'n@blah.com',
                role: RoleOf.COMMITTEE_MANAGER,
                permission: PermissionOf.LOW,
                committee: soldiers
        ).save()

//        secretariat department manager and one committee user
        def secretariat = new Department(
                name: DepartmentOf.SECRETARIAT
        ).save()

        def archive = new Committee(
                name: CommitteeOf.ARCHIVE,
                department: secretariat
        ).save()

        def secretariatManagement = new Committee(
                name: CommitteeOf.MANAGEMENT,
                department: secretariat
        ).save()

        def nirit = new User(
                name: 'נירית',
                password: PasswordHasher.encrypt('1'),
                phone: '143',
                email: 'd@blah.com',
                role: RoleOf.COMMUNITY_SECRETARY,
                permission: PermissionOf.MID,
                committee: secretariatManagement
        ).save()

        def yosi = new User(
                name: 'יוסי',
                password: PasswordHasher.encrypt('1'),
                phone: '675',
                email: 'k@blah.com',
                role: RoleOf.COMMITTEE_MANAGER,
                permission: PermissionOf.LOW,
                committee: archive
        ).save()


//        managers
        def managementDepartment = new Department(
                name: DepartmentOf.MANAGEMENT
        ).save()

        def managementCommittee = new Committee(
                name: CommitteeOf.MANAGEMENT,
                department: managementDepartment
        ).save()

        def nir = new User(
                name: 'ניר',
                password: PasswordHasher.encrypt('1'),
                phone: '555',
                email: 'a@blah.com',
                role: RoleOf.DEVELOPER,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        def yaron = new User(
                name: 'ירון',
                password: PasswordHasher.encrypt('1'),
                phone: '666',
                email: 'b@blah.com',
                role: RoleOf.DEVELOPER,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        def shlomit = new User(
                name: 'שלומית',
                password: PasswordHasher.encrypt('1'),
                phone: '888',
                email: 'e@blah.com',
                role: RoleOf.COMMUNITY_MANAGER,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        def avi = new User(
                name: 'אבי',
                password: PasswordHasher.encrypt('1'),
                phone: '999',
                email: 'f@blah.com',
                role: RoleOf.COMMUNITY_ACCOUNTANT,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        def ran = new User(
                name: 'רן',
                password: PasswordHasher.encrypt('1'),
                phone: '444',
                email: 'g@blah.com',
                role: RoleOf.MAINTENANCE_AND_SPORT_CEO,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        def yoav = new User(
                name: 'יואב',
                password: PasswordHasher.encrypt('1'),
                phone: '153',
                email: 'r@blah.com',
                role: RoleOf.LEISURE_AND_SECRETARY_CEO,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        def amit = new User(
                name: 'עמית',
                password: PasswordHasher.encrypt('1'),
                phone: '192',
                email: 't@blah.com',
                role: RoleOf.INFANCY_AND_YOUTH_CEO,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        Approval kadurRogby = new Approval(approved: false)
        new Purchase(totalPurchasePrice: 0, name: "מוצצים",user: irit, description: 'גיל הרך 1', status: PurchaseStatus.IN_PROGRESS, departmentApproval: kadurRogby).save()

        new Purchase(totalPurchasePrice: 0, name: "חיתולים", user: irit, description: 'גיל הרך 2', status: PurchaseStatus.COMPLETE).save()

        Approval magenZea = new Approval(approved: true)
        new Purchase(totalPurchasePrice: 0, name: "עגלות", user: moshe, description: 'גיל הרך - חינוך לגיל הרך 1', status: PurchaseStatus.IN_PROGRESS, departmentApproval: magenZea).save()

        new Purchase(totalPurchasePrice: 0, name: "מוביילים", user: moshe, description: 'גיל הרך - חינוך לגיל הרך 2', status: PurchaseStatus.DENIED).save()
        new Purchase(totalPurchasePrice: 0, name: "כיסאות מנהל", user: nir, description: 'הנהלה 1', status: PurchaseStatus.PAYMENT_REQUIRED).save()
        new Purchase(totalPurchasePrice: 0, name: "לוחות מחיקים", user: yaron, description: 'הנהלה 2', status: PurchaseStatus.APPROVED).save()


        //example of purchased status(3 approvals and PURCHASED status)
        MyFile f1 = new MyFile(fileName: "אבי", myFile: [0, 0, 0, 0, 0] as byte[]).save()

        PurchaseItem purchasedItemExample = new PurchaseItem(description: "אאא", packQuantity: 3, packPrice: 47).save()
        Approval leisure_and_communityApp = new Approval(approved: true)
        Approval accountantApp = new Approval(approved: true)
        Approval communityManagerApp = new Approval(approved: true)
        Approval paymentDateApprovalExample = new Approval(approved: true, date: '01 01 2019')
        Purchase purchasedExample = new Purchase(totalPurchasePrice: 0, name: "דוגמא נרכש", user: karin, description: 'הנהלה 2', status: PurchaseStatus.PURCHASED,
                paymentDateApproval: paymentDateApprovalExample, departmentApproval: leisure_and_communityApp, accountantApproval: accountantApp, communityApproval: communityManagerApp).save()
        Quote q1 = new Quote(number: 1, name: "אבי", price: 45, file: f1, purchase: purchasedExample).save()

    }
    def destroy = {
    }
}
