package ci.orange.digital.center.ahazzran.webservice.dtos;




public class DefinirCoursInputOuputDto {
    
    private int id_typeOfContent;
    private int id_template;
    private int id_contenuLangue;
    private int recompense;


    public DefinirCoursInputOuputDto(){
        super();
    }
    public int getId_TypeOfContent() {
        return id_typeOfContent;
    }

    public int getId_template() {
        return id_template;
    }

    public int getId_contenuLangue() {
        return id_contenuLangue;
    }  

    public int getRecompense() {
        return recompense;
    }
}
