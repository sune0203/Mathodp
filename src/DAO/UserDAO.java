package DAO;

import java.util.ArrayList;

import DTO.UserDTO;

public interface UserDAO {
	//데이터 넣고
	public void insert(UserDTO dto);
	
	//ID로 검색
	public UserDTO selectid(String id);
	
	//전체 데이터 가져오기
	public ArrayList<UserDTO> select();
}
