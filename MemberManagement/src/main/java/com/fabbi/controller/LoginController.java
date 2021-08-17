package com.fabbi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fabbi.repository.MemberRepositoty;
import com.fabbi.util.Helper;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Helper helper;
	private MemberRepositoty memberService;

	public void init() {
		helper = Helper.getInstance();
		memberService = MemberRepositoty.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String passwordRaw = request.getParameter("password");
		
		String password = helper.encrypt(passwordRaw);
		
		boolean result = memberService.getByUsernameAndPassword(username, password);
		
		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			
			response.sendRedirect("/MemberManagement/home");
		} else {
			request.setAttribute("msg", "Username or Password wrong!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
