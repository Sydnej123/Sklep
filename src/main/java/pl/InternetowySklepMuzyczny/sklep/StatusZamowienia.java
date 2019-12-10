package pl.InternetowySklepMuzyczny.sklep;

public class StatusZamowienia {
    //ODRZUCONE, ZREALIZOWANE, W_TRAKCIE,
    public static String getStatusById(Integer id) {
        if (id == 0) {
            return "W trakcie realizacji";
        }else if(id == 1){
            return "Zrealizowane";
        }else if(id == 2){
            return "Odrzucone";
        }else{
            return "Błąd";
        }
    }

    }