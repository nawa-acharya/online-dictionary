package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import dataaccess.DataService;

/**
 * Servlet implementation class DictionaryServlet
 */

public class DictionaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DictionaryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		DataService ds = new DataService();
		String word = request.getParameter("searchkey");
		// Gson gson = new Gson();
		//if (word != null) {
			JSONArray entriesList = ds.SearchWord(word);
			out.println(entriesList.toJSONString());
			/*
			 * String jsonData = gson.toJson(entriesList);
			 * response.getWriter().write(jsonData);
			 */

		//}
		/* autocomplete */
		/*
		String autoText = request.getParameter("term");
		JSONArray lstAutoText = ds.autoTextList(autoText);
		out.println(lstAutoText.toJSONString());*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
