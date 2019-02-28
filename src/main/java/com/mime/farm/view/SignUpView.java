//package com.mime.farm.view;
//
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;
//
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//
//import com.model.User;
//import com.service.UserService;
//import com.service.impl.ServiceFactory;
//
//public class SignUpView extends JFrame{
//		
//	private static final long serialVersionUID = 1L;
//	UserService userService = ServiceFactory.getInstanceOfUsersService();
//	/** 主面板  */
//	private JPanel common_panel;
//	private JPanel panel_up;
//	private JPanel panel_down;
//	private JLabel label_image;
//	
//	private JLabel label_username;
//	private JLabel label_password;
//	private JLabel label_confirmPwd;
//	private JLabel label_goLogin;
//	
//	private JTextField text_username;
//	private JTextField text_password;
//	private JTextField text_confirm_pwd;
//	//private JComboBox<String> text_type;
//	
//	private JButton btn_register;
//	
//	
//	//用作把各个组件初始化的方法
//	private void init(){
//		//在初始化方法中，把各个panel初始化
//		common_panel=new JPanel();
//		panel_up=new JPanel();
//		//使用网格布局管理器，指定3行3列，刚好放8个组件
//		panel_down=new JPanel(new GridLayout(4,3));
//		
//		label_username=new JLabel("账    号：");
//		label_password=new JLabel("密    码：");
//		label_confirmPwd=new JLabel("确认密码：");
//		label_goLogin = new JLabel("已有账号，去登陆");
//		
//		text_username=new JTextField(16);
//		text_password=new JPasswordField(16);
//		text_confirm_pwd=new JPasswordField(16);
//		//text_type=new JComboBox<String>(new String[]{"管理员","用户"});//下拉框
//		
//		btn_register=new JButton("注册");
//		
//		Icon image=new ImageIcon("config/images/001.png");
//		label_image=new JLabel(image);
//		panel_up.add(label_image);
//		panel_up.setPreferredSize(new Dimension(600, 200));
//		//第一排
//		panel_down.add(label_username);
//		panel_down.add(text_username);
//		panel_down.add(new JPanel());
//		//第二排
//		panel_down.add(label_password);
//		panel_down.add(text_password);
//		panel_down.add(new JPanel());
//		//第三排
//		panel_down.add(label_confirmPwd);
//		panel_down.add(text_confirm_pwd);
//		panel_down.add(new JPanel());
//		//第四排
//		panel_down.add(new JPanel());
//		panel_down.add(btn_register);
//		panel_down.add(label_goLogin);
//		
//		
//		label_username.setHorizontalAlignment(JTextField.RIGHT); 
//		label_password.setHorizontalAlignment(JTextField.RIGHT); 
//		label_confirmPwd.setHorizontalAlignment(JTextField.RIGHT); 
//		
//		common_panel.add(panel_up);
//		common_panel.add(panel_down);
//		this.add(common_panel);
//		//调用给按钮注册监听器的方法
//		registerListener();
//	}
//
//	public SignUpView(String title) {
//		//一定记得调用init方法
//		init();
//		this.setTitle(title);
//		this.setSize(600, 500);
//		// 设置窗体居中，代码放在setSize方法之后
//		this.setLocationRelativeTo(null);
//		//不可以放大
//		this.setResizable(false);
//		//默认的关闭操作，程序退出
//		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//		this.setVisible(true);
//	}
//	
//	/**
//	 * registerListener 给登陆按钮，注册按钮添加监听器 
//	 * 
//	 */
//	private void registerListener(){
//		btn_register.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				//1.获取用户名，密码
//				String username=text_username.getText().trim();
//				String password=text_password.getText().trim();
//				String confirmPwd=text_confirm_pwd.getText().trim();
//				//2.做非空判断
//				if("".equals(username)){
//					JOptionPane.showMessageDialog(SignUpView.this, "请输入用户名");
//					return;
//				}else if("".equals(password)){
//					JOptionPane.showMessageDialog(SignUpView.this, "请输入密码");
//					return;
//				}else if("".equals(confirmPwd)) {
//					JOptionPane.showMessageDialog(SignUpView.this, "请确认您的密码");
//					return;
//				}else if(!password.equals(confirmPwd)) {
//					JOptionPane.showMessageDialog(SignUpView.this, "两次输入的密码不一致");
//					return;
//				}else if(username.length()<=2||username.length()>=12) {
//					JOptionPane.showMessageDialog(SignUpView.this, "账号长度不能小于2大于12");
//					return;
//				}else if(password.length()<=3||password.length()>=18) {
//					JOptionPane.showMessageDialog(SignUpView.this, "密码长度不能小于3大于18");
//					return;
//				}else {
//					User isExist = userService.getUserByUsername(username);
//					if(isExist!=null) {
//						JOptionPane.showMessageDialog(SignUpView.this, "该用户已存在，请重新输入");
//						return;
//					}else {
//						boolean result = userService.registerAndInit(username, password);
//						if(result) {
//							JOptionPane.showMessageDialog(SignUpView.this, "注册成功");
//							new UserLoginView("登  录");
//							//销毁当前的登陆窗体
//							SignUpView.this.dispose();
//						} else {
//							JOptionPane.showMessageDialog(SignUpView.this, "出现未知错误");
//						}
//					}
//				}
//			}
//		});
//		label_goLogin.addMouseListener(new MouseAdapter(){
//			public void mouseClicked(MouseEvent e){
//				new UserLoginView("登  录");
//				//销毁当前的登陆窗体
//				SignUpView.this.dispose();
//			}
//		});
//		
//		addWindowListener((WindowListener) new WindowAdapter(){
//			@Override
//			public void windowClosing(WindowEvent e) {
//				int option= JOptionPane.showConfirmDialog(
//					SignUpView.this, "确定退出? ", "提示 ",JOptionPane.YES_NO_OPTION);
//				if(option==JOptionPane.YES_OPTION) {
//					if(e.getWindow() == SignUpView.this){
//						System.exit(0);
//					} else {
//						return;
//					}
//				}
//			}
//		});
//	}//监听 end
//}//class end
