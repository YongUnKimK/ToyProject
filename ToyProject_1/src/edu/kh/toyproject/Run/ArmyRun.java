package edu.kh.toyproject.Run;

import edu.kh.toyproject.model.service.ArmyService;

public class ArmyRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArmyService service = new ArmyService(); 
		service.ArmyService();
		service.displayMenu();
	}

}
