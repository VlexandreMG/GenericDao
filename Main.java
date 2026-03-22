import model.Olona;
import dao.GenericDao;

public class Main {
    
    public static void main(String[] args) throws Exception {
        Olona test = new Olona();
        test.setId(0);
        test.setNom("shee");

        Olona ahh = new Olona();
        ahh.setId(2);
        ahh.setNom("shee");
        
        try {
            GenericDao.delete(ahh);
        } catch (Exception e) {
            // TODO: handle exceptiont
            throw e;

        }
    }
}
