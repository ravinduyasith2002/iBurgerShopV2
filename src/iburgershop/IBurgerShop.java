package iburgershop;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Ravindu Yasith
 */
public class IBurgerShop {
    public static void main(String[] args) {
        List burgerList1 = new List();
        burgerList1.add(new Burger("B0001","0794837261","Amali",1,2));
        burgerList1.add(new Burger("B0002","0701928374","Shehan",2,1));
        burgerList1.add(new Burger("B0003","0715647382","Kasun",4,2));
        burgerList1.add(new Burger("B0004","0729483726","Shehan",2,2));
        burgerList1.add(new Burger("B0005","0731827364","Chathura",1,1));
        burgerList1.add(new Burger("B0006","0715647382","Kasun",1,1));
        burgerList1.add(new Burger("B0007","0783927461","Rishmi",3,0));
        burgerList1.add(new Burger("B0008","0761827365","Gihan",6,2));
        burgerList1.add(new Burger("B0009","0766489623","Yasith",5,1));
        burgerList1.add(new Burger("B0010","0788489623","Dilshan",2,1));
        
      
        try {
            FileWriter fw = new FileWriter("Burger.txt");
            for(int i = 0; i< burgerList1.size(); i++){
                Burger b1 = burgerList1.get(i);
                fw.write(b1.toString()+"\n");
            }
            fw.close();
        } catch (IOException ex) {
            System.getLogger(IBurgerShop.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        
    }
    
}
