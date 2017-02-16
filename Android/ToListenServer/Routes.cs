/* 
ToListenServer
Copyright 2017 Malah
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>. 
*/

using System;
using Nancy;
using Nancy.Json;
using Nancy.ModelBinding;
using ToListenServer.Model;

namespace ToListenServer.Routes {

	/// <summary>
	/// Routes du serveur web
	/// Compatibilité avec le serveur node.js https://github.com/Valannos/ToListen-Express.js
	/// </summary>
	public class MediaModule : NancyModule {
		public MediaModule() {

			/// <summary>
			/// Route pour récupérer tous les médias
			/// curl http://localhost:10563/links/api/tolisten
			/// </summary>
			Get["/links/api/tolisten"] = parameters => new JavaScriptSerializer().Serialize(MainClass.getMedias);

			/// <summary>
			/// Route pour ajouter un média
			/// curl -X POST -H "Content-Type: application/json" -H "Accept: application/json" -d '{"id":10,"url":"truc","sender":"me","genre":"machin","author":"haha","title":"lol","isViewed":0}' http://localhost:10563/links/api/tolisten/add
			/// </summary>
			Post["/links/api/tolisten/add"] = parameters => {
				Media m = this.Bind<Media>();
				m = MainClass.AddMedia(m);
				return m != null ? new JavaScriptSerializer().Serialize(m) : new Response { StatusCode = HttpStatusCode.BadRequest, ReasonPhrase = "Can't added media" };
			};

			/// <summary>
			/// Route pour supprimer un média
			/// curl -X DELETE http://localhost:10563/links/api/tolisten/delete/1
			/// </summary>
			Delete["/links/api/tolisten/delete/{id:long}"] = parameters => {
				long id = parameters.id;
				Media m = MainClass.DelMedia(MainClass.GetMedia(id));
				return m != null ? new JavaScriptSerializer().Serialize(m) : new Response { StatusCode = HttpStatusCode.BadRequest, ReasonPhrase = "Can't delete media" };
			};

			/// <summary>
			/// Route pour mettre à jour un média
			/// curl -X PUT -H "Content-Type: application/json" -H "Accept: application/json" -d '{"id":1,"url":"truc","sender":"me","genre":"machin","author":"haha","title":"lol","isViewed":0}' http://localhost:10563/links/api/tolisten/edit
			/// </summary>
			Put["/links/api/tolisten/edit"] = parameters => {
				Media m = this.Bind<Media>();
				m = MainClass.UpMedia(m);
				return m != null ? new JavaScriptSerializer().Serialize(m) : new Response { StatusCode = HttpStatusCode.BadRequest, ReasonPhrase = "Can't edit media" };
			};

			/// <summary>
			/// Route pour changer la propriété vue d'un média
			/// curl -X PUT -H "Content-Type: application/json" -H "Accept: application/json" -d '{"id":1}' http://localhost:10563/links/api/tolisten/updateViewState
			/// </summary>
			Put["/links/api/tolisten/updateViewState/"] = parameters => {
				Media m = this.Bind<Media>();
				Console.WriteLine(m);
				m = MainClass.GetMedia(m.id);
				Console.WriteLine(m);
				m.isViewed = m.isViewed == 0 ? 1 : 0;
				m = MainClass.UpMedia(m);
				return m != null ? new JavaScriptSerializer().Serialize(m) : new Response { StatusCode = HttpStatusCode.BadRequest, ReasonPhrase = "Can't update view state" };
			};
		}
	}
}
