package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> findAll() {
		// before
		StopWatch sw = new StopWatch();
		sw.start();
		
		List<GuestbookVo> list = sqlSession.selectList("guestbook.findAll");
		
		// after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		System.out.println("[Execution Time][GuestbookRepository.findAll] " + totalTime + "millis");

		return list;
	}
	
	public boolean delete(Long no, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("password", password);
		
		return sqlSession.delete("guestbook.delete", map) == 1;
	}
	
	public boolean insert(GuestbookVo vo) {
		System.out.println(vo);
		boolean result = sqlSession.insert("guestbook.insert", vo) == 1;
		System.out.println(vo);
		
		return result;
	}
}