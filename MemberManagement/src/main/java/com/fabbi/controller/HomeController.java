package com.fabbi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fabbi.entity.Member;
import com.fabbi.repository.MemberRepositoty;


@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberRepositoty memberService;

	public void init(){
		memberService = MemberRepositoty.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action == null) {
			action = "";
		}
		
		switch(action) {
		case "logout":
			logout(request, response);
			break;
		default:
			showMember(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void showMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> memberList = memberService.getAll();
		request.setAttribute("memberList", memberList);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect("/MemberManagement/login");
	}
}
