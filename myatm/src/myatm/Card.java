/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myatm;

public interface Card {
  //������������� �� ����� ��� ���
    public boolean isBlocked();
    
  //���������� ���� ��������� � ������ ������
    public Account getAccount();

  //��������� ������������ ���-����
    public boolean checkPin(int pinCode);    
}
