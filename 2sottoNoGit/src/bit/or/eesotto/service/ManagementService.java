package bit.or.eesotto.service;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import bit.or.eesotto.dao.PetDao;
import bit.or.eesotto.dao.UserDao;
import bit.or.eesotto.dto.Pet;
import bit.or.eesotto.dto.User;

@Service
public class ManagementService {

	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	@Autowired
	UserDao UserDao;

	public User normalLogin(String userid) {
		UserDao = sqlsession.getMapper(UserDao.class);
		return UserDao.getUser(userid);
	}
	
	
	@Autowired
	PetDao petDao;
	
	
	//반려동물 등록하기
	public int newPet(Pet pet) {
		
		int result = 0;
		
		try {
			petDao = sqlsession.getMapper(PetDao.class);
			result = petDao.newPet(pet);
		} catch (Exception e) {
			System.out.println("managementService newPet() 문제 발생" + e.getMessage());
		}
		
		return result;
	}
	
	

}