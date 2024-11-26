package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        Department department = new Department(1,"Books");

        System.out.println(department);

        Seller seller = new Seller(1,"Gabriel","gab@gmai.com",new Date(),3000.00,department);

        SellerDao sellerDao = new DaoFactory().createSellerDao();

        System.out.println(seller);
    }
}
