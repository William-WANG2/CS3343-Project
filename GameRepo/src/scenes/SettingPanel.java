/* Setting Scene 
 * By hrz*/

package scenes;

import java.awt.Graphics;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

public class SettingPanel extends JPanel{
	private JTextArea difficulty;
	private JComboBox<String> dOptions;
	private JTextArea bgm;
	private ButtonGroup bOptions; 
	private JRadioButton bgmOn, bgmOff;
	private JTextArea volume;
	private JScrollBar vOptions;
	
	public SettingPanel() {
		difficulty = new JTextArea("DIFFICULTY");
		dOptions = new JComboBox<String>();
		dOptions.addItem("EASY");
		dOptions.addItem("NORMAL");
		dOptions.addItem("HARD");
		dOptions.addItem("CRAZY");
		
		bgm = new JTextArea("BACKGROUND MUSIC");
		bOptions = new ButtonGroup();
		bgmOn = new JRadioButton("ON");
		bgmOff= new JRadioButton("OFF");
		bOptions.add(bgmOn);
		bOptions.add(bgmOff);
		
		volume = new JTextArea("VOLUME");
		vOptions = new JScrollBar();
	
		this.add(difficulty);
		this.add(dOptions);
		this.add(bgm);
		this.add(bgmOn);
		this.add(bgmOff);
		this.add(volume);
		this.add(vOptions);
		
		difficulty.setBounds(10, 10, 60, 20);
		dOptions.setBounds(80, 10, 60, 20);
		bgm.setBounds(10, 40, 60, 20);
		bgmOn.setBounds(80, 40, 20, 20);
		bgmOff.setBounds(90, 40, 20, 20);
		volume.setBounds(10, 70, 60, 20);
		vOptions.setBounds(80, 70, 60, 20);
	}
	
	
}
