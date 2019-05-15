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
                password: '1',
                phone: '777',
                email: 'c@blah.com',
                role: RoleOf.DEPARTMENT_MANAGER,
                permission: PermissionOf.MID,
                committee: infancyManagement
        ).save()

        def moshe = new User(
                name: 'משה',
                password: '1',
                phone: '756',
                email: 'm@blah.com',
                role: RoleOf.COMMITTEE_MANAGER,
                permission: PermissionOf.LOW,
                committee: early_childhood_education
        ).save()

//        leisure department manager and one committee user
        def leisure_and_community = new Department(
                name: DepartmentOf.LEISURE_AND_COMMUNITY
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
                password: '1',
                phone: '123',
                email: 'd@blah.com',
                role: RoleOf.DEPARTMENT_MANAGER,
                permission: PermissionOf.MID,
                committee: leisure_and_community_management
        ).save()

        def zuchmir = new User(
                name: 'זוכמיר',
                password: '1',
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
                password: '1',
                phone: '143',
                email: 'd@blah.com',
                role: RoleOf.DEPARTMENT_MANAGER,
                permission: PermissionOf.MID,
                committee: secretariatManagement
        ).save()

        def yosi = new User(
                name: 'יוסי',
                password: '1',
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
                password: '1',
                phone: '555',
                email: 'a@blah.com',
                role: RoleOf.DEVELOPER,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        def yaron = new User(
                name: 'ירון',
                password: '1',
                phone: '666',
                email: 'b@blah.com',
                role: RoleOf.DEVELOPER,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        def shlomit = new User(
                name: 'שלומית',
                password: '1',
                phone: '888',
                email: 'e@blah.com',
                role: RoleOf.COMMUNITY_MANAGER,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        def avi = new User(
                name: 'אבי',
                password: '1',
                phone: '999',
                email: 'f@blah.com',
                role: RoleOf.COMMUNITY_ACCOUNTANT,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        def ran = new User(
                name: 'רן',
                password: '1',
                phone: '444',
                email: 'g@blah.com',
                role: RoleOf.COMMUNITY_CEO,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        def yoav = new User(
                name: 'יואב',
                password: '1',
                phone: '153',
                email: 'r@blah.com',
                role: RoleOf.COMMUNITY_CEO,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        def amit = new User(
                name: 'עמית',
                password: '1',
                phone: '192',
                email: 't@blah.com',
                role: RoleOf.COMMUNITY_CEO,
                permission: PermissionOf.HIGH,
                committee: managementCommittee
        ).save()

        Quote q1 = new Quote(pricePerUnit: 20, qty: 5, totalPrice: 5100)
        Quote q2 = new Quote(pricePerUnit: 30, qty: 5, totalPrice: 150)
        Quote q3 = new Quote(pricePerUnit: 40, qty: 5, totalPrice: 200)
        Approval kadureiKadursal = new Approval(approved: true)
        new Purchase(user: zuchmir, description: 'פנאי וקהילה - חיילים', status: PurchaseStatus.IN_PROGRESS, departmentApproval: kadureiKadursal,
                accountantApproval: kadureiKadursal, quotes: [q1, q2, q3]).save()

        Approval kadurRogby = new Approval(approved: false)
        new Purchase(user: irit, description: 'גיל הרך 1', status: PurchaseStatus.IN_PROGRESS, departmentApproval: kadurRogby).save()

        new Purchase(user: irit, description: 'גיל הרך 2', status: PurchaseStatus.COMPLETE).save()

        Approval magenZea = new Approval(approved: true)
        new Purchase(user: moshe, description: 'גיל הרך - חינוך לגיל הרך 1', status: PurchaseStatus.PURCHASED, departmentApproval: magenZea).save()

        new Purchase(user: moshe, description: 'גיל הרך - חינוך לגיל הרך 2', status: PurchaseStatus.DENIED).save()
        new Purchase(user: nir, description: 'הנהלה 1', status: PurchaseStatus.PAYMENT_REQUIRED).save()
        new Purchase(user: yaron, description: 'הנהלה 2', status: PurchaseStatus.APPROVED).save()

    }
    def destroy = {
    }
}
