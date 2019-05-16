package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.QnADto;

public class QnAUpdateFormService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int boardid = Integer.parseInt(request.getParameter("boardid"));
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			QnADao dao = new QnADao();
			QnADto qna = new QnADto();
			
			qna = dao.searchQnA(boardid);
			
			request.setAttribute("qna", qna);
			request.setAttribute("cpage", cpage);

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/QnA/QnAupdate.jsp"); 

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}
}
