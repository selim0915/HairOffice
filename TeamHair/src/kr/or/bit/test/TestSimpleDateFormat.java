package kr.or.bit.test;

public class TestSimpleDateFormat {

	
		  
		 public static void main(String[] args){
		  try {
		   
		   String textDate = "20070722";
		   java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
		   java.util.Date date = format.parse(textDate);
		   java.text.SimpleDateFormat format1 = new java.text.SimpleDateFormat("yyyy년MM월dd일 HH시mm분ss초");
		   String dateString = format1.format(date);
		   
		   System.out.println(dateString);
		  } catch (java.text.ParseException ex) {
		   ex.printStackTrace();
		  }
		   
		 }
}
