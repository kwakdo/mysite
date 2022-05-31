package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardRepository {

	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.10.42:3306/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}
	
	public List<BoardVo> findByNo(int no) {
		
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select a.no, a.title, a.contents, a.reg_date, a.hit, a.group_no, a.order_no, a.depth, b.no, b.name" + 
						 "	from board a, user b" + 
						 "	where a.user_no = b.no and a.no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setRegDate(rs.getString(4));
				vo.setHit(rs.getLong(5));
				vo.setGroupNo( rs.getLong (6));
				vo.setOrderNo(rs.getLong (7));
				vo.setDepth(rs.getLong (8));
				vo.setUserNo(rs.getLong(9));
				vo.setUserName(rs.getString(10));
				
				list.add(vo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}

	public List<BoardVo> findAll() {
		
		List<BoardVo> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String sql =
					" select a.no, a.title, a.contents, a.hit, a.reg_date, a.g_no, a.o_no, a.depth, b.name, a.user_no "
						+ " from board a , user b "
						+ " where a.user_no = b.no "
						+ " order by g_no desc, o_no";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			//6. 결과처리
			while(rs.next()) {				
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setRegDate(rs.getString(4));
				vo.setHit(rs.getLong(5));
				vo.setGroupNo( rs.getLong (6));
				vo.setOrderNo(rs.getLong (7));
				vo.setDepth(rs.getLong (8));
				vo.setUserNo(rs.getLong(9));
				vo.setUserName(rs.getString(10));
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
				System.out.println("드라이버 로딩 실패:" + e);
			
			} finally {
				try {
					if(rs != null) {
						rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return list;	
		}

	public boolean insert(BoardVo vo) {
			boolean result = false;

			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = getConnection();
				
				String sql =
						" insert" +
						"   into guestbook" +
						" values (null, ?, ?, ?, now(), ?, ?, ?, ?);";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setLong(3, vo.getHit());
				pstmt.setLong(4, vo.getGroupNo());
				pstmt.setLong(5, vo.getOrderNo());
				pstmt.setLong(6, vo.getDepth());
				pstmt.setLong(7, vo.getUserNo());
				
				int count = pstmt.executeUpdate();
				result = count == 1;
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
				try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}		
			
			return result;
		}	
	
	}
	
