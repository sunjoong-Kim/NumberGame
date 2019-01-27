import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreReader {

	/**
	 * 저장된 파일의 데이터를 가져온다
	 * parameter 
	 * -path : 파일경로
	 * -encoding: encoding 타입 
	 **/
	public List<Map<String,Object>> getList(String path, String encoding) {
		List<Map<String,Object>> result = new ArrayList();
		
		BufferedReader br = null;
		String text = "";
		String line;
		try {
			//데이터 reading
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));
			while ((line = br.readLine()) != null) {//한줄씩 읽어옴
				
				Map<String,Object> map = new HashMap();
				
				// "\t"을 기준으로 date, initials, pairNum , score 형태로 나눠 map에 담음
				String[] lineSplit = line.split("\t");
				map.put("date", lineSplit[0]);
				map.put("initials", lineSplit[1]);
				map.put("pairNum", lineSplit[2]);
				map.put("score", lineSplit[3]);
				
				result.add(map);
			}
		} catch (FileNotFoundException e) {//파일이 없으면,
			ScoreWriter scoreWriter = new ScoreWriter();
			scoreWriter.setTxtFile(path, new ArrayList());//빈 파일 생성
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	/**
	 * strDir에 있는 파일 리스트를 불러온다 (현재 위 프로그램에서는 사용되지 않는다.) 
	 **/
	public List<String> getDirectory(String strDir) throws IOException {
		List<String> result = new ArrayList<>();
		File dir = new File(strDir);
		File[] fileList = dir.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			File file = fileList[i];
			if (file.isFile()) {
				//     
				result.add(file.getName().toString());
			}
		}
		return result;
	}
}
