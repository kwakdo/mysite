package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public List<GalleryVo> findAll() {
		return sqlSession.selectList("gallery.findAll");
	}

	public Boolean delete(Long no) {
		return sqlSession.delete("gallery.delete", no) == 1;
	}

	public Boolean upload(GalleryVo vo) {
		boolean result = sqlSession.insert("gallery.upload", vo) == 1;
		return result;
	}

}
