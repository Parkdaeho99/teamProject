package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.TotalSearchService;
import vo.ActionForward;
import vo.PageInfo;
import vo.Reviews;

public class TotalSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Reviews articleList;
	  	int page=1;
		int limit=8;
		
		String keyword = request.getParameter("keyword");
		request.setAttribute("keyword", keyword);
		System.out.println("keyword>>>>"+keyword);
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("액션에서의 페이지>>"+page);
		TotalSearchService tss = new TotalSearchService();
		int listCount = tss.getListCount(keyword); 
		articleList = tss.getArticleList(page,limit,keyword); 
   		int maxPage=(int)((double)listCount/limit+0.95); 
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
   		System.out.println("액션에서의 listcount>"+listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		ActionForward forward= new ActionForward();
   		forward.setPath("/totalSearchPage.jsp");
   		return forward;
	}

}
