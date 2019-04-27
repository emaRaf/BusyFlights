package lz.dao;

import org.springframework.stereotype.Repository;

import lz.model.Card;
import lz.model.Cards;

@Repository
public class CardDAO {
    private static Cards list = new Cards();

    static {
	list.getEmployeeList().add(new Card("bank1", "num1", "exp1"));
	list.getEmployeeList().add(new Card("bank2", "num2", "exp2"));
	list.getEmployeeList().add(new Card("bank3", "num3", "exp3"));
    }

    public Cards getAllEmployees() {
	return list;
    }

    public void addEmployee(Card employee) {
	list.getEmployeeList().add(employee);
    }
}