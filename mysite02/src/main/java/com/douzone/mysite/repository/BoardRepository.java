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

			String sql = "select a.no, a.title, a.contents, a.reg_date, a.hit, a.g_no, a.o_no, a.depth, b.no, b.name" + 
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
				vo.setHit(rs.getLong(4));
				vo.setRegDate(rs.getString(5));
				vo.setGroupNo( rs.getLong (6));
				vo.setOrderNo(rs.getLong (7));
				vo.setDepth(rs.getLong (8));
				vo.setUserName(rs.getString(9));
				vo.setUserNo(rs.getLong(10));
				
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
						"   into board" +
						" values (null, ?, ?, 0, now(), 1, 1, 1, ?);";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setLong(3, vo.getUserNo());
				
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

	public void delete(long no, long userNo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
				
		try {
			connection = getConnection();
				
			String sql = " delete from board "
					+ " where no = ? "
					+ " and user_no = ?";
			pstmt = connection.prepareStatement(sql);			
			pstmt.setLong(1, no);
			pstmt.setLong(2, userNo);
								
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
	}

	public boolean update(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql =
					" update board set "
							+ " title = ?  "
							+ " , contents = ? "
							+ " where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
			
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

	public void reply(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			String title = vo.getTitle();
			String contents = vo.getContents();
			long groupNo = vo.getGroupNo();
			long orderNo = vo.getOrderNo();
			long depth = vo.getDepth();
			long userNo = vo.getUserNo();
			long no = vo.getNo();
			
			if(orderNo == 1) {
				orderNo += 1;
			}
			System.out.println(orderNo + ", " + depth +", " + groupNo);
			connection = getConnection();
			
			String sql = "INSERT INTO  "
					+ " board (title, contents, hit, reg_date, g_no, o_no, depth, user_no) "
					+ " select  ?, ?, 0, now(), ? , ?, ?, ? "
					+ " from board where no = ?";
			pstmt = connection.prepareStatement(sql);			
			
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setLong(3, groupNo);
			pstmt.setLong(4, orderNo);
			pstmt.setLong(5, depth+1);
			pstmt.setLong(6, userNo);
			pstmt.setLong(7, no);
							
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		
	}
	
}
	
