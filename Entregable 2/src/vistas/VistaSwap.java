package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import entregable1.Coin;
import entregable1.Saldo;
import entregable1.TipoMoneda;

public class VistaSwap extends JFrame {
	private JLabel label,
		targetLabel,
		targetCantidad;
	
	private HintTextField cantidad;

	private JComboBox listMonedas;

	private JPanel botones,
		selecMonedas,
		selecCantidad,
		target;
	private JButton confirmar, cancelar;
	
	private Coin targetCoin;

	public VistaSwap() {
		// atributos
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(10);;
		borderLayout.setVgap(5);
		
		this.setLayout(borderLayout);
		this.setSize(500, 500);
		
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		
		// label
		label = new JLabel();
		label.setText("- Swap");
		label.setHorizontalAlignment(JLabel.CENTER);
		
		this.add(label, BorderLayout.NORTH);
		
		// botones
		botones = new JPanel();
		botones.setBackground(new Color(0xDAD6D7));
		this.add(botones, BorderLayout.SOUTH);
		
		// confirmar
		confirmar = new JButton();
		confirmar.setText("Confirmar");
		botones.add(confirmar);
		// cancelar
		cancelar = new JButton();
		cancelar.setText("Cancelar");
		botones.add(cancelar);
		
		//configColorBoton Nueva
		this.confirmar.setBackground(new Color(0xAB886D));
		this.confirmar.setFont(new Font("Tahoma",Font.BOLD,14));		
		this.confirmar.setFocusPainted(false);
		this.cancelar.setBackground(new Color(0xAB886D));
		this.cancelar.setFont(new Font("Tahoma",Font.BOLD,14));		
		this.cancelar.setFocusPainted(false);
		
		// target
		target = new JPanel(new GridLayout(2,1));
		target.setBackground(new Color(0xDAD6D7));
		
		targetLabel = new JLabel("undefined");
		targetLabel.setHorizontalAlignment(JLabel.LEFT);
		
		target.add(targetLabel);
		
		targetCantidad = new JLabel(" 0.0");
		targetCantidad.setHorizontalAlignment(JLabel.LEFT);
		targetCantidad.setPreferredSize(new Dimension(150, 25));
		
		target.add(targetCantidad);
		
		this.add(target, BorderLayout.EAST);
		
		
		// cantidad
		selecCantidad = new JPanel(new GridLayout(1, 1));
		
		cantidad = new HintTextField(" Cantidad");
		cantidad.setPreferredSize(new Dimension(200, 10));
		this.cantidad.setBackground(new Color(0xD6C0B3));
		
		selecCantidad.add(cantidad);
	
		this.add(selecCantidad, BorderLayout.CENTER);
		
		
		// monedas
		selecMonedas = new JPanel(new BorderLayout());
		selecMonedas.setBackground(new Color(0xDAD6D7));
		
		listMonedas = new JComboBox();
		listMonedas.setPreferredSize(new Dimension(150, 20));
		
		JPanel offsetPanel = new JPanel();
		offsetPanel.setPreferredSize(new Dimension(10, 10));
		offsetPanel.setBackground(new Color(0xDAD6D7));
		
		this.selecMonedas.add(offsetPanel, BorderLayout.NORTH);		
		this.selecMonedas.add(new JLabel("   Intercambiar"), BorderLayout.NORTH); 
		this.selecMonedas.add(listMonedas, BorderLayout.CENTER);
		this.selecMonedas.add(offsetPanel, BorderLayout.WEST);
		
		this.add(selecMonedas, BorderLayout.WEST);
		this.getContentPane().setBackground(new Color(0xDAD6D7));
		this.pack();
		this.setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 15, 15));		
		
		
		this.setVisible(false);
	}
	
	public void asignarSaldosDisponibles(List<Saldo> listSaldos) {
		// Remover todos los ítems
		listMonedas.removeAllItems();
		
		// Lista de las opciones
		List<String> listOpt = new LinkedList<String>();
		
		// Agregar las opciones
		listSaldos.forEach(s -> {
			String text = "%s : %.5f";
			
			if (s.getTipo().equals(TipoMoneda.CRIPTOMONEDA) && !(s.getSigla().equalsIgnoreCase(targetCoin.getSigla())))
				listOpt.add(String.format(text, s.getSigla().toUpperCase(), s.getCantMonedas()));
		});
		
		// Asignar un nuevo modelo
		listMonedas.setModel(new DefaultComboBoxModel(listOpt.toArray()));
		
		// 
		this.revalidate();
		this.repaint();	
	}
	
	public void actualizarConversion(double nuevaConversion) {
		this.targetCantidad.setText(" " + nuevaConversion);
	}
	
	public JButton getConfirmar() {
		return confirmar;
	}

	public void setConfirmar(JButton confirmar) {
		this.confirmar = confirmar;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public void setCancelar(JButton cancelar) {
		this.cancelar = cancelar;
	}
	
	public Coin getTargetCoin() {
		return targetCoin;
	}
	
	public void setTargetCoin(Coin coin) {
		this.targetCoin = coin;
		
		// Verificación
		if (targetCoin == null) {
			System.out.println("VistaSwap::Coin es igual a null");
		}
		
		// Asignar Texto
		targetLabel.setText(" Por " + targetCoin.getNombre() + " " + targetCoin.getSigla().toUpperCase() + " ");
	}
	
	public JComboBox getListMonedas() {
		return listMonedas;
	}
	
	public void setTargetLabel(JLabel targetLabel) {
		this.targetLabel = targetLabel;
	}
	
	public JTextField getCantidad() {
		return cantidad;
	}
	
	class HintTextField extends JTextField implements FocusListener {

		  private final String hint;
		  private boolean showingHint;
		  
		  private Color hintColor = Color.gray;
		  private Color normalColor = Color.black;
		  
		  public HintTextField(final String hint) {
		    super(hint);
		    this.hint = hint;
		    this.showingHint = true;
		    this.setForeground(hintColor);
		    super.addFocusListener(this);
		  }

		  @Override
		  public void focusGained(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText("");
		      showingHint = false;
		    }
		    
		    this.setForeground(normalColor);
		  }
		  @Override
		  public void focusLost(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText(hint);
		      this.setForeground(hintColor);
		      showingHint = true;
		    }
		  }

		  @Override
		  public String getText() {
		    return showingHint ? "" : super.getText();
		  }
		}

	
}
