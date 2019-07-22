package stock.orm.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.orm.dao.IFundDao;
import stock.orm.dao.IInvestorDao;
import stock.orm.model.Fund;
import stock.orm.model.Investor;


public class Investor_CRUD {
    private static ApplicationContext context;
    private static IInvestorDao InvestorDao;
    private static IFundDao fundDao;
    
    
    static{
    context = new ClassPathXmlApplicationContext("applicationContext.xml");
    InvestorDao = (IInvestorDao)context.getBean("InvestorDao");
    fundDao = (IFundDao)context.getBean("fundDao");
    }
    
    
    public static void main(String[] args) {

        add("Lucky", 10000, "A");
 /*      queryAll().stream()
                .forEach(e -> {
                    System.out.printf("%s %.0f %d %s \n", e.getName(), e.getUnits(), e.getNetValue(), e.getFund().getName());
               });
 */       
     queryAll().stream().forEach(e ->System.out.println(e.getName()+" "+e.getUnits()+" "+e.getNetValue()+" "+e.getFund().getName()));
        
    }
    
    public static void add(String investorName,double units , String fundName){
        Fund fund = fundDao.find(fundName);
        Investor inv = new Investor(investorName);
        inv.setUnits(units);
        inv.setFund(fund);
        InvestorDao.create(inv);
    }
    
    public static List<Investor> queryAll(){
    
    return InvestorDao.queryAll(Investor.class);
    }
    
}
