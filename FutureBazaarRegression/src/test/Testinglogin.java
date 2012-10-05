package test;
import com.futurebazaar.base.*;
import com.futurebazaar.common.methods.Cash;
import com.futurebazaar.common.methods.CreditCard;
import com.futurebazaar.common.methods.DebitCard;
import com.futurebazaar.common.methods.ForTestAccountlink;
import com.futurebazaar.common.methods.Itemcleanup;
import com.futurebazaar.common.methods.Loginlogout;
import com.futurebazaar.common.methods.Itemcleanup;
import com.futurebazaar.common.methods.Netbanking;
import com.futurebazaar.common.methods.PaymentPage;
import com.futurebazaar.common.methods.Productpurchase;

public class Testinglogin extends TestBase{

	
	public static void main(String[] args) throws Exception {
	
		
		
		
		
		
		//Loginlogout.getInstance().loginByEmail();
		//Loginlogout.loginByEmail("pawan1@gmail.com","p0513231068");
		//Loginlogout.loginByFacebook("pawan_garia@yahoo.com","224697");
		//Loginlogout.loginGuestflowFacebook("pawan_garia@yahoo.com","224697");
		/*
		Loginlogout.LoginByPhoneNo("5121324532","p0513231068");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.quit();
		Loginlogout.logOut();
		*/
		TestBase.initialize();
		TestBase.getURLOpen();
		//Loginlogout.loginByMyAccount("pawan1@gmail.com","p0513231068");
		//Loginlogout.newUserRegistration("pa@ss.com","p0513231068");
	   //Itemcleanup.itemCleanup();
		//Itemcleanup.itemCleanupForUser("pawan1@gmail.com","p0513231068");
		
		Productpurchase.purchaseItem("pawan1@gmail.com","p0513231068","mobile");
		
		//Loginlogout.loginByMyAccount("pawan1@gmail.com","p0513231068");
		//Productpurchase.purchaseItem("mobile");
		//PaymentPage.paymentPageOptionsVerification();
		
		//CreditCard cc = new CreditCard();
		//cc.creditcardVerification("1312312313","Feb","2014","123","pawan");
		
		//DebitCard dd = new DebitCard();
		//dd.debitCardVerification("HDFC","1312312313","Feb","2014","123","pawan");
	   
		//Cash cc = new Cash();
		//cc.cashVerification();
		
		//DebitCard dd = new DebitCard();
		//dd.debitCardVerification("HDFC","1312312313","Feb","2014","123","pawan");
		
		//Netbanking nn = new Netbanking();
		//nn.netbankingverification();
		// cc.getShippingDetails("pawan1@gmail.com","p0513231068","mobile");
	
		//ForTestAccountlink nn = new ForTestAccountlink();
		//nn.alllinksTest();
	}

}
