package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        SellerDao sellerDao = new DaoFactory().createSellerDao();

        System.out.println("Test 1: seller find by id ====: ");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("Test 2: seller find by department ====: ");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);

        System.out.println("Test 3: seller findAll ====: ");
        list = sellerDao.findAll();
        list.forEach(System.out::println);

        System.out.println("Test 4: seller Insert: ");
        Seller newSeller = new Seller(null,"Gabriel","gabs@gmail.com",new Date(),
                1700.00,new Department(2,null));

        sellerDao.insert(newSeller);
        System.out.println("Inserted! new id == " + newSeller.getId());

        System.out.println("Test 5: seller Update: ");
        seller = sellerDao.findById(10);
        seller.setName("Gabriel Ferreira");
        sellerDao.update(seller);
        System.out.println("Update Completed!");

        System.out.println("Test 6: seller Delete: ");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        sc.close();
    }
}
