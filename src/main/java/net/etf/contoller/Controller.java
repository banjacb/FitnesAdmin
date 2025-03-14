package net.etf.contoller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.etf.beans.AttributeBean;
import net.etf.beans.CategoryBean;
import net.etf.beans.UserBean;
import net.etf.dto.Attribute;
import net.etf.dto.Category;
import net.etf.dto.User;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String address = "/WEB-INF/pages/login.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.setAttribute("notification", "");

		if (action == null || action.equals("")) {

			address = "/WEB-INF/pages/login.jsp";
			
		}
		else if ("logout".equals(action)) {
			session.invalidate();
			address = "/WEB-INF/pages/login.jsp";
		}

		else if ("login".equals(action)) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserBean userBean = new UserBean();
			if (userBean.login(username, password) && userBean.isAdmin(username)) {
				session.setAttribute("userBean", userBean);

				address = "/WEB-INF/pages/users.jsp";

			} else {
				session.setAttribute("notification", "Pogresni parametri za pristup");
			}
		} 
		else if ("users".equals(action)) {
			address = "/WEB-INF/pages/users.jsp";
		}

		else if ("categories".equals(action)) {

			address = "/WEB-INF/pages/categories.jsp";

		} 
		else if ("statistic".equals(action)) {

			address = "/WEB-INF/pages/statistic.jsp";

		} 
		else if ("deleteUser".equals(action)) {

			UserBean userBean = (UserBean) session.getAttribute("userBean");
			String id = request.getParameter("deleteUser");
			address = "/WEB-INF/pages/users.jsp";
			if (id != null) {
				if (userBean.deleteUser(Integer.parseInt(id))) {
					session.setAttribute("notification", "Korisnik uspjesno izbrisan.");
				} else
					session.setAttribute("notification", "Korisnik nije izbrisan. Greska.");
			}

		}
		else if("updateUser".equals(action))
		{
			
			address="/WEB-INF/pages/updateUser.jsp";
			
		
		}
		else if("updateUserSave".equals(action)) 
		{
			
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			String stringId= request.getParameter("id");
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fisrtName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String city = request.getParameter("city");
			String email = request.getParameter("email");
			String roleParameter = request.getParameter("role");
			boolean status= Boolean.parseBoolean(request.getParameter("status"));
		  
			if (roleParameter != null && !roleParameter.isEmpty() && stringId != null) {
			    Integer role = Integer.parseInt(roleParameter);
			    Integer id =Integer.parseInt(stringId);
				User user = new User(username, password, fisrtName, lastName, city, email, role, status);
				if(userBean.editUser(id,user))
				{
					address = "/WEB-INF/pages/users.jsp";
				}
				else
				{
					session.setAttribute("notification", "Izmjene nisu sacuvane. Greska.");
				}
			}else
			{
				
			}
		}

		else if ("insertUser".equals(action)) {

			address = "/WEB-INF/pages/insertUser.jsp";

		} else if ("insert".equals(action)) {
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fisrtName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String city = request.getParameter("city");
			String email = request.getParameter("email");
			
			boolean status = Boolean.parseBoolean(request.getParameter("status"));
			
			String roleParameter = request.getParameter("role");
			if (roleParameter != null && !roleParameter.isEmpty()) {
			    Integer role = Integer.parseInt(roleParameter);
				var user = new User(username, password, fisrtName, lastName, city, email, role, status);

				if (userBean.insert(user)) {
					session.setAttribute("notification", "Korisnik je uspjesno dodan.");
					address = "/WEB-INF/pages/users.jsp";
			  
			} else {
			    //
			}

			
		
			} else {
				address = "/WEB-INF/pages/insertUser.jsp";
				session.setAttribute("notification", "Korisnik nije dodan. Greska.");
			}

		} else if ("blockUser".equals(action)) {
			try {
				UserBean userBean = (UserBean) session.getAttribute("userBean");
				Integer id = Integer.parseInt(request.getParameter("id"));
				address = "/WEB-INF/pages/users.jsp";

				if (userBean.block(id))
					session.setAttribute("notification", "Korisnik uspjesno blokiran.");
				else
					session.setAttribute("notification", "Korisnik nije blokiran. Greska.");

			} catch (Exception e) {
			
			}
		} else if ("unblockUser".equals(action)) {
			try {
				UserBean userBean = (UserBean) session.getAttribute("userBean");
				var id = Integer.parseInt(request.getParameter("id"));
				address = "/WEB-INF/pages/users.jsp";

				if (userBean.unblock(id))
					session.setAttribute("notification", "Korisnik je sada aktivan.");
				else
					session.setAttribute("notification", "Korisnik nije aktivan. Greska.");

			} catch (Exception e) {
			
			}
		} else if ("addCategory".equals(action)) {
			var categoryBean = (CategoryBean) session.getAttribute("categoryBean");
			var name = request.getParameter("categoryName");
			var tmp = categoryBean.insert(new Category(name));
			address = "/WEB-INF/pages/categories.jsp";
			if (tmp != null) {
				session.setAttribute("notification", "Kategorija uspjesno dodana.");
			} else {
				session.setAttribute("notification", "Kategorija nije dodana. Greska.");
			}
		} else if ("deleteCategory".equals(action)) {
			try {
				var categoryBean = (CategoryBean) session.getAttribute("categoryBean");
				int id = Integer.parseInt(request.getParameter("categoryId"));
				address = "/WEB-INF/pages/categories.jsp";
				if (categoryBean.delete(id)) {
					session.setAttribute("notification", "Kategorija uspjesno izbrisana.");
				} else
					session.setAttribute("notification", "Kategorija nije izbrisana.Greska.");

			} catch (Exception e) {
		
			}
		} else if ("deleteAttribute".equals(action)) {
			try {
				address = "/WEB-INF/pages/categories.jsp";
				var attributeBean = (AttributeBean) session.getAttribute("attributeBean");
				String idParametar=request.getParameter("attributeIdDelete");
				if(idParametar!=null && !idParametar.isEmpty())
				{
				int id = Integer.parseInt(idParametar);
				address = "/WEB-INF/pages/categories.jsp";
				if (attributeBean.delete(id)) {
					session.setAttribute("notification", "Atribut uspjesno izbrisan.");
				} else
					session.setAttribute("notification", "Atribut nije izbrisan.Greska.");
				}else
				{}

			} catch (Exception e) {
				
			}
		} else if ("addAttribute".equals(action)) {
			try {
				var attributeBean = (AttributeBean) session.getAttribute("attributeBean");
				String idParametar=request.getParameter("categoryId");
		if(idParametar!=null && !idParametar.isEmpty())
		{
				int id= Integer.parseInt(idParametar);
				var name = request.getParameter("nameAttribute");
				address = "/WEB-INF/pages/categories.jsp";
				var tmp = attributeBean.insert(id, new Attribute(name, id));
				if (tmp != null)
					session.setAttribute("notification", "Atribut uspjesno dodan.");
				else
					session.setAttribute("notification", "Atribut nije dodan. Greska.");
		}else
		{}
			} catch (Exception e) {
			
			}

		} else if ("updateAttribute".equals(action)) {
			try {

				address = "/WEB-INF/pages/updateAttribute.jsp";
				var attributeBean = (AttributeBean) session.getAttribute("attributeBean");
				String attributeId = request.getParameter("attributeIdInput");

				String name = attributeBean.findAttribute(attributeId);

				if (attributeId != null && name != null) {
					session.setAttribute("attributeIdUpdateName", name);
					session.setAttribute("attributeIdImp", attributeId);

				}

			} catch (Exception e) {
				
			}
		} else if ("updateCategory".equals(action)) {
			try {
				address = "/WEB-INF/pages/updateCategory.jsp";
				var categoryBean = (CategoryBean) session.getAttribute("categoryBean");
				String categoryId = request.getParameter("categoryIdInput");

				String name = categoryBean.findCategory(categoryId);
				if (categoryId != null && name != null) {
					session.setAttribute("categoryIdUpdateName", name);
					session.setAttribute("categoryIdImp", categoryId);

				}
			} catch (Exception e) {
			
			}
		}
		

		else if ("editAttribute".equals(action)) {
			try {
				address = "/WEB-INF/pages/updateAttribute.jsp";
				var attributeBean = (AttributeBean) session.getAttribute("attributeBean");
				String attributeId = (String) session.getAttribute("attributeIdImp");
				String name = request.getParameter("nameAttributeNew");
				if (attributeId != null && name != null) {
					
					if (attributeBean.update(new Attribute(name, Integer.parseInt(attributeId)))) {
						address = "/WEB-INF/pages/categories.jsp";
						session.setAttribute("notification", "Atribut uspjesno izmjenjen.");
					} else

						session.setAttribute("notification", "Atribut nije izmjenjen. Grska.");
				}

			} catch (Exception e) {
				
			}
		} else if ("editCategory".equals(action)) {
			try {
				address = "/WEB-INF/pages/updateCategory.jsp";
				var categoryBean = (CategoryBean) session.getAttribute("categoryBean");
				String categoryId = (String) session.getAttribute("categoryIdImp");
				String name = request.getParameter("nameCategoryNew");
				if (categoryId != null && name != null) {

					if (categoryBean.update(new Category(Integer.parseInt(categoryId), name))) {
						address = "/WEB-INF/pages/categories.jsp";
						session.setAttribute("notification", "Kategorija uspjesno izmjenjena.");
					} else

						session.setAttribute("notification", "Kategorija nije izmjenjena. Grska.");
				}

			} catch (Exception e) {
			
			}
		} else {

			session.setAttribute("notification", "Pogresni parametri za pristup");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
