package GirisEkranı;
	
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.prefs.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import SQLConnection.MicrosoftSQLConnection;
import Sube.SubeMainEkran;
import UniversalVariables.UniversalVariables;
	
public class GirisEkranı  {
	private final String Remembered_UserName = "Username";
	private final String Remembered_Password = "Password";
	Preferences pref = Preferences.userRoot().node(this.getClass().getName());
	JCheckBox RememberPasswordCheckBox; 
	JTextField LoginTextField = new JTextField(15);
	JPasswordField PasswordTextField = new JPasswordField(15);
	
	String userName = "";
	String password = "";
	JFrame GirisFrame;
	
	MicrosoftSQLConnection SQLConn = new MicrosoftSQLConnection();
	Connection conn = SQLConn.connect();
	
	public GirisEkranı() {
		
		Initializer();
		loadSavedCredentials();
		
	}
	
		

		public void Initializer(){
			//Universal Variables That Dont Change.
			
			UniversalVariables universalVariables = new UniversalVariables();
			String BuildVersion = universalVariables.getBuildVersion();
		
			
			//Frames
			
			GirisFrame = new JFrame("TRBank Login");
			GirisFrame.setSize(410,225);
			

			//Labels
			
			JLabel LoginLabel = new JLabel("Login:");
			LoginLabel.setBounds(80,25,50,15);
			JLabel PasswordLabel = new JLabel("Password:");
			PasswordLabel.setBounds(80,60,70,15);
			JLabel ReleaseInformationLabel = new JLabel("Release Information: " + BuildVersion);
			ReleaseInformationLabel.setBounds(235,165,150,15);
			
			//CheckBoxes
			
			RememberPasswordCheckBox = new JCheckBox("Remember Password");
			RememberPasswordCheckBox.setBounds(165,90,150,20);
			
			//Buttons
			
			JButton OkButton = new JButton("OK");
			OkButton.setBounds(140,120,70,25);
			JButton CancelButton = new JButton("Cancel");
			CancelButton.setBounds(240,120,80,25);
		
			//TextFields
			
			
			LoginTextField.setBounds(170,27,150,20);

			PasswordTextField.setBounds(170,62,150,20);
			
			//Icon 
			
			
			ImageIcon TRBankIcon = new ImageIcon("C:/Users/Rüstem/eclipse-workspace/TRBank/src/TRBankIcon.png");
			// C:\Users\Rüstem\eclipse-workspace\TRBank\src
			Image scaledImage = TRBankIcon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);
		    JLabel ImageLabel = new JLabel(scaledIcon);
		    ImageLabel.setBounds(5, 25, 80, 80);
		    
		    //Action Listener
		    
		    OkButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SaveCredentials();
					userName = LoginTextField.getText().trim();
					char[] PasswordChars = PasswordTextField.getPassword();
				    password = new String(PasswordChars);
			        EnterTheApp();
			        
			    
			      
				}
			});
		    
		    CancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			
			//Add
			GirisFrame.add(ImageLabel);
			GirisFrame.add(LoginLabel);
			GirisFrame.add(PasswordLabel);
			GirisFrame.add(ReleaseInformationLabel);
			GirisFrame.add(LoginTextField);
			GirisFrame.add(PasswordTextField);
			GirisFrame.add(RememberPasswordCheckBox);
			GirisFrame.add(OkButton);
			GirisFrame.add(CancelButton);
			GirisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			GirisFrame.setLocationRelativeTo(null);
			GirisFrame.setLayout(null);
			GirisFrame.setVisible(true);

		}
		private void SaveCredentials() {
			if(RememberPasswordCheckBox.isSelected()) {
				pref.put(Remembered_UserName,LoginTextField.getText() );
				pref.put(Remembered_Password,new String (PasswordTextField.getPassword()));
			}else {
				pref.remove(Remembered_UserName);
				pref.remove(Remembered_Password);
			}
		}
		private void loadSavedCredentials() {
			String SavedUsername = pref.get(Remembered_UserName,"" );
			String SavedPassword = pref.get(Remembered_Password,"" );
			
			LoginTextField.setText(SavedUsername);
			PasswordTextField.setText(SavedPassword);
			RememberPasswordCheckBox.setSelected(!SavedUsername.isEmpty() && !SavedPassword.isEmpty());
			
		}
		

		private void EnterTheApp() {
			String Query = "SELECT [UserName],[PassKey] FROM [TRBANK].[dbo].[USERS] Where UserName = ? AND PassKey = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(Query);
				ps.setString(1, userName);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					GirisFrame.dispose();
					SubeMainEkran sme = new SubeMainEkran();
					sme.ShowFrame();
				}else {
					JOptionPane.showMessageDialog(null,"Kullanıcı Adı Veya Şifre Yanlış", "Hata",JOptionPane.WARNING_MESSAGE );
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Bağlantı Sorunu, Lütfen bankaya danışınız", "Hata",JOptionPane.ERROR_MESSAGE );
			}
		}
		
		public static void main(String[] args) {
			GirisEkranı a = new GirisEkranı();
		}
		
		
		
	
	}
