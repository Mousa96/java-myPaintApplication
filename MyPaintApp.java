

import javax.imageio.ImageIO;
import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MyPaintApp extends JFrame implements ActionListener/*,MouseListener*/ {

	//declaring instance variables
	
	//upper menu
	private JMenuBar menuBar;
	
	//upper menu items
	private JMenu settings;
	private JMenu currentShape;
	
	//current shape menu items
	private JRadioButtonMenuItem circle;
	private JRadioButtonMenuItem oval;
	private JRadioButtonMenuItem rectangle;
	private JRadioButtonMenuItem square;
	private JRadioButtonMenuItem line;
	
	//settings menu items
	private JMenuItem drawingSettings;
	
	
	//JTool Bar 
	private JToolBar toolBar;
	private JToggleButton Circle;
	private JToggleButton Oval;
	private JToggleButton Rectangle;
	private JToggleButton Square;
	private JToggleButton Line;
	
	// Boolean variables for indicating the type of shape
	
	private boolean cir=false;
	private boolean ovl=false;
	private boolean rec=false;
	private boolean sqr=false;
	private boolean lin=false;
	
	//Shape Array & Counter to track number of objects created
	
	Shape shapes[] = new Shape[2000];
	int counter=0;
	
	//Variables to store the colorChoice,Shape & to transform the boolean value of isFill() to integer
	int shapeType=1;
	int fill = 0;
	int colorChoice = 0;
	
	//Variables for the drawing settings JDialog
	JRadioButton filled;
	JRadioButton normal;
	JComboBox colors;
	JButton okButton;
	JLabel drawingStyle;
	JLabel colorSelection;
	
	
	
	
	

	public MyPaintApp()
	{
		
		// Initializing & Setting basic JFrame components 
		super("Welcome to MyPaint - Designed by: Mosaa Alanazi");
		this.setSize(1000,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		menuBar=new JMenuBar();
		settings = new JMenu("Settings");
		currentShape = new JMenu("Current Shape");
		
		circle = new JRadioButtonMenuItem("Circle");
		oval = new JRadioButtonMenuItem("Oval");
		rectangle = new JRadioButtonMenuItem("Rectangle");
		square = new JRadioButtonMenuItem("Square");
		line = new JRadioButtonMenuItem("Line");
		
		//Adding components 
		
		currentShape.add(circle);
		currentShape.add(oval);
		currentShape.add(rectangle);
		currentShape.add(square);
		currentShape.add(line);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		buttonGroup.add(circle);
		buttonGroup.add(oval);
		buttonGroup.add(rectangle);
		buttonGroup.add(square);
		buttonGroup.add(line);
		
		drawingSettings = new JMenuItem("Drawing Settings");
		settings.add(drawingSettings);
		
		menuBar.add(settings);
		menuBar.add(currentShape);
		
		// JToolBar & images
		
		Circle = new JToggleButton("Circle");
		Oval = new JToggleButton("Oval");
		Rectangle = new JToggleButton("Rectangle");
		Square = new JToggleButton("Square");
		Line = new JToggleButton("Line");
		
		try{
			
			Image image1A = ImageIO.read(getClass().getResource("/circle.jpg"));
			Image image1B = image1A.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
			Circle.setIcon(new ImageIcon(image1B));
			
			Image image2A = ImageIO.read(getClass().getResource("/Oval.JPG"));
			Image image2B = image2A.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
			Oval.setIcon(new ImageIcon(image2B));
			
			Image image3A = ImageIO.read(getClass().getResource("/rect.jpg"));
			Image image3B = image3A.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
			Rectangle.setIcon(new ImageIcon(image3B));
			
			Image image4A = ImageIO.read(getClass().getResource("/square.JPG"));
			Image image4B = image4A.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
			Square.setIcon(new ImageIcon(image4B));
			
			Image image5A = ImageIO.read(getClass().getResource("/line.jpg"));
			Image image5B = image5A.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
			Line.setIcon(new ImageIcon(image5B));
			
			
		}catch(Exception e)
		{
		
		JOptionPane.showMessageDialog(this, "Images Couldn't be imported... Please Make Sure That You Have The Right Resources!");
		}
		
		Circle.setVerticalTextPosition(SwingConstants.BOTTOM);
		Circle.setHorizontalTextPosition(SwingConstants.CENTER);
		
		Oval.setVerticalTextPosition(SwingConstants.BOTTOM);
		Oval.setHorizontalTextPosition(SwingConstants.CENTER);
		
		Rectangle.setVerticalTextPosition(SwingConstants.BOTTOM);
		Rectangle.setHorizontalTextPosition(SwingConstants.CENTER);
		
		Square.setVerticalTextPosition(SwingConstants.BOTTOM);
		Square.setHorizontalTextPosition(SwingConstants.CENTER);
		
		Line.setVerticalTextPosition(SwingConstants.BOTTOM);
		Line.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		this.setJMenuBar(menuBar);
		
		toolBar = new JToolBar(JToolBar.VERTICAL);
		
		
		toolBar.add(Circle);
		toolBar.add(Oval);
		toolBar.add(Rectangle);
		toolBar.add(Square);
		toolBar.add(Line);
		
		toolBar.setFloatable(false);
		
	    
		this.add(toolBar, BorderLayout.WEST);
		
		//ActionListeners
		
		Oval.addActionListener(this);
		Circle.addActionListener(this);
		Rectangle.addActionListener(this);
		Square.addActionListener(this);
		Line.addActionListener(this);
		
		oval.addActionListener(this);
		circle.addActionListener(this);
		rectangle.addActionListener(this);
		square.addActionListener(this);
		line.addActionListener(this);
		
		drawingSettings.addActionListener(this);
		
		
		//Drawing Panel
		
		DrawingPanel drawingPanel = new DrawingPanel();
		this.add(drawingPanel,BorderLayout.CENTER);
		
		
		this.setVisible(true);
		
	}
	
	// Setting the constraints of the JFrame
	
	int x1,x2;
	int y1,y2;
	int width,height;
	
	public void startP(int x1A, int y1A) {
		x1 = x1A;
		y1 = y1A;
	}

	public void endP(int x2B, int y2B) {
		x2 = x2B;
		y2 = y2B;
	}

	
	
	// Drawing Part(I)
	
	
	
	public void drawOval(Graphics g, int x1, int y1, int x2, int y2) {
		int width = Math.abs(x1 - x2);
		int height = Math.abs(y1 - y2);
		g.drawOval(x1, y1, width, height);
		
	}
	
	public void drawCircle(Graphics g, int x1, int y1, int x2, int y2) {
		int diameter = Math.abs(x1 - x2);
		g.drawOval(x1, y1, diameter, diameter);
		
	}

	
	public void drawRectangle(Graphics g, int x1, int y1, int x2, int y2) {
		int startingX = Math.min(x1, x2);
		int startingY = Math.min(y1, y2);
		int width = Math.abs(x1 - x2);
		int height = Math.abs(y1 - y2);
		g.drawRect(startingX, startingY, width, height);
		
	}
	
	public void drawSquare(Graphics g, int x1, int y1, int x2, int y2) {
		int startingX = Math.min(x1, x2);
		int startingY = Math.min(y1, y2);
		int length = Math.abs(x1 - x2);
		g.drawRect(startingX, startingY, length, length);
		
	}
	
	public void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
		g.drawLine(x1, y1, x2, y2);
	
	}
	
	
	
	
	
	public void fillOval(Graphics g, int x1, int y1, int x2, int y2) {
		int width = Math.abs(x1 - x2);
		int height = Math.abs(y1 - y2);
		g.fillOval(x1, y1, width, height);
		
	}
	
	public void fillCircle(Graphics g, int x1, int y1, int x2, int y2) {
		int diameter = Math.abs(x1 - x2);
		g.fillOval(x1, y1, diameter, diameter);
		

	}
	public void fillRectangle(Graphics g, int x1, int y1, int x2, int y2) {
		int startingX = Math.min(x1, x2);
		int startingY = Math.min(y1, y2);
		int width = Math.abs(x1 - x2);
		int height = Math.abs(y1 - y2);
		g.fillRect(startingX, startingY, width, height);
	

	}
	
	public void fillSquare(Graphics g, int x1, int y1, int x2, int y2) {
		int startingX = Math.min(x1, x2);
		int startingY = Math.min(y1, y2);
		int length = Math.abs(x1 - x2);
		g.fillRect(startingX, startingY, length, length);
		
	}
	
	public void fillLine(Graphics g, int x1, int y1, int x2, int y2) {
		g.drawLine(x1, y1, x2, y2);
		

	}
	
	
	


	
	class DrawingSettings extends JDialog implements ActionListener{
		public DrawingSettings()
		{
			this.setTitle("Drawing Settings");
			this.setSize(300,300);
			this.setLocationRelativeTo(null);
			this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
			this.setLayout(new GridLayout(3, 1));
			
			JPanel firstPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			colorSelection = new JLabel("Select Drawing Color");
			String Colors[] = { "Blue", "Green", "Red", "Black" };
			colors = new JComboBox(Colors);
			firstPanel.add(colorSelection);
			firstPanel.add(colors);
			this.add(firstPanel);
			
			JPanel secondPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			filled = new JRadioButton("Filled");
			normal = new JRadioButton("Normal");
			normal.setSelected(true);
			drawingStyle = new JLabel("Select Drawing Style");
			ButtonGroup Group = new ButtonGroup();
			Group.add(filled);
			Group.add(normal);

			secondPanel.add(drawingStyle);
			secondPanel.add(filled);
			secondPanel.add(normal);
			this.add(secondPanel);
			
			okButton = new JButton("OK");
			okButton.addActionListener(this);
			
			this.add(okButton);
			
			this.pack();
			this.setVisible(true);
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("OK")) {
	
				if (colors.getSelectedItem().equals("Black"))
					colorChoice = 0;
				if (colors.getSelectedItem().equals("Green"))
					colorChoice = 1;
				if (colors.getSelectedItem().equals("Red"))
					colorChoice = 2;
				if (colors.getSelectedItem().equals("Blue"))
					colorChoice = 3;
				if (normal.isSelected())
					fill = 0;
				if (filled.isSelected())
					fill = 1;
			}
			
		}
	}
	
	/*@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} */
	
	
	// Drawing Part (II)
	
	
	class DrawingPanel extends JPanel
	{
		public DrawingPanel() {
			setBackground(Color.WHITE);
			MyMouseListener listener = new MyMouseListener();
			addMouseListener(listener);
			addMouseMotionListener(listener);
		}
		
		class MyMouseListener extends MouseAdapter{
			
			public void mousePressed(MouseEvent e) {
				startP(e.getX(), e.getY());
				
			}

			public void mouseDragged(MouseEvent e) {
				endP(e.getX(), e.getY());
				repaint();
			}
			
			public void mouseReleased(MouseEvent e) {
				endP(e.getX(), e.getY());
				
				if (shapeType==1 && fill == 0) {
					Color ColorSel = java.awt.Color.black;
					if (colorChoice == 0)
						ColorSel = java.awt.Color.black;
					if (colorChoice  == 1)
						ColorSel = java.awt.Color.green;
					if (colorChoice  == 2)
						ColorSel = java.awt.Color.red;
					if (colorChoice  == 3)
						ColorSel = java.awt.Color.blue;
					shapes[counter] = new Circle(x1, y1, x2, y2, false, ColorSel);
					}
				if (shapeType==2 && fill == 0) {
					Color ColorSel = java.awt.Color.black;
					if (colorChoice == 0)
						ColorSel = java.awt.Color.black;
					if (colorChoice  == 1)
						ColorSel = java.awt.Color.green;
					if (colorChoice  == 2)
						ColorSel = java.awt.Color.red;
					if (colorChoice  == 3)
						ColorSel = java.awt.Color.blue;
					shapes[counter] = new Oval(x1, y1, x2, y2, false, ColorSel);
					}
				if (shapeType==3 && fill == 0) {
					Color ColorSel = java.awt.Color.black;
					if (colorChoice == 0)
						ColorSel = java.awt.Color.black;
					if (colorChoice  == 1)
						ColorSel = java.awt.Color.green;
					if (colorChoice  == 2)
						ColorSel = java.awt.Color.red;
					if (colorChoice  == 3)
						ColorSel = java.awt.Color.blue;
					shapes[counter] = new Rectangle(x1, y1, x2, y2, false, ColorSel);
					
					}
				if (shapeType==4 && fill == 0) {
					Color ColorSel = java.awt.Color.black;
					if (colorChoice == 0)
						ColorSel = java.awt.Color.black;
					if (colorChoice  == 1)
						ColorSel = java.awt.Color.green;
					if (colorChoice  == 2)
						ColorSel = java.awt.Color.red;
					if (colorChoice  == 3)
						ColorSel = java.awt.Color.blue;
					shapes[counter] = new Square(x1, y1, x2, y2, false, ColorSel);
					}
				if (shapeType==5 && fill == 0) {
					Color ColorSel = java.awt.Color.black;
					if (colorChoice == 0)
						ColorSel = java.awt.Color.black;
					if (colorChoice  == 1)
						ColorSel = java.awt.Color.green;
					if (colorChoice  == 2)
						ColorSel = java.awt.Color.red;
					if (colorChoice  == 3)
						ColorSel = java.awt.Color.blue;
					shapes[counter] = new Line(x1, y1, x2, y2, false, ColorSel);
					}
				
				if (shapeType==1 && fill == 1) {
					Color ColorSel = java.awt.Color.black;
					if (colorChoice == 0)
						ColorSel = java.awt.Color.black;
					if (colorChoice  == 1)
						ColorSel = java.awt.Color.green;
					if (colorChoice  == 2)
						ColorSel = java.awt.Color.red;
					if (colorChoice  == 3)
						ColorSel = java.awt.Color.blue;
					shapes[counter] = new Circle(x1, y1, x2, y2, true, ColorSel);
					}
				if (shapeType==2 && fill == 1) {
					Color ColorSel = java.awt.Color.black;
					if (colorChoice == 0)
						ColorSel = java.awt.Color.black;
					if (colorChoice  == 1)
						ColorSel = java.awt.Color.green;
					if (colorChoice  == 2)
						ColorSel = java.awt.Color.red;
					if (colorChoice  == 3)
						ColorSel = java.awt.Color.blue;
					shapes[counter] = new Oval(x1, y1, x2, y2, true, ColorSel);
					}
				if (shapeType==3 && fill == 1) {
					Color ColorSel = java.awt.Color.black;
					if (colorChoice == 0)
						ColorSel = java.awt.Color.black;
					if (colorChoice  == 1)
						ColorSel = java.awt.Color.green;
					if (colorChoice  == 2)
						ColorSel = java.awt.Color.red;
					if (colorChoice  == 3)
						ColorSel = java.awt.Color.blue;
					shapes[counter] = new Rectangle(x1, y1, x2, y2, true, ColorSel);
					}
				if (shapeType==4 && fill == 1) {
					Color ColorSel = java.awt.Color.black;
					if (colorChoice == 0)
						ColorSel = java.awt.Color.black;
					if (colorChoice  == 1)
						ColorSel = java.awt.Color.green;
					if (colorChoice  == 2)
						ColorSel = java.awt.Color.red;
					if (colorChoice  == 3)
						ColorSel = java.awt.Color.blue;
					shapes[counter] = new Square(x1, y1, x2, y2, true, ColorSel);
					
					}
				if (shapeType==5 && fill == 1) {
					Color ColorSel = java.awt.Color.black;
					if (colorChoice == 0)
						ColorSel = java.awt.Color.black;
					if (colorChoice  == 1)
						ColorSel = java.awt.Color.green;
					if (colorChoice  == 2)
						ColorSel = java.awt.Color.red;
					if (colorChoice  == 3)
						ColorSel = java.awt.Color.blue;
					shapes[counter] = new Line(x1, y1, x2, y2, false, ColorSel);
					}
				counter++;
				repaint();
				

		}
		}
		
		public void paintComponent(Graphics g){
			
			super.paintComponent(g);
			for (int i = 0; i < counter; i++) {
				shapes[i].drawOrFill(g);
			}
			
			if (colorChoice == 0)
				g.setColor(java.awt.Color.black);
			if (colorChoice == 1)
				g.setColor(java.awt.Color.green);
			if (colorChoice == 2)
				g.setColor(java.awt.Color.red);
			if (colorChoice == 3)
				g.setColor(java.awt.Color.blue);
			
			if (shapeType==2 && fill == 0) {
				drawOval(g, x1, y1, x2, y2);

			}

			if (shapeType==1  && fill == 0) {
				drawCircle(g, x1, y1, x2, y2);

			}
			
			if (shapeType==3  && fill == 0) {
				drawRectangle(g, x1, y1, x2, y2);
			}
			if (shapeType==4   && fill == 0) {
				drawSquare(g, x1, y1, x2, y2);
			}

			if (shapeType==5 && fill == 0) {
				drawLine(g, x1, y1, x2, y2);
			}
			
			
			
			if (shapeType==2 && fill == 1) {
				fillOval(g, x1, y1, x2, y2);

			}
			
			if (shapeType==1 && fill == 1) {
				fillCircle(g, x1, y1, x2, y2);

			}

			if (shapeType==3 && fill == 1) {
				fillRectangle(g, x1, y1, x2, y2);

			}
			if (shapeType==4 && fill == 1) {
				fillSquare(g, x1, y1, x2, y2);

			}

			if (shapeType==5 && fill == 1) {
				fillLine(g, x1, y1, x2, y2);

			}
			
			
	}
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource().equals(Circle) || e.getSource().equals(circle)) {
			//cir=true;
			shapeType=1;
			
		}
		if (e.getSource().equals(Oval) || e.getSource().equals(oval)) {
			//ovl=true;
			shapeType=2;
		}
		if (e.getSource().equals(Rectangle) || e.getSource().equals(rectangle)) {
			//rec = true;
			shapeType=3;
		}
		if (e.getSource().equals(Square) || e.getSource().equals(square)) {
			//sqr = true;
			shapeType=4;
		}
		if (e.getSource().equals(Line) || e.getSource().equals(line)) {
			//lin = true;
			shapeType=5;
		}
		if (e.getSource().equals(drawingSettings)) {
			DrawingSettings ds = new DrawingSettings();
		}
		
	}
	
	public static void main(String[] args)
	{
		new MyPaintApp();
	}

	

}


