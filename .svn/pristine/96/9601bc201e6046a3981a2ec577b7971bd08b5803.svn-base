import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ScoreBoardPanel extends JFrame {
	
	public ScoreBoardPanel() {
		//테이블 머리행값 세팅
		String columnNames[] = {  "Date"
								, "Initials"
								, "Pairs"
								, "Score" };

		//테이블 데이터를 위한 Vector선언
		Vector<String> dataList = new Vector<String>();
		
		
		DefaultTableModel model = new DefaultTableModel(columnNames, 0){

			@Override
		    public boolean isCellEditable(int row, int column) {//테이블값 변경 불가하도록 설정
		        if (column >= 0) {
		            return false;
		        } else {
		            return true;
		        }
		    }
		};

		
		JTable table = new JTable(model);//테이블 생성
		
		//getDataList
		ScoreController scoreController = new ScoreController();
		List<Map<String,Object>> getScoreList = scoreController.getScoreList("HighScores");//저장된 파일에 Score를 가져옴
		
		// 가져온 데이터를 위에 선언한 Vector에 추가
		for(int i =0 ; i < getScoreList.size(); i++){
			dataList = new Vector<String>();
			dataList.add(getScoreList.get(i).get("date").toString());
			dataList.add(getScoreList.get(i).get("initials").toString());
			dataList.add(getScoreList.get(i).get("pairNum").toString());
			dataList.add(getScoreList.get(i).get("score").toString());
			
			model.addRow(dataList);
		}
		
		// 가운데 정렬
		DefaultTableCellRenderer cellAlignCenter = new DefaultTableCellRenderer();
		cellAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		/**
		 * 셀 크기, 셀정렬 설정     
		 **/
		table.getColumn("Date").setPreferredWidth(50);
		table.getColumn("Date").setCellRenderer(cellAlignCenter);
		table.getColumn("Initials").setPreferredWidth(10);
		table.getColumn("Initials").setCellRenderer(cellAlignCenter);
		table.getColumn("Pairs").setPreferredWidth(10);
		table.getColumn("Pairs").setCellRenderer(cellAlignCenter);
		table.getColumn("Score").setPreferredWidth(10);
		table.getColumn("Score").setCellRenderer(cellAlignCenter);

		
		JScrollPane jScrollPane = new JScrollPane(table);
		getContentPane().add(jScrollPane);
		setTitle("Score Board");
		setSize(400, 250);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
