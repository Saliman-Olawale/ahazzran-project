package ci.orange.digital.center.ahazzran.webservice.dtos;

public class ApprenantOuputDto {

        private String email;
        private int rang;
        private int xp;
    
        public ApprenantOuputDto(String email, int rang, int xp) {
            this.email = email;
            this.rang = rang;
            this.xp = xp;
        }
    
        public String getEmail() {
            return email;
        }
    
        public int getRang() {
            return rang;
        }
    
       
        public int getXp() {
            return xp;
        }
    
    
}
    
    

