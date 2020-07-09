package bit.or.eesotto.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import bit.or.eesotto.dto.Blog;
import bit.or.eesotto.dto.Qna;

import bit.or.eesotto.service.QnaService;


@Controller
@RequestMapping("/qa/")
public class QaController {

	private static final Logger logger = LoggerFactory.getLogger(QaController.class);
	
	@Autowired
	QnaService qa;
	
	
	// Qna 보러가기
	@RequestMapping(value = "main.bit", method = RequestMethod.GET)
	public String mainView(String cp, String ps, HttpSession session, Model model) {
		
		String userid = (String) session.getAttribute("userid");
		logger.info("로그인 유저 아이디: " + userid);
		
		HashMap<String, Object> map = qa.mainView(cp, ps, userid);
		
		// view까지 전달 (forward)
		model.addAttribute("cpage", map.get("cpage"));
		model.addAttribute("pageSize", map.get("pageSize"));
		model.addAttribute("qnaList", map.get("qnaList")); 		
		model.addAttribute("pageCount", map.get("pageCount"));
		model.addAttribute("totalMsgcount", map.get("totalMsgcount"));
		
		return "qa/main";
	}


	// QNA 상세 페이지 view
	@RequestMapping(value = "detail.bit", method = RequestMethod.GET)
	public String detail(String qaindex, Model model) {

		Qna qna = qa.getPost(qaindex);
		logger.info("내 Qna 글 조회 완료");
		model.addAttribute("qna", qna);
		
		return "qa/detail";
	}
	
	// Qna > Qna글 수정 view
		@RequestMapping(value = "edit.bit", method = RequestMethod.GET)
		public String update(String qaindex, Model model) {
			
			Qna qna = qa.getPost(qaindex);
			logger.info("내 블로그 글 조회 완료");
			model.addAttribute("qna", qna);
			
			return "qa/edit";	
		}
	
	
		// Qna > 글 수정 처리
		@RequestMapping(value = "edit.bit", method = RequestMethod.POST)
		public String update(Qna qna, Model model) {
												
			String msg = null;
			String url = null;
				
			int result = qa.editPost(qna);
		
			if(result==1) {
				
				logger.info("Qna 글 수정 완료");
				msg = "Qna 글 수정 완료";
		        url = "main.bit";
				
			}else { 
				
				logger.info("Qna 글 수정 실패");
				msg = "Qna 글 수정 실패";
		        url = "javascript:history.back();";

			}
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "redirect";	
			
		}
	
		// Qna > 글 삭제 처리
		@RequestMapping(value = "delete.bit", method = {RequestMethod.GET, RequestMethod.POST})
		public String delete(Qna post, Model model) {
												
			String msg = null;
			String url = null;
				
			int result = qa.deletePost(post);
		
			if(result==1) {
				
				logger.info("Qna 글 삭제 완료");
				msg = "Qna 글 삭제 완료";
		        url = "main.bit";
				
			}else { 
				
				logger.info("Qna 글 삭제 실패");
				msg = "Qna 글 삭제 실패";
		        url = "javascript:history.back();";

			}
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "redirect";	
			
		}
	

	// Qna write보러가기
	@RequestMapping(value = "write.bit", method = RequestMethod.GET)
	public String qaWrite() {
	
		return "qa/write";
	}

	// Qna>글쓰기 페이지 view
			@RequestMapping(value = "write.bit", method = RequestMethod.POST)
			public String write(Qna qna, HttpSession session) {

				String userid = (String) session.getAttribute("userid");
				logger.info("로그인 유저 아이디: " + userid);
			
				// 세션 userid post객체에 입력
				qna.setUserid(userid);

				// 임시 petindex 입력
				//message.setMsindex(1);

				int result = qa.writeQna(qna);
				if (result == 1) {
					
					logger.info("Qna 글작성  성공");

					return "redirect:/qa/main.bit";
					
				} else { // 회원가입 실패시 어찌할지 로직구현해야 함

					logger.info("Qna 글작성 실패");

					return "redirect:/qa/main.bit";
				}
	

			}
}
