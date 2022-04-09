package it.seba.metodologie.ex02;

import it.seba.metodologie.ex02.exception.NotEnoughMoneyException;

/**
 * Manage a Bank Account
 * 
 * @author Sebastian Rapetti
 *
 */
public class ContoCorrente {

	/**
	 * Current amount of money into the account.
	 */
	private double balance;

	/**
	 * Customer account id
	 */
	private String id;

	/**
	 * Class Constructor
	 * 
	 * @param idUtente       customer identification number
	 * @param valoreIniziale initial amount of money for the account
	 */
	public ContoCorrente(String idUtente, double valoreIniziale) {
		id = idUtente;
		balance = valoreIniziale;
	}

	/**
	 * Class constructor overloading. To start using only customer id and zero money
	 * into the account
	 * 
	 * @param idUtente customer identification number
	 */
	public ContoCorrente(String idUtente) {
		this(idUtente, 0.0);
	}

	/**
	 * Returns current account balance
	 * 
	 * @return account balance
	 */
	public double getImporto() {
		return balance;
	}

	/**
	 * Returns customer id
	 * 
	 * @return the customer id
	 */
	public String getIdUtente() {
		return id;
	}

	/**
	 * Withdraw an amount of money
	 * 
	 * @param importo amount of money to withdraw
	 * @return the money
	 * @throws NotEnoughMoneyException
	 */
	public double preleva(double importo) throws NotEnoughMoneyException {
		// check for enough money on account
		if (balance - importo < 0.0) {
			throw new NotEnoughMoneyException();
		}
		// update the balance
		balance -= importo;

		return importo;
	}

	/**
	 * Withdraw all money from the account
	 * 
	 * @return account balance
	 */
	public double svuota() {
		double tmpBalance = balance;
		balance = 0.0;

		return tmpBalance;
	}

	/**
	 * Deposit money into the account
	 * 
	 * @param amount of money to deposit
	 */
	public void versa(double importo) {
		balance += importo;
	}
}
