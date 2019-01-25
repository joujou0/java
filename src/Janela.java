/*
 * @Author: Vladimir Camelo
 * @Data Criação: 05/09/2018
 * @Horário Criação: 14:05
 * @Curso: Ciência da Computação e Sistema de Informação
 * @Empresa: Universidade Paulista (Unip)
 * @Campi: Norte
 */
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Janela extends JFrame implements ActionListener{
	private Connection con = null;
	
    private JLabel labelId, labelNome, labelEndereco, labelFone, labelUf, labelIdade, labelRg,
        labelSexo, labelEmail;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtId;
    private JTextField txtFone;
    private JTextField txtIdade;
    private JTextField txtRg;
    private JTextField txtEmail;
    private JButton btnCad, btnVis, btnAlt, btnExc, btnSair;
    private JComboBox comboUf;
    private JRadioButton rdbMasc, rdbFem, rdbOc;
    private ButtonGroup btgGrupo;
    private JPanel painel;
    private TitledBorder borda;
    public JTextArea txtArea;
    private JScrollPane jscrol;
    private JScrollBar jscrobar = null;
    private String uf[] = {
        "AC",
        "AL",
        "AM",
        "AP",
        "BA",
        "CE",
        "DF",
        "ES",
        "GO",
        "MA",
        "MT",
        "MS",
        "MG",
        "PA",
        "PB",
        "PR",
        "PE",
        "PI",
        "RJ",
        "RN",
        "RO",
        "RS",
        "RR",
        "SC",
        "SE",
        "SP",
        "TO"
    };

    public Janela()
    {
        setTitle( "Cliente" );
        setSize( 800, 500 );
        setLayout( null );
        setResizable(false);

        painel = new JPanel();
        painel.setLayout( new GridLayout( 1, 3 ) );

        labelSexo = new JLabel( "Sexo", JLabel.CENTER );
        labelSexo.setForeground( Color.white );
        rdbMasc = new JRadioButton( "Masculino" );
        rdbFem = new JRadioButton( "Feminino" );
        rdbOc = new JRadioButton( "" );

        btgGrupo = new ButtonGroup();
        btgGrupo.add( rdbMasc );
        btgGrupo.add( rdbFem );
        btgGrupo.add( rdbOc );

        rdbMasc.setBackground( Color.darkGray );
        rdbMasc.setForeground( Color.white );
        rdbFem.setBackground( Color.darkGray );
        rdbFem.setForeground( Color.white );

        rdbOc.setVisible( false );

        borda = new TitledBorder( "" );
        borda.setTitleColor( Color.black );

        getContentPane().setBackground( Color.lightGray );

        painel.add( labelSexo );
        painel.add( rdbMasc );
        painel.add( rdbFem );

        painel.setBorder( borda );

        painel.setBounds( 10, 105, 760, 50 );
        painel.setBackground( Color.darkGray );

        labelId = new JLabel( "ID" );
        labelNome = new JLabel( "Nome" );
        labelFone = new JLabel( "Telefone" );
        labelEndereco = new JLabel( "Endereço" );
        labelRg = new JLabel( "RG" );
        labelUf = new JLabel( "UF" );
        labelIdade = new JLabel( "Idade" );

        labelId.setBounds( 10, 10, 50, 25 );
        labelNome.setBounds( 150, 10, 50, 25 );
        labelIdade.setBounds( 580, 10, 50, 25 );
        labelEndereco.setBounds( 10, 40, 80, 25 );
        labelFone.setBounds( 580, 40, 50, 25 );
        labelRg.setBounds( 10, 70, 50, 25 );
        labelUf.setBounds( 580, 70, 50, 25 );

        add( painel );

        add(labelId);
        add( labelNome );
        add( labelFone );
        add( labelEndereco );
        add( labelRg );
        add( labelUf );
        add( labelIdade );

        txtId = new JTextField( "" );
        txtNome = new JTextField( "" );
        txtEndereco = new JTextField( "" );
        txtFone = new JTextField( "" );
        txtIdade = new JTextField( "" );
        txtRg = new JTextField( "" );

        try {
            final javax.swing.text.MaskFormatter campoRg = new javax.swing.text.MaskFormatter( "##.###.###-#" );
            txtRg = new javax.swing.JFormattedTextField( campoRg );
            final javax.swing.text.MaskFormatter campoFone = new javax.swing.text.MaskFormatter( "####-####" );
            txtFone = new javax.swing.JFormattedTextField( campoFone );
        } catch( final Exception exc ) {

        }

        txtId.setBounds( 70, 15, 70, 20 );
        txtNome.setBounds( 200, 15, 340, 20 );
        txtIdade.setBounds( 650, 15, 120, 20 );
        txtEndereco.setBounds( 70, 45, 470, 20 );
        txtFone.setBounds( 650, 45, 120, 20 );
        txtRg.setBounds( 70, 75, 470, 20 );

        add( txtId );
        add( txtNome );
        add( txtEndereco );
        add( txtFone );
        add( txtIdade );
        add( txtRg );

        labelEmail = new JLabel( "E-mail" );
        txtEmail = new JTextField( "" );

        labelEmail.setBounds( 10, 165, 50, 25 );
        txtEmail.setBounds( 65, 165, 705, 25 );

        add( labelEmail );
        add( txtEmail );

        this.comboUf = new JComboBox( uf );
        comboUf.setBounds( 650, 70, 120, 25 );

        add( comboUf );

        txtArea = new JTextArea();

        jscrol = new JScrollPane( txtArea );
        jscrobar = new JScrollBar();
        txtArea.setLineWrap( true );
        jscrol.setVerticalScrollBar( jscrobar );

        jscrol.setBounds( 10, 200, 760, 180 );

        add( jscrol );

        btnCad = new JButton( "Cadastrar" );
        btnVis = new JButton( "Visualizar" );
        btnAlt = new JButton( "Alterar" );
        btnExc = new JButton( "Excluir" );
        btnSair = new JButton( "Sair" );

        final ImageIcon icone = new ImageIcon( "imagens\\cadastrar.png" );
        btnCad.setIcon( icone );
        btnCad.setToolTipText( "Clique para cadastrar" );

        btnAlt.setIcon( new ImageIcon( "imagens\\alterar.gif" ) );
        btnAlt.setToolTipText( "Clique para Alterar" );

        btnExc.setIcon( new ImageIcon( "imagens\\bt_excluir.gif" ) );
        btnExc.setToolTipText( "Clique para excluir" );

        btnVis.setIcon( new ImageIcon( "imagens\\visualizar.gif" ) );
        btnVis.setToolTipText( "Clique para visualizar" );

        btnSair.setToolTipText( "Clique em sair para finalizar" );
        btnSair.setIcon( new ImageIcon( "imagens\\sair.jpg" ) );

        btnCad.setBounds( 10, 390, 150, 60 );
        btnAlt.setBounds( 185, 390, 130, 60 );
        btnExc.setBounds( 340, 390, 130, 60 );
        btnVis.setBounds( 495, 390, 130, 60 );
        btnVis.addActionListener(this);
        btnSair.setBounds( 650, 390, 120, 60 );
        add( btnCad );
        add( btnVis );
        add( btnAlt );
        add( btnExc );
        add( btnSair );

        this.setLocationRelativeTo( null );
        setVisible( true );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        
        conexaoBD();
    }

    public static void main(final String[] args )
    {
        new Janela();
    }
    
    public void conexaoBD(){
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aulabd", "root", "root");
    	} catch (ClassNotFoundException e){
    		JOptionPane.showMessageDialog(null, "Driver não encontrado!","DRIVER",JOptionPane.INFORMATION_MESSAGE);
    	} catch (SQLException e){
    		e.printStackTrace();
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnVis)){
			try {
				Statement st = con.createStatement();
				String consulta = "select * from cliente where idcliente =" + Integer.parseInt(txtId.getText());
				ResultSet rs = st.executeQuery(consulta);
				if(rs.next()){
					txtNome.setText(rs.getString("nome"));
					txtIdade.setText(rs.getString("idade"));
					txtEndereco.setText(rs.getString("Endereco"));
					txtFone.setText(rs.getString("telefone"));
					txtRg.setText(rs.getString("rg"));
					comboUf.setToolTipText(rs.getString("uf"));
					txtEmail.setText(rs.getString("email"));
					txtArea.setText(rs.getString("observacao"));
				}
				//JOptionPane.showMessageDialog(null, "Visualizar: " + rs );
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		
	}
}
