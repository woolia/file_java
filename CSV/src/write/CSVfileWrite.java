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
		
		// CSV ���� ���� / �̾��
		
		CSVfileWrite csvWriter = new CSVfileWrite();
		csvWriter.writeCSV(dataList);
		
		// �����Ͱ� �뷫 List<List<String>> �̷��� ���;� �Ѵ�.
		// why?? => [[name,height,weight],[AA,180,80],[BB,170,65]] ==> �̷������� �����Ͱ� �� �� �־ 
		// [1�� ������ ����Ʈ, 2�� ������ ����Ʈ , 3�� ������ ����Ʈ]
	}
	
	public void writeCSV(List<List<String>> dataList) {
		File csv = new File("C:\\Users\\zecra\\OneDrive\\���� ȭ��\\�޸� ����\\test����������\\testwritecsv.csv"); // new File("���⿡ .csv������ �����θ� �Է��Ѵ�.")
		BufferedWriter bufferedWriter = null;
		
		Iterator iterator = dataList.iterator();
		
		
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(csv, true));
			// csv������ ���� ���� �̾���� ����ó�� true�� �����ϰ� , ���� ���� ������� true�� �����Ѵ�.
			for(int i = 0; i< dataList.size(); i++) {
				String aData = "";
				List<String> list = (List<String>) iterator.next();
				aData = list.get(0) + "," + list.get(1) + "," + list.get(2);
				// �� �ٿ� ���� �� ������ ���̿� , �� �ִ´�.
				// String���� �ƿ� csv ���� ���ڿ��� �����. name,height,weight , AA,180,60 �̷���
				
				bufferedWriter.write(aData); //  �ۼ��� String �����͸� ���Ͽ� �ִ´�.
				bufferedWriter.newLine(); // newLine() �ϰ� �Ǹ� ���� �������� �ѱ��. => ����
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
					bufferedWriter.flush(); // �����ִ� �����ͱ��� ������
					bufferedWriter.close(); // ����� BufferedWriter �� �ݾ� �ش�.
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
