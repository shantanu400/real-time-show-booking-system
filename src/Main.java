import controller.AdminController;
import controller.ShowController;
import entity.Show;
import repository.AdminRepository;
import repository.ShowRepository;
import service.AdminServices;
import service.ShowServices;

import java.time.LocalDateTime;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


   /*   Admin */
        AdminRepository adminRepository=new AdminRepository();
        AdminServices adminServices=new AdminServices(adminRepository);
        AdminController adminController=new AdminController(adminServices);
        adminController.addMovie("M1","little krishna",20,"hindi");
        adminController.addTheater("T1","RadhaVenimadhav Media","Goloka");
        adminController.addScreen(
                "SC1",
                "Nityananda",
                "T1",
                6,
                3
        );
        adminController.createShow("M1","SC1", LocalDateTime.now().plusHours(2),300);

        /* User  */
        ShowRepository showRepository = new ShowRepository(adminRepository);
        ShowServices showServices = new ShowServices(showRepository);
        ShowController showController = new ShowController(showServices);
        List<Show> shows=showController.searchShow("little krishna");
        for (Show show : shows) {
            System.out.println(show);
        }
    }
}