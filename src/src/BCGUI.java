package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BCGUI {
	// main window
	private JFrame frm;
	
	//	backend for converting values from and to strings
	private BCBackend bcb;
	
	//	enum for field to convert from
	public static enum ActiveField{DEC,BIN,OCT,HEX,CHA,COL,FLT,NON}
	
	//	default to none
	private ActiveField af = ActiveField.NON;
	
	//	text fields, color field
	private JTextField decFld;
	private JTextField binFld;
	private JTextField octFld;
	private JTextField hexFld;
	private JTextField chaFld;
	private JLabel colFld;
	private JTextField fltFld;
	
	//	color choosing, convert, clear buttons
	private JButton colBtn;
	private JButton cvtBtn;
	private JButton clrBtn;
	
	
	public BCGUI()
	{
		bcb = new BCBackend();	//	initialize backend
		frm = new JFrame("Number Converter");	//	initialize main window
		
		JPanel pnl = new JPanel();	//	initialize main panel
		pnl.setLayout(new GridBagLayout());	//	set layout to gridbag
		
		GridBagConstraints c = new GridBagConstraints();	//	changing constraints for components
		
		//JLabel lbl;
		//JTextField fld;
		//JButton btn;
		
		addLabels(pnl);		//	add passive labels to pnl
		
		c.weighty = 0.0;	//	resetting vertical weight so only determined by label row
		
		//	ADD TEXT FIELDS, give focuslisteners for changing activeField
		
		//	Decimal Field
		decFld = new JTextField();
		decFld.addFocusListener(new FocusListener()
				{

					@Override
					public void focusGained(FocusEvent arg0) {
						// TODO Auto-generated method stub
						af = ActiveField.DEC;	//	change active field on focus gain
					}

					@Override
					public void focusLost(FocusEvent arg0) {
						// TODO Auto-generated method stub
						
					}
			
				});
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(decFld,c);
		
		//	Binary Field
		binFld = new JTextField();
		binFld.addFocusListener(new FocusListener()
		{

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				af = ActiveField.BIN;
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	
		});
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(binFld,c);
		
		//	Octal Field
		octFld = new JTextField();
		octFld.addFocusListener(new FocusListener()
		{

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				af = ActiveField.OCT;
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	
		});
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(octFld,c);
		
		//	Hex Field
		hexFld = new JTextField();
		hexFld.addFocusListener(new FocusListener()
		{

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				af = ActiveField.HEX;
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	
		});
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(hexFld,c);
		
		//	Char Field
		chaFld = new JTextField();
		chaFld.addFocusListener(new FocusListener()
		{

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				af = ActiveField.CHA;
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	
		});
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(chaFld,c);
		
		//	Color Field
		colFld = new JLabel(" ");
		c.gridx = 1;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		colFld.setOpaque(true);
		colFld.setVisible(false);
		//colFld.setBackground(new Color(0xFFFFFFFF));
		pnl.add(colFld,c);
		
		
		//	Float Field
		fltFld = new JTextField();
		fltFld.addFocusListener(new FocusListener()
		{

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				af = ActiveField.FLT;
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	
		});
		c.gridx = 1;
		c.gridy = 7;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(fltFld,c);
		
		
		//	ADD BUTTONS
		
		//	Convert Button
		cvtBtn = new JButton("Convert");
		cvtBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)	//	chanage backend value and fill fields when clicked
			{
				setVal();
				setFields();
			}
		});
		c.gridx = 1;
		c.gridy = 8;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(cvtBtn, c);
		
		//	Convert Button
		clrBtn = new JButton("Clear");
		clrBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 	//	clear fields when clicked
			{
				clrFields();
			}
		});
		c.gridx = 2;
		c.gridy = 8;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(clrBtn, c);
		
		//	Convert Button
		colBtn = new JButton("Choose Color");
		c.gridx = 2;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(colBtn, c);
		colBtn.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					
					Color newCol = JColorChooser.showDialog(new JFrame(), "Choose Background Color", Color.BLACK);	//	open color chooser when clicked, return chosen color when 'OK'
					if (newCol == null)
					{
						return;
					}
					af = ActiveField.COL;	//	change activefield to clor
					colFld.setBackground(newCol);	//	change background color of color field
					setVal();	//	set backend val by background color
					setFields();
				}
		
			}
			);
		
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//	close program on exit frame
		
		frm.add(pnl);	//	add panel to frame
		frm.setPreferredSize(new Dimension(500, 500));	//	preferred frame size
		frm.pack();	//	pack frame
		frm.setVisible(true);	//	show frame
	}
	
	//	add passive labels to a panel
	private void addLabels(JPanel pnl)
	{
		JLabel lbl;
		GridBagConstraints c = new GridBagConstraints();
	
//		ADD SPACE
	
		c.weightx = 1.0;	//	horizontal weight for textfield column
		//	Top Center Space via blank Label
		lbl = new JLabel();
		c.gridx = 1;
		c.gridy = 0;
		pnl.add(lbl,c);
		
		c.weightx = 0.25;	//	horizontal weight for button column
		//	Bottom Center Space via blank Label
		c.gridx = 1;
		c.gridy = 9;
		lbl = new JLabel();
		pnl.add(lbl,c);
		
		c.weighty = 1.0;	// vertical weight for header/footer rows
		c.weightx = 0.25;	//	horizontal weight for label column
		//	Top Left Space via blank Label
		c.gridx = 0;
		c.gridy = 0;
		lbl = new JLabel();
		pnl.add(lbl,c);
		
		c.weightx = 0.0;	//	resetting horizontal weight so only determined by above label spacers
		
		//	Bottom Left Space via blank Label
		c.gridx = 0;
		c.gridy = 9;
		lbl = new JLabel();
		pnl.add(lbl,c);
		
		
		//	ADD LABELS
		c.weighty = 0.5;	//	vertical weight for each label row
		//	Decimal Label
		lbl = new JLabel(" Decimal");
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(lbl,c);

		//	Binary Label
		lbl = new JLabel(" Binary");
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(lbl,c);
		
		//	Octal Label
		lbl = new JLabel(" Octal");
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(lbl,c);

		//	Hex Label
		lbl = new JLabel(" Hexadecimal");
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(lbl,c);

		//	Char Label
		lbl = new JLabel(" Character(s)");
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(lbl,c);

		//	Color Label
		lbl = new JLabel(" Color");
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(lbl,c);

		//	Float Label
		lbl = new JLabel(" Float Decimal");
		c.gridx = 0;
		c.gridy = 7;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(lbl,c);
		
		//	Blank Label to weight button row
		lbl = new JLabel();
		c.gridx = 0;
		c.gridy = 8;
		c.fill = GridBagConstraints.HORIZONTAL;
		pnl.add(lbl,c);
		
		c.weighty = 0.0;	//	resetting vertical weight so only determined by label row
	}

	//	set textfields based on backend value
	public void setFields()
	{
		if (af == ActiveField.NON) { return; }
		/*
		int i = bcb.getInt();
		decFld.setText(Integer.toString(i));
		binFld.setText(Integer.toBinaryString(i));
		octFld.setText(Integer.toOctalString(i));
		hexFld.setText(Integer.toHexString(i));
		*/
		
		decFld.setText(bcb.getVal(ActiveField.DEC));
		binFld.setText(bcb.getVal(ActiveField.BIN));
		octFld.setText(bcb.getVal(ActiveField.OCT));
		hexFld.setText(bcb.getVal(ActiveField.HEX));
		
		fltFld.setText(bcb.getVal(ActiveField.FLT));
		
		chaFld.setText(bcb.getVal(ActiveField.CHA));
		
		colFld.setBackground(bcb.getCol());
		colFld.setVisible(true);	//	show color field
		
		
	}
	
	public void clrFields()
	{
		decFld.setText("");
		binFld.setText("");
		octFld.setText("");
		hexFld.setText("");
		chaFld.setText("");
		colFld.setVisible(false);	//	hide color field
		colFld.setBackground(new Color(0));
		fltFld.setText("");
		af = ActiveField.NON;
		bcb.clrVal();
	}
	
	//	set backend value based on a field
	public void setVal()
	{
		switch(af)
		{
		case BIN:
			//bcb.setVal(Integer.parseInt(binFld.getText(), 2));
			bcb.setVal(binFld.getText(), af);
			break;
		case CHA:
			//bcb.setVal(chaFld.getText());
			bcb.setVal(chaFld.getText(), af);
			break;
		case COL:
			bcb.setVal(colFld.getBackground());
			break;
		case DEC:
			//bcb.setVal(Integer.parseInt(decFld.getText(), 10));
			bcb.setVal(decFld.getText(), af);
			break;
		case FLT:
			//bcb.setVal(Float.parseFloat(fltFld.getText()));
			bcb.setVal(fltFld.getText(), af);
			break;
		case HEX:
			//bcb.setVal(Integer.parseInt(hexFld.getText(), 16));
			bcb.setVal(hexFld.getText(), af);
			break;
		case OCT:
			//bcb.setVal(Integer.parseInt(octFld.getText(), 8));
			bcb.setVal(octFld.getText(), af);
			break;
		default:
			break;
		
		}
		
	}
}

