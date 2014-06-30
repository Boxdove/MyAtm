package myatm;

import static org.mockito.Mockito.mock;

public class ATM {

	protected Card card;
	protected Account account;
	public double moneyInATM;
	protected double accountBalance;

	public ATM(Account account, Card card) {
		this.account = account;
		this.card = card;

	}

	// Можно задавать количество денег в банкомате
	ATM(double moneyInATM) {
	}

	// проверяем пин-код и блокировку карты
	public boolean validateCard() {
		return card.isBlocked();
	}

	public boolean validatePincode(int pinCode) {
		return card.checkPin(pinCode);
	}

	// если заблокирована или неверный пин-код - исключение

	public void checkValidCard(Card card, int pinCode) throws NoCardInserted {
		if (validateCard() == true)
			throw new NoCardInserted();
		if (validatePincode(pinCode) == false)
			throw new NoCardInserted();
		else {
		}
	}

	// количество денег на счету
	public double getBalance() {
		return account.getBalance();
	}

	// снятие суммы
	public double getCash(double ammount, double accountBalance,
			double moneyInATM) throws NotEnoughMoneyInAccount,
			NotEnoughMoneyInATM {

		if (ammount > account.getBalance()) {
			throw new NotEnoughMoneyInAccount();
		}
		if (ammount > moneyInATM) {
			throw new NotEnoughMoneyInATM();
		} else {
			account.withdraw(ammount);
			accountBalance = account.getBalance() - ammount;
		}
		return accountBalance;
	}

	
	public static void main(String[] args) {
		double moneyInATM = 1000;
		double ammount = 200;
		double accountBalance = 500;

		ATM atm = new ATM(moneyInATM);
		Account account = mock(Account.class);
		Card card = mock(Card.class);
		atm.validateCard();
		atm.validatePincode(1234);
		try {
			atm.checkValidCard(card,1234);
		} catch (NoCardInserted em) {
			em.printStackTrace();
		}
		atm.getBalance();
		try {
			atm.getCash(ammount, accountBalance, moneyInATM);
		} catch (NotEnoughMoneyInAccount e) {
			e.printStackTrace();
		} catch (NotEnoughMoneyInATM e) {
			e.printStackTrace();
		}
	}

}
