package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import DB.DB;
import DTO.UserDTO;

public class UserDAOImpl implements UserDAO{

	public UserDAOImpl() {
		Scanner sc = new Scanner(System.in);
	}
	
	@Override
	public void insert(UserDTO dto) {
		//DB 연결
		//쿼리 만들고
		//쿼리 실행
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DB.conn();
			String sql = "INSERT INTO user_tb (id, pw, em, addr, hp, data) VALUES(?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			// 4. 데이터 binding
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getEm());
			pstmt.setString(4, dto.getAddr());
			pstmt.setString(5, dto.getHp());
			pstmt.setTimestamp(6, dto.getTm());
			
			int count = pstmt.executeUpdate();
			
			if (count == 0) {
				System.out.println("데이터 입력 실패");
			} else {
				System.out.println("데이터 입력 성공");
			}

		} catch (Exception e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if( pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	@Override
	public ArrayList<UserDTO> select() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 전달 변수(dto 담을 그릇)
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		try {
			conn = DB.conn();
			String sql = "SELECT * FROM user_tb";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setEm(rs.getString("em"));
				dto.setAddr(rs.getString("addr"));
				dto.setHp(rs.getString("hp"));
				dto.setTm(rs.getTimestamp("data"));
				list.add(dto);
			}

		} catch (Exception e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if( rs != null && !rs.isClosed()){
                    rs.close();
                }
				if( pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public UserDTO selectid(String id) {
		
		//데이터 찾아오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 전달 변수(dto 담을 그릇)
		UserDTO dto = null;
		try {
			conn = DB.conn();
			String sql = "SELECT * FROM user_tb WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); //rs에 데이터가 들어옴
			//쿼리로 보내기
			
			 if (rs.next()) {
				 dto = new UserDTO();
				 dto.setId(rs.getString("id"));
				 dto.setPw(rs.getString("pw"));
				 dto.setEm(rs.getString("em"));
				 dto.setAddr(rs.getString("addr"));
				 dto.setHp(rs.getString("hp"));
				 dto.setTm(rs.getTimestamp("data"));
			 }else {
				 System.out.println("사용자가 없다.");
			 }

		} catch (Exception e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if( rs != null && !rs.isClosed()){
                    rs.close();
                }
				if( pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
}
