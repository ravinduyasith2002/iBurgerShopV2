
package iburgershop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Ravindu Yasith
 */
public class BurgerController {
    public static String getNewOrderId(){
        try{
            String line;
            try (Scanner input = new Scanner(new File("Burger.txt"))) {
                line = null;
                while(input.hasNext()){
                    line = input.nextLine();
                }
            }
            if(line==null){
                return "B0001";
            }else{
                String lastId = line.substring(0,5);
                int lastIdNo = Integer.parseInt(lastId.substring(1));
                return String.format("B%04d", lastIdNo+1);
            }
        }catch(FileNotFoundException ex){
            return null;
        }
    }
    
    //--search customer existance
    static String checkForCustomerId(String customerId) {
        try{
            Scanner input = new Scanner(new File("Burger.txt"));
            String line = null;
            while(input.hasNext()){
                line = input.nextLine();
                String[] customerdetails = line.split(",");
                if(line!=null && customerdetails[1].equals(customerId)){
                    return customerdetails[2];
                }
            }
            return null;
        }catch(FileNotFoundException ex){
            return null;
        }
    }
    //--Search order details for search Order
    static Burger findOrderDetails(String orderId) { 
        Burger foundOrder = findOrder(orderId);
        if(foundOrder!=null){
            return foundOrder;
        }else{
            return null;
        }
    }
    //-- from -> Search order details for search Order
    private static Burger findOrder(String orderId) {
        try{
            Scanner input = new Scanner(new File("Burger.txt"));
            String line = null;
            while(input.hasNext()){
                line = input.nextLine();
                String[] rowData = line.split(",");
                if(rowData[0].equalsIgnoreCase(orderId)){
                    Burger foundOrder = new Burger(rowData[0],rowData[1],rowData[2],Integer.parseInt(rowData[3]),Integer.parseInt(rowData[4]));
                    return foundOrder;
                }
            }
            return null;
        }catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
    //--Find orders of a given customer for search customer details
    static List findOrderDetailsOfGivenCustomer(String customerId) {
        try{
            List ordersOfGivenCustomer = new List();
            Scanner input = new Scanner(new File("Burger.txt"));
            String line = null;
            String foundId = null;
            while(input.hasNext()){
                line = input.nextLine();
                String[] rowData = line.split(",");
                if(customerId.equalsIgnoreCase(rowData[1])){
                    foundId=rowData[1];
                    ordersOfGivenCustomer.add(new Burger(rowData[0],rowData[1],rowData[2],Integer.parseInt(rowData[3]),Integer.parseInt(rowData[4])));
                }
            }
            return (foundId==null)?null:ordersOfGivenCustomer;
        }catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
    //--create order list for view orders
    static List generateOrderList(String orderType) {
        try{
            Scanner input = new Scanner(new File("Burger.txt"));
            String line = null;
            String hasThisType = null;
            List orderList = new List();
            Burger burger = new Burger();
            while(input.hasNext()){
                line = input.nextLine();
                String[] rowData = line.split(",");
                if(Integer.parseInt(rowData[4])==burger.getOrderStatusInNumber(orderType)){
                    hasThisType = rowData[4];
                    orderList.add(new Burger(rowData[0],rowData[1],rowData[2],Integer.parseInt(rowData[3]),Integer.parseInt(rowData[4])));
                }
            }
            return (hasThisType!=null)?orderList:null;
        }catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
    //--For best customer
    static List getBestCustomerList() {
        try{
            Scanner input = new Scanner(new File("Burger.txt"));
            String line = null;
            int lineCount = 0;
            while(input.hasNext()){
                lineCount++;
                line = input.nextLine();
            }
            if(lineCount==0){
                return null;
            }else{
                Burger[] duplicatedOrders = new Burger[lineCount];
                Scanner input2 = new Scanner(new File("Burger.txt"));
                String line2 = null;
                int index =0;
                while(input2.hasNext()){
                    line2 = input2.nextLine();
                    String[] rowData = line2.split(",");
                    duplicatedOrders[index++] = new Burger(rowData[1],rowData[2],Integer.parseInt(rowData[3]));
                }
                if(duplicatedOrders!=null){
                    Burger[] nonDuplicatedOrders = removeDuplicates(duplicatedOrders);
                    if(nonDuplicatedOrders==null){
                        return null;
                    }else{
                        List sortedBestCustomerList = new List();
                        for (int i = 0; i < nonDuplicatedOrders.length; i++) {
                            sortedBestCustomerList.add(nonDuplicatedOrders[i]);
                        }
                        return sortedBestCustomerList;
                    }
                }else{
                    return null;
                }       
            }
        }catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    private static Burger[] removeDuplicates(Burger[] duplicatedOrders) {
        //remove duplicates of customer id array
        String[] nonDuplicatedCustomerIdArray = new String[0];
        for (int i = 0; i < duplicatedOrders.length; i++) {
            String id = duplicatedOrders[i].getCustomerId();
            if(!search(nonDuplicatedCustomerIdArray,id)){
                String[] tempCustomerIdArray = new String[nonDuplicatedCustomerIdArray.length+1];
                for (int j = 0; j < nonDuplicatedCustomerIdArray.length; j++) {
                    tempCustomerIdArray[j] = nonDuplicatedCustomerIdArray[j];
                }
                tempCustomerIdArray[tempCustomerIdArray.length-1] = id;
                nonDuplicatedCustomerIdArray = tempCustomerIdArray;
            }
        }
        
        //set order qunatity array and name array according to customer id array
        String[] nonDuplicatedCustomerNameArray = new String[nonDuplicatedCustomerIdArray.length];
        int[] nonDuplicatedOrderQuantityArray = new int[nonDuplicatedCustomerIdArray.length];
        for (int i = 0; i < nonDuplicatedCustomerIdArray.length; i++) {
            String id = nonDuplicatedCustomerIdArray[i];
            int total = 0;
            String name = null;
            for (int j = 0; j < duplicatedOrders.length; j++) {
                if(id.equals(duplicatedOrders[j].getCustomerId())){
                    total+=duplicatedOrders[j].getOrderQuantity();
                    name = duplicatedOrders[j].getCustomerName();
                }
            }
            nonDuplicatedOrderQuantityArray[i] = total;
            nonDuplicatedCustomerNameArray[i] = name; 
        }

        //sorting all arrays according to size of nonDuplicatedOrderQuantityArray
        for (int i = 1; i < nonDuplicatedOrderQuantityArray.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nonDuplicatedOrderQuantityArray[i]>nonDuplicatedOrderQuantityArray[j]){
                    //->quantity
                    int tempQty = nonDuplicatedOrderQuantityArray[i];
                    nonDuplicatedOrderQuantityArray[i] = nonDuplicatedOrderQuantityArray[j];
                    nonDuplicatedOrderQuantityArray[j] = tempQty;
                    //->Customer id
                    String tempCustomerId = nonDuplicatedCustomerIdArray[i];
                    nonDuplicatedCustomerIdArray[i] = nonDuplicatedCustomerIdArray[j];
                    nonDuplicatedCustomerIdArray[j] = tempCustomerId;
                    //->quantity
                    String tempName = nonDuplicatedCustomerNameArray[i];
                    nonDuplicatedCustomerNameArray[i] = nonDuplicatedCustomerNameArray[j];
                    nonDuplicatedCustomerNameArray[j] = tempName;
                }
            }
        }
        //creating an object array of burger[]
        Burger[] sortedOrders = new Burger[nonDuplicatedCustomerNameArray.length];
        for (int i = 0; i < nonDuplicatedCustomerIdArray.length; i++) {
            sortedOrders[i] = new Burger(nonDuplicatedCustomerIdArray[i],nonDuplicatedCustomerNameArray[i],nonDuplicatedOrderQuantityArray[i]);
        }
        if(sortedOrders==null){
            return null;
        }else{
            return sortedOrders;
        }
    }

    private static boolean search(String[] nonDuplicatedCustomerIdArray, String id) {
        for (int i = 0; i < nonDuplicatedCustomerIdArray.length; i++) {
            if(id.equals(nonDuplicatedCustomerIdArray[i])){
                return true;
            }
        }
        return false;
    }

    static Burger checkForUpdate(String orderId) {
        try{
            Scanner input = new Scanner(new File("Burger.txt"));
            String line = null;
            while(input.hasNext()){
                line = input.nextLine();
                String[] rowData = line.split(",");
                if(rowData[0].equalsIgnoreCase(orderId)){
                    Burger foundOne = new Burger(rowData[0],rowData[1],rowData[2],Integer.parseInt(rowData[3]),Integer.parseInt(rowData[4]));
                    return foundOne;
                }
            }
            return null;
        }catch(IOException ex){    
            ex.printStackTrace();
        }
        return null;
    }

    static boolean updateWithNewValues(Burger updatedOrder) {
        try{
           Scanner input = new Scanner(new File("Burger.txt"));
           String line = null;
           List burgerListForUpdate = new List();
           while(input.hasNext()){
               line = input.nextLine();
               String[] rowData = line.split(",");
               Burger addingOrder = new Burger(rowData[0],rowData[1],rowData[2],Integer.parseInt(rowData[3]),Integer.parseInt(rowData[4]));
               burgerListForUpdate.add(addingOrder);
           }
           boolean isUpdated = burgerListForUpdate.set(updatedOrder);
           if(isUpdated){
               boolean reWriteWithNewValues = reWriteTheTextFile(burgerListForUpdate);
               if(reWriteWithNewValues){
                   boolean confirmUpdate = isUpdatedCorrectly(updatedOrder);
                   if(confirmUpdate){
                       return true;
                   }else{
                   return false;
                    }
               }
           }else{
               return false;
           }
        }catch(IOException ex){    
            ex.printStackTrace();
        }
        return false;
    }

    private static boolean reWriteTheTextFile(List burgerListForUpdate) {
        try{
            FileWriter fw = new FileWriter("Burger.txt");
            for (int i = 0; i < burgerListForUpdate.size(); i++) {
                Burger b1 = burgerListForUpdate.get(i);
                fw.write(b1.toString()+"\n");
            }
            fw.close();
            return true;
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return false;
    }

    private static boolean isUpdatedCorrectly(Burger updatedOrder) {
        try{
            Scanner input = new Scanner(new File("Burger.txt"));
            String line = null;
            while(input.hasNext()){
                line = input.nextLine();
                String[] rowData = line.split(",");
                if(rowData[1].equalsIgnoreCase(updatedOrder.getCustomerId())){
                    if(Integer.parseInt(rowData[3])==updatedOrder.getOrderQuantity() && Integer.parseInt(rowData[4])==updatedOrder.getOrderStatus()){
                        return true;
                    }
                }
            }
            return false;
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
