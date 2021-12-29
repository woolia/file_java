package write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import read.CSVfileRead;

public class CSVfileWrite {

	public static void main(String[] args) {

		CSVfileRead csvReader = new CSVfileRead();
		List<List<String>> dataList = csvReader.readCSV();
		
		// CSV 파일 쓰기 / 이어쓰기
		
		CSVfileWrite csvWriter = new CSVfileWrite();
		csvWriter.writeCSV(dataList);
		
		// 데이터가 대략 List<List<String>> 이렇게 나와야 한다.
		// why?? => [[name,height,weight],[AA,180,80],[BB,170,65]] ==> 이런식으로 데이터가 들어갈 수 있어서 
		// [1번 데이터 리스트, 2번 데이터 리스트 , 3번 데이터 리스트]
	}
	
	public void writeCSV(List<List<String>> dataList) {
		File csv = new File("C:\\Users\\zecra\\OneDrive\\바탕 화면\\메모 파일\\test데이터폴더\\testwritecsv.csv"); // new File("여기에 .csv파일의 절대경로를 입력한다.")
		BufferedWriter bufferedWriter = null;
		
		Iterator iterator = dataList.iterator();
		
		
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(csv, true));
			// csv파일의 기존 값에 이어쓰려면 위에처럼 true를 지정하고 , 기존 값을 덮어쓰려면 true를 삭제한다.
			for(int i = 0; i< dataList.size(); i++) {
				String aData = "";
				List<String> list = (List<String>) iterator.next();
				aData = list.get(0) + "," + list.get(1) + "," + list.get(2);
				// 한 줄에 넣을 각 데이터 사이에 , 를 넣는다.
				// String으로 아예 csv 형태 문자열로 만든다. name,height,weight , AA,180,60 이렇게
				
				bufferedWriter.write(aData); //  작성한 String 데이터를 파일에 넣는다.
				bufferedWriter.newLine(); // newLine() 하게 되면 다음 라인으로 넘긴다. => 개행
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(bufferedWriter != null) {
					bufferedWriter.flush(); // 남아있는 데이터까지 보내줌
					bufferedWriter.close(); // 사용한 BufferedWriter 를 닫아 준다.
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
