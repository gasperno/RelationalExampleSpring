package com.company.enterprise;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.company.enterprise.dao.EmployeeDAO;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "10,20,30,40,50 * * * * *")
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }

//    @Scheduled(fixedRate=10000)
//    public void testRunDBConn(){
//
//        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        EmployeeDAO employeeDAO = (EmployeeDAO) context.getBean("employeeDAO");
//
//        Employee employee4 = employeeDAO.findById(456);
//        String name = employeeDAO.findNameById(456);
//        System.out.println(name);
//
//        List<Employee> employees = employeeDAO.findAll();
//        System.out.println(employees);
//        context.close();
//    }
}
