class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/"(controller: 'login', action: 'index')
       // "/"(view:"/index")
     //   "500"(controller: 'util',action: 'index')
      //  "404"(controller: 'util',action: 'error')
	}
}
