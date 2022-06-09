package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;

	private Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.10.42:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return connection;
	}
	
	public List<BoardVo> findByNo(long no) {
		return sqlSession.selectList("board.findByNo");

	}

	public List<BoardVo> findAll(int pages) {
		
		List<BoardVo> list = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			connection = getConnection();
			
			String sql =
					" select a.no, a.title, a.contents, a.hit, "
							+ "a.reg_date, a.g_no, a.o_no, a.depth, b.name, a.user_no "
							+ " from board a , user b "
							+ " where a.user_no = b.no "
							+ " order by g_no desc, o_no asc, depth asc  "
							+ " limit ?, 5";			
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, (pages - 1) * 5);
			
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
					if(connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return list;	
		}

	public boolean insert(BoardVo vo) {
			boolean result = false;

			Connection connection = null;
			PreparedStatement pstmt = null;
			try {
				connection = getConnection();
				
				String sql = "insert into  " +
								" board (title, contents, hit, reg_date, g_no, o_no, depth, user_no) " +
								" select ?, ?, 0, now(), max(g_no) + 1 , 1, 0, ? " +
								" from board";
				pstmt = connection.prepareStatement(sql);
				
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
					if(connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}		
			
			return result;
		}

	public boolean delete(long no, long userNo) {
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
		return false;	
		
	}

	public boolean update(BoardVo vo) {
		boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();
			
			String sql =
					" update board set "
							+ " title = ?  "
							+ " , contents = ? "
							+ " where no = ?";
			pstmt = connection.prepareStatement(sql);
			
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
				if(connection != null) {
					connection.close();
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
	
			connection = getConnection();
			
			if(orderNo == 1) {
				String sql = "insert into  "
					+ " board (title, contents, hit, reg_date, g_no, o_no, depth, user_no) "
					+ " select  ?, ?, 0, now(), ? , Max(o_no)+1, ?, ? "
					+ " from board where no = ?";
				pstmt = connection.prepareStatement(sql);			
			
				pstmt.setString(1, title);
				pstmt.setString(2, contents);
				pstmt.setLong(3, groupNo);
				//pstmt.setLong(4, o_no);
				pstmt.setLong(4, depth + 1);
				pstmt.setLong(5, userNo);
				pstmt.setLong(6, no);
			
			} else { String sql = "insert into  "
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
			}
			
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

	public boolean HitUp(long no) {
		boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = 
					  "  update board"
					+ "  set hit = hit + 1"
					+ "  where no = ?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();
			
			result = count == 1;
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
		
		return result;
		
	}

	public int count() {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			
			String sql =
				"select count(*)" +
				"	from board";
			pstmt = connection.prepareStatement(sql);
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
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
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}	

	
	
}
	
