package attainrvtwo

class UrlMappings {
    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

//        "/"(view:"/index")

//        "/*" {
//            id = { params.id }
//            isEligible = { session.user != null } // must be logged in
//        }
//        "/*/*" {
//            id = { params.id }
//            isEligible = { session.user != null } // must be logged in
//        }
//        "/*/*/*" {
//            id = { params.id }
//            isEligible = { session.user != null } // must be logged in
//        }
        "/"(controller: 'user', action: 'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
