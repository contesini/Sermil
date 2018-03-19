package br.com.sermil.viewer;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import br.com.sermil.miner.Documento;
import br.com.sermil.miner.Mineradora;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TelaArquivo extends JFrame {
	private static final long serialVersionUID = 3977298828280279606L;
	private Documento documento = new Documento();
	
	
	public TelaArquivo(Mineradora mineradora) {
		setTitle("Selecione o Arquivo");
		setVisible(true);
		setSize(487, 249);

		try {
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				if (JOptionPane.showConfirmDialog(null,"Deseja sair")==JOptionPane.OK_OPTION){
					mineradora.logout(); System.exit(0);	}
		}
		});
		
		setResizable(false);

		JLabel lblNewLabel = new JLabel("Selecione o Arquivo que contem as RA's");
		lblNewLabel.setBounds(94, 23, 287, 17);
		getContentPane().add(lblNewLabel);
		

		JButton btnselecionar = new JButton("Verificar RA's");
		btnselecionar.setBounds(165, 52, 143, 23);
		getContentPane().add(btnselecionar);
		
		btnselecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				documento.limparArray();
				documento.chooser();
				JOptionPane.showMessageDialog(null, "Aguarde, no final do processor voce recebera uma mensagem");

				for (int i = 0; i < Documento.getLinhaRALength(); i++) {					
					try {
						mineradora.minerar(Documento.getLinhaRA(i));
						mineradora.anotacoes();
						mineradora.info();
						mineradora.getDataAlistamento();

						try {
							mineradora.salvarCsv();

						} catch (IOException e1) {
							e1.printStackTrace();
							System.out.println("erro ao salvar");
						} // se achou o RA para por aqui

					} catch (Exception e2) {

						try {
							mineradora.erroRA(); // caso contrario salva no csv
												 // que nao foi encontrado
						} catch (Exception e3) {
							e3.printStackTrace();
						}
					}
				}
				
				documento.limparArray();
				JOptionPane.showMessageDialog(null, "Fichas Verificadas conferir arquivo FAMs.csv");
				

			}
		});


	}
}
