package iburgershop;

/**
 *
 * @author Ravindu Yasith
 */
public class Burger {
    private String orderId;
    private String customerId;
    private String customerName;
    private int orderQuantity;
    private int orderStatus;
    
    private final double BURGERPRICE = 500.00;

    public Burger() {
    }
    
    //--constructor for best Customer
    public Burger(String customerId,String customerName,int orderQuantity){
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderQuantity = orderQuantity;
    }

    public Burger(String orderId, String customerId, String customerName, int orderQuantity, int orderStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderQuantity = orderQuantity;
        this.orderStatus = orderStatus;
    }

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the orderQuantity
     */
    public int getOrderQuantity() {
        return orderQuantity;
    }

    /**
     * @param orderQuantity the orderQuantity to set
     */
    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    /**
     * @return the orderStatus
     */
    public int getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean equals(Burger burger) {
        return this.orderId.equalsIgnoreCase(burger.orderId); 
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return orderId+","+customerId+","+customerName+","+Integer.toString(orderQuantity)+","+Integer.toString(orderStatus);
    }
    
    //--Other helping methods
    public double getBURGERPRICE(){
        return BURGERPRICE;
    }
    
    public String getOrderStatusInText(){
//        return(orderType==0)?"Canceled":(orderType==1)?"Preparing":(orderType==2)?"Delivered":null;
        return(orderStatus==0)?"Canceled":(orderStatus==1)?"Preparing":(orderStatus==2)?"Delivered":"Invalid";
    }
    
    public int getOrderStatusInNumber(String orderType){
        return(orderType.equalsIgnoreCase("Canceled"))? 0 :(orderType.equalsIgnoreCase("Preparing"))?1:(orderType.equalsIgnoreCase("Delivered"))?2:-1;
    }
    
    public double getOrderTotalAmount(){
        return (BURGERPRICE*orderQuantity);
    }
    
}
