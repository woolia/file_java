package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CSVfileRead {

	public static void main(String[] args) {
		
		// CSV 파일 읽기
		CSVfileRead csvReader = new CSVfileRead();
		List<List<String>> list = csvReader.readCSV();
		System.out.println("============= read ===============");
		
		System.out.println("line : " + list.size());
		
		Iterator iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
		
		// 2차원 배열 형태로 저장된다. 
		iterator = list.iterator();
		while(iterator.hasNext()) {
			List<String> li = (List<String>) iterator.next();
			for(int i = 0 ; i < li.size(); i++) {
				System.out.println("값 : " + li.get(i));
			}
		}
		
		// 값이 숫자인지 아닌지 확인 => Integer.parseInt() , NumberFormatException 에러로 확인
		
		System.out.println("===================== Integer.parseInt() , NumberFormatException 에러로 문자열 숫자열 판별 ====================");
		
		iterator = list.iterator();
		
		Loop1 :
		while(iterator.hasNext()) {
			System.out.println("테스트");
			List<String> li = (List<String>) iterator.next();
			try {
				String name = li.get(0);
				int height = Integer.parseInt(li.get(1));
				int weight = Integer.parseInt(li.get(2));
				System.out.println("값 : " + name + ", 값 : " + height + ", 값 : " + weight );
			}catch (NumberFormatException e) {
				continue Loop1; 
				// 루프문에서 break, continue 제어문을 사용할때 어디까지 탈출할 것인지 정할 수 있다.
				// while 이나 for 의 반복문 위에 "명령어 :" 하면 해당 부분까지 탈출
			}
		}
		
		
		// 해당 csv 값을 사용하기 위해 User 라는 클래스 VO 를 만들어서 객체를 저장하는 법 
		
		System.out.println("===================== 해당 csv 값을 사용하기 위해 User 라는 클래스 VO 를 만들어서 객체를 저장하는 법  ====================");
		
		iterator = list.iterator();
		List<UserVO> users = new ArrayList<UserVO>(); 
		
		Loop1 :
		while(iterator.hasNext()) {
			System.out.println("VO_테스트");
			List<String> li = (List<String>) iterator.next();
			try {
				String name = li.get(0);
				int height = Integer.parseInt(li.get(1));
				int weight = Integer.parseInt(li.get(2));
				// users 라는 ArrayList<UserVO> 에 값들을 저장
				users.add(new UserVO(name,height,weight));
				
			}catch (NumberFormatException e) {
				continue Loop1; 
				// 루프문에서 break, continue 제어문을 사용할때 어디까지 탈출할 것인지 정할 수 있다.
				// while 이나 for 의 반복문 위에 "명령어 :" 하면 해당 부분까지 탈출
			}
		}
		
		for (UserVO uservo : users) {
			System.out.println("name : " + uservo.getName() +" height : " + uservo.getHeight() + " weight : " + uservo.getWeight());
		}
		// or stream 사용 (자바 8 이상)
		System.out.println("========== 스트림 사용 ============");
		users.stream().forEach(u -> System.out.println("name : " + u.getName() + " height : " + u.getHeight() + " weight : " + u.getWeight() ));
		
		
		
		
		
		
		
	}
	public List<List<String>> readCSV(){
		List<List<String>> csvList = new ArrayList<List<String>>();
		File csvfile = new File("C:\\Users\\zecra\\OneDrive\\바탕 화면\\메모 파일\\test데이터폴더\\testcsv.csv"); // new File("여기에 .csv 파일의 절대경로를 입력한다.")
		BufferedReader bufferedReader = null;
		String readline = "";
		
		try {
			bufferedReader = new BufferedReader(new FileReader(csvfile));
			Charset.forName("UTF-8");
			readline = bufferedReader.readLine(); 
			// 먼저 readline 을 앞에 빼면 해당 column 값 즉 name,height,weight 가 빠지게 된다.
			
			while((readline = bufferedReader.readLine()) != null) { // readline() 은 파일에서 개행된 한 줄의 데이터를 읽어온다.
				// 위에서 이미 한번 readline() 으로 맨 처음 column 값을 뺏기 때문에 while문의 readline은 csv의 데이터만 해당이 되고
				// while 문을 돌면서 readline() 즉, 모든 데이터 값을 불러올때 까지 while 문이 반복된다.
				
				List<String> aLine = new ArrayList<String>();
				String[] token = readline.split(","); // 파일의 한줄을 , 로 나누어서 배열에 저장 한다.
				System.out.printf("lineArr : %s , %s , %s \n" , token[0], token[1] , token[2]); // token 배열에 값이 각각 저장된 것을 알 수 있다.
				aLine = Arrays.asList(token); // , 로 나누어서 저장한 lineArr 배열을 하나의 줄을 의미하는 aLine ArrayList에 저장
				csvList.add(aLine); // csvList는 new ArrayList<List<String>>() 이다. <List>(String) 타입이 들어간다.
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(bufferedReader != null) {
					bufferedReader.close(); 
					// finally 에서는 new 로 열어둔 것을 close() 하는 습관을 들이자
					// BufferedReader 클래스는 close() 하자
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvList;
	}
	
}
