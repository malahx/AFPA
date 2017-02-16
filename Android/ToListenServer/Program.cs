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
using System.Collections.Generic;
using Nancy.Hosting.Self;
using ToListenServer.Model;

namespace ToListenServer {
	class MainClass {

		/// <summary>
		/// Récupérer tous les médias de la base de donné
		/// </summary>
		/// <value>The get medias.</value>e
		public static List<Media> getMedias {
			get {
				return DAO.Media.FindAll();
			}
		}

		/// <summary>
		/// Ajouter un média à la base de donnée
		/// </summary>
		/// <returns>The media.</returns>
		/// <param name="m">M.</param>
		public static Media AddMedia(Media m) {
			return DAO.Media.Insert(m);
		}

		/// <summary>
		/// Mettre à jour un média à la base de donné
		/// </summary>
		/// <returns>The media.</returns>
		/// <param name="m">M.</param>e
		public static Media UpMedia(Media m) {
			return DAO.Media.Update(m);
		}

		/// <summary>
		/// Récupérer un média de la base de donnée en fonction de son id
		/// </summary>
		/// <returns>The media.</returns>
		/// <param name="m">M.</param>e
		public static Media DelMedia(Media m) {
			return DAO.Media.Delete(m);
		}

		/// <summary>
		/// Récupérer un média de la base de donnée en fonction de son id
		/// </summary>
		/// <returns>The media.</returns>
		/// <param name="id">Identifier.</param>
		public static Media GetMedia(long id) {
			return DAO.Media.FindBy(id);
		}

		/// <summary>
		/// The entry point of the program, where the program control starts and ends.
		/// </summary>
		/// <param name="args">The command-line arguments.</param>
		public static void Main(string[] args) {

			// Lancement du serveur web
			NancyHost host = new NancyHost(new Uri("http://localhost:10563"));
			host.Start();

			Console.WriteLine("Running on http://localhost:10563");

			// Attente du programme
			Console.ReadLine();
		}
	}
}
