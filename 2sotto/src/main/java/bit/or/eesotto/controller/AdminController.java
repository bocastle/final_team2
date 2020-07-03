package bit.or.eesotto.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {


	//admin 보러가기
	@RequestMapping(value = "adminMain.bit", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView register(HttpSession session) throws IOException {


		/* 생성한 url 전달 */
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/adminPage");

		return mav;
	}
	
	//adminQA 보러가기
	@RequestMapping(value = "adminQa.bit", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminQa(HttpSession session) throws IOException {


		/* 생성한 url 전달 */
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/adminQa");

		return mav;
	}
	
	//adminQaView 보러가기
		@RequestMapping(value = "adminQaView.bit", method = { RequestMethod.GET, RequestMethod.POST })
		public ModelAndView adminQaView(HttpSession session) throws IOException {


			/* 생성한 url 전달 */
			ModelAndView mav = new ModelAndView();
			mav.setViewName("admin/adminQaView");

			return mav;
		}
		
		//adminQaView 보러가기
				@RequestMapping(value = "adminWrite.bit", method = { RequestMethod.GET, RequestMethod.POST })
				public ModelAndView adminWrite(HttpSession session) throws IOException {


					/* 생성한 url 전달 */
					ModelAndView mav = new ModelAndView();
					mav.setViewName("admin/adminWrite");

					return mav;
				}
				
				//adminPointDetail 보러가기
				@RequestMapping(value = "adminPointDetail.bit", method = { RequestMethod.GET, RequestMethod.POST })
				public ModelAndView adminPointDetail(HttpSession session) throws IOException {


					/* 생성한 url 전달 */
					ModelAndView mav = new ModelAndView();
					mav.setViewName("admin/adminPointDetail");

					return mav;
				}
				//adminPoint List
				@RequestMapping(value = "adminPointList.bit", method = { RequestMethod.GET, RequestMethod.POST })
				public ModelAndView adminPointList(HttpSession session) throws IOException {


					/* 생성한 url 전달 */
					ModelAndView mav = new ModelAndView();
					mav.setViewName("admin/adminPointList");

					return mav;
				}
				
				//adminUser List
				@RequestMapping(value = "adminUserList.bit", method = { RequestMethod.GET, RequestMethod.POST })
				public ModelAndView adminUserList(HttpSession session) throws IOException {


					/* 생성한 url 전달 */
					ModelAndView mav = new ModelAndView();
					mav.setViewName("admin/adminUserList");

					return mav;
				}
				
				//adminUser Detail
				@RequestMapping(value = "adminUserDetail.bit", method = { RequestMethod.GET, RequestMethod.POST })
				public ModelAndView adminUserDetail(HttpSession session) throws IOException {


					/* 생성한 url 전달 */
					ModelAndView mav = new ModelAndView();
					mav.setViewName("admin/adminUserDetail");

					return mav;
				}

}