package bit.or.eesotto.controller;

import java.security.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bit.or.eesotto.dto.Pet;
import bit.or.eesotto.dto.User;
import bit.or.eesotto.service.LoginService;
import bit.or.eesotto.service.ManagementService;

@Controller
@RequestMapping("/management/")
public class ManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@Autowired
	private ManagementService managementService;

	// 반려동물 관리 홈 보여주기
	@RequestMapping(value = "main.bit", method = RequestMethod.GET)
	public String mainView(Principal principal, Model model) {
		
		//String userid = (String)session.getAttribute("userid");
		String userid =  principal.getName();
		logger.info("로그인 유저 아이디: "+userid);
		
		List<Pet> pet = managementService.getPetInfo(userid);
		
		/*
		for(Pet p : pet) {
			
			int age_year = p.getAge() / 12;
			int age_month = p.getAge() % 12;
			
			model.addAttribute("age_year", age_year);
			model.addAttribute("age_month", age_month);
		}
		*/

		if(pet != null) {

			logger.info("반려동물 정보 가져오기 성공");
			model.addAttribute("petInfoList", pet);
		
		} else {
			
			logger.info("반려동물 정보 가져오기 실패");
			return "redirect:/main.bit";
		}
		
		return "management/main";
	}

	// 반려동물 등록 페이지 보여주기
	@RequestMapping(value = "register.bit", method = RequestMethod.GET)
	public String registerPets() {
		return "management/register";
	}
	
	// 반려동물 등록 처리
	@RequestMapping(value = "register.bit", method = RequestMethod.POST)
	public String registerPets(Pet pet, Principal principal, RedirectAttributes redirectAttributes, Model model) {
		
		//////////////////////////파일 업로드 구현 빠진 상태////////////////////////////
		
		//String userid = (String)session.getAttribute("userid");
		String userid =  principal.getName();
		logger.info("반려동물을 등록한 유저 아이디: " + userid);
		
		// 반려동물 등록한 유저 아이디 저장
		pet.setUserid(userid);
		
		//동물 나이 저장
		pet.setAge( (pet.getAge_year()*12)+pet.getAge_month() );
		
		int result = managementService.newPet(pet);
		
		String msg = null;
		String url = null;
		
		if(result == 1) {
			
			logger.info("반려동물 등록 성공");
			
			msg = "반려동물이 등록되었습니다.";
	        url = "../index.jsp";
	        
		} else { 
			
			redirectAttributes.addFlashAttribute("failedRegisterPet", "failed");
			logger.info("반려동물 등록 실패");
			
			msg = "등록 실패";
			url = "javascript:history.back();";

		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "redirect";
	}
	
	
	// 반려동물 상세페이지 view
	/*
	@RequestMapping(value = "showMyPets.bit", method = RequestMethod.GET)
	public String showMyPets(HttpSession session, Model model) {
		
		String userid = (String)session.getAttribute("userid");
		logger.info("로그인 유저 아이디: "+userid);
		
		List<Pet> pet = managementService.getPetInfo(userid);

		if(pet != null) {

			logger.info("반려동물 정보 가져오기 성공");
			model.addAttribute("petInfoList", pet);
		
		} else {
			
			logger.info("반려동물 정보 가져오기 실패");
			return "redirect:/main.bit";
		}
		
		return "management/myPetPage";
	}
	*/
	
	// 반려동물 정보 수정 페이지 보여주기
	@RequestMapping(value = "edit.bit", method = RequestMethod.GET)
	public String edit(int petindex, Model model) {
		
		Pet pet = managementService.editPetInfo(petindex);
		model.addAttribute("petInfo", pet);
		
		return "management/edit";
	}
	
	// 반려동물 정보 수정 처리
	@RequestMapping(value = "edit.bit", method = RequestMethod.POST)
	public String editOk(Pet pet, Model model) {
		
		String msg = null;
		String url = null;
			
		int result = managementService.updatePetInfo(pet);
	
		if(result==1) {
			
			logger.info("반려동물 정보 수정 완료");
			msg = "반려동물 정보 수정 완료";
	        url = "main.bit";
			
		}else { 
			
			logger.info("반려동물 정보 수정 실패");
			msg = "반려동물 정보 수정 실패";
	        url = "javascript:history.back();";

		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "redirect";	
		
	}
	
	// 반려동물 정보 삭제
	@RequestMapping(value = "delete.bit", method = RequestMethod.GET)
	public String delete(int petindex, Model model, RedirectAttributes redirectAttributes) {
		
		int result = managementService.deletePet(petindex);
		
		String msg = null;
		String url = null;
		
		if(result == 1) {
			
			logger.info("반려동물 삭제 성공");
			
			msg = "반려동물 정보가 삭제되었습니다.";
	        url = "main.bit";
	        
		} else { 
			
			redirectAttributes.addFlashAttribute("failedRegisterPet", "failed");
			logger.info("반려동물 삭제 실패");
			
			msg = "삭제 실패";
			url = "javascript:history.back();";

		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "redirect";
	}
	
}