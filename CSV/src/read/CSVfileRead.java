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
		
		// CSV ���� �б�
		CSVfileRead csvReader = new CSVfileRead();
		List<List<String>> list = csvReader.readCSV();
		System.out.println("============= read ===============");
		
		System.out.println("line : " + list.size());
		
		Iterator iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
		
		// 2���� �迭 ���·� ����ȴ�. 
		iterator = list.iterator();
		while(iterator.hasNext()) {
			List<String> li = (List<String>) iterator.next();
			for(int i = 0 ; i < li.size(); i++) {
				System.out.println("�� : " + li.get(i));
			}
		}
		
		// ���� �������� �ƴ��� Ȯ�� => Integer.parseInt() , NumberFormatException ������ Ȯ��
		
		System.out.println("===================== Integer.parseInt() , NumberFormatException ������ ���ڿ� ���ڿ� �Ǻ� ====================");
		
		iterator = list.iterator();
		
		Loop1 :
		while(iterator.hasNext()) {
			System.out.println("�׽�Ʈ");
			List<String> li = (List<String>) iterator.next();
			try {
				String name = li.get(0);
				int height = Integer.parseInt(li.get(1));
				int weight = Integer.parseInt(li.get(2));
				System.out.println("�� : " + name + ", �� : " + height + ", �� : " + weight );
			}catch (NumberFormatException e) {
				continue Loop1; 
				// ���������� break, continue ����� ����Ҷ� ������ Ż���� ������ ���� �� �ִ�.
				// while �̳� for �� �ݺ��� ���� "��ɾ� :" �ϸ� �ش� �κб��� Ż��
			}
		}
		
		
		// �ش� csv ���� ����ϱ� ���� User ��� Ŭ���� VO �� ���� ��ü�� �����ϴ� �� 
		
		System.out.println("===================== �ش� csv ���� ����ϱ� ���� User ��� Ŭ���� VO �� ���� ��ü�� �����ϴ� ��  ====================");
		
		iterator = list.iterator();
		List<UserVO> users = new ArrayList<UserVO>(); 
		
		Loop1 :
		while(iterator.hasNext()) {
			System.out.println("VO_�׽�Ʈ");
			List<String> li = (List<String>) iterator.next();
			try {
				String name = li.get(0);
				int height = Integer.parseInt(li.get(1));
				int weight = Integer.parseInt(li.get(2));
				// users ��� ArrayList<UserVO> �� ������ ����
				users.add(new UserVO(name,height,weight));
				
			}catch (NumberFormatException e) {
				continue Loop1; 
				// ���������� break, continue ����� ����Ҷ� ������ Ż���� ������ ���� �� �ִ�.
				// while �̳� for �� �ݺ��� ���� "��ɾ� :" �ϸ� �ش� �κб��� Ż��
			}
		}
		
		for (UserVO uservo : users) {
			System.out.println("name : " + uservo.getName() +" height : " + uservo.getHeight() + " weight : " + uservo.getWeight());
		}
		// or stream ��� (�ڹ� 8 �̻�)
		System.out.println("========== ��Ʈ�� ��� ============");
		users.stream().forEach(u -> System.out.println("name : " + u.getName() + " height : " + u.getHeight() + " weight : " + u.getWeight() ));
		
		
		
		
		
		
		
	}
	public List<List<String>> readCSV(){
		List<List<String>> csvList = new ArrayList<List<String>>();
		File csvfile = new File("C:\\Users\\zecra\\OneDrive\\���� ȭ��\\�޸� ����\\test����������\\testcsv.csv"); // new File("���⿡ .csv ������ �����θ� �Է��Ѵ�.")
		BufferedReader bufferedReader = null;
		String readline = "";
		
		try {
			bufferedReader = new BufferedReader(new FileReader(csvfile));
			Charset.forName("UTF-8");
			readline = bufferedReader.readLine(); 
			// ���� readline �� �տ� ���� �ش� column �� �� name,height,weight �� ������ �ȴ�.
			
			while((readline = bufferedReader.readLine()) != null) { // readline() �� ���Ͽ��� ����� �� ���� �����͸� �о�´�.
				// ������ �̹� �ѹ� readline() ���� �� ó�� column ���� ���� ������ while���� readline�� csv�� �����͸� �ش��� �ǰ�
				// while ���� ���鼭 readline() ��, ��� ������ ���� �ҷ��ö� ���� while ���� �ݺ��ȴ�.
				
				List<String> aLine = new ArrayList<String>();
				String[] token = readline.split(","); // ������ ������ , �� ����� �迭�� ���� �Ѵ�.
				System.out.printf("lineArr : %s , %s , %s \n" , token[0], token[1] , token[2]); // token �迭�� ���� ���� ����� ���� �� �� �ִ�.
				aLine = Arrays.asList(token); // , �� ����� ������ lineArr �迭�� �ϳ��� ���� �ǹ��ϴ� aLine ArrayList�� ����
				csvList.add(aLine); // csvList�� new ArrayList<List<String>>() �̴�. <List>(String) Ÿ���� ����.
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
					// finally ������ new �� ����� ���� close() �ϴ� ������ ������
					// BufferedReader Ŭ������ close() ����
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvList;
	}
	
}
