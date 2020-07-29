package action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardWriteProService;
import vo.ActionForward;
import vo.Reviews;

public class BoardWriteProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Date cal = Calendar.getInstance().getTime();
   		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
   		String datestr = sdf.format(cal.getTime());
   		
		HttpSession session = request.getSession();
		ActionForward forward=null;
		Reviews reviews = null;
		String saveFolder="c:/boardUpload";
//		String saveFolder="http://parkdaeho.dothome.co.kr/boardUpload";
		int fileSize=5*1024*1024;
		System.out.println("saveFolder >>"+saveFolder);
		MultipartRequest multi=new MultipartRequest(request,
				saveFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		
		
		reviews = new Reviews();
		System.out.println("saveFolder: "+saveFolder);
		
		ArrayList<String> category = new ArrayList<String>();
		category.add(multi.getParameter("category"));
		ArrayList<String> title = new ArrayList<String>();
		title.add(multi.getParameter("title"));
		ArrayList<String> image = new ArrayList<String>();
		image.clear();
//		image.add(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));		
//		image.add(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));		
//		image.add(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		System.out.println("여기1");
		if(multi.getFilesystemName("image") != null) {
			File oldFile = new File(saveFolder+"/"+multi.getFilesystemName("image"));
			System.out.println("saveFolder+\"/\"+multi.getFilesystemName(\"image\")>>"+saveFolder+"/"+multi.getFilesystemName("image"));
			File newFile = new File(saveFolder+"/"+category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image"));
			System.out.println("saveFolder+\"/\"+category.get(0)+\"_\"+datestr+\"_\"+multi.getFilesystemName(\"image\")>>"+saveFolder+"/"+category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image"));
			oldFile.renameTo(newFile);
			System.out.println("1번 파일 업로드");
			image.add(category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image"));
			System.out.println(image.get(0));
		}
		if(multi.getFilesystemName("image2") != null) {
			File oldFile = new File(saveFolder+"/"+multi.getFilesystemName("image2"));
			File newFile = new File(saveFolder+"/"+category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image2"));
			oldFile.renameTo(newFile);
			System.out.println("2번 파일 업로드");
			image.add(category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image2"));
			System.out.println(image.get(1));
		} else image.add("이미지없음");
		if(multi.getFilesystemName("image3") != null) {
			File oldFile = new File(saveFolder+"/"+multi.getFilesystemName("image3"));
			File newFile = new File(saveFolder+"/"+category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image3"));
			oldFile.renameTo(newFile);
			System.out.println("3번 파일 업로드");
			image.add(category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image3"));		
			System.out.println(image.get(2));
		} else image.add("이미지없음");
		
		System.out.println("여기2");
		
		ArrayList<String> user = new ArrayList<String>();
		user.add((String)session.getAttribute("id"));
		ArrayList<String> content = new ArrayList<String>();
		content.add(multi.getParameter("content"));
		reviews.setCategory(category);
		reviews.setTitle(title);
		reviews.setImage(image);
		reviews.setUser(user);
		reviews.setContent(content);
		BoardWriteProService boardWriteProService = new BoardWriteProService();
		boolean isWriteSuccess = boardWriteProService.registArticle(reviews);

		if(!isWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		else{
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardList.do");
		}

		return forward;
		
	}  	
	
}