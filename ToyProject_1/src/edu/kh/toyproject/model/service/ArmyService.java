package edu.kh.toyproject.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import edu.kh.toyproject.model.dto.Army;

public class ArmyService extends Army {
	Scanner sc = new Scanner(System.in);
	List<Army> manpower = new ArrayList<Army>();
	Map<Integer, String> weapon = new HashMap<Integer, String>();
	List<Army> newman = new ArrayList<Army>();
	public void ArmyService() {
		manpower.add(new Army("김계란", 22, 2, "평민") );
		manpower.add(new Army("이순신", 24, 2, "선비") );
		manpower.add(new Army("이춘삼", 22, 3, "노비") );
		manpower.add(new Army("이동룡", 26, 3, "상인") );
		manpower.add(new Army("박주한", 30, 4, "상인") );
		manpower.add(new Army("김말똥", 31, 4, "노비") );
		manpower.add(new Army("김홍구", 20, 1, "상인") );
		manpower.add(new Army("이자성", 21, 2, "평민") );
		manpower.add(new Army("황정민", 24, 2, "평민") );
		manpower.add(new Army("황동수", 27, 3, "노비") );
		
		weapon.put(1, "활");
		weapon.put(2, "화승총");
		weapon.put(3, "창");
		weapon.put(4, "석궁");
		
		
		
	}
	
	public void displayMenu() {
		/*
		    1. 전체 병사 조회
			2. 값을 받은 후, 신분에 일치하는 병사 조회
			3. 나이순으로 정렬
			4. 경력순으로 정렬
			5. 새로운 병사 모집 ( 난수를 4개 발생시킨후 랜덤생성 3개 + 입력받아서 그중 하나를 새 병사 목록에 추가
			6. 병사 전역시키기 ( 인덱스 번호에 맞는 병사를 remove로삭제)
			7. 무기 지급 후 부대 편성
			0. 프로그램 종료
		*/
		try {
			
		int action = -1;
		while(action != 0) {
		System.out.println("---병사 징집 프로그램을 실시하겠습니다.---");
		System.out.println("--- 메뉴 로딩중 ---");
		System.out.println("1. 전체 병사 조회");
		System.out.println("2. 신분과 일치하는 병사 조회");
		System.out.println("3. 나이순으로 병사 정렬");
		System.out.println("4. 경력순으로 정렬");
		System.out.println("5. 새로운 병사 모집");
		System.out.println("6. 병사 전역시키기");
		System.out.println("7. 무기 지급");		
		System.out.println("0. 프로그램 종료");
		
		System.out.print("하실 행동을 선택하세요 : ");	
		action = sc.nextInt();
		
		switch(action) {
		 case 1 : allPeople(); break;
		 case 2 : identPeople(); break;
		 case 3 : sortByAge(); break;
		 case 4 : sortByExp(); break;
		 case 5 : armySupply(); break;
		 case 6 : removeMan(); break; 
		 case 7 : giveWeapon(); break; 		 
		 case 0 : System.out.println("프로그램을 종료하겠습니다. 오늘도 수고하셨습니다."); break;
		 default : System.out.println("그 행동은 프로그램에 등록되어 있지 않습니다.");	 
		}
		
		
		}
		} catch(Exception e) {
			System.out.println("올바른 값을 입력하지 않으면, 다음 단계로 진행되지 않으며, 프로그램은 종료됩니다.");
			e.printStackTrace();
		}
		
		
	}
	
	public void allPeople() throws Exception{
		int index = 0;
		for(Army army : manpower) {
			System.out.println((index+1) + "번 병사 : " +army);
			index++;
		}
		
		if( manpower.size() < 10) {
			System.out.println("최소 10명은 필요합니다. 인원을 보충하시기 바랍니다.");
			armySupply();
		}
	}
	
	public void identPeople() {
		Set<Army> people = new HashSet<Army>();
		System.out.println("============= 신분으로 병사 조회 ==============");
		System.out.print("어떤 신분의 병사들을 조회하시겠습니까? : ");
		sc.nextLine();
		String input = sc.nextLine();
		System.out.println("조회 결과를 아래에 출력합니다.");
		for(Army a : manpower) {
			if(input.equals(a.getIdent())) {
				people.add(a);
			} 
		}
		if(people.isEmpty()) {System.out.println("신분에 맞는 병사가 없습니다.");;}
		
		for ( Army a : people) {
			System.out.println(a);
		}
		
	}
	
	public void sortByAge() {
		System.out.println("=========== 나이순으로 병사 조회============");
		Collections.sort(manpower);
		
		for(Army a : manpower) {
			System.out.println(a);
		}
		
	}
	
	public void sortByExp() {
		System.out.println("=============== 군 경력순으로 병사 조회==================");
		Comparator<Army> expComparator = Comparator.comparing(Army::getExp);
		
		Collections.sort(manpower, expComparator);
		for (Army a : manpower) {
			System.out.println(a);
		}
	}
	
	public void armySuplly_assist() {
		String name =  randomName();
		int age = (int)(Math.random() * 20  + 20 );
		int exp = (int)(age * 0.1);
		
		int identran = (int)(Math.random() * 5);
		String ident = "";
		switch(identran) {
		case 1 : ident = "선비"; break;
		case 2 : ident = "평민"; break;
		case 3 : ident = "상인"; break;
		case 4 : ident = "노비"; break;
		}
		newman.add(new Army(name, age, exp, ident));
	}
	
	public void armySupply() throws Exception{
		System.out.println("==================== 징병 가능한 병사 명단입니다 ==========================");
		armySuplly_assist();
		armySuplly_assist();
		armySuplly_assist();
		int index = 0;
		
		for(Army newman : newman) {
			System.out.println((index+1) + "번 후보" + newman); index++;
		} 
		System.out.println();
		System.out.println("모집할 병사의 번호를 입력해 주십시오[ 1 ~ 3 ]");
		int select = sc.nextInt();
		switch(select) {
		case 1 : manpower.add(newman.get(0)); break;
		case 2 : manpower.add(newman.get(1)); break;
		case 3 : manpower.add(newman.get(2)); break;
		default : System.out.println(" 1과 3 사이의 숫자만 입력 가능합니다.");
		}
		
		System.out.println("모집 완료되었습니다.");
		
	}
	
	public void removeMan()  {
	
		System.out.println("============== 전역시킬 병사를 선택해 주십시오================");
		try {
			allPeople();
			
			System.out.println("번호 선택");
			int remove = sc.nextInt();
			
			manpower.remove(remove-1);
			
			
			System.out.println((remove) + "번 병사의 전역이 완료되었습니다.");
			
		} catch(Exception e) {
			System.out.println("범위를 초과한 값을 입력하거나, 잘못된 값이 입력되었습니다.");
		}
	}
	
	public void giveWeapon() {
		
		System.out.println("부대원들에게 지급할 무기를 선택하여 주십시오!");
		int input = sc.nextInt();
		
		switch(input) {
		case 1 : System.out.println( "부대원들에게 '"+weapon.put(1, getIdent()) + "'이 지급되었습니다"); break;
		case 2 : System.out.println( "부대원들에게 '"+weapon.put(2, getIdent()) + "'이 지급되었습니다"); break;
		case 3 : System.out.println( "부대원들에게 '"+weapon.put(3, getIdent()) + "'이 지급되었습니다"); break;
		case 4 : System.out.println( "부대원들에게 '"+weapon.put(4, getIdent()) + "'이 지급되었습니다"); break;
		default : System.out.println("저희 훈련소는 그런 무기를 보유하고 있지 않습니다.");
		}
	}
}
