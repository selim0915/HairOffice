package kr.or.bit.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.QnADto;

public class QnAreinsertservice implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int parentref = Integer.parseInt(request.getParameter("parentref"));
		int parentseq = Integer.parseInt(request.getParameter("parentseq"));
		int parentdepth = Integer.parseInt(request.getParameter("parentdepth"));
		String userID = request.getParameter("userid");
		String boardSubject = request.getParameter("boardsubject"); 
		String boardContent = request.getParameter("boardcontent");
		String fileName = request.getParameter("filename");
		
		int result;
		
		try {
			request.setCharacterEncoding("UTF-8");	
			
			QnADto qna = new QnADto();
			QnADao dao = new QnADao();
			
			qna.setUserID(userID);
			qna.setBoardSubject(boardSubject);
			qna.setBoardContent(boardContent);
			qna.setFileName(fileName);
			
			System.out.println(qna.toString());
			
			result = dao.insertQnAreply(qna, parentref, parentseq, parentdepth);

			if (result > 0) {
				System.out.println("등록성공");
			} else { // -1 (제약, 컬럼길이 문제)
				System.out.println("등록실패");
			}
			

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/QnA.do"); //리스트

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
