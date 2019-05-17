package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.QnACommentsDto;

public class QnACommentsDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int commentid = Integer.parseInt(request.getParameter("commentid"));
		
		int result = 0;
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			QnADao dao = new QnADao();
			result = dao.deleteQnAComments(commentid);
			
			if (result > 0) {
				//System.out.println("글 삭제 성공");
			} else { // -1 (제약, 컬럼길이 문제)
				//System.out.println("글 삭제 실패");
			}
			

			forward = new ActionForward(); forward.setRedirect(false);
			forward.setPath("/WEB-INF/QnA/QnAreply.jsp"); //리스트
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
