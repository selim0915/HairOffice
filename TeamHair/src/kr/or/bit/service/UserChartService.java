package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.ChartDao;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.ChartUserDto;

public class UserChartService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		JSONObject jsonObject = new JSONObject();

		JSONArray userlist = new JSONArray();
		try {
			ChartDao dao = new ChartDao();
			List<ChartUserDto> list = dao.userlist();

			for (int i = 0; i < list.size(); i++) {
				ChartUserDto dto = list.get(i);

				JSONObject userinfo = new JSONObject();

				userinfo.put("userid", dto.getUserid());
				userinfo.put("likecount", dto.getLikecount());

				userlist.add(userinfo);
			}
			jsonObject.put("userlist", userlist);

			request.setAttribute("userlist", jsonObject);

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/chart/likeuser_list.jsp");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;
	}

}
