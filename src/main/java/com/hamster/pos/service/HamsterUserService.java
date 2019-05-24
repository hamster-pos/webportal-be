package com.hamster.pos.service;

import java.util.Optional;

import com.hamster.pos.dto.BasicDTO;
import com.hamster.pos.dto.HamsterUserDTO;

public interface HamsterUserService {

	public Object authenticateUser(HamsterUserDTO hamsterUserDTO);
	public void applyLicense();
}
