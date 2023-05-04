import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.*;

public class bill_frame extends JFrame implements ActionListener {
    JTextField txt_name;
    String customer_id,name,meter;
    int amount,unit_rate=7;
    JButton btn;
    JTextField txt_area, customer_ID_txt;
    Color color ;
    public bill_frame() {
        super("Bill system");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(1920, 1080);
        this.setLocation(0, 0);

        Image icon = Toolkit.getDefaultToolkit().getImage("Untitled.png");
        this.setIconImage(icon);
         color = new Color(173,216,230);
        String font_srting = "Constantia";
        
        this.setLayout(new BorderLayout(10, 10));
        this.getContentPane().setBackground(color);
        this.getContentPane().setForeground(Color.yellow);
        JPanel panel = new JPanel();
        JPanel temPanel=new JPanel();
        panel.setLayout(new BorderLayout(10, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(color);

        JLabel customer_ID_Label = new JLabel("Coustomer Id ");
        customer_ID_Label.setFont(new Font(font_srting, Font.BOLD, 20));
        customer_ID_txt = new JTextField(30);
        customer_ID_txt.setBackground(color);
        panel.add(customer_ID_Label,BorderLayout.WEST);
        panel.add(customer_ID_txt,BorderLayout.CENTER);
        btn = new JButton("Submit");
        btn.addActionListener(this);
        temPanel.setLayout(new FlowLayout());
        temPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        temPanel.setBackground(color);
        
        temPanel.add(btn);
        this.add(panel,BorderLayout.NORTH);
        this.add(temPanel,BorderLayout.CENTER);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            // this.btn.setEnabled(false);
            customer_id = customer_ID_txt.getText();
            customer_details();
            System.out.println(customer_id);

        }
        // TODO Auto-generated method stub
        
    }

    void customer_details(){
        try {
            Conn c= new Conn();
            ResultSet rs= c.s.executeQuery("select * from customer where customer_id="+customer_id+";");
            
            if (rs.next()) {
                 meter=rs.getString("meter_value");
                 name=rs.getString("name");
                 amount=Integer.parseInt(meter) * unit_rate;
                 
            }}
            
        
    catch(SQLException e)    {
        // TODO Auto-generated catch block
        
    }

    
    JPanel panel2 = new JPanel();panel2.setLayout(new GridLayout(3,2,10,-110));panel2.setBackground(color);
    
    panel2.setBorder(BorderFactory.createEmptyBorder(200, 100, 100, 100));
    JLabel name_label = new JLabel("Customer Name");name_label.setFont(new Font("Constantia",Font.BOLD,20));
    JLabel name_txt = new JLabel(name);name_txt.setFont(new Font("Constantia",Font.BOLD,20));
    JLabel meter_label = new JLabel("Meter Value");meter_label.setFont(new Font("Constantia",Font.BOLD,20));
    JLabel meter_txt = new JLabel(meter+" Units");meter_txt.setFont(new Font("Constantia",Font.BOLD,20));panel2.add(name_label);
    JLabel amount_Label=new JLabel("Amount");amount_Label.setFont(new Font("Constantia",Font.BOLD,20));amount_Label.setForeground(new Color(100, 183, 15));
    JLabel amount_value=new JLabel(Integer.toString(amount));amount_value.setFont(new Font("Constantia",Font.BOLD,20));amount_value.setForeground(new Color(100, 183, 15));
    panel2.add(name_label);
    panel2.add(name_txt);
    panel2.add(meter_label);
    panel2.add(meter_txt);
    panel2.add(amount_Label);
    panel2.add(amount_value);
    JPanel panel3 = new JPanel();panel3.setLayout(new BorderLayout(10, 20));panel3.setBackground(color);
    JButton btn2=new JButton("Pay");
    btn2.addActionListener(new ActionListener(){
    
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if(e.getSource()==btn2){
                Conn c= new Conn();
                try {
                    c.s.executeUpdate("update customer set meter_value=0 where customer_id="+customer_id+";");
                    JOptionPane.showMessageDialog(null, "Payment Successful");
                    setVisible(false);
                    new bill_frame();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    });
    panel3.add(btn2,BorderLayout.SOUTH);
    this.add(panel2);
    this.add(panel3,BorderLayout.SOUTH);
    this.setVisible(true);

        }}

