import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ScoreController {
	
	static SimpleDateFormat format = new SimpleDateFormat("hh:mm yyyy-MM-dd");//날짜 포맷 지정
	
	public static void insertScoreList(String path, List<Map<String,Object>> score){
		List<Map<String,Object>> result = getScoreList(path);
		
		//새로운 데이터를 저장된 데이터가 담겨잇는 동적리스트(result)에 add
		for(int i =0 ; i<score.size() ; i++){
			result.add(score.get(i));
		}

		
		//list 정렬
		Collections.sort(result, new Comparator<Map>() {
			public int compare(Map map1, Map map2) {
				//점수 내림차순 으로 정렬
				if(Integer.parseInt(map1.get("score").toString())
						< Integer.parseInt(map2.get("score").toString())){
					return 1;
				} else if(Integer.parseInt(map1.get("score").toString()) == Integer.parseInt(map2.get("score").toString())){//점수가 같으면 
					try {
						Date date1 = format.parse(map1.get("date").toString());
						Date date2 = format.parse(map2.get("date").toString());
						
						int compare = date1.compareTo(date2);
						//날짜 오름차순으로 정렬
						if(compare > 0){
							return 1;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return -1;
				}else {
					return -1;
				}
			}
		});
		
		ScoreWriter scoreWriter = new ScoreWriter();
		scoreWriter.setTxtFile(path, result);
		
	}
	
	/**
	 * 저장된 파일 경로를 parameter로 받는다.
	 * return은 List<Map<String,Object>>형태  
	 **/
	public static List<Map<String,Object>> getScoreList(String path){
		ScoreReader scoreReader = new ScoreReader();
		
		List<Map<String,Object>> getScoreList = scoreReader.getList(path, "UTF-8");
		
		
		return getScoreList;
	}
	
}
