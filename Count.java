
import java.awt.BorderLayout; //组件
import java.awt.Color;
import java.awt.Container; //容器
import java.awt.Font;      //字体
import java.awt.GridLayout;//网格布局
import java.awt.event.ActionEvent;//指示发生了组件定义的动作的语义事件
import java.awt.event.ActionListener;
import java.util.Stack;//栈

import javax.swing.JApplet;//JApplet类使用BorderLayout的一个实例做为其内容窗格的布局管理器
import javax.swing.JButton; //按钮
import javax.swing.JFrame;//窗体
import javax.swing.JPanel;//中间容器
import javax.swing.JTextField;//文本框
 
public class Count extends JApplet implements ActionListener  //实现接口
{
	
	
	private static final long serialVersionUID = 1L; //声明serialVersionUID可以避免对象不一致，
	private JTextField textField = new JTextField("请输入");
	String operator = "";//操作
	String input = "";//输入的 式子
	boolean flag =  true;
//	boolean flag1 = true;
//	boolean flag2 = true;
	
	//界面设置
	public void init()//重写Applet里边的init方法，  init()方法仅用来做初始化操作。
	{
		Container C = getContentPane();  	//初始化容器 添加控件
		JButton b[] = new JButton[16];		//添加按钮
		JPanel panel = new JPanel();
		C.add(textField, BorderLayout.NORTH);
		C.add(panel,BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 4,5,5)); 	//行数，列数，水平间距，垂直间距
		String name[]={"7","8","9","+","4","5","6","-","1","2","3","*","0","C","=","/"};//设置 按钮
		for(int i=0;i<16;i++)//添加按钮
		{
			b[i] = new JButton(name[i]);
			b[i].setBackground( Color.WHITE);//设置背景颜色    new color(240,240,240)
			b[i].setForeground(Color.BLUE);//数字键 设置为 蓝颜色
			if(i%4==3)
				b[i].setForeground(Color.RED);
			b[i].setFont(new Font("宋体",Font.PLAIN,30));//设置字体格式 大小
			panel.add(b[i]);
			b[i].addActionListener(this);
		}
		b[13].setForeground(Color.RED);//非数字键，即运算键设置为红颜色
	}

	//计算逻辑实现
	public void actionPerformed(ActionEvent e) 
	{
		int cnt = 0;
		String actionCommand = e.getActionCommand();//获得这个命令名
		if(actionCommand.equals("+")||actionCommand.equals("-")||actionCommand.equals("*") ||actionCommand.equals("/"))
			input +=" "+actionCommand+" ";//设置输入，把输入的样式改成 需要的样子
		else if(actionCommand.equals("C"))
			input = "";
		else if(actionCommand.equals("="))//当监听到等号时，则处理 input
		{
			input+= "="+compute(input);
			textField.setText(input);
			input="";
			cnt = 1;
		}
		else
			input += actionCommand;//数字为了避免多位数的输入 不需要加空格
		if(cnt==0)
		textField.setText(input);
	}
	//计算逻辑
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
                if(str[i].compareTo("+")==0)  //compare是CString的成员函数，比较两个字符串的   相等则返回0； 
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
                    double ans = s.peek();//取出栈顶元素  
                    s.pop();//消栈  
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
		applet.init();//applet的init方法
		applet.start();//线程开始
		frame.setSize(350, 400);//设置窗口大小
		frame.setVisible(true);//设置窗口可见
	}
 
}
