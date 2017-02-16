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

namespace ToListenServer.Model {

	/// <summary>
	/// Objet media
	/// </summary>a
	public class Media {

		public long id { get; set; }
		public string url { get; set; }
		public string sender { get; set; }
		public string genre { get; set; }
		public string author { get; set; }
		public string title { get; set; }
		public bool isViewed { get; set; }

		/// <summary>
		/// Initializes a new instance of the <see cref="T:ToListenServer.Model.Media"/> class.
		/// </summary>
		/// <param name="id">Identifier.</param>
		/// <param name="url">URL.</param>
		/// <param name="sender">Sender.</param>
		/// <param name="genre">Genre.</param>
		/// <param name="author">Author.</param>
		/// <param name="title">Title.</param>
		/// <param name="isViewed">If set to <c>true</c> is viewed.</param>
		public Media(long id, string url, string sender, string genre, string author, string title, bool isViewed) {
			this.id = id;
			this.url = url;
			this.sender = sender;
			this.genre = genre;
			this.author = author;
			this.title = title;
			this.isViewed = isViewed;
		}

		/// <summary>
		/// Initializes a new instance of the <see cref="T:ToListenServer.Model.Media"/> class.
		/// </summary>
		/// <param name="url">URL.</param>
		/// <param name="sender">Sender.</param>
		/// <param name="genre">Genre.</param>
		/// <param name="author">Author.</param>
		/// <param name="title">Title.</param>
		public Media(string url, string sender, string genre, string author, string title) {
			id = 0;
			this.url = url;
			this.sender = sender;
			this.genre = genre;
			this.author = author;
			this.title = title;
			isViewed = false;
		}

		/// <summary>
		/// Initializes a new instance of the <see cref="T:ToListenServer.Model.Media"/> class.
		/// </summary>
		/// <param name="id">Identifier.</param>
		public Media(long id) {
			this.id = id;
		}

		/// <summary>
		/// Initializes a new instance of the <see cref="T:ToListenServer.Model.Media"/> class.
		/// </summary>
		public Media() { }

		/// <summary>
		/// Returns a <see cref="T:System.String"/> that represents the current <see cref="T:ToListenServer.Model.Media"/>.
		/// </summary>
		/// <returns>A <see cref="T:System.String"/> that represents the current <see cref="T:ToListenServer.Model.Media"/>.</returns>
		public override string ToString() {
			return string.Format("{0} {1} {2} {3} {4} {5} {6}", id, url, sender, genre, author, title, isViewed);
		}

	}
}
