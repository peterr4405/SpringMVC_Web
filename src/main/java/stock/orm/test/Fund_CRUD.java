package stock.orm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.orm.dao.IFundDao;
import stock.orm.dao.IInvestorDao;
import stock.orm.dao.IStockDao;
import stock.orm.dao.ITraderDao;
import stock.orm.model.Fund;
import stock.orm.model.FundNet;
import stock.orm.model.Investor;
import stock.orm.model.Stock;
import stock.orm.model.Trader;

public class Fund_CRUD {

    private static ApplicationContext context;
    private static IFundDao fundDao;
    private static IStockDao stockDao;
    private static ITraderDao traderDao;
    private static IInvestorDao investorDao;
    

    static {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        fundDao = (IFundDao) context.getBean("fundDao");
        stockDao = (IStockDao) context.getBean("stockDao");
        traderDao = (ITraderDao)context.getBean("traderDao");
        investorDao = (IInvestorDao)context.getBean("InvestorDao");
        
    }

    public static void main(String[] args) {

         create("A", "高科技");
         create("B", "高收益");
         create("C", "高股息");        
         addStock("A", "2330", "3008", "2303");
        addTrader("A", "John","Mary");
        addInvestior("A", "Lucky");
        find("A");
    }

    public static void create(String name, String desc) {

        Fund fund = new Fund(name, desc);

        FundNet fundnet = new FundNet();
        fundnet.setValue(10);
        fundnet.setFund(fund);
        fund.setFundnet(fundnet);
        fundDao.create(fund);
    }

    public static void addStock(String fundName, String... stockCodes) {

        Fund fund = fundDao.find(fundName);

        for (String stockCode : stockCodes) {
            // 查找 stock
            Stock stock = stockDao.findByCode(stockCode);
            // 加入股票
            fund.getStocks().add(stock);
        }

        fundDao.update(fund);
    }

    public static void addTrader(String fundName, String ... traderNames) {
       Fund fund = fundDao.find(fundName);
        
          for(String traderName : traderNames) {
            // 查找 trader
            Trader trader = traderDao.findByName(traderName);
            // 加入 trader
            fund.getTraders().add(trader);
        }
        fundDao.update(fund);
    }
    
    public static void addInvestior(String name,String ... InvestorNames){
        Fund fund = fundDao.find(name);
        for(String investor : InvestorNames){
            Investor inverstor = investorDao.findByName(investor);
            fund.getInvestors().add(inverstor);
        }
        fundDao.update(fund); 
    
    } 

    public static void find(String fundName) {
        // 查找新基金
        Fund fund = fundDao.find(fundName);
        System.out.println("基金名稱: " + fund.getName() + " " + fund.getDesc());
        System.out.print("成分股:");
        fund.getStocks().stream().forEach(e -> System.out.print(" " + e.getStockName()));
        System.out.print("\n操盤人:");
        fund.getTraders().stream().forEach(e -> System.out.print(" " + e.getName()));
        System.out.print("\n投資人:");
        fund.getInvestors().stream().forEach(e -> System.out.print(" " + e.getName()));

    }

}
