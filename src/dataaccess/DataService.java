package dataaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.DictionaryInfo;
import dataaccess.DBconnection;

public class DataService {
	DBconnection cm;
	Connection con;
	Statement stmt;
	ResultSet rs;

	public DataService() {
		try {
			con = DBconnection.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONArray autoTextList(String autoText) {
		try {
			JSONArray lstString=new JSONArray();
			String sql = "SELECT * FROM entries.entries where word like'"+autoText+"%';";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				JSONObject obj=new JSONObject();
				obj.put("word", rs.getString("word"));
				lstString.add(obj);

			}
			return lstString;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public JSONArray SearchWord(String word) {

		try {
			// List<DictionaryInfo> lstResult = new ArrayList<>();
			String sql = "SELECT * FROM entries.entries where word like'"
					+ word + "%';";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs == null) {
				return null;
			}
			JSONArray lstResult = new JSONArray();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("word", rs.getString("word"));
				obj.put("wordtype", rs.getString("wordtype"));
				obj.put("definition", rs.getString("definition"));
				/*
				 * DictionaryInfo(rs.getString("word") .trim(),
				 * rs.getString("wordtype").trim(), rs.getString(
				 * "definition").trim());
				 */
				lstResult.add(obj);

			}
			return lstResult;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
}
