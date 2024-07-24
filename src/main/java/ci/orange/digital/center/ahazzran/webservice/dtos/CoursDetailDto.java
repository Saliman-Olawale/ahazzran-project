package ci.orange.digital.center.ahazzran.webservice.dtos;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;

import ci.orange.digital.center.ahazzran.webservice.entities.ContenuLangue;
import ci.orange.digital.center.ahazzran.webservice.entities.ICoursDetail;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class CoursDetailDto {

    private String nomQuiz;
    private String correctMotLangue;
    private String correctAudio;
    private String motFrancais;
    private String audio;
    private String enonce;
    private int templateId;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Optional<Integer> contenuLangueId;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Optional<Integer> dictionnaireId;
    private String codeTemplate;

    private List<AudioDto> audios;
    private List<ListMotsFrDto> propositionFr;
    private List<ListMotsLangDto> propositionLang;
    private List<MotsDto> motLangues;
    private List<ImagesDto> pictures;

    // Pour le template de SonEtMotService
    public CoursDetailDto(ICoursDetail coursDetail, List<AudioDto> audios, List<MotsDto> motLangues){
        
        this.nomQuiz = coursDetail.getCoursDetailTemplate().getLibelle();
        this.templateId = coursDetail.getId();
        this.codeTemplate  = coursDetail.getCoursDetailTemplate().getCode();
        this.audios = audios;

        this.motLangues = motLangues;
    }

    // Pour le template de MotsEtImageService
    public CoursDetailDto(List<MotsDto> motLangues, List<ImagesDto> pictures, ICoursDetail coursDetail){
        this.nomQuiz = coursDetail.getCoursDetailTemplate().getLibelle();
        this.templateId = coursDetail.getId();
        this.codeTemplate  = coursDetail.getCoursDetailTemplate().getCode();

        this.pictures = pictures;
        this.motLangues = motLangues;
    }

    // Pour le template de ChoisirPhraseJusteService
    public CoursDetailDto(ICoursDetail coursDetail, ContenuLangue contenuLangue, List<ListMotsFrDto> incorrectMots){
        this.nomQuiz = coursDetail.getCoursDetailTemplate().getLibelle();
        this.templateId = coursDetail.getId();
        this.codeTemplate  = coursDetail.getCoursDetailTemplate().getCode();

        this.contenuLangueId = Optional.of(contenuLangue.getContenuLangueId());
        this.dictionnaireId = Optional.of(contenuLangue.getDictionnaire().getDictionnaireId());
        this.correctMotLangue = contenuLangue.getMot();
        this.correctAudio = contenuLangue.getAudio();

        this.propositionFr = incorrectMots;
    }

    // Pour le template de PhraseEnLangueService
    public CoursDetailDto(ICoursDetail coursDetail, List<ListMotsLangDto> incorrectMots, ContenuLangue contenuLangue){
        this.nomQuiz = coursDetail.getCoursDetailTemplate().getLibelle();
        this.templateId = coursDetail.getId();
        this.codeTemplate  = coursDetail.getCoursDetailTemplate().getCode();

        this.contenuLangueId = Optional.of(contenuLangue.getContenuLangueId());
        this.dictionnaireId = Optional.of(contenuLangue.getDictionnaire().getDictionnaireId());
        this.motFrancais = contenuLangue.getDictionnaire().getMot();

        this.propositionLang = incorrectMots;
    }

    // Pour la comparaison vocale

    public CoursDetailDto(ICoursDetail coursDetail,ContenuLangue contenuLangue) {
        this.nomQuiz = coursDetail.getCoursDetailTemplate().getLibelle();
        this.templateId = coursDetail.getId();
        this.codeTemplate  = coursDetail.getCoursDetailTemplate().getCode();

        this.contenuLangueId = Optional.of(contenuLangue.getContenuLangueId());

        this.audio = contenuLangue.getAudio();
        this.enonce = contenuLangue.getMot();
    }

    
    public static class ListMotsLangDto {
        private String mot;
        private int id;


        public ListMotsLangDto(ContenuLangue contenuLangue) {
            this.mot = contenuLangue.getMot();
            this.id = contenuLangue.getDictionnaire().getDictionnaireId();

        }

        public String getMot() {
            return mot;
        }
        
        public int getId() {
            return id;
        }
      
    }

    public static class ListMotsFrDto {
        private String mot;
        private int id;


        public ListMotsFrDto(ContenuLangue contenuLangue) {
            this.mot = contenuLangue.getDictionnaire().getMot();
            this.id = contenuLangue.getDictionnaire().getDictionnaireId();

        }

        public String getMot() {
            return mot;
        }
        
        public int getId() {
            return id;
        }
      
    }
    
    public static class AudioDto {
        private String audio;
        private int id;

        public AudioDto(ContenuLangue contenuLangue) {
            this.audio = contenuLangue.getAudio();
            this.id = contenuLangue.getContenuLangueId();
        }


        public String getAudio() {
            return audio;
        }


        public int getId() {
            return id;
        }

          
    }

    public static class MotsDto{
        private String mot;
        private int id;

        public MotsDto(ContenuLangue contenuLangue) {
            this.mot = contenuLangue.getMot();
            this.id = contenuLangue.getContenuLangueId();
        }

        public String getMot() {
            return mot;
        }

        public int getId() {
            return id;
        }   

    }

    public static class ImagesDto {
        private String picture;
        private int id;

        public ImagesDto(ContenuLangue contenuLangue) {
            this.picture = contenuLangue.getDictionnaire().getImage();
            this.id = contenuLangue.getDictionnaire().getDictionnaireId();
        }


        public String getPicture() {
            return picture;
        }


        public int getId() {
            return id;
        }

          
    }

    public String getCorrectMotLangue() {
        return correctMotLangue;
    }



    public String getCorrectAudio() {
        return correctAudio;
    }

    public String getMotFrancais() {
        return motFrancais;
    }

    public List<AudioDto> getAudios() {
        return audios;
    }

    public List<ListMotsFrDto> getPropositionFr() {
        return propositionFr;
    }

    public List<ListMotsLangDto> getPropositionLang() {
        return propositionLang;
    }

    public List<MotsDto> getMotLangues() {
        return motLangues;
    }

    public List<ImagesDto> getPictures() {
        return pictures;
    }

    public String getNomQuiz() {
        return nomQuiz;
    }

    public String getAudio() {
        return audio;
    }

    public String getEnonce() {
        return enonce;
    }

    public int getTemplateId() {
        return templateId;
    }

    public String getCodeTemplate() {
        return codeTemplate;
    }

    public Optional<Integer> getContenuLangueId() {
        return contenuLangueId;
    }

    public Optional<Integer> getDictionnaireId() {
        return dictionnaireId;
    }
   

}
