package com.noticeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.DBManager;

import com.noticeboard.dto.NoticeBoardVO;

public class NoticeBoardDAO {
	
	private static NoticeBoardDAO instance=new NoticeBoardDAO();
	
	public static NoticeBoardDAO getInstance(){
		return instance;
	}
	
	//c Read u d
	public List<NoticeBoardVO> searchBoards(String type, String keyword,int firstRow,int endRow){
		String sql="select * from board_notice where "+type+" like ? order by num desc limit ?, ?";
		List<NoticeBoardVO> list=new ArrayList<NoticeBoardVO>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;	
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			//pstmt.setString(1, type);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, firstRow-1);
			pstmt.setInt(3, endRow-firstRow+1);
			rs=pstmt.executeQuery();
			while(rs.next()){
				NoticeBoardVO vo=new NoticeBoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setUserid(rs.getString("userid"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setWritedate(rs.getTimestamp("writedate"));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn,pstmt,rs);
		}
		return list;
	}
	
	//c Read u d
	public List<NoticeBoardVO> selectAllBoards(int firstRow,int endRow){
		String sql="select * from board_notice order by num desc limit ?, ?";
		List<NoticeBoardVO> list=new ArrayList<NoticeBoardVO>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;		
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow-1);
			pstmt.setInt(2, endRow-firstRow+1);
			System.out.println(pstmt);
			rs=pstmt.executeQuery();
			while(rs.next()){
				NoticeBoardVO vo=new NoticeBoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setUserid(rs.getString("userid"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setWritedate(rs.getTimestamp("writedate"));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn,pstmt,rs);
		}
		System.out.println(list);
		return list;		
	}
	
	//c Read u d
	public int getAllCount(){
		String sql="select count(*) from board_notice";
		int result=0;
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try{
			conn=DBManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			rs.next();
			result=rs.getInt(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, stmt, rs);
		}
		return result;
	}
	
	//c Read u d
	public int getSearchCount(String type,String keyword){
		String sql="select count(*) from board_notice where "+type+" like ?";
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		System.out.println(keyword);
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			//pstmt.setString(1, type);
			pstmt.setString(1, "%"+keyword+"%");
			rs=pstmt.executeQuery();
			rs.next();
			result=rs.getInt(1);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	//Create r u d
	public void insertBoard(NoticeBoardVO vo){
		String sql="insert into board_notice(userid,pass,title,content,writedate,fileurl) "+
							"values(?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());			
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			Timestamp time=new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(5,time);
			pstmt.setString(6, vo.getFileurl());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt);
		}
	}
	
	//c r Update d
	public void updateReadCount(String num){
		String sql="update board_notice set readcount=readcount+1 where num=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn,pstmt);
		}
	}
	
	//c Read u d
	public NoticeBoardVO selectOneBoardByNum(String num){
		String sql="select * from board_notice where num=?";
		NoticeBoardVO vo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				vo=new NoticeBoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setUserid(rs.getString("userid"));
				vo.setPass(rs.getString("pass"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWritedate(rs.getTimestamp("writedate"));
				vo.setFileurl(rs.getString("fileurl"));
				vo.setReadcount(rs.getInt("readcount"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}
	
	//c r Update d
	public void updateBoard(NoticeBoardVO vo){
		String sql="update board_notice set userid=?, pass=?,title=?,content=?, fileurl=? where num=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,vo.getUserid());
			pstmt.setString(2,vo.getPass());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			if(vo.getFileurl()!=null){
				pstmt.setString(5, vo.getFileurl());
			}
			
			pstmt.setInt(6, vo.getNum());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn,pstmt);
		}
	}
	
	//c r u Delete
	public void deleteBoard(String num){
		String sql="delete from board_notice where num=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn,pstmt);
		}
	}
	
	//c Read u d
	public int updateCheck(String userid,String num){
		int result=-1;
		String sql="select userid from board_notice where num=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString("userid")!=null&&rs.getString("userid").equals(userid)){
					result=1;
				}else{
					result=-1;
				}
			}else{
				result=-1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn, pstmt, rs);
		}
	
		return result;
	}
	
	/*
	//c Read u d
	public NoticeBoardVO checkPassWord(String pass,String num){
		String sql="select * from board where pass=? and num=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		NoticeBoardVO bVo=null;
		try{
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				bVo=new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBManager.close(conn,pstmt,rs);
		}
		return bVo;
	}
	*/


}
