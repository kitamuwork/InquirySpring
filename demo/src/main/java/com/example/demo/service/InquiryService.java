package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Inquiry;

public interface InquiryService {
	public void save(Inquiry inquiry);
	public void update(Inquiry inquiry);
	public List<Inquiry> getAll();
}
