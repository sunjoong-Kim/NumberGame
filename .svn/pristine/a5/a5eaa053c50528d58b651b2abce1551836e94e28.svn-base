import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

public class ScoreWriter {
	
	/**
	 *  파일을 생성한다.
	 *  - filePath :filePath(이름포함)
	 *  - list : 데이터 list
	 **/
	public void setTxtFile(String filePath, List<Map<String,Object>> list){
		try{
			String setTxtFile = filePath;
			
			String bodyText = "";
			if(list.size()>10){//받은 list가 10개가 넘으면
				for(int i =0 ; i< 10; i++){//10개까지만 for문 
					bodyText = bodyText + list.get(i).get("date") + "\t";
					bodyText = bodyText + list.get(i).get("initials") + "\t";
					bodyText = bodyText + list.get(i).get("pairNum") + "\t";
					bodyText = bodyText + list.get(i).get("score") + "\n";
				}
			}else{//10 이하면,
				for(int i =0 ; i< list.size(); i++){//list의 크기만큼
					bodyText = bodyText + list.get(i).get("date") + "\t";
					bodyText = bodyText + list.get(i).get("initials") + "\t";
					bodyText = bodyText + list.get(i).get("pairNum") + "\t";
					bodyText = bodyText + list.get(i).get("score")  + "\n";
				}
			}
			//파일 생성
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(setTxtFile),"UTF-8"));
			writer.write(bodyText);
			
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
