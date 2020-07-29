package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberPermitService;
import vo.ActionForward;

public class MemberPermitAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		MemberPermitService mbs = new MemberPermitService();
		boolean result = mbs.memberPermit(id);
		if(result == true) {
			out.println("1");
		} else {
			out.println("0");
		}
		return null;
	}

}
