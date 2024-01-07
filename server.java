package ChatComm;

import javax.swing.*; //this is used to bring a frame in our code

import javax.swing.border.EmptyBorder;
import javax.swing.border.*;

import java.awt.*; // this is used to make changes in frame i.e>background color etc.
import java.awt.event.*;
import java.util.*;
import java.util.Calendar;
import java.text.*;
import java.net.*;
import java.io.*;

public class server implements ActionListener{

    JTextField text; //global declararion kar na padega local declaratioin se mess send nai hoiga!
    JPanel a1; 
    //ye jo niche static likha hu ... wo nai likhege to wo error dega , since static likh k declare karege to function globally declare hoiga.
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dos;


    server() {
        f.setLayout(null); //ye kar ne se ...jo default wale jo bhi layout hai wo use nai honge.........to ab apun khud ka layout banaige , to agar khud se layout banaige to us ko call karna padega{add(name of object created for layout) }aise call kar ne ka.
        JPanel p1 = new JPanel(); // ye panel lagane se ,jo bhi changes hoga panel wale side hoga
        p1.setBackground(new Color(7, 94, 84)); 
        p1.setBounds(0, 0, 450, 70);  //is se define hota hia k panel k changes jo hai wo exectly kaha kar ne ka hai i.e> panel ka location kaha dena hai changes kar ne pe
        p1.setLayout(null); // ye dalege to hi Image likhe gi as icon ...warna image ki size bahut badi dikhegi ...q k agar yaha setlayout null nai karege to aage wale me setbounds kam nai karega
        f.add(p1);   //uper setLayout k comment jo likh hai wo is k bare me likha hua hai!!

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icoin/back_arrow.png")); //classloader  & getsystemresocurce se apun ne frame me icon fit karwayeaur bracket me jo likha hai wo icon wali file ka addres hai
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT); // ye tag dal ne se image puri aigi frame i.e> apun ne aage icon ki size 30X30 diye hai...to agar ye nai dalege to actual image me 30x30 me jitna area aiga khali utna hi dikhega matlab cut ho jaigi image ....ye dalege to puri image aigi choti size me
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);  //ye Jlabel class ki help se icon display hoga/....lekin displayy hone k bad bhi clear nai dikhega ...clear display dikha ne k liye line 14-15 likhna padega
        back.setBounds(5, 20 ,30, 30);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {//ye function dal ne se back_arrow image pe click karege to pura exit ho jaiga i.e>frame close ho jaigi
            public void mouseClicked(MouseEvent ae) {
                // setVisible(false);       {ye kar ne se khali frame hat jaigi ,par code background me chalu rahta hia }
                System.exit(0);     //{ye kar ne se pura code band ho jata hai !!}
            }
        } );

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icoin/profile.png")); 
        Image i5 = i4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT); 
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);  
        profile.setBounds(45, 15 ,40, 40);
        p1.add(profile);
        

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icoin/zoom.png")); 
        Image i8 = i7.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT); 
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video_call = new JLabel(i9);  
        video_call.setBounds(325, 20 ,30, 30);
        p1.add(video_call);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icoin/call.png")); 
        Image i11 = i10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT); 
        ImageIcon i12 = new ImageIcon(i11);
        JLabel voice_call = new JLabel(i12);  
        voice_call.setBounds(380, 25 ,20, 20);
        p1.add(voice_call);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icoin/dots.png")); 
        Image i14 = i13.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT); 
        ImageIcon i15 = new ImageIcon(i14);
        JLabel dots = new JLabel(i15);  
        dots.setBounds(410, 25 ,20, 20);
        p1.add(dots);

        JLabel name = new JLabel("Badshah khan");
        name.setBounds(100, 20, 100, 20);
        name.setForeground(Color.black);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

        JLabel status = new JLabel("Active now");
        status.setBounds(100, 35, 100, 20);
        status.setForeground(Color.white);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 10));
        p1.add(status);

        a1 = new JPanel();
        a1.setBounds(5, 75, 440, 570);
        f.add(a1);

        // Create a JScrollPane to make the chat area scrollable
        JScrollPane scrollPane = new JScrollPane(a1);
        scrollPane.setBounds(5, 75, 440, 570);
        f.add(scrollPane);

        text = new JTextField();
        text.setBounds(2, 650, 375, 50);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        f.add(text);

        JButton b1 = new JButton("send");
        b1.setBounds(380, 650, 69, 50);
        b1.setBackground(Color.red);
        b1.setForeground(Color.black);
        b1.addActionListener(this);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        f.add(b1);//is ki jagaah par try kar k ...jaise back arrow me dala hai waise ho!...ye button ko nai rakhna pade ...apni image hi click kar k aage kam kar ni chahiye!!

        f.setSize(450, 700);
        f.setLocation(200, 300);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.pink);  //getContentPane() :ye wala tag use kar k age likhege kuch function to function pure frame pe kam karega
        f.setVisible(true);

    }
    public void actionPerformed(ActionEvent ae) {
        try{
        String out = text.getText();
        // System.out.println(out);

        //ye label and pannel is liye bana na pada QK line no.111 me "out" directyly apun nai dal sakte
        // JLabel output = new JLabel(out);
        JPanel p2 = formatLabel(out);
        // p2.add(output);

        a1.setLayout(new BorderLayout());

        //ye panel ki help se, jo bhi apun likhege wo top right corner pe visible hoga(with 15px padding b\w 2 lines)
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));

        a1.add(vertical,BorderLayout.PAGE_START);

        // Make sure you revalidate the JScrollPane
        a1.revalidate();
        a1.repaint();
        a1.validate();


        dos.writeUTF(out);

        text.setText("");

        f.repaint();
        f.invalidate();
        f.validate();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<HTML> <p style=\"width:150px\">"+out+"</p> </HTML> ");//we can also use HTML tag in java 
        output.setFont(new Font("tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(250, 185, 234));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
         
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }


    public static void main(String[] args) {
        new server();

        try{

            ServerSocket skt = new ServerSocket(6001);
            while (true) {
                Socket s = skt.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());

                while (true) {
                    String msg = din.readUTF();
                    JPanel panel = formatLabel(msg);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
