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
//import com.util.Constant;
//import com.view.UserRegisterView;
///**
// * 用户登陆窗体：
// * 需要三个JPanel:一个是总的panel，一个是leftpanel（放图片） 一个是rightpanel（放标签，输入框，按钮）
// * 
// * panel_right:有三个标签， 两个文本框，一个下拉框， 两个按钮
// *
// */
//public class SignInView extends JFrame{
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
//	private JLabel label_register;
//	private JLabel label_retrieve;
//	
//	private JTextField text_username;
//	private JTextField text_password;
//	//private JComboBox<String> text_type;
//	
//	private JButton btn_login;
//	
//	//用作把各个组件初始化的方法
//	private void init(){
//		//在初始化方法中，把各个panel初始化
//		common_panel=new JPanel();
//		panel_up=new JPanel();
//		//使用网格布局管理器，指定3行3列，刚好放8个组件
//		panel_down=new JPanel(new GridLayout(3,3));
//		
//		label_username=new JLabel("账    号：");
//		label_password=new JLabel("密    码：");
//		label_register=new JLabel("注册账号");
//		label_retrieve=new JLabel("找回密码");
//		
//		text_username=new JTextField(16);
//		text_password=new JPasswordField(16);
//		
//		//text_type=new JComboBox<String>(new String[]{"管理员","用户"});//下拉框
//		
//		btn_login=new JButton("登录");
//		
//		Icon image=new ImageIcon("config/images/001.png");
//		label_image=new JLabel(image);
//		panel_up.add(label_image);
//		panel_up.setPreferredSize(new Dimension(600, 200));
//		
//		panel_down.add(label_username);
//		panel_down.add(text_username);
//		panel_down.add(label_register);
//		
//		panel_down.add(label_password);
//		panel_down.add(text_password);
//		panel_down.add(label_retrieve);
//		panel_down.add(new JPanel());
//		panel_down.add(btn_login);
//		
//		label_username.setHorizontalAlignment(JTextField.RIGHT); 
//		label_password.setHorizontalAlignment(JTextField.RIGHT); 
//		
//		common_panel.add(panel_up);
//		common_panel.add(panel_down);
//		this.add(common_panel);
//		//调用给按钮注册监听器的方法
//		registerListener();
//	}
//
//	public SignInView(String title) {
//		//一定记得调用init方法
//		init();
//		
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
//	 */
//	
//	
//	private void registerListener(){
//		btn_login.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				/*
//				 * 1.获取用户名，密码，用户类型
//				 *  非空判断，为空，提醒用户输入
//				 *  		不为空，执行第2步
//				 * 2.拿着用户名和密码去数据库查询用户是否存在
//				 * 	存在登陆成功去到主界面
//				 * 	不存在提示用户，用户名密码错误，重新输入
//				 */
//				//1.获取用户名，密码
//				String username=text_username.getText().trim();
//				String password=text_password.getText().trim();
//				
//				//String type=(String)text_type.getSelectedItem();//获取下拉框中的值
//				//2.做非空判断
//				if("".equals(username)){
//					JOptionPane.showMessageDialog(SignInView.this, "请输入用户名");
//					return;
//				}if("".equals(password)){
//					JOptionPane.showMessageDialog(SignInView.this, "请输入密码");
//					return;
//				}
//				//登录
//				User user=userService.login(username, password);
//				if(user==null){
//					JOptionPane.showMessageDialog(SignInView.this, "用户名密码错误");
//					return;
//				} else {
//					if(user.getStatus()==2) {
//						JOptionPane.showMessageDialog(SignInView.this, "你的账户已被查封");
//						return;
//					}else if(user.getUserslock()==0) {
//						System.out.println(user.toString());
//						Constant.setUser(user);
//						userService.userLock(1, user.getUsername());
//						// 如果用户合法：根据用户类型，跳转到对应窗体
//						if(user.getStatus()==0) {
//							// 普通用户
//							new GameView(user.getName()+"的农场");
//							SignInView.this.dispose();
//						} else if(user.getStatus()==1){
//							//等于1是管理员
//							//AdminMainView amf=new AdminMainView();
//							JOptionPane.showMessageDialog(SignInView.this, "您是管理员用户");
//						}
//					}else{
//						int option= JOptionPane.showConfirmDialog(
//							SignInView.this, "您的账户正在游戏中，是否强制下线", "提示 ",JOptionPane.YES_NO_OPTION);
//						if(option==JOptionPane.YES_OPTION) {
//							userService.userLock(0, user.getUsername());
//						}
//					}
//				}
//			}
//		});
//		
//		/**
//		 * 跳转注册窗口
//		 */
//		label_register.addMouseListener(new MouseAdapter(){
//			public void mouseClicked(MouseEvent e){
//				new UserRegisterView("注  册");
//				//销毁当前的登陆窗体
//				SignInView.this.dispose();
//			}
//		});
//		
//		/**
//		 * 关闭窗口验证
//		 */
//		addWindowListener((WindowListener) new WindowAdapter(){
//			@Override
//			public void windowClosing(WindowEvent e) {
//				int option= JOptionPane.showConfirmDialog(
//						SignInView.this, "确定退出? ", "提示 ",JOptionPane.YES_NO_OPTION);
//				if(option==JOptionPane.YES_OPTION) {
//					if(e.getWindow() == SignInView.this){
//						System.exit(0);
//					} else {
//						return;
//					}
//				}
//			}
//		});
//	}
//}
