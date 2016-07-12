class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        /*"/"(view:"/index")*/
        "500"(view:'/error')
        "/"(controller: "Login", action: "index")
        //Use read() for /topic/show
        "/topic/show"(controller: 'Topic',action: 'read')
        // load() for /resource/delete action.
        "/resource/delete"(controller: 'Resource',action: 'load')
	}
}
