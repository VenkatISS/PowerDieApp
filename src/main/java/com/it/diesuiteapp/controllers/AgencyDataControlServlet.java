package com.it.diesuiteapp.controllers;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.it.diesuiteapp.framework.model.vos.AgencyVO;
import com.it.diesuiteapp.processor.ProcessorBean;
import com.it.diesuiteapp.processor.RequestResponseProcessorBean;


/**
 * Servlet implementation class AgencyDataControlServlet
 */
@WebServlet("/AgencyDataControlServlet")
@MultipartConfig
public class AgencyDataControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgencyDataControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("jsp/pages/app.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String jsp_page = "jsp/pages/app.jsp";
		HttpSession session = request.getSession(true);
		AgencyVO voObj = (AgencyVO) session.getAttribute("agencyVO");
		if( (voObj != null) && (voObj.getAgency_code() > 0 ) ) {
			jsp_page = request.getParameter("page");
			String actionId = request.getParameter("actionId") != null ? request.getParameter("actionId") : "";
			if(actionId.length()>0){
				ProcessorBean processBean = new RequestResponseProcessorBean();
				processBean.process(request, response);
			} else {
				jsp_page = "jsp/pages/app.jsp";
			}
		}	
		RequestDispatcher rd = request.getRequestDispatcher(jsp_page);
		rd.forward(request, response);	
	}

}
