// specify the package
package model;

// system imports
import java.util.Vector;
import javax.swing.JFrame;

// project imports

/** The class containing the TransactionFactory for the ATM application */
//==============================================================
public class TransactionFactory
{

	/**
	 *
	 */
	//----------------------------------------------------------
	public static Transaction createTransaction(String transType,
		AccountHolder cust)
		throws Exception
	{
		Transaction retValue = null;

		if (transType.equals("AddBook") == true)
		{
			retValue = new DepositTransaction(cust);
		}
		else
		if (transType.equals("SearchBook") == true)
		{
			retValue = new WithdrawTransaction(cust);
		}
		else
		if (transType.equals("AddPatron") == true)
		{
			retValue = new TransferTransaction(cust);
		}
		else
		if (transType.equals("SearchPatron") == true)
		{
			retValue = new BalanceInquiryTransaction(cust);
		}
		else
		if (transType.equals("ImposeServiceCharge") == true)
		{
			retValue = new ImposeServiceChargeTransaction(cust);
		}

		return retValue;
	}
}
