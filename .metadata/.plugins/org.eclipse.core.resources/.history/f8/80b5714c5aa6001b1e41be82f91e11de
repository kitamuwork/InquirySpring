package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Inquiry;
import com.example.demo.repository.InquiryDao;

@Service
public class InquiryServiceImpl implements InquiryService {


	private final InquiryDao inquiryDao;

	@Autowired
	public InquiryServiceImpl(InquiryDao inquiryDao){
		this.inquiryDao = inquiryDao;
	}

	@Override
	public void save(Inquiry inquiry) {
		inquiryDao.insertInquiry(inquiry);
	}

	@Override
	public List<Inquiry> getAll() {
		return inquiryDao.getAll();
	}

}
