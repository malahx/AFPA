using System;
using System.Collections.Generic;
using Nancy.Hosting.Self;
using ToListenServer.Model;

namespace ToListenServer {
	class MainClass {

		public static List<Media> getMedias {
			get {
				return DAO.Media.FindAll();
			}
		}

		public static Media AddMedia(Media m) {
			return DAO.Media.Insert(m);
		}

		public static void Main(string[] args) {
			NancyHost host = new NancyHost(new Uri("http://localhost:10563"));
			host.Start();
			Console.WriteLine("Running on http://localhost:10563");
			Console.ReadLine();
		}
	}
}
