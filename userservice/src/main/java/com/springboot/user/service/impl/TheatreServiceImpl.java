package com.springboot.user.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.user.entity.Screening;
import com.springboot.user.entity.Theatre;
import com.springboot.user.repo.ScreeningRepository;
import com.springboot.user.repo.TheatreRepository;
import com.springboot.user.service.TheatreService;
import com.springboot.user.utils.EnumUtils.Screens;
import com.springboot.user.utils.EnumUtils.Theatres;
import com.springboot.user.utils.Utils;

@Service
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreRepository theatreRepository;
	
	@Autowired
	private ScreeningRepository screeningRepository;
	public void addTheatre() {
		
		Theatres theatreArr[] = Theatres.values();
		for(Theatres theatreName : theatreArr) {
		Theatre theatre = theatreRepository.findByName(theatreName.toString());
		if(Utils.isEmpty(theatre))
		{
			 theatre = new Theatre();
			 theatre.setName(theatreName.toString());
			 Screens screens[] = Screens.values();
			 Set<Screening> screeningList = new HashSet<Screening>(screens.length);
			 for(Screens screen : screens)
			 {
				 Screening screening = new Screening();
				 screening.setName(screen.toString());
				 screening.setTheatre(theatre);				 
				 screeningList.add(screening);
			 }
			 theatreRepository.save(theatre);
			 if(!Utils.isEmpty(screeningList))
				 screeningRepository.saveAll(screeningList);
			 
		}
		}
	}
	
}
