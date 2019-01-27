import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @see http://stackoverflow.com/questions/7702697
 */
public class GridButtonPanel extends JFrame{
	
	//   
	int [][] getArray;
	
    private static final int row = 12; // 
    private static final int col = 9;  // 
	
	// font 
	Font font = new Font("font", Font.BOLD, 25);
	
	//defalut
	Color color_default = Color.RED;
	// 
	Color color_click = Color.GREEN;
	// 
	Color color_over = Color.WHITE;
	// 
	Color color_correct = Color.ORANGE;
	
	//pairs  
	JLabel labelPairs;
	
	//labelScore 
	JLabel labelScore; 
	
	// rowCnt
	int removeRowCnt = 0;
	int pairs = 0 ;
	int scores = 0;
	
	int arrayRemoveRowCnt = 0;
	
	
	
	JButton click1;//  
	int row1 = 0;
	int col1 = 0;
	
	JButton click2;//  
	int row2 = 0;
	int col2 = 0;
	
	int clickCount = 0 ; //  0,   1 ,  2  2   0 
	
	JPanel panel;
	
	ScoreBoardPanel scoreBoardPanel;
	
	public GridButtonPanel(ScoreBoardPanel scoreBoardPanel){
		getContentPane().setLayout(null);
		
		setTitle("Number Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setBounds(100, 100, 450, 700);
     	super.setResizable(false);
        
        /*  panel*/
        panel = new JPanel();
		panel.setBounds(0, 0, 450, 447);
		panel.add(createGridPanel());
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		/*  panel */
        
		/* */
		JPanel panelInfo = new JPanel();
		panelInfo.setBounds(0, 450, 450, 250);
		panelInfo.setLayout(null);
		getContentPane().add(panelInfo);
		
		
		JLabel lblScore = new JLabel("Scores");
		lblScore.setBounds(8, 7, 45, 15);
		panelInfo.add(lblScore);
		
		labelPairs = new JLabel("Pairs : " + pairs);
		labelPairs.setBounds(8, 59, 270, 70);
		labelPairs.setFont(font);
		panelInfo.add(labelPairs);
		
		labelScore = new JLabel("Score : "  + scores);
		labelScore.setBounds(8, 134, 270, 70);
		labelScore.setFont(font);
		panelInfo.add(labelScore);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.setBounds(64, 26, 97, 23);
		btnRestart.addActionListener(new ActionListener() {//add 이벤트
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Yes or No Dialog
				int opcion = JOptionPane.showConfirmDialog(null, "Restart?", "Warning", JOptionPane.YES_NO_OPTION);
				if (opcion == 0) {//YES이면,
					
					if(Utils.getLowestScoreCompare("HighScores", scores)){
						String initials = JOptionPane.showInputDialog("Input your initials"); //inputDialog창 호출
	
						if(initials !=null){// 그냥 창을 닫지 않았으면(위의 창을 닫으면 null이 나오므로),
							ScoreController scoreController = new ScoreController();
							List<Map<String,Object>> paramList = new ArrayList();
							Map<String,Object> map = new HashMap();
							
							long time = System.currentTimeMillis(); //현재시간을 받아온다.
							SimpleDateFormat dayTime = new SimpleDateFormat("hh:mm yyyy-MM-dd");//날짜형식 포맷 변경
							String date = dayTime.format(new Date(time)); //날짜형식 포맷 설정
							
							map.put("date", 	date);
							map.put("initials", initials);
							map.put("pairNum", 	pairs);
							map.put("score", 	scores);
							
							paramList.add(map); 
							
							scoreController.insertScoreList("HighScores", paramList);//score Insert
						}
					}
					
					dispose(); //게임창닫기(메모리 제거)
					scoreBoardPanel.dispose(); //점수창 닫기(메모리제거)
					new GridButtonPanel(new ScoreBoardPanel()).setVisible(true); //새롭게 호출
					
				}
				
			}
		});
		panelInfo.add(btnRestart);
		
		JButton btnRepopulate = new JButton("Repopulate");
		btnRepopulate.setBounds(168, 26, 120, 23);
		btnRepopulate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Yes or No Dialog
				int opcion = JOptionPane.showConfirmDialog(null, "Repopulate?", "Warning", JOptionPane.YES_NO_OPTION);
				if (opcion == 0) {//YES이면,
					repopulateButtonEvent(); //repopulateButtonEvent 이벤트 호출
				} 
			}
		});
		panelInfo.add(btnRepopulate);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(300, 26, 97, 23);
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Yes or No Dialog
				int opcion = JOptionPane.showConfirmDialog(null, "Exit?", "Warning", JOptionPane.YES_NO_OPTION);
				
				if (opcion == 0) { //YES이면,
					if(Utils.getLowestScoreCompare("HighScores", scores)){//등록된 점수와 비교 
						String initials = JOptionPane.showInputDialog("Input your initials"); //inputDialog창 호출
	
						if(initials !=null){// 그냥 창을 닫지 않았으면(위의 창을 닫으면 null이 나오므로),
							ScoreController scoreController = new ScoreController();
							List<Map<String,Object>> paramList = new ArrayList();
							Map<String,Object> map = new HashMap();
							
							long time = System.currentTimeMillis(); //현재시간을 받아온다.
							SimpleDateFormat dayTime = new SimpleDateFormat("hh:mm yyyy-MM-dd");//날짜형식 포맷 변경
							String date = dayTime.format(new Date(time)); //날짜형식 포맷 설정
							
							map.put("date", 	date);
							map.put("initials", initials);
							map.put("pairNum", 	pairs);
							map.put("score", 	scores);
							
							paramList.add(map);
							
							scoreController.insertScoreList("HighScores", paramList);
						}
					}
					System.exit(0);
				} 
			}
		});
		panelInfo.add(btnExit);
		
		this.scoreBoardPanel = scoreBoardPanel;
		
		super.repaint();
	}
    
	private void repopulateButtonEvent(){
		scores = scores - 20;//scores 20 
		labelScore.setText("Score : "  + scores);// text 
		
		getArray = Utils.getRepopulateArr(getArray);
		rePaintGridPanel();
		
		scoreBoardPanel.dispose();
		scoreBoardPanel = new ScoreBoardPanel();
	}
	
	//생성된 버튼 이벤트를 동적 리스트로 저장하기 위함
    private final List<JButton> list = new ArrayList<JButton>();

    //행,열값을 이용하여 위에 생성한 버튼 동적리스트에 해당된느 버튼을 가져온다.
    private JButton getGridButton(int r, int c) {
        int index = r * col + c;
        return list.get(index);
    }

    /** 
     * 버튼 생성
     * 행, 열, 요소값(배열의값)을 받아온다.
     **/
    private JButton createGridButton(final int row, final int col, int element) {
        JButton b = new JButton(String.valueOf(element));
        
        b.addMouseListener(new MouseAdapter()//마우스 이벤트 추가
        {
            public void mouseEntered(MouseEvent evt)//해당 컴포먼트에 마우스가 들어오면,
            {
            	JButton gb = GridButtonPanel.this.getGridButton(row, col); //위에 행 열 값으로 동적리스트에서 해당되는 버튼을 가져온다. 
            	if(gb.getBackground() == color_default){//    RED
            		gb.setBackground(color_over); //  WHITE .
            		gb.setOpaque(true);
            	}
            }
            public void mousePressed(MouseEvent evt)//마우스를 클릭하면
            {
            	JButton gb = GridButtonPanel.this.getGridButton(row, col); //위에 행 열 값으로 동적리스트에서 해당되는 버튼을 가져온다.
            	if(gb.getBackground() == color_default){ //    RED
            		gb.setBackground(color_click);//  GREEN .
            		gb.setOpaque(true);
            		clickCount ++;
            		if(clickCount == 1){//1번 클릭했을때
            			click1 = gb; //첫번째 클릭했을때의 버튼
            			row1 = row;
            			col1 = col;
            		}else if(clickCount ==2){//2번째 클릭했을때
            			click2 = gb; //두번째 클릭했을때의 버튼
            			row2 = row;
            			col2 = col;
            			
            			if(row1 > row2){
            				int tmpRow = row1; 
            				int tmpCol = col1;
            				
            				row1 = row2;
            				col1 = col2;
            				
            				row2 = tmpRow;
            				col2 = tmpCol;
            				
            			}
            			if( Utils.chkPositionValidation(row1, col1, row2, col2, getArray)
            			 && Utils.chkPairOrCalculationValidation(Integer.parseInt(click1.getText()), Integer.parseInt(click2.getText()))){//position validataion && pairOrCalcuValidation
	            			getArray[row1][col1] = 0; //  0  
	            			click1.setText(""); //  text ""
	            			click1.setBackground(color_correct); //  
	            			click1.setOpaque(true);
	            			
	            			getArray[row2][col2] = 0;
	            			click2.setText("");
	            			click2.setBackground(color_correct);
	            			click2.setOpaque(true);
	            			getArray = Utils.getRepaintArr(getArray);

	            			removeRowCnt = Utils.removeRow(getArray);
	            			rePaintGridPanel();
	            			
	            			//pairs 1
	            			pairs++;
		            		labelPairs.setText("Pairs : " + pairs);// text 
		            		
		            		//scores 10  &&   25 
		            		scores = scores + 10 + ((removeRowCnt-arrayRemoveRowCnt) * 25); //현재 score
		            		
		            		if(removeRowCnt>0){ //입력되는 줄칸이 1개이상이면, 
		            			arrayRemoveRowCnt = removeRowCnt;//위에 선언된 removeRowcnt에 현재 지워진칸 대입
		            		}
		            		
		            		labelScore.setText("Score : "  + scores);// text 
            			}else{
            				click1.setBackground(color_default); // RED 
            				click1.setOpaque(true);
            				
            				click2.setBackground(color_default); // RED
            				click2.setOpaque(true);
            			}
            			clickCount = 0;
            		}
            	}else if(gb.getBackground() == color_over){
            		gb.setBackground(color_click);
            		gb.setOpaque(true);
            		clickCount ++;
            		if(clickCount == 1){
            			click1 = gb; //첫번째 클릭했을때의 버튼
            			row1 = row; //좌표저장
            			col1 = col;
            		}else if(clickCount ==2){
            			click2 = gb; //두번째 클릭했을때의 버튼
            			row2 = row;//좌표저장
            			col2 = col;

            			if(row1 > row2){
            				int tmpRow = row1; 
            				int tmpCol = col1;
            				
            				row1 = row2;
            				col1 = col2;
            				
            				row2 = tmpRow;
            				col2 = tmpCol;
            			}
            			if( Utils.chkPositionValidation(row1, col1, row2, col2, getArray)
            			 && Utils.chkPairOrCalculationValidation(Integer.parseInt(click1.getText()), Integer.parseInt(click2.getText()))){//position validataion && pairOrCalcuValidation
	            			getArray[row1][col1] = 0; //  0  
	            			click1.setText(""); //  text ""
	            			click1.setBackground(color_correct); //  
	            			click1.setOpaque(true); //MAC에서의 적용을 위해 추가
	            			
	            			getArray[row2][col2] = 0;
	            			click2.setText("");
	            			click2.setBackground(color_correct);
	            			click2.setOpaque(true);//MAC에서의 적용을 위해 추가
	            			
	            			getArray = Utils.getRepaintArr(getArray);
	            			//   
	            			removeRowCnt = Utils.removeRow(getArray);
	            			rePaintGridPanel();
	            			
	            			//pairs 1
	            			pairs++;
		            		labelPairs.setText("Pairs : " + pairs);// text 
		            		//scores 10  &&   25 
		            		scores = scores + 10 + ((removeRowCnt-arrayRemoveRowCnt) * 25);
		            		
		            		if(removeRowCnt>0){
		            			arrayRemoveRowCnt = removeRowCnt;
		            		}
		            		
		            		labelScore.setText("Score : "  + scores);// text 
            			}else{
            				click1.setBackground(color_default);
            				click1.setOpaque(true);//MAC에서의 적용을 위해 추가
            				
            				click2.setBackground(color_default);
            				click2.setOpaque(true);//MAC에서의 적용을 위해 추가
            				
            			}
            			clickCount = 0;
            		}
            	}
            }
            public void mouseExited(MouseEvent evt) //해당 컴포먼트에서 마우스가 떠날때,
            {
            	JButton gb = GridButtonPanel.this.getGridButton(row, col);
            	if(gb.getBackground() == color_over){
            		gb.setBackground(color_default);
            		gb.setOpaque(true);
            	}
            }
        });
        return b;
    }
    
    
    /**
     *  판넬 생성 Panel(숫자판넬)
     **/
    private JPanel createGridPanel() {
        JPanel p = new JPanel(new GridLayout(row, col));//gridLayout 설정
        getArray = Utils.makeRandomArray(row,col);//랜덤배열을 받아온다.

        for(int i = 0 ; i< row; i++){ //배열돌면서 버튼 생성
        	for(int j = 0 ; j<col ; j++){
        		JButton gb = createGridButton(i, j, getArray[i][j]);
        		gb.setBackground(Color.RED);
        		gb.setOpaque(true);//MAC에서의 적용을 위해 추가
        		gb.setFont(font);
        		list.add(gb);
        		p.add(gb);
        	}
        }
        
        return p;
    }
    
    /**
     *  버튼 repaint
     **/
    private void rePaintGridPanel() {
        for(int i = 0 ; i< row; i++){
        	for(int j = 0 ; j<col ; j++){
        		JButton gb = GridButtonPanel.this.getGridButton(i, j);
        		if(getArray[i][j] == 0){
        			gb.setBackground(color_correct);
        			gb.setOpaque(true);
        			gb.setText("");
        		}else{
	        		gb.setBackground(color_default);
	        		gb.setOpaque(true);
	        		gb.setText(String.valueOf(getArray[i][j]));
        		}
        		gb.setFont(font);
        	}
        }
        super.repaint();
        
    }

    //paint (해당 컴퍼넌트를 다시 그린다.)
    public void paint(Graphics g){
    	super.paint(g);
    }
}