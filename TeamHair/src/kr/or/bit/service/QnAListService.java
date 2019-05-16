package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.QnADto;

public class QnAListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		try {
			QnADao qnadao = new QnADao();
			int totalboardCount = qnadao.totalboardCount();
			String scpage = request.getParameter("cpage");
			String spagesize = request.getParameter("pagesize");

			if (scpage == null || scpage.trim().equals("")) {
				scpage = "1";
			}
			if (spagesize == null || spagesize.trim().equals("")) {
				spagesize = "5";
			}

			int pagesize = Integer.parseInt(spagesize);
			int cpage = Integer.parseInt(scpage);
			int pagecount = 0;

			if (totalboardCount % pagesize == 0) {
				pagecount = totalboardCount / pagesize;
			} else {
				pagecount = (totalboardCount / pagesize) + 1;
			}

			List<QnADto> qnalist = qnadao.QnAlist(cpage, pagesize);

			request.setAttribute("cpage", cpage);
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("qnalist", qnalist);
			request.setAttribute("totalboardCount", totalboardCount);

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/QnA/QnA.jsp");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}

}
