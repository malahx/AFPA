using System;
using Nancy;
using Nancy.Json;
using Nancy.ModelBinding;
using ToListenServer.Model;

namespace ToListenServer.Routes {
	public class MediaModule : NancyModule {
		public MediaModule() {
			Get["/links/api/tolisten"] = parameters => new JavaScriptSerializer().Serialize(MainClass.getMedias);
			Post["/links/api/tolisten/add"] = parameters => {
				Media m = this.Bind<Media>();
				m = MainClass.AddMedia(m);
				return m != null ? new JavaScriptSerializer().Serialize(m) : new Response { StatusCode = HttpStatusCode.BadRequest };
			};
			Delete["/links/api/tolisten/delete/"] = parameters => new JavaScriptSerializer().Serialize(MainClass.getMedias);
			Put["/links/api/tolisten/updateViewState/"] = parameters => new JavaScriptSerializer().Serialize(MainClass.getMedias);
		}
	}
}
//curl -X POST -H "Content-Type: application/json" -H "Accept: application/json" -d '{"id":10,"url":"truc","sender":"me","genre":"machin","author":"haha","title":"lol","isViewed":0}' http://localhost:10563/links/api/tolisten/add
