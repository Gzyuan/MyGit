package cn.dataStructure.forHuffmanTree;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyGUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JButton openBotton = null;
	private JButton compressBotton = null;
	private JButton decompressBotton = null; 
	private JTextArea jTextArea =null;
	private JScrollPane jScrollPane =null;
	private JFileChooser openFileChooser = null;
	private JFileChooser compressFileChooser = null;
	private JFileChooser decompressFileChooser = null;
	public MyGUI() {
		// TODO Auto-generated constructor stub
		jPanel = new JPanel();
		compressBotton = new JButton("压缩");
		compressBotton.addActionListener(this);
		compressBotton.setActionCommand("a");
		decompressBotton = new JButton("解压");
		decompressBotton.addActionListener(this);
		decompressBotton.setActionCommand("b");
		openBotton = new JButton("打开");
		openBotton.addActionListener(this);
		openBotton.setActionCommand("c");
		jTextArea = new JTextArea();
		jScrollPane = new JScrollPane(jTextArea);
		compressFileChooser = new JFileChooser();
		decompressFileChooser = new JFileChooser();
		openFileChooser = new JFileChooser();
		
		compressFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		String openType[] = {"txt"};
		compressFileChooser.setFileFilter(new FileNameExtensionFilter("txt", openType));
		
		openFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		openFileChooser.setFileFilter(new FileNameExtensionFilter("txt", openType));
		
		decompressFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		String saveType[] = {"abc"};
		decompressFileChooser.setFileFilter(new FileNameExtensionFilter("abc", saveType));
		
		jPanel.add(openBotton);
		jPanel.add(compressBotton);
		jPanel.add(decompressBotton);
		
		
		this.setLayout(new BorderLayout());
		this.add(jPanel,"North");
		this.add(jScrollPane, "Center");
		
		this.setTitle("Huffman树解压缩");
		this.setBounds(500, 300, 500, 500);
		this.setVisible(true);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String s = arg0.getActionCommand();
		if(s.equals("a")){
			compressFileChooser.showOpenDialog(this);
			File path = compressFileChooser.getSelectedFile();
			if(path!=null){
				jTextArea.append("正在压缩文件:"+path.getAbsolutePath()+"\n");
				BaseUtil baseUtil = new BaseUtil();
				jTextArea.append(baseUtil.compressFile(path)+"\n");
			}
			compressFileChooser.setSelectedFile(null);
		}
		if(s.equals("b")){
			decompressFileChooser.showOpenDialog(this);
			File path = decompressFileChooser.getSelectedFile();
			if(path!=null){
				jTextArea.append("正在解压文件:"+path.getAbsolutePath()+"\n");
				BaseUtil baseUtil = new BaseUtil();
				jTextArea.append(baseUtil.decompressFile(path)+"\n");
			}
			decompressFileChooser.setSelectedFile(null);
		}
		if(s.equals("c")){
			openFileChooser.showOpenDialog(this);
			File path = openFileChooser.getSelectedFile();
			if(path!=null){
				jTextArea.append("正在打开文件:"+path.getAbsolutePath()+"\n");
				BaseUtil baseUtil = new BaseUtil();
				jTextArea.append(baseUtil.openFile(path)+"\n");
			}
			openFileChooser.setSelectedFile(null);
		}
	}
	
}
