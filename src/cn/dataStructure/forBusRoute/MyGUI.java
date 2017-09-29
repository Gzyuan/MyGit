package cn.dataStructure.forBusRoute;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyGUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JLabel jLabelImg =null;
	private JButton button = null;
	private JLabel jLabelStart = null;
	private JLabel jLabelEnd = null;
	private JTextField jTextFieldStart = null;
	private JTextField jTextFieldEnd = null;
	private JTextArea jTextArea = null;
	private JScrollPane jScrollPane =null;
	private BaseUtil baseUtil = null;
	public MyGUI(){
		baseUtil = new BaseUtil();//查询的对象
		jPanel = new JPanel();
		jLabelImg = new JLabel();
		ImageIcon img = new ImageIcon("img/BUS.jpg");
		jLabelImg.setIcon(img);
		button = new JButton("查询");
		button.addActionListener(this);
		jTextFieldStart = new JTextField(10);
		jTextFieldEnd = new JTextField(10);
		jLabelStart = new JLabel("起始：");
		jLabelEnd = new JLabel("终点：");
		jTextArea = new JTextArea(13,10);
		jTextArea.setEditable(false);
		jTextArea.setFont(new Font("微软雅黑",0,20));
		jScrollPane = new JScrollPane(jTextArea);
		
		
		jPanel.add(jLabelStart);
		jPanel.add(jTextFieldStart);
		jPanel.add(jLabelEnd);
		jPanel.add(jTextFieldEnd);
		jPanel.add(button);
		
		this.setTitle("公交车路线查询");
		this.setLayout(new BorderLayout());
		this.add(jLabelImg,"North");
		this.add(jPanel,"Center");
		this.add(jScrollPane,"South");
		this.setIconImage(getToolkit().getImage("img/logo.jpg"));
		
		this.setSize(1700, 850);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String start = jTextFieldStart.getText();
		String end = jTextFieldEnd.getText();
		jTextArea.setText("");
		if(start.equals(end)){
			jTextArea.append("不能输入相同参数，请输入大写的A到J\n");
		}else{
			if(isLegal(start)&&isLegal(end)){
				jTextArea.append("----------------分割符-------------------\n");
				jTextArea.append(start+"站到"+end+"站的最短路径:\n\n");
				String text1 = baseUtil.getShortestPath(start, end);
				jTextArea.append(text1);
				jTextArea.append("----------------分割符-------------------\n");
				jTextArea.append(start+"站到"+end+"站的所有可乘路线:\n\n");
				String text2 = baseUtil.getPath(start, end);
				jTextArea.append(text2);
				jTextArea.append("----------------分割符-------------------\n");
				
			}else{
				jTextArea.append("输入有误，请输入大写的A到J\n");
			}	
		}	
	}
	//检验输入信息合不合法
	private boolean isLegal(String value){
		if(value.length()!=1){
			return false;
		}
		char temp = value.charAt(0);
		if(temp<65||temp>74){
			return false;
		}
		return true;
	}
	
	
}
