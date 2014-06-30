package myatm;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InOrder;

public class ATMtest {

	@Test
	public void getValidateCardMethod() {
		Card card = mock(Card.class);
		Account acc = mock(Account.class);
		ATM atm = new ATM(acc, card);
		card.isBlocked();
		atm.validateCard();
		verify(card, atLeastOnce()).isBlocked();
	}

	@Test
	public void ValidateCardParametr() {
		Card card = mock(Card.class);
		Account acc = mock(Account.class);
		ATM atm = new ATM(acc, card);
		when(card.isBlocked()).thenReturn(true);
		boolean result = atm.validateCard();
		assertEquals(true, result);
	}

	@Test
	public void getValidatePinCodeMethod() {
		Card card = mock(Card.class);
		Account acc = mock(Account.class);
		ATM atm = new ATM(acc, card);
		card.checkPin(1234);
		atm.validatePincode(1234);
		verify(card, atLeastOnce()).checkPin(1234);
	}

	@Test
	public void PinCodeParametr() {
		Card card = mock(Card.class);
		Account acc = mock(Account.class);
		ATM atm = new ATM(acc, card);
		when(card.checkPin(1234)).thenReturn(true);
		boolean result = atm.validatePincode(1234);
		assertEquals(true, result);
	}

	@Test(expected = NoCardInserted.class)
	public void checkIfCardIsNotBlockedThrowsException() throws NoCardInserted {
		Card card = mock(Card.class);
		Account acc = mock(Account.class);
		ATM atm = new ATM(acc, card);
		when(card.isBlocked()).thenReturn(true);
		atm.checkValidCard(card,1234);
	}

	@Test(expected = NoCardInserted.class)
	public void checkIfPincodeCorrectThrowsException() throws NoCardInserted {
		Card card = mock(Card.class);
		Account acc = mock(Account.class);
		ATM atm = new ATM(acc, card);
		when(card.checkPin(1234)).thenReturn(true);
		atm.checkValidCard(card, 2345);
	}

	@Test
	public void getGettingBalanceMethod() {
		Account acc = mock(Account.class);
		Card card = mock(Card.class);
		ATM atm = new ATM(acc, card);
		when(acc.getBalance()).thenReturn(500.0);
		atm.getBalance();
		verify(acc, atLeastOnce()).getBalance();
	}

	@Test
	public void checkBalanceGetting() {
		Account acc = mock(Account.class);
		Card card = mock(Card.class);
		ATM atm = new ATM(acc, card);
		when(acc.getBalance()).thenReturn(500.0);
		double expected = 500;
		double actual = atm.getBalance();
		assertEquals(expected, actual, 0.01);
	}

	@Test(expected = NotEnoughMoneyInAccount.class)
	public void checkGettingAmountFromAccount() throws NotEnoughMoneyInAccount,
			NotEnoughMoneyInATM {
		Account acc = mock(Account.class);
		Card card = mock(Card.class);
		ATM atm = new ATM(acc, card);
		double ammount = 500.0;
		double accountBalance = 200;
		double moneyFromATM = 1000;
		when(acc.getBalance()).thenReturn(accountBalance);
		atm.getCash(ammount, accountBalance, moneyFromATM);
		assertEquals(ammount, accountBalance, 0.01);
	}

	@Test(expected = NotEnoughMoneyInATM.class)
	public void checkMoneyEnoughInATMThrowsException()
			throws NotEnoughMoneyInATM, NotEnoughMoneyInAccount {
		double ammount = 500;
		double moneyInATM = 200;
		double accountBalance = 1000;
		Account acc = mock(Account.class);
		Card card = mock(Card.class);
		ATM atm = new ATM(acc, card);
		when(acc.getBalance()).thenReturn(accountBalance);
		atm.getCash(ammount, accountBalance, moneyInATM);
		assertEquals(ammount, moneyInATM, 0.01);
	}

	@Test
	public void getInCorrectOrderCashMethod() throws NotEnoughMoneyInAccount,
			NotEnoughMoneyInATM {
		Account acc = mock(Account.class);
		Card card = mock(Card.class);
		ATM atm = new ATM(acc, card);
		double ammount = 500.0;
		double accountBalance = 700;
		double moneyFromATM = 1000;
		when(acc.getBalance()).thenReturn(accountBalance);
		atm.getCash(ammount, accountBalance, moneyFromATM);
		InOrder order = inOrder(acc);
		order.verify(acc, times(1)).getBalance();
		order.verify(acc, times(1)).withdraw(anyDouble());
	}

}
