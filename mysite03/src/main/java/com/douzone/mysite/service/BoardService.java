package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	public List<BoardVo> getContentsList(int pages) {
		return boardRepository.findAll(pages);
	}
	
	public BoardVo getContent(Long no) {
		BoardVo boardVo = (BoardVo)boardRepository.findByNo(no);
		
		if( boardVo != null ) {
			boardRepository.HitUp(no);
		}
		return boardVo;
	}
	
	
	public boolean deleteMessage(Long no, Long userNo) {
		return boardRepository.delete(no, userNo);
	}
	
	public void updateBoard(BoardVo vo) {
		boardRepository.update(vo);
	}
	public void reply(BoardVo vo) {
		boardRepository.reply(vo);
	}

	


}
