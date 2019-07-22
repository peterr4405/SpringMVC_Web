package stock.orm.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.orm.dao.IStockDao;
import stock.orm.model.Stock;

public class STOCK_CRUD {

     private static ApplicationContext context;
    private static IStockDao stockDao;
    
    static{
    context = new ClassPathXmlApplicationContext("applicationContext.xml");
    stockDao = (IStockDao)context.getBean("stockDao");
    }
    
    
    public static void main(String[] args) {
        
        add();
        //update("3376", "新日興");
        //queryAll().stream().forEach(e -> System.out.println(e.getStockCode() + "\t" + e.getStockName()));
        //delete("3376");
    }
    
    public static void add(){
    Stock stock1 = new Stock("3008", "大立光");
    Stock stock2 = new Stock("3376", "新日興");    
    Stock stock3 = new Stock("2330", "台積電");
    
    stockDao.create(stock1);
    stockDao.create(stock2);
    stockDao.create(stock3);
    
    }
    
    public static void update(String stockCode,String stockName){
        Stock stock = stockDao.findByCode(stockCode);
        if(stock != null){
        stock.setStockName(stockName);
        stockDao.update(stock);
        }
    }
    
    public static List<Stock> queryAll(){
    
    return stockDao.queryAll(Stock.class);
    }
    
    public static void delete(String stockCode){
        Stock stock = stockDao.findByCode(stockCode);
        if(stock != null){
        stockDao.delete(stock);
        }
        
    }

}
