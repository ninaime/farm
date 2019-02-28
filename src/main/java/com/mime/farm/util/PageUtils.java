package com.mime.farm.util;


public class PageUtils {
	int sum;//总条数
    int start=1;//首页
    int count = 6;//一页多少条数据
    int last = 0;//多少页
    int pages=1;//当前页数
	public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getLast() {
        return last;
    }
    public void setLast(int last) {
        this.last = last;
    }
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public void totalPages(int total) {
        // 假设总数是50，是能够被5整除的，那么最后一页的开始就是45
        if (0 == total % count) {
            last = total/count;
        // 假设总数是51，不能够被5整除的，那么最后一页的开始就是50
        }else {
        	last = (total/count)+1;
        }
    }
//	
//	public boolean choosePage() {
//		System.out.println("\t当前第  "+this.pages+"  页");
//		System.out.println("输入“1”进入下一页    输入“2”进入上一页    输入“3”进入首页    输入“4”进入尾页    输入“5”选择页数    输入“0”返回上一层");
//		boolean flag = true;
//		while(flag) {
//			String choose = Tools.getChoose();
//			switch (choose) {
//			case "1":
//				//如果当前页数+1大于最大页数
//				if(this.pages+1>this.last) {
//					System.out.println("这已经是最后一页了");
//				}else {
//					this.pages++;
//					flag = false;
//				}
//				break;
//			case "2":
//				//如果当前页数-1小于最小页数
//				if(this.pages-1<this.start) {
//					System.out.println("这已经是第一页了");
//				}else {
//					this.pages--;
//					flag = false;
//				}
//				break;
//			case "3":
//				this.pages=this.start;
//				flag = false;
//				break;
//			case "4":
//				this.pages=this.last;
//				flag = false;
//				break;
//			case "5":
//				while(true) {
//					try {
//						int choosePage = Integer.parseInt(Tools.getChoose("请输入你选择的页数(请输入"+this.start+"~"+this.last+"之内的数字)："));
//						if(choosePage<this.start||choosePage>this.last) {
//							System.out.println(Constant.error);
//						}else {
//							this.pages=choosePage;
//							flag = false;
//							break;
//						}
//						
//					} catch (Exception e) {
//						System.out.println(Constant.error);
//					}
//				}			
//				break;
//			case "0":
//				flag = false;
//				System.out.println(Constant.goBack);
//				return false;
//			default:
//				System.out.println(Constant.error);
//				break;
//			}
//		}
//		return true;
//	}
}