package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

@WebServlet("*.brh")
public class BranchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BranchController() {
    	
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlCommand = requestURI.substring(contextPath.length());
	
    	Action action = null;
    	ActionForward forward = new ActionForward();
    	
      	if(urlCommand.equals("/Branch.brh")) { // 지점 메인
    		try {
    			System.out.println("/Branch.brh");
    			forward.setRedirect(false);
    			forward.setPath("/WEB-INF/branch/branch_list.jsp");
			}catch(Exception e) {
					e.printStackTrace();
			}
      	}  else if(urlCommand.equals("/BranchnoSearch.brh")) { // 공간 검색 정보
    		try {
    			System.out.println("/Space.brh");
    			forward.setRedirect(false);
    			forward.setPath("/WEB-INF/branch/branch_search.jsp");
			}catch(Exception e) {
					e.printStackTrace();
			}	
    	}  else if(urlCommand.equals("/Space.brh")) { // 공간 메인 정보
    		try {
    			System.out.println("/Space.brh");
    			forward.setRedirect(false);
    			forward.setPath("/WEB-INF/space/seoul_office.jsp");
			}catch(Exception e) {
					e.printStackTrace();
			}
    	} else if(urlCommand.equals("/GangnamBranchInfo.brh")) { // 강남오피스 지점 정보
    		try {
    			System.out.println("/Space.brh");
    			forward.setRedirect(false);
    			forward.setPath("/WEB-INF/branch/salone_reserve_infor.jsp");
			}catch(Exception e) {
					e.printStackTrace();
			}
    	} else if(urlCommand.equals("/GangnamOfficeInfo.brh")) { // 강남오피스의 공간 정보
    		try {
    			System.out.println("/GangnamOfficeInfo.brh");
    			forward.setRedirect(false);
    			forward.setPath("/WEB-INF/space/seoulgangnam.jsp");
			}catch(Exception e) {
					e.printStackTrace();
			}
    	}

      	
      	
      	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}



    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
