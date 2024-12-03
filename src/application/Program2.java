package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = new DaoFactory().createDepartmentDao();

        System.out.println("Test 1: Department find by id == : ");
        Department dep = departmentDao.findById(3);
        System.out.println(dep);

        System.out.println("Test 2: Department findAll == : ");
        List<Department> list = departmentDao.findAll();
        list.forEach(System.out::println);

        System.out.println("Test 3: Department insert == : ");
        Department newDep = new Department(null,"Sexshop");
        departmentDao.insert(newDep);
        System.out.println("Inserted! new id == " + newDep.getId());

        System.out.println("Test 4: Department update == : ");
        Department newDep2 = departmentDao.findById(3);
        newDep2.setName("Sex Shop Do Bom");
        departmentDao.update(newDep2);
        System.out.println("Updated! new Name == " + newDep2.getId());

        System.out.println("Test 5: Department delete == : ");
        System.out.print("Enter id for delete: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);

        sc.close();

    }
}
