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
using System.Data.SQLite;

namespace ToListenServer.DAO {

	/// <summary>
	/// Gestion de la base de donnée SQL
	/// </summary>
	public static class Dao {

		/// <summary>
		/// Fichier de la base de donné
		/// </summary>
		const string DATABASE_FILE = "../../tolisten.db";

		/// <summary>
		/// Connection ouverte
		/// </summary>n
		static SQLiteConnection conn;

		/// <summary>
		/// Overture de la connection
		/// </summary>
		public static SQLiteConnection Open() {
			if (conn == null || conn.State != System.Data.ConnectionState.Open) {
				conn = new SQLiteConnection("Data Source=" + DATABASE_FILE + ";Version=3;");
				conn.Open();
			}
			return conn;
		}

		/// <summary>
		/// Fermeture de la connection
		/// </summary>
		public static void Close() {
			if (conn == null) {
				return;
			}
			conn.Close();
			conn = null;
		}
	}

	public static class Media {

		/// <summary>
		/// Récupérer tous les objets
		/// </summary>
		/// <returns>The all.</returns>
		public static List<Model.Media> FindAll() {

			List<Model.Media> medias = new List<Model.Media>();
			string sql = "SELECT * FROM link";

			try {
				SQLiteConnection c = Dao.Open();
				SQLiteCommand query = new SQLiteCommand(sql, c);
				SQLiteDataReader reader = query.ExecuteReader();

				while (reader.Read()) {
					Model.Media m = new Model.Media();
					m.id = reader.GetInt32(0);
					m.url = reader.GetString(1);
					m.sender = reader.GetString(2);
					m.genre = reader.GetString(3);
					m.author = reader.GetString(4);
					m.title = reader.GetString(5);
					m.isViewed = reader.GetInt16(6);
					medias.Add(m);
				}

				reader.Close();
				query.Dispose();
				c.Close();

				Console.WriteLine("FindAll");
			} catch (Exception ex) {

				Console.WriteLine(ex.Message);
				throw new Exception(ex.Message);
			}
			return medias;
		}

		/// <summary>
		/// Récupérer un objet en fonction de son id
		/// </summary>
		/// <returns>The by.</returns>
		/// <param name="id">Identifier.</param>
		public static Model.Media FindBy(long id) {

			string sql = "SELECT * FROM link WHERE id = @id";
			Model.Media m = null;

			try {
				SQLiteConnection c = Dao.Open();
				SQLiteCommand query = new SQLiteCommand(sql, c);
				query.Parameters.AddWithValue("@id", id);
				SQLiteDataReader reader = query.ExecuteReader();

				while (reader.Read()) {
					m = new Model.Media();
					m.id = reader.GetInt64(0);
					m.url = reader.GetString(1);
					m.sender = reader.GetString(2);
					m.genre = reader.GetString(3);
					m.author = reader.GetString(4);
					m.title = reader.GetString(5);
					m.isViewed = reader.GetInt16(6);
				}

				reader.Close();
				query.Dispose();
				c.Close();

				Console.WriteLine("FindBy");
			} catch (Exception ex) {

				Console.WriteLine(ex.Message);
				throw new Exception(ex.Message);
			}
			return m;
		}

		/// <summary>
		/// Insérer un objet
		/// </summary>
		/// <param name="m">M.</param>
		public static Model.Media Insert(Model.Media m) {

			string sql = "INSERT INTO link (url, sender, genre, author, title, isViewed) VALUES (@url, @sender, @genre, @author, @title, @isViewed)";
			Model.Media media = null;

			try {
				SQLiteConnection c = Dao.Open();
				SQLiteCommand query = new SQLiteCommand(sql, c);
				query.Parameters.AddWithValue("@url", m.url);
				query.Parameters.AddWithValue("@sender", m.sender);
				query.Parameters.AddWithValue("@genre", m.genre);
				query.Parameters.AddWithValue("@author", m.author);
				query.Parameters.AddWithValue("@title", m.title);
				query.Parameters.AddWithValue("@isViewed", m.isViewed);
				query.Prepare();
				query.ExecuteNonQuery();

				long id = c.LastInsertRowId;
				if (id > 0) {
					m.id = id;
				} else {
					m = null;
				}

				query.Dispose();
				c.Close();

				media = m;
				Console.WriteLine("Inserted");
			} catch (Exception ex) {

				Console.WriteLine(ex.Message);
				throw new Exception(ex.Message);
			}
			return media;
		}

		/// <summary>
		/// Mettre à jour un objet en fonction de son id
		/// </summary>
		/// <param name="m">M.</param>
		public static Model.Media Update(Model.Media m) {

			string sql = "UPDATE link SET url = @url, sender = @sender, genre = @genre, author = @author, title = @title, isViewed = @isViewed WHERE id = @id";
			Model.Media media = null;

			try {
				SQLiteConnection c = Dao.Open();
				SQLiteCommand query = new SQLiteCommand(sql, c);
				query.Parameters.AddWithValue("@id", m.id);
				query.Parameters.AddWithValue("@url", m.url);
				query.Parameters.AddWithValue("@sender", m.sender);
				query.Parameters.AddWithValue("@genre", m.genre);
				query.Parameters.AddWithValue("@author", m.author);
				query.Parameters.AddWithValue("@title", m.title);
				query.Parameters.AddWithValue("@isViewed", m.isViewed);
				query.Prepare();
				query.ExecuteNonQuery();

				query.Dispose();
				c.Close();

				media = m;
				Console.WriteLine("Updated");
			} catch (Exception ex) {

				Console.WriteLine(ex.Message);
				throw new Exception(ex.Message);
			}
			return media;
		}

		/// <summary>
		/// Supprimer un objet
		/// </summary>
		/// <param name="m">M.</param>
		public static Model.Media Delete(Model.Media m) {

			string sql = "DELETE FROM link WHERE id = @id";
			Model.Media media = null;

			try {
				SQLiteConnection c = Dao.Open();
				SQLiteCommand query = new SQLiteCommand(sql, c);
				query.Parameters.AddWithValue("@id", m.id);
				query.Prepare();
				query.ExecuteNonQuery();

				query.Dispose();
				c.Close();

				media = m;
				Console.WriteLine("Deleted");
			} catch (Exception ex) {

				Console.WriteLine(ex.Message);
				throw new Exception(ex.Message);
			}
			return media;
		}
	}
}
