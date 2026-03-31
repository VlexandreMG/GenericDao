import model.Olona;
import dao.GenericDao;

public class Main {
    
    public static void main(String[] args) throws Exception {
        GenericDao genericDao = new GenericDao();

        Olona test = new Olona();
        test.setId(0);
        test.setNom("shee");

        Olona ahh = new Olona();
        ahh.setId(2);
        ahh.setNom("shee");
        
        try {
            // genericDao.save(ahh);
        } catch (Exception e) {
            // TODO: handle exceptiont
            throw e;

        }
    }
}
