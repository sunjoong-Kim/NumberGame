import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * <b>NumberGame</b>
 * <pre>
 * @file : util
 *           └ utils.java
 *
 * --------------------------------------------------------
 *                                 
 * --------------  -------------  -------------------------
 * 2018. 5. 29.    ksj  
 *
 * --------------------------------------------------------
 *</pre>
 * @date : 2018. 5. 29.
 * @author : ksj
 * @version : 1.0 (jdk 1.6)
 */
public class Utils {
	
	static int emptyListIndex = 0 ;//  
//	public static void main(String[] args) {
//		int [][] arr = {{9,8,7,6,5}
//						,{0,0,0,0,0}
//						,{0,0,0,0,0}
//						,{1,1,1,1,1}
//		};
//		getRepaintArr(arr);
//	}
//	
	
	static int z = 0;
	
	public static int[][] makeRandomArray(int row, int col){
		Random random = new Random();
		
		int [][] result = new int[row][col];
		
		for(int i = 0; i< row; i++){
			for(int j = 0; j<col; j++){
				int randomElement = random.nextInt(9) +1; //1~9   
				result[i][j] = randomElement; //int[i][j]  .
			}
		}
		
		return result;
	}
	
	/**
	 *       . 
	 *  (row1,col1)       .
	 *   true  .
	 **/
	public static boolean chkPositionValidation(int row1, int col1, int row2, int col2, int [][] arr){
		boolean result = false;
		
		List<String> positionList = new ArrayList<>(); //  
		
		//
		if(row1 != 0){
			for(int i = row1 -1 ; i >= 0; i--){
				if(arr[i][col1] != 0){
					positionList.add("(" + i + "," + col1 +")");
					break;
				}
			}
		}
		
		//
		if(row1 != arr.length -1){
			for(int i = row1 + 1  ; i <= arr.length -1; i++){
				if(arr[i][col1] != 0){
					positionList.add("(" + i + "," + col1 +")");
					break;
				}
			}
		}
		
		//
		boolean rightChk = true;
		if(rightChk){
			for(int i = col1 +1 ; i <= arr[row1].length -1; i++){
				if(arr[row1][i] != 0){
					positionList.add("(" + row1 + "," + i +")");
					//   rightChk false;
					rightChk = false;
					break;
				}
			}
		}
		if(rightChk){//     
			if(row1 != arr.length -1){//  ,
				for(int i = 0 ; i <= arr[row1+1].length -1; i++){
					if(arr[row1 + 1][i] != 0){
						int modifyRow = row1 +1;
						positionList.add("(" + modifyRow + "," + i +")");
						break;
					}
				}
			}
		}
		//
		for(int i = col1- 1 ; i >= 0; i--){// ,   row1,col1 row2,col2     rightChkX
			if(arr[row1][i] != 0){
				positionList.add("(" + row1 + "," + i +")");
				break;
			}
		}
		
		for(int i = 0 ; i <= positionList.size() - 1; i++){
			String position2 = "(" + row2 + "," + col2 +")";
			if(positionList.get(i).toString().equals(position2)){
				result = true;
			}
		}
		return result;
	}
	
	/**
	 *  ,   10  
	 * */
	public static boolean chkPairOrCalculationValidation(int element1, int element2){
		boolean result = false;
		if(element1 == element2){// element  ,
			result = true;
		}else{// element   ,
			if(element1 + element2 == 10){//  10,
				result = true;
			}
		}
		return result;
	}
	
	/**
	 *   Arr  (1  0     )
	 **/
	public static int[][] getRepaintArr(int [][] arr){
		int[][] result = arr;
		
		List<Integer> nonEmptyRowList = new ArrayList();
		
		List<Integer> arrElementList = new ArrayList();
		//loop
		for(int i = 0 ; i< arr.length; i++){
			boolean emptyChk = true;
			for(int j =0 ; j< arr[i].length; j++){
				if(arr[i][j] != 0){//0  break;
					emptyChk = true;
					break;
				}if(arr[i][j] == 0){//  
					emptyChk = false;
				}
			}
			if(emptyChk){//   0  emptyChk false
				nonEmptyRowList.add(i);//   .
			}
		}
		
		if(nonEmptyRowList.size() != arr.length){
			for(int i = 0 ; i< nonEmptyRowList.size(); i++){
				for(int j = 0 ;j< arr[i].length; j++){
					arrElementList.add(arr[nonEmptyRowList.get(i)][j]);
				}
			}
			
			//   0 
			for(int i = 0; i<arr.length; i++){
				for(int j = 0; j<arr[i].length;j++){
					result[i][j] = 0;
				}
			}
			
			//0   Element  0    .
			for(int i =0 ; i<arr.length; i++){
				for(int j =0 ; j<arr[i].length ; j++){
					if(arrElementList.size() > z){
						arr[i][j] = arrElementList.get(z);
						z++;
					}
				}
			}
			z = 0;// 
		}
		return result;
	}
	
	public static int[][] getRepopulateArr(int [][] arr){
		int[][] result = arr;
		Random random = new Random();
		for(int i =0 ; i<result.length; i++){
			for(int j =0 ; j<result[i].length ; j++){
				if(result[i][j] != 0){//0  
					int randomElement = random.nextInt(9) +1; //1~9   
					result[i][j] = randomElement; //int[i][j]  .
				}
			}
		}
		
		return result;
	}
	
	public static int removeRow(int [][] arr){
		List<Integer> nonEmptyRowList = new ArrayList();
		//loop
		for(int i = 0 ; i< arr.length; i++){
			boolean emptyChk = true;
			for(int j =0 ; j< arr[i].length; j++){
				if(arr[i][j] != 0){//0  break;
					emptyChk = true;
					break;
				}if(arr[i][j] == 0){//  
					emptyChk = false;
				}
			}
			if(emptyChk){//   0  emptyChk false
				nonEmptyRowList.add(i);//   .
			}
		}
		
		return arr.length-nonEmptyRowList.size();
	}
	
	//    
	//        (, 10   ) return false
	public static boolean getLowestScoreCompare(String path, int currentScore){
		boolean result = true;
		ScoreController scoreController = new ScoreController();
		
		List<Map<String,Object>> getScoreList = scoreController.getScoreList(path);
		
		if(getScoreList.size()<10){
			return result;
		}else{
			int getLowestScore = Integer.parseInt(getScoreList.get(getScoreList.size()-1).get("score").toString());
			if(getLowestScore > currentScore){
				result = false;
			}
			
		}
		return result;
	}
}
