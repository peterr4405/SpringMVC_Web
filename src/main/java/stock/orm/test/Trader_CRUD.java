package stock.orm.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.orm.dao.ITraderDao;
import stock.orm.model.Trader;


public class Trader_CRUD {
    private static ApplicationContext context;
    private static ITraderDao traderDao;
    
    static{
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        traderDao = (ITraderDao)context.getBean("traderDao");
    
    }
    
    
    
    public static void main(String[] args) {
       
        add();
        //update("Mary", "Mery");
        //delete("Mery");
        queryAll().stream().forEach(e ->System.out.println(e.getName()));
    }
    
    public static void add(){
    Trader t1 = new Trader("Mary");
    Trader t2 = new Trader("John");
    Trader t3 = new Trader("Sam");
    traderDao.create(t1);
    traderDao.create(t2);
    traderDao.create(t3);
        
    }
    
    public static void update(String name,String name2){
    Trader trader = traderDao.findByName(name);
    if(trader != null){
        trader.setName(name2);
        traderDao.update(trader);
    }

    }
    
    public static void delete(String name){
        Trader trader = traderDao.findByName(name);
        if(trader != null){
        traderDao.delete(trader);          
        }
    
    }
    
    public static List<Trader> queryAll(){
    return traderDao.queryAll(Trader.class);
    }
    
}
