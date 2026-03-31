import model.Olona;
import model.Mapping;
import dao.GenericDao;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) throws Exception {

        //List<Mapping> listMapping = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        Mapping mapping = new Mapping();

        Olona test = new Olona();
        test.setId(0);
        test.setNom("shee");

        Olona ahh = new Olona();
        ahh.setId(2);
        ahh.setNom("shee");
        
        try {
            // listMapping = mapping.getAllClass("model");
            // System.out.println(listMapping + "\n");
            genericDao.save(test);
        } catch (Exception e) {
            // TODO: handle exceptiont
            throw e;
        }
    }
}
