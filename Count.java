
import java.awt.BorderLayout; //���
import java.awt.Color;
import java.awt.Container; //����
import java.awt.Font;      //����
import java.awt.GridLayout;//���񲼾�
import java.awt.event.ActionEvent;//ָʾ�������������Ķ����������¼�
import java.awt.event.ActionListener;
import java.util.Stack;//ջ

import javax.swing.JApplet;//JApplet��ʹ��BorderLayout��һ��ʵ����Ϊ�����ݴ���Ĳ��ֹ�����
import javax.swing.JButton; //��ť
import javax.swing.JFrame;//����
import javax.swing.JPanel;//�м�����
import javax.swing.JTextField;//�ı���
 
public class Count extends JApplet implements ActionListener  //ʵ�ֽӿ�
{
	
	
	private static final long serialVersionUID = 1L; //����serialVersionUID���Ա������һ�£�
	private JTextField textField = new JTextField("������");
	String operator = "";//����
	String input = "";//����� ʽ��
	boolean flag =  true;
//	boolean flag1 = true;
//	boolean flag2 = true;
	
	//��������
	public void init()//��дApplet��ߵ�init������  init()��������������ʼ��������
	{
		Container C = getContentPane();  	//��ʼ������ ��ӿؼ�
		JButton b[] = new JButton[16];		//��Ӱ�ť
		JPanel panel = new JPanel();
		C.add(textField, BorderLayout.NORTH);
		C.add(panel,BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 4,5,5)); 	//������������ˮƽ��࣬��ֱ���
		String name[]={"7","8","9","+","4","5","6","-","1","2","3","*","0","C","=","/"};//���� ��ť
		for(int i=0;i<16;i++)//��Ӱ�ť
		{
			b[i] = new JButton(name[i]);
			b[i].setBackground( Color.WHITE);//���ñ�����ɫ    new color(240,240,240)
			b[i].setForeground(Color.BLUE);//���ּ� ����Ϊ ����ɫ
			if(i%4==3)
				b[i].setForeground(Color.RED);
			b[i].setFont(new Font("����",Font.PLAIN,30));//���������ʽ ��С
			panel.add(b[i]);
			b[i].addActionListener(this);
		}
		b[13].setForeground(Color.RED);//�����ּ��������������Ϊ����ɫ
	}

	//�����߼�ʵ��
	public void actionPerformed(ActionEvent e) 
	{
		int cnt = 0;
		String actionCommand = e.getActionCommand();//������������
		if(actionCommand.equals("+")||actionCommand.equals("-")||actionCommand.equals("*") ||actionCommand.equals("/"))
			input +=" "+actionCommand+" ";//�������룬���������ʽ�ĳ� ��Ҫ������
		else if(actionCommand.equals("C"))
			input = "";
		else if(actionCommand.equals("="))//���������Ⱥ�ʱ������ input
		{
			input+= "="+compute(input);
			textField.setText(input);
			input="";
			cnt = 1;
		}
		else
			input += actionCommand;//����Ϊ�˱����λ�������� ����Ҫ�ӿո�
		if(cnt==0)
		textField.setText(input);
	}
	//�����߼�
	private String compute(String input)
	{
		String str[];
		str = input.split(" ");
		Stack<Double> s = new Stack<Double>();
		double m = Double.parseDouble(str[0]);
		s.push(m);
		for(int i=1;i<str.length;i++)
		{
			if(i%2==1)  
            {  
                if(str[i].compareTo("+")==0)  //compare��CString�ĳ�Ա�������Ƚ������ַ�����   ����򷵻�0�� 
                {  
                    double help = Double.parseDouble(str[i+1]);  
                    s.push(help);  
                }  
                  
                if(str[i].compareTo("-")==0)  
                {  
                    double help = Double.parseDouble(str[i+1]);  
                    s.push(-help);  
                }  
                  
                if(str[i].compareTo("*")==0)  
                {  
                    double help = Double.parseDouble(str[i+1]);  
                    double ans = s.peek();//ȡ��ջ��Ԫ��  
                    s.pop();//��ջ  
                    ans*=help;  
                    s.push(ans);  
                }  
                  
                if(str[i].compareTo("/")==0)  
                {  
                    double help = Double.parseDouble(str[i+1]);  
                    double ans = s.peek();  
                    s.pop();  
                    ans/=help;
                    
                    s.push(ans);  
                }  
            }  
        }  
        double ans = 0d;  
        while(!s.isEmpty())  
        {  
            ans+=s.peek();  
            s.pop();  
        }  
        String result = String.valueOf(ans);
        return result;
	}
	public static void main(String args[])
	{
		JFrame frame = new JFrame("Count");
		Count applet = new Count();
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();//applet��init����
		applet.start();//�߳̿�ʼ
		frame.setSize(350, 400);//���ô��ڴ�С
		frame.setVisible(true);//���ô��ڿɼ�
	}
 
}
