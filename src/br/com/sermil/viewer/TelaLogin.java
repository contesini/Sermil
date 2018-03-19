package br.com.sermil.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.sermil.miner.Militar;
import br.com.sermil.miner.Mineradora;

public class TelaLogin extends JFrame {
	private static final long serialVersionUID = -5318662051899841363L;
	private JTextField tfCpf;
	private JPasswordField tfsenha;
	Mineradora mineradora = new Mineradora();
	Militar militar = new Militar(null, null, null);
	
	public TelaLogin() {
		try {
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				if (JOptionPane.showConfirmDialog(null,"Deseja sair")==JOptionPane.OK_OPTION){
					mineradora.logout(); System.exit(0);	}
		}
		});
		
		setResizable(false);

		setSize(290, 260);
		getContentPane().setLayout(null);

		JLabel lblCpf = new JLabel("SENHA");
		lblCpf.setBounds(80, 89, 120, 15);
		getContentPane().add(lblCpf);

		JLabel lblsenha = new JLabel("CPF");
		lblsenha.setBounds(80, 36, 120, 15);
		getContentPane().add(lblsenha);

		tfCpf = new JTextField();
		tfCpf.setBounds(80, 59, 120, 19);
		getContentPane().add(tfCpf);
		tfCpf.setColumns(10);

		tfsenha = new JPasswordField();
		tfsenha.setBounds(80, 112, 120, 19);
		getContentPane().add(tfsenha);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(80, 153, 120, 23);
		getContentPane().add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				
				String senha = new String(tfsenha.getPassword());

				militar = new Militar(tfCpf.getText(), senha, null);

				if (mineradora.login(militar)) {
					TelaArquivo telaarquivo = new TelaArquivo(mineradora);
					telaarquivo.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Dados incorretos", "Login Erro", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
				

				
			}
		});

		setVisible(true);
	}
	
	public static void main(String[] args)  {
		new TelaLogin();
		
		
	}

}
