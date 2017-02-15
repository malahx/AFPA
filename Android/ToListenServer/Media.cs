
namespace ToListenServer.Model {

	public class Media {

		public long id { get; set; }
		public string url { get; set; }
		public string sender { get; set; }
		public string genre { get; set; }
		public string author { get; set; }
		public string title { get; set; }
		public bool isViewed { get; set; }


		public Media(long id, string url, string sender, string genre, string author, string title, bool isViewed) {
			this.id = id;
			this.url = url;
			this.sender = sender;
			this.genre = genre;
			this.author = author;
			this.title = title;
			this.isViewed = isViewed;
		}

		public Media(string url, string sender, string genre, string author, string title) {
			id = 0;
			this.url = url;
			this.sender = sender;
			this.genre = genre;
			this.author = author;
			this.title = title;
			isViewed = false;
		}

		public Media() { }

	}
}
