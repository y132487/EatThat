package com.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBManager;

import com.member.dto.MemberVO;

public class MemberDAO {
	private static MemberDAO instance=new MemberDAO();
	
	public static MemberDAO getInstance(){
		return instance;
	}
	
	//Create r u d
	public int insertMember(MemberVO vo){
		int result=-1;
		String sql="insert into member value(?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,vo.getName());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getPass());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5,vo.getPhone());
			result=pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn,pstmt);
		}
		return result;
	}
	
	//c Read u d
	public int confirmID(String userid){
		int result=-1;
		String sql="select userid from member where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=1; //해당 아이디가 있으면 1
			}else{
				result=-1; //없을 경우 -1
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				DBManager.close(conn, pstmt,rs);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//c Read u d
	public int userCheck(String userid,String pass){
		int result=-1;
		String sql="select pass from member where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString("pass")!=null&&rs.getString("pass").equals(pass)){
					result=1; //해당 아이디가 있으면 1
				}else{
					result=0;
				}				
			}else{
				result=-1; //없을 경우 -1
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	//c Read u d
	public MemberVO getMember(String userid){
		MemberVO vo=null;
		String sql="select * from member where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs=pstmt.executeQuery();
			if(rs.next()){ //아이디가 같을 경우
				vo=new MemberVO(); //MemberVO의 객체를 생성하여 각 멤버변수에 table어 저장된 값을 받아온다
				vo.setName(rs.getString("name"));
				vo.setUserid(rs.getString("userid"));
				vo.setPass(rs.getString("pass"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}
	
	//c r Update d
	public int updateMember(MemberVO vo){
		int result=-1;
		String sql="update member set pass=?, email=?,phone=? where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4,vo.getUserid());
			result=pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	//c r u Delete
	public void deleteMember(String userid){
		String sql="delete from member where userid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}
	}
	
}
