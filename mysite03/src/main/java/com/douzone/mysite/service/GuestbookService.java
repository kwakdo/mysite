package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVo> getMessage() {
		return null;
	}

	public boolean deleteMessage(Long no, String password) {
		return false;
		
	}

	public boolean addMessage(GuestbookVo vo) {
		return false;
	}


}
