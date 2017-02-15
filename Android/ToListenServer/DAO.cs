using System;
using System.Collections.Generic;
using System.Data.SQLite;

namespace ToListenServer.DAO {
	public static class dao {

		const string DATABASE_FILE = "../../tolisten.db";

		static SQLiteConnection conn;

		public static SQLiteConnection Open() {
			if (conn == null || conn.State != System.Data.ConnectionState.Open) {
				conn = new SQLiteConnection("Data Source=" + DATABASE_FILE + ";Version=3;");
				conn.Open();
			}
			return conn;
		}

		public static void Close() {
			if (conn == null) {
				return;
			}
			conn.Close();
			conn = null;
		}
	}

	public static class Media {

		public static List<Model.Media> FindAll() {

			List<Model.Media> medias = new List<Model.Media>();
			string sql = "SELECT * FROM link";

			try {
				SQLiteConnection c = dao.Open();
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
					m.isViewed = reader.GetBoolean(6);
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

		public static Model.Media Insert(Model.Media m) {

			string sql = "INSERT INTO link (url, sender, genre, author, title, isViewed) VALUES (@url, @sender, @genre, @author, @title, @isViewed)";

			try {
				SQLiteConnection c = dao.Open();
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

				Console.WriteLine("Insert");
			} catch (Exception ex) {

				Console.WriteLine(ex.Message);
				throw new Exception(ex.Message);
			}
			return m;
		}
	}
}
