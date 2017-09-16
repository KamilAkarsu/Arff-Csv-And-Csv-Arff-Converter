import java.awt.EventQueue;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;

import org.omg.CORBA.Environment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;


public class Frame1 {

	private JFrame frmVersion;
	private JTextPane textPane;
	private JTextPane textPane_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frmVersion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVersion = new JFrame();
		frmVersion.getContentPane().setBackground(new Color(250, 240, 230));
		frmVersion.setTitle("Arff-Csv & Csv-Arff    Version_1.0");
		frmVersion.setBounds(100, 100, 388, 283);
		frmVersion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVersion.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(23, 24, 326, 189);
		frmVersion.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		tabbedPane.addTab("Arff to Csv Converter", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblArffCsv = new JLabel("Arff --> Csv");
		lblArffCsv.setBounds(10, 40, 94, 14);
		panel.add(lblArffCsv);
		
		JButton btnLoadButton1 = new JButton("Load File");
		btnLoadButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				textPane.setText("");
				textPane_1.setText("");
				try {
					ArffLoader loader1 = new ArffLoader();		
					JFileChooser cho1 = new JFileChooser();
					cho1.setCurrentDirectory(new java.io.File("C://"));
					cho1.setDialogTitle("Arff-Csv");
					
					FileFilter filter = new FileNameExtensionFilter("Arff File (.arff)","arff");
					cho1.addChoosableFileFilter(filter);
					
					if(cho1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
					{
						
						loader1.setSource(new File (cho1.getSelectedFile().toString()));
						Instances data = loader1.getDataSet();
						
						
						//REMOVE ADDITOIN//
						String file_name = cho1.getSelectedFile().getName().toString();
						int length_file_name = file_name.length();
						String file_name_without_addition = file_name.substring(0,length_file_name-5);
						//if you want to remove .arff you need to delete 5 char 
						
						
						CSVSaver saver = new CSVSaver();
						saver.setInstances(data);
						saver.setFile(new File(cho1.getCurrentDirectory()+"/"+file_name_without_addition+".csv"));
						saver.writeBatch();
						JOptionPane.showMessageDialog(null,"Convert Successfull,Saving Your File !");
						textPane.setText("Directory : " + cho1.getCurrentDirectory() + "\nFile Name : " +  file_name_without_addition + ".csv");
					}
					else
						JOptionPane.showMessageDialog(null,"File Not Selected ");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Please make sure your extension is .arff");
				}
				
				
			}
		});
		btnLoadButton1.setBounds(121, 36, 89, 23);
		panel.add(btnLoadButton1);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(new Color(0, 255, 255));
		textPane.setBounds(10, 85, 301, 65);
		panel.add(textPane);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 255, 0));
		tabbedPane.addTab("Csv to Arff Converter", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblCsvArff = new JLabel("Csv --> Arff");
		lblCsvArff.setBounds(10, 40, 94, 14);
		panel_1.add(lblCsvArff);
		
		JButton btnLoadButton2 = new JButton("Load File");
		btnLoadButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText("");
				textPane_1.setText("");
				try {
					CSVLoader loader2 = new CSVLoader();		
					JFileChooser cho2 = new JFileChooser();
					cho2.setCurrentDirectory(new java.io.File("C://"));
					cho2.setDialogTitle("Csv-Arff");
					
					FileFilter filter = new FileNameExtensionFilter("Csv File (.csv)","csv");
					cho2.addChoosableFileFilter(filter);
					
					if(cho2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
					{
						
						loader2.setSource(new File (cho2.getSelectedFile().toString()));
						Instances data = loader2.getDataSet();
						
						
						//ADDITION REMOVE//
						String file_name = cho2.getSelectedFile().getName().toString();
						int length_file_name = file_name.length();
						String file_name_without_addition = file_name.substring(0,length_file_name-4);
						//if you want to remove .arff you need to delete 4 char 
						
						ArffSaver saver2 = new ArffSaver();
						saver2.setInstances(data);
						saver2.setFile(new File(cho2.getCurrentDirectory()+"/"+file_name_without_addition+".arff"));
						saver2.writeBatch();
						JOptionPane.showMessageDialog(null,"Convert Successfull,Saving Your File !");
						textPane_1.setText("Directory : " + cho2.getCurrentDirectory() + "\nFile Name : " +  file_name_without_addition + ".arff");
					}
					else
						JOptionPane.showMessageDialog(null,"File Not Selected");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Please Make Sure our extension is .csv");
				}
			}
		});
		btnLoadButton2.setBounds(121, 36, 89, 23);
		panel_1.add(btnLoadButton2);
		
		textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBackground(new Color(0, 255, 0));
		textPane_1.setBounds(10, 85, 301, 65);
		panel_1.add(textPane_1);
		
	}
}
